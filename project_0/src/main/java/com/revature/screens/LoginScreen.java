package com.revature.screens;

import com.revature.models.AppUser;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.AppDriver.app;

public class LoginScreen extends Screen {

    private UserService userService;

    public LoginScreen(UserService userService) {

        super("LoginScreen", "/login");
        this.userService = userService;
    }

    @Override
    public void render() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String username, password;

        try {
            System.out.println("Please provide your login credentials");
            System.out.print("Username: ");
            username = app.getConsole().readLine();
            System.out.print("Password: ");
            password = app.getConsole().readLine();

            userService.authenticate(username, password);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/selectAccount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
