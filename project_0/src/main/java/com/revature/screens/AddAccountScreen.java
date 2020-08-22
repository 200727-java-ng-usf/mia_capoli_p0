package com.revature.screens;

import com.revature.models.Account;
import com.revature.models.AppUser;
import com.revature.services.AccountService;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.AppDriver.app;

public class AddAccountScreen extends Screen {

    private AccountService accountService;

    public
    AddAccountScreen(AccountService accountService) {
        super("AddAccountScreen", "/addAccount");
        this.accountService = accountService;
    }

    @Override
    public void render() {
        int accountId;
        String accountName;


        try {
            System.out.println("Add a bank account!");
            System.out.println("Please enter your desired Account Id: ");
            accountId = Integer.parseInt(app.getConsole().readLine());
            System.out.println("Please enter the account name: ");
            accountName = app.getConsole().readLine();

            Account newAccount = new Account(accountName, accountId);
            accountService.registration(newAccount);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/selectAccount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
