package me.dev.killerjore.networking.server;

import jdk.internal.util.xml.impl.Input;
import me.dev.killerjore.networking.userStorage.ClientList;
import me.dev.killerjore.networking.userStorage.UsernameList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static boolean running;
    private ServerSocket serverSocket;
    private Socket socket;
    private ClientList clientList;
    private transient UsernameList usernameList;

    public Server() {

        init();
        System.out.println("Server's up and running.");

        running = true;

        while (running) {

            socket = null;
            try {

                System.out.println("Accepting clients.");
                socket = serverSocket.accept();
                System.out.println("Client accepted");

                BufferedReader nameInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = nameInput.readLine();
                System.out.println("Obtained name: " + name);

                clientList.addUser(socket);
                usernameList.addUser(name);

                ClientHandler clientHandler = new ClientHandler(socket, clientList, usernameList, name);



                Thread thread = new Thread(clientHandler);
                thread.start();

            }catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void init() {
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        clientList = ClientList.getInstance();
        usernameList = UsernameList.getInstance();
    }

}
