package com.revature.services;

import com.revature.exceptions.AuthenticatorException;
import com.revature.exceptions.InvalidInputException;
import com.revature.models.AppUser;
import com.revature.models.Role;
import com.revature.repos.AppUserRepo;
import com.revature.services.UserService;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.mockito.*;

import static com.revature.AppDriver.app;


public class UserServiceTest {

    private AppUserRepo mockedRepo = Mockito.mock(AppUserRepo.class);


    private UserService userService;
    Set<AppUser> mockUsers = new HashSet<>();


    @Before
    public void setup() {
        userService = new UserService(mockedRepo);
        mockUsers.add(new AppUser(1, "Adam", "Inn", "admin", "secret", Role.ADMIN ));
        mockUsers.add(new AppUser(2, "Manny", "Gerr", "mann@", "manager", Role.MANAGER ));
        mockUsers.add(new AppUser(3, "Alice", "Anderson", "aandserson", "password", Role.BASIC_MEMBER ));
        mockUsers.add(new AppUser(4, "Bob", "Bailey", "bbailey", "dev", Role.PREMIUM_MEMBER ));
    }

    @After
    public void teardown() {
        userService= null;
        mockUsers.removeAll(mockUsers);
    }

    @Test
    public void authenticationWithValidCredentials() {
        AppUser expectedUser = new AppUser(1, "Adam", "Inn", "admin", "secret", Role.ADMIN);
        Mockito.when(mockedRepo.findUser("admin", "secret"))
                .thenReturn(java.util.Optional.of(expectedUser));
       AppUser actualResult = userService.authenticate("admin", "secret");
        Assert.assertEquals(expectedUser, actualResult);
    }

    @Test(expected = InvalidInputException.class)
    public void authenticationWithInvalidCredentials() {
        //no arrange

        // Act
        userService.authenticate("","");

        //no assert, should raise an exception
    }

    @Test(expected = AuthenticatorException.class)
    public void authenticationWithUnknownCredentials() {
        userService.authenticate("garbage", "user");
    }

    @Test
    public void isUserValidValidser() {
        AppUser mockedCorrectAppUser = new AppUser("Mia","Capoli", "cp", "nerd");
        boolean expectedResult = true;

        boolean actualResult = userService.isUserValid(mockedCorrectAppUser);

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test
    public void isUserValidValidserFaulty() {
        AppUser mockedFaultyAppUser = new AppUser("Mia","", "cp", "nerd");
        boolean expectedResult = false;

        boolean actualResult = userService.isUserValid(mockedFaultyAppUser);

        Assert.assertEquals(actualResult, expectedResult);

    }
    @Test(expected = InvalidInputException.class)
    public void registrationFaulty() {
        AppUser mockedFaultyAppUser = new AppUser("Mia","", "cp", "nerd");
        userService.registration(mockedFaultyAppUser);

    }
    @Test(expected = AuthenticatorException.class)
    public void registrationUserExists() {
        AppUser mockedFaultyAppUser = new AppUser(1, "Adam", "Inn", "admin", "secret", Role.ADMIN );

        Mockito.when(mockedRepo.findUserByUsername("admin"))
                .thenReturn(Optional.of(mockedFaultyAppUser));

        userService.registration(mockedFaultyAppUser);
    }

    @Test
    public void registrationUserSuccess() {
        AppUser mockedCorrectAppUser = new AppUser("Mia","Capoli", "cp", "nerd");

        userService.registration(mockedCorrectAppUser);

        Assert.assertNotNull(app.getCurrentUser());
    }



}

