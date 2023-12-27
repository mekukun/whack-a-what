package com.designpattern.Behaviors;

import com.designpattern.Singleton.Game;
import com.designpattern.Singleton.Logger;

import javafx.scene.control.Label;

public class AddScore implements ScoreBehavior{

    @Override
    public void handleScore() {
        Game game = Game.getInstance();
        game.setScore(game.getScore() + 1);
        
        Logger.getInstance().log("Plus Score! New Score : " + Game.getInstance().getScore());
        Label actualScoreLabel = (Label) Game.getInstance().getScene().lookup("#actualScoreLabel");
        actualScoreLabel.setText(Integer.toString(Game.getInstance().getScore()));
    }
    
}
