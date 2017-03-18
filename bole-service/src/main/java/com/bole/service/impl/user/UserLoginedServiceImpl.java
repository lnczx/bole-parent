package com.bole.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.service.user.UserLoginedService;
import com.bole.po.dao.user.UserLoginedMapper;
import com.bole.po.model.user.UserLogined;
import com.meijia.utils.TimeStampUtil;

@Service
public class UserLoginedServiceImpl implements UserLoginedService{

	@Autowired
	private UserLoginedMapper userLoginedMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return userLoginedMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserLogined record) {
		return userLoginedMapper.insert(record);
	}

	@Override
	public int insertSelective(UserLogined record) {
		return userLoginedMapper.insertSelective(record);
	}

	@Override
	public UserLogined selectByPrimaryKey(Long id) {
		return userLoginedMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(UserLogined record) {
		return userLoginedMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLogined record) {
		return userLoginedMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public UserLogined initPo() {
		UserLogined record = new UserLogined();
		record.setAddTime(TimeStampUtil.getNow()/1000);
		record.setLoginFrom((short) 0);//0 = APP 1 = 微网站 2 = 管理后台
		record.setMobile("");
		record.setUserId(0L);
		record.setLoginIp(0L);
		return record;
	}
	
	@Override
	public int selectByCount(Long userId) {
		return userLoginedMapper.selectByCount(userId);
	}

	@Override
	public List<Long> selectUserIdsLastMonth() {
		return userLoginedMapper.selectUserIdsLastMonth();
	}

}