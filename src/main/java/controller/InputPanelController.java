package controller;

import java.io.*;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.ApplicationFrame;
import view.InputPanel;

import static util.Tools.breakIntoLines;
import static util.Tools.transformString;

/**
 * This class handles the input panel logic
 */
public class InputPanelController extends FileController {
    /** log4j instance */
    private static final Logger logger = LogManager.getLogger("InputPanelController");
    private final InputPanel view;

    public InputPanelController(InputPanel view) {
        this.view = view;
    }



    /**
     * defines the logic that should be executed when the open button is clicked
     */
    public void onOpenButtonClicked() {
        Optional<File> fileMaybe = openFile();
        fileMaybe.ifPresent(file -> {
                    view.getPathTextField().setText(file.getAbsolutePath());
                    view.getContentContainer().setText(breakIntoLines(transformString(readFromFile(file))));
                });
        logger.info(String.format("Selected file: %s", view.getPathTextField().getText().isEmpty()
                                                                ? "NONE"
                                                                : view.getPathTextField().getText()));
    }

    public void onNext(ApplicationFrame parent) {
        parent.callEncryptionPanel();
    }
}
