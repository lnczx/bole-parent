package com.bole.action.kefu;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.action.BaseController;
import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.service.async.UserAsyncService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.meijia.utils.StringUtil;
import com.meijia.utils.TimeStampUtil;
import com.simi.oa.auth.AuthPassport;

@Controller
@RequestMapping(value = "/kefu")
public class AgentController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAsyncService userAsyncService;
		
	@AuthPassport
	@RequestMapping(value = "/agentForm", method = { RequestMethod.GET })
	public String agentForm(HttpServletRequest request, Model model, Long userId) {
		
		User record = userService.initPo();
		
		if (userId > 0L) {
			record = userService.selectByPrimaryKey(userId);
		}
		record.setUserType((short) 0);
		if (!model.containsAttribute("contentModel")) {
			model.addAttribute("contentModel", record);
		}
		
		
		return "kefu/agentForm";
	}

	@AuthPassport
	@RequestMapping(value = "/agentForm", method = { RequestMethod.POST })
	public String doAgentForm(HttpServletRequest request, Model model, 
			@Valid @ModelAttribute("contentModel") User formData, BindingResult result) throws NoSuchAlgorithmException {
		Long userId = formData.getUserId();
		if (result.hasErrors())
			return agentForm(request, model, userId);
		
		String gameId = formData.getGameId();
		if (StringUtil.isEmpty(gameId)) {
			result.addError(new FieldError("contentModel", "gameId", "游戏ID不能为空."));
			return agentForm(request, model, userId);
		}
		
		String pGameId = formData.getpGameId();
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGameId(pGameId);
		List<User> pUsers = userService.selectBySearchVo(searchVo);
		User pUser = null;
		Long pId = 0L;
		if (!pUsers.isEmpty()) {
			pUser = pUsers.get(0);
			pId = pUser.getUserId();
		}
		if (pUser != null) {
			if (pUser.getpGameId().equals(gameId)) {
				result.addError(new FieldError("contentModel", "gameId", "上级代理ID不正确."));
				return agentForm(request, model, userId);
			}
		}
		
		searchVo = new UserSearchVo();
		searchVo.setGameId(gameId);
		List<User> list = userService.selectBySearchVo(searchVo);
		if (!list.isEmpty()) {
			result.addError(new FieldError("contentModel", "gameId", "代理已经添加过了."));
			return agentForm(request, model, userId);
		}
		
		User record = userService.initPo();
		if (userId > 0L) {
			record = userService.selectByPrimaryKey(userId);
		}
		record.setGameId(formData.getGameId());
		record.setpGameId(pGameId);
		record.setpId(pId);
		record.setLevel(Constants.USER_LEVEL_1);
		record.setUserType(Constants.USER_TYPE_0);
		record.setActive(Constants.USER_ACTIVE_0);
		
		//生成注册码
		String shareCode = userService.genShareCode(gameId);
		record.setInviteCode(shareCode);
		
		if (userId.equals(0L)) {
			record.setAddTime(TimeStampUtil.getNowSecond());
			userService.insertSelective(record);
		} else {
			record.setUpdateTime(TimeStampUtil.getNowSecond());
			userService.updateByPrimaryKeySelective(record);
		}
		
		//异步生成PCode
		userAsyncService.genPcode(record);
		
		//异步统计代理人数
		userAsyncService.totalUser(record.getUserId());

		String returnUrl = "/user/agentView?userId="+ record.getUserId();
		return "redirect:" + returnUrl;

	}

}
