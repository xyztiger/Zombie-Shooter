package ui;

import com.google.gson.stream.JsonReader;
import exceptions.BorderException;
import exceptions.NoAmmoException;
import exceptions.NotEnoughPointsException;
import model.*;

import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import persistence.GameState;

// A game where a player controls a character to move and shoot zombies
public class Game {
    private Scanner input;
    private Player player;
    private Zombie zombie;
    private Score score;
    private Gson game;
    private GameState gameState;
    private ShopPanel shopPanel;
    private static final String GAMES_FILE = "./data/games.json";
    private static final ArrayList<String> MOVEMENTS = new ArrayList<>(Arrays.asList("w", "a", "s", "d"));

    // EFFECTS: runs the game
    public Game() {
        startGame();
    }

    /*
     * MODIFIES: this
     * EFFECTS: shows the main menu, spawns the player and a zombie
     *          processes user input
     */
    private void startGame() {
        boolean endGame = false;
        input = new Scanner(System.in);
        gameState = new GameState();
        loadGame();
        while (!endGame) {
            displayMenu();
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                endGame = processQuit();
            }
            processMainScreen(command);
            if (!zombie.getAlive()) {
                zombie = new Zombie();
            }
        }
        System.out.println("See you next time!");
    }

    // MODIFIES: this
    // EFFECTS: creates the player, a zombie, and the score
    private void init() {
        System.out.println("Started new game!");
        player = new Player();
        zombie = new Zombie();
        score = new Score();
//        gameState.savePlayer(player);
//        gameState.saveScore(score);
//        gameState.saveZombie(zombie);
    }

    // EFFECTS: asks player if they would like to save current game, and if they really want to quit
    private boolean processQuit() {
        System.out.println("Would you like to save the game before you quit?");
        System.out.println("'S': Save and quit");
        System.out.println("'Q': Quit without saving");
        System.out.println("'B': Go back to game");
        String command = input.next();
        command = command.toLowerCase();
        switch (command) {
            case "q":
                return true;
            case "s":
                saveGame();
                return true;
            case "b":
                return false;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: loads gameState from GAMES_FILE, if that file exists;
    // otherwise initializes new gameState with default values
    private void loadGame() {
        try {
            game = new GsonBuilder().setPrettyPrinting().create();
            JsonReader reader = new JsonReader(new FileReader(GAMES_FILE));
            System.out.println("Continue from saved game or start a new game?");
            System.out.println("'C': Continue");
            System.out.println("'N': New game");
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("c")) {
                System.out.println("Loaded previous save:");
                gameState = game.fromJson(reader, gameState.getClass());
                reader.close();
                player = gameState.loadPlayer();
                zombie = gameState.loadZombie();
                score = gameState.loadScore();
            } else if (command.equals("n")) {
                init();
            }
        } catch (Exception e) {
            e.printStackTrace();
            init();
        }
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
        if (command.equals("b")) {
            proccessShop();
        } else if (command.equals("c")) {
            showWeapons();
        } else if (command.equals("i")) {
            displayInstructions();
        } else if (MOVEMENTS.contains(command)) {
            processMovement(command);
        } else if (command.equals("j")) {
            processShootGun();
        }
    }

    private void proccessShop() {
        shopPanel = new ShopPanel(score, player);
        this.score = shopPanel.getScore();
        this.player = shopPanel.getPlayer();
        showWeapons();
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
    private void displayMenu() {
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
     * EFFECTS: displays the shop with available guns the player can purchase with points
     *         if a player purchases a gun that they do not already have,
     *         the gun is added to the player's list of available weapons
     *         if the player already has the gun, ammo is added instead
     *
     *         if the player does not have enough points, nothing is bought and a message is thrown
     */
    private void showShop() {
        String command = "";
//        shopPanel = new ShopPanel(score);

        while (!command.equals("q")) {
            command = shopPanel.getPressed().toLowerCase();
            try {
                processBuyGun(command);
            } catch (NotEnoughPointsException nepe) {
                System.out.println("Not enough points! Kill zombies to get more points!");
            }
        }

//        while (!command.equals("q")) {
//            showScore();
//            System.out.println("Current weapons:");
//            HashMap<Integer, Weapon> weapons = player.getWeapons();
//            for (Weapon w : weapons.values()) {
//                System.out.println(w.getName() + ": " + w.getAmmo());
//            }
//            System.out.println("press 'U' to buy Uzi/ammo");
//            System.out.println("press 'S' to buy Shotgun/ammo");
//            System.out.println("press 'R' to buy RPG/ammo");
//            System.out.println("press 'Q' to exit buy gun menu");
//
//            command = input.next();
//            command = command.toLowerCase();
//
//            try {
//                processBuyGun(command);
//            } catch (NotEnoughPointsException nepe) {
//                System.out.println("Not enough points! Kill zombies to get more points!");
//            }
//        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input and adds the selected gun to the player's list of available guns
    private void processBuyGun(String choice) throws NotEnoughPointsException {
        Pistol pistol = new Pistol();
        Uzi uzi = new Uzi();
        RPG rpg = new RPG();
        Shotgun shotgun = new Shotgun();
        switch (choice) {
            case "p":
                score.spend(pistol.getCost());
                player.addWeapons(pistol);
                break;
            case "u":
                score.spend(uzi.getCost());
                player.addWeapons(uzi);
                break;
            case "r":
                score.spend(rpg.getCost());
                player.addWeapons(rpg);
                break;
            case "s":
                score.spend(shotgun.getCost());
                player.addWeapons(shotgun);
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: shows the list of available guns, prompts the player to choose an available gun
    private void showWeapons() {
        int index = 0;
        String command = "";

        while (command.equals("")) {
            System.out.println("Press number keys to select weapon:");
            HashMap<Integer, Weapon> weapons = player.getWeapons();
            for (Weapon w : weapons.values()) {
                index++;
                System.out.println(index + ": " + w.getName());
            }

            command = input.next();
            command = command.toLowerCase();

            if (!processChooseWeapon(command)) {
                showWeapons();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input to change the player's currently selected weapon
    private boolean processChooseWeapon(String choice) {
        if (checkValidIndex(choice)) {
            HashMap<Integer, Weapon> weapons = player.getWeapons();
            switch (choice) {
                case "1":
                    player.setCurrentWeapon(weapons.get(1));
                    break;
                case "2":
                    player.setCurrentWeapon(weapons.get(2));
                    break;
                case "3":
                    player.setCurrentWeapon(weapons.get(3));
                    break;
                case "4":
                    player.setCurrentWeapon(weapons.get(4));
                    break;
            }
            System.out.println("Now using the " + player.getCurrentWeaponName() + "!");
            return true;
        }
        System.out.println("No weapon at that index! Please choose from one of the weapons below:");
        return false;
    }

    // EFFECTS: checks if the user's input for selecting an available gun was valid
    private boolean checkValidIndex(String choice) {
        ArrayList<String> indexes = new ArrayList<>();
        int index = 0;
        while (index < player.getWeapons().size()) {
            index++;
            indexes.add(Integer.toString(index));
        }
        return indexes.contains(choice);
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

}
