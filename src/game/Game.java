package game;

import entity.Entity;
import input.KeyHandler;
import net.Client;
import net.Ping;
import net.Server;
import player.Player;
import tile.TileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game {
    private final int tileSize = 16;
    private final int maxWorldCol;
    private final int maxWorldRow;
    private final int map[][];
    private final int fps;
    private final int ups;
    private final Dimension screenDimension;
    public ArrayList<Entity> entities = new ArrayList<>();
    public Player player;
    public TileHandler tileHandler;
    public KeyHandler keyHandler;
    public Client client;
    public Server server;
    public int currentFps;
    public int currentUps;
    public int fov;
    private JFrame window;
    private GameEngine gameEngine;
    private Ping ping;
    private GamePanel gamePanel;
    private Thread clientThread;
    private Thread serverThread;
    private Thread engineThread;
    private Thread panelThread;
    private Thread pingThread;

    public Game(int fov, int maxWorldCol, int maxWorldRow, int fps, int ups) {
        this.fov = fov;
        this.maxWorldCol = maxWorldCol;
        this.maxWorldRow = maxWorldRow;
        this.fps = fps;
        this.ups = ups;
        this.currentFps = fps;
        this.currentUps = ups;
        this.screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        this.keyHandler = new KeyHandler(this);
        this.tileHandler = new TileHandler(this);
        this.gamePanel = new GamePanel(this);
        this.gameEngine = new GameEngine(this);

        client = new Client("localhost");

        map = loadMap("");
        player = new Player(this);

        createWindow();
        startGameThread();
    }

    public void startNetThreads(boolean isServer) {
        clientThread = new Thread(client);
        window.setTitle("Angry ball CLIENT");
        if (isServer) {
            server = new Server();
            serverThread = new Thread(server);
            serverThread.start();
            window.setTitle("Angry ball SERVER");
        }
        clientThread.start();
        ping = new Ping(this);
        pingThread = new Thread(ping);
        pingThread.start();
    }

    public int getTileSize() {
        return tileSize * getFov();
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
        int[][] genMap = new int[maxWorldCol][maxWorldRow];
        genMap[4][4] = 1;
        genMap[5][4] = 1;
        genMap[6][4] = 1;
        genMap[7][4] = 1;

        genMap[4][5] = 1;
        genMap[4][6] = 1;

        genMap[7][5] = 1;
        genMap[7][6] = 1;

        genMap[4][7] = 1;
        genMap[5][7] = 1;
        genMap[6][7] = 1;
        genMap[7][7] = 1;
        return genMap;
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

    public int getFov() {
        return fov;
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
