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
public class UserTeamController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserScoreCashService userScoreCashService;

	@AuthPassport
	@RequestMapping(value = "/teamTree", method = { RequestMethod.GET })
	public String list(HttpServletRequest request, Model model) {

		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		
		model.addAttribute("userId", u.getUserId());
		
		String name = u.getGameId();
		int lft = u.getLft();
		int rgt = u.getRgt();
		// (right-left+1)/2
		int totalSub = (rgt - lft + 1) / 2;
		name += "(" + totalSub + "人)";
		model.addAttribute("name", name);
		
		return "user/teamTree";
	}

	@AuthPassport
	@RequestMapping(value = "teamSubs", method = RequestMethod.GET)
	public AppResultData<Object> zTreezNodes(HttpServletRequest request, @RequestParam(value = "id", required = true, defaultValue = "0") Long id) {

		AppResultData<Object> result = new AppResultData<Object>(Constants.SUCCESS_0, ConstantMsg.SUCCESS_0_MSG, null);

		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		Short userType = u.getUserType();

		if (id.equals(0L)) {
			id = u.getUserId();
			if (!userType.equals(Constants.USER_TYPE_0))
				id = 1L;
		}

		if (userType.equals(Constants.USER_TYPE_0) && !id.equals(u.getUserId())) {
			if (!userService.isSubUser(u.getUserId(), id)) {
				return result;
			}
		}

		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setpId(id);
		searchVo.setOrderByProperty(" order by lft asc");
		List<User> list = userService.selectBySearchVo(searchVo);

		List<TreeNodeVo> treeNodes = new ArrayList<TreeNodeVo>();
		for (int i = 0; i < list.size(); i++) {
			User user = list.get(i);
			TreeNodeVo vo = new TreeNodeVo();
			vo.setId(user.getUserId());
			vo.setpId(user.getUserId());
			String name = user.getGameId();

			int lft = user.getLft();
			int rgt = user.getRgt();
			// (right-left+1)/2
			int totalSub = (rgt - lft + 1) / 2;
			name += "(" + totalSub + "人)";
			vo.setName(name);

			vo.setIsParent(true);
			if ((rgt - lft) == 1)
				vo.setIsParent(false);

			treeNodes.add(vo);

		}

		result.setData(treeNodes);
		return result;

	}
}
