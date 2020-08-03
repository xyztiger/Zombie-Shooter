package model;

import exceptions.NoAmmoException;

import java.util.Objects;

/*
 * Represents all types of weapons that the players can buy
 * all weapons have their specific ammo amounts
 * all weapons have specific names
 * all weapons can shoot and kill zombies by consuming ammo
 */

public abstract class Weapon {
    private int ammo; // the weapon's current ammo count
    private int cost; // the number of points required to buy the weapon
    private String name; // the weapon's name

    /*
     * MODIFIES: this
     * EFFECTS: reduces the ammo count of the weapon by 1
     *          if no more ammo, throws an exception which indicates no ammo
     */
    public void shoot() throws NoAmmoException {
        if (ammo > 0) {
            ammo -= 1;
        } else {
            throw new NoAmmoException();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: increases the weapon's ammo count by specified amount
     */
    public void loadAmmo(int amount) {
        ammo += amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public void setAmmo(Integer amount) {
        ammo = amount;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setCost(int amount) {
        cost = amount;
    }

    public int getCost() {
        return cost;
    }

    public abstract int getInitialAmmo();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weapon weapon = (Weapon) o;
        return name.equals(weapon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
