package view;

import controller.InputPanelController;

import javax.swing.*;

public class InputPanel {
    /** reference to the open button */
    private JButton open;
    /** reference to the path text field */
    private JTextField pathTextField;
    /** reference to the root panel */
    private JPanel root;
    /** reference to the text area which yields the file content */
    private JTextArea fileContent;
    private JButton nextButton;
    /** reference to this classes controller */
    private final InputPanelController controller;
    /**
     * default constructor
     */
    public InputPanel(ApplicationFrame parent) {
        this.controller = new InputPanelController(this);

        // register listener for the open button
        this.open.addActionListener(event -> controller.onOpenButtonClicked());
        this.nextButton.addActionListener(e -> controller.onNext(parent));
    }

    /**
     * simple getter for the root panel
     * @return reference to the root panel
     */
    public JPanel getRoot() {
        return this.root;
    }

    /**
     * simple getter for the path text field
     * @return reference to the path text field
     */
    public JTextField getPathTextField() {
        return this.pathTextField;
    }

    /**
     * simple getter for the text area that yields the file content
     * @return reference to the text area
     */
    public JTextArea getContentContainer(){
        return this.fileContent;
    }

    public JButton getNextButton() {
        return this.nextButton;
    }
}
