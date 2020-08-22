package com.revature.screens;

import com.revature.models.Account;
import com.revature.models.AppUser;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.AppDriver.app;

public class ViewBalanceScreen extends Screen {


    public ViewBalanceScreen() {
        super("ViewBalanceScreen", "/viewBalance");
    }

    @Override
    public void render() {

        Account currentAccount = app.getCurrentAccount();
        AppUser currentUser = app.getCurrentUser();

        System.out.println("Your balance is: " + currentAccount.getBalance());
        System.out.println("1) Add Funds");
        System.out.println("2) Withdraw Funds");
        System.out.println("3) Go home");

        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine().trim();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/addFunds");
                    break;
                case "2":
                    app.getRouter().navigate("/withdrawFunds");
                    break;
                case "3":
                    app.getRouter().navigate("/home");
                    break;
                default:
                    System.out.println("[LOG] - Invalid selection!");
                    app.getRouter().navigate("/dashboard");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
