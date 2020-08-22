package com.revature.screens;

import com.revature.exceptions.AuthenticatorException;
import com.revature.models.AppUser;
import static com.revature.AppDriver.app;
import com.revature.repos.AppUserRepo;
import com.revature.services.AccountService;


public class SelectAccountScreen extends Screen{

    private AccountService accountService;

    protected SelectAccountScreen(AccountService accountService) {
        super("SelectAccountScreen", "/selectAccount");
        this.accountService = accountService;
    }



    @Override
    public void render() {

    }


}
