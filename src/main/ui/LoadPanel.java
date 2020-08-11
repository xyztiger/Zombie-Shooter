package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.Player;
import model.Score;
import model.Weapon;
import model.Zombie;
import persistence.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.util.ArrayList;

public class LoadPanel extends ButtonPanel {
    private JPanel loadPanel;
    private Player player;
    private boolean ended;
    private JLabel loadLabel;
    private JFrame frame;
    private JButton loadButton = null;
    private JButton newButton = null;
    private JButton shotgunButton = null;
    private JButton rpgButton = null;
    private JButton pressed;
    private GameState gameState;
    private ArrayList<JButton> buttons;
    private Game game;
    private Gson gson;
    private static final String GAMES_FILE = "./data/games.json";
    private String choice;

    public LoadPanel(Game game) {
        gameState = new GameState();
        this.game = game;
        initChoosePanel();
    }

    public LoadPanel() {
        initChoosePanel();
    }

    private void initChoosePanel() {
        frame = new JFrame();
        loadLabel = new JLabel("Welcome to BOXHEAD ZOMBIE SHOOTER!");
        this.player = this.game.getPlayer();
        this.ended = false;

        loadPanel = new JPanel();
        loadPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        loadPanel.setLayout(new GridLayout(0, 1));

        loadPanel.add(loadLabel);
        initializeButtons(loadPanel);

        frame.add(loadPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BOXHEAD ZOMBIE SHOOTER");
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeButtons(JPanel panel) {
        loadButton = addButton("Load Previous Save");
        panel.add(loadButton);
        newButton = addButton("Start New Game!");
        panel.add(newButton);
    }

    /**
     * Invoked when an action occurs.
     *
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        pressed = (JButton) ae.getSource();
        if (pressed.equals(loadButton)) {
            try {
                gson = new GsonBuilder().setPrettyPrinting().create();
                JsonReader reader = new JsonReader(new FileReader(GAMES_FILE));
                System.out.println("Loaded previous save:");
                gameState = gson.fromJson(reader, gameState.getClass());
                reader.close();
                choice = "c";
            } catch (Exception e) {
                e.printStackTrace();
                init();
            }
        } else if (pressed.equals(newButton)) {
            init();
            System.out.println("Started new game!");
            choice = "n";
        }
        frame.dispose();
        game.loadGameState(gameState);
        game.startGame();
    }

    public GameState getGameState() {
        return gameState;
    }

    private void init() {
        gameState = new GameState();
//        gameState.savePlayer(player);
//        gameState.saveScore(score);
//        gameState.saveZombie(zombie);
    }

    public String choice() {
        return choice;
    }
}
