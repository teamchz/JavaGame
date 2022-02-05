package com.company;

import com.object.OBJ_Bomb;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        Random random = new Random();
            int upperBound = 11;
            int col = 100;
            int row = 80;
            int count = 1;

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
}
