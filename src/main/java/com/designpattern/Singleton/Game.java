package com.designpattern.Singleton;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game {
    private static Game game_instance = null;

    private Stage stage;
    private Scene scene;
    private int score;
    private String userName;

    private Game() {
        score = 0;
    }

    public static synchronized Game getInstance() {
        if (game_instance == null)
            game_instance = new Game();

        return game_instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return userName;
    }

    // public void renewGame(){
    // game_instance = new Game();
    // }
}
