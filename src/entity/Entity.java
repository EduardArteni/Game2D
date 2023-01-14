package entity;

import game.Game;

import java.awt.*;

public abstract class Entity {
    private Game game;
    private Point position;

    public Entity(Game game) {
        this.game = game;
    }

    public Point getPosition() {
        return position;
    }

    public void move(Point position) {
        this.position = position;
    }

    public void draw(Graphics2D g) {
        Point playerProjectionPosition = new Point(game.player.getPosition().x * game.getFov(), game.player.getPosition().y * game.getFov());
        Point projectionPosition = new Point(position.x * game.getFov(), position.y * game.getFov());
        Point relativeProjectionPosition = new Point(projectionPosition.x - playerProjectionPosition.x, projectionPosition.y - playerProjectionPosition.y);
        Point screenPos = new Point(game.player.screenPosition.x + relativeProjectionPosition.x - game.getTileSize() / 2, game.player.screenPosition.y + relativeProjectionPosition.y - game.getTileSize() / 2);
        g.drawRect(screenPos.x, screenPos.y, game.getTileSize(), game.getTileSize());
    }
}
