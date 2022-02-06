package com.company;

import com.object.OBJ_Bomb;

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
            int col = 100;
            int row = 80;

            for (int i=0; i<row*2; i++) {
                for (int j=0; j<col; j++) {
                    int rand1 = random.nextInt(upperBound);
                    int rand2 = random.nextInt(upperBound);
                    if (rand1 < 2 && rand2 < 1) {  //3, 1
                        gp.obj[count] = new OBJ_Bomb();
                        gp.obj[count].worldX = i * gp.tileSize;
                        gp.obj[count].worldY = j * gp.tileSize;
                        count++;
                    }
                }
            }
    }

    public void setOneObject() {
        int upperBound1 = 101;
        int upperBound2 = 81;
        int rand1 = random.nextInt(upperBound1);
        int rand2 = random.nextInt(upperBound2);

        gp.obj[count] = new OBJ_Bomb();
        gp.obj[count].worldX = rand1 * gp.tileSize;
        gp.obj[count].worldY = rand2 * gp.tileSize;
        count++;

        System.out.print("New Bomb Was Generated at X: " + (rand1*gp.tileSize));
        System.out.print(", Y: " + (rand2* gp.tileSize));
        System.out.println();
    }
}
