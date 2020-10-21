package me.dev.killerjore.networking.client;

import me.dev.killerjore.main.gui.ErrorDialog;
import me.dev.killerjore.main.gui.MainApplicationGUI;
import me.dev.killerjore.networking.server.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class Client {

    private Socket socket;
    private Thread thread;

    public Client(String internetProtocolAddress, String name) {

        socket = null;

        try {
            socket = new Socket(internetProtocolAddress, 9999);

            PrintWriter sentName = new PrintWriter(socket.getOutputStream(), true);
            sentName.println(name);

            MainApplicationGUI gui = new MainApplicationGUI(socket, name);

            MessageReadThread mrt = new MessageReadThread(gui, socket, thread);

            thread = new Thread(mrt);
            thread.start();
        }catch (Exception e) {
            new ErrorDialog();
        }


    }

}
