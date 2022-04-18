package com.company;

import com.entity.PlayerMP;
import com.object.OBJ_Bomb;
import com.object.OBJ_ET;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class AssetSetter {
    GamePanel gp;
    Random random = new Random();
    public int count = 0;
    public int counter = 800;
    public String bombData = "";
    public String etData = "";
    public String etNew = "";
    public String bufferRand1, bufferRand2;
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

                        bombData = bombData + i*gp.tileSize + "/" + j* gp.tileSize + "/";
                        count++;

                    }
                    else if (rand3 == 1) {
                        gp.obj[counter] = new OBJ_ET();
                        gp.obj[counter].worldX = i * gp.tileSize;
                        gp.obj[counter].worldY = j * gp.tileSize;

                        etData = etData + i* gp.tileSize + "q" + j* gp.tileSize + "q" + gp.obj[counter].energy + "q";
                        counter++;
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
        gp.obj[count].worldX = rand1 * gp.tileSize;
        gp.obj[count].worldY = rand2 * gp.tileSize;
        bufferRand1 = String.valueOf(rand1* gp.tileSize);
        bufferRand2 = String.valueOf(rand2* gp.tileSize);
        count++;

        System.out.print("New Bomb Was Generated at X: " + (rand1));
        System.out.print(", Y: " + (rand2));
        System.out.println();
    }
    public void setNewET(int index) {
        int upperBound1 = 100;
        int upperBound2 = 80;
        int rand1 = random.nextInt(upperBound1) + 1;
        int rand2 = random.nextInt(upperBound2) + 1;

        gp.obj[counter] = new OBJ_ET();
        gp.obj[counter].energy = gp.player.energyBuffer;
        try {
            gp.obj[counter].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/ET/ET-"+gp.obj[counter].energy+".png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        gp.obj[counter].worldX = rand1 * gp.tileSize;
        gp.obj[counter].worldY = rand2 * gp.tileSize;

        gp.server.SendNewET(index, gp.obj[counter].worldX, gp.obj[counter].worldY, gp.obj[counter].energy);

        System.out.print("New EnergyTank Was Generated at X: " + (rand1));
        System.out.print(", Y: " + (rand2));
        System.out.println();
        counter++;
    }

    public void setPlayerMP() {
        gp.playermp[0] = new PlayerMP(gp);
        gp.playermp[0].worldX = 1536;
        gp.playermp[0].worldY = 1536;
    }
}
