package com.revature.services;

import com.revature.exceptions.AuthenticatorException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.NegativeException;
import com.revature.exceptions.OverdraftException;
import com.revature.models.Account;
import com.revature.repos.AccountRepo;

import java.util.Optional;
import java.util.Set;

import static com.revature.AppDriver.app;

public class AccountService {

    private AccountRepo accountRepo;

    public AccountService(AccountRepo repo) {
        accountRepo = repo;
    }

    public Account findAccountByAccountId(int accountId) {

        if (accountId <= 0) {
            throw new InvalidInputException("Invalid account credentials given!");
        }
        Optional<Account> _authAccount = accountRepo.findAccountByAccountId(accountId);

        if (!_authAccount.isPresent()) {
            throw new AuthenticatorException("No such account exists!");
        }

        app.setCurrentAccount(_authAccount.get());
        return _authAccount.get();

    }

    public void registration(Account newAccount) {
        if (!isAccountValid(newAccount)) {
            throw new InvalidInputException("Invalid credentials given for registration.");
        }
        Optional<Account> existingAccount = accountRepo.findAccountByAccountId(newAccount.getAccountId());

        if (existingAccount.isPresent()) {
            throw new AuthenticatorException("Provided account id is already in use!");
        }
        accountRepo.save(newAccount);
        app.setCurrentAccount(newAccount);
    }

    public Set<Account> returnUsersAccounts(int currentUserId) {

        if (currentUserId <= 0) {
            throw new InvalidInputException("Please enter a positive, non-zero number!");
        }

        Set<Account> usersAccounts = accountRepo.findUsersAccounts(currentUserId);

        if (usersAccounts.isEmpty()) {
            throw new AuthenticatorException("No accounts exist for this user.");
        }
        return usersAccounts;
    }


    public boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getAccountName() == null || account.getAccountName().trim().equals("")) return false;
        if (account.getAccountId() == 0) return false;
        return true;
    }


    public double fundsUpdate(boolean isAdd, double funds) {
        Account currentAccount = app.getCurrentAccount();
        double balance = currentAccount.getBalance();
        double temp = funds;
        if (temp <= 0) {
            throw new NegativeException("Please enter a positive, non-zero number!");
        } else {
            if (isAdd) {
                balance = balance + temp;
            } else if (balance < temp) {
                throw new OverdraftException("This account does not support overdrafting.");
            } else {
                balance = balance - temp;
            }
            currentAccount.setBalance(balance);
            accountRepo.updateBalance(balance);
        }
        return balance;
    }
}
