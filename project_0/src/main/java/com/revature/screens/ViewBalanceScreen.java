package com.revature.screens;

import com.revature.models.Account;
import java.text.NumberFormat;
import java.util.Locale;

import static com.revature.AppDriver.app;

public class ViewBalanceScreen extends Screen {

    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

    public ViewBalanceScreen() {
        super("ViewBalanceScreen", "/viewBalance");
    }

    @Override
    public void render() {

        Account currentAccount = app.getCurrentAccount();

        System.out.println("Your balance is: " + nf.format(currentAccount.getBalance()));
        app.getRouter().navigate("/Dashboard");

    }
}
