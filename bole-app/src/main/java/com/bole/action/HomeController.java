package com.bole.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bole.common.Constants;
import com.bole.po.model.user.User;
import com.bole.service.user.UserService;
import com.bole.vo.MenuVo;
import com.bole.vo.UserSearchVo;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthPassport;



@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController {
	
	@Autowired
	private UserService userService;
	
    @AuthPassport
    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public String home(HttpServletRequest request, Model model) {
    	
    	
    	AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
    	User u = accountAuth.getU();
    	Short userType = u.getUserType();
    	
    	List<MenuVo> menus = new ArrayList<MenuVo>();
    	//管理员
    	if (userType.equals(Constants.USER_TYPE_2)) {
    		//客服管理
    		MenuVo menu1 = new MenuVo();
    		menu1.setIconUrl("/static/images/icon_nav_dialog.png");
    		menu1.setLinkUrl("/admin/kefuList");
    		menu1.setName("客服管理");
    		
    		menus.add(menu1);
    		//代理管理
    		MenuVo menu2 = new MenuVo();
    		menu2.setIconUrl("/static/images/icon_nav_search_bar.png");
    		menu2.setLinkUrl("/kefu/agentList");
    		menu2.setName("代理管理");
    		menus.add(menu2);
    		
    		//代理管理
    		MenuVo menu3 = new MenuVo();
    		menu3.setIconUrl("/static/images/icon_nav_cell.png");
    		menu3.setLinkUrl("/kefu/agentList");
    		menu3.setName("充值记录");
    		menus.add(menu3);
    		
    	}
    	
    	//客服
    	if (userType.equals(Constants.USER_TYPE_1)) {
    		//我的信息
    		MenuVo menu1 = new MenuVo();
    		menu1.setIconUrl("/static/images/icon_nav_actionSheet.png");
    		menu1.setLinkUrl("/admin/kefuList");
    		menu1.setName("我的信息");
    		menus.add(menu1);
    		//代理管理
    		MenuVo menu2 = new MenuVo();
    		menu2.setIconUrl("/static/images/icon_nav_search_bar.png");
    		menu2.setLinkUrl("/kefu/agentList");
    		menu2.setName("代理管理");
    		menus.add(menu2);
    		
    		//代理管理
    		MenuVo menu3 = new MenuVo();
    		menu3.setIconUrl("/static/images/icon_nav_cell.png");
    		menu3.setLinkUrl("/kefu/agentList");
    		menu3.setName("充值记录");
    		menus.add(menu3);
    	}
    	
    	//代理
    	if (userType.equals(Constants.USER_TYPE_0)) {
    		//我的信息
    		MenuVo menu1 = new MenuVo();
    		menu1.setIconUrl("/static/images/icon_nav_actionSheet.png");
    		menu1.setLinkUrl("/admin/kefuList");
    		menu1.setName("我的信息");
    		menus.add(menu1);
    		//我的团队
    		MenuVo menu2 = new MenuVo();
    		menu2.setIconUrl("/static/images/icon_nav_search_bar.png");
    		menu2.setLinkUrl("/kefu/agentList");
    		menu2.setName("我的团队");
    		menus.add(menu2);
    		//返利记录
    		MenuVo menu3 = new MenuVo();
    		menu3.setIconUrl("/static/images/icon_nav_up.png");
    		menu3.setLinkUrl("/kefu/agentList");
    		menu3.setName("返利记录");
    		menus.add(menu3);
    		//申请提现
    		MenuVo menu4 = new MenuVo();
    		menu4.setIconUrl("/static/images/icon_nav_ptr.png");
    		menu4.setLinkUrl("/kefu/agentList");
    		menu4.setName("申请提现");
    		menus.add(menu4);
    	}
    	
    	MenuVo menuHelper = new MenuVo();
    	menuHelper.setIconUrl("/static/images/icon_nav_noti.png");
    	menuHelper.setLinkUrl("/kefu/agentList");
    	menuHelper.setName("等级说明");
    	menus.add(menuHelper);
    	
    	model.addAttribute("menus", menus);

        return "home/index";
    }
    
	@RequestMapping(value = "/success", method = { RequestMethod.GET })
	public String successView(Model model, String nextUrl) {
		model.addAttribute("nextUrl", nextUrl);
		return "home/success";
	}

	@RequestMapping(value = "/fail", method = { RequestMethod.GET })
	public String failView(Model model, String nextUrl) {
		
		return "home/fail";
	}
    
    
}
