package view;

import controller.InputPanelController;

import javax.swing.*;

/**
 * this class defines the graphical user interface
 */
public class View {
    /** reference to the root panel */
    private JPanel root;
    /** reference to the tabbed panel */
    private JTabbedPane tabPanel;
    /** reference to the input panel */
    private JPanel inputPanel;
    /** reference to the open button*/
    private JButton openBtn;
    /** reference to the path text field */
    private JTextField pathTextField;
    /** reference to the input path label */
    private JLabel inputPathLabel;

    /**
     * default constructor. registers all necessary listeners
     */
    public View() {
        // register listener for the open button
        openBtn.addActionListener(event -> {
            InputPanelController.onOpenButtonClicked();
            pathTextField.setText(InputPanelController.getSelectedFilePath());
        });
    }

    /**
     * simple getter method for the root panel
     * @return reference to the root panel
     */
    public JPanel getRoot() {
        return this.root;
    }
}
