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

// Panel for allowing the user to choose between starting a new game or loading a save
public class LoadPanel extends ButtonPanel {
    private JPanel loadPanel;
    private JLabel loadLabel;
    private JFrame frame;
    private JButton loadButton = null;
    private JButton newButton = null;
    private JButton pressed;
    private GameState gameState;
    private Game game;
    private Gson gson;
    private static final String GAMES_FILE = "./data/games.json";

    // EFFECTS: Creates a new load game panel
    public LoadPanel(Game game) {
        gameState = new GameState();
        this.game = game;
        initLoadPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes the panel and labels
    private void initLoadPanel() {
        frame = new JFrame();
        loadLabel = new JLabel("Welcome to BOXHEAD ZOMBIE SHOOTER!");
        loadPanel = new JPanel();
        initPanel(loadPanel, loadLabel);
        initializeButtons(loadPanel);

        frame.add(loadPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BOXHEAD ZOMBIE SHOOTER");
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the load game/new game buttons
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
    // MODIFIES: this
    // EFFECTS: processes user input
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (pressed.equals(newButton)) {
            System.out.println("Started new game!");
        }
        frame.dispose();
        game.startGame();
    }

    public GameState getGameState() {
        return gameState;
    }

}
