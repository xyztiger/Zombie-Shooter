package model;

import java.util.ArrayList;
import java.util.Random;
import enviornment.*;

public class Zombie extends Person {
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

    public boolean alive() {
        return alive;
    }

}
