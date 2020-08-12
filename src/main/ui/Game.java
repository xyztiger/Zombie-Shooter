package ui;

import com.google.gson.stream.JsonReader;
import exceptions.BorderException;
import exceptions.NoAmmoException;
import exceptions.NotEnoughPointsException;
import model.*;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import persistence.GameState;

import javax.swing.*;

// A game where a player controls a character to move and shoot zombies
public class Game extends JFrame implements KeyListener {
    private Scanner input;
    private Player player;
    private Zombie zombie;
    private Score score;
    private Gson game;
    private GameState gameState;
    private ShopPanel shopPanel;
    private GamePanel gamePanel;
    private ChoosePanel choosePanel;
    private LoadPanel loadPanel;
    private QuitPanel quitPanel;
    private JFrame gameFrame;
    private static final String GAMES_FILE = "./data/games.json";
    private static final ArrayList<String> MOVEMENTS = new ArrayList<>(Arrays.asList("w", "a", "s", "d"));

    // EFFECTS: runs the game
    public Game() {
        homeScreen();
//        startGame();
    }

    /*
     * MODIFIES: this
     * EFFECTS: shows the main menu, spawns the player and a zombie
     *          processes user input
     */
    public void startGame() {
        boolean endGame = false;
        input = new Scanner(System.in);
//        gameState = new GameState();
//        loadGame();
        loadGameState(loadPanel.getGameState());
//        loadGame(loadPanel.choice());
//        while (!endGame) {
        initGamePanel(this);
        initKeyListener();
        displayMenu();
        String command = input.next();
        command = command.toLowerCase();
        if (command.equals("q")) {
            processQuit();
        }
        processMainScreen(command);
        if (!zombie.getAlive()) {
            zombie = new Zombie();
        }
//        }
//        System.out.println("See you next time!");
    }

    private void homeScreen() {
        loadPanel = new LoadPanel(this);
    }

    // MODIFIES: this
    // EFFECTS: loads the game state
    public void loadGameState(GameState gs) {
        this.gameState = gs;
        this.player = gameState.loadPlayer();
        this.score = gameState.loadScore();
        this.zombie = gameState.loadZombie();
    }

//    private void loadGame(String choice) {
//        try {
//            game = new GsonBuilder().setPrettyPrinting().create();
//            JsonReader reader = new JsonReader(new FileReader(GAMES_FILE));
////            System.out.println("Continue from saved game or start a new game?");
////            System.out.println("'C': Continue");
////            System.out.println("'N': New game");
//            String command = input.next();
//            command = command.toLowerCase();
//            if (choice.equals("c")) {
//                System.out.println("Loaded previous save:");
//                gameState = game.fromJson(reader, gameState.getClass());
//                reader.close();
//                player = gameState.loadPlayer();
//                zombie = gameState.loadZombie();
//                score = gameState.loadScore();
//            } else if (choice.equals("n")) {
//                init();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            init();
//        }
//    }

    // MODIFIES: this
    // EFFECTS: loads gameState from GAMES_FILE, if that file exists;
    // otherwise initializes new gameState with default values
    public void loadGame() {
        try {
            game = new GsonBuilder().setPrettyPrinting().create();
            JsonReader reader = new JsonReader(new FileReader(GAMES_FILE));
//            System.out.println("Continue from saved game or start a new game?");
//            System.out.println("'C': Continue");
//            System.out.println("'N': New game");
//            String command = input.next();
//            command = command.toLowerCase();
//            if (command.equals("c")) {
            System.out.println("Loaded previous save:");
            gameState = game.fromJson(reader, gameState.getClass());
            reader.close();
            player = gameState.loadPlayer();
            zombie = gameState.loadZombie();
            score = gameState.loadScore();
//            } else if (command.equals("n")) {
//                init();
//            }
        } catch (Exception e) {
            e.printStackTrace();
            init();
        }
    }

    //EFFECTS: initializes the game panel
    private void initGamePanel(Game panel) {
        gamePanel = new GamePanel(this);
        add(gamePanel);
        setVisible(true);
        setFocusable(true);
        requestFocusInWindow();
    }

    //EFFECTS: initializes the key listener
    private void initKeyListener() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (String.valueOf(e.getKeyChar()).equals("q")) {
                    processQuit();
                }
                processMovement(String.valueOf(e.getKeyChar()));

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates the player, a zombie, and the score
    public void init() {
        player = new Player();
        zombie = new Zombie();
        score = new Score();
//        gameState.savePlayer(player);
//        gameState.saveScore(score);
//        gameState.saveZombie(zombie);
    }

