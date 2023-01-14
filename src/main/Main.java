package main;

import entity.Player;
import game.Game;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Game game1 = new Game(4, 10, 10, 60, 60);
        game1.startNetThreads(true);
        Player bob = new Player(game1);
        bob.move(new Point(32, -16));
        game1.entities.add(bob);
    }
}