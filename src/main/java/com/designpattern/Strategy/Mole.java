package com.designpattern.Strategy;

import com.designpattern.Singleton.Game;
import com.designpattern.Singleton.Logger;

import javafx.scene.control.Label;

public class Mole extends Pests {

    @Override
    public void peek() {
        //
    }

    @Override
    public void handleScore() {
        Logger.getInstance().log("Plus Score! New Score : " + Game.getInstance().getScore());
        Label actualScoreLabel = (Label) Game.getInstance().getScene().lookup("#actualScoreLabel");
        actualScoreLabel.setText(Integer.toString(Game.getInstance().getScore()));
    }

}
