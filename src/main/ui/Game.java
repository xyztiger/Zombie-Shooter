package ui;

import exceptions.BorderException;
import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// A game where a player controls a character to move and shoot zombies
public class Game {
    private Scanner input;
    private Player player;
    private Zombie zombie;
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
        init();
        while (!endGame) {
            displayMenu();
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                endGame = true;
            }
            processMainScreen(command);
            if (!zombie.getAlive()) {
                zombie = new Zombie();
            }
        }
        System.out.println("See you next time!");
    }

    // MODIFIES: this
    // EFFECTS: creates the player and a zombie
    private void init() {
        player = new Player();
        zombie = new Zombie();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for the main screen
    private void processMainScreen(String command) {
        if (command.equals("b")) {
            showShop();
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

    // MODIFIES: this
    // EFFECTS: proccesses movement input in the main screen for the player
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
        System.out.println("press 'C' to choose a weapon");
        System.out.println("press 'B' to buy new weapons");
        System.out.println("press 'Q' to quit game");

    }

    /*
     * MODIFIES: this
     * EFFECTS: displays the shop with available guns the player can purchase
     *         if a player purchases a gun that they do not already have,
     *         the gun is added to the player's list of available weapons
     */
    private void showShop() {
        String command = "";

        while (!command.equals("q")) {
            System.out.println("Current weapons:");
            for (Weapon w : player.getWeapons()) {
                System.out.println(w.getName() + ": " + w.getAmmo());
            }
            System.out.println("press 'U' to buy Uzi");
            System.out.println("press 'S' to buy Shotgun");
            System.out.println("press 'R' to buy RPG");
            System.out.println("press 'Q' to exit buy gun menu");

            command = input.next();
            command = command.toLowerCase();

            processBuyGun(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input and adds the selected gun to the player's list of available guns
    private void processBuyGun(String choice) {
        ArrayList<String> weaponNames = player.getWeaponNames();
        Uzi uzi = new Uzi();
        RPG rpg = new RPG();
        Shotgun shotgun = new Shotgun();
        switch (choice) {
            case "u":
                player.addWeapons(uzi);
                break;
            case "r":
                player.addWeapons(rpg);
                break;
            case "s":
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
            for (String weapon : player.getWeaponNames()) {
                index++;
                System.out.println(index + ": " + weapon);
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
            ArrayList<Weapon> weapons = player.getWeapons();
            switch (choice) {
                case "1":
                    player.setCurrentWeapon(weapons.get(0));
                    break;
                case "2":
                    player.setCurrentWeapon(weapons.get(1));
                    break;
                case "3":
                    player.setCurrentWeapon(weapons.get(2));
                    break;
                case "4":
                    player.setCurrentWeapon(weapons.get(3));
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
     *          if a zombie is hit, the zombie dies and a new one spawns
     */
    private void processShootGun() {
        Weapon currentWeapon = player.getCurrentWeapon();
        try {
            currentWeapon.shoot();
            if (detectHit()) {
                zombie.die();
                System.out.println("Killed a zombie! Here comes another one!");
            } else {
                System.out.println("Missed! Try again!");
            }
        } catch (Exception nae) {
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

}
