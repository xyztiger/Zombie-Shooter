package model;

public class Uzi extends Weapon {
    private static final int INITIALAMMO = 100;

    public Uzi() {
        setName("Uzi");
        setAmmo(INITIALAMMO);
    }

    public int getInitialAmmo() {
        return INITIALAMMO;
    }
}
