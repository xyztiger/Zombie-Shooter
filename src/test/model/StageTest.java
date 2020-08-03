package model;

import enviornment.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StageTest {
    @Test
    void testConstants() {
        assertEquals(20, Stage.WIDTH);
        assertEquals(20, Stage.HEIGHT);
    }
}
