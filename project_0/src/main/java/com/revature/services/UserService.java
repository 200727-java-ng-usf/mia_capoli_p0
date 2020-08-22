package com.revature.services;

import com.revature.exceptions.AuthenticatorException;
import com.revature.models.AppUser;
import com.revature.models.Role;
import com.revature.repos.AppUserRepo;

import java.util.Optional;

import static com.revature.AppDriver.app;

public class UserService {

    private AppUserRepo userRepo;

    public UserService(AppUserRepo repo) {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
        userRepo = repo;
//        userRepo = new UserRepository(); // tight coupling! ~hard~ impossible to unit test
    }

    public void authenticate(String username, String password) {

        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new AuthenticatorException("Invalid user credentials given!");
        }
        AppUser authUser = userRepo.findUser(username, password)
                .orElseThrow(AuthenticatorException::new);


        app.setCurrentUser(authUser);

    }

    public void registration(AppUser newUser) {
        if (!isUserValid(newUser)) {
            throw new RuntimeException("Invalid credentials given for registration.");
        }

        Optional<AppUser> _existingUser = userRepo.findUserByUsername(newUser.getUsername());

        if (_existingUser.isPresent()) {
            throw new RuntimeException("Provided username is already in use!");
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
