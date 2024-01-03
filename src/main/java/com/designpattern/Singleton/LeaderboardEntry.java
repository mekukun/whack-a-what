package com.designpattern.Singleton;

public class LeaderboardEntry {
    private int rank;
    private String name;
    private int score;

    public LeaderboardEntry(int rank, String name, int score) {
        this.rank = rank;
        this.name = name;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String print() {
        return rank + " || " + name + " || " + score;
    }
}
