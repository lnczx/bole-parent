package com.bole.service.impl.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.common.Constants;
import com.bole.po.dao.user.UserMapper;
import com.bole.po.model.user.User;
import com.bole.po.model.user.UserLevelStat;
import com.bole.service.user.UserLevelLogService;
import com.bole.service.user.UserLevelStatService;
import com.bole.service.user.UserService;
import com.bole.vo.UserSearchVo;
import com.bole.vo.user.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meijia.utils.BeanUtilsExp;
import com.meijia.utils.ShareCodeUtil;
import com.meijia.utils.TimeStampUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private UserLevelStatService userLevelStatService;
	
	@Autowired
	private UserLevelLogService userLevelLogService;

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
		record.setUserType(Constants.USER_TYPE_0);
		record.setOpenId("");
		record.setNickName("");
		record.setHeadImg("");
		record.setGameId("");
		record.setPassword("");
		record.setInviteCode("");
		record.setLevel(Constants.USER_LEVEL_1);
		record.setpId(0L);
		record.setpGameId("");
		record.setpCode("");
		record.setScore(new BigDecimal(0));
		record.setScoreLastTime(0L);
		record.setEnable((short) 1);
		record.setActive(Constants.USER_ACTIVE_0);
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
		
		User pUser = this.selectByPrimaryKey(pId);
		String gameId = pUser.getGameId();
		
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setpCode(gameId);
		searchVo.setUserId(userId);
		List<User> list = this.selectBySearchVo(searchVo);
		
		if (list.isEmpty()) return false;
		
		return true;
	}

	@Override
	public String genShareCode(String gameId) {
		String shareCode = "";

		int i = 0;
		while (i < 10) {
			shareCode = ShareCodeUtil.toSerialCode(Long.valueOf(gameId));
			UserSearchVo searchVo = new UserSearchVo();
			searchVo.setInviteCode(shareCode);
			List<User> list = this.selectBySearchVo(searchVo);
			if (list.isEmpty()) {
				break;
			}
			i++;
		}

		return shareCode;
	}

	@Override
	public UserVo getVo(User item) {
		UserVo vo = new UserVo();

		BeanUtilsExp.copyPropertiesIgnoreNull(item, vo);

		vo.setTotalSubAgent(0);

		List<UserLevelStat> userLeveStats = new ArrayList<UserLevelStat>();
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setUserId(vo.getUserId());
		List<UserLevelStat> list = userLevelStatService.selectBySearchVo(searchVo);

		if (!list.isEmpty()) {
			for (UserLevelStat uls : list) {
				if (uls.getLevel().equals(Constants.USER_LEVEL_0)) {
					vo.setTotalSubAgent(uls.getTotal());
				} else {
					userLeveStats.add(uls);
				}
			}
		}
		vo.setUserLevelStats(userLeveStats);

		return vo;
	}

	// 获得全部上级的gameID,拼装成 001652001653
	@Override
	public String getPcode(Long userId) {
		String pCode = "";

		User u = this.selectByPrimaryKey(userId);
		Long pId = u.getpId();
		if (pId.equals(0L))
			return pCode;

		int i = 0;
		while (i < 6) {
			User pUser = this.selectByPrimaryKey(pId);
			pId = pUser.getpId();
			pCode = pUser.getGameId() + "-" + pCode;
			if (pId.equals(0L)) {
				break;
			} else {
				pUser = this.selectByPrimaryKey(pId);
			}
			i++;
		}

		pCode = pCode + "-" + u.getGameId();

		return pCode;
	}

	/**
	 * 代理激活后，需要进行上级的升级校验，进行升级.并记录到升级日志中.
	 * 1. 检测是否需要升级
	 * 2. 记录升级日志.
	 * 3. 统计上级用户的团队人数
	 * 4. 统计上级用户每一个级别的总人数
	 */
	@Override
	public Boolean userLevelupTree(Long userId) {
		User u = this.selectByPrimaryKey(userId);

		Long pId = u.getpId();
		if (pId.equals(0L))
			return true;

		int i = 0;
		while (i < 6) {
			User pUser = this.selectByPrimaryKey(pId);
			UserSearchVo searchVo = new UserSearchVo();
			searchVo.setpId(pUser.getUserId());
			searchVo.setActive(Constants.USER_ACTIVE_1);
			
			Integer totalUser = this.totalUser(searchVo);
			
			if (totalUser >= 3) {
				//1. 升级用户，
				Short levelPre = pUser.getLevel();
				Short levelAfter = (short) (levelPre + 1);
				if (!levelPre.equals(Constants.USER_LEVEL_6)) {
					pUser.setLevel(levelAfter);
					pUser.setUpdateTime(TimeStampUtil.getNowSecond());
					this.updateByPrimaryKeySelective(pUser);
					//2. 记录日志.
					userLevelLogService.setUserLevelLog(pUser.getUserId(), (short)0, levelPre, levelAfter, 0L);
				} 
			}
			
			//3. 统计总人数，并且记录下级每一级别的用户数.
			for (int l = 0 ; l <=6; l++) {
				userLevelStatService.totalLevel(pUser, (short) l);
			}
			if (totalUser < 3) break;
			if (pUser.getpId().equals(0L)) break;
			pId = pUser.getpId();
			i++;
		}

		return true;
	}

	/**
	 * 统计用户的下级人数
	 */
	@Override
	public Integer totalUser(UserSearchVo searchVo) {
		Integer total = mapper.totalSubUser(searchVo);
		if (total == null) total = 0;
		
		return total;
	}

}
