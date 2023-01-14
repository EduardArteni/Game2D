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
        long timer = 0;
        int updateCount = 0;

        while (true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                delta--;
                updateCount++;
            }
            if (timer >= 1000000000) {
                game.currentUps = updateCount;
                updateCount = 0;
                timer = 0;
            }
        }
    }

//    @Override
//    public void run() {
//        double drawInterval = 1000000000 / UPS; // 0.0166 seconds
//
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//
//        long timer = 0;
//        int updateCount = 0;
//
//        while (true) {
//            currentTime = System.nanoTime();
//
//            delta += (currentTime - lastTime) / drawInterval;
//            timer += currentTime - lastTime;
//            lastTime = currentTime;
//
//            if (delta >= 1) {
//                update();
//                delta--;
//                updateCount++;
//            }
//
//            if (timer >= 1000000000) {
//                updateCount = 0;
//                timer = 0;
//            }
//
//        }
//    }

    private void update() {
        try {
            game.player.update();
        } catch (Exception e) {
            System.out.println("Tried updating we failed");
            e.printStackTrace();
        }
    }
}
