package com.object;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullet extends SuperObject {
    GamePanel gp;
    private int worldX;
    private int worldY;
    private final String direction;
    BufferedImage image;

    public Bullet (int worldX, int worldY, String direction,GamePanel gp) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/bullet.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void tick() {
        if (Objects.equals(direction, "up")) worldY -= 20;
        else if (Objects.equals(direction, "down")) worldY += 20;
        else if (Objects.equals(direction, "left")) worldX -= 20;
        else if (Objects.equals(direction, "right")) worldX += 20;
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
