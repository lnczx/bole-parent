package com.bole.vo.user;

import java.math.BigDecimal;
import java.util.List;

import com.bole.po.model.user.User;
import com.bole.po.model.user.UserLevelStat;

public class UserVo extends User {
	
	private Integer totalSubAgent;
	
	private BigDecimal totalPayBack;
	
	private BigDecimal totalCash;
	
	private BigDecimal totalStore;
	
	private List<UserLevelStat> userLevelStats;

	public Integer getTotalSubAgent() {
		return totalSubAgent;
	}

	public void setTotalSubAgent(Integer totalSubAgent) {
		this.totalSubAgent = totalSubAgent;
	}

	public List<UserLevelStat> getUserLevelStats() {
		return userLevelStats;
	}

	public void setUserLevelStats(List<UserLevelStat> userLevelStats) {
		this.userLevelStats = userLevelStats;
	}

	public BigDecimal getTotalPayBack() {
		return totalPayBack;
	}

	public void setTotalPayBack(BigDecimal totalPayBack) {
		this.totalPayBack = totalPayBack;
	}

	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}

	public BigDecimal getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(BigDecimal totalStore) {
		this.totalStore = totalStore;
	}
}
