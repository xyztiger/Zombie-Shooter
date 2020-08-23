package model;

import enviornment.Stage;
import exceptions.BorderException;

import java.util.ArrayList;

// Represents an entity which occupies a specific location (x-position and y-position) on the stage
public abstract class Entity {
    int posX; // the Entity's x-coordinate on the stage
    int posY; // the Entity's y-coordinate on the stage
    Direction dir;
    int speed;
    int width;
    int length;

    //EFFECTS: returns a list of integers representing the Entity's x and y position
    public ArrayList<Integer> getPosition() {
        ArrayList<Integer> position = new ArrayList<>();
        position.add(posX);
        position.add(posY);
        return position;
    }

    /*
     * MODIFIES: this
     * EFFECTS: moves the player by one unit in the direction they are facing;
     *          if the player attempts to move beyond the boundaries set by the stage,
     *          the player does not move
     */
    public void move() throws BorderException {
        if (dir == Direction.N) {
            posY -= speed;
        }
        if (dir == Direction.E) {
            posX += speed;
        }
        if (dir == Direction.S) {
            posY += speed;
        }
        if (dir == Direction.W) {
            posX -= speed;
        }
        checkHitBorder();
    }

    // EFFECTS: if the player is beyond the boundaries set by the stage, throw a border exception
    public void checkHitBorder() throws BorderException {
        if (getPosY() < 0) {
            setPosY(0);
            throw new BorderException();
        }
        if (getPosY() > Stage.HEIGHT) {
            setPosY(Stage.HEIGHT);
            throw new BorderException();
        }
        if (getPosX() < 0) {
            setPosX(0);
            throw new BorderException();
        }
        if (getPosX() > Stage.WIDTH) {
            setPosX(Stage.WIDTH);
            throw new BorderException();
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
