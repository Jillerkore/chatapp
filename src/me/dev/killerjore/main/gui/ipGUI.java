package me.dev.killerjore.main.gui;

import me.dev.killerjore.networking.client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ConnectException;

public class ipGUI {

    private String name;
    private String ip;
    private JTextField textField1;
    private JButton joinChatSessionButton;
    private JPanel contentPane;

    private JFrame frame;

    public ipGUI(String name) {
        this.name = name;
        init();
    }

    private void init() {

        frame = new JFrame();
        frame.setContentPane(contentPane);
        frame.setResizable(false);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        joinChatSessionButton.addActionListener(actionPerformed -> {
            executeEvent();
        });

        textField1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeEvent();
            }
        });
    }

    private void executeEvent() {

        try {
            new Client(ip, name);

            this.ip = textField1.getText();
            frame.dispose();

        }catch (Exception e) {
            e.printStackTrace();
           new ErrorDialog();
        }


    }

}
