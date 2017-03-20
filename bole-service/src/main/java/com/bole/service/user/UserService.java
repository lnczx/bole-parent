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


	boolean isSubUser(Long pId, Long userId);

	String genShareCode(String gameId);

	UserVo getVo(User item);

	String getPcode(Long userId);

	Boolean userLevelupTree(Long userId);

	Integer totalUser(UserSearchVo searchVo);

	User genAgenUser(User pUser, User newUser);

}
