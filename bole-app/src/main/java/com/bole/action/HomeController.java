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
    		
    		//会员管理
    		
    		MenuVo menu2 = new MenuVo();
    		menu2.setIconUrl("/static/images/icon_nav_search_bar.png");
    		menu2.setLinkUrl("/user/agentList");
    		menu2.setName("会员管理");
    		menus.add(menu2);
    		
    		//团队查看
    		MenuVo menu21 = new MenuVo();
    		menu21.setIconUrl("/static/images/icon_nav_search_bar.png");
    		menu21.setLinkUrl("/user/teamTree");
    		menu21.setName("团队查看");
    		menus.add(menu21);
    		
    		//充值记录
    		MenuVo menu3 = new MenuVo();
    		menu3.setIconUrl("/static/images/icon_nav_ptr.png");
    		menu3.setLinkUrl("/user/rechargeList");
    		menu3.setName("充值记录");
    		menus.add(menu3);
    		
    		//返利记录
    		MenuVo menu4 = new MenuVo();
    		menu4.setIconUrl("/static/images/icon_nav_up.png");
    		menu4.setLinkUrl("/user/payBackList");
    		menu4.setName("返利详情");
    		menus.add(menu4);
    		
    		//返利审核
    		MenuVo menu5 = new MenuVo();
    		menu5.setIconUrl("/static/images/icon_nav_panel.png");
    		menu5.setLinkUrl("/user/cashList");
    		menu5.setName("返利审核");
    		menus.add(menu5);
    		
    		//会员统计
    		MenuVo menu6 = new MenuVo();
    		menu6.setIconUrl("/static/images/icon_nav_photo.png");
    		menu6.setLinkUrl("/home/todo");
    		menu6.setName("会员统计");
    		menus.add(menu6);
    		
    		//充值统计
    		MenuVo menu7 = new MenuVo();
    		menu7.setIconUrl("/static/images/icon_nav_new.png");
    		menu7.setLinkUrl("/home/todo");
    		menu7.setName("充值统计");
    		menus.add(menu7);

    	}
    	
    	//客服
    	if (userType.equals(Constants.USER_TYPE_1)) {

    		//会员管理
    		MenuVo menu2 = new MenuVo();
    		menu2.setIconUrl("/static/images/icon_nav_search_bar.png");
    		menu2.setLinkUrl("/user/agentList");
    		menu2.setName("会员管理");
    		menus.add(menu2);
    		
    		//充值记录
    		MenuVo menu3 = new MenuVo();
    		menu3.setIconUrl("/static/images/icon_nav_ptr.png");
    		menu3.setLinkUrl("/user/rechargeList");
    		menu3.setName("充值记录");
    		menus.add(menu3);
    		
    		//返利记录
    		MenuVo menu4 = new MenuVo();
    		menu4.setIconUrl("/static/images/icon_nav_cell.png");
    		menu4.setLinkUrl("/user/payBackList");
    		menu4.setName("返利详情");
    		menus.add(menu4);
    		
    		//返利记录
    		MenuVo menu5 = new MenuVo();
    		menu5.setIconUrl("/static/images/icon_nav_cell.png");
    		menu5.setLinkUrl("/user/cashList");
    		menu5.setName("返利审核");
    		menus.add(menu5);
    	}
    	
    	//会员
    	if (userType.equals(Constants.USER_TYPE_0)) {
    		//我的信息
    		MenuVo menu1 = new MenuVo();
    		menu1.setIconUrl("/static/images/icon_nav_actionSheet.png");
    		menu1.setLinkUrl("/user/agentView?userId="+u.getUserId());
    		menu1.setName("我的信息");
    		menus.add(menu1);
    		//我的团队
    		MenuVo menu2 = new MenuVo();
    		menu2.setIconUrl("/static/images/icon_nav_search_bar.png");
    		menu2.setLinkUrl("/user/teamTree");
    		menu2.setName("我的团队");
    		menus.add(menu2);
    		//充值记
    		MenuVo menu3 = new MenuVo();
    		menu3.setIconUrl("/static/images/icon_nav_ptr.png");
    		menu3.setLinkUrl("/user/rechargeList");
    		menu3.setName("充值记录");
    		menus.add(menu3);
    		//返利详情
    		MenuVo menu4 = new MenuVo();
    		menu4.setIconUrl("/static/images/icon_nav_up.png");
    		menu4.setLinkUrl("/user/payBackList");
    		menu4.setName("返利详情");
    		menus.add(menu4);
    		//申请领取
    		MenuVo menu5 = new MenuVo();
    		menu5.setIconUrl("/static/images/icon_nav_progress.png");
    		menu5.setLinkUrl("/user/cashList");
    		menu5.setName("申请返利");
    		menus.add(menu5);
    	}
    	
    	MenuVo menuHelper = new MenuVo();
    	menuHelper.setIconUrl("/static/images/icon_nav_noti.png");
    	menuHelper.setLinkUrl("/user/leveldesc");
    	menuHelper.setName("等级说明");
    	menus.add(menuHelper);
    	
    	model.addAttribute("menus", menus);

        return "home/index";
    }
    
    @AuthPassport
	@RequestMapping(value = "/success", method = { RequestMethod.GET })
	public String successView(Model model, String nextUrl) {
		model.addAttribute("nextUrl", nextUrl);
		return "home/success";
	}

    @AuthPassport
	@RequestMapping(value = "/fail", method = { RequestMethod.GET })
	public String failView(Model model, String nextUrl) {
		
		return "home/fail";
	}
    
    @AuthPassport
	@RequestMapping(value = "/todo", method = { RequestMethod.GET })
	public String todoView(Model model) {
		
		return "home/todo";
	}
    
    
}
