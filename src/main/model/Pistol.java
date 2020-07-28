package model;

// A specific type of weapon which the player starts with; high starting ammo
public class Pistol extends Weapon {
    private static final int MAXAMMO = 500; // the pistol's ammo capacity

    /*
     * EFFECTS: creates a pistol with name set to "Pistol" and ammo
     * set to max
     */
    public Pistol() {
        setName("Pistol");
        setAmmo(MAXAMMO);
    }

    public int getInitialAmmo() {
        return MAXAMMO;
    }
}
