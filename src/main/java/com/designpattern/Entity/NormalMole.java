package com.designpattern.Entity;


import com.designpattern.Behaviors.PeekBehavior;
import com.designpattern.Behaviors.ScoreBehavior;
import com.designpattern.Factory.ImageEnum;
import com.designpattern.Factory.ImageGenerator;


public class NormalMole extends Pest {

    public NormalMole(ScoreBehavior score, PeekBehavior peek) {
        super(score, peek);
        super.setImageView(ImageGenerator.GenerateImage(ImageEnum.RAT.getAbbreviation()));
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
