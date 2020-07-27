package model;

public class Pistol extends Weapon {
    private static final int INITIALAMMO = 500;

    public Pistol() {
        setName("Pistol");
        setAmmo(INITIALAMMO);
    }

    public int getInitialAmmo() {
        return INITIALAMMO;
    }
}
