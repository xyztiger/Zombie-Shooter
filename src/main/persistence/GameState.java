package persistence;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents the current state of the game; contains all necessary information for games to
// be saved and loaded
public class GameState {
    private Player player;
    private Zombie zombie;
    private Score score;
    private List<Zombie> zombies;

    // EFFECTS: creates a new game with a new player, new zombie, and score of 0
    public GameState() {
        player = new Player();
        zombie = new Zombie();
        score = new Score();
        zombies = new ArrayList<>();
        zombies.add(zombie);
    }

    // setters and getters:
    public void savePlayer(Player p) {
        player = p;
    }

    public void saveZombies(List<Zombie> z) {
        zombies = z;
    }

    public void saveZombie(Zombie z) {
        zombie = z;
    }

    public void saveScore(Score s) {
        score = s;
    }

    public Player loadPlayer() {
        return player;
    }

    public List<Zombie> loadZombies() {
        return zombies;
    }

    public Zombie loadZombie() {
        return zombie;
    }

    public Score loadScore() {
        return score;
    }
}
