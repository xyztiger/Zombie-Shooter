package model;

import java.util.ArrayList;

import enviornment.*;
import exceptions.BorderException;

// Represents the player which the user can move around the stage and shoot from to kill zombies
// the player has a direction of either N, E, S, or W which they are facing and can shoot the gun in that direction
// the player has an arsenal of weapons which they carry and may choose from to fire

public class Player extends Entity {

    private Direction dir; // the direction the player is currently facing
    private ArrayList<Weapon> weapons; // the weapons the player currently has
    private ArrayList<String> weaponNames; // the names of the weapons the player currently has
    private Weapon currentWeapon; // the current weapon the player is using
//    private EnumSet<Weapons> weapons;
//    private Set<Weapon> weapons;
//    private int health;
//    private boolean alive;
//    private int posX;
//    private int posY;

    // possible directions the player can face: N: North, E: East, S: South, W: West
    public enum Direction {
        N, E, S, W
    }


    /*
     * EFFECTS: creates the player at the center of the stage
     *          the player is created with only one weapon: a pistol with max ammo
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: moves the player by one unit in the direction they are facing;
     *          if the player attempts to move beyond the boundaries set by the stage,
     *          the player does not move
     */
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

    public void setDirection(String d) {
        dir = Direction.valueOf(d);
    }

    public Direction getDirection() {
        return dir;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds the specified weapon to the player's list of available weapons
     *          if the weapon is not already in the list of available weapons
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: sets the player's current weapon to the specified weapon
     */
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
