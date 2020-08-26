package com.revature.screens;

public abstract class Screen {
    //Screen Abstract Class with constructor and render abstract method
    private String name;
    private String location;

    protected Screen(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public abstract void render();

}
