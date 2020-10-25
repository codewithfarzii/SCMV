/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scmv;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author farzeen
 */
public class keysListener implements KeyListener{
    private Dashboard panel;

    @Override
    public void keyTyped(KeyEvent e) {
         System.out.println("logout");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_L)
            System.out.println("logout");
            else
             System.out.println("nologout");
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
         System.out.println("logout");
    }
    
}
