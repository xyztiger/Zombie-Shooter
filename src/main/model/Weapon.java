package model;

import exceptions.*;

// Represents all types of weapons that the players can buy
// all weapons have their specific ammo amounts
// all weapons have specific names
// all weapons can shoot and kill zombies by consuming ammo

public abstract class Weapon {
    private int ammo;
//    private int price;
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

    public abstract int getInitialAmmo();
}
