package view;

import javax.swing.*;

public class InjectPanel {
    private final ApplicationFrame parent;
    private JPanel root;
    private JButton backButton;

    public InjectPanel(ApplicationFrame parent) {
        this.parent = parent;
        backButton.addActionListener(e -> parent.popLastPanel());
    }

    public JPanel getRoot() {
        return this.root;
    }
}
