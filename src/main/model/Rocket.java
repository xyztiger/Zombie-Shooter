package model;

public class Rocket extends Entity {
    private static final int SPEED = 20;
    private static final int WIDTH = 5;
    private static final int LENGTH = 5;

    public Rocket(Player p) {
        this.posX = p.getPosX();
        this.posY = p.getPosY();
        this.dir = p.getDirection();
        this.speed = this.SPEED;
        this.width = WIDTH;
        this.length = LENGTH;
    }

}