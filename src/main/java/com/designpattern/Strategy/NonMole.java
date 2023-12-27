package com.designpattern.Strategy;

import com.designpattern.Behaviors.PeekBehavior;
import com.designpattern.Behaviors.ScoreBehavior;
import com.designpattern.Factory.ImageEnum;
import com.designpattern.Factory.ImageFactory;

public class NonMole extends Pests {
     public NonMole(ScoreBehavior score, PeekBehavior peek) {
        super(score, peek);
        super.setImageView(ImageFactory.CreateImage("NONE"));
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
