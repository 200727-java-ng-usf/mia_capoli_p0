package com.revature.screens;

import com.revature.models.AppUser;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.AppDriver.app;

public class ViewBalanceScreen extends Screen{

    private UserService userService;

    public ViewBalanceScreen(UserService userService) {
        super("ViewBalanceScreen", "/viewBalance");
        this.userService = userService;
    }

    @Override
    public void render() {

        try {
            AppUser currentUser = app.getCurrentUser();



            //System.out.println("Your balance is: " + balance);
            //todo implement navigation


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
