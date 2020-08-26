package com.revature.screens;


import com.revature.exceptions.AuthenticatorException;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.NegativeException;
import com.revature.models.Account;
import com.revature.services.AccountService;
import java.io.IOException;

import static com.revature.AppDriver.app;

/**
 * Render the Add Account Screen and add the entered account to the repo.
 */
public class AddAccountScreen extends Screen {

    private AccountService accountService;

    public
    AddAccountScreen(AccountService accountService) {
        super("AddAccountScreen", "/addAccount");
        this.accountService = accountService;
    }

    /**
     *  Render the Add Account Screen.
     */
    @Override
    public void render() {
        int accountId;
        String accountName;
        try {
            System.out.println("Add a bank account!");
            System.out.println("Please up to 9 digits for your desired Account Id: ");
            accountId = Integer.parseInt(app.getConsole().readLine());
            System.out.println("Please enter the account name: ");
            accountName = app.getConsole().readLine();

            //create an account from user inputs and register it on the service layer
            Account newAccount = new Account(accountName, accountId);
            accountService.registration(newAccount);
            //navigate to the next logical screen
            if (app.isSessionValid()) {
                app.getRouter().navigate("/selectAccount");
            }

        } catch (NumberFormatException | NegativeException nfe) {
            System.err.println("Please enter a account number that is also less than 9 digits!");
            app.getRouter().navigate("/addAccount");
        } catch (IOException | InvalidInputException ioe) {
            System.err.println("Please enter a a non-zero, positive account number!");
            app.getRouter().navigate("/addAccount");
        } catch (AuthenticatorException ae) {
            System.err.println("Provided account id is already in use!");
            app.getRouter().navigate("/addAccount");
        } catch (Exception e) {
            System.err.println("A problem occurred.");
            app.getRouter().navigate("/addAccount");
        }

    }
}
