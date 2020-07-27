package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UziTest {
    Uzi testUzi;
    
    @BeforeEach
    void runBefore() {
        testUzi = new Uzi();
    }
    
    @Test
    void testConstructor() {
        assertEquals(testUzi.getInitialAmmo(), testUzi.getAmmo());
        assertEquals("Uzi", testUzi.getName());
    }
}
