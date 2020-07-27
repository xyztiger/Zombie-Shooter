package ui;

import exceptions.BorderException;
import exceptions.InvalidIndexException;
import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Scanner input;
    private Player player;
    private Zombie zombie;
    private static final ArrayList<String> MOVEMENTS = new ArrayList<>(Arrays.asList("w", "a", "s", "d"));

//    private enum Direction {
//        N, E, S, W
//    }

    public Game() {
        startGame();
    }

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

    private void init() {
        player = new Player();
        zombie = new Zombie();
    }

//    private void processMainScreen(String command) {
//        switch (command) {
////            case "q":
////                setToFalse(endGame);
////                break;
//            case "b":
//                showShop();
//                break;
//            case "c":
//                showWeapons();
//                break;
//            case "i":
//                displayInstructions();
//                break;
//            default:
//                processMovement(command);
//                break;
//        }
//    }

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

    private void displayMenu() {
        System.out.println("current weapon: " + player.getCurrentWeaponName());
        System.out.println("current weapon ammo: " + player.getCurrentWeapon().getAmmo());
        System.out.println("current position: " + player.getPosition());
//        System.out.println("current health: " + player.getHealth());
        System.out.println("ZOMBIE AT: " + zombie.getPosition() + "!!!");
        System.out.println("press 'I' for instructions");
    }

    private void displayInstructions() {
        System.out.println("INSTRUCTIONS:");
        System.out.println("use 'WASD' to move");
        System.out.println("press 'C' to choose a weapon");
        System.out.println("press 'B' to buy new weapons");
        System.out.println("press 'Q' to quit game");

    }

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

    //    private void processBuyGun(String choice) {
//        ArrayList<String> weaponNames = player.getWeaponNames();
//        Uzi uzi = new Uzi();
//        RPG rpg = new RPG();
//        Shotgun shotgun = new Shotgun();
//        switch (choice) {
//            case "u":
//                if (!weaponNames.contains(uzi.getName())) {
//                    player.addWeapons(uzi);
//
//                }
//                break;
//            case "r":
//                if (!weaponNames.contains(rpg.getName())) {
//                    player.addWeapons(rpg);
//
//                }
//                break;
//            case "s":
//                if (!weaponNames.contains(shotgun.getName())) {
//                    player.addWeapons(shotgun);
//
//                }
//                break;
//        }
//    }
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

            try {
                processChooseWeapon(command);
            } catch (InvalidIndexException e) {
                System.out.println("No weapon at that index! Please choose from one of the weapons below:");
                showWeapons();
            }
        }
    }

    private void processChooseWeapon(String choice) throws InvalidIndexException {
        checkValidIndex(choice);
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
    }

    private void checkValidIndex(String choice) throws InvalidIndexException {
        ArrayList<String> indexes = new ArrayList<>();
        int index = 0;
        while (index < player.getWeapons().size()) {
            index++;
            indexes.add(Integer.toString(index));
        }
        if (!indexes.contains(choice)) {
            throw new InvalidIndexException();
        }
    }

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
