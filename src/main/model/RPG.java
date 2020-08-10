package model;

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

    public int getInitialAmmo() {
        return MAXAMMO;
    }
}
