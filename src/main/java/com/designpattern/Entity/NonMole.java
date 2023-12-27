package com.designpattern.Entity;

import java.util.Random;

import com.designpattern.App;
import com.designpattern.Behaviors.PeekBehavior;
import com.designpattern.Behaviors.ScoreBehavior;
import com.designpattern.Factory.ImageEnum;
import com.designpattern.Factory.ImageGenerator;

import javafx.scene.image.Image;

public class NonMole extends Pest {
     public NonMole(ScoreBehavior score, PeekBehavior peek) {
        super(score, peek);
        
        Random r = new Random();
        String moleString = r.nextBoolean() ? "snake" : "spider";
        
        super.setImageView(ImageGenerator.GenerateImage(moleString));
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
