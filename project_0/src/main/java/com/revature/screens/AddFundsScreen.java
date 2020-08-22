package com.revature.screens;

import com.revature.exceptions.InvalidInputException;
import com.revature.models.Account;
import com.revature.services.AccountService;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;

import static com.revature.AppDriver.app;
import static java.lang.Integer.parseInt;

public class AddFundsScreen extends Screen{

    private AccountService accountService;
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);


    public AddFundsScreen(AccountService accountService) {
        super("AddFundsScreen", "/addFunds");
        this.accountService = accountService;
    }

    @Override
    public void render() {

        try {
            Account currentAccount = app.getCurrentAccount();


            double temp;

            System.out.println(" How much would you like to add? ");

            temp = parseInt(app.getConsole().readLine());
            accountService.addFunds(temp);
            System.out.println("Your balance is now: " + nf.format(currentAccount.getBalance()));

            app.getRouter().navigate("/Dashboard");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
