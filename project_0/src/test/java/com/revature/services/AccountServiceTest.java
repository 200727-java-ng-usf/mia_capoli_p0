package com.revature.services;

import com.revature.exceptions.AuthenticatorException;
import com.revature.exceptions.InvalidInputException;
import com.revature.models.Account;

import com.revature.repos.AccountRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.AppDriver.app;

public class AccountServiceTest {

    private AccountRepo mockedRepo = Mockito.mock(AccountRepo.class);


    private AccountService accountService;
    Set<Account> mockAccounts = new HashSet<>();


    @Before
    public void setup() {
        accountService = new AccountService(mockedRepo);
        mockAccounts.add(new Account(12345, "AdamAccount", 10.00d));
        mockAccounts.add(new Account(22345, "MannyAccount", 0.00d));
        mockAccounts.add(new Account(32345, "AliceAccount", 0.00d));
        mockAccounts.add(new Account(42345, "BobAccount", 0.00d));
    }

    @After
    public void teardown() {
        accountService = null;
        mockAccounts.removeAll(mockAccounts);
    }

    @Test
    public void findUserByAccountID() {
        Account expectedAccount= new Account("MiaAccount", 92341);
        Mockito.when(mockedRepo.findUserByAccountId(92341))
                .thenReturn(java.util.Optional.of(expectedAccount));
        Account actualResult = accountService.findUserByAccountId(92341);
        Assert.assertEquals(expectedAccount, actualResult);
    }

    @Test(expected = InvalidInputException.class)
    public void authenticationWithInvalidCredentials() {

        accountService.findUserByAccountId(0);

    }

    @Test
    public void registrationSuccessful() {
        Account mockedCorrectAppUser = new Account("Mia", 123123);

        accountService.registration(mockedCorrectAppUser);

        Assert.assertNotNull(app.getCurrentAccount());
    }

    @Test(expected = InvalidInputException.class)
    public void registrationInvalidAccountInput() {
        accountService.registration(new Account("", 12340));
    }

    @Test(expected = AuthenticatorException.class)
    public void registrationAccountExists() {
        Account mockAccount = new Account(12345, "AdamAccount", 10.00d);

        Mockito.when(mockedRepo.findUserByAccountId(12345))
                .thenReturn(Optional.of(mockAccount));

        accountService.registration(new Account("AdamAccount", 12345));

    }

    @Test
    public void addFundsSuccessful() {
        Account mockAccount = new Account(12345, "AdamAccount", 10.00d);
        app.setCurrentAccount(mockAccount);
        double actualResult = accountService.addFunds(10);
        Assert.assertEquals(20, actualResult, .001);

    }

    @Test(expected = InvalidInputException.class)
    public void addFundsZero() {
        Account mockAccount = new Account(12345, "AdamAccount", 10.00d);
        app.setCurrentAccount(mockAccount);
        double actualResult = accountService.addFunds(0);

    }

    @Test
    public void withdrawFundsSuccessful() {
        Account mockAccount = new Account(12345, "AdamAccount", 10.00d);
        app.setCurrentAccount(mockAccount);
        double actualResult = accountService.withdrawFunds(10);
        Assert.assertEquals(0, actualResult, .001);

    }

    @Test(expected = InvalidInputException.class)
    public void withdrawFundsZero() {
        Account mockAccount = new Account(12345, "AdamAccount", 10.00d);
        app.setCurrentAccount(mockAccount);
        accountService.addFunds(0);

    }


}
