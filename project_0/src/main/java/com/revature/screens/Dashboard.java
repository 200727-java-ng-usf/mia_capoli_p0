package com.revature.screens;

import java.io.IOException;

import static com.revature.AppDriver.app;

public class Dashboard extends Screen{
    protected Dashboard() {
        super("DashboardScreen", "/dashboard");
    }

    @Override
    public void render() {
        System.out.println("Welcome to your Dashboard!\n");
        System.out.println("1) Check Balance");
        System.out.println("2) Add Funds");
        System.out.println("3) Withdraw Funds");
        System.out.println("3) Go home");

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
                    System.out.println("[LOG] - Invalid selection!");
                    app.getRouter().navigate("/dashboard");
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}