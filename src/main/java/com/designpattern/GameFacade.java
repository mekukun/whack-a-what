package com.designpattern;

import java.util.Random;

import com.designpattern.Behaviors.LeftRightPeek;
import com.designpattern.Entity.Furniture;
import com.designpattern.Entity.Pest;
import com.designpattern.Factory.FurnitureFactory;
import com.designpattern.Factory.PestFactory;
import com.designpattern.Singleton.Game;
import com.designpattern.Singleton.Logger;
import com.designpattern.App;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

public class GameFacade {
    private Timeline timer;
    private Scene scene = App.scene;
    
    // Method to format time as mm:ss
    private String formatTime(int seconds) {
        return seconds + "s";
    }

    public void StartInterface(Stage stage){
        
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
    
    private void showCongratsPopup(Stage stage) {
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

        Button replayButton = new Button("Play Again");
        Button exitButton = new Button("Home");

        // Add labels to the VBox
        popupLayout.getChildren().addAll(congrats, score, actualScore, leaderboard, replayButton, exitButton);

        // Create the popup
        Popup popup = new Popup();
        popup.getContent().add(popupLayout); // Add content to the popup

        // Show the popup at a specific location (adjust as needed)
        popup.show((Stage) scene.getWindow());

        replayButton.setOnAction(event -> {
            // Set game singleton to this initial screen
            Game.getInstance().setScore(0);
            popup.hide();
            sceneToGame(stage);
        });

        exitButton.setOnAction(event -> {
            // Set game singleton to this initial screen
            popup.hide();
            this.StartInterface(stage);
        });

    }

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

        FurnitureFactory furnitureFactory = new FurnitureFactory();

        // 2nd layer (Carpet)
        Furniture carpet = furnitureFactory.createFurniture("CARPET");
        sp.getChildren().add(carpet.getImage());

        // 3rd layer (Sofa)
        Furniture sofa = furnitureFactory.createFurniture("SOFA");
        sp.getChildren().add(sofa.getImage());


        // 4th layer (Cabinet)
        Furniture cabinet = furnitureFactory.createFurniture("CABINET");
        sp.getChildren().add(cabinet.getImage());


        // 5th layer (Lamp)
        Furniture lamp = furnitureFactory.createFurniture("LAMP");
        sp.getChildren().add(lamp.getImage());


        // 6th layer (Clock)
        Furniture clock = furnitureFactory.createFurniture("CLOCK");
        sp.getChildren().add(clock.getImage());

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
                        showCongratsPopup(stage);
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
        actualScoreLabel.setId("actualScoreLabel");
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
        System.out.println(log);
        log.log("Game starts!");

        // Starts the timer
        timer.play();
        
        // Create the pests
        Random r = new Random();
        int max = 4;
        int min = 0;
        int randomNumber = r.nextInt(max - min + 1) + min;
        Pest[] pests = new Pest[5];
        for (int i = 0; i < pests.length; i++) {
            if(i == randomNumber){
                pests[i] = new PestFactory().createPest("NORMAL");
            }else{
                pests[i] = new PestFactory().createPest("NON");
            }
        }
        // Set the mole to the hole
        carpet.getHole().setPest(pests[0]);
        clock.getHole().setPest(pests[1]);
        lamp.getHole().setPest(pests[2]);
        sofa.getHole().setPest(pests[3]);

        // special for cabinet only
        pests[4].setPeekBehavior(new LeftRightPeek());
        cabinet.getHole().setPest(pests[4]);

        for (Pest pest : pests) {
            pest.performPeek();
        }
    }
}
