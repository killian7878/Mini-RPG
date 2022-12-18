package com.isep.utils.object;

import com.isep.utils.GUI.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Boots extends SuperObject{
    GamePanel gp;
    public Boots(GamePanel gp) {
        name = "Boots";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
