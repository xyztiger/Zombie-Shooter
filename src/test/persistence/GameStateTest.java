package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {
    private GameState testGameState;
    private Player testPlayer;
    private Zombie testZombie;
    private Score testScore;
    private Player testPlayerLoad;
    private Zombie testZombieLoad;
    private Score testScoreLoad;
    private Gson game;
    private static final String GAMES_FILE = "./data/testGameState1.json";

    @BeforeEach
    void runBefore() {
        testGameState = new GameState();
    }

    @Test
    void testConstructorAndLoad() {
        try {

            game = new GsonBuilder().setPrettyPrinting().create();
            JsonReader reader = new JsonReader(new FileReader(GAMES_FILE));
            testGameState = game.fromJson(reader, testGameState.getClass());
            reader.close();
            testPlayer = testGameState.loadPlayer();
            testZombie = testGameState.loadZombie();
            testScore = testGameState.loadScore();

            assertEquals(Player.Direction.S, testPlayer.getDirection());
            assertEquals(8, testPlayer.getPosX());
            assertEquals(5, testPlayer.getPosY());
            HashMap<Integer, Weapon> testWeapons = testPlayer.getWeapons();
            assertEquals(2, testWeapons.size());
            Weapon testCurrentWeapon = testPlayer.getCurrentWeapon();
            assertEquals(490, testCurrentWeapon.getAmmo());
            assertTrue(testZombie.getAlive());
            assertEquals(12, testZombie.getPosX());
            assertEquals(13, testZombie.getPosY());
            assertEquals(6, testScore.getPoints());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail("Should not have thrown exception");
        }
    }

    @Test
    void testSave() {
        testPlayer = new Player();
        RPG testPRG = new RPG();
        testPRG.setAmmo(20);
        testPlayer.addWeapons(testPRG);
        testPlayer.setCurrentWeapon(testPRG);
        testPlayer.setDirection("E");
        testPlayer.setPosX(2);
        testPlayer.setPosY(3);

        testZombie = new Zombie();

        testScore = new Score();
        testScore.increase(20);

        testGameState.savePlayer(testPlayer);
        testGameState.saveZombie(testZombie);
        testGameState.saveScore(testScore);

        testPlayerLoad = testGameState.loadPlayer();
        testZombieLoad = testGameState.loadZombie();
        testScoreLoad = testGameState.loadScore();

        assertEquals(testPRG, testPlayerLoad.getCurrentWeapon());
        assertEquals(2, testPlayerLoad.getPosX());
        assertEquals(3, testPlayerLoad.getPosY());
        assertEquals(Player.Direction.E, testPlayerLoad.getDirection());
        assertEquals(20, testPlayerLoad.getCurrentWeapon().getAmmo());

        assertTrue(testZombie.getAlive());

        assertEquals(20, testScoreLoad.getPoints());
    }
}
