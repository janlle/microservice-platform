package com.leone.microservice.transaction.entity;

import java.util.Date;

public class Account {

    private Long accountId;

    private Long userId;

    private String account;

    private Integer preMoney;

    private Integer money;

    private Integer version;

    private Boolean deleted;

    private Date createTime;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(Integer preMoney) {
        this.preMoney = preMoney;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", account='" + account + '\'' +
                ", userId=" + userId +
                ", preMoney=" + preMoney +
                ", money=" + money +
                ", version=" + version +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                '}';
    }
}