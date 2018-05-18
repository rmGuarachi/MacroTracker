package edu.qc.cs370.macrotracker.macrotracker.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.qc.cs370.macrotracker.db.User;

@Dao
public interface UserDao {

    @Insert
    public void addUser(edu.qc.cs370.macrotracker.macrotracker.db.User user);

    @Query("SELECT * FROM user")
    public List<edu.qc.cs370.macrotracker.macrotracker.db.User> getUsers();

    @Delete
    public void deleteUser(edu.qc.cs370.macrotracker.macrotracker.db.User User);

    @Update
    public void updateUser(edu.qc.cs370.macrotracker.macrotracker.db.User user);
}
