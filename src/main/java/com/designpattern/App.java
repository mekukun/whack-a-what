package com.designpattern;

import java.io.IOException;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Whack-a-what");
        GameFacade gameFacade = new GameFacade();
        gameFacade.StartInterface(stage);
        // Initial Game Scene
        
    }


    public static void main(String[] args) {
        launch();
    }

}