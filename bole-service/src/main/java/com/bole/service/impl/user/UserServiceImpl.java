package com.bole.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.po.dao.user.UserMapper;
import com.bole.po.model.user.User;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.TimeStampUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;


	@Override
	public int insertSelective(User record) {
		return mapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User initPo() {
		User record = new User();
		
		record.setUserId(0L);
		record.setUserType((short) 0);
		record.setOpenId("");
		record.setNickName("");
		record.setHeadImg("");
		record.setGameId("");
		record.setPassword("");
		record.setInviteCode("");
		record.setLevel((short) 1);
		record.setpId(0L);
		record.setpGameId("");
		record.setScore(0);
		record.setScoreLastTime(0L);
		record.setEnable((short) 1);
		record.setActive((short) 1);
		record.setAddTime(TimeStampUtil.getNowSecond());
		record.setUpdateTime(TimeStampUtil.getNowSecond());

		return record;
	}

	@Override
	public User selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<User> selectBySearchVo(UserSearchVo searchVo) {
		return mapper.selectBySearchVo(searchVo);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PageInfo selectByListPage(UserSearchVo searchVo, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = mapper.selectByListPage(searchVo);
		PageInfo info = new PageInfo(list);
		return info;
	}
	
	@Override
	public User genUser(String openId, String nickName, String headImg, String gameId) {
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setOpenId(openId);
		List<User> users = this.selectBySearchVo(searchVo);
		User u = this.initPo();
		if (users.isEmpty()) {
			// 验证手机号是否已经注册，如果未注册，则自动注册用户，
			
			u.setOpenId(openId);
			u.setNickName(nickName);
			u.setHeadImg(headImg);
			u.setGameId(gameId);
			this.insertSelective(u);
		} else {
			u = users.get(0);
		}

		return u;
	}
	
	@Override
	public boolean isSubUser(Long pId, Long userId) {
		boolean isExist = false;
		int i = 0;
		List<Long> pIds = new ArrayList<Long>();
		pIds.add(pId);
		while (true) {
			UserSearchVo searchVo = new UserSearchVo();
			searchVo.setpIds(pIds);
			List<User> list = this.selectBySearchVo(searchVo);
			
			if (list.isEmpty()) {
				isExist = false;
				break;
			} 
			pIds = new ArrayList<Long>();
			for(User item : list) {
				pIds.add(item.getUserId());
				if (item.getUserId().equals(userId)) {
					isExist = true;
					break;
				}
			}
		}
		
		
		return isExist;
	}
	
}
