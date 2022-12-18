package com.isep.utils.GUI;

import com.isep.utils.entity.Player;
import com.isep.utils.object.SuperObject;
import com.isep.utils.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // 3x scale
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    // System
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public UI ui = new UI(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    //Increase number of items displayable in the game if needed
    public SuperObject[] obj = new SuperObject[10];

    public GamePanel() {
        // Set the size of the panel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long curentTime;
        while(gameThread != null){
            // Game loop
            curentTime = System.nanoTime();
            delta += (curentTime - lastTime) / drawInterval;
            lastTime = curentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }


    public void update (){
        // Update information like player position
        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        // Tile
        tileManager.draw(graphics2D);

        // Object
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(graphics2D, this);
            }
        }

        // Player
        player.draw(graphics2D);
        ui.draw(graphics2D);

        graphics2D.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
