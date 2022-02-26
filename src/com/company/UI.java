package com.company;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font arial_40;
    public boolean messageOn = false;
    public boolean energyTextOn = false;
    public String message = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.black);
        g2.drawString("Points = ", 70, 70);
        g2.setColor(Color.BLUE);
        g2.drawString("" + gp.player.point, 230, 70);
        g2.setColor(Color.black);
        g2.drawString("Health = ", 70, 900);
        g2.setColor(Color.red);
        g2.drawString("" + gp.player.playerHealth, 230, 900);
        g2.setColor(Color.black);
        g2.drawString("x: " + gp.player.worldX/ gp.tileSize, 600, 70);
        g2.drawString("y: " + gp.player.worldY/gp.tileSize, 800, 70);

        if (messageOn) {
            g2.setColor(Color.red);
            g2.drawString(message, 510, gp.screenHeight/2);
            g2.setColor(Color.black);
        }

        int nameLength = (int)g2.getFontMetrics().getStringBounds(gp.username, g2).getWidth();
        int x = gp.player.screenX;
        int y = gp.player.screenY;

        if (gp.player.screenX > gp.player.worldX) {
            x = gp.player.worldX;
        }
        if (gp.player.screenY > gp.player.worldY) {
            y = gp.player.worldY;
        }
        int rightOffset = gp.screenWidth - gp.player.screenX;
        if (rightOffset > gp.worldWidth - gp.player.worldX) {
            x = gp.screenWidth - (gp.worldWidth - gp.player.worldX);
        }
        int bottomOffset = gp.screenHeight - gp.player.screenY;
        if (bottomOffset > gp.worldHeight - gp.player.worldY) {
            y = gp.screenHeight - (gp.worldHeight - gp.player.worldY);
        }
        g2.drawString(gp.username, x - nameLength/2+20, y - 40);
    }

}
