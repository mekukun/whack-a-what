package com.designpattern.Factory;

import java.util.Random;

import com.designpattern.Behaviors.AddScore;
import com.designpattern.Behaviors.BonusScore;
import com.designpattern.Behaviors.DeductScore;
import com.designpattern.Behaviors.PopOutPeek;
import com.designpattern.Behaviors.SlidingPeek;
import com.designpattern.Entity.BonusMole;
import com.designpattern.Entity.NonMole;
import com.designpattern.Entity.NormalMole;
import com.designpattern.Entity.Pest;

public class PestFactory {
    public Pest createPest(String type) {
        Pest pest = null;
        
        
        switch(type){
            case "NORMAL" :
                return new NormalMole(new AddScore(), new PopOutPeek());
            
            case "NON" :
                return new NonMole(new DeductScore(),new PopOutPeek());
            
            case "BONUS":
                return new BonusMole(new BonusScore(), new SlidingPeek());
        }

        return pest;
    }

}
