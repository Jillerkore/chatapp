package me.dev.killerjore.main.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InitialGUI {

    private JPanel contentPane;
    private JTextField nameField;
    private JButton joinButton;

    private JFrame frame;

    private String name;

    public InitialGUI() {

        init();

    }

    private void init() {

        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        joinButton.addActionListener(actionPerformed -> {
            eventExecution();
        });

        nameField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventExecution();
            }
        });
    }

    private void eventExecution() {

        this.name = nameField.getText();

        new ipGUI(name);
        frame.dispose();

    }

}
