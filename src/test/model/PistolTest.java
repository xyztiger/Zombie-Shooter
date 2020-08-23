package model;

import model.weapons.Pistol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PistolTest {
    Pistol testPistol;

    @BeforeEach
    void runBefore() {
        testPistol = new Pistol();
    }

    @Test
    void testConstructor() {
        assertEquals(testPistol.getInitialAmmo(), testPistol.getAmmo());
        assertEquals("Pistol", testPistol.getName());
    }
}
