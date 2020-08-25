package com.revature.screens;

import com.revature.exceptions.AuthenticatorException;
import com.revature.exceptions.InvalidInputException;
import com.revature.models.AppUser;
import com.revature.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;

import static com.revature.AppDriver.app;

public class RegisterScreen extends Screen {

    private UserService userService;

    public RegisterScreen(UserService userService) {
        super("RegisterScreen", "/register");
        this.userService = userService;
    }


    @Override
    public void render() {
        String firstName;
        String lastName;
        String username;
        String password;

        try {
            System.out.println("Sign up for an account with the Bank of Capoli!");
            System.out.println("Please enter your First name: ");
            firstName = app.getConsole().readLine();
            System.out.println("Please enter your Last name: ");
            lastName = app.getConsole().readLine();
            System.out.println("Please enter a username: ");
            username = app.getConsole().readLine();
            System.out.println("Please enter a password: ");
            password = app.getConsole().readLine();

            AppUser newUser = new AppUser(username, password, firstName, lastName);
            userService.registration(newUser);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/addAccount");
            }
        } catch (AuthenticatorException ae) {
            System.err.println("Provided username is already in use!");
            app.getRouter().navigate("/register");
        } catch (InvalidInputException ie) {
            System.err.println("Invalid credentials given for registration.");
            app.getRouter().navigate("/register");
        } catch (IOException ioe) {
            System.err.println("Invalid credentials given!");
            app.getRouter().navigate("/register");
        } catch (Exception e) {
            System.err.println("A problem occurred.");
            app.getRouter().navigate("/addAccount");
        }

    }
}
