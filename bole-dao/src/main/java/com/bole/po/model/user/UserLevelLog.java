package com.bole.po.model.user;

public class UserLevelLog {
    private Long id;

    private Long userId;

    private Short logType;

    private Short levelPre;

    private Short levelAfter;

    private String remarks;

    private Long adminId;

    private Long addTime;

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

    public Short getLogType() {
        return logType;
    }

    public void setLogType(Short logType) {
        this.logType = logType;
    }

    public Short getLevelPre() {
        return levelPre;
    }

    public void setLevelPre(Short levelPre) {
        this.levelPre = levelPre;
    }

    public Short getLevelAfter() {
        return levelAfter;
    }

    public void setLevelAfter(Short levelAfter) {
        this.levelAfter = levelAfter;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}