package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import exceptions.NotEnoughPointsException;
import model.*;

public class ShopPanel implements ActionListener {

    private JLabel shopLabel;
    private JLabel scoreLabel;
    private JFrame frame;
    private JPanel panel;
    private JButton buyPistol;
    private JButton buyUzi;
    private JButton buyShotgun;
    private JButton buyRPG;
    private JButton quit;
    private JButton pressed;
    private Score score;
    private Player player;
    private boolean ended;

    private static final ImageIcon PISTOL_IMG = new ImageIcon("./data/images/pistol.jpg");
    private static final ImageIcon UZI_IMG = new ImageIcon("./data/images/uzi.jpg");
    private static final ImageIcon SHOTGUN_IMG = new ImageIcon("./data/images/shotgun.jpg");
    private static final ImageIcon RPG_IMG = new ImageIcon("./data/images/RPG.jpg");


    public ShopPanel(Score score, Player player) {

        frame = new JFrame();
        shopLabel = new JLabel("Click to buy new weapons/refill ammo!");
        this.score = score;
        this.player = player;
        this.ended = false;
        scoreLabel = new JLabel("Available points: " + this.score.getPoints());
        panel = new JPanel();
        panel.add(shopLabel);
        panel.add(scoreLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(200, 800, 200, 800));
        panel.setLayout(new GridLayout(0, 1));
        initializeButtons(panel);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Weapon Shop");
        frame.pack();
        frame.setVisible(true);
    }

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

    private JButton addButton(String label, ImageIcon icon) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setBounds(0, 200, 80, 80);
        try {
            button.setIcon(fitToButton(icon));
        } catch (Exception e) {
            return button;
        }
        return button;
    }

    private Icon fitToButton(ImageIcon icon) {
        Image img = icon.getImage();
        Image resized = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }

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
                ended = true;
                frame.dispose();
            }
            this.scoreLabel.setText("Available points: " + score.getPoints());
        } catch (NotEnoughPointsException nepe) {
            this.scoreLabel.setText("Not enough points for that weapon! Available points: " + score.getPoints());
        }
    }

    public String getPressed() {
        if (buyPistol.equals(pressed)) {
            return "P";
        } else if (buyUzi.equals(pressed)) {
            return "U";
        } else if (buyShotgun.equals(pressed)) {
            return "S";
        } else if (buyRPG.equals(pressed)) {
            return "R";
        } else if (quit.equals(pressed)) {
            frame.dispose();
            return "Q";
        }
        return "";
    }

    public Player getPlayer() {
        return this.player;
    }

    public Score getScore() {
        return this.score;
    }

    public boolean getEnded() {
        return ended;
    }
}
