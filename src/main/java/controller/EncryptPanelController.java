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
    /** reference to logger instance */
    private static final Logger logger = LogManager.getLogger("EncryptPanel");
    /** reference to view */
    private final EncryptPanel view;
    /** reference to frame */
    private final ApplicationFrame parent;

    /**
     * default constructor
     * @param view reference to {@link EncryptPanel} view
     * @param parent reference to the {@link ApplicationFrame} frame
     */
    public EncryptPanelController(EncryptPanel view, ApplicationFrame parent) {
        this.view = view;
        this.parent = parent;
    }

    /**
     * Behavior for clicking on the open button
     */
    public void onOpenFile() {
        openFile().ifPresent(file -> view.getKeyPathTextField().setText(file.getAbsolutePath()));
    }

    /**
     * Encrypts a given {@link String} using caesar encryption
     * @param engine used {@link CryptoEngine}
     * @param input {@link String} to encrypt
     * @param key key to use for encryption
     */
    public void encryptCaesar(CryptoEngine engine,String input, int key) {
        logger.info("Started encryption using Caesar");
        try {
            String encrypted = engine.encrypt(input, key);
            logger.info("Encryption using Caesar is done");
            view.getPreviewTextArea().setText(breakIntoLines(encrypted));
        } catch (CryptoException e) {
            logger.error("Encryption using Caesar failed!");
            e.printStackTrace();
        }
    }

    /**
     * Encrypts a given {@link String}using matrix encryption
     * @param engine used {@link CryptoEngine}
     * @param input {@link String} to encrypt
     */
    public void encryptMatrix(CryptoEngine engine, String input) {
        logger.info("Started encryption using Matrix encryption");
        try {
            String encrypted = engine.encrypt(input);
            logger.info("Encryption using Matrix encryption is done");
            view.getPreviewTextArea().setText(breakIntoLines(encrypted));
        } catch (CryptoException e) {
            logger.error("Encyption using Matrix encryption failed!");
            e.printStackTrace();
        }
    }

    /**
     * Encrypts a given {@link String} using vigerene encryption
     * @param engine used {@link CryptoEngine}
     * @param input {@link String} to encrypt
     * @param key used {@link String} key
     */
    public void encryptVigerene(CryptoEngine engine, String input, String key) {
        logger.info("Started encryption using Vigerene");
        try {
            File keyFile = new File(key);
            if (keyFile.exists()) {
                String encrypted = engine.encrypt(input, transformString(readFromFile(new File(key))));
                logger.info("Encryption using Vigerene is done");
                view.getPreviewTextArea().setText(breakIntoLines(encrypted));
            } else {
                throw new NoSuchFileException("This file does not exists!");
            }
        } catch (CryptoException e) {
            logger.error("Encryption using Vigerene failed!");
            e.printStackTrace();
        } catch (NoSuchFileException ignore) {
            logger.error("Encryption using Vigerene failed!");
            JOptionPane.showMessageDialog(null, "This file does not exist!","Error", JOptionPane.ERROR_MESSAGE);
            view.getKeyPathTextField().setText("");
        }
    }

    /**
     * Behavior when clicking on the encrypt button
     */
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
        view.getNextButton().setEnabled(!view.getPreviewTextArea().getText().isEmpty());
    }

    /**
     * Shows a dialog to the user if invalid input is provided
     * @param invalid {@link String} input that provoked the call
     */
    public void handleInvalidInput(String invalid) {
        logger.error(String.format("Found invalid input %s", invalid));
        JOptionPane.showMessageDialog(null, "Please provide a numeric encryption key!",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Behavior when the save button is clicked
     */
    public void onSaveFile() {
        saveTextFile().ifPresent(file -> writeToFile(file, transformString(view.getPreviewTextArea().getText())));
    }

    /**
     * Updated the view whenever the selected encryption method is changed
     * @param e current {@link ItemEvent} that provoked the call
     */
    public void onItemChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            switch (e.getItem().toString()) {
                case "Matrix" -> {
                    logger.debug(String.format("Selected encryption Method: %s", e.getItem()));
                    view.getKeyPathTextField().setEnabled(false);
                    view.getKeyPathTextField().setEditable(false);
                    view.getOpenButton().setEnabled(false);
                }
                case "Vigerene" -> {
                    logger.debug(String.format("Selected encryption Method: %s", e.getItem()));
                    view.getKeyPathTextField().setEnabled(true);
                    view.getKeyPathTextField().setEditable(false);
                    view.getOpenButton().setEnabled(true);
                }
                default -> {
                    logger.debug(String.format("Selected encryption Method: %s", e.getItem()));
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
