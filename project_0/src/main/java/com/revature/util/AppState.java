package com.revature.util;

import com.revature.models.Account;
import com.revature.models.AppUser;
import com.revature.repos.AccountRepo;
import com.revature.repos.AppUserRepo;
import com.revature.screens.*;
import com.revature.services.AccountService;
import com.revature.services.UserService;

import javax.xml.soap.SAAJMetaFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
    private BufferedReader console;
    private AppUser currentUser;
    private ScreenRouter router;
    private boolean appRunning;
    private Account currentAccount;


    public AppState() {

        appRunning = true;
        console = new BufferedReader(new InputStreamReader(System.in));

        final AppUserRepo userRepository = new AppUserRepo();
        final UserService userService = new UserService(userRepository);
        final AccountRepo accountRepo = new AccountRepo();
        final AccountService accountService = new AccountService(accountRepo);

        router = new ScreenRouter();
        router.addScreen(new HomeScreen())
              .addScreen(new RegisterScreen(userService))
              .addScreen(new LoginScreen(userService))
              .addScreen(new ViewBalanceScreen(userService))
              .addScreen(new WithdrawScreen(userService))
              .addScreen(new AddFundsScreen(userService))
              .addScreen(new AddAccountScreen(accountService))
              .addScreen(new Dashboard());
    }

    public BufferedReader getConsole() {
        return console;
    }

    public AppUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AppUser currentUser) {
        this.currentUser = currentUser;
    }

    public ScreenRouter getRouter() {
        return router;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public void invalidateCurrentUser() {
        currentUser = null;
    }

    public boolean isSessionValid() {
        return (this.currentUser != null);
    }
}
