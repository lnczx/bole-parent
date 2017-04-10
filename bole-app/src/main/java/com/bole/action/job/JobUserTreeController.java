package com.bole.action.job;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bole.common.ConstantMsg;
import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.service.user.UserScoreCashService;
import com.bole.service.user.UserService;
import com.bole.service.user.UserTreeService;
import com.bole.vo.UserSearchVo;
import com.meijia.utils.vo.AppResultData;

@Controller
@RequestMapping(value = "/job")
public class JobUserTreeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	public UserTreeService userTreeService;

	@RequestMapping(value = "moveUser", method = RequestMethod.GET)
	public AppResultData<Object> moveUser(HttpServletRequest request,
			 @RequestParam("gameId") String gameId,
			 @RequestParam("pGameId") String pGameId
			) {

		AppResultData<Object> result = new AppResultData<Object>(
				Constants.SUCCESS_0, ConstantMsg.SUCCESS_0_MSG, new String());
		
		String reqHost = request.getRemoteHost();// 如果用的 IPV6 得到的 将是 0:0.。。。。1

		// String reqHost = request.getRemoteAddr();
		// 限定只有localhost能访问
		if (reqHost.equals("localhost") || reqHost.equals("127.0.0.1")) {
//			String gameId = "001727";
//			String pGameId = "001730";
			
			UserSearchVo searchVo = new UserSearchVo();
			searchVo.setGameId(pGameId);
			List<User> pUsers = userService.selectBySearchVo(searchVo);
			if (pUsers.isEmpty()) return result;
			
			User pUser = pUsers.get(0);
			
			
			searchVo = new UserSearchVo();
			searchVo.setGameId(gameId);
			List<User> users = userService.selectBySearchVo(searchVo);
			if (users.isEmpty()) return result;
			
			User mUser = users.get(0);
			
			userTreeService.moveAgenUser(mUser, pUser);
			
			
		}
		return result;
	}
}
