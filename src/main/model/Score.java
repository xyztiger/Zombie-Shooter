package model;

import exceptions.NotEnoughPointsException;

public class Score {
    private int points;

    public Score() {
        points = 0;
    }

    public void spend(Integer amount) throws NotEnoughPointsException {
        if (amount > points) {
            throw new NotEnoughPointsException();
        } else {
            points -= amount;
        }
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
