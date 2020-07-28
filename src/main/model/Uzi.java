package model;

// A specific type of weapon which the player can buy; medium ammo capacity
public class Uzi extends Weapon {
    private static final int MAXAMMO = 100; // the uzi's ammo capacity

    /*
     * EFFECTS: creates a Shotgun with name set to "Uzi" and ammo
     * set to max
     */
    public Uzi() {
        setName("Uzi");
        setAmmo(MAXAMMO);
    }

    public int getInitialAmmo() {
        return MAXAMMO;
    }
}
