package player;

import game.Game;

import java.awt.*;

public class Player {
    private Game game;
    private Point position;
    private Point screenPosition;

    public Player(Game game) {
        this.game = game;
        this.position = new Point(0, 0);
        this.screenPosition = new Point(game.getScreenDimension().width / 2, game.getScreenDimension().height / 2);
    }

    public void update() {
        if (game.keyHandler.upPressed) {
            this.position.y -= 2 * (120 / game.currentUps);
        }
        if (game.keyHandler.downPressed) {
            this.position.y += 2 * (120 / game.currentUps);
        }
        if (game.keyHandler.rightPressed) {
            this.position.x += 2 * (120 / game.currentUps);
        }
        if (game.keyHandler.leftPressed) {
            this.position.x -= 2 * (120 / game.currentUps);
        }
    }

    public void draw(Graphics2D g) {
        g.drawRoundRect(screenPosition.x - (game.getTileSize()) / 2, screenPosition.y - (game.getTileSize()) / 2, game.getTileSize(), game.getTileSize(), game.getTileSize() / 2, game.getTileSize() / 2);
    }

    public Point getPosition() {
        return position;
    }
}
