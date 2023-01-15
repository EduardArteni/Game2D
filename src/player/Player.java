package player;

import game.Game;

import java.awt.*;

public class Player {
    public Point screenPosition;
    public Rectangle hitbox;
    private Game game;
    private Point position;

    public Player(Game game) {
        this.game = game;
        this.position = new Point(0, 0);
        this.screenPosition = new Point(game.getScreenDimension().width / 2, game.getScreenDimension().height / 2);
        this.hitbox = new Rectangle(0, 0, 16, 16);
    }

    public void update() {
        if (game.keyHandler.upPressed) {
            this.position.y -= 1 * (120 / game.currentUps);
        }
        if (game.keyHandler.downPressed) {
            this.position.y += 1 * (120 / game.currentUps);
        }
        if (game.keyHandler.rightPressed) {
            this.position.x += 1 * (120 / game.currentUps);
        }
        if (game.keyHandler.leftPressed) {
            this.position.x -= 1 * (120 / game.currentUps);
        }
        checkCollision();
    }

    public void draw(Graphics2D g) {
        g.drawRoundRect(screenPosition.x - (game.getTileSize()) / 2, screenPosition.y - (game.getTileSize()) / 2, game.getTileSize(), game.getTileSize(), game.getTileSize() / 2, game.getTileSize() / 2);
    }

    public Point getPosition() {
        return position;
    }

    public void checkCollision() {
        //CENTER
        int centerPosX = position.x;
        int centerPosY = position.y;

        //EDGES
        int upPosX = centerPosX;
        int upPosY = centerPosY - this.hitbox.height / 2;

        int downPosX = centerPosX;
        int downPosY = centerPosY + this.hitbox.height / 2;

        int rightPosX = centerPosX + this.hitbox.width / 2;
        int rightPosY = centerPosY;

        int leftPosX = centerPosX - this.hitbox.width / 2;
        int leftPosY = centerPosY;

        //TILES
        int centerTileX = (centerPosX - 8) / 16;
        int centerTileY = (centerPosY - 8) / 16;



        System.out.println("CENTER [" + centerTileX + "," + centerTileY + "]");
//        System.out.print("UP [" + upPosX + "," + upPosY + "] ");
//        System.out.print("DOWN [" + downPosX + "," + downPosY + "] ");
//        System.out.print("RIGHT [" + rightPosX + "," + rightPosY + "] ");
//        System.out.println("LEFT [" + leftPosX + "," + leftPosY + "].");


    }
}
