package entity;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    private Game game;
    private Point position;

    public Entity(Game game) {
        this.game = game;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void draw(Graphics2D g) {

    }
}
