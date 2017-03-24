package com.bole.service.user;

import java.math.BigDecimal;
import java.util.List;

import com.bole.po.model.user.User;
import com.bole.po.model.user.UserScoreDetail;
import com.bole.vo.UserSearchVo;
import com.bole.vo.user.UserScoreDetailVo;
import com.github.pagehelper.PageInfo;

public interface UserScoreDetailService {

	int insertSelective(UserScoreDetail record);

	int deleteByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserScoreDetail record);

	UserScoreDetail initPo();

	UserScoreDetail selectByPrimaryKey(Long id);

	List<UserScoreDetail> selectBySearchVo(UserSearchVo searchVo);

	PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize);

	UserScoreDetailVo getVo(UserScoreDetail item);

	List<UserScoreDetailVo> getAgentTreePayBack(User userIdTo, UserScoreDetail item);

	BigDecimal totalScoreMoney(UserSearchVo searchVo);
	
}
