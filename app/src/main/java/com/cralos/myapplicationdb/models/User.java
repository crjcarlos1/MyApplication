package com.cralos.myapplicationdb.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = User.USER_TABLE)
public class User {

    public static final String USER_TABLE="tUser";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUsuario")
    private int idUsuario;

    @ColumnInfo(name = "idPerfil")
    private int idPerfil;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "apellidoPaterno")
    private String apellidoPaterno;

    @ColumnInfo(name = "apellidoMaterno")
    private String apellidoMaterno;

    @Ignore
    private String profile;

    public User() {
    }

    public User(int idPerfil, String name, String apellidoPaterno, String apellidoMaterno) {
        this.idPerfil = idPerfil;
        this.name = name;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
