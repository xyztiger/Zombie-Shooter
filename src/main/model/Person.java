package model;

import java.util.ArrayList;

public abstract class Person {
    int posX;
    int posY;
//    void move();
//
//    void hurt();
//
//    void die();

    public ArrayList<Integer> getPosition() {
        ArrayList<Integer> position = new ArrayList<>();
        position.add(posX);
        position.add(posY);
        return position;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
