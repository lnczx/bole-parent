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
		record.setScoreType((short) 0);
		record.setScorePre(new BigDecimal(0));
		record.setScoreAfter(new BigDecimal(0));
		record.setLinkDetailId(0L);
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
		
		String addTimeStr = TimeStampUtil.timeStampToDateStr(vo.getAddTime() * 1000, "MM-dd HH:MM");
		vo.setAddTimeStr(addTimeStr);
		
		BigDecimal totalPayBack = this.totalPayBack(vo.getId());
		if (totalPayBack == null )  totalPayBack = new BigDecimal(0);
		vo.setTotalPayBack(totalPayBack);
		return vo;
	}
	
	@Override
	public BigDecimal totalPayBack(Long linkDetailId) {
		BigDecimal totalPayBack = new BigDecimal(0);
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setLinkDetailId(linkDetailId);
		totalPayBack = mapper.totalPayBack(searchVo);		
		return totalPayBack;
	}
	
	/**
	 * 计算代理返利的情况
	 */
	@Override
	public List<UserScoreDetailVo> getAgentTreePayBack(User userIdTo, UserScoreDetail item) {
		List<UserScoreDetailVo> result = new ArrayList<UserScoreDetailVo>();
		
		//如果是客服直接返回空数据，不做返利
		if (userIdTo.getUserType().equals(Constants.USER_TYPE_1)) return result;
		
		Long pId = userIdTo.getpId();
		if (pId.equals(0L)) return result;
		
		BigDecimal score = item.getScore();
		int i = 0;
		while (i < 6) {
			User pUser = userService.selectByPrimaryKey(pId);
			
			Short level = pUser.getLevel();
			BigDecimal leveRatio = BoleUtil.getLevelRatio(level);
			
			BigDecimal scoreBack = MathBigDecimalUtil.mul(score, leveRatio);
			scoreBack = MathBigDecimalUtil.round(scoreBack, 2);
			
			BigDecimal scoreAfter = pUser.getScore().add(scoreBack);
			scoreAfter = MathBigDecimalUtil.round(scoreAfter, 2);
			String remarks = "充值返利:" + MathBigDecimalUtil.round2(score) +"x";
			remarks+= MathBigDecimalUtil.round(leveRatio.multiply(new BigDecimal(100)), 0) + "%=" + MathBigDecimalUtil.round2(scoreBack);
			
			UserScoreDetail record = this.initPo();
			record.setUserIdFrom(0L);
			record.setUserIdTo(pUser.getUserId());
			record.setScore(scoreBack);
			record.setScoreType(Constants.SCORE_TYPE_2);
			record.setScorePre(pUser.getScore());
			record.setScoreAfter(scoreAfter);
			record.setRemarks(remarks);
			UserScoreDetailVo vo = this.getVo(record);
			
			result.add(vo);
		
			if (pUser.getpId().equals(0L)) break;
			pId = pUser.getpId();
			i++;
		}
		
		
		return result;
	}
}
