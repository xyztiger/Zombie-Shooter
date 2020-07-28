package model;

// A specific type of weapon which the player can buy; low ammo capacity
public class Shotgun extends Weapon {
    private static final int MAXAMMO = 50; // the shotguns's ammo capacity

    /*
     * EFFECTS: creates a Shotgun with name set to "Shotgun" and ammo
     * set to max
     */
    public Shotgun() {
        setName("Shotgun");
        setAmmo(MAXAMMO);
    }

    public int getInitialAmmo() {
        return MAXAMMO;
    }
}
