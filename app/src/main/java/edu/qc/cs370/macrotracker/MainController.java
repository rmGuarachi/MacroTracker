package edu.qc.cs370.macrotracker;

import android.arch.persistence.room.Room;

import edu.qc.cs370.macrotracker.db.FoodDatabase;
import edu.qc.cs370.macrotracker.db.User;
import edu.qc.cs370.macrotracker.macro.Profile;

public class MainController {
    //Class used to 'host' Profile object for app;

    static FoodDatabase db;
    static Profile profile;
    // TODO Create a constructor that will create a Profile object
    // TODO from a database table
    public MainController(FoodDatabase db) {
        profile = new Profile(2200, 343, 99, 65);

        this.db = db;
        //Grab information from DB
        if(db.userDao().getUsers().size() == 0) {
            // Create new user
            // TODO foodDatabase convert to use Profile class or
            // TODO change UI to use User class
            /*
            user.setName("User");
            foodDatabase.userDao().addUser(user);
             */
            profile = new Profile();
            // TODO map Profile object to User object..?
            //User user = new User(profile.getCalorie(), profile.getCarb(), profile.get)
        } else {
            //Retrieve saved information
            /*
            user = foodDatabase.userDao().getUsers().get(0);
             */
            User user = db.userDao().getUsers().get(0);
            profile = new Profile(user.getCalories(), user.getCarbs(), user.getProtein(), user.getFat());
        }
    }

    public static Profile getProfile() {
        return profile;
    }
}
