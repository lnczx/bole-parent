package com.bole.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bole.common.Constants;
import com.bole.po.dao.user.UserMapper;
import com.bole.po.model.user.User;
import com.bole.service.user.UserLevelLogService;
import com.bole.service.user.UserLevelStatService;
import com.bole.service.user.UserService;
import com.bole.service.user.UserTreeService;
import com.bole.vo.UserSearchVo;
import com.meijia.utils.TimeStampUtil;

@Service
public class UserTreeServiceImpl implements UserTreeService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserLevelStatService userLevelStatService;
	
	@Autowired
	private UserLevelLogService userLevelLogService;
	
	@Autowired
	private UserMapper mapper;
	
	/**
	 * 添加用户，设计到左右分支的树形结构. 只应用于会员的新增
	 * @param pUser  上级
	 * @param newUser 新增节点
	 * @return
	 */
	@Override
	public User genAgenUser(User pUser, User newUser) {
			
		String gameId = newUser.getGameId();

		//生成注册码
		String shareCode = userService.genShareCode(gameId);
		newUser.setInviteCode(shareCode);
		
		Integer pUserRgt = pUser.getRgt();
		newUser.setLft(pUserRgt);
		newUser.setRgt(pUserRgt + 1);
		
		//先更新左右值之后，再进行插入
		mapper.updateAllLeft(pUser);
		mapper.updateAllRight(pUser);
		
		userService.insertSelective(newUser);

		return newUser;
	}
	
	/**
	 * 移动用户及所有下属的子类到某个节点，设计到左右分支的树形结构.
	 * @param mUser  移动节点
	 * @param pUser  移动到的父级
	 * @return
	 */
	@Override
	public User moveAgenUser(User mUser, User pUser) {
		
		Integer pLft = pUser.getLft();
		Integer pRgt = pUser.getRgt();
		Long pId = pUser.getUserId();
		String pGameId = pUser.getGameId();
		
		Integer selfLft = mUser.getLft();
		Integer selfRgt = mUser.getRgt();
		
		
		//找出mUser 所有的子类.
		//找出所有的上级
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGetSubs(1);
		searchVo.setLft(selfLft);
		searchVo.setRgt(selfRgt);
		List<User> subs = userService.selectBySearchVo(searchVo);
		
		Integer val = selfRgt - selfLft;
		//如果pUser Rgt > mUser
		if (pRgt > selfRgt) {
			//执行的SQL为
			//update user set lft = lft - $val - 1 where lft > $selfLft and rgt <= $pRgt;
			//update user set rgt = rgt - $val - 1 where rgt > $selfRgt and rgt < $pRgt
			//update user set lft = lft + $offset, rgt = rgt + offset where user_id in ();
			Integer offset = selfLft - pRgt;
			
			String sql1 = "update user set lft = lft - " + val +" - 1 where lft > "+ selfLft + " and rgt <= " +pRgt;
			System.out.println(sql1);
			mapper.updateBySql(sql1);
			
			String sql2 = "update user set rgt = rgt - " + val +" - 1 where rgt > "+ selfRgt + " and rgt < " +pRgt;
			System.out.println(sql2);
			mapper.updateBySql(sql2);
			
			
			
			
			if (!subs.isEmpty()) {
				String sql3 = "update user set lft = lft + " + offset + ", rgt = rgt +" + offset;
				sql3+= " where user_id in (";
				for (User subUser : subs) {
					sql3+= subUser.getUserId() + ",";
				}
				sql3+= ")";
				System.out.println(sql3);
				mapper.updateBySql(sql3);
			}

		} else {
			//执行的SQL为
			//update set lft = lft + $val + 1 where lft > $pRgt and lft < $selfLft;
			//update set rgt = rgt + $val + 1 where rgt > $pRgt and rgt < $selfLft;
			//update user set lft = lft -$offset , rgt = rgt - $offset where user_id in ();
			Integer offset = pRgt - selfRgt;
			
			String sql1 = "update user set lft = lft + " + val +" + 1 where lft > "+ pRgt + " and lft <= " + selfLft;
			System.out.println(sql1);
			mapper.updateBySql(sql1);
			
			String sql2 = "update user set rgt = rgt + " + val +" + 1 where rgt > "+ pRgt + " and rgt < " + selfLft;
			System.out.println(sql2);
			mapper.updateBySql(sql2);
			
			
			
			if (!subs.isEmpty()) {
				String sql3 = "update user set lft = lft - " + offset + ", rgt = rgt -" + offset;
				sql3+= " where user_id in (";
				for (User subUser : subs) {
					sql3+= subUser.getUserId() + ",";
				}
				sql3+= ")";
				System.out.println(sql3);
				mapper.updateBySql(sql3);
			}
		}
		
		//更新mUser 的信息
		mUser.setpId(pUser.getUserId());
		mUser.setpGameId(pUser.getGameId());
		userService.updateByPrimaryKeySelective(mUser);
		
		return mUser;
	}

	@Override
	public boolean isSubUser(Long pId, Long userId) {
		
		User pUser = userService.selectByPrimaryKey(pId);
		
		if (pUser == null) return false;
		
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGetSubs(1);
		searchVo.setLft(pUser.getLft());
		searchVo.setRgt(pUser.getRgt());
		searchVo.setUserId(userId);
		List<User> list = userService.selectBySearchVo(searchVo);
		
		if (!list.isEmpty()) return true;
		return false;
	}

	/**
	 * 会员激活后，需要进行上级的升级校验，进行升级.并记录到升级日志中.
	 * 1. 检测是否需要升级
	 * 2. 记录升级日志.
	 * 3. 统计上级用户的团队人数
	 * 4. 统计上级用户每一个级别的总人数
	 */
	@Override
	public Boolean userLevelupTree(Long userId) {
		User u = userService.selectByPrimaryKey(userId);
		Long pId = u.getpId();
		if (pId.equals(0L))
			return true;
		//找出所有的上级
		UserSearchVo searchVo = new UserSearchVo();
		searchVo.setGetParents(1);
		searchVo.setLft(u.getLft());
		searchVo.setRgt(u.getRgt());
		searchVo.setOrderByProperty(" order by lft desc");
		
		List<User> list = userService.selectBySearchVo(searchVo);
		for (int i = 0 ; i < list.size(); i++) {
			User pUser = list.get(i);
			if (pUser.getUserId().equals(1L)) continue;
			UserSearchVo searchVo1 = new UserSearchVo();
			searchVo1.setpId(pUser.getUserId());
			searchVo1.setLevel(pUser.getLevel());
			searchVo1.setActive(Constants.USER_ACTIVE_1);
			Integer totalUser = userService.totalUser(searchVo1);
			
			boolean canUp = false;
			if (pUser.getLevel().equals(Constants.USER_LEVEL_1)) {
				if (totalUser >= 3) canUp = true;
			} else {
				if (totalUser >= 2) canUp = true;
			}
			
			if (canUp) {
				//1. 升级用户，
				Short levelPre = pUser.getLevel();
				Short levelAfter = (short) (levelPre + 1);
				if (!levelPre.equals(Constants.USER_LEVEL_6)) {
					pUser.setLevel(levelAfter);
					pUser.setUpdateTime(TimeStampUtil.getNowSecond());
					userService.updateByPrimaryKeySelective(pUser);
					//2. 记录日志.
					userLevelLogService.setUserLevelLog(pUser.getUserId(), (short)0, levelPre, levelAfter, 0L);
				} 
			}
			
			//3. 统计总人数，并且记录下级每一级别的用户数.
			for (int l = 0 ; l <=6; l++) {
				userLevelStatService.totalLevel(pUser, (short) l);
			}
		}

		return true;
	}
}
