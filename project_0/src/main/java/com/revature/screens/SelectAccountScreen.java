package com.revature.screens;

import com.revature.exceptions.InvalidInputException;
import com.revature.models.Account;

import static com.revature.AppDriver.app;

import com.revature.services.AccountService;

import java.io.IOException;
import java.util.Set;


public class SelectAccountScreen extends Screen {

    private AccountService accountService;

    public SelectAccountScreen(AccountService accountService) {
        super("SelectAccountScreen", "/selectAccount");
        this.accountService = accountService;
    }


    @Override
    public void render() {
        Set<Account> accounts = accountService.returnUsersAccounts();
        if (accounts.isEmpty()) {
            app.getRouter().navigate("/addAccount");
        } else {
            for (Account userAccount : accounts) {
                System.out.println(userAccount.toString());
            }

            System.out.println("Please enter the account number that you would like to access:");

            try {
                System.out.print("> ");
                int userSelection = Integer.parseInt(app.getConsole().readLine().trim());
                Account currentAccount = accountService.findAccountByAccountId(userSelection);

                app.setCurrentAccount(currentAccount);
                app.getRouter().navigate("/Dashboard");

            } catch (IOException | InvalidInputException e) {
                System.err.println("Please enter a valid account number!");
                app.getRouter().navigate("/selectAccount");
            } catch (Exception e) {
                System.err.println("A problem occurred.");
                app.getRouter().navigate("/selectAccount");
            }
        }
    }
}
