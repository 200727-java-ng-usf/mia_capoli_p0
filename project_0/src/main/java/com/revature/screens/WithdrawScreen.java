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
import static java.lang.Integer.parseInt;

public class WithdrawScreen extends Screen {
    private AccountService accountService;

    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

    public WithdrawScreen(AccountService accountService) {
        super("WithdrawFundsScreen", "/withdrawFunds");
        this.accountService = accountService;
    }

    @Override
    public void render() {

        try {
            Account currentAccount = app.getCurrentAccount();

            double temp;

            System.out.println(" How much would you like to withdraw? ");

            temp = Double.parseDouble(app.getConsole().readLine());
            accountService.withdrawFunds(temp);
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
