package com.designpattern;

import java.util.Random;

import com.designpattern.Behaviors.LeftRightPeek;
import com.designpattern.Entity.Furniture;
import com.designpattern.Entity.Pest;
import com.designpattern.Factory.FurnitureFactory;
import com.designpattern.Factory.PestFactory;
import com.designpattern.Observer.Timer;
import com.designpattern.Observer.TimerSubscriber;
import com.designpattern.Singleton.Game;
import com.designpattern.Singleton.Leaderboard;
import com.designpattern.Singleton.LeaderboardEntry;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Timer timer = new Timer();
    private Scene scene = App.scene;
    private static Furniture[] furnitures = new Furniture[5];
    private Stage stage;

    // Method to format time as mm:ss
    // private String formatTime(int seconds) {
    // return seconds + "s";
    // }

    private VBox StartScreenSetup() {
        VBox screen = new VBox(20);
        // spacing = 8
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

        // Label for "Choose your mole"
        Label chooseMoleLabel = new Label("Your mission is to destroy RATS only!");
        chooseMoleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Set font properties for label

        // Create the "Start" button Container
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> {
            // Set game singleton to this initial screen
            Game.getInstance().setUsername(textField.getText());
            sceneToGame(stage);
        });
        // Populate screen w/ containers
        screen.getChildren().addAll(b, nameInputBox, chooseMoleLabel, startButton);

        return screen;
    }

    private void showCongratsPopup() {

        // Save log
        Logger.getInstance().saveAsFile();

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
        Label leaderboard = new Label("Top 5 Leaderboard"); // Set the initial value directly
        leaderboard.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        TableView<LeaderboardEntry> table = new TableView<>();
        TableColumn<LeaderboardEntry, Integer> rankColumn = new TableColumn<>("Rank");
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

        TableColumn<LeaderboardEntry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<LeaderboardEntry, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        table.getColumns().addAll(rankColumn, nameColumn, scoreColumn);
        table.setMaxHeight(150);
        // ensure that the extra space in table column header will be distributed among
        // the columns
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Update leaderboard
        Leaderboard.getInstance().paintLeaderboard(table);

        Button replayButton = new Button("Play Again");
        Button exitButton = new Button("Home");

        // Add labels to the VBox
        popupLayout.getChildren().addAll(congrats, score, actualScore, leaderboard, table, replayButton, exitButton);

        // Create the popup
        Popup popup = new Popup();
        popup.getContent().add(popupLayout); // Add content to the popup

        // Show the popup at a specific location (adjust as needed)
        popup.show((Stage) scene.getWindow());

        replayButton.setOnAction(event -> {
            // Set game singleton to this initial screen
            // Game.getInstance().renewGame();
            popup.hide();
            sceneToGame(stage);
        });

        exitButton.setOnAction(event -> {
            // Set game singleton to this initial screen
            popup.hide();
            // Game.getInstance().renewGame();
            this.StartInterface(stage);
        });

    }

    public void StartInterface(Stage stage) {

        this.stage = stage;

        // // Start Actual Game Scene
        scene = new Scene(StartScreenSetup(), 400, 300);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    private void setBleedImage(StackPane sp) {
        Image bleedImage = new Image(App.class.getResource("images/bleed.png").toExternalForm());

        ImageView bleed = new ImageView();
        bleed.setImage(bleedImage);
        bleed.setFitHeight(860);
        bleed.setPreserveRatio(false);
        bleed.setSmooth(true);
        bleed.setCache(true);
        bleed.setOpacity(0.5);
        bleed.setVisible(false);
        bleed.setId("bleed");
        sp.getChildren().add(bleed);
    }

    private void setBackground(StackPane sp) {
        Image image = new Image(App.class.getResource("images/background.png").toExternalForm());

        sp.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, false))));
    }

    private void setFurnitures(StackPane sp) {

        FurnitureFactory furnitureFactory = new FurnitureFactory();

        // Create furniture
        furnitures[0] = furnitureFactory.createFurniture("CARPET");
        furnitures[1] = furnitureFactory.createFurniture("SOFA");
        furnitures[2] = furnitureFactory.createFurniture("CABINET");
        furnitures[3] = furnitureFactory.createFurniture("LAMP");
        furnitures[4] = furnitureFactory.createFurniture("CLOCK");
        // 2nd layer (Carpet)
        Furniture carpet = furnitures[0];
        sp.getChildren().add(carpet.getImage());

        // 3rd layer (Sofa)
        Furniture sofa = furnitures[1];
        sp.getChildren().add(sofa.getImage());

        // 4th layer (Cabinet)
        Furniture cabinet = furnitures[2];
        sp.getChildren().add(cabinet.getImage());

        // 5th layer (Lamp)
        Furniture lamp = furnitures[3];
        sp.getChildren().add(lamp.getImage());

        // 6th layer (Clock)
        Furniture clock = furnitures[4];
        sp.getChildren().add(clock.getImage());

    }

    public static void createPest() {
        StackPane pestStackPane = (StackPane) Game.getInstance().getScene().lookup("#pestLayer");
        pestStackPane.getChildren().clear();
        Random r = new Random();
        int max = 4;
        int min = 0;
        int randomNumber = r.nextInt(max - min + 1) + min;
        Pest[] pests = new Pest[5];
        for (int i = 0; i < pests.length; i++) {
            if (i == randomNumber) {
                pests[i] = new PestFactory().createPest("NORMAL");
            } else {
                pests[i] = new PestFactory().createPest("NON");
            }
        }
        // Set the mole to the hole
        furnitures[0].getHole().setPest(pests[0]);
        furnitures[1].getHole().setPest(pests[1]);
        furnitures[2].getHole().setPest(pests[2]);
        furnitures[3].getHole().setPest(pests[3]);
        furnitures[4].getHole().setPest(pests[4]);

        // special for cabinet only
        pests[2].setPeekBehavior(new LeftRightPeek());

        for (Pest pest : pests) {
            pest.performPeek();
        }

        int HEIGHT_MAX = 300;
        int HEIGHT_MIN = 0;
        int RANDOM_HEIGHT = r.nextInt(HEIGHT_MAX - HEIGHT_MIN + 1) + HEIGHT_MIN;
        RANDOM_HEIGHT = r.nextBoolean() ? -RANDOM_HEIGHT : RANDOM_HEIGHT;
        Pest bonusPest = new PestFactory().createPest("BONUS");
        bonusPest.getImageView().setId("bonusPest");
        bonusPest.getImageView().setVisible(false);
        bonusPest.setCoordinateTranslation(0, RANDOM_HEIGHT);
        bonusPest.performPeek();
    }

    private void setLoggingBox(StackPane sp) {
        TextArea textBox = new TextArea();
        textBox.setId("loggingBox");
        StackPane.setMargin(textBox, new Insets(750, 800, 10, 10)); // Adjust values as needed
        textBox.getStyleClass().add("text-area");

        // Limit lines to 5 and make scrollable
        textBox.setWrapText(true); // Enable text wrapping

        // Optional: Disable editing if needed
        textBox.setEditable(false);
        sp.getChildren().add(textBox);
    }

    private void setTimerBox(StackPane sp) {
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
        timerLabel.setId("timerLabel");
        // Add labels to the VBox
        timerBox.getChildren().addAll(timeLeftLabel, timerLabel);

        // 8th layer (Timer Box)
        sp.getChildren().add(timerBox);
    }

    private void setTimer() {
        new EndGameTimerSubscriber(timer);
        timer.startTimer();
    }

    private void setClickThisBox(StackPane sp) {
        VBox moleBox = new VBox();
        moleBox.setAlignment(Pos.CENTER);
        moleBox.setStyle("-fx-background-color: rgba(53, 89, 119, 0.2);");
        StackPane.setMargin(moleBox, new Insets(10, 400, 750, 400)); // Adjust values as needed

        // Create a label for "Click"
        Label clickLabel = new Label("Click");
        clickLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Create a label for the mole
        Label moleLabel = new Label("The Rat!"); // Set the initial value directly
        moleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add labels to the VBox
        moleBox.getChildren().addAll(clickLabel, moleLabel);

        // 9th layer (Mole Box)
        sp.getChildren().add(moleBox);
    }

    private void setScoreBox(StackPane sp) {
        VBox scoreBox = new VBox();
        scoreBox.setAlignment(Pos.CENTER);
        scoreBox.setStyle("-fx-background-color: rgba(53, 89, 119, 0.2);");
        StackPane.setMargin(scoreBox, new Insets(10, 10, 750, 900)); // Adjust values as needed

        // Create a label for "Score:"
        Label scoreLabel = new Label("Score:");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Create a label for the score
        Game.getInstance().setScore(0); // Reset score in the initialization
        Label actualScoreLabel = new Label(Integer.toString(Game.getInstance().getScore())); // Set the initial value
                                                                                             // directly
        actualScoreLabel.setId("actualScoreLabel");
        actualScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add labels to the VBox
        scoreBox.getChildren().addAll(scoreLabel, actualScoreLabel);

        // 10th layer (Score Box)
        sp.getChildren().add(scoreBox);
    }

    private void sceneToGame(Stage stage) {

        // Setup global stackPane
        StackPane sp = new StackPane();
        sp.setId("globalPane");

        StackPane pestLayer = new StackPane();
        pestLayer.setId("pestLayer");

        // Set bleeding overlay to be the first layer
        setBleedImage(sp);

        // 1st layer of the stackpane
        sp.getChildren().add(pestLayer);

        setBackground(sp);

        setFurnitures(sp);

        setLoggingBox(sp);

        // Timer Box
        setTimerBox(sp);

        // Create a Timeline to update the timer every second
        setTimer();

        // Click-this-mole Box
        setClickThisBox(sp);

        // Score Box
        setScoreBox(sp);

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
        createPest();
    }

    public class EndGameTimerSubscriber extends TimerSubscriber {

        Timer endGameTimer;

        public EndGameTimerSubscriber(Timer timer) {
            endGameTimer = timer;
            endGameTimer.attachSubscribers_EndGame(this);
        }

        @Override
        public void update() {
            showCongratsPopup();
        }

    }

}
