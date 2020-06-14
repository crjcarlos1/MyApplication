package com.cralos.myapplicationdb.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cralos.myapplicationdb.models.User;

import java.util.List;

@Dao
public interface UserDao {

    /**
     * obtener todos los usuarios
     */
    @Query("SELECT *FROM " + User.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT *FROM " + User.USER_TABLE + " WHERE name LIKE :myString OR apellidoPaterno LIKE :myString OR apellidoMaterno LIKE :myString")
    List<User> getUsersByNameOrLastName(String myString);

    /**
     * insertar usuarios
     */
    @Insert
    public void insertUsers(User... users);

    /**
     * eliminar usuario
     */
    @Delete
    public void deleteUser(User user);

}
