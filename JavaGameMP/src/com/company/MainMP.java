package com.company;

import javax.swing.*;

public class MainMP {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JavaGame");

        GamePanelMP gamePanelMP = new GamePanelMP();
        window.add(gamePanelMP);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanelMP.setupGame();
        gamePanelMP.client.StartConnect();
        gamePanelMP.startGameThread();
    }
}
