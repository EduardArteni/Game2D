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
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < game.getMaxWorldCol() && worldRow < game.getMaxWorldRow()) {
            int tileNum = game.getMap()[worldCol][worldRow];

            int worldX = worldCol * game.getTileSize();
            int worldY = worldRow * game.getTileSize();
            int screenX = worldX - game.player.getPosition().x + game.getScreenDimension().width / 2;
            int screenY = worldY - game.player.getPosition().y + game.getScreenDimension().height / 2;


            if (worldX + game.getTileSize() > game.player.getPosition().x - game.getScreenDimension().width / 2 && worldX - game.getTileSize() < game.player.getPosition().x + game.getScreenDimension().width / 2 && worldY + game.getTileSize() > game.player.getPosition().y - game.getScreenDimension().height / 2 && worldY - game.getTileSize() < game.player.getPosition().y + game.getScreenDimension().height / 2) {
                g.drawImage(tiles[tileNum].getImage(), screenX, screenY, game.getTileSize(), game.getTileSize(), null);
            }
            worldCol++;

            if (worldCol == game.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
