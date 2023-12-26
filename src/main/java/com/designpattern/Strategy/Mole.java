package com.designpattern.Strategy;

import com.designpattern.Singleton.Game;
import com.designpattern.Singleton.Logger;

public class Mole extends Pests {

    @Override
    public void peek(double x, double y) {
        //
    }

    @Override
    public void handleScore() {
        Logger.getInstance().log("Plus Score! New Score : " + Game.getInstance().getScore());
    }

}
