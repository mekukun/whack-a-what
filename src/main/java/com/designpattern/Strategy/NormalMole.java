package com.designpattern.Strategy;

import com.designpattern.App;
import com.designpattern.Singleton.Game;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class NormalMole extends Mole {

    private ImageView moleIv;

    public NormalMole(Image image, double x, double y) {
        ImageView iv5 = new ImageView();
        iv5.setImage(image);
        iv5.setFitWidth(100);
        iv5.setPreserveRatio(true);
        iv5.setSmooth(true);
        iv5.setCache(true);

        iv5.setCursor(Cursor.HAND);
        
        iv5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Tile pressed ");
                handleScore();
                event.consume();
            }
        });

        StackPane sp = (StackPane) Game.getInstance().getScene().lookup("#pestLayer");
        sp.getChildren().add(iv5);
        iv5.setVisible(false);

        moleIv = iv5;

        moleIv.setVisible(true);

        moleIv.setTranslateX(x);
        moleIv.setTranslateY(y);
    }

    @Override
    public void peek() {

        // Create a TranslateTransition for sliding in
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), moleIv);
        slideIn.setByY(130); // Slide to the original Y position

        // Create a TranslateTransition for sliding out
        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(1), moleIv);
        slideOut.setByY(-130); // Slide to the top

        // Start by sliding in
        slideIn.play();

        // After sliding in, set up a cycle for sliding in and out
        slideIn.setOnFinished(e -> {
            slideOut.play();
            slideOut.setOnFinished(event -> slideIn.play());
        });
    }

    @Override
    public void handleScore() {
        Game game = Game.getInstance();
        game.setScore(game.getScore() + 1);
        super.handleScore();
    }
}
