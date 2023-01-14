package main;

import game.Game;

public class Main {
    public static void main(String[] args) {
        Game game1 = new Game(3, 10, 10, 60, 30);
        game1.startNetThreads(true);
    }
}