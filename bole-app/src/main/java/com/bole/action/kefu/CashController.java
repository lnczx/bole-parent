package com.bole.action.kefu;

import java.math.BigDecimal;
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
import com.bole.po.model.user.UserScoreCash;
import com.bole.po.model.user.UserScoreDetail;
import com.bole.service.user.UserScoreCashService;
import com.bole.service.user.UserScoreDetailService;
import com.bole.service.user.UserService;
import com.bole.vo.user.UserScoreCashTotalVo;
import com.bole.vo.user.UserScoreCashVo;
import com.meijia.utils.TimeStampUtil;
import com.simi.oa.auth.AccountAuth;
import com.simi.oa.auth.AuthHelper;
import com.simi.oa.auth.AuthPassport;

@Controller
@RequestMapping(value = "/kefu")
public class CashController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserScoreCashService userScoreCashService;
	
	@Autowired
	private UserScoreDetailService userScoreDetailService;

	@AuthPassport
	@RequestMapping(value = "/cashView", method = { RequestMethod.GET })
	public String cashView(HttpServletRequest request, Model model, Long id) {
		
		UserScoreCash record = userScoreCashService.selectByPrimaryKey(id);
		
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		Short userType = u.getUserType();
		Long userId = u.getUserId();
		if (userType.equals(Constants.USER_TYPE_0)) {
			if (!record.getUserId().equals(userId)) {
				return "404";
			}
		}
		
		
		Long cashUserId = record.getUserId();
		UserScoreCashVo vo = userScoreCashService.getVo(record);
		
		if (!model.containsAttribute("contentModel"))
			model.addAttribute("contentModel", vo);
		
		
		UserScoreCashTotalVo userScoreCashTotalVo = userScoreCashService.getTotalVo(cashUserId);
		model.addAttribute("userScoreCashTotalVo", userScoreCashTotalVo);
		
		model.addAttribute("minScoreMoneyCash", Constants.MIN_SCORE_MONEY_CASH);
		model.addAttribute("minCashDate", Constants.MIN_CASH_DATE);
		return "kefu/cashView";
	}
	
	@AuthPassport
	@RequestMapping(value = "/cashView", method = { RequestMethod.POST })
	public String doAgentForm(HttpServletRequest request, Model model, 
			@Valid @ModelAttribute("contentModel") UserScoreCashVo formData, BindingResult result) throws NoSuchAlgorithmException {
		Long id = formData.getId();
		if (result.hasErrors())
			return cashView(request, model, id);
		
		AccountAuth accountAuth = AuthHelper.getSessionAccountAuth(request);
		User u = accountAuth.getU();
		Long userId = u.getUserId();
		u = userService.selectByPrimaryKey(userId);
		
		
		Short userType = u.getUserType();
		if (userType.equals(Constants.USER_TYPE_0)) {
			result.addError(new FieldError("contentModel", "userId", "权限不足"));
			return cashView(request, model, id);
		}
		
		
		Long cashUserId = formData.getUserId();
		User cashUser = userService.selectByPrimaryKey(cashUserId);
		UserScoreCash record = userScoreCashService.selectByPrimaryKey(id);
		//判断是否可以进行领取返利
		BigDecimal scoreCash = record.getScoreCash();
		
		UserScoreCashTotalVo userScoreCashTotalVo = userScoreCashService.getTotalVo(cashUserId);
		BigDecimal totalScore = userScoreCashTotalVo.getTotalScore();
		if (totalScore.compareTo(Constants.MIN_SCORE_MONEY_CASH) == -1) {
			result.addError(new FieldError("contentModel", "userId", "返利不满"+Constants.MIN_SCORE_MONEY_CASH));
			return cashView(request, model, id);
		}
		
		BigDecimal totalStore = userScoreCashTotalVo.getTotalStore();
		if (scoreCash.compareTo(totalStore) == 1) {
			result.addError(new FieldError("contentModel", "userId", "可领取数不足"));
			return cashView(request, model, id);
		}
		
		//判断当前客服是否有足够余额审核
		if (u.getScoreMoney().compareTo(scoreCash) == -1) {
			result.addError(new FieldError("contentModel", "userId", "你的余额不足,请联系管理员充值."));
			return cashView(request, model, id);
		}
		
		
		record.setStatus((short) 1);
		record.setUpdateTime(TimeStampUtil.getNowSecond());
		userScoreCashService.updateByPrimaryKeySelective(record);
		
		//自动进行返利
		UserScoreDetail userScoreDetail = userScoreDetailService.initPo();
		userScoreDetail.setUserIdFrom(0L);
		userScoreDetail.setUserIdTo(cashUserId);
		userScoreDetail.setScoreType(Constants.SCORE_TYPE_3);
		userScoreDetail.setScoreMoney(scoreCash);
		userScoreDetail.setScorePre(cashUser.getScoreMoney());
		userScoreDetail.setScoreAfter(cashUser.getScoreMoney().add(scoreCash));
		userScoreDetail.setRemarks("领取返利");
		userScoreDetailService.insertSelective(userScoreDetail);
		
		//客服扣除余额 
		u.setScoreMoney(u.getScoreMoney().subtract(scoreCash));
		u.setUpdateTime(TimeStampUtil.getNowSecond());
		userService.updateByPrimaryKeySelective(u);
		
		String returnUrl = "/home/success?nextUrl=";
		String nextUrl = "/user/cashList";
		nextUrl = URLEncoder.encode(nextUrl);
		returnUrl = returnUrl + nextUrl;
		return "redirect:" + returnUrl;

	}
}
