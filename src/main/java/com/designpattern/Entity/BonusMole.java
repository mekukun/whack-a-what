package com.designpattern.Entity;

import com.designpattern.Behaviors.PeekBehavior;
import com.designpattern.Behaviors.ScoreBehavior;
import com.designpattern.Factory.ImageEnum;
import com.designpattern.Factory.ImageGenerator;

public class BonusMole extends Pest {
     public BonusMole(ScoreBehavior score, PeekBehavior peek) {
        super(score, peek);
        super.setImageView(ImageGenerator.GenerateImage(ImageEnum.GOLDENRAT.getAbbreviation()));
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
