package com.company;

import com.entity.PlayerMP;
import com.object.OBJ_Bomb;
import com.object.OBJ_ET;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class AssetSetter {
    GamePanelMP gp;
    Random random = new Random();
    public int count = 0;
    public int counter = 800;
    public String[] bombData;
    public String[] etData;
    public String bufferRand1, bufferRand2;
    public AssetSetter(GamePanelMP gp) {
        this.gp = gp;
    }

    public void setObject(){
        for (int i=0; i<bombData.length;i++){
            gp.obj[i] = new OBJ_Bomb();
            if (i%2!=0){
                gp.obj[count].worldX = Integer.parseInt(bombData[i-1]);
                gp.obj[count].worldY = Integer.parseInt(bombData[i]);
                count++;
            }

        }
        for (int i=0;i<etData.length;i+=3) {
            gp.obj[counter] = new OBJ_ET();
            gp.obj[counter].worldX = Integer.parseInt(etData[i]); // x
            gp.obj[counter].worldY = Integer.parseInt(etData[i+1]); // y
            gp.obj[counter].energy = Integer.parseInt(etData[i+2]); // e
            try {
                gp.obj[counter].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/ET/ET-"+gp.obj[counter].energy+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            counter++;

        }
    }

    public void setPlayerMP() {
        gp.playermp[0] = new PlayerMP(gp);
        gp.playermp[0].worldX = 1536;
        gp.playermp[0].worldY = 1536;
    }


    public void setNewET() {
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
        counter++;

        System.out.print("New EnergyTank Was Generated at X: " + (rand1));
        System.out.print(", Y: " + (rand2));
        System.out.println();
    }

    public void NewBombMP(int bufferIndex, int x, int y) {
        System.out.println(bufferIndex);
        gp.obj[bufferIndex] = null;
        gp.obj[count] = new OBJ_Bomb();
        gp.obj[count].worldX = x;
        gp.obj[count].worldY = y;
        count++;
    }

    public void NewETMP(int x, int y, int energy) {
        gp.obj[counter] = new OBJ_ET();
        gp.obj[counter].worldX = x;
        gp.obj[counter].worldY = y;
        gp.obj[counter].energy = energy;
        try {
            gp.obj[counter].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/ET/ET-"+gp.obj[counter].energy+".png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        counter++;
    }
}