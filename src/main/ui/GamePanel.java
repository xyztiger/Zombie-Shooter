package ui;

import com.sun.prism.GraphicsPipeline;
import enviornment.Stage;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    private BufferedImage playerImage;
    private Game game;

    public GamePanel(Game game) {
        setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
        setBackground(Color.white);
        this.game = game;
    }

    private void drawGame(Graphics g) {
        drawPlayer(g);
        drawZombie(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        int[] vertexX;
        vertexX = new int[]{p.getPosX() - 5, p.getPosX() + 5, p.getPosX()};
        int[] vertexY;
        vertexY = new int[]{p.getPosY(), p.getPosY(), p.getPosY() + 5};
        Polygon triangle = new Polygon(vertexX, vertexY, 3);
        g.fillPolygon(triangle);
        g.setColor(Color.GREEN);
//        try {
//            playerImage = ImageIO.read(new File("./data/8bitmegaman"));
//        } catch (IOException ioe) {
//            System.err.println("Cannot find player image!");
//        }
    }

    private void drawZombie(Graphics g) {
        Zombie z = game.getZombie();
        g.fillRect(z.getPosX(), z.getPosY(), 5, 5);
        g.setColor(Color.RED);
    }
}
