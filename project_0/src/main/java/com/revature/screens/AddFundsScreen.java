package com.revature.screens;

import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.NegativeException;
import com.revature.models.Account;
import com.revature.services.AccountService;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import static com.revature.AppDriver.app;


public class AddFundsScreen extends Screen{

    private AccountService accountService;
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);


    public AddFundsScreen(AccountService accountService) {
        super("AddFundsScreen", "/addFunds");
        this.accountService = accountService;
    }

    @Override
    public void render() {

        try {
            Account currentAccount = app.getCurrentAccount();

            double temp;

            System.out.println(" How much would you like to add? ");

            temp =  Double.parseDouble(app.getConsole().readLine());
            accountService.fundsUpdate(true, temp);
            System.out.println("Your balance is now: " + nf.format(currentAccount.getBalance()));

            app.getRouter().navigate("/Dashboard");


        } catch (NumberFormatException | IOException nfe) {
            System.err.println("Please enter a valid number!");
            app.getRouter().navigate("/addFunds");
        } catch (InvalidInputException iie) {
            System.err.println("Please enter a proper number.");
            app.getRouter().navigate("/addFunds");
        } catch (NegativeException nwe) {
            System.err.println("Please enter a positive, non-zero number!");
            app.getRouter().navigate("/addFunds");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("A problem occurred.");
            app.getRouter().navigate("/addFunds");
        }
    }


}
