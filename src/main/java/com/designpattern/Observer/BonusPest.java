package com.designpattern.Observer;

import javafx.scene.image.ImageView;

public class BonusPest extends TimerSubscriber{

    ImageView bonusPestImageView;
    Timer timer;
    
    public BonusPest(Timer timer, ImageView bonusPestImageView) {
        this.bonusPestImageView = bonusPestImageView;
        this.timer = timer;
        timer.attachSubscribers_10Sec(this);
    }

    @Override
    public void update() {
        bonusPestImageView.setVisible(true);
    }

}
