package com.bole.po.model.user;

import java.math.BigDecimal;

public class UserScoreDetail {
    private Long id;

    private Long userIdFrom;

    private Long userIdTo;

    private BigDecimal score;
    
    private BigDecimal scoreMoney;

    private Short scoreType;

    private BigDecimal scorePre;

    private BigDecimal scoreAfter;
    
    private Long linkDetailId;
    
    private Short linkBackLevel;
    
    private BigDecimal linkBackRatio;

    private String remarks;

    private Long addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getScorePre() {
        return scorePre;
    }

    public void setScorePre(BigDecimal scorePre) {
        this.scorePre = scorePre;
    }

    public BigDecimal getScoreAfter() {
        return scoreAfter;
    }

    public void setScoreAfter(BigDecimal scoreAfter) {
        this.scoreAfter = scoreAfter;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

	public Short getScoreType() {
		return scoreType;
	}

	public void setScoreType(Short scoreType) {
		this.scoreType = scoreType;
	}

	public Long getLinkDetailId() {
		return linkDetailId;
	}

	public void setLinkDetailId(Long linkDetailId) {
		this.linkDetailId = linkDetailId;
	}

	public Short getLinkBackLevel() {
		return linkBackLevel;
	}

	public void setLinkBackLevel(Short linkBackLevel) {
		this.linkBackLevel = linkBackLevel;
	}

	public BigDecimal getLinkBackRatio() {
		return linkBackRatio;
	}

	public void setLinkBackRatio(BigDecimal linkBackRatio) {
		this.linkBackRatio = linkBackRatio;
	}

	public BigDecimal getScoreMoney() {
		return scoreMoney;
	}

	public void setScoreMoney(BigDecimal scoreMoney) {
		this.scoreMoney = scoreMoney;
	}
}