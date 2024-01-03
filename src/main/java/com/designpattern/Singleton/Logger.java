package com.designpattern.Singleton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TextArea;

public class Logger {
    private static Logger logger_instance = null;
    private static TextArea log_area;

    private Logger() {
    }

    public static synchronized Logger getInstance() {

        log_area = (TextArea) Game.getInstance().getScene().lookup("#loggingBox");

        if (logger_instance == null)
            logger_instance = new Logger();

        return logger_instance;
    }

    // Method to append text to the TextArea and scroll to the end
    public void log(String text) {
        log_area.appendText("\n" + getCurrentTimestamp("yyyy-MM-dd HH:mm:ss") + " : " + text);
        // Set the caret position to the end
        log_area.positionCaret(log_area.getLength());
    }

    // Method to get text from the TextArea and save it into a single file
    public void saveAsFile() {

        // Construct the dynamic file name
        String fileName = getCurrentTimestamp("yyyyMMddHHmmss") + " GameLog.txt";

        // Specify the folder path if needed
        String folderPath = "GameLogs/";

        // Create the directory if it doesn't exist
        File directory = new File(folderPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.out.println("Failed to create the directory.");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderPath + fileName))) {
            // Write the text to the file
            writer.write(log_area.getText());
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }

    private String getCurrentTimestamp(String format) {
        // Get the current timestamp
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        // Format the timestamp using the formatter
        String formattedTimestamp = currentDateTime.format(formatter);

        return formattedTimestamp;
    }
}
