package edu.qc.cs370.macrotracker;

import edu.qc.cs370.macrotracker.macro.Profile;

public class MainController {
    //Class used to 'host' Profile object for app;

    static Profile profile;
    // TODO Create a constructor that will create a Profile object
    // TODO from a database table
    public MainController() {
        profile = new Profile();
    }

    public static Profile getProfile() {
        return profile;
    }
}
