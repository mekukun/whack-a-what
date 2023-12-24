package com.designpattern;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Whack-a-what");

        StackPane sp = new StackPane();

        StackPane pestLayer = new StackPane();

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

        sp.getChildren().add(iv4);

        scene = new Scene(sp, 1200, 860);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

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
                event.consume();
            }
        });

        pestLayer.getChildren().add(iv5);
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