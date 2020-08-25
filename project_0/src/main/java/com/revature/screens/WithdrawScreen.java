package com.revature.screens;

import com.revature.models.Account;
import com.revature.services.AccountService;

import java.text.NumberFormat;
import java.util.Locale;

import static com.revature.AppDriver.app;
import static java.lang.Integer.parseInt;

public class WithdrawScreen extends Screen {
    private AccountService accountService;

    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

    public WithdrawScreen(AccountService accountService) {
        super("WithdrawFundsScreen", "/withdrawFunds");
        this.accountService = accountService;
    }

    @Override
    public void render() {

        try {
            Account currentAccount = app.getCurrentAccount();

            double temp;

            System.out.println(" How much would you like to withdraw? ");

            temp = Double.parseDouble(app.getConsole().readLine());
            accountService.withdrawFunds(temp);
            System.out.println("Your balance is now: " + nf.format(currentAccount.getBalance()));

            app.getRouter().navigate("/Dashboard");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
