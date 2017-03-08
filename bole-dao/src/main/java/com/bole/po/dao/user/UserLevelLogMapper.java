package com.bole.po.dao.user;

import java.util.List;

import com.bole.po.model.user.UserLevelLog;
import com.bole.vo.UserSearchVo;

public interface UserLevelLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLevelLog record);

    int insertSelective(UserLevelLog record);

    UserLevelLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLevelLog record);

    int updateByPrimaryKey(UserLevelLog record);
    
    List<UserLevelLog> selectByListPage(UserSearchVo searchVo);
    
    List<UserLevelLog> selectBySearchVo(UserSearchVo searchVo);
}