package com.revature.services;

import com.revature.exceptions.AuthenticatorException;
import com.revature.models.Account;
import com.revature.repos.AccountRepo;

import java.util.Optional;

import static com.revature.AppDriver.app;

public class AccountService {

    private AccountRepo accountRepo;

    public AccountService(AccountRepo repo) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        accountRepo = repo;
    }

    public void authenticate(int accountId, String accountName) {

        if (accountId == 0 || accountName == null || accountName.trim().equals("")) {
            throw new AuthenticatorException("Invalid account credentials given!");
        }
        Account authAccount = accountRepo.findAccountByCredentials(accountName, accountId)
                .orElseThrow(AuthenticatorException::new);


        app.setCurrentAccount(authAccount);

    }

    public void registration(Account newAccount) {
        if (!isAccountValid(newAccount)) {
            throw new RuntimeException("Invalid credentials given for registration.");
        }

        Optional<Account> existingAccount = accountRepo.findUserByAccountId(newAccount.getAccountId());

        if (existingAccount.isPresent()) {
            throw new RuntimeException("Provided account id is already in use!");
        }


        accountRepo.save(newAccount);
        app.setCurrentAccount(newAccount);
    }


    public boolean isAccountValid(Account account) {
        if (account == null) return false;
        if (account.getAccountName() == null || account.getAccountName().trim().equals("")) return false;
        if (account.getAccountId() == 0 ) return false;
        return true;
    }

}