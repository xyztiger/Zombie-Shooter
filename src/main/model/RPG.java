package model;

// A specific type of weapon which the player can buy; very low ammo capacity
public class RPG extends Weapon {
    private static final int INITIALAMMO = 500;

    public RPG() {
        setName("RPG");
        setAmmo(INITIALAMMO);
    }

    public int getInitialAmmo() {
        return INITIALAMMO;
    }
}
