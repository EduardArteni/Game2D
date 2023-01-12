package player;

import game.Game;

import java.awt.*;

public class Player {
    private Game game;
    private Point position;
    private Point screenPosition;

    public Player(Game game) {
        this.game = game;
        this.position = new Point(100, 100);
        this.screenPosition = new Point(game.getScreenDimension().width / 2, game.getScreenDimension().height / 2);
    }

    public void update() {
        if (game.keyHandler.upPressed) {
            this.position.y -= 4;
        }
        if (game.keyHandler.downPressed) {
            this.position.y += 4;
        }
        if (game.keyHandler.rightPressed) {
            this.position.x += 4;
        }
        if (game.keyHandler.leftPressed) {
            this.position.x -= 4;
        }
    }

    public void draw(Graphics2D g) {
        g.drawRect(screenPosition.x - 48 / 2, screenPosition.y - 48 / 2, 48, 48);
    }

    public Point getPosition() {
        return position;
    }
}
