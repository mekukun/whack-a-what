package com.designpattern;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import com.designpattern.Singleton.Game;
import com.designpattern.Singleton.Logger;
import com.designpattern.Strategy.NormalMole;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Timeline timer;

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Whack-a-what");

        // Initial Game Scene
        VBox screen = new VBox(20); // spacing = 8
        screen.setAlignment(Pos.CENTER); // Center nodes vertically
        screen.setPadding(new Insets(0, 30, 0, 30));

        // Title Container
        Label b = new Label("Whack-a-what?");
        b.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Set font size and weight

        // Name Input Container
        VBox nameInputBox = new VBox(8); // spacing = 8
        nameInputBox.setAlignment(Pos.CENTER); // Center nodes vertically

        // Name label
        Label name = new Label("Name"); // spacing = 8
        name.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font size and weight

        // Name text input
        TextField textField = new TextField();
        // Center text content horizontally
        textField.setAlignment(Pos.CENTER);

        // Populate inside the Name Input Container
        nameInputBox.getChildren().addAll(name, textField);

        // Choose Your Mole Container
        HBox hbox = new HBox(); // Create an HBox
        hbox.setSpacing(10); // Set spacing between elements
        // Center elements both vertically and horizontally
        hbox.setAlignment(Pos.CENTER);

        // Label for "Choose your mole"
        Label chooseMoleLabel = new Label("Choose your mole :");
        chooseMoleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14)); // Set font properties for label

        // Create a ComboBox with three options
        ComboBox<String> moleOptions = new ComboBox<>();
        moleOptions.getItems().addAll("Snake", "Rat", "Spider"); // Add your desired options
        // Set first option as the default value
        moleOptions.getSelectionModel().selectFirst();

        // Add elements to the HBox
        hbox.getChildren().addAll(chooseMoleLabel, moleOptions);

        // Create the "Start" button Container
        Button startButton = new Button("Start");

        // Populate screen w/ containers
        screen.getChildren().addAll(b, nameInputBox, hbox, startButton);

        // // Start Actual Game Scene
        scene = new Scene(screen, 400, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // ---------------------------------------------------------------------------------------

        // Start Actual Game Scene
        // Add a click event handler to the button
        startButton.setOnAction(event -> {
            // Set game singleton to this initial screen
            Game.getInstance().setMole(moleOptions.getValue());
            sceneToGame(stage);
        });
    }

    // Method to format time as mm:ss
    private String formatTime(int seconds) {
        return seconds + "s";
    }

    private void showCongratsPopup() {
        // Create a VBox
        VBox popupLayout = new VBox(8);
        popupLayout.setAlignment(Pos.CENTER);
        popupLayout.setPadding(new Insets(10, 10, 10, 10));
        popupLayout.setStyle("-fx-background-color: white;");

        // Create a label for "Congrats"
        Label congrats = new Label("Congrats!");
        congrats.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Create a label for the score
        Label score = new Label("Score:"); // Set the initial value directly
        score.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Create a label for the actualScore
        Label actualScore = new Label(Integer.toString(Game.getInstance().getScore())); // Set the initial value
                                                                                        // directly
        actualScore.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Create a label for the leaderboard
        Label leaderboard = new Label("Leaderboard"); // Set the initial value directly
        leaderboard.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add labels to the VBox
        popupLayout.getChildren().addAll(congrats, score, actualScore, leaderboard);

        // Create the popup
        Popup popup = new Popup();
        popup.getContent().add(popupLayout); // Add content to the popup

        // Show the popup at a specific location (adjust as needed)
        popup.show((Stage) scene.getWindow());

    }

    // Method to format time as mm:ss
    private void sceneToGame(Stage stage) {

        // Actual Play Game Scene
        StackPane sp = new StackPane();
        sp.setId("globalPane");

        StackPane pestLayer = new StackPane();
        pestLayer.setId("pestLayer");

        TextArea textBox = new TextArea();
        textBox.setId("loggingBox");
        StackPane.setMargin(textBox, new Insets(750, 800, 10, 10)); // Adjust values as needed
        textBox.getStyleClass().add("text-area");

        // Limit lines to 5 and make scrollable
        textBox.setWrapText(true); // Enable text wrapping

        // Optional: Disable editing if needed
        textBox.setEditable(false);

        Image bleedImage = new Image(App.class.getResource("images/bleed.png").toExternalForm());

        ImageView bleed = new ImageView();
        bleed.setImage(bleedImage);
        bleed.setFitHeight(860);
        bleed.setPreserveRatio(false);
        bleed.setSmooth(true);
        bleed.setCache(true);
        bleed.setOpacity(0.5);
        bleed.setVisible(false);

        // 0th layer of the stackpane (the most back)
        sp.getChildren().add(bleed);

        // 1st layer of the stackpane
        sp.getChildren().add(pestLayer);

        Image image = new Image(App.class.getResource("images/background.png").toExternalForm());

        sp.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false))));

        Image i0 = new Image(App.class.getResource("images/carpet.png").toExternalForm());

        ImageView iv = new ImageView();
        iv.setImage(i0);
        iv.setFitHeight(200);
        iv.setFitWidth(1050);
        iv.setPreserveRatio(false);
        iv.setSmooth(true);
        iv.setCache(true);
        iv.setTranslateX(0);
        iv.setTranslateY(300);

        // 2nd layer (Carpet)
        sp.getChildren().add(iv);

        Image i = new Image(App.class.getResource("images/sofa.png").toExternalForm());

        ImageView iv1 = new ImageView();
        iv1.setImage(i);
        iv1.setFitWidth(600);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);
        iv1.setTranslateX(200);
        iv1.setTranslateY(150);

        // 3rd layer (Sofa)
        sp.getChildren().add(iv1);

        Image i2 = new Image(App.class.getResource("images/cabinet.png").toExternalForm());

        ImageView iv2 = new ImageView();
        iv2.setImage(i2);
        iv2.setFitWidth(300);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);
        iv2.setTranslateX(-300);
        iv2.setTranslateY(160);

        // 4th layer (Cabinet)
        sp.getChildren().add(iv2);

        Image i3 = new Image(App.class.getResource("images/lamp.png").toExternalForm());

        ImageView iv3 = new ImageView();
        iv3.setImage(i3);
        iv3.setFitWidth(100);
        iv3.setPreserveRatio(true);
        iv3.setSmooth(true);
        iv3.setCache(true);
        iv3.setTranslateX(-300);
        iv3.setTranslateY(-25);

        // 5th layer (Lamp)
        sp.getChildren().add(iv3);

        Image i4 = new Image(App.class.getResource("images/clock.png").toExternalForm());

        ImageView iv4 = new ImageView();
        iv4.setImage(i4);
        iv4.setFitWidth(100);
        iv4.setPreserveRatio(true);
        iv4.setSmooth(true);
        iv4.setCache(true);
        iv4.setTranslateX(0);
        iv4.setTranslateY(-130);

        // 6th layer (Clock)
        sp.getChildren().add(iv4);

        // 7th layer (Log Message Box)
        sp.getChildren().add(textBox);

        // Timer Box
        // Create a VBox
        VBox timerBox = new VBox();
        timerBox.setAlignment(Pos.CENTER);
        timerBox.setStyle("-fx-background-color: rgba(53, 89, 119, 0.2);");
        StackPane.setMargin(timerBox, new Insets(10, 900, 750, 10)); // Adjust values as needed

        // Create a label for "Time left:"
        Label timeLeftLabel = new Label("Time left:");
        timeLeftLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Create a label for the timer
        Label timerLabel = new Label("30s"); // Set the initial value directly
        timerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add labels to the VBox
        timerBox.getChildren().addAll(timeLeftLabel, timerLabel);

        // 8th layer (Timer Box)
        sp.getChildren().add(timerBox);

        // Create a Timeline to update the timer every second
        timer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    int remainingSeconds = Integer.parseInt(timerLabel.getText().replace("s", ""));
                    remainingSeconds--;
                    timerLabel.setText(formatTime(remainingSeconds));
                    if (remainingSeconds == 10) {
                        bleed.setVisible(true);
                    }
                    if (remainingSeconds == 0) {
                        timer.stop();
                        showCongratsPopup();
                    }
                }));
        timer.setCycleCount(Timeline.INDEFINITE);

        // Click-this-mole Box
        // Create a VBox
        VBox moleBox = new VBox();
        moleBox.setAlignment(Pos.CENTER);
        moleBox.setStyle("-fx-background-color: rgba(53, 89, 119, 0.2);");
        StackPane.setMargin(moleBox, new Insets(10, 400, 750, 400)); // Adjust values as needed

        // Create a label for "Click"
        Label clickLabel = new Label("Click");
        clickLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Create a label for the mole
        Label moleLabel = new Label(Game.getInstance().getMole()); // Set the initial value directly
        moleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add labels to the VBox
        moleBox.getChildren().addAll(clickLabel, moleLabel);

        // 9th layer (Mole Box)
        sp.getChildren().add(moleBox);

        // Score Box
        // Create a VBox
        VBox scoreBox = new VBox();
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setStyle("-fx-background-color: rgba(53, 89, 119, 0.2);");
        StackPane.setMargin(scoreBox, new Insets(10, 10, 750, 900)); // Adjust values as needed

        // Create a label for "Score:"
        Label scoreLabel = new Label("Score:");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Create a label for the score
        Label actualScoreLabel = new Label(Integer.toString(Game.getInstance().getScore())); // Set the initial value
                                                                                             // directly
        actualScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add labels to the VBox
        scoreBox.getChildren().addAll(scoreLabel, actualScoreLabel);

        // 10th layer (Score Box)
        sp.getChildren().add(scoreBox);

        scene = new Scene(sp, 1200, 860);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);

        // Center the stage on the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

        stage.show();

        // Starts game singleton
        Game game = Game.getInstance();
        game.setStage(stage);
        game.setScene(scene);

        // Starts logging singleton
        Logger log = Logger.getInstance();
        log.log("Game starts!");

        // Starts the timer
        timer.play();

        Image i5 = new Image(App.class.getResource("images/snake.png").toExternalForm());
        NormalMole nm = new NormalMole(i5);
        nm.peek(0, -130);
    }

    public static void main(String[] args) {
        launch();
    }

}