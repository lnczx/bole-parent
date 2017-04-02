package com.bole.vo.user;

import java.math.BigDecimal;

import com.bole.po.model.user.UserScoreDetail;

public class UserScoreDetailVo extends UserScoreDetail {
	
	private String gameIdFrom;
	
	private String gameIdTo;
	
	private Short levelTo;
	
	private String scoreTypeName;
	
	private String addTimeStr;
	
	private String addTimeStrFull;
	
	private String payBackRemarks;
	
	private String scoreStr;
	
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

	public Short getLevelTo() {
		return levelTo;
	}

	public void setLevelTo(Short levelTo) {
		this.levelTo = levelTo;
	}

	public String getPayBackRemarks() {
		return payBackRemarks;
	}

	public void setPayBackRemarks(String payBackRemarks) {
		this.payBackRemarks = payBackRemarks;
	}

	public String getScoreStr() {
		return scoreStr;
	}

	public void setScoreStr(String scoreStr) {
		this.scoreStr = scoreStr;
	}

	public String getAddTimeStrFull() {
		return addTimeStrFull;
	}

	public void setAddTimeStrFull(String addTimeStrFull) {
		this.addTimeStrFull = addTimeStrFull;
	}
}
