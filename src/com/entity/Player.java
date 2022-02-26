package com.entity;

import com.company.GamePanel;
import com.company.KeyHandler;
import com.object.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int point = 0;
    public int playerHealth = 100;
    boolean moving = false;
    int pixelCounter = 0;
    public int energyBuffer;


    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 1;
        solidArea.y = 1;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 46;
        solidArea.height = 46;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 1536;
        worldY = 1536;
        speed = 8;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up-1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up-2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up-3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up-4.png")));

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down-1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down-2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down-3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down-4.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left-1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left-2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left-3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left-4.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right-1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right-2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right-3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right-4.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!moving) {
            if (keyHandler.upPressed || keyHandler.downPressed ||
                    keyHandler.leftPressed || keyHandler.rightPressed) {
                if (keyHandler.upPressed) {
                    direction = "up";
                    if (keyHandler.spacePressed && bulletCounter == 0) {
                        gp.controller.addBullet(new Bullet(worldX, worldY, direction, gp));
                        bulletCounter++;
                    }
                } else if (keyHandler.downPressed) {
                    direction = "down";
                    if (keyHandler.spacePressed && bulletCounter == 0) {
                        gp.controller.addBullet(new Bullet(worldX, worldY, direction, gp));
                        bulletCounter++;
                    }
                } else if (keyHandler.leftPressed) {
                    direction = "left";
                    if (keyHandler.spacePressed && bulletCounter == 0) {
                        gp.controller.addBullet(new Bullet(worldX, worldY, direction, gp));
                        bulletCounter++;
                    }
                } else if (keyHandler.rightPressed && bulletCounter == 0) {
                    direction = "right";
                    if (keyHandler.spacePressed) {
                        gp.controller.addBullet(new Bullet(worldX, worldY, direction, gp));
                        bulletCounter++;
                    }
                }
                moving = true;

                //CHECK TILE COLLISION
                collisionOn = false;
                gp.cChecker.checkTile(this);

                // CHECK OBJECT COLLISION
                int objIndexBomb = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndexBomb);

                // CHECK IF PLAYER HEALTH IS 0
                if (gp.player.playerHealth <= 0) {
                    gp.ui.showMessage("You Lose");
                    gp.gameThread = null;
                }
            }
        }

            if (moving == true) {
                //IF COLLISION IS FALSE, PLAYER CAN MOVE
                if (collisionOn == false) {
                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }

                spriteCounter++;
                if (spriteCounter > 9) {
                    if (spriteNum == 1) spriteNum = 2;
                    else if (spriteNum == 2) spriteNum = 3;
                    else if (spriteNum == 3) spriteNum = 4;
                    else if (spriteNum == 4) spriteNum = 1;
                    spriteCounter = 0;
                }
                pixelCounter += speed;
                if (pixelCounter == 48) {
                    moving = false;
                    pixelCounter = 0;
                }
            }
            else {
                if (Objects.equals(direction, "up")) spriteNum = 1;
                if (Objects.equals(direction, "down")) spriteNum = 1;
                if (Objects.equals(direction, "left")) spriteNum = 1;
                if (Objects.equals(direction, "right")) spriteNum = 1;
            }

            if (keyHandler.spacePressed && bulletCounter == 0) {
            gp.controller.addBullet(new Bullet(worldX, worldY, direction, gp));
            bulletCounter++;
        }

        if (!keyHandler.spacePressed) bulletCounter = 0;

    }

    public void pickUpObject(int i) {
        if (i != 99999) {
            gp.obj[i] = null;
        }
    }

    public void deleteObject(int i) {
        if (i != 9999999) {
            gp.obj[i] = null;
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

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


}
