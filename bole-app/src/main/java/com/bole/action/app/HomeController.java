package com.bole.action.app;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.simi.oa.auth.AuthPassport;



@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController {
	

    @AuthPassport
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) {
    	
        return "home/index";
    }
    
}
