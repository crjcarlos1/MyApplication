package com.cralos.myapplicationdb.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cralos.myapplicationdb.models.Profile;
import com.cralos.myapplicationdb.models.User;
import com.cralos.myapplicationdb.room.RoomUserDataBase;

import java.util.List;

public class RepositoryUsers {

    private static RepositoryUsers instance = null;
    private MutableLiveData<List<User>> users;
    private MutableLiveData<String> message;

    public static RepositoryUsers getInstance() {
        if (instance == null) {
            instance = new RepositoryUsers();
        }
        return instance;
    }

    public void initRepository() {
        users = new MutableLiveData<>();
        message = new MutableLiveData<>();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void getUsersFromDataBase(String string, RoomUserDataBase dataBase) {
        List<User> usersRoom = dataBase.userDao().getUsersByNameOrLastName(string);
        if (usersRoom != null && usersRoom.size() > 0) {
            /**setProfile*/
            for (int i = 0; i < usersRoom.size(); i++) {
                usersRoom.get(i).setProfile(dataBase.profileDao().getProfileById(usersRoom.get(i).getIdUsuario()).getName());
                usersRoom.get(i).setApellidoMaterno(usersRoom.get(i).getApellidoMaterno() + "\n" + usersRoom.get(i).getProfile());
            }
            users.setValue(usersRoom);
        } else {
            message.setValue("No se encontraron datos");
            users.setValue(null);
        }
    }

    public void deleteUser(User user, RoomUserDataBase dataBase) {
        /**se borra al usuario de la su misma tabla*/
        dataBase.userDao().deleteUser(user);

        /**se borra perfil del usuario*/
        Profile profile = new Profile();
        profile.setIdProfile(user.getIdPerfil());
        profile.setName(user.getProfile());
        Profile[] profiles = {profile};
        dataBase.profileDao().deleteProfile(profiles);

        /**actualizamos lista*/
        updateUsersList(user);
    }

    private void updateUsersList(User user) {
        List<User> currentUsers = users.getValue();
        currentUsers.remove(user);
        users.setValue(currentUsers);
        currentUsers = null;
    }
}
