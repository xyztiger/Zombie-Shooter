package model;

import exceptions.NoAmmoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeaponTest {
    private Weapon testPistol1;
    private Weapon testUzi;
    private Weapon testPistol2;
    private Weapon testWeapon1;
    private Weapon testWeapon2;

    @BeforeEach
    void runBefore() {
        this.testPistol1 = new Pistol();
        this.testPistol2 = new Pistol();
        this.testUzi = new Uzi();
        this.testWeapon1 = new Weapon();
        this.testWeapon2 = new Weapon();
        }

    @Test
    void testShoot() {
        assertEquals(testPistol1.getInitialAmmo(), testPistol1.getAmmo());
        try {
            testPistol1.shoot();
            assertEquals(testPistol1.getInitialAmmo() - 1, testPistol1.getAmmo());
        } catch (NoAmmoException nae) {
            fail();
        }
        testPistol1.setAmmo(0);
        try {
            testPistol1.shoot();
            fail();
        } catch (NoAmmoException ignored) { }
        testPistol1.setAmmo(-1);
        try {
            testPistol1.shoot();
            fail();
        } catch (NoAmmoException ignored) { }
    }

    @Test
    void testLoadAmmo() {
        assertEquals(testUzi.getInitialAmmo(), testUzi.getAmmo());
        testUzi.loadAmmo(50);
        assertEquals(testUzi.getInitialAmmo() + 50, testUzi.getAmmo());
    }

    @Test
    void testEqualsAndHashCode() {
        assertTrue(testPistol1.equals(testPistol2));
        assertTrue(testPistol1.equals(testPistol1));
        assertEquals(testPistol1.hashCode(), testPistol2.hashCode());
        assertFalse(testPistol1.equals(testUzi));
        assertNotEquals(testPistol1.hashCode(), testUzi.hashCode());
        assertTrue(testUzi.equals(testUzi));
        assertFalse(testUzi.equals(testPistol2));
        assertTrue(testWeapon1.equals(testWeapon2));
        assertEquals(testWeapon1.hashCode(), testWeapon2.hashCode());
    }

    @Test
    void testGetCost() {
        assertEquals(testPistol1.getCost(), 10);
        assertEquals(testPistol2.getCost(), 10);
        assertEquals(testUzi.getCost(), 20);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testWeapon1.getInitialAmmo());
        assertEquals(0, testWeapon1.getCost());
        assertEquals("Weapon", testWeapon2.getName());
    }
}
