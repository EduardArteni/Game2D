package player;

import game.Game;

import java.awt.*;

public class Player {
    public Point screenPosition;
    private Game game;
    private Point position;
    private boolean upCollide, downCollide, rightCollide, leftCollide;

    public Player(Game game) {
        this.game = game;
        this.position = new Point(88, 88);
        this.screenPosition = new Point(game.getScreenDimension().width / 2, game.getScreenDimension().height / 2);
    }

    public void update() {
        if (game.keyHandler.upPressed) {
            if (!upCollide)
                this.position.y -= 1 * (120 / game.currentUps);
            if (this.position.y < 0) {
                this.position.y = 0;
            }
        }
        if (game.keyHandler.downPressed) {
            if (!downCollide)
                this.position.y += 1 * (120 / game.currentUps);
            if (this.position.y > (game.getMaxWorldRow() * 16) - 16) {
                this.position.y = (game.getMaxWorldRow() * 16) - 16;
            }
        }
        if (game.keyHandler.rightPressed) {
            if (!rightCollide)
                this.position.x += 1 * (120 / game.currentUps);
            if (this.position.x > (game.getMaxWorldCol() * 16) - 16) {
                this.position.x = (game.getMaxWorldCol() * 16 - 16);
            }
        }
        if (game.keyHandler.leftPressed) {
            if (!leftCollide)
                this.position.x -= 1 * (120 / game.currentUps);
            if (this.position.x < 0) {
                this.position.x = 0;
            }
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
        int centerPosX = this.position.x + 8;
        int centerPosY = this.position.y + 8;

        //int upCenterX = centerPosX;
        int upCenterY = centerPosY - 8;

        //int downCenterX = centerPosX;
        int downCenterY = centerPosY + 8;

        int rightCenterX = centerPosX + 8;
        //int rightCenterY = centerPosY;

        int leftCenterX = centerPosX - 8;
        //int leftCenterY = centerPosY;

        //TILE
        int centerTileX = centerPosX / 16;
        int centerTileY = centerPosY / 16;

        int upTileY = upCenterY / 16;
        int downTileY = downCenterY / 16;
        int rightTileX = rightCenterX / 16;
        int leftTileX = leftCenterX / 16;

        if (game.getMap()[centerTileX][upTileY] == 1) {
            upCollide = true;
        } else {
            upCollide = false;
        }
        if (game.getMap()[centerTileX][downTileY] == 1) {
            downCollide = true;
        } else {
            downCollide = false;
        }
        if (game.getMap()[rightTileX][centerTileY] == 1) {
            rightCollide = true;
        } else {
            rightCollide = false;
        }
        if (game.getMap()[leftTileX][centerTileY] == 1) {
            leftCollide = true;
        } else {
            leftCollide = false;
        }

        System.out.print("CENTER [" + game.getMap()[centerTileX][centerTileY] + "] ");
        System.out.print("UP [" + game.getMap()[centerTileX][upTileY] + "] ");
        System.out.print("DOWN [" + game.getMap()[centerTileX][downTileY] + "] ");
        System.out.print("RIGHT [" + game.getMap()[rightTileX][centerTileY] + "] ");
        System.out.println("LEFT [" + game.getMap()[leftTileX][centerTileY] + "].");


    }


}
