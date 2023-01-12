package game;

import input.KeyHandler;
import player.Player;
import tile.TileHandler;

import javax.swing.*;
import java.awt.*;

public class Game {
    private final int tileSize;
    private final int maxWorldCol;
    private final int maxWorldRow;
    private final int map[][];
    private final int fps;
    private final int ups;
    private final Dimension screenDimension;
    public Player player;
    public TileHandler tileHandler;
    private JFrame window;
    private GameEngine gameEngine;
    private GamePanel gamePanel;
    public KeyHandler keyHandler;
    private Thread engineThread;
    private Thread panelThread;

    public Game(int tileSize, int maxWorldCol, int maxWorldRow, int fps, int ups) {
        this.tileSize = tileSize;
        this.maxWorldCol = maxWorldCol;
        this.maxWorldRow = maxWorldRow;
        this.fps = fps;
        this.ups = ups;
        this.screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        this.keyHandler = new KeyHandler();
        this.tileHandler = new TileHandler(this);
        this.gamePanel = new GamePanel(this);
        this.gameEngine = new GameEngine(this);

        map = loadMap("");
        player = new Player(this);

        createWindow();
        startGameThread();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int[][] getMap() {
        return map;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    private int[][] loadMap(String path) {
        return new int[maxWorldCol][maxWorldRow];
    }

    public Dimension getScreenDimension() {
        return screenDimension;
    }

    public int getFps() {
        return fps;
    }

    public int getUps() {
        return ups;
    }

    private void createWindow() {
        window = new JFrame();
        window.setUndecorated(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Angry ball");

        initPanel(screenDimension);

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private void initPanel(Dimension size) {

        gamePanel.setPreferredSize(size);
        gamePanel.setBackground(Color.black);
        gamePanel.setDoubleBuffered(true);
        gamePanel.addKeyListener(keyHandler);
        gamePanel.setFocusable(true);
    }

    public void startGameThread() {
        engineThread = new Thread(this.gameEngine);
        panelThread = new Thread(this.gamePanel);

        engineThread.start();
        panelThread.start();
    }

}
