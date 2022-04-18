package com.company;


import com.object.Bullet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;

public class Server implements Serializable {
    GamePanel gp;
    public int worldX;
    public int worldY;
    public String direction, sNum;
    public Socket socket;
    public String mpWorldX;
    public String mpWorldY;
    public String mpDirection;
    public int bulletX, bulletY;
    public String bulletDirection;
    public int bufferIndex, bombX, bombY;
    public int etDel;


    public Server(int worldX, int worldY, String direction,GamePanel gp) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
    }

    public void ServerStart() {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            socket = serverSocket.accept();
            SendData();
            BombData();
            ETData();
            if (socket.isConnected()) {
                ReceiveData();
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SendData() {
        if (gp.server.socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(gp.server.socket.getOutputStream());
                dout.writeUTF(String.valueOf(gp.player.worldX)+"-"
                        +(String.valueOf(gp.player.worldY))+"-"
                        +gp.player.direction+"-"
                        +gp.player.spriteNum);
                dout.flush();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void ReceiveData() throws IOException {
        DataInputStream din = new DataInputStream(socket.getInputStream());

        String word = din.readUTF();
        if (word.contains("-")) {
            gp.spriteBreak = 1;
            String[] part = word.split("-");
            mpWorldX = part[0];
            mpWorldY = part[1];
            mpDirection = part[2];
            sNum = part[3];
        }
        else if (word.contains("_")) {
            String[] part = word.split("_");
            bulletX = Integer.parseInt(part[0]);
            bulletY = Integer.parseInt(part[1]);
            bulletDirection = part[2];
            gp.controller.addBulletMP(new Bullet(bulletX, bulletY, bulletDirection, gp));
        }
        else if (word.contains("b")) {
            String[] part = word.split("b");
            bufferIndex = Integer.parseInt(part[0]);
            if (bufferIndex != 99999) {
                gp.obj[bufferIndex] = null;
                gp.aSetter.setNewBomb();
                SendNewBomb();
            }
        }
        else if (word.contains("z")) {
            String[] part = word.split("z");
            etDel = Integer.parseInt(part[0]);
            gp.player.energyBuffer = Integer.parseInt(part[1]);
            gp.obj[etDel] = null;
            gp.aSetter.setNewET(etDel);
        }
        else if (word.contains("Winner")) {
            gp.ui.showMessage("You Win");
            gp.gameThread = null;
        }
    }

    public void BulletData(int worldX, int worldY, String direction)  {
        if (socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF(String.valueOf(worldX)+"_"+String.valueOf(worldY)+"_"+direction);
                dout.flush();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void BombData() {
        if (socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF(gp.aSetter.bombData);
                dout.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void ETData() {
        if (socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF(gp.aSetter.etData);
                dout.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void SendNewBomb() {
        if (socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF(gp.bufferIndex + "b" + gp.aSetter.bufferRand1 + "b" + gp.aSetter.bufferRand2);
                dout.flush();
                System.out.println(gp.bufferIndex);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void SendNewET(int delIndex, int x, int y, int energy) {
        if (socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF(delIndex + "e" + x + "e" + y + "e" + energy);
                dout.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void SendWinner() {
        if (gp.server.socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF("Winner");
                dout.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
