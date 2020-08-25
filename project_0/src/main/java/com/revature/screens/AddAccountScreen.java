package com.revature.screens;


import com.revature.exceptions.InvalidInputException;
import com.revature.models.Account;

import com.revature.services.AccountService;

import java.io.IOException;


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

        } catch (NumberFormatException nfe) {
            System.err.println("Please enter a number!");
            app.getRouter().navigate("/addAccount");
        } catch (IOException | InvalidInputException ioe) {
            System.err.println("Please enter a proper input!");
            app.getRouter().navigate("/addAccount");
        } catch (Exception e) {
            System.err.println("A problem occurred.");
            app.getRouter().navigate("/addAccount");
        }

    }
}
