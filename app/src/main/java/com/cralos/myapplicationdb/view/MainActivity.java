package com.cralos.myapplicationdb.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.cralos.myapplicationdb.R;
import com.cralos.myapplicationdb.adapters.OnClickUser;
import com.cralos.myapplicationdb.adapters.RecyclerViewAdapter;
import com.cralos.myapplicationdb.databinding.ActivityMainBinding;
import com.cralos.myapplicationdb.interfaces.OnClickSearchUser;
import com.cralos.myapplicationdb.models.Profile;
import com.cralos.myapplicationdb.models.User;
import com.cralos.myapplicationdb.room.RoomUserDataBase;
import com.cralos.myapplicationdb.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickSearchUser, OnClickUser {
    private static final String TAG = "MainActivity";
    private RoomUserDataBase dataBase;
    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
        initRecyclerView();
        setupUsersObserver();
        setupMessageObserver();
        getUsersByName();
    }

    @Override
    public void onClickSearchQuery() {
        String name = binding.edtName.getText().toString();
        if (name != null && name.length() > 0) {
            viewModel.getUsersFromRepository(binding.edtName.getText().toString(), dataBase);
            return;
        }
        String lastName = binding.edtLastName.getText().toString();
        if (lastName != null && lastName.length() > 0) {
            viewModel.getUsersFromRepository(binding.edtLastName.getText().toString(), dataBase);
            return;
        }
        Toast.makeText(getApplicationContext(), "Ingresa nombre o apellido", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickDeleteUser(User user) {
        viewModel.deleteUser(user, dataBase);
    }

    private void insertProfiles() {
        Profile[] array = {new Profile("perfil 1"),
                new Profile("perfil 2"), new Profile("perfil 3"), new Profile("perfil 4"),
                new Profile("perfil 5"), new Profile("perfil 6"), new Profile("perfil 7"),
                new Profile("perfil 8"), new Profile("perfil 9"), new Profile("perfil 10"),
                new Profile("perfil 11"), new Profile("perfil 12"), new Profile("perfil 13"),
                new Profile("perfil 14"), new Profile("perfil 15"), new Profile("perfil 16"),
                new Profile("perfil 17"), new Profile("perfil 18"), new Profile("perfil 19"), new Profile("perfil 20"),
        };
        dataBase.profileDao().insertProfiles(array);
    }

    private void insertUsers() {
        User[] users = {new User(1, "Alicia", "Perez", "Perez"), new User(2, "Carlos", "Contreras", "Ramirez"),
                new User(3, "Carlos", "Corona", "Gutierrez"), new User(4, "David", "Solis", "Guardado"),
                new User(5, "Ivan", "Solis", "Guardado"), new User(6, "Oscar", "Cruz", "Ramirez"),
                new User(7, "Erandy", "Galvan", "Galvan"), new User(8, "Ofelia", "Soto", "Telles"),
                new User(9, "Fabiola", "Mondragon", "Cruz"), new User(10, "Gabriela", "Soto", "Montemayor"),
                new User(11, "Graciela", "Ramirez", "Garcia"), new User(12, "Maricela", "Ramirez", "Ramirez"),
                new User(13, "Maria", "Ramirez", "Salazar"), new User(14, "Jorge", "Contreras", "Garcia"),
                new User(15, "Juan", "Avellano", "Dominguez"), new User(16, "Jose", "Alcantara", "Lopez"),
                new User(17, "Luis", "Contreras", "Lopez"), new User(18, "Fernanda", "Contreras", "Lopez"),
                new User(19, "Karen", "Contreras", "Ramirez"), new User(20, "Nancy", "Contreras", "Morales"),};
        dataBase.userDao().insertUsers(users);
    }

    private void initActivity() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setOnClick(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.initViewModel();
        dataBase = Room.databaseBuilder(getApplicationContext(), RoomUserDataBase.class, "users.sqlite").allowMainThreadQueries().build();
        showProfiles();
        showUsers();
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter(getApplicationContext());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerView.setAdapter(adapter);
        adapter.setUsers(viewModel.getUsers().getValue());
        adapter.setOnClickUser(this);
    }

    private void setupUsersObserver() {
        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsers(users);
            }
        });
    }

    private void setupMessageObserver() {
        viewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProfiles() {
        List<Profile> profiles = dataBase.profileDao().getAllProfiles();
        if (profiles != null && profiles.size() > 0) {
            for (Profile profile : profiles) {
                Log.e(TAG, "profile:  " + profile.getIdProfile() + " " + profile.getName());
            }
        } else {
            Log.e(TAG, "sin perfiles de usuario");
        }
    }

    private void showUsers() {
        List<User> users = dataBase.userDao().getAllUsers();
        if (users != null && users.size() > 0) {
            for (User user : users) {
                Log.e(TAG, "user: " + user.getName() + " " + user.getApellidoPaterno());
            }
        } else {
            Log.e(TAG, "No hay usuarios");
        }
    }

    private void getUsersByName() {
        List<User> s = dataBase.userDao().getUsersByNameOrLastName("Contreras");
        for (User user : s) {
            Log.e(TAG, "name: " + user.getName() + ", apellido: " + user.getApellidoPaterno() + " " + user.getApellidoMaterno());
        }
    }

}