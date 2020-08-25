package com.revature.services;

import com.revature.exceptions.AuthenticatorException;
import com.revature.exceptions.InvalidInputException;
import com.revature.models.AppUser;
import com.revature.models.Role;
import com.revature.repos.AppUserRepo;

import java.util.Optional;

import static com.revature.AppDriver.app;

public class UserService {

    private AppUserRepo userRepo;

    public UserService(AppUserRepo repo) {
        userRepo = repo;
    }

    public AppUser authenticate(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            app.invalidateCurrentUser();
            throw new InvalidInputException("Invalid user credentials given!");
        }
        Optional<AppUser> authUser = (userRepo.findUser(username, password));

        if (!authUser.isPresent()) {
            app.invalidateCurrentUser();
            throw new AuthenticatorException("No such user exists!");
        }

        app.setCurrentUser(authUser.get());

        return authUser.get();
    }

    public void registration(AppUser newUser) {
        if (!isUserValid(newUser)) {
            app.invalidateCurrentUser();
            throw new InvalidInputException("Invalid credentials given for registration.");
        }

        Optional<AppUser> _existingUser = userRepo.findUserByUsername(newUser.getUsername());
        if (_existingUser.isPresent()) {
            app.invalidateCurrentUser();
            throw new AuthenticatorException("Provided username is already in use!");
        }

        newUser.setRole(Role.BASIC_MEMBER);
        userRepo.save(newUser);
        app.setCurrentUser(newUser);
    }


    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }

}
