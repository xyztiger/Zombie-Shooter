package model.weapons;

import exceptions.NoAmmoException;
import model.Bullet;
import model.Player;

import java.util.ArrayList;
import java.util.List;

// A specific type of weapon which the player can buy; low ammo capacity
public class Shotgun extends Weapon {
    private static final int MAXAMMO = 50; // the shotguns's ammo capacity
    public static final int COST = 30; // amount of points needed to purchase uzi
    private static final int SPREAD = 3;

    /*
     * EFFECTS: creates a Shotgun with name set to "Shotgun" and ammo
     * set to max and cost set to COST
     */
    public Shotgun() {
        setName("Shotgun");
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
//    public List<Bullet> shoot(Player p) throws NoAmmoException {
//        if (ammo > 0) {
//            ammo -= 1;
//            return createSpread(p);
//        } else {
//            throw new NoAmmoException();
//        }
//    }

    public List<Bullet> createSpread(Player p) {
        List<Bullet> pellets = new ArrayList<>();
        Bullet pellet1 = new Bullet(p);
        Bullet pellet2 = new Bullet(p);
        Bullet pellet3 = new Bullet(p);
        switch (p.getDirection()) {
            case N, S -> {
                pellet1.setPosX(p.getPosX() - SPREAD);
                pellet3.setPosX(p.getPosX() + SPREAD);
            }
            case E, W -> {
                pellet1.setPosY(p.getPosY() - SPREAD);
                pellet3.setPosY(p.getPosY() + SPREAD);
            }
        }
        pellets.add(pellet1);
        pellets.add(pellet2);
        pellets.add(pellet3);
        return pellets;
    }
}
