package model;

import enviornment.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZombieTest {
    private Zombie testZombie;

    @BeforeEach
    void runBefore() {
        testZombie = new Zombie();
    }

    @Test
    void testConstructor() {
        assertTrue(testZombie.getAlive());
        assertTrue(testZombie.posX <= Stage.WIDTH);
        assertTrue(testZombie.posX >= 0);
        assertTrue(testZombie.posY <= Stage.HEIGHT);
        assertTrue(testZombie.posY >= 0);
    }

    @Test
    void testDie() {
        assertTrue(testZombie.getAlive());
        testZombie.die();
        assertFalse(testZombie.getAlive());
    }
}
