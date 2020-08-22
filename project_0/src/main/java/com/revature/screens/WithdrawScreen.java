package com.revature.screens;

import com.revature.exceptions.InvalidInputException;
import com.revature.models.Account;
import com.revature.services.AccountService;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.AppDriver.app;
import static java.lang.Integer.parseInt;

public class WithdrawScreen extends Screen {
    private AccountService accountService;

    public WithdrawScreen(AccountService accountService) {
        super("WithdrawFundsScreen", "/withdrawFunds");
        this.accountService = accountService;
    }

    @Override
    public void render() {

        try {
            Account currentAccount = app.getCurrentAccount();

            double balance = currentAccount.getBalance();
            double temp = 0.00d;

            System.out.println(" How much would you like to withdraw? ");

            temp = parseInt(app.getConsole().readLine());
            accountService.withdrawFunds(temp);
            System.out.println("Your balance is now: " + currentAccount.getBalance());

            app.getRouter().navigate("/Dashboard");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
