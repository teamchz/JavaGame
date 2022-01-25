package com.company;

import java.awt.*;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class UI {
    GamePanel gp;
    Font arial_40;
    public boolean messageOn= false;
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
        if (messageOn == true) {
            g2.drawString(message, 510, 400);

        }
    }

}
