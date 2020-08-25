package com.revature.screens;

import com.revature.services.AccountService;
import com.revature.services.UserService;

import java.io.IOException;

import static com.revature.AppDriver.app;

public class Dashboard extends Screen{

    public Dashboard() {
        super("DashboardScreen", "/Dashboard");
    }

    @Override
    public void render() {
        System.out.println("Welcome to your Dashboard!\n");
        System.out.println("1) Check Balance");
        System.out.println("2) Add Funds");
        System.out.println("3) Withdraw Funds");
        System.out.println("4) Go home");

        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine().trim();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/viewBalance");
                    break;
                case "2":
                    app.getRouter().navigate("/addFunds");
                    break;
                case "3":
                    app.getRouter().navigate("/withdrawFunds");
                    break;
                case "4":
                    app.getRouter().navigate("/home");
                    break;
                default:
                    System.err.println("Invalid selection!");
                    app.getRouter().navigate("/Dashboard");
            }

        } catch (IOException e) {
            System.err.println("Please enter a valid selection!");
            app.getRouter().navigate("/Dashboard");
        } catch (Exception e) {
            System.err.println("A problem occurred.");
            app.getRouter().navigate("/Dashboard");
        }
    }

}
