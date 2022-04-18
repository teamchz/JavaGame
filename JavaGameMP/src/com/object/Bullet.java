package com.object;

import com.company.GamePanelMP;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullet extends SuperObject {
    GamePanelMP gp;
    public int worldX;
    public int worldY;
    public String direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public String bulletName = "";
    BufferedImage image;

    public Bullet (int worldX, int worldY, String direction,GamePanelMP gp) {
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
            worldY -= 12;
            solidArea.y -= 12;
        }
        else if (Objects.equals(direction, "down")) {
            worldY += 12;
            solidArea.y += 12;
        }
        else if (Objects.equals(direction, "left")) {
            worldX -= 12;
            solidArea.x -= 12;
        }
        else if (Objects.equals(direction, "right")) {
            worldX += 12;
            solidArea.x += 12;
        }

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                gp.obj[i].solidArea.x = gp.obj[i].worldX;
                gp.obj[i].solidArea.y = gp.obj[i].worldY;
                if (solidArea.intersects(gp.obj[i].solidArea)) {
                    if (gp.obj[i].name == "bomb") {
                        gp.obj[i] = null;
                        gp.controller.removeBullet(this);
                    }
                    else if (gp.obj[i].name == "ET") {
                        gp.controller.removeBullet(this);
                    }
                }
            }
        }
        if (solidArea.x > gp.playermp[0].worldX - 48 && solidArea.x < gp.playermp[0].worldX + 48
                && solidArea.y > gp.playermp[0].worldY - 48 && solidArea.y < gp.playermp[0].worldY + 48
                && Objects.equals(bulletName, "")) {
            gp.controller.removeBullet(this);
        }

        if (solidArea.x > gp.player.worldX - 48 && solidArea.x < gp.player.worldX + 48
                && solidArea.y > gp.player.worldY - 48 && solidArea.y < gp.player.worldY + 48
                && Objects.equals(bulletName, "A")) {
            gp.controller.removeBullet(this);
            gp.player.playerHealth -= 50;
        }
    }

    public int getX() {
        return worldX;
    }

    public int getY() {
        return worldY;
    }

    public String getDirection() {
        return direction;
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        int x = screenX;
        int y = screenY;

        if (screenX > worldX) {
            x = worldX;
        }
        if (screenY > worldY) {
            y = worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
        if (rightOffset > gp.worldWidth - worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomOffset = gp.screenHeight - screenY;
        if (bottomOffset > gp.worldHeight - worldY) {
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    public void draw2(Graphics2D g2) {

        g2.drawImage(image, Integer.parseInt(gp.client.bulletX) , Integer.parseInt(gp.client.bulletY), gp.tileSize, gp.tileSize, null);
    }

}
