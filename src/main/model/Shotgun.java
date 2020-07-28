package model;

// A specific type of weapon which the player can buy; low ammo capacity
public class Shotgun extends Weapon {
    private static final int INITIALAMMO = 50;

    public Shotgun() {
        setName("Shotgun");
        setAmmo(INITIALAMMO);
    }

    public int getInitialAmmo() {
        return INITIALAMMO;
    }
}
