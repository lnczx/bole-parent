package com.bole.action.kefu;

import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

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
import com.bole.po.model.user.UserScoreDetail;
import com.bole.service.user.UserScoreDetailService;
import com.bole.service.user.UserService;
import com.bole.vo.user.UserScoreDetailVo;
import com.meijia.utils.BeanUtilsExp;
import com.meijia.utils.StringUtil;
import com.meijia.utils.TimeStampUtil;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthPassport;

@Controller
@RequestMapping(value = "/kefu")
public class ReChargeController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserScoreDetailService userScoreDetailService;

	@AuthPassport
	@RequestMapping(value = "/rechargeForm", method = { RequestMethod.GET })
	public String rechargeForm(HttpServletRequest request, Model model, Long userIdTo) {

		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User userFrom = accountAuth.getU();
		Long userIdFrom = userFrom.getUserId();
		Short userType = userFrom.getUserType();
		if (userType.equals(Constants.USER_TYPE_0)) {
			return "404";
		}

		User userTo = userService.selectByPrimaryKey(userIdTo);
		UserScoreDetail record = userScoreDetailService.initPo();
		record.setUserIdTo(userIdTo);
		record.setUserIdFrom(userIdFrom);

		UserScoreDetailVo vo = new UserScoreDetailVo();
		BeanUtilsExp.copyPropertiesIgnoreNull(record, vo);
		vo.setGameIdFrom(userFrom.getGameId());
		vo.setGameIdTo(userTo.getGameId());

		model.addAttribute("contentModel", vo);

		return "kefu/rechargeForm";
	}

	@AuthPassport
	@RequestMapping(value = "/rechargeForm", method = { RequestMethod.POST })
	public String doRecharge(HttpServletRequest request, Model model, 
			@Valid @ModelAttribute("contentModel") UserScoreDetailVo formData, BindingResult result) throws NoSuchAlgorithmException {
		
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
    	User userFrom = accountAuth.getU();
    	Long userIdFrom = userFrom.getUserId();
    	Short userType = userFrom.getUserType();
    	if (userType.equals(Constants.USER_TYPE_0)) {
    		return "404";
    	}
		
		Long userIdTo = formData.getUserIdTo();
		User userTo = userService.selectByPrimaryKey(userIdTo);
		if (result.hasErrors())
			return rechargeForm(request, model, userIdTo);
		
		if (userIdFrom.equals(userIdTo)) {
			result.addError(new FieldError("contentModel", "userIdFrom", "游戏ID不正确"));
			return rechargeForm(request, model, userIdTo);
		}
		
		int score = formData.getScore();
		String scoreTypeStr = request.getParameter("scoreType");
		Short scoreType = 0;
		if (!StringUtil.isEmpty(scoreTypeStr)) {
			scoreType = Short.valueOf(scoreTypeStr);
		}
		//如果为客服，需要判断金额是否足够.
		if (userType.equals(Constants.USER_TYPE_1)) {
			int userScore = userFrom.getScore();
			if (userScore < score) {
				result.addError(new FieldError("contentModel", "userIdFrom", "钻石不足,请联系管理员充值"));
				return rechargeForm(request, model, userIdTo);
			}
		}

		UserScoreDetail record = userScoreDetailService.initPo();
		
		record.setUserIdFrom(userIdFrom);
		record.setUserIdTo(userIdTo);
		record.setScore(score);
		record.setScoreType(scoreType);
		record.setScorePre(userTo.getScore());
		record.setScoreAfter( (userTo.getScore() + score) );
		record.setRemarks(formData.getRemarks());
		record.setAddTime(TimeStampUtil.getNowSecond());
		
		userScoreDetailService.insertSelective(record);
		
		//如果是给代理充值，则需要异步处理返利的情况
		if (userTo.getUserType().equals((short)0)) {
			//todo async user payback
		}
		
		String returnUrl = "/home/success?nextUrl=";
		String nextUrl = "/user/rechargeList?userIdTo="+userIdTo;
		nextUrl = URLEncoder.encode(nextUrl);
		returnUrl = returnUrl + nextUrl;
		return "redirect:" + returnUrl;

	}
}
