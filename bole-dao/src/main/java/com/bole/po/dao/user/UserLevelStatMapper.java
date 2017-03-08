package com.bole.po.dao.user;

import java.util.List;

import com.bole.po.model.user.UserLevelStat;
import com.bole.vo.UserSearchVo;

public interface UserLevelStatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLevelStat record);

    int insertSelective(UserLevelStat record);

    UserLevelStat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLevelStat record);

    int updateByPrimaryKey(UserLevelStat record);
    
    List<UserLevelStat> selectByListPage(UserSearchVo searchVo);
    
    List<UserLevelStat> selectBySearchVo(UserSearchVo searchVo);
}