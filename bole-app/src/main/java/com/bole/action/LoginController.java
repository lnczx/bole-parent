package com.bole.action;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.po.model.user.User;
import com.bole.po.model.user.UserLogined;
import com.bole.service.user.UserLoginedService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.meijia.utils.IPUtil;
import com.meijia.utils.StringUtil;
import com.meijia.utils.TimeStampUtil;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AccountRole;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthorityMenu;
import com.simi.oa.auth.PermissionMenu;
import com.simi.oa.vo.account.AccountLoginVo;

@Controller
@RequestMapping(value = "/home")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserLoginedService userLoginedService;

	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(Model model) {
		if (!model.containsAttribute("contentModel"))
			model.addAttribute("contentModel", new AccountLoginVo());
		return "home/login";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") AccountLoginVo accountLoginVo, BindingResult result)
			throws ValidationException, NoSuchAlgorithmException {
		// 如果有验证错误 返回到form页面
		if (result.hasErrors())
			return login(model);

		String username = accountLoginVo.getUsername().trim();
		String password = accountLoginVo.getPassword().trim();
		
		if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
			result.addError(new FieldError("contentModel", "username", "用户名或密码错误。"));
			return login(model);
		}

		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGameId(username);
		List<User> users = userService.selectBySearchVo(searchVo);

		if (users.isEmpty()) {
			result.addError(new FieldError("contentModel", "username", "用户名或密码错误。"));
			return login(model);
		}

		User u = users.get(0);
		if (u.getEnable() == 0) {
			result.addError(new FieldError("contentModel", "username", "此用户被禁用，不能登录。"));
			return login(model);
		}

		if (!StringUtil.md5(password.trim()).equals(u.getPassword())) {
			result.addError(new FieldError("contentModel", "username", "用户名或密码错误。"));
			return login(model);
		}

		AccountAuth accountAuth = new AccountAuth();
		accountAuth.setU(u);
		AuthHelper.setSessionAccountAuth(request, accountAuth);

		// 登陆日志
		long ip = IPUtil.getIpAddr(request);
		UserLogined record = userLoginedService.initPo();
		record.setLoginFrom((short) 1);// 0 = APP 1 = 微网站 2 = 管理后台
		record.setUserId(u.getUserId());
		record.setLoginIp(ip);
		userLoginedService.insert(record);

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if (returnUrl == null)
			returnUrl = "/home/index";
		return "redirect:" + returnUrl;

	}

	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	public String logout(HttpServletRequest request) {
		AuthHelper.removeSessionAccountAuth(request, "accountAuth");
		return "redirect:/home/login";
	}

}
