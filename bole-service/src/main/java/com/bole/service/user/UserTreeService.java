package com.bole.service.user;

import com.bole.po.model.user.User;

public interface UserTreeService {

	boolean isSubUser(Long pId, Long userId);

	Boolean userLevelupTree(Long userId);

	User genAgenUser(User pUser, User newUser);

	User moveAgenUser(User mUser, User pUser);

}
