package view;

import controller.EncryptPanelController;
import javax.swing.*;


public class EncryptPanel {
    private JButton backButton;
    private JPanel root;
    private JComboBox<String> methodComboBox;
    private JTextField keyTextField;
    private JButton openButton;
    private JButton encryptButton;
    private JButton nextButton;
    private JTextArea previewTextArea;
    private JButton saveToFileButton;
    private final EncryptPanelController controller;

    public EncryptPanel(ApplicationFrame parent) {
        this.controller = new EncryptPanelController(this, parent);
        backButton.addActionListener(e -> parent.popLastPanel());
        openButton.addActionListener(e -> controller.onOpenFile());
        methodComboBox.addItemListener(controller::onItemChanged);
        encryptButton.addActionListener(e -> controller.onEncrypt());
        saveToFileButton.addActionListener(e -> controller.onSaveFile());
        nextButton.addActionListener(e -> parent.callInjectPanel());
    }

    public JPanel getRoot() {
        return this.root;
    }

    public JTextField getKeyPathTextField() {
        return this.keyTextField;
    }

    public JComboBox<String> getMethodComboBox() {
        return this.methodComboBox;
    }

    public JTextArea getPreviewTextArea() {
        return this.previewTextArea;
    }

    public JButton getOpenButton() {
        return this.openButton;
    }
}
