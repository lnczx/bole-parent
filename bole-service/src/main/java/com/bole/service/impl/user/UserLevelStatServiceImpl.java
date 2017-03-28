package com.bole.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.common.Constants;
import com.bole.po.dao.user.UserLevelStatMapper;
import com.bole.po.model.user.User;
import com.bole.po.model.user.UserLevelStat;
import com.bole.service.user.UserLevelStatService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.TimeStampUtil;

@Service
public class UserLevelStatServiceImpl implements UserLevelStatService {

	@Autowired
	private UserLevelStatMapper mapper;

	@Autowired
	private UserService userService;

	@Override
	public int insertSelective(UserLevelStat record) {
		return mapper.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLevelStat record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserLevelStat initPo() {
		UserLevelStat record = new UserLevelStat();

		record.setId(0L);
		record.setUserId(0L);
		record.setLevel(Constants.USER_LEVEL_0);
		record.setTotal(0);
		record.setAddTime(TimeStampUtil.getNowSecond());
		record.setUpdateTime(TimeStampUtil.getNowSecond());

		return record;
	}

	@Override
	public UserLevelStat selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserLevelStat> selectBySearchVo(UserSearchVo searchVo) {
		return mapper.selectBySearchVo(searchVo);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<UserLevelStat> list = mapper.selectByListPage(searchVo);
		PageInfo info = new PageInfo(list);
		return info;
	}

	/**
	 * 统计每个级别的会员数
	 */
	@Override
	public void totalLevel(User u, Short level) {
		Long userId = u.getUserId();
		Long pId = u.getpId();
		
		
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGetSubs(1);
		searchVo.setLft(u.getLft());
		searchVo.setRgt(u.getRgt());
		if (level > 0)
			searchVo.setLevel(level);
//		searchVo.setActive(Constants.USER_ACTIVE_1);
		Integer total = userService.totalUser(searchVo);
		if (total == null) total = 0;
		
		searchVo = new UserSearchVo();
		searchVo.setUserId(userId);
		searchVo.setLevel(level);
		List<UserLevelStat> list = this.selectBySearchVo(searchVo);
		
		UserLevelStat record = this.initPo();
		if (!list.isEmpty()) {
			record = list.get(0);
		}
		
		record.setUserId(userId);
		record.setLevel(level);
		record.setTotal(total);
		
		if (record.getId() > 0L) {
			record.setUpdateTime(TimeStampUtil.getNowSecond());
			this.updateByPrimaryKeySelective(record);
		} else {
			this.insertSelective(record);
		}
	}
}
