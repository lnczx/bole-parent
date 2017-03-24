package com.bole.service.impl.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.common.BoleUtil;
import com.bole.common.Constants;
import com.bole.po.dao.user.UserScoreDetailMapper;
import com.bole.po.model.user.User;
import com.bole.po.model.user.UserScoreDetail;
import com.bole.service.user.UserScoreDetailService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.bole.vo.user.UserScoreDetailVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.BeanUtilsExp;
import com.meijia.utils.MathBigDecimalUtil;
import com.meijia.utils.TimeStampUtil;



@Service
public class UserScoreDetailServiceImpl implements UserScoreDetailService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserScoreDetailMapper mapper;


	@Override
	public int insertSelective(UserScoreDetail record) {
		return mapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserScoreDetail record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserScoreDetail initPo() {
		UserScoreDetail record = new UserScoreDetail();
		
		record.setId(0L);
		record.setUserIdFrom(0L);
		record.setUserIdTo(0L);
		record.setScore(new BigDecimal(0));
		record.setScoreMoney(new BigDecimal(0));
		record.setScoreType((short) 0);
		record.setScorePre(new BigDecimal(0));
		record.setScoreAfter(new BigDecimal(0));
		record.setLinkDetailId(0L);
		record.setLinkBackLevel(Constants.USER_LEVEL_0);
		record.setLinkBackRatio(new BigDecimal(0));
		record.setRemarks("");
		record.setAddTime(TimeStampUtil.getNowSecond());

		return record;
	}

	@Override
	public UserScoreDetail selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<UserScoreDetail> selectBySearchVo(UserSearchVo searchVo) {
		return mapper.selectBySearchVo(searchVo);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserScoreDetail> list = mapper.selectByListPage(searchVo);
		PageInfo info = new PageInfo(list);
		return info;
	}
	
	@Override 
	public UserScoreDetailVo getVo(UserScoreDetail item) {
		UserScoreDetailVo vo = new UserScoreDetailVo();
		
		BeanUtilsExp.copyPropertiesIgnoreNull(item, vo);
		
		vo.setGameIdFrom("");
		Long userIdFrom = vo.getUserIdFrom();
		if (userIdFrom > 0L) {
			User userFrom = userService.selectByPrimaryKey(userIdFrom);
			vo.setGameIdFrom(userFrom.getGameId());
		}
		
		
		vo.setGameIdTo("");
		vo.setLevelTo(Constants.USER_LEVEL_0);
		Long userIdTo = vo.getUserIdTo();
		if (userIdTo > 0L) {
			User userTo = userService.selectByPrimaryKey(userIdTo);
			vo.setGameIdTo(userTo.getGameId());
			vo.setLevelTo(userTo.getLevel());
		}
		
		String scoreTypeName = BoleUtil.getScoreTypeName(vo.getScoreType());
		vo.setScoreTypeName(scoreTypeName);
		
		String addTimeStr = TimeStampUtil.timeStampToDateStr(vo.getAddTime() * 1000, "MM-dd");
		vo.setAddTimeStr(addTimeStr);
		
		return vo;
	}
	
	@Override
	public BigDecimal totalScoreMoney(UserSearchVo searchVo) {
		BigDecimal totalPayBack = mapper.totalScoreMoney(searchVo);	
		if (totalPayBack == null )  totalPayBack = new BigDecimal(0);
		return totalPayBack;
	}
	
	/**
	 * 计算代理返利的情况
	 */
	@Override
	public List<UserScoreDetailVo> getAgentTreePayBack(User userTo, UserScoreDetail item) {
		List<UserScoreDetailVo> result = new ArrayList<UserScoreDetailVo>();
		
		//如果是客服直接返回空数据，不做返利
		if (userTo.getUserType().equals(Constants.USER_TYPE_1)) return result;
		
		//如果不是付款的充值，不做返利
		if (!item.getScoreType().equals(Constants.SCORE_TYPE_1)) return result;
		
		Long pId = userTo.getpId();
		if (pId.equals(0L)) return result;
		
		BigDecimal scoreMoney = item.getScoreMoney();
		BigDecimal score = item.getScore();
		
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGetParents(1);
		searchVo.setLft(userTo.getLft());
		searchVo.setRgt(userTo.getRgt());
		searchVo.setOrderByProperty(" order by lft desc");
		
		List<User> list = userService.selectBySearchVo(searchVo);
		
		Short linkBackLevel = 1;
		for (int i = 0 ; i < list.size(); i++) {
			User pUser = list.get(i);
			if (!pUser.getUserType().equals(Constants.SCORE_TYPE_0)) continue;
			if (linkBackLevel > 6) break;
//			Short level = pUser.getLevel();
			BigDecimal leveRatio = BoleUtil.getLevelRatio(linkBackLevel);
			
			//充值返利金额计算
			BigDecimal scoreMoneyBack = MathBigDecimalUtil.mul(scoreMoney, leveRatio);
			scoreMoneyBack = MathBigDecimalUtil.round(scoreMoneyBack, 2);
			
			BigDecimal scoreAfter = pUser.getScoreMoney().add(scoreMoneyBack);
			scoreAfter = MathBigDecimalUtil.round(scoreAfter, 2);
			String remarks = "充值返利:" + MathBigDecimalUtil.round2(scoreMoney) +"x";
			remarks+= MathBigDecimalUtil.round(leveRatio.multiply(new BigDecimal(100)), 0) + "%=" + MathBigDecimalUtil.round2(scoreMoneyBack);
			
			//充值返利钻石数计算
			BigDecimal scoreBack = MathBigDecimalUtil.mul(score, leveRatio);
			scoreBack = MathBigDecimalUtil.round(scoreBack, 2);
			
			UserScoreDetail record = this.initPo();
			record.setUserIdFrom(0L);
			record.setUserIdTo(pUser.getUserId());
			record.setScoreMoney(scoreMoneyBack);
			record.setScore(scoreBack);
			record.setScoreType(Constants.SCORE_TYPE_2);
			record.setScorePre(pUser.getScoreMoney());
			record.setScoreAfter(scoreAfter);
			record.setLinkBackLevel(linkBackLevel);
			record.setLinkBackRatio(leveRatio);
			record.setLinkDetailId(item.getId());
			record.setRemarks(remarks);
			UserScoreDetailVo vo = this.getVo(record);
			result.add(vo);
			
			linkBackLevel++;
		}
		
		
		return result;
	}
}
