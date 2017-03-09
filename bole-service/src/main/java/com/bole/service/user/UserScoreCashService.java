package com.bole.service.user;

import java.util.List;

import com.bole.po.model.user.UserScoreCash;
import com.bole.vo.UserSearchVo;
import com.github.pagehelper.PageInfo;

public interface UserScoreCashService {

	int insertSelective(UserScoreCash record);

	int deleteByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserScoreCash record);

	UserScoreCash initPo();

	UserScoreCash selectByPrimaryKey(Long id);

	List<UserScoreCash> selectBySearchVo(UserSearchVo searchVo);

	PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize);

}
