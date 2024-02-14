package view;

import controller.InjectPanelController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class InjectPanel {
    /** reference to the logger instance */
    private static final Logger logger = LogManager.getLogger("EncryptPanel");
    /** reference to the frame */
    private final ApplicationFrame parent;
    /** reference to the root panel */
    private JPanel root;
    /** reference to the back button */
    private JButton backButton;
    /** reference to the open button */
    private JButton openButton;
    /** reference to the input path text field */
    private JTextField inputPathField;
    /** reference to the next button */
    private JButton nextButton;
    /** reference to the image panel */
    private JPanel imagePanel;
    /** reference to the controller */
    private final InjectPanelController controller;


    /**
     * default constructor
     * @param parent reference to the {@link ApplicationFrame} frame
     */
    public InjectPanel(ApplicationFrame parent) {
        this.parent = parent;
        controller = new InjectPanelController(this, parent);
        backButton.addActionListener(e -> parent.popLastPanel());
        openButton.addActionListener(e -> controller.onOpenButton());
    }

    /**
     * Getter for the root panel
     * @return reference to the root {@link JPanel}
     */
    public JPanel getRoot() {
        return this.root;
    }

    /**
     * Getter for the input text field
     * @return reference to the input {@link JTextField}
     */
    public JTextField getInputPathField() {
        return this.inputPathField;
    }

    /**
     * Getter for the image panel
     * @return reference to the image {@link JPanel}
     */
    public JPanel getImagePanel() {
        return this.imagePanel;
    }

    /**
     * This method redraws the image if an image was previously selected
     */
    public void redraw() {
        logger.debug("Trying to reconstruct the preview image...");
        if (!inputPathField.getText().isEmpty()) {
            logger.debug("Path to image found!");
            controller.readImage(inputPathField.getText());
        }
    }
}
