package com.bole.vo.user;

import com.bole.po.model.user.UserScoreDetail;

public class UserScoreDetailVo extends UserScoreDetail {
	
	private String gameIdFrom;
	
	private String gameIdTo;
	
	private String scoreTypeName;
	
	private String addTimeStr;

	public String getGameIdTo() {
		return gameIdTo;
	}

	public void setGameIdTo(String gameIdTo) {
		this.gameIdTo = gameIdTo;
	}

	public String getGameIdFrom() {
		return gameIdFrom;
	}

	public void setGameIdFrom(String gameIdFrom) {
		this.gameIdFrom = gameIdFrom;
	}

	public String getScoreTypeName() {
		return scoreTypeName;
	}

	public void setScoreTypeName(String scoreTypeName) {
		this.scoreTypeName = scoreTypeName;
	}

	public String getAddTimeStr() {
		return addTimeStr;
	}

	public void setAddTimeStr(String addTimeStr) {
		this.addTimeStr = addTimeStr;
	}
}
