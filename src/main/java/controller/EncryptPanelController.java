package controller;

import view.EncryptPanel;

public class EncryptPanelController extends FileController {
    EncryptPanel view;

    public EncryptPanelController(EncryptPanel view) {
        this.view = view;
    }

    public void onOpenFile() {
        openFile().ifPresent(file -> view.getKeyPathTextField().setText(file.getAbsolutePath()));
    }
}
