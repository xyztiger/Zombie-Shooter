package model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;
import enviornment.*;

public class Player implements Person {
    private int posX;
    private int posY;
    private Direction dir;
    private int health;
    private boolean alive;
    private ArrayList<Weapon> weapons;
    private ArrayList<String> weaponNames;
    private Weapon currentWeapon;
    //private EnumSet<Weapons> weapons;
    //private Set<Weapon> weapons;

    private enum Direction {
        N, E, S, W
    }

    private enum Weapons {
        Pistol, Shotgun, Uzi, RPG
    }

    public Player() {
        posX = Stage.WIDTH / 2;
        posY = Stage.HEIGHT / 2;
        health = 100;
        alive = true;
        //weapons = EnumSet.of(Weapons.Pistol);
        weapons = new ArrayList<>();
        weaponNames = new ArrayList<>();
        Weapon pistol = new Pistol();
        pistol.loadAmmo(50);
        addWeapons(pistol);
        currentWeapon = pistol;
    }

    public void move() {
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
    }

    public void die() {
    }

    public void switchWeapon() {
    }

    public void shoot() {
    }

    public void setDirection(String d) {
        dir = Direction.valueOf(d);
    }

    public ArrayList<Integer> getPosition() {
        ArrayList<Integer> position = new ArrayList<>();
        position.add(posX);
        position.add(posY);
        return position;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void addWeapons(Weapon choice) {
        weapons.add(choice);
        weaponNames.add(choice.getName());
    }

    public ArrayList<String> getWeaponNames() {
        return weaponNames;
    }

    public int getHealth() {
        return health;
    }

    public void hurt() {
        health -= 1;
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
}
