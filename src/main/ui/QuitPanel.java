package ui;

import com.google.gson.Gson;
import model.Player;
import model.Score;
import model.Zombie;
import persistence.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

// Panel which appears when user wants to quit; allows user to quit, save, or go back to game
public class QuitPanel extends ButtonPanel {
    private JPanel quitPanel;
    private Player player;
    private Zombie zombie;
    private Score score;
    private JLabel quitLabel;
    private JFrame frame;
    private JButton quitButton = null;
    private JButton saveButton = null;
    private JButton backButton = null;
    private JButton pressed;
    private GameState gameState;
    private Gson gson;
    private static final String GAMES_FILE = "./data/games.json";

    // EFFECTS: creates the quit panel
    public QuitPanel(Game game) {
        this.player = game.getPlayer();
        this.zombie = game.getZombie();
        this.score = game.getScore();
        frame = new JFrame();
        quitLabel = new JLabel("Exit the game?");
        quitPanel = new JPanel();
        initPanel(quitPanel, quitLabel);
        initializeButtons(quitPanel);

        frame.add(quitPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BOXHEAD ZOMBIE SHOOTER");
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates the buttons which allow the player to save, quit, or go back
    private void initializeButtons(JPanel panel) {
        saveButton = addButton("Save and Exit");
        panel.add(saveButton);
        quitButton = addButton("Exit Without Saving");
        panel.add(quitButton);
        backButton = addButton("Back to Game");
        panel.add(backButton);
    }

    /**
     * Invoked when an action occurs.
     *
     */
    // MODIFIES: the save filed
    // EFFECTS: processes user input to save or quit the game
    @Override
    public void actionPerformed(ActionEvent ae) {
        pressed = (JButton) ae.getSource();
        if (pressed.equals(saveButton)) {
            try {
                Writer writer = new FileWriter(GAMES_FILE);
                gameState = new GameState();
                gameState.savePlayer(player);
                gameState.saveZombie(zombie);
                gameState.saveScore(score);
                gson = new Gson();
                gson.toJson(gameState, writer);
                writer.close();
                System.out.println("Game saved to file " + GAMES_FILE);
                System.exit(0);
            } catch (IOException e) {
                System.out.println("Saving failed!");
            }
        } else if (pressed.equals(quitButton)) {
            System.exit(0);
        } else if (pressed.equals(backButton)) {
            frame.dispose();
        }
    }
}
