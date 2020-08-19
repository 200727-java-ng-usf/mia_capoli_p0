package com.revature.screens;

import com.revature.models.AppUser;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoginScreen extends Screen {

    private UserService userService;

    public LoginScreen(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void render() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String username, password;

        try {
            System.out.println("Please provide your login credentials");
            System.out.print("Username: ");
            username = consoleReader.readLine();
            System.out.print("Password: ");
            password = consoleReader.readLine();

            AppUser authUser = userService.authenticate(username, password);
            System.out.println(authUser);

            //TODO save logged in user?
            //TODO implement navigation

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
