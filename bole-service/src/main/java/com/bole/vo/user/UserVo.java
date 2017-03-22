package com.bole.vo.user;

import java.math.BigDecimal;
import java.util.List;

import com.bole.po.model.user.User;
import com.bole.po.model.user.UserLevelStat;

public class UserVo extends User {
	
	private String scoreLastTimeStr;
	
	private Integer totalSubAgent;
		
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

	public String getScoreLastTimeStr() {
		return scoreLastTimeStr;
	}

	public void setScoreLastTimeStr(String scoreLastTimeStr) {
		this.scoreLastTimeStr = scoreLastTimeStr;
	}
}
