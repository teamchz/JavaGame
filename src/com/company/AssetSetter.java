package com.company;

import com.object.OBJ_Bomb;
import com.object.OBJ_ET;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random random = new Random();
    int count = 1;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
            int upperBound = 11;
            int col = 98;
            int row = 78;

            for (int i=2; i<row*2; i++) {
                for (int j=2; j<col; j++) {
                    int rand1 = random.nextInt(upperBound);
                    int rand2 = random.nextInt(upperBound);
                    int rand3 = random.nextInt(1000);

                    if (rand1 < 1 && rand2 < 2) {
                        gp.obj[count] = new OBJ_Bomb();
                        gp.obj[count].worldX = i * gp.tileSize;
                        gp.obj[count].worldY = j * gp.tileSize;
                        count++;

                    }
                    else if (rand3 == 1) {
                        gp.obj[count] = new OBJ_ET();
                        gp.obj[count].worldX = i * gp.tileSize;
                        gp.obj[count].worldY = j * gp.tileSize;
                        count++;
                    }
                }
            }
    }

    public void setNewBomb() {
        int upperBound1 = 100;
        int upperBound2 = 80;
        int rand1 = random.nextInt(upperBound1) + 1;
        int rand2 = random.nextInt(upperBound2) + 1;

        gp.obj[count] = new OBJ_Bomb();
        randomPosition(rand1, rand2);

        System.out.print("New Bomb Was Generated at X: " + (rand1));
        System.out.print(", Y: " + (rand2));
        System.out.println();
    }
    public void setNewET() {
        int upperBound1 = 100;
        int upperBound2 = 80;
        int rand1 = random.nextInt(upperBound1) + 1;
        int rand2 = random.nextInt(upperBound2) + 1;

        gp.obj[count] = new OBJ_ET();
        gp.obj[count].energy = gp.player.energyBuffer;
        try {
            gp.obj[count].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/ET/ET-"+gp.obj[count].energy+".png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        randomPosition(rand1, rand2);

        System.out.print("New EnergyTank Was Generated at X: " + (rand1));
        System.out.print(", Y: " + (rand2));
        System.out.println();
    }

    private void randomPosition(int rand1, int rand2) {
        gp.obj[count].worldX = rand1 * gp.tileSize;
        gp.obj[count].worldY = rand2 * gp.tileSize;
        count++;
    }
}
