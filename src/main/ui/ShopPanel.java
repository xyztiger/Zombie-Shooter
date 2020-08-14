package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import exceptions.NotEnoughPointsException;
import model.*;

// Panel for the weapon shop; allows player to purchase new weapons
public class ShopPanel extends ButtonPanel {

    private JLabel shopLabel;
    private JLabel scoreLabel;
    private JFrame frame;
    private JPanel shopPanel;
    private JButton buyPistol;
    private JButton buyUzi;
    private JButton buyShotgun;
    private JButton buyRPG;
    private JButton quit;
    private JButton pressed;
    private Score score;
    private Player player;
    private Game game;

    private static final ImageIcon PISTOL_IMG = new ImageIcon("./data/images/pistol.jpg");
    private static final ImageIcon UZI_IMG = new ImageIcon("./data/images/uzi.jpg");
    private static final ImageIcon SHOTGUN_IMG = new ImageIcon("./data/images/shotgun.jpg");
    private static final ImageIcon RPG_IMG = new ImageIcon("./data/images/RPG.jpg");


    // EFFECTS: constructs new shop panel
    public ShopPanel(Game game) {

        frame = new JFrame();
        shopLabel = new JLabel("Click to buy new weapons/refill ammo!");
        this.game = game;
        this.score = this.game.getScore();
        this.player = this.game.getPlayer();
        scoreLabel = new JLabel("Available points: " + this.score.getPoints());
        shopPanel = new JPanel();
        initPanel(shopPanel, shopLabel);
        shopPanel.add(scoreLabel);
        initializeButtons(shopPanel);

        frame.add(shopPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Weapon Shop");
        frame.pack();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons for the panel
    private void initializeButtons(JPanel panel) {
        buyPistol = addButton("Buy pistol/ammo: " + Pistol.COST, PISTOL_IMG);
        buyUzi = addButton("Buy uzi/ammo: " + Uzi.COST, UZI_IMG);
        buyShotgun = addButton("Buy shotgun/ammo: " + Shotgun.COST, SHOTGUN_IMG);
        buyRPG = addButton("Buy RPG/ammo: " + RPG.COST, RPG_IMG);
        quit = addButton("Back to game", null);

        panel.add(buyPistol);
        panel.add(buyShotgun);
        panel.add(buyUzi);
        panel.add(buyRPG);
        panel.add(quit);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    @Override
    public void actionPerformed(ActionEvent e) {
        pressed = (JButton) e.getSource();
        try {
            if (buyPistol.equals(pressed)) {
                this.score.spend(Pistol.COST);
                this.player.addWeapons(new Pistol());
            } else if (buyUzi.equals(pressed)) {
                this.score.spend(Uzi.COST);
                this.player.addWeapons(new Uzi());
            } else if (buyShotgun.equals(pressed)) {
                this.score.spend(Shotgun.COST);
                this.player.addWeapons(new Shotgun());
            } else if (buyRPG.equals(pressed)) {
                this.score.spend(RPG.COST);
                this.player.addWeapons(new RPG());
            } else if (quit.equals(pressed)) {
                System.out.println("Thanks for shopping!");
                game.displayMenu();
                frame.dispose();
            }
            this.scoreLabel.setText("Available points: " + score.getPoints());
        } catch (NotEnoughPointsException nepe) {
            this.scoreLabel.setText("<html>Not enough points! <br/>Available points: " + score.getPoints() + "</html>");
        }
    }


    public Player getPlayer() {
        return this.player;
    }

    public Score getScore() {
        return this.score;
    }

}
