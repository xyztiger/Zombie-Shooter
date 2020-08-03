package model;

import exceptions.NotEnoughPointsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ScoreTest {
    Score testScore;

    @BeforeEach
    void runBefore() {
        testScore = new Score();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testScore.getPoints());
    }

    @Test
    void testIncreaseAndSpend() {
        testScore.increase(5);
        assertEquals(5, testScore.getPoints());
        try {
            testScore.spend(2);
            assertEquals(3, testScore.getPoints());
        } catch (NotEnoughPointsException nepe) {
            fail("Should not have thrown exception");
        }
        try {
            testScore.spend(10);
            fail("Should have thrown exception");
        } catch (NotEnoughPointsException nepe) {
            assertEquals(3, testScore.getPoints());
        }
    }
}
