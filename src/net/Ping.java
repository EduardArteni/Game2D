package net;

import game.Game;
import net.packets.Packet00Ping;

public class Ping implements Runnable {
    Game game;

    public Ping(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (true) {
            double drawInterval = 1000000000 / 1;

            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;

            while (true) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;

                if (delta >= 1) {
                    ping();
                    delta--;
                }
            }
        }
    }

    private void ping() {
        Packet00Ping ping = new Packet00Ping();
        ping.writeData(game.client);
    }


}
