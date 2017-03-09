package com.bole.service.user;

import java.util.List;

import com.bole.po.model.user.UserLevelLog;
import com.bole.vo.UserSearchVo;
import com.github.pagehelper.PageInfo;

public interface UserLevelLogService {

	int insertSelective(UserLevelLog record);

	int deleteByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserLevelLog record);

	UserLevelLog initPo();

	UserLevelLog selectByPrimaryKey(Long id);

	List<UserLevelLog> selectBySearchVo(UserSearchVo searchVo);

	PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize);

}
