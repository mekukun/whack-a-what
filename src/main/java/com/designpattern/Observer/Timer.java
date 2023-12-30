package com.designpattern.Observer;

import java.util.ArrayList;
import java.util.List;

import com.designpattern.Singleton.Game;
import com.designpattern.Singleton.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Timer {

    private Timeline timer;
    private List<TimerSubscriber> subscribers_10sec = new ArrayList<TimerSubscriber>();
    private List<TimerSubscriber> subscribers_EndGame = new ArrayList<TimerSubscriber>();


    public void startTimer() {
        timer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    Label timerLabel = (Label) Game.getInstance().getScene().lookup("#timerLabel");
                    int remainingSeconds = Integer.parseInt(timerLabel.getText().replace("s", ""));
                    remainingSeconds--;
                    timerLabel.setText(formatTime(remainingSeconds));
                    if (remainingSeconds == 10) {
                        ImageView bleedImage = (ImageView) Game.getInstance().getScene().lookup("#bleed");
                        ImageView bonusPestImage = (ImageView) Game.getInstance().getScene().lookup("#bonusPest");
                        
                        new Bleed(this, bleedImage); 
                        new BonusPest(this, bonusPestImage);
                        
                        notifySubscribers_10Sec();
                    }
                    if (remainingSeconds == 0) {
                        timer.stop();
                        Logger.getInstance().log("Game has ended!");

                        notifySubscribers_EndGame();
                    }
                }));
        timer.setCycleCount(Timeline.INDEFINITE);
    }

    public void play() {
        timer.play();
    }

    // Method to format time as mm:ss
    private String formatTime(int seconds) {
        return seconds + "s";
    }

    public void attachSubscribers_10Sec(TimerSubscriber subscriber) {
        subscribers_10sec.add(subscriber);
    }

    public void notifySubscribers_10Sec() {
        for (TimerSubscriber subscriber : subscribers_10sec) {
            subscriber.update();
        }
    }

    public void attachSubscribers_EndGame(TimerSubscriber subscriber) {
        subscribers_EndGame.add(subscriber);
    }

    public void notifySubscribers_EndGame() {
        for (TimerSubscriber subscriber : subscribers_EndGame) {
            subscriber.update();
        }
    }

    

}
