package com.cralos.myapplicationdb.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Profile.PROFILE_TABLE)
public class Profile {
    public static final String PROFILE_TABLE = "profile";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idProfile")
    private int idProfile;

    @ColumnInfo(name = "name")
    private String name;

    public Profile() {
    }

    public Profile(String name) {
        this.name = name;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
