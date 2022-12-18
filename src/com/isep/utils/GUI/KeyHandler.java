package com.isep.utils.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP){
            // Move up
            up = true;
        }
        if(keyCode == KeyEvent.VK_DOWN){
            // Move down
            down = true;
        }
        if(keyCode == KeyEvent.VK_LEFT){
            // Move left
            left = true;
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            // Move right
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if(keyCode == KeyEvent.VK_UP){
                // Stop moving up
                up = false;
            }
            if(keyCode == KeyEvent.VK_DOWN){
                // Stop moving down
                down = false;
            }
            if(keyCode == KeyEvent.VK_LEFT){
                // Stop moving left
                left = false;
            }
            if(keyCode == KeyEvent.VK_RIGHT){
                // Stop moving right
                right = false;
            }
    }
}
