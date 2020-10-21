package me.dev.killerjore.main.gui;

import javax.swing.*;
import java.awt.event.*;

public class ErrorDialog extends JDialog {
    private JPanel contentPane;

    public ErrorDialog() {
        setContentPane(contentPane);
        setModal(true);
        setSize(300, 250);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
