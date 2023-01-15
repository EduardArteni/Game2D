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
            if (game.keyHandler.f1pressed) {
                g.drawString("ping " + game.client.ping, 0, 12);
                g.drawString("fps " + game.currentFps, 0, 24);
                g.drawString("ups " + game.currentUps, 0, 36);
                g.drawString("fov " + game.getFov(), 0, 48);
                g.drawString("x " + game.player.getPosition().x, 0, 60);
                g.drawString("y " + game.player.getPosition().y, 0, 72);
                g.drawString("y " + (game.player.getPosition().x + 8) / 16, 0, 84);
                g.drawString("y " + (game.player.getPosition().y + 8) / 16, 0, 96);
            }

        } catch (Exception e) {
            System.out.println("Tried drawing we failed");
        }
        g2.dispose();
    }

}
