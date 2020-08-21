package com.revature.screens;

import com.revature.exceptions.AuthenticatorException;
import com.revature.models.AppUser;
import static com.revature.AppDriver.app;
import com.revature.repos.AppUserRepo;


public class SelectAccountScreen extends Screen{
    protected SelectAccountScreen() {
        super("SelectAccountScreen", "/selectAccount");
    }

    @Override
    public void render() {
        AppUser currentUser = app.getCurrentUser()
        currentUser = userRepo.findUser(username, password)
                .orElseThrow(AuthenticatorException::new);

        //get accounts associated w user
        //if there are none, error, go to add account
        //set active account to account name matching user input
        //go to dashboard
    }


}
