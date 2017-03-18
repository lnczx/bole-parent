package com.bole.po.model.user;

import java.math.BigDecimal;

public class UserScoreCash {
    private Long id;

    private Long userId;

    private BigDecimal scoreCash;

    private Short status;

    private Long addTime;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getScoreCash() {
        return scoreCash;
    }

    public void setScoreCash(BigDecimal scoreCash) {
        this.scoreCash = scoreCash;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}