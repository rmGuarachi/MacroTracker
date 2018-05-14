package edu.qc.cs370.macrotracker.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Query("SELECT * FROM user")
    public List<User> getUsers();

    @Delete
    public void deleteUser(User User);

    @Update
    public void updateUser(User user);
}
