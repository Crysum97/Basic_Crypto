package controller;

import view.ApplicationFrame;
import view.InjectPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InjectPanelController extends FileController {
    private final InjectPanel view;
    private final ApplicationFrame parent;

    public InjectPanelController(InjectPanel view, ApplicationFrame parent) {
        this.view = view;
        this.parent = parent;
    }

    public void onOpenButton() {
        openImage().ifPresent(file -> {
            view.getInputPathField().setText(file.getAbsolutePath());
            readImage(file.getAbsolutePath());
        });
    }

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
