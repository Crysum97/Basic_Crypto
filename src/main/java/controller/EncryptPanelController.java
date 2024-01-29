package controller;

import engine.CryptoEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CryptoException;
import view.EncryptPanel;

import javax.swing.*;
import java.io.File;

import static util.Tools.breakIntoLines;
import static util.Tools.transformString;

public class EncryptPanelController extends FileController {
    private static final Logger logger = LogManager.getLogger("EncryptPanel");
    EncryptPanel view;

    public EncryptPanelController(EncryptPanel view) {
        this.view = view;
    }

    public void onOpenFile() {
        openFile().ifPresent(file -> view.getKeyPathTextField().setText(file.getAbsolutePath()));
    }

    public void encryptCaesar(CryptoEngine engine,String input, int key) {
        try {
            String encrypted = engine.encrypt(input, key);
            view.getPreviewTextArea().setText(breakIntoLines(encrypted));
        } catch (CryptoException e) {
            e.printStackTrace();
        }
    }

    public void encryptMatrix(CryptoEngine engine, String input) {
        try {
            String encrypted = engine.encrypt(input);
            view.getPreviewTextArea().setText(breakIntoLines(encrypted));
        } catch (CryptoException e) {
            e.printStackTrace();
        }
    }

    public void encryptVigerene(CryptoEngine engine, String input, String key) {
        try {
            String encrypted = engine.encrypt(input, transformString(readFromFile(new File(key))));
            view.getPreviewTextArea().setText(breakIntoLines(encrypted));
        } catch (CryptoException e) {
            e.printStackTrace();
        }
    }

    public void handleInvalidInput(String invalid) {
        logger.error(String.format("Found invalid input %s", invalid));
        JOptionPane.showMessageDialog(null, "Please provide a valid encryption key!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void onSaveFile() {
        saveTextFile().ifPresent(file -> writeToFile(file, transformString(view.getPreviewTextArea().getText())));
    }


}
