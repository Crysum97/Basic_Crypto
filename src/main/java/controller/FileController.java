package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Optional;

public class FileController {
    /** Log4J instance */
    private static final Logger logger = LogManager.getLogger("FileController");

    /**
     * Opens a dialog for the user to select a text file
     * @return {@link Optional} of the selected {@link File}
     */
    protected Optional<File> openFile() {
        // construct FileChooser
        JFileChooser chooser = getTextFileChooser(new FileNameExtensionFilter("text file","txt"));
        // show open dialog
        logger.info("Showing open dialog");
        chooser.showOpenDialog(null);
        logger.info("Open dialog closed");

        // return file as Optional since the value might be null if the user did not select a valid file
        return Optional.ofNullable(chooser.getSelectedFile());
    }

    protected Optional<File> openImage() {
        JFileChooser chooser = getTextFileChooser(new FileNameExtensionFilter("Bitmap Image", "bmp"));
        logger.info("Showing open dialog");
        chooser.showOpenDialog(null);
        logger.info("Open dialog closed");

        return Optional.ofNullable(chooser.getSelectedFile());
    }

    /**
     * Reads the content of a given file
     * @param source {@link File} to read
     * @return content of the file as {@link String}
     */
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

    /**
     * Selects a text file to save to
     * @return {@link Optional} of the selected {@link File}
     */
    protected Optional<File> saveTextFile() {
        JFileChooser chooser = getTextFileChooser(new FileNameExtensionFilter("text file", "txt"));

        logger.info("Showing save dialog");
        chooser.showSaveDialog(null);
        logger.info("Save dialog closed");

        // This check is necessary to ensure that the correct file extension is used!
        if (chooser.getSelectedFile() == null || chooser.getSelectedFile().getAbsolutePath().endsWith("txt")) {
            return Optional.ofNullable(chooser.getSelectedFile());
        } else {
            return Optional.of(new File(chooser.getSelectedFile().getAbsoluteFile() + ".txt"));
        }
    }

    /**
     * Overwrites the Content of the given {@link File} with the given {@link String}
     * @param target {@link File} to save to
     * @param content {@link String} to save to the file
     */
    protected void writeToFile(File target, String content) {
        try {
            logger.info(String.format("Started writing to file at %s", target.getAbsolutePath()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target, false));
            writer.write(content);
            writer.flush();
            writer.close();
            logger.info(String.format("Done writing to file at %s", target.getAbsolutePath()));
        } catch (IOException ignore) {
            logger.error(String.format("Encountered a problem while writing to %s", target.getAbsolutePath()));
        }
    }

    /**
     * Generates a {@link JFileChooser} that lets the user select a single text file
     * @return {@link JFileChooser} that only accepts a single text files
     */
    private JFileChooser getTextFileChooser(FileNameExtensionFilter filter) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(filter);
        return chooser;
    }
}
