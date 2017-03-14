package com.bole.vo;

public class UserSearchVo  {
	
	private Long userId;
	
	private Short userType;
	
	private String openId;
	
	private String gameId;
	
	private String password;
	
	private String inviteCode;
	
	private Long pId;
	
	private Long userIdFrom;
	
	private Long userIdTo;
	
	private Short logType;
	
	private Long adminId;
	
	private Short level;
	
	private Short status;
	
	private Short scoreType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserIdFrom() {
		return userIdFrom;
	}

	public void setUserIdFrom(Long userIdFrom) {
		this.userIdFrom = userIdFrom;
	}

	public Long getUserIdTo() {
		return userIdTo;
	}

	public void setUserIdTo(Long userIdTo) {
		this.userIdTo = userIdTo;
	}

	public Short getLogType() {
		return logType;
	}

	public void setLogType(Short logType) {
		this.logType = logType;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getScoreType() {
		return scoreType;
	}

	public void setScoreType(Short scoreType) {
		this.scoreType = scoreType;
	}

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
