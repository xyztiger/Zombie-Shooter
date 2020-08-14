package persistence;

import model.Player;
import model.Score;
import model.Zombie;

// Represents the current state of the game; contains all necessary information for games to
// be saved and loaded
public class GameState {
    private Player player;
    private Zombie zombie;
    private Score score;

    // EFFECTS: creates a new game with a new player, new zombie, and score of 0
    public GameState() {
        player = new Player();
        zombie = new Zombie();
        score = new Score();
    }

    // setters and getters:
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
