package com.bole.po.model.user;

public class UserScoreDetail {
    private Long id;

    private Long userIdFrom;

    private Long userIdTo;

    private Integer score;

    private Short score_Type;

    private Integer scorePre;

    private Integer scoreAfter;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Short getScore_Type() {
        return score_Type;
    }

    public void setScore_Type(Short score_Type) {
        this.score_Type = score_Type;
    }

    public Integer getScorePre() {
        return scorePre;
    }

    public void setScorePre(Integer scorePre) {
        this.scorePre = scorePre;
    }

    public Integer getScoreAfter() {
        return scoreAfter;
    }

    public void setScoreAfter(Integer scoreAfter) {
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
}