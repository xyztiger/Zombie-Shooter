package model;

// A specific type of weapon which the player can buy; low ammo capacity
public class Shotgun extends Weapon {
    private static final int MAXAMMO = 50; // the shotguns's ammo capacity
    public static final int COST = 0; // amount of points needed to purchase uzi

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
}
