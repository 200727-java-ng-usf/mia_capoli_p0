package com.revature.screens;

import com.revature.models.AppUser;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ViewBalanceScreen extends Screen{

    private UserService userService;

    public ViewBalanceScreen(UserService userService) {
        super("ViewBalanceScreen", "/viewBalance");
        this.userService = userService;
    }

    @Override
    public void render() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            //todo get proper logged in user
            //AppUser loggedInUser = current AppUser();

            //double balance = loggedInUser.getBalance;

            //System.out.println("Your balance is: " + balance);
            //todo implement navigation


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
