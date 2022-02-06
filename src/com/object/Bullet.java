package com.object;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullet extends SuperObject {
    GamePanel gp;
    public int worldX;
    public int worldY;
    public String direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    BufferedImage image;

    public Bullet (int worldX, int worldY, String direction,GamePanel gp) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        solidArea = new Rectangle();
        solidArea.x = gp.player.worldX;
        solidArea.y = gp.player.worldY;
        solidArea.width = 32;
        solidArea.height = 32;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bullet.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (Objects.equals(direction, "up")) {
            worldY -= 20;
            solidArea.y -= 20;
        }
        else if (Objects.equals(direction, "down")) {
            worldY += 20;
            solidArea.y += 20;
        }
        else if (Objects.equals(direction, "left")) {
            worldX -= 20;
            solidArea.x -= 20;
        }
        else if (Objects.equals(direction, "right")) {
            worldX += 20;
            solidArea.x += 20;
        }
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                gp.obj[i].solidArea.x = gp.obj[i].worldX;
                gp.obj[i].solidArea.y = gp.obj[i].worldY;
                if (solidArea.intersects(gp.obj[i].solidArea)) {
                    gp.obj[i] = null;
                    gp.controller.removeBullet(this);
                    gp.player.point += 100;
                    gp.aSetter.setOneObject();
                }
            }
        }

    }

    public int getX() {
        return worldX;
    }

    public int getY() {
        return worldY;
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
