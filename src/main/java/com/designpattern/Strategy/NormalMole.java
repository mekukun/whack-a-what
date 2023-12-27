package com.designpattern.Strategy;


import com.designpattern.Behaviors.PeekBehavior;
import com.designpattern.Behaviors.ScoreBehavior;
import com.designpattern.Factory.ImageEnum;
import com.designpattern.Factory.ImageFactory;


public class NormalMole extends Pests {

    public NormalMole(ScoreBehavior score, PeekBehavior peek) {
        super(score, peek);
        super.setImageView(ImageFactory.CreateImage("NORMAL"));
    }

    @Override
    public void performPeek() {
        super.performPeek();
    }

    @Override
    public void performScore() {
        super.performScore();
    }
}
