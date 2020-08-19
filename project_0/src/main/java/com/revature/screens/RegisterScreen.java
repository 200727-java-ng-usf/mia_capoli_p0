package com.revature.screens;

import com.revature.models.AppUser;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterScreen extends Screen{

    private UserService userService;

    public RegisterScreen(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void render() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String firstName;
        String lastName;
        String username;
        String password;

        try {
            System.out.println("Sign up for an account with the Bank of Capoli!");
            System.out.println("Please enter your First name: ");
            firstName = consoleReader.readLine();
            System.out.println("Please enter your Last name: ");
            lastName = consoleReader.readLine();
            System.out.println("Please enter a username: ");
            username = consoleReader.readLine();
            System.out.println("Please enter a password: ");
            password = consoleReader.readLine();

            AppUser newUser = new AppUser(username, password, firstName, lastName);
            AppUser registeredUser = userService.registration(newUser);
            //TODO add new user to the repo

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
