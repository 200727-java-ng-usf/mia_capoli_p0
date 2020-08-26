package com.revature.util;

import com.revature.screens.Screen;

import java.util.HashSet;
import java.util.Set;

/**
 * A class to add screens to and to navigate with in the application.
 */
public class ScreenRouter {

    private Set<Screen> screens = new HashSet<>();

    //get the screens that exist
    public Set<Screen> getScreens() {
        return screens;
    }

    //add the screens into the screens available
    public ScreenRouter addScreen(Screen screen) {
        screens.add(screen);
        return this;
    }

    //navigate through the screen router
    public void navigate(String route) {
        screens.stream()
                .filter(screen -> screen.getLocation().equals(route))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No screen found."))
                .render();
    }
}
