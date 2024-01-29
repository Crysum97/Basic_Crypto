package view;

import controller.EncryptPanelController;
import iterative.Matrix;
import iterative.Vigerene;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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
        this.controller = new EncryptPanelController(this);
        backButton.addActionListener(e -> parent.popLastPanel());
        openButton.addActionListener(e -> controller.onOpenFile());
        encryptButton.addActionListener(e -> {
            String input = parent.input.getContentContainer().getText();
            String keyField = keyTextField.getText();
            switch (Objects.requireNonNull(methodComboBox.getSelectedItem()).toString()) {
                case "Caesar 1" -> {
                    try {
                        controller.encryptCaesar(new functional.Caesar(), input, Integer.parseInt(keyField));
                    } catch (NumberFormatException ignore) {
                        controller.handleInvalidInput(keyField);
                    }
                }
                case "Caesar 2" -> {
                    try {
                        controller.encryptCaesar(new iterative.Caesar(), input, Integer.parseInt(keyField));
                    } catch (NumberFormatException ignore) {
                        controller.handleInvalidInput(keyField);
                    }
                }
                case "Matrix" -> controller.encryptMatrix(new Matrix(), input);
                case "Vigerene" -> controller.encryptVigerene(new Vigerene(), input, keyField);
                default -> System.out.println("Shouldn't be here...");
            }
        });
        saveToFileButton.addActionListener(e -> controller.onSaveFile());
    }

    public JPanel getRoot() {
        return this.root;
    }

    public JTextField getKeyPathTextField() {
        return this.keyTextField;
    }

    public JTextArea getPreviewTextArea() {
        return this.previewTextArea;
    }
}
