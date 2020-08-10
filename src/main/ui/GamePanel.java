package ui;

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

    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        try {
            playerImage = ImageIO.read(new File("./data/8bitmegaman"));
        } catch (IOException ioe) {
            System.err.println("Cannot find player image!");
        }
    }
}
