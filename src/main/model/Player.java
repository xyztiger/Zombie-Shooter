package model;

import java.util.HashMap;

import enviornment.*;
import exceptions.BorderException;
import model.weapons.Pistol;
import model.weapons.Weapon;

// Represents the player which the user can move around the stage and shoot from to kill zombies
// the player has a direction of either N, E, S, or W which they are facing and can shoot the gun in that direction
// the player has an arsenal of weapons which they carry and may choose from to fire

public class Player extends Entity {

    private HashMap<Integer, Weapon> weapons; // the weapons the player currently has
    private Weapon currentWeapon; // the current weapon the player is using
    private static final int SPEED = 5;

    // possible directions the player can face: N: North, E: East, S: South, W: West
//    public enum Direction {
//        N, E, S, W
//    }


    /*
     * EFFECTS: creates the player at the center of the stage
     *          the player is created with only one weapon: a pistol with max ammo
     */
    public Player() {
        posX = Stage.WIDTH / 2;
        posY = Stage.HEIGHT / 2;
        speed = SPEED;
//        health = 100;
//        alive = true;
        setDirection("N");
        weapons = new HashMap<>();
        Weapon pistol = new Pistol();
        addWeapons(pistol);
        currentWeapon = pistol;
    }

    public void setDirection(String d) {
        dir = Direction.valueOf(d);
    }

    public Direction getDirection() {
        return dir;
    }

    public HashMap<Integer, Weapon> getWeapons() {
        return weapons;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds the specified weapon to the player's list of available weapons
     *          if the weapon is not already in the list of available weapons
     */
    public void addWeapons(Weapon choice) {
        int nextIndex = weapons.size() + 1;
        if (!weapons.containsValue(choice)) {
            weapons.put(nextIndex, choice);
        } else {
            for (Weapon weapon : weapons.values()) {
                if (weapon.equals(choice)) {
                    weapon.setAmmo(weapon.getInitialAmmo());
                }
            }
        }
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

}
