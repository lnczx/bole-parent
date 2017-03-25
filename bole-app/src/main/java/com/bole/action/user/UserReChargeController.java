package com.bole.action.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.action.BaseController;
import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.po.model.user.UserScoreDetail;
import com.bole.service.user.UserScoreCashService;
import com.bole.service.user.UserScoreDetailService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.bole.vo.user.UserScoreCashTotalVo;
import com.bole.vo.user.UserScoreDetailVo;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.DateUtil;
import com.meijia.utils.MathBigDecimalUtil;
import com.meijia.utils.StringUtil;
import com.meijia.utils.TimeStampUtil;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthPassport;

@Controller
@RequestMapping(value = "/user")
public class UserReChargeController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserScoreDetailService userScoreDetailService;
	
	@Autowired
	private UserScoreCashService userScoreCashService;

	@SuppressWarnings("rawtypes")
	@AuthPassport
	@RequestMapping(value = "/rechargeList", method = { RequestMethod.GET })
	public String list(HttpServletRequest request, Model model, UserSearchVo searchVo, Long userIdTo) {

		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

		if (searchVo == null)
			searchVo = new UserSearchVo();
		if (userIdTo != null && userIdTo > 0L)
			searchVo.setUserIdTo(userIdTo);

		if (searchVo.getScoreType() == null) {
			List<Short> scoreTypes = new ArrayList<Short>();
			scoreTypes.add(Constants.SCORE_TYPE_0);
			scoreTypes.add(Constants.SCORE_TYPE_1);
			searchVo.setScoreTypes(scoreTypes);
		}
		
		// 如果是代理，则只能看到给自己充值的记录.
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		Long userId = u.getUserId();
		Short userType = u.getUserType();

		if (userType.equals(Constants.USER_TYPE_0)) {
			searchVo.setUserIdTo(userId);
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
		PageInfo pageInfo = userScoreDetailService.selectByListPage(searchVo, pageNo, pageSize);
		List<UserScoreDetail> list = pageInfo.getList();
		for (int i = 0; i < list.size(); i++) {
			UserScoreDetail item = list.get(i);

			UserScoreDetailVo vo = userScoreDetailService.getVo(item);

			list.set(i, vo);
		}

		pageInfo = new PageInfo(list);

		model.addAttribute("contentModel", pageInfo);
		model.addAttribute("userType", userType);
		
		//总充值数据钻石数   
		BigDecimal totalScore = userScoreDetailService.totalScore(searchVo);
		String totalScoreStr = MathBigDecimalUtil.roundInt(totalScore);
		model.addAttribute("totalScore", totalScoreStr);
		
		//总充值金额.
		BigDecimal totalScoreMoney = userScoreDetailService.totalScoreMoney(searchVo);
		model.addAttribute("totalScoreMoney", totalScoreMoney);
		
		
		return "user/rechargeList";
	}
	
	
	@SuppressWarnings("rawtypes")
	@AuthPassport
	@RequestMapping(value = "/payBackList", method = { RequestMethod.GET })
	public String payBackList(HttpServletRequest request, Model model, UserSearchVo searchVo, Long userIdTo) {

		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

		if (searchVo == null)
			searchVo = new UserSearchVo();
		if (userIdTo != null && userIdTo > 0L)
			searchVo.setUserIdTo(userIdTo);
				
		searchVo.setScoreType(Constants.SCORE_TYPE_2);
		// 如果是代理，则只能看到给自己充值的记录.
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		Long userId = u.getUserId();
		Short userType = u.getUserType();

		if (userType.equals(Constants.USER_TYPE_0)) {
			searchVo.setUserIdTo(userId);
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
		PageInfo pageInfo = userScoreDetailService.selectByListPage(searchVo, pageNo, pageSize);
		List<UserScoreDetail> list = pageInfo.getList();
		for (int i = 0; i < list.size(); i++) {
			UserScoreDetail item = list.get(i);

			UserScoreDetailVo vo = userScoreDetailService.getVo(item);
			
			//关联充值ID
			UserScoreDetail linkUserScoreDetail = null;
			UserScoreDetailVo linkUserScoreDetailVo = new UserScoreDetailVo();
			if (vo.getLinkDetailId() > 0L) {
				linkUserScoreDetail = userScoreDetailService.selectByPrimaryKey(vo.getLinkDetailId());
				linkUserScoreDetailVo = userScoreDetailService.getVo(linkUserScoreDetail);
				
			}
			
			String payBackRemarks = "";
			
			if (vo.getScoreType().equals(Constants.SCORE_TYPE_2)) {
				payBackRemarks = vo.getLinkBackLevel() + "层代理" + linkUserScoreDetailVo.getGameIdTo() + "充值" + linkUserScoreDetailVo.getScoreMoney() ;
				
				BigDecimal levelRatio = vo.getLinkBackRatio().multiply(new BigDecimal(100));
				levelRatio = MathBigDecimalUtil.round(levelRatio, 0);
				String levelRatioStr = levelRatio.toString() + "%";
				payBackRemarks+= ",返利" + levelRatioStr + "," + vo.getScoreMoney();
			}
			
			if (vo.getScoreType().equals(Constants.SCORE_TYPE_3)) {
				payBackRemarks = "代理领取返利"+ vo.getScore();
			}
			vo.setPayBackRemarks(payBackRemarks);
			list.set(i, vo);
		}

		pageInfo = new PageInfo(list);

		model.addAttribute("contentModel", pageInfo);
		model.addAttribute("userType", userType);
		
		
		Long searchUserId = searchVo.getUserIdTo();
		if (!StringUtil.isEmpty(searchVo.getGameId())) {
			UserSearchVo usearchVo = new UserSearchVo();
			usearchVo.setGameId(searchVo.getGameId());
			List<User> searchUsers = userService.selectBySearchVo(usearchVo);
			if (!searchUsers.isEmpty()) {
				User searchUser = searchUsers.get(0);
				searchUserId = searchUser.getUserId();
			}
		}
		
		UserScoreCashTotalVo userScoreCashTotalVo = userScoreCashService.getTotalVo(searchUserId);
		
		model.addAttribute("userScoreCashTotalVo", userScoreCashTotalVo);
		
		return "user/payBackList";
	}

}
