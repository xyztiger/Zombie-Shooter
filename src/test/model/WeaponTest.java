package model;

import exceptions.NoAmmoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {
    private Weapon testPistol;
    private Weapon testUzi;

    @BeforeEach
    void runBefore() {
        this.testPistol = new Pistol();
        this.testUzi = new Uzi();
        }

    @Test
    void testShoot() {
        assertEquals(testPistol.getInitialAmmo(), testPistol.getAmmo());
        try {
            testPistol.shoot();
            assertEquals(testPistol.getInitialAmmo() - 1, testPistol.getAmmo());
        } catch (NoAmmoException nae) {
            fail();
        }
        testPistol.setAmmo(0);
        try {
            testPistol.shoot();
            fail();
        } catch (NoAmmoException ignored) { }
        testPistol.setAmmo(-1);
        try {
            testPistol.shoot();
            fail();
        } catch (NoAmmoException ignored) { }
    }

    @Test
    void testLoadAmmo() {
        assertEquals(testUzi.getInitialAmmo(), testUzi.getAmmo());
        testUzi.loadAmmo(50);
        assertEquals(testUzi.getInitialAmmo() + 50, testUzi.getAmmo());
    }
}
