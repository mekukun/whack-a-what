package com.designpattern.Strategy;


import com.designpattern.Behaviors.PeekBehavior;
import com.designpattern.Behaviors.ScoreBehavior;


public class NormalMole extends Pests {

    public NormalMole(ScoreBehavior score, PeekBehavior peek) {
        super(score, peek);
        //TODO Auto-generated constructor stub
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
