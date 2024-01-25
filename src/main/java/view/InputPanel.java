package view;

import controller.InputPanelController;

import javax.swing.*;

public class InputPanel {
    /** reference to the open button */
    private JButton openBtn;
    /** reference to the path text field */
    private JTextField pathTextField;
    /** reference to the root panel */
    private JPanel root;

    /**
     * default constructor
     */
    public InputPanel() {
        // register listener for the open button
        openBtn.addActionListener(event -> {
            InputPanelController.onOpenButtonClicked();
            pathTextField.setText(InputPanelController.getSelectedFilePath());
        });
    }

    /**
     * Simple getter for the root panel
     * @return reference to the root panel
     */
    public JPanel getRoot() {
        return this.root;
    }
}
