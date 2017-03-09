package com.bole.service.user;

import java.util.List;

import com.bole.po.model.user.UserLevelStat;
import com.bole.vo.UserSearchVo;
import com.github.pagehelper.PageInfo;

public interface UserLevelStatService {

	int insertSelective(UserLevelStat record);

	int deleteByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserLevelStat record);

	UserLevelStat initPo();

	UserLevelStat selectByPrimaryKey(Long id);

	List<UserLevelStat> selectBySearchVo(UserSearchVo searchVo);

	PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize);

}
