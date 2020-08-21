package com.revature.screens;

import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AddFundsScreen extends Screen{

    private UserService userService;

    public AddFundsScreen(UserService userService) {
        super("AddFundsScreen", "/addFunds");
        this.userService = userService;
    }

    @Override
    public void render() {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            //TODO get proper logged in user
            //AppUser loggedInUser = current AppUser();

            //double balance = loggedInUser.getAccount.getBalance;

            //how much would you like to add?
            //get add balance check if valid

            //System.out.println("Your balance is now: " + balance);
            //to do implement transaction add
            //TODO implement navigation



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
