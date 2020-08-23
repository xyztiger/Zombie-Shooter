package model;

import model.weapons.RPG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPGTest {
    RPG testRPG;

    @BeforeEach
    void runBefore() {
        testRPG = new RPG();
    }

    @Test
    void testConstructor() {
        assertEquals(testRPG.getInitialAmmo(), testRPG.getAmmo());
        assertEquals("RPG", testRPG.getName());
    }
}
