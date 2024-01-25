package controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class handles the input panel logic
 */
public class InputPanelController {
    /** currently selected file. empty string if no file is selected */
    private static String selectedFilePath = "";
    /** log4j instance */
    private static final Logger logger = LogManager.getLogger("InputPanelController");

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

    /**
     * defines the logic that should be executed when the open button is clicked
     */
    public static void onOpenButtonClicked() {
        Optional<File> fileMaybe = openFile();
        selectedFilePath = fileMaybe.map(File::getAbsolutePath).orElse("");
        logger.info(String.format("Selected file: %s", selectedFilePath.isEmpty() ? "NONE" : selectedFilePath));
    }

    /**
     * simple getter for the absolute file path of the selected file
     * @return absolute path of the selected file
     */
    public static String getSelectedFilePath() {
        return selectedFilePath;
    }
}
