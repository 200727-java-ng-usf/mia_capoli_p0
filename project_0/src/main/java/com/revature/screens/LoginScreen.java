package com.revature.screens;

import com.revature.exceptions.AuthenticatorException;
import com.revature.exceptions.InvalidInputException;
import com.revature.services.UserService;

import javax.imageio.IIOException;
import javax.security.sasl.AuthenticationException;

import java.io.IOException;

import static com.revature.AppDriver.app;

public class LoginScreen extends Screen {

    private UserService userService;

    public LoginScreen(UserService userService) {

        super("LoginScreen", "/login");
        this.userService = userService;
    }

    @Override
    public void render() {
        String username, password;

        try {
            System.out.println("Please provide your login credentials");
            System.out.print("Username: ");
            username = app.getConsole().readLine();
            System.out.print("Password: ");
            password = app.getConsole().readLine();

            userService.authenticate(username, password);

            if (app.isSessionValid()) {
                app.getRouter().navigate("/selectAccount");
            }

        } catch (AuthenticatorException ae) {
            System.err.println("Provided user does not exist!");
            app.getRouter().navigate("/login");
        } catch (InvalidInputException ie) {
            System.err.println("Invalid user credentials given!");
            app.getRouter().navigate("/login");
        } catch (IOException ioe) {
            System.err.println("Invalid user credentials given!");
            app.getRouter().navigate("/login");
        } catch (Exception e) {
            System.err.println("A problem occurred.");
            app.getRouter().navigate("/login");
        }
    }

}
