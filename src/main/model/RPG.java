package model;

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
