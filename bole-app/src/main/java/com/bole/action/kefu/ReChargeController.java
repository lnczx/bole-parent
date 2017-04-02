package com.bole.action.kefu;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
import com.bole.po.model.user.UserScoreDetail;
import com.bole.service.user.UserScoreDetailService;
import com.bole.service.user.UserService;
import com.bole.vo.user.UserScoreDetailVo;
import com.meijia.utils.BeanUtilsExp;
import com.meijia.utils.MathBigDecimalUtil;
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
		
		//当前时间
		Long addTime = TimeStampUtil.getNowSecond();
		String addTimeStrFull = TimeStampUtil.timeStampToDateStr(addTime * 1000, "yyyy年MM月dd日 HH时mm分");
		model.addAttribute("addTimeStrFull", addTimeStrFull);
		
		if (!model.containsAttribute("contentModel"))
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
		
		BigDecimal score = formData.getScore();
		BigDecimal scoreMoney = formData.getScoreMoney();
		Short scoreType = formData.getScoreType();

		//如果为客服，需要判断钻石数是否足够.
		if (userType.equals(Constants.USER_TYPE_1)) {
			BigDecimal userScore = userFrom.getScore();
			if (userScore.compareTo(scoreMoney) == -1) {
				result.addError(new FieldError("contentModel", "userIdFrom", "钻石不足,请联系管理员充值"));
				return rechargeForm(request, model, userIdTo);
			}
		}
		
		//查看是否有选择充值时间
		Long addTime = TimeStampUtil.getNowSecond();
		String addTimeStrFull = request.getParameter("addTimeStrFull");
		if (!StringUtil.isEmpty(addTimeStrFull)) {
			addTime = TimeStampUtil.getMillisOfDayFull(addTimeStrFull) / 1000;
		}
		
		
		UserScoreDetail record = userScoreDetailService.initPo();
		
		BigDecimal scoreAfter = userTo.getScoreMoney().add(scoreMoney);
		scoreAfter = MathBigDecimalUtil.round(scoreAfter, 2);
		record.setUserIdFrom(userIdFrom);
		record.setUserIdTo(userIdTo);
		record.setScoreMoney(scoreMoney);
		record.setScore(score);
		record.setScoreType(scoreType);
		record.setScorePre(userTo.getScoreMoney());
		record.setScoreAfter(scoreAfter);
		record.setRemarks(formData.getRemarks());
		record.setAddTime(addTime);
		
		UserScoreDetailVo vo = userScoreDetailService.getVo(record);
		
		
		//计算会员返利的情况
		List<UserScoreDetailVo> paybacks = new ArrayList<UserScoreDetailVo>();
		
		if (scoreType.equals(Constants.SCORE_TYPE_1)) {
			paybacks = userScoreDetailService.getAgentTreePayBack(userTo, record);
		}
		model.addAttribute("contentModel", vo);
		model.addAttribute("paybacks", paybacks);
		model.addAttribute("userToUserType", userTo.getUserType());
		
		return "/kefu/rechargeConfirm";

	}
	
	@AuthPassport
	@RequestMapping(value = "/rechargeConfirm", method = { RequestMethod.POST })
	public String doRechargeConfirm(HttpServletRequest request, Model model, 
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
		
		BigDecimal score = formData.getScore();
		BigDecimal scoreMoney = formData.getScoreMoney();
		Short scoreType = formData.getScoreType();

		//如果为客服，需要判断金额是否足够.
		if (userType.equals(Constants.USER_TYPE_1)) {
			BigDecimal userScore = userFrom.getScore();
			if (userScore.compareTo(score) == -1) {
				result.addError(new FieldError("contentModel", "userIdFrom", "钻石不足,请联系管理员充值"));
				return rechargeForm(request, model, userIdTo);
			}
		}

		UserScoreDetail record = userScoreDetailService.initPo();
		BigDecimal scoreAfter = userTo.getScoreMoney().add(scoreMoney);
		scoreAfter = MathBigDecimalUtil.round(scoreAfter, 2);
		record.setUserIdFrom(userIdFrom);
		record.setUserIdTo(userIdTo);
		record.setScore(score);
		record.setScoreMoney(scoreMoney);
		record.setScoreType(scoreType);
		record.setScorePre(userTo.getScoreMoney());
		record.setScoreAfter(scoreAfter);
		record.setRemarks(formData.getRemarks());
		record.setAddTime(formData.getAddTime());
		
		userScoreDetailService.insertSelective(record);
		
		//更新user表余额
		userTo.setScoreMoney(scoreAfter);
		userTo.setScore(userTo.getScore().add(score));
		userTo.setScoreLastTime(formData.getAddTime());
		userTo.setUpdateTime(formData.getAddTime());
		userService.updateByPrimaryKeySelective(userTo);
		
		//如果充值方式为付款，则扣除会员的钻石和增加会员的金额
		if (userType.equals(Constants.USER_TYPE_1)) {
			userFrom.setScore(userFrom.getScore().subtract(score));
			userFrom.setScoreMoney(userFrom.getScoreMoney().add(scoreMoney));
			userFrom.setUpdateTime(formData.getAddTime());
			userService.updateByPrimaryKeySelective(userFrom);
		}

		//计算会员返利的情况
		List<UserScoreDetailVo> paybacks = new ArrayList<UserScoreDetailVo>();
		if (scoreType.equals(Constants.SCORE_TYPE_1)) {
			paybacks = userScoreDetailService.getAgentTreePayBack(userTo, record);
			for (UserScoreDetailVo item : paybacks) {
				item.setAddTime(formData.getAddTime());
				userScoreDetailService.insertSelective(item);
			}
		}

		String returnUrl = "/home/success?nextUrl=";
		String nextUrl = "/user/rechargeList?userIdTo="+userIdTo;
		nextUrl = URLEncoder.encode(nextUrl);
		returnUrl = returnUrl + nextUrl;
		return "redirect:" + returnUrl;

	}
}
