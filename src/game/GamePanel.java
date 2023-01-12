package game;

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

        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                repaint();
                delta--;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        try {
            game.tileHandler.draw(g2);
            game.player.draw(g2);
        } catch (Exception e) {
            System.out.println("Tried drawing we failed");
        }
        g2.dispose();
    }

}
