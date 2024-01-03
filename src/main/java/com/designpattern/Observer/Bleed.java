package com.designpattern.Observer;

import javafx.scene.image.ImageView;

public class Bleed extends TimerSubscriber{

    ImageView bleedImageView;
    Timer timer;
    
    public Bleed(Timer timer, ImageView bleedImageView) {
        this.bleedImageView = bleedImageView;
        this.timer = timer;
        timer.attachSubscribers_10Sec(this);
    }

    @Override
    public void update() {
        bleedImageView.setVisible(true);
    }

}
