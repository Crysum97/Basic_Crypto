package view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

/**
 * this class defines the graphical user interface
 */
public class View {
    /** reference to the root panel */
    private JPanel root;
    /** reference to the tabbed panel */
    private JTabbedPane tabPanel;
    private static final Logger logger = LogManager.getLogger("View");

    /**
     * default constructor. registers all necessary listeners
     */
    public View() {
        InputPanel inputPanel = new InputPanel();
        tabPanel.add("Input", inputPanel.getRoot());
        logger.debug("Registered InputPanel in View");
    }

    /**
     * simple getter method for the root panel
     * @return reference to the root panel
     */
    public JPanel getRoot() {
        return this.root;
    }
}
