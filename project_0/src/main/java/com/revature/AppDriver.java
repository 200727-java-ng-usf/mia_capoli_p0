package com.revature;


import com.revature.util.AppState;

public class AppDriver {

    public static AppState app = new AppState();

    public static void main(String[] args) {

        //if the app is still running, go to home. otherwise, finish the application.
        while (app.isAppRunning()) {
            app.getRouter().navigate("/home");
        }
    }
}
