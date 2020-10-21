package me.dev.killerjore.networking.userStorage;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class UsernameList implements Serializable {

    private ArrayList<String> userList;

    public void addUser(String username) {
        if (!userList.contains(username))
            userList.add(username);
    }

    public ArrayList<String> getUsernameList() {
        return userList;
    }

    private UsernameList() {
        userList = new ArrayList<>();
    }

    private static UsernameList instance;

    public static UsernameList getInstance() {
        if (instance == null)
            instance = new UsernameList();
        return instance;
    }

}
