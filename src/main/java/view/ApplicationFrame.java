package view;

import javax.swing.*;

/**
 * This class represents the frame which displays the graphical user interface
 */
public class ApplicationFrame extends JFrame {
    /** Reference to the content of the frame */
    View root = new View();

    /**
     * default constructor to apply basic frame settings
     */
    public ApplicationFrame() {
        super();
        this.setSize(500, 500);
        this.setResizable(false);
        this.setTitle("Basic Crypto");
        this.setContentPane(this.root.getRoot());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
