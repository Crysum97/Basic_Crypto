package controller;

import engine.CryptoEngine;
import iterative.Matrix;
import iterative.Vigerene;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CryptoException;
import view.ApplicationFrame;
import view.EncryptPanel;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.Objects;

import static util.Tools.breakIntoLines;
import static util.Tools.transformString;

public class EncryptPanelController extends FileController {
    private static final Logger logger = LogManager.getLogger("EncryptPanel");
    private final EncryptPanel view;
    private final ApplicationFrame parent;

    public EncryptPanelController(EncryptPanel view, ApplicationFrame parent) {
        this.view = view;
        this.parent = parent;
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
            File keyFile = new File(key);
            if (keyFile.exists()) {
                String encrypted = engine.encrypt(input, transformString(readFromFile(new File(key))));
                view.getPreviewTextArea().setText(breakIntoLines(encrypted));
            } else {
                throw new NoSuchFileException("This file does not exists!");
            }
        } catch (CryptoException e) {
            e.printStackTrace();
        } catch (NoSuchFileException ignore) {
            JOptionPane.showMessageDialog(null, "This file does not exist!","Error", JOptionPane.ERROR_MESSAGE);
            view.getKeyPathTextField().setText("");
        }
    }

    public void onEncrypt() {
        String input = parent.getInput().getContentContainer().getText();
        String keyField = view.getKeyPathTextField().getText();
        switch (Objects.requireNonNull(view.getMethodComboBox().getSelectedItem()).toString()) {
            case "Caesar 1" -> {
                try {
                    encryptCaesar(new functional.Caesar(), input, Integer.parseInt(keyField));
                } catch (NumberFormatException ignore) {
                    handleInvalidInput(keyField);
                }
            }
            case "Caesar 2" -> {
                try {
                    encryptCaesar(new iterative.Caesar(), input, Integer.parseInt(keyField));
                } catch (NumberFormatException ignore) {
                    handleInvalidInput(keyField);
                }
            }
            case "Matrix" -> encryptMatrix(new Matrix(), input);
            case "Vigerene" -> encryptVigerene(new Vigerene(), input, keyField);
            default -> System.out.println("Shouldn't be here...");
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

    public void onItemChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            switch (e.getItem().toString()) {
                case "Matrix" -> {
                    view.getKeyPathTextField().setEnabled(false);
                    view.getKeyPathTextField().setEditable(false);
                    view.getOpenButton().setEnabled(false);
                }
                case "Vigerene" -> {
                    view.getKeyPathTextField().setEnabled(true);
                    view.getKeyPathTextField().setEditable(false);
                    view.getOpenButton().setEnabled(true);
                }
                default -> {
                    view.getKeyPathTextField().setEnabled(true);
                    view.getKeyPathTextField().setEditable(true);
                    view.getOpenButton().setEnabled(false);
                }
            }
            view.getKeyPathTextField().setText("");
            view.getPreviewTextArea().setText("");
        }
    }

}
