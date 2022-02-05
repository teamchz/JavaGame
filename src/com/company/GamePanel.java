package com.company;

import com.entity.Player;
import com.object.Bullet;
import com.object.Controller;
import com.object.SuperObject;
import com.tile.TileManager;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

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

    TileManager tileM = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public SuperObject obj[] = new SuperObject[1000];
    public Bullet bullet = new Bullet(player.worldX, player.worldY, player.direction,this);
    public Controller controller = new Controller(this);
    public UI ui = new UI(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;  // 0.01666 second
        double delta = 0;
        long lastTime = 0;
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
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
             if (obj[i] != null) obj[i].draw(g2, this);
         }

        player.draw(g2);
         controller.draw(g2);
        ui.draw(g2);

        g2.dispose();
     }
}
