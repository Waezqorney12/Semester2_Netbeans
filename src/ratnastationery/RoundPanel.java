/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratnastationery;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class RoundPanel extends JPanel{
    
    public RoundPanel(){
        setOpaque(false);
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2=(Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int widht = getWidth();
        int height = getHeight();
        
        g2.fillRoundRect(0, 0, widht, height, 15, 15);
        
        super.paint(grphcs);
    }
    
    
}
