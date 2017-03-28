package com.bole.service.impl.async;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.bole.po.model.user.User;
import com.bole.service.async.UserAsyncService;
import com.bole.service.user.UserLevelStatService;
import com.bole.service.user.UserService;

@Service
public class UserAsyncServiceImpl implements UserAsyncService {

	@Autowired
	public UserService userService;
	
	@Autowired
	private UserLevelStatService userLevelStatService;

	/**
	 * 用户激活后，相应的上级需要进行检测是否需要升级
	 */
	@Async
	@Override
	public Future<Boolean> userLevelupTree(Long userId) {
		userService.userLevelupTree(userId);
		return new AsyncResult<Boolean>(true);
	}
	
	
	/**
	 * 异步统计用户的各级会员人数和总人数
	 */
	@Async
	@Override
	public Future<Boolean> totalUser(Long userId) {
		User u = userService.selectByPrimaryKey(userId);
		for (int l = 0 ; l <=6; l++) {
			userLevelStatService.totalLevel(u, (short) l);
		}
		return new AsyncResult<Boolean>(true);
	}


}
