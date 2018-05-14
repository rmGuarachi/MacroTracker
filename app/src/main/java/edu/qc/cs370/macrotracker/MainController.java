package edu.qc.cs370.macrotracker;

import edu.qc.cs370.macrotracker.macro.Profile;

public class MainController {
    //Class used to 'host' Profile object for app;

    static Profile profile;
    public MainController() {
        profile = new Profile();
    }
}
