package com.bole.job;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.action.BaseController;
import com.bole.common.ConstantMsg;
import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.meijia.utils.vo.AppResultData;

@Controller
@RequestMapping(value = "/app/job/cleanup")
public class GenAgentController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "gen-user", method = RequestMethod.GET)
	public AppResultData<Object> cleanupUserOrderCard(HttpServletRequest request) {

		AppResultData<Object> result = new AppResultData<Object>(
				Constants.SUCCESS_0, ConstantMsg.SUCCESS_0_MSG, new String());
		
		String reqHost = request.getRemoteHost();

		// 限定只有localhost能访问
		if (reqHost.equals("localhost") || reqHost.equals("127.0.0.1")) {
			int level = 1;
			int gameId = 1800;
			Long pId = 1L;
			while (true) {
				if (level > 6) break;
				
				UserSearchVo searchVo = new UserSearchVo();
				searchVo.setpId(pId);
				List<User> list = userService.selectBySearchVo(searchVo);
				
				for (User u : list) {
					
				}
				
				for (int i = 0; i < 3 ; i++) {
					
				}
				
				
				level++;
			}
		}
		return result;
	}

	
	private boolean genTree(Long pId, int level, int maxCount) {
		
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setpId(pId);
		
		return true;
	}
}
