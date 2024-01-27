package view;

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

    public EncryptPanel(ApplicationFrame parent) {
        backButton.addActionListener(e -> {
            parent.popLastPanel();
        });
    }

    public JPanel getRoot() {
        return this.root;
    }
}
