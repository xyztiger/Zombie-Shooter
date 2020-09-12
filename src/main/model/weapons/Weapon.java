package model.weapons;

import exceptions.NoAmmoException;
import model.Bullet;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * Represents all types of weapons that the players can buy
 * all weapons have their specific ammo amounts
 * all weapons have specific names
 * all weapons can shoot and kill zombies by consuming ammo
 */

public class Weapon {
    int ammo; // the weapon's current ammo count
    int cost; // the number of points required to buy the weapon
    Player player;
    private String name; // the weapon's name
    private static final int MAXAMMO = 0;

    /*
     * EFFECTS: creates a weapon with name set to "Weapon" and ammo
     * set to 0 and cost set to 0
     */
    public Weapon() {
        setName("Weapon");
        setAmmo(MAXAMMO);
        setCost(0);
    }

    /*
     * MODIFIES: this
     * EFFECTS: reduces the ammo count of the weapon by 1
     *          if no more ammo, throws an exception which indicates no ammo
     */
    public CopyOnWriteArrayList<Bullet> shoot(Player player) throws NoAmmoException {
        if (ammo > 0) {
            ammo -= 1;
            CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
            bullets.add(new Bullet(player));
            return bullets;
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

    public int getInitialAmmo() {
        return ammo;
    }

    //EFFECTS: Overrides default equals() method to allow two weapon subtypes to be considered equal
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

    //EFFECTS: Overrides default hashCode() method to allow two weapon subtypes to have the same hashcode
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
