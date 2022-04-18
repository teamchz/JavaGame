package com.company;

import com.entity.Entity;
import com.entity.Player;
import com.object.Bullet;
import com.object.Controller;
import com.object.SuperObject;
import com.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class GamePanelMP extends JPanel implements Runnable {

    // SCREEN SETTING
    final int originalTileSize = 16;  // 16 x 16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 x 48
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 20;
    public final int screenWidth = tileSize * maxScreenCol; // 400 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 320 pixel

    // WORLD SETTING
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 80;

    public final int worldWidth = tileSize * maxWorldCol;  // 4800 pixel
    public final int worldHeight = tileSize * maxWorldRow;  // 3840 pixel

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    public Thread gameThread;
    public Thread gameThread2;
    TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public SuperObject obj[] = new SuperObject[1000];
    public Entity playermp[] = new Entity[10];
    public Bullet bullet = new Bullet(player.worldX, player.worldY, player.direction,this);
    public Controller controller = new Controller(this);
    public UI ui = new UI(this);
    public Client client;
    public String bufferIndex;
    public int spriteBreak = 0;
    {
        try {
            client = new Client(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GamePanelMP() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setPlayerMP();
    }

    public void startGameThread() {
        gameThread = new Thread(this, "Thread1");
        gameThread.start();
        gameThread2 = new Thread(this, "Thread2");
        gameThread2.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;  // 0.01666 second
        double delta = 0;
        long lastTime = 0;
        long currentTime;
        int i = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1 && ((Objects.equals(Thread.currentThread().getName(), "Thread1")) || i==0)) {
                update();
                repaint();
                delta--;
                i=1;
            }
            else {
                try {
                    if (Objects.equals(Thread.currentThread().getName(), "Thread2"))
                    client.ReceiveData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (player.playerHealth <= 0) {
                ui.showMessage("You Lose");
                client.SendWinner();
                gameThread = null;
            }
        }
    }

    public void update() {
        player.update();
        controller.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);

        // OBJ
        for (int i = 0; i < obj.length; i++ ) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);

        if (client.socket != null)
        playermp[0].draw(g2);

        if (client.socket != null && client.bulletY != null)
            bullet.draw2(g2);

        controller.draw(g2);
        ui.draw(g2);

        g2.dispose();
    }
}