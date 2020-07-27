package model;

import enviornment.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class EntityTest {
    Entity testPlayer;
    Entity testZombie;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player();
        testZombie = new Zombie();
    }

    @Test
    void testGetPosition() {
        assertEquals(2, testPlayer.getPosition().size());
        assertEquals(2, testZombie.getPosition().size());
        assertEquals(Stage.WIDTH / 2, testPlayer.getPosition().get(0));
        assertEquals(Stage.HEIGHT / 2, testPlayer.getPosition().get(1));
        testPlayer.setPosX(40);
        testPlayer.setPosY(60);
        assertEquals(40, testPlayer.getPosition().get(0));
        assertEquals(60, testPlayer.getPosition().get(1));
        assertTrue(testZombie.getPosition().get(0) <= Stage.WIDTH);
        assertTrue(testZombie.getPosition().get(0) >= 0);
        assertTrue(testZombie.getPosition().get(1) <= Stage.HEIGHT);
        assertTrue(testZombie.getPosition().get(1) >= 0);
    }
}
