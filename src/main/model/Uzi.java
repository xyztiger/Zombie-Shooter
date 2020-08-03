package model;

// A specific type of weapon which the player can buy; medium ammo capacity
public class Uzi extends Weapon {
    private static final int MAXAMMO = 100; // the uzi's ammo capacity
    private static final int COST = 20; // amount of points needed to purchase uzi

    /*
     * EFFECTS: creates a Shotgun with name set to "Uzi" and ammo
     * set to max and cost set to COST
     */
    public Uzi() {
        setName("Uzi");
        setAmmo(MAXAMMO);
        setCost(COST);
    }

    @Override
    public int getInitialAmmo() {
        return MAXAMMO;
    }
}
