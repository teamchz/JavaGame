package com.entity;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class PlayerMP extends Entity {
    public PlayerMP(GamePanel gp) {
        super(gp);

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
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/up-1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/up-2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/up-3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/up-4.png")));

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/down-1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/down-2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/down-3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/down-4.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/left-1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/left-2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/left-3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/left-4.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/right-1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/right-2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/right-3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playermp/right-4.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
