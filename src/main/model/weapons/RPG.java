package model.weapons;

import exceptions.NoAmmoException;
import model.Bullet;
import model.Player;
import model.weapons.Weapon;

// A specific type of weapon which the player can buy; very low ammo capacity
public class RPG extends Weapon {
    private static final int MAXAMMO = 20; // the RPG's ammo capacity
    public static final int COST = 50; // amount of points needed to purchase the RPG

    /*
     * EFFECTS: creates an RPG with name set to "RPG" and ammo
     * set to max and cost set to COST
     */
    public RPG() {
        setName("RPG");
        setAmmo(MAXAMMO);
        setCost(COST);
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

    public int getInitialAmmo() {
        return MAXAMMO;
    }
}
