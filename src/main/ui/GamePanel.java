package ui;

import model.Bullet;
import model.Player;
import model.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Panel for the game that shows the player and zombie; allows player to move and shoot
public class GamePanel extends JPanel {
    private Game game;
    private JLabel label;

    // EFFECTS: creates the game panel with an instruction label
    public GamePanel(Game game) {
        this.game = game;
        label = new JLabel("press J to shoot; B to buy guns; C to choose weapon; Q to quit");
        label.setSize(label.getPreferredSize());
        label.setLocation(0,0);
        add(label);
        setBackground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
        setLayout(new GridLayout(0, 1));
    }

    // EFFECTS: draws the player and the zombie
    private void drawGame(Graphics g) {
        drawPlayer(g);
        drawZombie(g);
        drawBullet(g);
    }

    // EFFECTS: draws the components of the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // EFFECTS: draws the player at their specific location
    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        int[] vertexX;
        vertexX = new int[]{p.getPosX() - 10, p.getPosX() + 10, p.getPosX()};
        int[] vertexY;
        vertexY = new int[]{p.getPosY(), p.getPosY(), p.getPosY() + 15};
        Polygon triangle = new Polygon(vertexX, vertexY, 3);
        g.fillPolygon(triangle);
        g.setColor(Color.GREEN);
    }

    // EFFECTS: draws the zombie at its specific location
    private void drawZombie(Graphics g) {
        Zombie z = game.getZombie();
        g.fillRect(z.getPosX(), z.getPosY(), 10, 10);
        g.setColor(Color.RED);
    }

    // EFFECTS: draws bullet at specific location
    private void drawBullet(Graphics g) {
        ArrayList<Bullet> bullets = game.getBullets();
        for (Bullet bullet : bullets) {
            g.fillRect(bullet.getPosX(), bullet.getPosY(), 2, 2);
            g.setColor(Color.BLACK);
        }
    }
}
