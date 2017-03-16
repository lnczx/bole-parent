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
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.meijia.utils.StringUtil;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AccountRole;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthorityMenu;
import com.simi.oa.auth.PermissionMenu;
import com.simi.oa.vo.account.AccountLoginVo;

@Controller
@RequestMapping(value = "/home")
public class RegisterController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/reg", method = { RequestMethod.GET })
	public String reg(Model model) {
		if (!model.containsAttribute("contentModel")) {
			User u = userService.initPo();
			model.addAttribute("contentModel", u);
		}
		return "home/reg";
	}

	@RequestMapping(value = "/reg", method = { RequestMethod.POST })
	public String doReg(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") User u, BindingResult result)
			throws ValidationException, NoSuchAlgorithmException {
		// 如果有验证错误 返回到form页面
		if (result.hasErrors())
			return reg(model);

		String gameId = u.getGameId();
		String pGameId = u.getpGameId();

		if (gameId.equals(pGameId)) {
			result.addError(new FieldError("contentModel", "gameId", "上级代理ID不正确."));
			return reg(model);
		}

		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGameId(gameId);
		List<User> users = userService.selectBySearchVo(searchVo);

		if (!users.isEmpty()) {
			result.addError(new FieldError("contentModel", "gameId", "游戏ID已经注册过，请直接登录."));
			return reg(model);
		}

		// 找出对于的上级代理
		searchVo = new UserSearchVo();
		searchVo.setGameId(pGameId);
		List<User> pUsers = userService.selectBySearchVo(searchVo);
		User pUser = null;
		Long pId = 0L;
		if (!pUsers.isEmpty()) {
			pUser = pUsers.get(0);
			pId = pUser.getUserId();
		}

		// 设定不能互为上级
		if (pUser != null) {
			if (pUser.getpGameId().equals(gameId)) {
				result.addError(new FieldError("contentModel", "gameId", "上级代理ID不正确."));
				return reg(model);
			}
		}

		User newUser = userService.initPo();
		newUser.setGameId(u.getGameId());
		newUser.setPassword(StringUtil.md5(u.getPassword()));
		newUser.setpGameId(pGameId);
		newUser.setLevel((short) 1);
		newUser.setpId(pId);

		userService.insertSelective(newUser);

		// todo,异步处理上级搭理ID的等级.
		if (pId > 0L) {

		}

		String returnUrl = "/home/login";
		return "redirect:" + returnUrl;

	}
}
