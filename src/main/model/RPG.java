package model;

// A specific type of weapon which the player can buy; very low ammo capacity
public class RPG extends Weapon {
    private static final int MAXAMMO = 500; // the RPG's ammo capacity

    /*
     * EFFECTS: creates an RPG with name set to "RPG" and ammo
     * set to max
     */
    public RPG() {
        setName("RPG");
        setAmmo(MAXAMMO);
    }

    public int getInitialAmmo() {
        return MAXAMMO;
    }
}
