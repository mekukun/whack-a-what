package com.designpattern;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

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
        moleOptions.getItems().addAll("Bookworm Mole", "Foodie Mole", "Adventurous Mole"); // Add your desired options

        // Set Bookworm Mole as the default value
        moleOptions.setValue("Bookworm Mole");

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

        // Actual Play Game Scene
        StackPane sp = new StackPane();

        StackPane pestLayer = new StackPane();

        TextArea textBox = new TextArea();
        StackPane.setMargin(textBox, new Insets(750, 800, 10, 10)); // Adjust values as needed
        textBox.getStyleClass().add("text-area");
        appendText(textBox, "This is the first line.\n");
        appendText(textBox, "This is the second line.\n");
        appendText(textBox, "This is the third line.\n");
        appendText(textBox, "This is the third line.\n");
        appendText(textBox, "This is the third line.\n");
        appendText(textBox, "This is the third line.\n");
        appendText(textBox, "This is the third line.\n");
        appendText(textBox, "Hi lol.");

        // Limit lines to 5 and make scrollable
        textBox.setWrapText(true); // Enable text wrapping

        // Optional: Disable editing if needed
        textBox.setEditable(false);

        // 1st layer of the stackpane (the most back)
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
                    if (remainingSeconds == 0) {
                        timer.stop();
                        showCongratsPopup();
                    }
                }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

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
        String mole = "Snake";
        Label moleLabel = new Label(mole); // Set the initial value directly
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
        Label actualScoreLabel = new Label("90"); // Set the initial value directly
        actualScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add labels to the VBox
        scoreBox.getChildren().addAll(scoreLabel, actualScoreLabel);

        // 10th layer (Score Box)
        sp.getChildren().add(scoreBox);

        // Start Actual Game Scene
        // Add a click event handler to the button
        startButton.setOnAction(event -> {
            scene = new Scene(sp, 1200, 860);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);

            // Center the stage on the screen
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

            stage.show();
        });

        Image i5 = new Image(App.class.getResource("images/snake.png").toExternalForm());

        ImageView iv5 = new ImageView();
        iv5.setImage(i5);
        iv5.setFitWidth(100);
        iv5.setPreserveRatio(true);
        iv5.setSmooth(true);
        iv5.setCache(true);
        iv5.setTranslateX(0);
        iv5.setTranslateY(-130);

        // Create a TranslateTransition for sliding in
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), iv5);
        slideIn.setToY(0); // Slide to the original Y position

        // Create a TranslateTransition for sliding out
        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(1), iv5);
        slideOut.setToY(-130); // Slide to the top

        // Start by sliding in
        slideIn.play();

        // After sliding in, set up a cycle for sliding in and out
        slideIn.setOnFinished(e -> {
            slideOut.play();
            slideOut.setOnFinished(event -> slideIn.play());
        });

        iv5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Tile pressed ");
                pestLayer.getChildren().remove(iv5);
                showCongratsPopup();
                event.consume();
            }
        });

        // Put snake ImageView into the first layer
        pestLayer.getChildren().add(iv5);
    }

    // Method to append text to the TextArea and scroll to the end
    private void appendText(TextArea textArea, String text) {
        textArea.appendText(text);
        // Set the caret position to the end
        textArea.positionCaret(textArea.getLength());
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
        Label actualScore = new Label("90"); // Set the initial value directly
        actualScore.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Create a label for the leaderboard
        Label leaderboard = new Label("Leaderboard"); // Set the initial value directly
        leaderboard.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Add labels to the VBox
        popupLayout.getChildren().addAll(congrats, score, actualScore, leaderboard);

        // Create the popup
        Popup popup = new Popup();
        popup.getContent().add(popupLayout); // Add content to the popup

        System.out.println("Is this pop showing? " + popup.isShowing());

        // Show the popup at a specific location (adjust as needed)
        popup.show((Stage) scene.getWindow());

    }

    // static void setRoot(String fxml) throws IOException {
    // scene.setRoot(loadFXML(fxml));
    // }

    // private static Parent loadFXML(String fxml) throws IOException {
    // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml +
    // ".fxml"));
    // return fxmlLoader.load();
    // }

    public static void main(String[] args) {
        launch();
    }

}