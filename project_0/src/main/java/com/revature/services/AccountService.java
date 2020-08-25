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

        if (accountId == 0) {
            throw new InvalidInputException("Invalid account credentials given!");
        }
        Account _authAccount = accountRepo.findUserByAccountId(accountId)
                .orElseThrow(AuthenticatorException::new);

        app.setCurrentAccount(_authAccount);
        return _authAccount;


    }

    public void registration(Account newAccount) {
        if (!isAccountValid(newAccount)) {
            throw new InvalidInputException("Invalid credentials given for registration.");
        }

        Optional<Account> existingAccount = accountRepo.findUserByAccountId(newAccount.getAccountId());

        if (existingAccount.isPresent()) {
            throw new AuthenticatorException("Provided account id is already in use!");
        }

        accountRepo.save(newAccount);
        app.setCurrentAccount(newAccount);
    }

    public Set<Account> returnUsersAccounts() {
        Set<Account> usersAccounts = accountRepo.findUsersAccounts(app.getCurrentUser());
        return usersAccounts;
    }

    public double addFunds(double fundsToAdd) {
        Account currentAccount = app.getCurrentAccount();
        double balance = currentAccount.getBalance();
        double temp = fundsToAdd;
        if (temp <= 0) {
            throw new NegativeException("Please enter a positive, non-zero number!");
        } else {
            balance = balance + temp;
            currentAccount.setBalance(balance);
            accountRepo.updateBalance(balance);
        }
        return balance;
    }

    public double withdrawFunds(double fundsToAdd) {
        Account currentAccount = app.getCurrentAccount();
        double balance = currentAccount.getBalance();
        double temp = fundsToAdd;
        if (temp <= 0) {
            throw new NegativeException("Please enter a positive, non-zero number!");
        } else if (balance < temp) {
            throw new OverdraftException("This account does not support overdrafting.");
        } else {
            balance = balance - temp;
            currentAccount.setBalance(balance);
            accountRepo.updateBalance(balance);
        }


        return balance;
    }

    public boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getAccountName() == null || account.getAccountName().trim().equals("")) return false;
        if (account.getAccountId() == 0) return false;
        return true;
    }

}
