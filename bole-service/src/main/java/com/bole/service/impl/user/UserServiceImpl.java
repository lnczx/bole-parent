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
import com.bole.service.user.UserScoreCashService;
import com.bole.service.user.UserScoreDetailService;
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
	
	@Autowired
	private UserScoreDetailService userScoreDetailService;
	
	@Autowired
	private UserScoreCashService userScoreCashService;

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
		record.setPayAccount("");
		record.setHeadImg("");
		record.setGameId("");
		record.setPassword("");
		record.setInviteCode("");
		record.setLevel(Constants.USER_LEVEL_1);
		record.setpId(0L);
		record.setpGameId("");
		record.setLft(0);
		record.setRgt(0);
		record.setScoreMoney(new BigDecimal(0));
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
		Long userId = vo.getUserId();

		//团队成员分布情况
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
		
		vo.setScoreLastTimeStr("");
		Long lastScoreTime = vo.getScoreLastTime() * 1000;
		if (lastScoreTime > 0L) {
			vo.setScoreLastTimeStr(TimeStampUtil.timeStampToDateStr(lastScoreTime, "MM-dd"));
		}
		return vo;
	}

	/**
	 * 统计用户的人数
	 */
	@Override
	public Integer totalUser(UserSearchVo searchVo) {
		Integer total = mapper.totalUser(searchVo);
		if (total == null) total = 0;
		
		return total;
	}

}
