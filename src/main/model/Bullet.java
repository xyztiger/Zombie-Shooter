package model;

public class Bullet extends Entity {
    private static final int SPEED = 10;
    private static final int WIDTH = 2;
    private static final int LENGTH = 5;

    public Bullet(Player p) {
        this.posX = p.getPosX();
        this.posY = p.getPosY();
        this.dir = p.getDirection();
        this.speed = this.SPEED;
        this.width = WIDTH;
        this.length = LENGTH;
    }

}
