package com.entity;

import com.company.GamePanelMP;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanelMP gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4,
            left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int bulletCounter = 0;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public Entity(GamePanelMP gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        if (gp.client.worldX != null) {
            gp.playermp[0].worldX = Integer.parseInt(gp.client.worldX);
            gp.playermp[0].worldY = Integer.parseInt(gp.client.worldY);
            direction = gp.client.direction;
            spriteNum = Integer.parseInt(gp.client.sNum);
            if (gp.playermp[0].worldX % 48 == 0
                    && gp.playermp[0].worldY % 48 == 0
                    && gp.spriteBreak == 0) spriteNum = 1;
        }

        BufferedImage image = null;
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

        switch (direction) {
            case "up":
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                if (spriteNum == 3) image = up3;
                if (spriteNum == 4) image = up4;
                break;
            case "down" :
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                if (spriteNum == 3) image = down3;
                if (spriteNum == 4) image = down4;
                break;
            case "left":
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                if (spriteNum == 3) image = left3;
                if (spriteNum == 4) image = left4;
                break;
            case "right" :
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                if (spriteNum == 3) image = right3;
                if (spriteNum == 4) image = right4;
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        g2.setFont(gp.ui.arial_40);
        g2.setColor(Color.black);
        g2.drawString("player1", x - 40, y - 40);
        gp.spriteBreak = 0;
    }
}
