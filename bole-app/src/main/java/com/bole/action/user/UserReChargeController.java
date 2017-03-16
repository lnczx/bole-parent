package com.bole.action.user;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.action.BaseController;
import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.service.user.UserScoreDetailService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
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

	@AuthPassport
	@RequestMapping(value = "/rechargeList", method = { RequestMethod.GET })
	public String list(HttpServletRequest request, Model model, 
			UserSearchVo searchVo, Long userIdTo) {
		
		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		
		if (searchVo == null) searchVo = new UserSearchVo();
		if (userIdTo != null && userIdTo > 0L) searchVo.setUserIdTo(userIdTo);
		
		//如果是代理，则只能看到给自己充值的记录.
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
    	User u = accountAuth.getU();
    	Long userId = u.getUserId();
    	Short userType = u.getUserType();
		
    	if (userType.equals(Constants.USER_TYPE_0)) {
    		searchVo.setUserIdTo(userId);
    	}
    	
		
		
		
		return "user/rechargeList";
	}

}
