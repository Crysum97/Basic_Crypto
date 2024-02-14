package view;

import javax.swing.*;
import java.util.Stack;

/**
 * This class represents the frame which displays the graphical user interface
 */
public class ApplicationFrame extends JFrame {
    /** Reference to the content of the frame */
    JPanel currentContent;
    private final InputPanel input = new InputPanel(this);
    private final EncryptPanel encrypt = new EncryptPanel(this);
    private final InjectPanel inject = new InjectPanel(this);
    Stack<JPanel> visited = new Stack<>();

    /**
     * default constructor to apply basic frame settings
     */
    public ApplicationFrame() {
        super();
        this.setSize(500, 500);
        this.setResizable(false);
        this.setTitle("Basic Crypto");
        this.currentContent = input.getRoot();
        this.setContentPane(this.currentContent);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Calls the encryption panel
     */
    public void callEncryptionPanel() {
        visited.push(currentContent);
        currentContent = encrypt.getRoot();
        update();
    }

    /**
     * Calls the injection panel
     */
    public void callInjectPanel() {
        visited.push(currentContent);
        currentContent = inject.getRoot();
        update();
    }

    /**
     * Reopens the last panel
     */
    public void popLastPanel() {
        currentContent = visited.pop();
        update();
    }

    /**
     * Getter for the input panel
     * @return reference to the {@link InputPanel}
     */
    public InputPanel getInput() {
        return this.input;
    }

    /**
     * Updates the GUI
     */
    private void update() {
        this.setContentPane(currentContent);
        this.revalidate();
        this.repaint();

        SwingUtilities.invokeLater(() -> {
            if (currentContent.equals(inject.getRoot())) {
                inject.redraw();
            }
        });
    }
}
