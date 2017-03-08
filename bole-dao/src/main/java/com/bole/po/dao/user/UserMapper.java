package com.bole.po.dao.user;

import java.util.List;

import com.bole.po.model.user.User;
import com.bole.vo.UserSearchVo;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectByListPage(UserSearchVo searchVo);
    
    List<User> selectBySearchVo(UserSearchVo searchVo);
}