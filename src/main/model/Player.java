package model;

import java.util.ArrayList;

import enviornment.*;
import exceptions.BorderException;

public class Player extends Entity {

    private Direction dir;
    private ArrayList<Weapon> weapons;
    private ArrayList<String> weaponNames;
    private Weapon currentWeapon;
//    private EnumSet<Weapons> weapons;
//    private Set<Weapon> weapons;
//    private int health;
//    private boolean alive;
//    private int posX;
//    private int posY;

    public enum Direction {
        N, E, S, W
    }

    public Player() {
        posX = Stage.WIDTH / 2;
        posY = Stage.HEIGHT / 2;
//        health = 100;
//        alive = true;
        weapons = new ArrayList<>();
        weaponNames = new ArrayList<>();
        Weapon pistol = new Pistol();
        addWeapons(pistol);
        currentWeapon = pistol;
    }

    public void move() throws BorderException {
        if (dir == Direction.N) {
            posY -= 1;
        }
        if (dir == Direction.E) {
            posX += 1;
        }
        if (dir == Direction.S) {
            posY += 1;
        }
        if (dir == Direction.W) {
            posX -= 1;
        }
        checkHitBorder();
    }

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

    public void setDirection(String d) {
        dir = Direction.valueOf(d);
    }

    public Direction getDirection() {
        return dir;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void addWeapons(Weapon choice) {
        if (!weaponNames.contains(choice.getName())) {
            weapons.add(choice);
            weaponNames.add(choice.getName());
        }
    }

    public ArrayList<String> getWeaponNames() {
        return weaponNames;
    }

    public String getCurrentWeaponName() {
        return currentWeapon.getName();
    }

    public void setCurrentWeapon(Weapon weapon) {
        currentWeapon = weapon;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

//    public void die() {
//    }
//
//    public void switchWeapon() {
//    }
//
//    public void shoot() {
//    }

//    public int getHealth() {
//        return health;
//    }

//    public void hurt() {
//        health -= 1;
//    }

//    public ArrayList<Integer> getPosition() {
//        ArrayList<Integer> position = new ArrayList<>();
//        position.add(posX);
//        position.add(posY);
//        return position;
//    }
}
