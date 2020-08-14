package ui;

import com.google.gson.Gson;
import exceptions.BorderException;
import exceptions.NoAmmoException;
import model.Player;
import model.Score;
import model.Weapon;
import model.Zombie;
import persistence.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// A game where a player controls a character to move and shoot zombies
public class Game extends JFrame implements KeyListener {
    private Player player;
    private Zombie zombie;
    private Score score;
    private GameState gameState;
    private ShopPanel shopPanel;
    private GamePanel gamePanel;
    private ChoosePanel choosePanel;
    private LoadPanel loadPanel;
    private QuitPanel quitPanel;
    private static final int INTERVAL = 20;
    private static final String GAMES_FILE = "./data/games.json";
    private static final ArrayList<String> MOVEMENTS = new ArrayList<>(Arrays.asList("w", "a", "s", "d"));

    // EFFECTS: runs the game
    public Game() {
        super("BOXHEAD");
        homeScreen();
    }

    /*
     * MODIFIES: this
     * EFFECTS: shows the main menu, spawns the player and a zombie
     *          processes user input
     */
    public void startGame() {
        addTimer();
        loadGameState(loadPanel.getGameState());
        initGamePanel();
        initKeyListener();
        displayMenu();
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

    //EFFECTS: initializes the game panel
    private void initGamePanel() {
        gamePanel = new GamePanel(this);
        add(gamePanel, BorderLayout.CENTER);
        setFocusable(true);
        requestFocusInWindow();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    //EFFECTS: initializes the key listener
    private void initKeyListener() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                processKey(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void processKey(KeyEvent e) {
        if (String.valueOf(e.getKeyChar()).equals("q")) {
            processQuit();
        }
        processMovement(String.valueOf(e.getKeyChar()));
        if (String.valueOf(e.getKeyChar()).equals("b")) {
            proccessShop();
        }
        if (String.valueOf(e.getKeyChar()).equals("c")) {
            processChooseWeapon();
        }
        if (String.valueOf(e.getKeyChar()).equals("j")) {
            processShootGun();
        }
    }


    private void processQuit() {
        quitPanel = new QuitPanel(this);
    }

    // EFFECTS: shows the current score on screen
    private void showScore() {
        System.out.println("Points: " + score.getPoints());
    }

    private void proccessShop() {
        shopPanel = new ShopPanel(this);
        this.score = shopPanel.getScore();
        this.player = shopPanel.getPlayer();
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
    }

    // EFFECTS: displays list of information the user needs to know
    public void displayMenu() {
        showScore();
        System.out.println("current weapon: " + player.getCurrentWeaponName());
        System.out.println("current weapon ammo: " + player.getCurrentWeapon().getAmmo());
        System.out.println("current position: " + player.getPosition());
        System.out.println("ZOMBIE AT: " + zombie.getPosition() + "!!!");
        System.out.println("press 'I' for instructions");
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
                this.zombie = new Zombie();
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
                return ((zombie.getPosX() - 5) <= player.getPosX() && player.getPosX() <= (zombie.getPosX() + 5))
                        && (player.getPosY() >= zombie.getPosY());
            case E:
                return ((zombie.getPosY() - 5) <= player.getPosY() && player.getPosY() <= (zombie.getPosY() + 5))
                        && (player.getPosX() <= zombie.getPosX());
            case S:
                return ((zombie.getPosX() - 5) <= player.getPosX() && player.getPosX() <= (zombie.getPosX() + 5))
                        && (player.getPosY() <= zombie.getPosY());
            case W:
                return ((zombie.getPosY() - 5) <= player.getPosY() && player.getPosY() <= (zombie.getPosY() + 5))
                        && (player.getPosX() >= zombie.getPosX());
            default:
                return false;
        }
    }


    private void addTimer() {
        Timer t = new Timer(INTERVAL, ae -> gamePanel.repaint());

        t.start();
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
