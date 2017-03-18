package com.bole.service.async;

import java.util.concurrent.Future;

import com.bole.po.model.user.User;

public interface UserAsyncService {

	Future<Boolean> genPcode(User u);

	Future<Boolean> userLevelupTree(Long userId);

	Future<Boolean> totalUser(Long userId);


	
}