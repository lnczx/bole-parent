package com.bole.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.common.BoleUtil;
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
		record.setScore(0);
		record.setScoreType((short) 0);
		record.setScorePre(0);
		record.setScoreAfter(0);
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
		
		Long userIdFrom = vo.getUserIdFrom();
		User userFrom = userService.selectByPrimaryKey(userIdFrom);
		vo.setGameIdFrom(userFrom.getGameId());
		
		Long userIdTo = vo.getUserIdTo();
		User userTo = userService.selectByPrimaryKey(userIdTo);
		vo.setGameIdTo(userTo.getGameId());
		
		String scoreTypeName = BoleUtil.getScoreTypeName(vo.getScoreType());
		vo.setScoreTypeName(scoreTypeName);
		
		String addTimeStr = TimeStampUtil.timeStampToDateStr(vo.getAddTime() * 1000, "MM-dd HH:MM");
		vo.setAddTimeStr(addTimeStr);
		
		Integer totalPayBack = this.totalPayBack(vo.getId());
		if (totalPayBack == null )  totalPayBack = 0;
		vo.setTotalPayBack(totalPayBack);
		return vo;
	}
	
	@Override
	public Integer totalPayBack(Long linkDetailId) {
		Integer totalPayBack = 0;
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setLinkDetailId(linkDetailId);
		totalPayBack = mapper.totalPayBack(searchVo);		
		return totalPayBack;
	}
	
}
