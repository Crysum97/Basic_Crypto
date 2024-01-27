package view;

import controller.EncryptPanelController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptPanel {
    private JButton backButton;
    private JPanel root;
    private JComboBox<String> methodComboBox;
    private JTextField keyTextField;
    private JButton openButton;
    private JButton encryptButton;
    private JButton nextButton;
    private JTextArea previewTextArea;
    private final EncryptPanelController controller;

    public EncryptPanel(ApplicationFrame parent) {
        this.controller = new EncryptPanelController(this);
        backButton.addActionListener(e -> parent.popLastPanel());
        openButton.addActionListener(e -> controller.onOpenFile());
    }

    public JPanel getRoot() {
        return this.root;
    }

    public JTextField getKeyPathTextField() {
        return this.keyTextField;
    }
}
