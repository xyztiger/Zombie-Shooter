package model;

import enviornment.Stage;
import exceptions.BorderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player testPlayer;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player();
    }

    @Test
    void testConstructor() {
        assertEquals(Stage.WIDTH / 2, testPlayer.posX);
        assertEquals(Stage.HEIGHT / 2, testPlayer.posY);
        assertNotNull(testPlayer.getWeapons());
        assertTrue(testPlayer.getCurrentWeapon() instanceof Pistol);
        assertNotNull(testPlayer.getWeaponNames());
        assertEquals("Pistol", testPlayer.getCurrentWeaponName());
        assertEquals(500, testPlayer.getCurrentWeapon().getAmmo());
    }

    @Test
    void testMove() {
        testPlayer.setDirection("N");
        try {
            testPlayer.move();
            assertEquals(Stage.WIDTH / 2, testPlayer.posX);
            assertEquals(Stage.HEIGHT / 2 - 1, testPlayer.posY);
        } catch (BorderException be) {
            fail();
        }
        testPlayer.setDirection("S");
        try {
            testPlayer.move();
            assertEquals(Stage.WIDTH / 2, testPlayer.posX);
            assertEquals(Stage.HEIGHT / 2, testPlayer.posY);
        } catch (BorderException be) {
            fail();
        }
        testPlayer.setDirection("E");
        try {
            testPlayer.move();
            assertEquals(Stage.WIDTH / 2 + 1, testPlayer.posX);
            assertEquals(Stage.HEIGHT / 2, testPlayer.posY);
        } catch (BorderException be) {
            fail();
        }
        testPlayer.setDirection("W");
        try {
            testPlayer.move();
            assertEquals(Stage.WIDTH / 2, testPlayer.posX);
            assertEquals(Stage.HEIGHT / 2, testPlayer.posY);
        } catch (BorderException be) {
            fail();
        }
        testPlayer.setPosX(0);
        try {
            testPlayer.move();
            fail();
        } catch (BorderException be) {
            assertEquals(0, testPlayer.posX);
        }
        testPlayer.setPosX(-1);
        try {
            testPlayer.move();
            fail();
        } catch (BorderException be) {
            assertEquals(0, testPlayer.posX);
        }
    }

    @Test
    void testCheckHitBorder() {
        try {
            testPlayer.checkHitBorder();
        } catch (BorderException be) {
            fail();
        }
        testPlayer.setPosX(-1);
        try {
            testPlayer.checkHitBorder();
            fail();
        } catch (BorderException ignored) {
        }
        testPlayer.setPosX(0);
        try {
            testPlayer.checkHitBorder();
        } catch (BorderException be) {
            fail();
        }
        testPlayer.setPosX(Stage.WIDTH + 1);
        try {
            testPlayer.checkHitBorder();
            fail();
        } catch (BorderException ignored) {
        }
        testPlayer.setPosX(Stage.WIDTH);
        try {
            testPlayer.checkHitBorder();
        } catch (BorderException be) {
            fail();
        }
        testPlayer.setPosY(-1);
        try {
            testPlayer.checkHitBorder();
            fail();
        } catch (BorderException ignored) {
        }
        testPlayer.setPosY(0);
        try {
            testPlayer.checkHitBorder();
        } catch (BorderException be) {
            fail();
        }
        testPlayer.setPosY(Stage.HEIGHT + 1);
        try {
            testPlayer.checkHitBorder();
            fail();
        } catch (BorderException ignored) {
        }
        testPlayer.setPosY(Stage.HEIGHT);
        try {
            testPlayer.checkHitBorder();
        } catch (BorderException be) {
            fail();
        }
    }

    @Test
    void testAddWeapons() {
        ArrayList<Weapon> testWeapons = testPlayer.getWeapons();
        ArrayList<String> testWeaponNames = testPlayer.getWeaponNames();
        assertEquals(1, testWeapons.size());
        assertEquals(1, testWeaponNames.size());
        Weapon testCurrentWeapon = testPlayer.getCurrentWeapon();
        assertTrue(testCurrentWeapon instanceof Pistol);
        assertEquals("Pistol", testPlayer.getCurrentWeaponName());
        Uzi testUzi = new Uzi();
        testPlayer.addWeapons(testUzi);
        assertEquals(2, testWeapons.size());
        assertEquals(2, testWeaponNames.size());
        assertTrue(testWeapons.contains(testUzi));
        assertTrue(testWeaponNames.contains(testUzi.getName()));
        RPG testRPG = new RPG();
        testPlayer.addWeapons(testRPG);
        assertEquals(3, testWeapons.size());
        assertEquals(3, testWeaponNames.size());
        assertTrue(testWeapons.contains(testRPG));
        assertTrue(testWeaponNames.contains(testRPG.getName()));
        testPlayer.addWeapons(testUzi);
        assertEquals(3, testWeapons.size());
        assertEquals(3, testWeaponNames.size());
    }

}