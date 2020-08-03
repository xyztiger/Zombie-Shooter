package persistence;

import model.*;

import java.util.Scanner;

public class GameState {
    private Player player;
    private Zombie zombie;
    private Score score;

    public GameState() {
        player = new Player();
        zombie = new Zombie();
        score = new Score();
    }

    public void savePlayer(Player p) {
        player = p;
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

    public Zombie loadZombie() {
        return zombie;
    }

    public Score loadScore() {
        return score;
    }
}
