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

public class QuitPanel extends ButtonPanel {
    private JPanel quitPanel;
    private Player player;
    private Zombie zombie;
    private Score score;
    private boolean ended;
    private JLabel quitLabel;
    private JFrame frame;
    private JButton quitButton = null;
    private JButton saveButton = null;
    private JButton backButton = null;
    private JButton pressed;
    private ArrayList<JButton> buttons;
    private Game game;
    private GameState gameState;
    private Gson gson;
    private static final String GAMES_FILE = "./data/games.json";

    public QuitPanel(Game game) {
        this.game = game;
        this.player = game.getPlayer();
        this.zombie = game.getZombie();
        this.score = game.getScore();
        frame = new JFrame();
        quitLabel = new JLabel("Exit the game?");
        quitPanel = new JPanel();
        quitPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        quitPanel.setLayout(new GridLayout(0, 1));

        quitPanel.add(quitLabel);
        initializeButtons(quitPanel);

        frame.add(quitPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BOXHEAD ZOMBIE SHOOTER");
        frame.pack();
        frame.setVisible(true);
    }

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
