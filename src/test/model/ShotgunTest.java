package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShotgunTest {
    Shotgun testShotgun;

    @BeforeEach
    void runBefore() {
        testShotgun = new Shotgun();
    }

    @Test
    void testConstructor() {
        assertEquals(testShotgun.getInitialAmmo(), testShotgun.getAmmo());
        assertEquals("Shotgun", testShotgun.getName());
    }
}
