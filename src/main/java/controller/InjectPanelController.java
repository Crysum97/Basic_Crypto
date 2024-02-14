package controller;

import view.ApplicationFrame;
import view.InjectPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InjectPanelController extends FileController {
    /** reference to the {@link InjectPanel} view*/
    private final InjectPanel view;
    /** reference to {@link ApplicationFrame} frame */
    private final ApplicationFrame parent;

    /**
     * default constructor
     * @param view reference to the {@link InjectPanel} view
     * @param parent reference to the {@link ApplicationFrame} frame
     */
    public InjectPanelController(InjectPanel view, ApplicationFrame parent) {
        this.view = view;
        this.parent = parent;
    }

    /**
     * Behavior when clicking on the open button
     */
    public void onOpenButton() {
        openImage().ifPresent(file -> {
            view.getInputPathField().setText(file.getAbsolutePath());
            readImage(file.getAbsolutePath());
        });
    }

    /**
     * reads and displays the selected image
     * @param filePath Path to the selected image
     */
    public void readImage(String filePath) {
            try {
                BufferedImage image = ImageIO.read(new File(filePath));
                JPanel imgPanel = view.getImagePanel();
//            imgPanel.getGraphics().clearRect(0, 0, imgPanel.getWidth(), imgPanel.getHeight());
                imgPanel.getGraphics().drawImage(image, 0, 0, imgPanel.getWidth(), imgPanel.getHeight(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
