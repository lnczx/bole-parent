package com.bole.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bole.common.ConstantMsg;
import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.service.user.UserScoreCashService;
import com.bole.service.user.UserService;
import com.bole.vo.TreeNodeVo;
import com.bole.vo.UserSearchVo;
import com.meijia.utils.vo.AppResultData;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthPassport;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserScoreCashService userScoreCashService;

	@AuthPassport
	@RequestMapping(value = "/leveldesc", method = { RequestMethod.GET })
	public String levelDesc(HttpServletRequest request, Model model) {
		
		return "user/levelDesc";
	}
}
