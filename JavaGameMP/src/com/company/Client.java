package com.company;

import com.object.Bullet;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Client {

    GamePanelMP gp;
    Socket socket;
    public String word, worldX, worldY, direction, sNum;
    public String bulletX, bulletY, bulletDirection;
    public int bufferIndex, bombX, bombY;
    public int delIndexET, etX, etY, etEnergy;

    public Client(GamePanelMP gp) throws IOException {
        this.gp = gp;
    }

    public void StartConnect() {
        try {
            socket = new Socket("localhost", 6789);
            DataInputStream din = new DataInputStream(socket.getInputStream());
            String fword = din.readUTF();
            String bombData = din.readUTF();
            String etData = din.readUTF();
            System.out.println("connect!");
            gp.aSetter.bombData = bombData.split("/");
            gp.aSetter.etData = etData.split("q");
            gp.aSetter.setObject();
            SendData();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReceiveData() throws IOException {
        if (socket != null) {
            DataInputStream din = new DataInputStream(socket.getInputStream());

            word = din.readUTF();
            if (word.contains("-")) {
                gp.spriteBreak = 1;
                String part[] = word.split("-");
                worldX = part[0];
                worldY = part[1];
                direction = part[2];
                sNum = part[3];
            } else if (word.contains("_")) {
                String part[] = word.split("_");
                bulletX = part[0];
                bulletY = part[1];
                bulletDirection = part[2];
                gp.controller.addBulletMP(new Bullet(Integer.parseInt(bulletX), Integer.parseInt(bulletY), bulletDirection, gp));
            }
            else if (word.contains("b") && !word.contains("null")) {
                String part[] = word.split("b");
                if (bufferIndex != 99999) {
                    bufferIndex = Integer.parseInt(part[0]);
                    bombX = Integer.parseInt(part[1]);
                    bombY = Integer.parseInt(part[2]);
                    gp.aSetter.NewBombMP(bufferIndex, bombX, bombY);
                }
            }
            else if (word.contains("Winner")) {
                gp.ui.showMessage("You Win");
                gp.gameThread = null;
            }
            else if (word.contains("e")) {
                String[] part = word.split("e");
                delIndexET = Integer.parseInt(part[0]);
                etX = Integer.parseInt(part[1]);
                etY = Integer.parseInt(part[2]);
                etEnergy = Integer.parseInt(part[3]);
                gp.obj[delIndexET] = null;
                gp.aSetter.NewETMP(etX, etY, etEnergy);
            }

        }
    }

    public void SendData() {
        try {
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
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

    public void BulletData(int worldX, int worldY, String direction) {
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

    public void bombDelete(int index) {
        if (socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF(index + "b");
                dout.flush();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void etDelete(int index, int remain) {
        if (socket != null) {
            try {
                DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
                dout.writeUTF(index + "z" + remain);
                dout.flush();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void SendWinner() {
        if (socket != null) {
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

