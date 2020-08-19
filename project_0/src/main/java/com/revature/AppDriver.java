package com.revature;

import com.revature.screens.LoginScreen;
import com.revature.screens.RegisterScreen;
import com.revature.services.UserService;

public class AppDriver {

    public static void main(String[] args) {

        UserService userService = new UserService();
        RegisterScreen registerScreen = new RegisterScreen(userService);
        registerScreen.render();

        LoginScreen loginScreen = new LoginScreen(userService);
        loginScreen.render();
    }
}
