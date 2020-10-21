package me.dev.killerjore.networking.client;

import me.dev.killerjore.main.gui.ErrorDialog;
import me.dev.killerjore.main.gui.MainApplicationGUI;
import me.dev.killerjore.networking.userStorage.Message;
import me.dev.killerjore.networking.userStorage.UsernameList;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MessageReadThread implements Runnable{

    MainApplicationGUI instance;
    private boolean running;
    private Socket socket;
    private Thread thread;

    private Object obj;
    private Message message;
    private ObjectInputStream ois;

    public MessageReadThread(MainApplicationGUI instance, Socket socket, Thread thread) {
        this.instance = instance;
        this.socket = socket;
        this.thread = thread;
    }

    @Override
    public void run() {

        System.out.println("Thread running");
        running = true;
        while (running) {

            if (instance.getJFrame() == null) {
                running = false;
            }else {
                try {

                    ois = null;
                    obj = null;
                    message = null;

                    ois = new ObjectInputStream(socket.getInputStream());

                    obj = ois.readObject();

                    message = new Message("");
                    ArrayList<String> arrayList = new ArrayList<>();

                    if (obj.getClass() == message.getClass()) {
                        message = null;
                        message = (Message) obj;
                        instance.appendMessage(message.getText());
                    }else if (obj.getClass() == arrayList.getClass()) {
                        arrayList = null;
                        arrayList = (ArrayList<String>) obj;
                        instance.emptyOnlinePeople();
                        for (String s : arrayList) {
                            instance.appendOnlinePeople(s);
                        }

                        System.out.println(arrayList);


                    }

                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                    instance.getJFrame().dispose();
                    running = false;
                    new ErrorDialog();
                }

            }

        }

    }
}
