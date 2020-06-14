package com.cralos.myapplicationdb.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cralos.myapplicationdb.models.Profile;
import com.cralos.myapplicationdb.models.User;

@Database(entities = {User.class, Profile.class}, version = 1)
public abstract class RoomUserDataBase extends RoomDatabase {

    private static RoomDatabase instance;

    /**
     * add interfaces here
     */
    public abstract UserDao userDao();

    public abstract ProfileDao profileDao();


}
