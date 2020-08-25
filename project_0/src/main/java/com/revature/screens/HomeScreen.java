package com.revature.screens;

import com.revature.util.ScreenRouter;

import java.io.IOException;
import static com.revature.AppDriver.app;

public class HomeScreen extends Screen {

    public HomeScreen() {
        super("HomeScreen", "/home");
    }

    @Override
    public void render() {

        System.out.println("Welcome to the Bank of Capoli!\n");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit Application");
        try {
            System.out.print("> ");
            String userSelection = app.getConsole().readLine().trim();

            switch (userSelection) {
                case "1":
                    app.getRouter().navigate("/login");
                    break;
                case "2":
                    app.getRouter().navigate("/register");
                    break;
                case "3":
                    app.setAppRunning(false);
                    break;
                default:
                    System.out.println("[ERROR] - Invalid selection!");
            }

        } catch (IOException e) {
            System.err.println("Please enter a valid selection!");
            app.getRouter().navigate("/home");
        } catch (Exception e) {
            System.err.println("A problem occurred.");
        }
    }
}