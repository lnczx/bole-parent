package com.bole.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.po.dao.user.UserLevelLogMapper;
import com.bole.po.model.user.UserLevelLog;
import com.bole.service.user.UserLevelLogService;
import com.bole.vo.UserSearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.TimeStampUtil;



@Service
public class UserLevelLogServiceImpl implements UserLevelLogService {

	@Autowired
	private UserLevelLogMapper userLevelLogMapper;


	@Override
	public int insertSelective(UserLevelLog record) {
		return userLevelLogMapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return userLevelLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLevelLog record) {
		return userLevelLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserLevelLog initPo() {
		UserLevelLog record = new UserLevelLog();
		
		record.setId(0L);
		record.setUserId(0L);
		record.setLogType((short) 0);
		record.setLevelPre((short) 0);
		record.setLevelAfter((short) 0);
		record.setRemarks("");
		record.setAdminId(0L);
		record.setAddTime(TimeStampUtil.getNowSecond());

		return record;
	}

	@Override
	public UserLevelLog selectByPrimaryKey(Long id) {
		return userLevelLogMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<UserLevelLog> selectBySearchVo(UserSearchVo searchVo) {
		return userLevelLogMapper.selectBySearchVo(searchVo);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserLevelLog> list = userLevelLogMapper.selectByListPage(searchVo);
		PageInfo info = new PageInfo(list);
		return info;
	}
	
}
