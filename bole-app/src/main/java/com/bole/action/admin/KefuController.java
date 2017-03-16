package com.bole.action.admin;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.action.BaseController;
import com.bole.po.model.user.User;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.StringUtil;
import com.meijia.utils.TimeStampUtil;
import com.simi.oa.auth.AuthPassport;
import com.simi.oa.common.ConstantOa;

@Controller
@RequestMapping(value = "/admin")
public class KefuController extends BaseController {

	@Autowired
	private UserService userService;

	@AuthPassport
	@RequestMapping(value = "/kefuList", method = { RequestMethod.GET })
	public String list(HttpServletRequest request, Model model, UserSearchVo searchVo) {
		
		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		
		if (searchVo == null) searchVo = new UserSearchVo();
		
		if (searchVo.getUserType() == null) searchVo.setUserType((short) 1);
		
		model.addAttribute("searchModel", searchVo);
		int pageNo = ServletRequestUtils.getIntParameter(request, ConstantOa.PAGE_NO_NAME, ConstantOa.DEFAULT_PAGE_NO);
		int pageSize = ConstantOa.DEFAULT_PAGE_SIZE;
		
		PageInfo pageInfo = userService.selectByListPage(searchVo, pageNo, pageSize);
		model.addAttribute("contentModel", pageInfo);
		return "admin/kefuList";
	}
	
	@AuthPassport
	@RequestMapping(value = "/kefuForm", method = { RequestMethod.GET })
	public String kefuForm(HttpServletRequest request, Model model, Long userId) {
		
		User record = userService.initPo();
		
		if (userId > 0L) {
			record = userService.selectByPrimaryKey(userId);
		}
		record.setUserType((short) 1);
		model.addAttribute("contentModel", record);
		
		return "admin/kefuForm";
	}

	@AuthPassport
	@RequestMapping(value = "/kefuForm", method = { RequestMethod.POST })
	public String doKefuForm(HttpServletRequest request, Model model, 
			@Valid @ModelAttribute("contentModel") User formData, BindingResult result) throws NoSuchAlgorithmException {
		Long userId = formData.getUserId();
		if (result.hasErrors())
			return kefuForm(request, model, userId);
		String password = formData.getPassword();
		password = StringUtil.md5(password);
		
		User record = userService.initPo();
		if (userId > 0L) {
			record = userService.selectByPrimaryKey(userId);
		}
		record.setGameId(formData.getGameId());
		record.setPassword(password);
		record.setUserType((short) 1);
		
		if (userId.equals(0L)) {
			record.setAddTime(TimeStampUtil.getNowSecond());
			userService.insertSelective(record);
		} else {
			record.setUpdateTime(TimeStampUtil.getNowSecond());
			userService.updateByPrimaryKeySelective(record);
		}

		String returnUrl = "/admin/kefuList";
		return "redirect:" + returnUrl;

	}
}
