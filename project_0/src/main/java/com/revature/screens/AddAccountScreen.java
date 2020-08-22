package com.revature.screens;

import com.revature.models.AppUser;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.AppDriver.app;

public class AddAccountScreen extends Screen {

    private UserService userService;

    public
    AddAccountScreen(UserService userService) {
        super("AddAccountScreen", "/addAccount");
        this.userService = userService;
    }

    @Override
    public void render() {

        try {
            AppUser currentUser = app.getCurrentUser();

            //create account;

            //associate account with user

            //this is your account
            //TODO implement navigation


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
