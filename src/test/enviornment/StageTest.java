package enviornment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StageTest {
    Stage testStage;

    @Test
    void testConstructor() {
        testStage  = new Stage();
        assertEquals(400, testStage.WIDTH);
        assertEquals(400, testStage.HEIGHT);
    }
}
