package game;

import entity.Entity;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / game.getFps();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                game.currentFps = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        try {
            game.tileHandler.draw(g2);
            for (Entity e : game.entities) {
                e.draw(g2);
            }
            game.player.draw(g2);
            g.drawString(String.valueOf(game.client.ping), 0, 12);
            g.drawString(String.valueOf(game.currentFps), 0, 24);
            g.drawString(String.valueOf(game.currentUps), 0, 36);

        } catch (Exception e) {
            System.out.println("Tried drawing we failed");
        }
        g2.dispose();
    }

}
