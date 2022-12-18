package com.isep.utils.GUI;

import javax.swing.*;

public class GUIParser {
    public static void startGUI() {
        JFrame window = new JFrame("My First GUI");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Game");
        GamePanel panel = new GamePanel();
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        panel.setupGame();
        panel.startGameThread();
    }
}
