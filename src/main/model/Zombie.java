package model;

import java.util.Random;
import enviornment.*;

// Represents the enemies that they player wants to shoot to kill
// Zombies spawn at random coordinates on the stage and are killed if the player shoots while facing them
// A new zombie spawns at a random coordinate on the stage when a zombie is killed

public class Zombie extends Entity {
//    private int posX;
//    private int posY;
    private boolean alive;

    public Zombie() {
        alive = true;
        Random random = new Random();
        posX = random.nextInt(Stage.WIDTH + 1);
        posY = random.nextInt(Stage.HEIGHT + 1);
    }

    public void die() {
        alive = false;
    }

    public boolean getAlive() {
        return alive;
    }

}
