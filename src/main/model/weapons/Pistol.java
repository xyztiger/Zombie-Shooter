package model.weapons;

import exceptions.NoAmmoException;
import model.Bullet;
import model.Player;

import java.util.List;

// A specific type of weapon which the player starts with; high starting ammo
public class Pistol extends Weapon {
    private static final int MAXAMMO = 500; // the pistol's ammo capacity
    public static final int COST = 10; // amount of points needed to purchase ammo

    /*
     * EFFECTS: creates a pistol with name set to "Pistol" and ammo
     * set to max
     */
    public Pistol() {
        setName("Pistol");
        setAmmo(MAXAMMO);
        setCost(COST);
    }

    public int getInitialAmmo() {
        return MAXAMMO;
    }

    /*
     * MODIFIES: this
     * EFFECTS: reduces the ammo count of the weapon by 1
     *          if no more ammo, throws an exception which indicates no ammo
     */
    public Bullet shoot(Player p) throws NoAmmoException {
        if (ammo > 0) {
            ammo -= 1;
            return new Bullet(p);
        } else {
            throw new NoAmmoException();
        }
    }
}
