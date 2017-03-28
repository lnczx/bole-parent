package com.bole.action.user;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.action.BaseController;
import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.po.model.user.UserScoreCash;
import com.bole.po.model.user.UserScoreDetail;
import com.bole.service.user.UserScoreCashService;
import com.bole.service.user.UserScoreDetailService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.bole.vo.user.UserScoreCashTotalVo;
import com.bole.vo.user.UserScoreCashVo;
import com.bole.vo.user.UserScoreDetailVo;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.DateUtil;
import com.meijia.utils.StringUtil;
import com.meijia.utils.TimeStampUtil;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthPassport;

@Controller
@RequestMapping(value = "/user")
public class UserCashController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserScoreCashService userScoreCashService;
	
	@Autowired
	private UserScoreDetailService userScoreDetailService;

	@SuppressWarnings("rawtypes")
	@AuthPassport
	@RequestMapping(value = "/cashList", method = { RequestMethod.GET })
	public String list(HttpServletRequest request, Model model, UserSearchVo searchVo, Long userIdTo) {

		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

		if (searchVo == null)
			searchVo = new UserSearchVo();
		
		
		// 如果是会员，则只能看到给自己领取返利的记录.
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		Long userId = u.getUserId();
		Short userType = u.getUserType();

		if (userType.equals(Constants.USER_TYPE_0)) {
			searchVo.setUserId(userId);
		}
		
		//处理日期的情况
		String searchDate = searchVo.getSearchDate();
		if (!StringUtil.isEmpty(searchDate)) {
			String startTimeStr = searchDate + " 00:00:00";
			Long startTime = TimeStampUtil.getMillisOfDayFull(startTimeStr) / 1000;
			searchVo.setStartAddTime(startTime);
			
			String endTimeStr = searchDate + " 23:59:59";
			Long endTime = TimeStampUtil.getMillisOfDayFull(endTimeStr) / 1000;
			searchVo.setStartEndTime(endTime);
		}
		
		
		model.addAttribute("searchModel", searchVo);
		int pageNo = ServletRequestUtils.getIntParameter(request, Constants.PAGE_NO_NAME, Constants.DEFAULT_PAGE_NO);
		int pageSize = Constants.DEFAULT_PAGE_SIZE;
		PageInfo pageInfo = userScoreCashService.selectByListPage(searchVo, pageNo, pageSize);
		List<UserScoreCash> list = pageInfo.getList();
		for (int i = 0; i < list.size(); i++) {
			UserScoreCash item = list.get(i);

			UserScoreCashVo vo = userScoreCashService.getVo(item);

			list.set(i, vo);
		}

		pageInfo = new PageInfo(list);

		model.addAttribute("contentModel", pageInfo);
		model.addAttribute("userType", userType);
		
		UserScoreCashTotalVo  userScoreCashTotalVo = new UserScoreCashTotalVo();
		
		Long searchUserId = searchVo.getUserId();
		if (!StringUtil.isEmpty(searchVo.getGameId())) {
			UserSearchVo usearchVo = new UserSearchVo();
			usearchVo.setGameId(searchVo.getGameId());
			List<User> searchUsers = userService.selectBySearchVo(usearchVo);
			if (!searchUsers.isEmpty()) {
				User searchUser = searchUsers.get(0);
				searchUserId = searchUser.getUserId();
			}
		}
		
		userScoreCashTotalVo = userScoreCashService.getTotalVo(searchUserId);
		
		model.addAttribute("userScoreCashTotalVo", userScoreCashTotalVo);
		
		return "user/cashList";
	}
	
	@AuthPassport
	@RequestMapping(value = "/cashForm", method = { RequestMethod.GET })
	public String cashForm(HttpServletRequest request, Model model, Long id) {
		
		UserScoreCash record = userScoreCashService.initPo();
		
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		Long userId = u.getUserId();
		Short userType = u.getUserType();
		
		if (id > 0L) {
			record = userScoreCashService.selectByPrimaryKey(id);
			
			if (userType.equals(Constants.USER_TYPE_0)) {
				if (!record.getUserId().equals(userId)) {
					return "404";
				}
			}
		}
		
		record.setUserId(userId);
		record.setStatus((short) 0);
		
		UserScoreCashVo vo = userScoreCashService.getVo(record);
		if (!model.containsAttribute("contentModel")) {
			model.addAttribute("contentModel", vo);
		}
		
		UserScoreCashTotalVo userScoreCashTotalVo = userScoreCashService.getTotalVo(userId);
		model.addAttribute("userScoreCashTotalVo", userScoreCashTotalVo);
		
		model.addAttribute("minScoreMoneyCash", Constants.MIN_SCORE_MONEY_CASH);
		model.addAttribute("minCashDate", Constants.MIN_CASH_DATE);
		return "user/cashForm";
	}
	
	@AuthPassport
	@RequestMapping(value = "/cashForm", method = { RequestMethod.POST })
	public String doAgentForm(HttpServletRequest request, Model model, 
			@Valid @ModelAttribute("contentModel") UserScoreCashVo formData, BindingResult result) throws NoSuchAlgorithmException {
		Long id = formData.getId();
		if (result.hasErrors())
			return cashForm(request, model, id);
		
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		Long userId = u.getUserId();	
		
		//判断是否可以进行领取返利
		BigDecimal scoreCash = formData.getScoreCash();
		
		UserScoreCashTotalVo userScoreCashTotalVo = userScoreCashService.getTotalVo(userId);
		BigDecimal totalScore = userScoreCashTotalVo.getTotalScore();
		if (totalScore.compareTo(Constants.MIN_SCORE_MONEY_CASH) == -1) {
			result.addError(new FieldError("contentModel", "userId", "返利必须满"+Constants.MIN_SCORE_MONEY_CASH+"以上才可领取."));
			return cashForm(request, model, id);
		}
		
		BigDecimal totalStore = userScoreCashTotalVo.getTotalStore();
		if (scoreCash.compareTo(totalStore) == 1) {
			result.addError(new FieldError("contentModel", "userId", "可领取数不足"));
			return cashForm(request, model, id);
		}
		
		//判断距离上次是否超过7天.
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setUserId(userId);
		searchVo.setStatus((short) 1);
		searchVo.setOrderByProperty(" order by update_time desc");
		PageInfo pageInfo = userScoreCashService.selectByListPage(searchVo, 1, 1);
		List<UserScoreCash> list = pageInfo.getList();
		if (!list.isEmpty()) {
			UserScoreCash uc = list.get(0);
			Long updateTime = uc.getUpdateTime();
			String lastCashDateStr = TimeStampUtil.timeStampToDateStr(updateTime * 1000);
			Date lastCashDate = DateUtil.parse(lastCashDateStr);
			Date nowDate = DateUtil.getNowOfDate();
			
			int days = DateUtil.daysOfTwo(lastCashDate, nowDate);
			if (days <= Constants.MIN_CASH_DATE) {
				result.addError(new FieldError("contentModel", "userId", "一个星期可领取一次."));
				return cashForm(request, model, id);
			}
		}
		
		
		UserScoreCash record = userScoreCashService.initPo();
		record.setUserId(userId);
		record.setScoreCash(scoreCash);
		record.setStatus((short) 0);
		userScoreCashService.insertSelective(record);
		
		String returnUrl = "/home/success?nextUrl=";
		String nextUrl = "/user/cashList";
		nextUrl = URLEncoder.encode(nextUrl);
		returnUrl = returnUrl + nextUrl;
		return "redirect:" + returnUrl;

	}
}
