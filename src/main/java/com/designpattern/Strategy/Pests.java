package com.designpattern.Strategy;

import com.designpattern.Behaviors.PeekBehavior;
import com.designpattern.Behaviors.ScoreBehavior;
import com.designpattern.Singleton.Game;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public abstract class Pests {
    
    private ImageView pest;

    ScoreBehavior score;
    PeekBehavior peek;
    
    

    public Pests(ScoreBehavior score, PeekBehavior peek) {
        this.score = score;
        this.peek = peek;
    }

    public void performPeek(){
        peek.peek(pest);
    };

    public void performScore(){
        score.handleScore();
    };

    public void setImageView(Image image){
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        imageView.setCursor(Cursor.HAND);
        
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Tile pressed ");
                performScore();
                event.consume();
            }
        });

        StackPane sp = (StackPane) Game.getInstance().getScene().lookup("#pestLayer");
        sp.getChildren().add(imageView);
        imageView.setVisible(false);

        pest = imageView;

        pest.setVisible(true);

        
    }

    public void setCoordinateTranslation(double x, double y){
        pest.setTranslateX(x);
        pest.setTranslateY(y);
    }
}
