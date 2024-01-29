package controller;

import engine.CryptoEngine;
import util.CryptoException;
import view.EncryptPanel;

import java.io.File;

import static util.Tools.breakIntoLines;
import static util.Tools.transformString;

public class EncryptPanelController extends FileController {
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


}