    // EFFECTS: asks player if they would like to save current game, and if they really want to quit
//    private boolean processQuit() {
//        System.out.println("Would you like to save the game before you quit?");
//        System.out.println("'S': Save and quit");
//        System.out.println("'Q': Quit without saving");
//        System.out.println("'B': Go back to game");
//        String command = input.next();
//        command = command.toLowerCase();
//        switch (command) {
//            case "q":
//                return true;
//            case "s":
//                saveGame();
//                return true;
//            case "b":
//                return false;
//        }
//        return false;
//    }

    private void processQuit() {
        quitPanel = new QuitPanel(this);
    }

    // EFFECTS: saves the current gameState to GAMES_FILE to be loaded in the future
    private void saveGame() {
        try {
            Writer writer = new FileWriter(GAMES_FILE);
            gameState.savePlayer(player);
            gameState.saveZombie(zombie);
            gameState.saveScore(score);
            game.toJson(gameState, writer);
            writer.close();
            System.out.println("Game saved to file " + GAMES_FILE);
        } catch (IOException e) {
            System.out.println("Saving failed!");
        }
    }

    // EFFECTS: shows the current score on screen
    private void showScore() {
        System.out.println("Points: " + score.getPoints());
    }

    // MODIFIES: this
    // EFFECTS: processes user input for the main screen
    private void processMainScreen(String command) {
        switch (command) {
            case "b":
                proccessShop();
                break;
            case "c":
                processChooseWeapon();
                break;
            case "i":
                displayInstructions();
//        } else if (MOVEMENTS.contains(command)) {
//            processMovement(command);
                break;
            case "j":
                processShootGun();
                break;
        }
    }

    private void proccessShop() {
        shopPanel = new ShopPanel(this);
        this.score = shopPanel.getScore();
        this.player = shopPanel.getPlayer();
//        showWeapons();
    }

    private void processChooseWeapon() {
        choosePanel = new ChoosePanel(this);
        this.player = choosePanel.getPlayer();
    }

    // MODIFIES: this
    // EFFECTS: processes movement input in the main screen for the player
    private void processMovement(String movement) {
        try {
            switch (movement) {
                case "w":
                    player.setDirection("N");
                    player.move();
                    break;
                case "a":
                    player.setDirection("W");
                    player.move();
                    break;
                case "s":
                    player.setDirection("S");
                    player.move();
                    break;
                case "d":
                    player.setDirection("E");
                    player.move();
                    break;
            }
        } catch (BorderException be) {
            System.out.println("Hit the wall of the room!");
        }
        System.out.println("Now at" + player.getPosition());
    }

    // EFFECTS: displays list of information the user needs to know
    public void displayMenu() {
        showScore();
        System.out.println("current weapon: " + player.getCurrentWeaponName());
        System.out.println("current weapon ammo: " + player.getCurrentWeapon().getAmmo());
        System.out.println("current position: " + player.getPosition());
//        System.out.println("current health: " + player.getHealth());
        System.out.println("ZOMBIE AT: " + zombie.getPosition() + "!!!");
        System.out.println("press 'I' for instructions");
    }

    // EFFECTS: displays list of instructions to play the game
    private void displayInstructions() {
        System.out.println("INSTRUCTIONS:");
        System.out.println("use 'WASD' to move");
        System.out.println("press 'J' to shoot!");
        System.out.println("press 'C' to choose a weapon");
        System.out.println("press 'B' to buy new weapons");
        System.out.println("press 'Q' to quit game");
    }



    /*
     * MODIFIES: this
     * EFFECTS: shoots the player's current weapon in the player's current direction;
     *          if a zombie is hit, the score increases by 1, the zombie dies and a new one spawns
     */
    private void processShootGun() {
        Weapon currentWeapon = player.getCurrentWeapon();
        try {
            currentWeapon.shoot();
            if (detectHit()) {
                score.increase(1);
                zombie.die();
                System.out.println("Killed a zombie! Here comes another one!");
            } else {
                System.out.println("Missed! Try again!");
            }
        } catch (NoAmmoException nae) {
            System.out.println("Current weapon out of ammo!");
        }
    }

    // EFFECTS: detects whether the player's shot hit a zombie or not
    private boolean detectHit() {
        switch (player.getDirection()) {
            case N:
                return (player.getPosX() == zombie.getPosX()) && (player.getPosY() >= zombie.getPosY());
            case E:
                return (player.getPosY() == zombie.getPosY()) && (player.getPosX() <= zombie.getPosX());
            case S:
                return (player.getPosX() == zombie.getPosX()) && (player.getPosY() <= zombie.getPosY());
            case W:
                return (player.getPosY() == zombie.getPosY()) && (player.getPosX() >= zombie.getPosX());
            default:
                return false;
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public Zombie getZombie() {
        return this.zombie;
    }

    public Score getScore() {
        return this.score;
    }

    public void setGameState(GameState gamestate) {
        this.gameState = gamestate;
    }

    public GameState getGameState() {
        return gameState;
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
