package model;

import exceptions.*;

public abstract class Weapon {
    private int ammo;
    private int price;
    private String name;

    public void shoot() throws NoAmmoException {
        if (ammo > 0) {
            ammo -= 1;
        } else {
            throw new NoAmmoException();
        }
    }

    public void loadAmmo(int amount) {
        ammo += amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public void setAmmo(Integer amount) {
        ammo = amount;
    }

    public int getAmmo() {
        return ammo;
    }
}
