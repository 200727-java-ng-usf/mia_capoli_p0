package com.revature.screens;

import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WithdrawScreen extends Screen {
    private UserService userService;

    public WithdrawScreen(UserService userService) {
        super("WithdrawFundsScreen", "/withdrawFunds");
        this.userService = userService;
    }

    @Override
    public void render() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            //TODO get proper logged in user
            //AppUser loggedInUser = current AppUser();

            //double balance = loggedInUser.getBalance;

            //how much would you like to withdraw?
            //get withdraw balance check if valid

            //System.out.println("Your balance is now: " + balance);
            //TODO implement navigation


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
