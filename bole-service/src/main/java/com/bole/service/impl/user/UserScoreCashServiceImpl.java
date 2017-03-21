package com.bole.service.impl.user;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.po.dao.user.UserScoreCashMapper;
import com.bole.po.model.user.UserScoreCash;
import com.bole.service.user.UserScoreCashService;
import com.bole.vo.UserSearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.TimeStampUtil;



@Service
public class UserScoreCashServiceImpl implements UserScoreCashService {

	@Autowired
	private UserScoreCashMapper mapper;


	@Override
	public int insertSelective(UserScoreCash record) {
		return mapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserScoreCash record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserScoreCash initPo() {
		UserScoreCash record = new UserScoreCash();
		
		record.setId(0L);
		record.setUserId(0L);
		record.setScoreCash(new BigDecimal(0));
		record.setStatus((short) 0);
		
		record.setAddTime(TimeStampUtil.getNowSecond());
		record.setUpdateTime(TimeStampUtil.getNowSecond());

		return record;
	}

	@Override
	public UserScoreCash selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<UserScoreCash> selectBySearchVo(UserSearchVo searchVo) {
		return mapper.selectBySearchVo(searchVo);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserScoreCash> list = mapper.selectByListPage(searchVo);
		PageInfo info = new PageInfo(list);
		return info;
	}
	
	@Override
	public BigDecimal totalCash(UserSearchVo searchVo) {
		BigDecimal totalCash = mapper.totalCash(searchVo);
		if (totalCash == null) totalCash = new BigDecimal(0);
		return totalCash;
	}
	
}
