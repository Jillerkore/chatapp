package me.dev.killerjore.main.gui;

import me.dev.killerjore.networking.userStorage.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainApplicationGUI {

    private JFrame mainFrame;
    private JPanel contentPane;
    private JTextArea messageArea;
    private JTextArea connectedUsersArea;
    private JTextField messageEnterField;

    private Socket socket;
    private String name;

    public JFrame getJFrame() {
        return this.mainFrame;
    }

    public MainApplicationGUI(Socket socket, String name) {

        this.socket = socket;
        this.name = name;

        init();

    }

    private void init() {

        mainFrame = new JFrame("LocalChat");
        mainFrame.setSize(600, 500);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(contentPane);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);

        messageEnterField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(new Message("[" + name + "] " + messageEnterField.getText()));
                    oos.flush();
                    System.out.println("Message sent");
                    messageEnterField.setText("");
                    System.out.println("Field cleared");

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        });

    }

    public void appendMessage(String message) {
        messageArea.append("\n" + message);
    }

    public void appendOnlinePeople(String username) {
        connectedUsersArea.append("\n" +username);
    }

    public void emptyOnlinePeople() {
        connectedUsersArea.setText("");
    }


}
