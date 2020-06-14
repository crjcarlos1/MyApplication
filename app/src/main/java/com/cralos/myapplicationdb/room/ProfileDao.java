package com.cralos.myapplicationdb.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cralos.myapplicationdb.models.Profile;

import java.util.List;

@Dao
public interface ProfileDao {

    /**
     * insert profiles
     */
    @Insert
    public void insertProfiles(Profile... profiles);

    /**
     * getAllProfileS
     */
    @Query("SELECT *FROM " + Profile.PROFILE_TABLE)
    List<Profile> getAllProfiles();

    /**
     * get profile by id
     */
    @Query("SELECT *FROM " + Profile.PROFILE_TABLE + " WHERE idProfile = :id")
    Profile getProfileById(int id);

    /**
     * delete profile
     */
    @Delete
    void deleteProfile(Profile... profiles);

}
