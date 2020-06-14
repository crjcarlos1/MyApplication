package com.cralos.myapplicationdb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.myapplicationdb.models.User;
import com.cralos.myapplicationdb.repository.RepositoryUsers;
import com.cralos.myapplicationdb.room.RoomUserDataBase;

import java.util.List;

public class MainViewModel extends ViewModel {

    private RepositoryUsers repository;

    public LiveData<List<User>> getUsers() {
        return repository.getUsers();
    }

    public LiveData<String> getMessage() {
        return repository.getMessage();
    }

    public void initViewModel() {
        repository = RepositoryUsers.getInstance();
        repository.initRepository();
    }

    public void getUsersFromRepository(String string, RoomUserDataBase dataBase) {
        repository.getUsersFromDataBase(string, dataBase);
    }


    public void deleteUser(User user, RoomUserDataBase dataBase) {
        repository.deleteUser(user, dataBase);
    }
}
