package com.isep.utils.entity;

import com.isep.utils.GUI.GamePanel;
import com.isep.utils.GUI.KeyHandler;
import com.isep.utils.GUI.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction= "down";
    }

    public void getPlayerImage(){
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");

    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/"+imageName + ".png")));
            image = uTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void update(){

        if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right){

            if(keyHandler.up){
                direction = "up";
            }
            else if(keyHandler.down){
                direction = "down";
            }
            else if(keyHandler.left){
                direction = "left";
            }
            else {
                direction = "right";
            }

        collisionOn = false;
        gamePanel.cChecker.checkTile(this);
        int objIndex = gamePanel.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
            //if collision, not move
        if(!collisionOn){
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }

        }
        }
            // check map collision


            spriteCounter++;
            if(spriteCounter > 8){
                if(spriteNum == 1 && (keyHandler.down || keyHandler.up || keyHandler.left || keyHandler.right)){
                    spriteNum = 2;
                } else if (spriteNum == 2 ){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }


    }

    public void pickUpObject(int objIndex){
        if(objIndex != 999){
            String objName = gamePanel.obj[objIndex].name;

            switch (objName) {
                case "Key" -> {
                    gamePanel.playSoundEffect(1);
                    hasKey++;
                    gamePanel.obj[objIndex] = null;
                    gamePanel.ui.showMessage("You picked up a key!");
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gamePanel.playSoundEffect(3);
                        gamePanel.obj[objIndex] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("You opened the door!");
                    } else {
                        gamePanel.ui.showMessage("You need a key to open this door!");
                    }
                }
                case "Boots" -> {
                    gamePanel.playSoundEffect(2);
                    speed += 1;
                    gamePanel.obj[objIndex] = null;
                    gamePanel.ui.showMessage("Speed up!");
                }
                case "Chest" -> {
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSoundEffect(4);
                }
            }
        }
    }
    public void draw(Graphics2D graphics2D){
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        graphics2D.drawImage(image, screenX, screenY, null);
    }
}
