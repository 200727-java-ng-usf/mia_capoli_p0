package com.revature.screens;

import com.revature.services.UserService;

import static com.revature.AppDriver.app;

public class LoginScreen extends Screen {

    private UserService userService;

    public LoginScreen(UserService userService) {

        super("LoginScreen", "/login");
        this.userService = userService;
    }

    @Override
    public void render() {
        String username, password;

        try {
            System.out.println("Please provide your login credentials");
            System.out.print("Username: ");
            username = app.getConsole().readLine();
            System.out.print("Password: ");
            password = app.getConsole().readLine();

            userService.authenticate(username, password);

            if (app.isSessionValid()) {
                //check if user has account
                app.getRouter().navigate("/selectAccount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
