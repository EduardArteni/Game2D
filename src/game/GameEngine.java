package game;

public class GameEngine implements Runnable {
    private Game game;

    public GameEngine(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / game.getUps();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                delta--;
            }
        }
    }

    private void update() {
        try {
            game.player.update();
        } catch (Exception e) {
            System.out.println("Tried updating we failed");
        }
    }
}
