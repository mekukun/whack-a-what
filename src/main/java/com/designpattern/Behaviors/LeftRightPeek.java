package com.designpattern.Behaviors;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LeftRightPeek implements PeekBehavior{

    @Override
    public void peek(ImageView pest) {
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), pest);
        slideIn.setByX(100); // Slide to the original Y position

        // Create a TranslateTransition for sliding out
        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(1), pest);
        slideOut.setByX(-100); // Slide to the top

        // Start by sliding in
        slideIn.play();

        // After sliding in, set up a cycle for sliding in and out
        slideIn.setOnFinished(e -> {
            slideOut.play();
            slideOut.setOnFinished(event -> slideIn.play());
        });
    }
    
}
