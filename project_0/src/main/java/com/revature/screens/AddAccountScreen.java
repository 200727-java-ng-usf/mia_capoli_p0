package com.revature.screens;

import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AddAccountScreen extends Screen {

    private UserService userService;

    public
    AddAccountScreen(UserService userService) {
        super("AddAccountScreen", "/addAccount");
        this.userService = userService;
    }

    @Override
    public void render() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            //TODO get proper logged in user
            //AppUser loggedInUser = current AppUser();

            //create account;

            //associate account with user

            //this is your account
            //TODO implement navigation


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
