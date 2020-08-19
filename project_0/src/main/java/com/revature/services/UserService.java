package com.revature.services;

import com.revature.exceptions.AuthenticatorException;
import com.revature.models.AppUser;

public class UserService {

    public AppUser authenticate(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new AuthenticatorException("Invalid user credentials given!");
        }
        AppUser authenticatedUser = new AppUser(username, password);
        //TODO implement AWS / SQL user repo


        if (authenticatedUser == null) {
            throw new AuthenticatorException("No user exists with the provided credentials!");
        }
        return authenticatedUser;

    }

    public AppUser registration(AppUser newUser) {
        if (!isUserValid(newUser)) {
            throw new RuntimeException("Invalid credentials given for registration.");
        }

        //Todo implement a check in the user repo to see if the username already exists.

        //Todo implement a method to save the user to the repo

//        if (newUser.getAccount.getBalance().equals(0.00)) {
//            newUser.setBalance(0.00);
//        } else {
//            //todo implement check for user's initial balance
//            newUser.getAccount.setBalance(0.00);
//        }

        return newUser;
    }


    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }

}
