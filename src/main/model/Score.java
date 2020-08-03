package model;

public class Score {
    private int points;

    public Score() {
        points = 0;
    }

    public void spend(Integer points) {
        this.points -= points;
    }

    public void increase(Integer points) {
        this.points += points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }
}
