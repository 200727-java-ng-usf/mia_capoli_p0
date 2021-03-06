package com.revature.screens;

import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.NegativeException;
import com.revature.exceptions.OverdraftException;
import com.revature.models.Account;
import com.revature.services.AccountService;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import static com.revature.AppDriver.app;


/**
 * Render the Withdraw Screen and withdraw money from the account.
 */
public class WithdrawScreen extends Screen {
    private AccountService accountService;

    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

    public WithdrawScreen(AccountService accountService) {
        super("WithdrawFundsScreen", "/withdrawFunds");
        this.accountService = accountService;
    }

    /**
     * Render the Withdraw Screen.
     */
    @Override
    public void render() {

        try {
            Account currentAccount = app.getCurrentAccount();

            double temp;

            System.out.println(" How much would you like to withdraw? ");
            temp = Double.parseDouble(app.getConsole().readLine());

            //add funds on the service layer
            accountService.fundsUpdate(false, temp);

            //print out the new balance and navigate back to the dashboard
            System.out.println("Your balance is now: " + nf.format(currentAccount.getBalance()));
            app.getRouter().navigate("/Dashboard");

        } catch (NumberFormatException | IOException | InvalidInputException nfe) {
            System.err.println("Please enter a valid number!");
            app.getRouter().navigate("/withdrawFunds");
        } catch (OverdraftException iie) {
            System.err.println("This account does not support overdrafting.");
            app.getRouter().navigate("/Dashboard");
        } catch (NegativeException nwe) {
            System.err.println("Please enter a positive, non-zero number!");
            app.getRouter().navigate("/withdrawFunds");
        } catch (Exception e) {
            System.err.println("A problem occurred.");
            app.getRouter().navigate("/withdrawFunds");
        }
    }
}
