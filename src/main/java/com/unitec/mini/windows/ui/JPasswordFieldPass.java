/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unitec.mini.windows.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

/**
 *
 * @author leonel
 */
public class JPasswordFieldPass extends JPasswordField{
    public Icon prefixIcon;
    
     public JPasswordFieldPass(){
        setBorder(BorderFactory.createEmptyBorder(5, 20, 0, 5));
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        paintIcon(g);
         if (isFocusOwner()) {
            g.setColor(new Color(4, 88, 167));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        } else {
            g.setColor(new Color(142, 142, 142));
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.drawRect(1, 1, getWidth() - 3, getHeight() - 3);
        }
    }
    
    private void paintIcon(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(prefixIcon !=null){
            Image prefix  = ((ImageIcon)prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 0, y, this);

        }
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }
    
    private void initBorder(){
        int left = 20;
        if(prefixIcon !=null){
            left = prefixIcon.getIconWidth();
        }
        setBorder(BorderFactory.createEmptyBorder(5, left, 0, 5));
    }
}
