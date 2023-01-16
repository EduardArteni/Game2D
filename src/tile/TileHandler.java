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
                //Tile tile = tiles[game.getMap()[col][row]];
                Point worldPos = new Point(col * game.getTileSize() - game.getTileSize() / 2, row * game.getTileSize() - game.getTileSize() / 2);
                Point screenPos = new Point(worldPos.x - game.player.getPosition().x * game.getFov() + game.getScreenDimension().width / 2, worldPos.y - game.player.getPosition().y * game.getFov() + game.getScreenDimension().height / 2);

                Point pos = game.tileHandler.getTileFromPoint(this.game.player.getPosition());

//                if (pos.x == col && pos.y == row) {
//                    g.drawRect(screenPos.x, screenPos.y, game.getTileSize(), game.getTileSize());
//                    g.drawRect(screenPos.x + 1, screenPos.y + 1, game.getTileSize() - 2, game.getTileSize() - 2);
//                    g.drawRect(screenPos.x + 2, screenPos.y + 2, game.getTileSize() - 4, game.getTileSize() - 4);
//                } else {
                if (game.getMap()[col][row] == 1) {
                    g.drawLine(screenPos.x, screenPos.y, screenPos.x + game.getTileSize(), screenPos.y + game.getTileSize());
                    g.drawLine(screenPos.x, screenPos.y + game.getTileSize(), screenPos.x + game.getTileSize(), screenPos.y);
                }
                g.drawRect(screenPos.x, screenPos.y, game.getTileSize(), game.getTileSize());
//                }

            }
        }
    }

    public Point getTileFromPoint(Point point) {
        Point position = new Point();

        for (int col = 0; col < game.getMaxWorldCol(); col++) {
            for (int row = 0; row < game.getMaxWorldRow(); row++) {
                if (isPointInTile(point, col, row)) {
                    position.x = col;
                    position.y = row;
                    return position;
                }
            }
        }

        return null;
    }

    public boolean isPointInTile(Point point, int tileX, int tileY) {
        int upLimit = (tileY * 16) - 8;
        int downLimit = (tileY * 16) + 8;
        int rightLimit = (tileX * 16) + 8;
        int leftLimit = (tileX * 16) - 8;

        if (leftLimit <= point.x && point.x < rightLimit) {
            if (upLimit <= point.y && point.y < downLimit) {
                return true;
            }
        }
        return false;
    }
}
