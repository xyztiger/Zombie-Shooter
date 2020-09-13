package model;

import enviornment.Stage;
import exceptions.BorderException;
import model.weapons.Pistol;
import model.weapons.RPG;
import model.weapons.Uzi;
import model.weapons.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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
        assertEquals("Pistol", testPlayer.getCurrentWeaponName());
        assertEquals(500, testPlayer.getCurrentWeapon().getAmmo());
    }

    @Test
    void testMove() {
        testPlayer.setDirection("N");
        try {
            testPlayer.move();
            assertEquals(Stage.WIDTH / 2, testPlayer.posX);
            assertEquals(Stage.HEIGHT / 2 - 5, testPlayer.posY);
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
            assertEquals(Stage.WIDTH / 2 + 5, testPlayer.posX);
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
        HashMap<Integer, Weapon> testWeapons = testPlayer.getWeapons();
        assertEquals(1, testWeapons.size());
        Weapon testCurrentWeapon = testPlayer.getCurrentWeapon();
        assertTrue(testCurrentWeapon instanceof Pistol);
        assertEquals("Pistol", testPlayer.getCurrentWeaponName());
        Uzi testUzi = new Uzi();
        testPlayer.addWeapons(testUzi);
        assertEquals(2, testWeapons.size());
        assertTrue(testWeapons.containsValue(testUzi));
        RPG testRPG = new RPG();
        testPlayer.addWeapons(testRPG);
        assertEquals(3, testWeapons.size());
        assertTrue(testWeapons.containsValue(testRPG));
        Uzi testUzi2 = new Uzi();
        testPlayer.addWeapons(testUzi2);
        assertEquals(3, testWeapons.size());
    }

    @Test
    void testGetDirection() {
        testPlayer.setDirection("N");
        assertEquals(testPlayer.getDirection(), Direction.N);
        assertNotEquals(testPlayer.getDirection(), Direction.S);
        testPlayer.setDirection("W");
        assertNotEquals(testPlayer.getDirection(), Direction.N);
        assertEquals(testPlayer.getDirection(), Direction.W);
        testPlayer.setDirection("E");
        assertNotEquals(testPlayer.getDirection(), Direction.S);
        assertEquals(testPlayer.getDirection(), Direction.E);
        testPlayer.setDirection("S");
        assertNotEquals(testPlayer.getDirection(), Direction.N);
        assertEquals(testPlayer.getDirection(), Direction.S);
    }

    @Test
    void testSetCurrentWeapon() {
        assertTrue(testPlayer.getCurrentWeapon() instanceof Pistol);
        assertFalse(testPlayer.getCurrentWeapon() instanceof Uzi);
        assertFalse(testPlayer.getCurrentWeapon() instanceof RPG);
        Uzi testUzi = new Uzi();
        testPlayer.setCurrentWeapon(testUzi);
        assertFalse(testPlayer.getCurrentWeapon() instanceof Pistol);
        assertTrue(testPlayer.getCurrentWeapon() instanceof Uzi);
        assertFalse(testPlayer.getCurrentWeapon() instanceof RPG);
    }

}