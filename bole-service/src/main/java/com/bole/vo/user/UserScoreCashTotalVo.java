package com.bole.vo.user;

import java.math.BigDecimal;

public class UserScoreCashTotalVo {
	private Long userId;
	
	private String gameId;
	
	private BigDecimal totalScore;
	
	private BigDecimal totalCash;
	
	private BigDecimal totalCashing;
	
	private BigDecimal totalStore;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public BigDecimal getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
	}

	public BigDecimal getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}

	public BigDecimal getTotalCashing() {
		return totalCashing;
	}

	public void setTotalCashing(BigDecimal totalCashing) {
		this.totalCashing = totalCashing;
	}

	public BigDecimal getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(BigDecimal totalStore) {
		this.totalStore = totalStore;
	}
}
