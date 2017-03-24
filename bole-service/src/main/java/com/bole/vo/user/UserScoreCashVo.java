package com.bole.vo.user;

import com.bole.po.model.user.UserScoreCash;

public class UserScoreCashVo extends UserScoreCash {
	
	private String gameId;
	
	private String statusName;
	
	private String addTimeStr;
	
	private String payAccount;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getAddTimeStr() {
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	

}
