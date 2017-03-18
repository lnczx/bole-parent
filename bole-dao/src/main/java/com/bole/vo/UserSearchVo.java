package com.bole.vo;

import java.util.List;

public class UserSearchVo  {
	
	private Long userId;
	
	private Short userType;
	
	private String openId;
	
	private String gameId;
	
	private String pGameId;
	
	private String password;
	
	private String inviteCode;
	
	private String pCode;
	
	private Long pId;
	
	private List<Long> pIds;
	
	private String gameIdFrom;
	
	private String gameIdTo;
	
	private Long userIdFrom;
	
	private Long userIdTo;
	
	private Short logType;
	
	private Long adminId;
	
	private Short level;
	
	private Short status;
	
	private Short scoreType;
	
	private Long linkDetailId;
	
	private Short active;

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

	public String getpGameId() {
		return pGameId;
	}

	public void setpGameId(String pGameId) {
		this.pGameId = pGameId;
	}

	public List<Long> getpIds() {
		return pIds;
	}

	public void setpIds(List<Long> pIds) {
		this.pIds = pIds;
	}

	public Long getLinkDetailId() {
		return linkDetailId;
	}

	public void setLinkDetailId(Long linkDetailId) {
		this.linkDetailId = linkDetailId;
	}

	public String getGameIdFrom() {
		return gameIdFrom;
	}

	public void setGameIdFrom(String gameIdFrom) {
		this.gameIdFrom = gameIdFrom;
	}

	public String getGameIdTo() {
		return gameIdTo;
	}

	public void setGameIdTo(String gameIdTo) {
		this.gameIdTo = gameIdTo;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public Short getActive() {
		return active;
	}

	public void setActive(Short active) {
		this.active = active;
	}
}
