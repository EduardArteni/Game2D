package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public Tile(String path) throws IOException {
        image = ImageIO.read(new File(path));
    }
}
