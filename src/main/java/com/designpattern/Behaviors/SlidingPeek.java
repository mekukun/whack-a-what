package com.designpattern.Behaviors;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SlidingPeek implements PeekBehavior{

    @Override
    public void peek(ImageView pest) {
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.8), pest);
        slideIn.setToX(600); // Slide to the original Y position

        // Create a TranslateTransition for sliding out
        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.8), pest);
        slideOut.setToX(-600); // Slide to the top

        // Start by sliding in
        slideIn.play();

        // After sliding in, set up a cycle for sliding in and out
        slideIn.setOnFinished(e -> {
            slideOut.play();
            slideOut.setOnFinished(event -> slideIn.play());
        });
    }
    
}
