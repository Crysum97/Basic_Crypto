package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Optional;

public class FileController {
    private static final Logger logger = LogManager.getLogger("FileController");

    /**
     * Opens a dialog for the user to select a text file
     * @return {@link Optional} of the selected {@link File}
     */
    protected Optional<File> openFile() {
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

    protected String readFromFile(File source) {
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

    protected Optional<File> saveTextFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("text file", "txt");
        chooser.setFileFilter(filter);

        logger.info("Showing save dialog");
        chooser.showSaveDialog(null);
        logger.info("Save dialog closed");

        if (chooser.getSelectedFile() == null || chooser.getSelectedFile().getAbsolutePath().endsWith("txt")) {
            return Optional.ofNullable(chooser.getSelectedFile());
        } else {
            return Optional.of(new File(chooser.getSelectedFile().getAbsoluteFile() + ".txt"));
        }
    }

    protected void writeToFile(File target, String content) {
        try {
            logger.info(String.format("Started writing to file at %s", target.getAbsolutePath()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target, false));
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException ignore) {
            logger.error(String.format("Encountered a problem while writing to %s", target.getAbsolutePath()));
        }
    }
}
