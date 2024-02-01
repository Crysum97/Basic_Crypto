package view;

import controller.InjectPanelController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class InjectPanel {
    private final ApplicationFrame parent;
    private JPanel root;
    private JButton backButton;
    private JButton openButton;
    private JTextField inputPathField;
    private JButton nextButton;
    private JPanel imagePanel;
    private final InjectPanelController controller;

    public InjectPanel(ApplicationFrame parent) {
        this.parent = parent;
        controller = new InjectPanelController(this, parent);
        backButton.addActionListener(e -> parent.popLastPanel());
        openButton.addActionListener(e -> controller.onOpenButton());
    }

    public JPanel getRoot() {
        return this.root;
    }

    public JTextField getInputPathField() {
        return this.inputPathField;
    }

    public JPanel getImagePanel() {
        return this.imagePanel;
    }

    public void redraw() {
        controller.readImage(inputPathField.getText());
    }
}
