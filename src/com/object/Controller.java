package com.object;

import com.company.GamePanel;

import java.awt.*;
import java.util.LinkedList;

public class Controller {
    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    Bullet TempBullet;
    GamePanel gp;

    public Controller(GamePanel gp) {
        this.gp = gp;
    }

    public void update() {
        for (int i = 0; i < b.size(); i++) {
            TempBullet = b.get(i);
            if (TempBullet.getY() < 0 || TempBullet.getY() > 3840
            || TempBullet.getX() < 0 || TempBullet.getX() > 4800) {
                removeBullet(TempBullet);
            }
            TempBullet.update();
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < b.size(); i++) {
            TempBullet = b.get(i);
            TempBullet.draw(g2);
        }
    }

    public void addBullet(Bullet block) {
        b.add(block);
    }

    public void removeBullet(Bullet block) {
        b.remove(block);
    }
}
