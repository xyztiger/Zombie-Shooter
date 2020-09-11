package model.weapons;

import exceptions.NoAmmoException;
import model.Bullet;
import model.Player;

// A specific type of weapon which the player can buy; medium ammo capacity
public class Uzi extends Weapon {
    private static final int MAX_AMMO = 100; // the uzi's ammo capacity
    private static final int FIRE_RATE = 10;
    public static final int COST = 20; // amount of points needed to purchase uzi

    /*
     * EFFECTS: creates a Shotgun with name set to "Uzi" and ammo
     * set to max and cost set to COST
     */
    public Uzi() {
        setName("Uzi");
        setAmmo(MAX_AMMO);
        setCost(COST);
    }

    public int getInitialAmmo() {
        return MAX_AMMO;
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
