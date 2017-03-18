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

import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.service.async.UserAsyncService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
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
public class RegisterController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAsyncService userAsyncService;

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
		
		String inviteCode = u.getInviteCode();
		String gameId = u.getGameId();

		if (StringUtil.isEmpty(inviteCode) || StringUtil.isEmpty(gameId)) {
			result.addError(new FieldError("contentModel", "gameId", "请输入邀请码和游戏ID."));
			return reg(model);
		}

		User agent = null;
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGameId(gameId);
		List<User> users = userService.selectBySearchVo(searchVo);
		
		if (users.isEmpty()) {
			result.addError(new FieldError("contentModel", "gameId", "邀请码不正确."));
			return reg(model);
		}
		
		if (!users.isEmpty()) {
			agent = users.get(0);
			if (!agent.getInviteCode().equals(inviteCode)) {
				result.addError(new FieldError("contentModel", "gameId", "邀请码不正确."));
				return reg(model);
			}
		}

		agent.setPassword(StringUtil.md5(u.getPassword()));
        agent.setActive(Constants.USER_ACTIVE_1);
        agent.setUpdateTime(TimeStampUtil.getNowSecond());
        userService.updateByPrimaryKeySelective(agent);
        
        Long pId  = agent.getpId();
		// todo,异步处理上级搭理ID的等级.
		if (pId > 0L) {
			userAsyncService.userLevelupTree(agent.getUserId());
		}

		String returnUrl = "/home/login";
		return "redirect:" + returnUrl;

	}
}
