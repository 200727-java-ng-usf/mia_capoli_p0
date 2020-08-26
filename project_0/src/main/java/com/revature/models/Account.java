package com.revature.models;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Account Object Pojo
 * An object created to hold accounts and their properties.
 */
public class Account {


    //Account fields
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    String accountName;
    int accountId;
    double balance;


    //Account Constructors
    public Account() {
    }

    public Account(String accountName, int accountId) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.balance = 0.00d;
    }

    public Account(int accountId, String accountName, double balance) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.balance = balance;
    }


    //Account getters and setters
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    //Account method overrides.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                Double.compare(account.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance);
    }


    @Override
    public String toString() {
        return " Account Name: " + accountName +
                ", Account Id: " + accountId +
                ", Balance: " + nf.format(balance);
    }
}
