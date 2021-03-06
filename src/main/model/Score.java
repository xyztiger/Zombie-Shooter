package model;

import exceptions.NotEnoughPointsException;

// Represents the current score of the player ie. how many zombies the player has killed; can be spent to buy weapons
public class Score {
    private int points;

    // MODIFIES: this
    // EFFECTS: creates a new score object with points set to 0
    public Score() {
        points = 0;
    }

    // MODIFIES: this
    // EFFECTS: decreases points by sepcified amount, throws NotEnoughPointsException if
    //          specified amount is greater than current points
    public void spend(Integer amount) throws NotEnoughPointsException {
        if (amount > points) {
            throw new NotEnoughPointsException();
        } else {
            points -= amount;
        }
    }

    // MODIFIES: this
    // EFFECTS: increases points by specified amount of points
    public void increase(Integer points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }
}
