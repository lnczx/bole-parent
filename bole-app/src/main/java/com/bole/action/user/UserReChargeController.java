package com.bole.action.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.bole.vo.user.UserScoreDetailVo;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.StringUtil;
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
		
		//总充值数据.   
		BigDecimal totalScore = userScoreDetailService.totalScore(searchVo);
		model.addAttribute("totalScore", totalScore);
		
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
			if (vo.getLinkDetailId() > 0L) {
				linkUserScoreDetail = userScoreDetailService.selectByPrimaryKey(vo.getLinkDetailId());
				UserScoreDetailVo linkUserScoreDetailVo = userScoreDetailService.getVo(linkUserScoreDetail);
				vo.setLinkUserScoreDetail(linkUserScoreDetailVo);
			}
			
			list.set(i, vo);
		}

		pageInfo = new PageInfo(list);

		model.addAttribute("contentModel", pageInfo);
		model.addAttribute("userType", userType);
		
		//总返利数据.   
		BigDecimal totalScore = userScoreDetailService.totalScore(searchVo);
		model.addAttribute("totalScore", totalScore);
		
		//总提现数据
		UserSearchVo totalCashSearchVo = new UserSearchVo();
		if (searchVo.getUserId() != null) totalCashSearchVo.setUserId(searchVo.getUserId());
		if (!StringUtil.isEmpty(searchVo.getGameIdTo())) totalCashSearchVo.setGameId(searchVo.getGameIdTo());
		totalCashSearchVo.setStatus((short) 1);
		BigDecimal totalCash = userScoreCashService.totalCash(totalCashSearchVo);
		
		BigDecimal totalStore = totalScore.subtract(totalCash);
		model.addAttribute("totalStore", totalStore);
		return "user/payBackList";
	}

}
