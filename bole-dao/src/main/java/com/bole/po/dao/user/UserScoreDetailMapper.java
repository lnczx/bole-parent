package com.bole.po.dao.user;

import java.math.BigDecimal;
import java.util.List;

import com.bole.po.model.user.UserScoreDetail;
import com.bole.vo.UserSearchVo;

public interface UserScoreDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserScoreDetail record);

    int insertSelective(UserScoreDetail record);

    UserScoreDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserScoreDetail record);

    int updateByPrimaryKey(UserScoreDetail record);
    
    List<UserScoreDetail> selectByListPage(UserSearchVo searchVo);
    
    List<UserScoreDetail> selectBySearchVo(UserSearchVo searchVo);  
    
	BigDecimal totalScoreMoney(UserSearchVo searchVo);
}