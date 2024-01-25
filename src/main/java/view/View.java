package view;

import javax.swing.*;

/**
 * this class defines the graphical user interface
 */
public class View {
    /** reference to the root panel */
    private JPanel root;
    /** reference to the tabbed panel */
    private JTabbedPane tabPanel;

    /**
     * default constructor. registers all necessary listeners
     */
    public View() {
        InputPanel inputPanel = new InputPanel();
        tabPanel.add("Input", inputPanel.getRoot());
    }

    /**
     * simple getter method for the root panel
     * @return reference to the root panel
     */
    public JPanel getRoot() {
        return this.root;
    }
}
