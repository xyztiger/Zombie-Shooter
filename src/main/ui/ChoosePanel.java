package ui;

import model.Player;
import model.weapons.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// The panel in which the player can choose a weapon from their purchased weapons
public class ChoosePanel extends ButtonPanel {
    private JPanel choosePanel;
    private Player player;
    private JLabel chooseLabel;
    private JFrame frame;
    private JButton pistolButton = null;
    private JButton uziButton = null;
    private JButton shotgunButton = null;
    private JButton rpgButton = null;
    private JButton pressed;
    private Game game;

    private static final ImageIcon PISTOL_IMG = new ImageIcon("./data/images/pistol.jpg");
    private static final ImageIcon UZI_IMG = new ImageIcon("./data/images/uzi.jpg");
    private static final ImageIcon SHOTGUN_IMG = new ImageIcon("./data/images/shotgun.jpg");
    private static final ImageIcon RPG_IMG = new ImageIcon("./data/images/RPG.jpg");

    // EFFECTS: creates a new panel for player to choose weapons
    public ChoosePanel(Game game) {

        frame = new JFrame();
        chooseLabel = new JLabel("Choose your weapon!");
        this.game = game;
        this.player = this.game.getPlayer();
//        initChoosePanel();
//        choosePanel.add(chooseLabel);
        choosePanel = new JPanel();
        initPanel(choosePanel, chooseLabel);
        initializeButtons(choosePanel);

        frame.add(choosePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Choose your weapon:");
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initiates the panel
    private void initChoosePanel() {
        choosePanel = new JPanel();
        choosePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        choosePanel.setLayout(new GridLayout(0, 1));
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons
    private void initializeButtons(JPanel panel) {
        for (Weapon w : player.getWeapons().values()) {
            switch (w.getName()) {
                case ("Pistol"):
                    pistolButton = addButton("Pistol: " + w.getAmmo(), PISTOL_IMG);
                    panel.add(pistolButton);
                    break;
                case ("Uzi"):
                    uziButton = addButton("Uzi: " + w.getAmmo(), UZI_IMG);
                    panel.add(uziButton);
                    break;
                case ("Shotgun"):
                    shotgunButton = addButton("Shotgun: " + w.getAmmo(), SHOTGUN_IMG);
                    panel.add(shotgunButton);
                    break;
                case ("RPG"):
                    rpgButton = addButton("RPG: " + w.getAmmo(), RPG_IMG);
                    panel.add(rpgButton);
                    break;
            }
        }

    }

    /**
     * Invoked when an action occurs.
     */
    // EFFECTS: processes user input
    @Override
    public void actionPerformed(ActionEvent e) {
        pressed = (JButton) e.getSource();
        String pressedText = pressed.getText();
        if (pressedText.contains("Pistol")) {
            setWeapon("Pistol");
        } else if (pressedText.contains("Uzi")) {
            setWeapon("Uzi");
        } else if (pressedText.contains("Shotgun")) {
            setWeapon("Shotgun");
        } else if (pressedText.contains("RPG")) {
            setWeapon("RPG");
        }
        frame.dispose();
        game.displayMenu();
    }

    // MODIFIES: this
    // EFFECTS: changes the player's current weapon
    private void setWeapon(String name) {
        for (Weapon w : player.getWeapons().values()) {
            if (w.getName().equals(name)) {
                player.setCurrentWeapon(w);
                System.out.println("Now using the " + w.getName() + " !");
            }
        }
    }

    public Player getPlayer() {
        return this.player;
    }
}
