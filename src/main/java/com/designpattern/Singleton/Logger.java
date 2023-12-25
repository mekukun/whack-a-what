package com.designpattern.Singleton;

import javafx.scene.control.TextArea;

public class Logger {
    private static Logger logger_instance = null;
    private TextArea log_area;

    private Logger() {
        log_area = (TextArea) Game.getInstance().getScene().lookup("#loggingBox");
        log("Logging...");
    }

    public static synchronized Logger getInstance() {
        if (logger_instance == null)
            logger_instance = new Logger();

        return logger_instance;
    }

    // Method to append text to the TextArea and scroll to the end
    public void log(String text) {
        log_area.appendText("\n" + text);
        // Set the caret position to the end
        log_area.positionCaret(log_area.getLength());
    }
}
