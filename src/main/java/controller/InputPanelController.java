package controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.ApplicationFrame;
import view.InputPanel;

/**
 * This class handles the input panel logic
 */
public class InputPanelController {
    /** log4j instance */
    private static final Logger logger = LogManager.getLogger("InputPanelController");
    private final InputPanel view;

    public InputPanelController(InputPanel view) {
        this.view = view;
    }

    /**
     * Opens a dialog for the user to select a text file
     * @return {@link Optional} of the selected {@link File}
     */
    private static Optional<File> openFile() {
        // construct FileChooser
        JFileChooser chooser = new JFileChooser();
        // disable multi-selection
        chooser.setMultiSelectionEnabled(false);
        // only allow text files to be selected
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text file", "txt");
        chooser.setFileFilter(filter);
        // show open dialog
        logger.info("Showing open dialog");
        chooser.showOpenDialog(null);
        logger.info("Open dialog closed");

        // return file as Optional since the value might be null if the user did not select a valid file
        return Optional.ofNullable(chooser.getSelectedFile());
    }

    private String readFromFile(File source) {
        try {
            logger.info("Started reading file content");
            StringBuilder content = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(source));
            reader.lines().forEach(line -> content.append(line).append('\n'));
            reader.close();
            logger.info("Finished reading file content");
            return content.toString();
        } catch (IOException ignore) {
            logger.error("An IOException occured while reading the file content");
            return "";
        }
    }

    /**
     * defines the logic that should be executed when the open button is clicked
     */
    public void onOpenButtonClicked() {
        Optional<File> fileMaybe = openFile();
        fileMaybe.ifPresent(file -> {
                    view.getPathTextField().setText(file.getAbsolutePath());
                    view.getContentContainer().setText(readFromFile(file));
                });
        logger.info(String.format("Selected file: %s", view.getPathTextField().getText().isEmpty()
                                                                ? "NONE"
                                                                : view.getPathTextField().getText()));
    }

    public void onNext(ApplicationFrame parent) {
        parent.callEncryptionPanel();
    }
}
