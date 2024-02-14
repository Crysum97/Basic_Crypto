package view;

import controller.EncryptPanelController;
import javax.swing.*;


public class EncryptPanel {
    /** reference to the back button */
    private JButton backButton;
    /** reference to the root panel */
    private JPanel root;
    /** reference to the combobox for method selection */
    private JComboBox<String> methodComboBox;
    /** reference to the key text field */
    private JTextField keyTextField;
    /** reference to the open button */
    private JButton openButton;
    /** reference to the encrypt button */
    private JButton encryptButton;
    /** reference to the next button */
    private JButton nextButton;
    /** reference to the preview text area */
    private JTextArea previewTextArea;
    /** reference to the save button */
    private JButton saveToFileButton;
    /** reference to the controller */
    private final EncryptPanelController controller;


    /**
     * default constructor
     * @param parent reference to the {@link ApplicationFrame} frame
     */
    public EncryptPanel(ApplicationFrame parent) {
        this.controller = new EncryptPanelController(this, parent);
        backButton.addActionListener(e -> parent.popLastPanel());
        openButton.addActionListener(e -> controller.onOpenFile());
        methodComboBox.addItemListener(controller::onItemChanged);
        encryptButton.addActionListener(e -> controller.onEncrypt());
        saveToFileButton.addActionListener(e -> controller.onSaveFile());
        nextButton.addActionListener(e -> parent.callInjectPanel());
    }

    /**
     * Getter for the root panel
     * @return reference to the root {@link JPanel}
     */
    public JPanel getRoot() {
        return this.root;
    }

    /**
     * Getter for the key text field
     * @return reference to the key {@link JTextField}
     */
    public JTextField getKeyPathTextField() {
        return this.keyTextField;
    }

    /**
     * Getter for the method combobox
     * @return reference to the method {@link JComboBox<String>}
     */
    public JComboBox<String> getMethodComboBox() {
        return this.methodComboBox;
    }

    /**
     * Getter for the preview text area
     * @return reference to the preview {@link JTextArea}
     */
    public JTextArea getPreviewTextArea() {
        return this.previewTextArea;
    }

    /**
     * Getter for the open button
     * @return reference to the open {@link JButton}
     */
    public JButton getOpenButton() {
        return this.openButton;
    }

    /**
     * Getter for the next button
     * @return reference to the next {@link JButton}
     */
    public JButton getNextButton() {
        return this.nextButton;
    }
}
