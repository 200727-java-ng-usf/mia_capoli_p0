package com.revature.models;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class AccountUtilPOJO {


    /**
     * Account Util  Pojo
     * An object created to hold the joined accounts and user associated accounts tables
     */

    int accountId;
    int userId;


    public AccountUtilPOJO(int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    public AccountUtilPOJO() {

    }

    //Account getters and setters


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //Account method overrides.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountUtilPOJO that = (AccountUtilPOJO) o;
        return accountId == that.accountId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, userId);
    }

    @Override
    public String toString() {
        return "AccountUtilPOJO{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                '}';
    }
}


