package tile;

import game.Game;

import java.awt.*;

public class TileHandler {
    private Game game;
    private Tile[] tiles;

    public TileHandler(Game game) {
        this.game = game;
        this.tiles = new Tile[1];
        getTileImages();
    }

    private void getTileImages() {
        try {
            tiles[0] = new Tile("res/tile/grass.png");
        } catch (Exception e) {
            System.out.println("Failed getting tile images");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        //col  row
        for (int col = 0; col < game.getMaxWorldCol(); col++) {
            for (int row = 0; row < game.getMaxWorldRow(); row++) {
                Tile tile = tiles[game.getMap()[col][row]];
                Point worldPos = new Point(col * game.getTileSize() + game.getTileSize() / 2, row * game.getTileSize() + game.getTileSize() / 2);
                Point screenPos = new Point(worldPos.x - game.player.getPosition().x * game.getFov() + game.getScreenDimension().width / 2, worldPos.y - game.player.getPosition().y * game.getFov() + game.getScreenDimension().height / 2);
                g.drawRect(screenPos.x, screenPos.y, game.getTileSize(), game.getTileSize());
            }
        }
    }
}
