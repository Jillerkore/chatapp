package me.dev.killerjore.networking.userStorage;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class ClientList implements Serializable {

    private ArrayList<Socket> socketList;

    public void addUser(Socket socket) {
        if (!socketList.contains(socket))
            socketList.add(socket);
    }

    public ArrayList<Socket> getSocketList() {
        return socketList;
    }

    private ClientList() {
        socketList = new ArrayList<>();
    }

    private static ClientList instance;

    public static ClientList getInstance() {
        if (instance == null)
            instance = new ClientList();
        return instance;
    }

}
