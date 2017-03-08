package com.bole.po.dao.user;

import java.util.List;

import com.bole.po.model.user.User;
import com.bole.po.model.user.UserScoreCash;
import com.bole.vo.UserSearchVo;

public interface UserScoreCashMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserScoreCash record);

    int insertSelective(UserScoreCash record);

    UserScoreCash selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserScoreCash record);

    int updateByPrimaryKey(UserScoreCash record);
    
    List<UserScoreCash> selectByListPage(UserSearchVo searchVo);
    
    List<UserScoreCash> selectBySearchVo(UserSearchVo searchVo);    
}