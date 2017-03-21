package com.bole.action.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.service.user.UserScoreCashService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.bole.vo.user.UserScoreCashTotalVo;
import com.bole.vo.user.UserVo;
import com.github.pagehelper.PageInfo;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthPassport;

@Controller
@RequestMapping(value = "/user")
public class UserAgentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserScoreCashService userScoreCashService;

	@AuthPassport
	@RequestMapping(value = "/agentList", method = { RequestMethod.GET })
	public String list(HttpServletRequest request, Model model, UserSearchVo searchVo, Long pId) {
		
		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		
		if (searchVo == null) searchVo = new UserSearchVo();
		
		if (searchVo.getUserType() == null) searchVo.setUserType((short) 0);
		
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
    	User u = accountAuth.getU();
    	Short userType = u.getUserType();
    	if (userType.equals(Constants.USER_TYPE_0)) {
    		if (pId == null) {
    			searchVo.setpId(pId);
    		}
    		
    		if (pId.equals(u.getUserId())) {
    			Boolean isSub = userService.isSubUser(u.getUserId(), pId);
    			if (isSub) searchVo.setpId(u.getUserId());
    		}
    	}
		
		
		model.addAttribute("searchModel", searchVo);
		int pageNo = ServletRequestUtils.getIntParameter(request, Constants.PAGE_NO_NAME, Constants.DEFAULT_PAGE_NO);
		int pageSize = Constants.DEFAULT_PAGE_SIZE;
		
		PageInfo pageInfo = userService.selectByListPage(searchVo, pageNo, pageSize);
		model.addAttribute("contentModel", pageInfo);
		model.addAttribute("userType", userType);
		
		
		return "user/agentList";
	}
	
	@AuthPassport
	@RequestMapping(value = "/agentView", method = { RequestMethod.GET })
	public String agentView(HttpServletRequest request, Model model, Long userId) {
		
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
    	User u = accountAuth.getU();
    	Short userType = u.getUserType();
    	Long sessionUserId = u.getUserId();
    	if (userId == null ) userId = sessionUserId;
    	
    	if (userType.equals(Constants.USER_TYPE_0) && !sessionUserId.equals(userId)) {
    		Boolean isSub = userService.isSubUser(u.getUserId(), userId);
			if (!isSub) {
				userId = u.getUserId();
			}
    	}
    	
		User record = userService.selectByPrimaryKey(userId);
		UserVo vo = userService.getVo(record);
		
		UserScoreCashTotalVo userScoreCashTotalVo = userScoreCashService.getTotalVo(userId);
		
		model.addAttribute("userScoreCashTotalVo", userScoreCashTotalVo);
		
		model.addAttribute("contentModel", vo);
		return "user/agentView";
	}
}
