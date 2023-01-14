package main;

import game.Game;

public class Main {
    public static void main(String[] args) {
        Game game1 = new Game(3, 10, 10, 144, 60);
        game1.startNetThreads(true);
    }
}