package com.bole.service.user;

import java.util.List;

import com.bole.po.model.user.User;
import com.bole.vo.UserSearchVo;
import com.bole.vo.user.UserVo;
import com.github.pagehelper.PageInfo;

public interface UserService {

	int insertSelective(User record);

	int deleteByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(User record);

	User initPo();

	User selectByPrimaryKey(Long id);

	List<User> selectBySearchVo(UserSearchVo searchVo);

	PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize);

	String genShareCode(String gameId);

	UserVo getVo(User item);

	Integer totalUser(UserSearchVo searchVo);

}
