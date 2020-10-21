package me.dev.killerjore.networking.server;

import me.dev.killerjore.networking.userStorage.ClientList;
import me.dev.killerjore.networking.userStorage.Message;
import me.dev.killerjore.networking.userStorage.UsernameList;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    private Socket socket;
    private final String clientName;
    private boolean sessionStarted = false;
    private ClientList clientList;
    private UsernameList usernameList;

    private Thread userlistThread;

    public ClientHandler(Socket socket, ClientList clientList, UsernameList usernameList, String clientName) {

        this.socket = socket;
        this.clientList = clientList;
        this.clientName = clientName;
        this.usernameList = usernameList;

    }

    @Override
    public void run() {

        sessionStarted = true;
        System.out.println("Session started.");

        clientList.getSocketList().forEach(s -> {
            try {

                ObjectOutputStream oos;
                oos = new ObjectOutputStream(s.getOutputStream());
                oos.writeObject(new Message(clientName + " has joined the chat session."));

                ObjectOutputStream ooss = new ObjectOutputStream(s.getOutputStream());

                ooss.writeObject(usernameList.getUsernameList());
                System.out.println("Sent the object!!! YAYYYYYYY ITS THE CLIENT'S FAULT OMG");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        while (sessionStarted) {

            try {

                InputStream os = socket.getInputStream();

                ObjectInputStream ois = new ObjectInputStream(os);
                Message message = (Message) ois.readObject();

                clientList.getSocketList().forEach(s -> {

                    try {
                        ObjectOutputStream oos;
                        oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeObject(message);
                    } catch (IOException e) {
                    }

                });


            } catch (IOException | ClassNotFoundException e) {
                sessionStarted = false;

                clientList.getSocketList().remove(socket);
                usernameList.getUsernameList().remove(clientName);

                clientList.getSocketList().forEach(s -> {
                    try {
                        ObjectOutputStream oos;
                        oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeObject(new Message(clientName + " has left the chat session."));

                        ObjectOutputStream ooss = new ObjectOutputStream(s.getOutputStream());

                        ooss.writeObject(usernameList.getUsernameList());

                    } catch (IOException ignored) {

                    }
                });
            }

        }

    }

}
