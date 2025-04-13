/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Constants.ConstantsList;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Wrapper for JButton
 * @author brune
 */
public class Button extends JButton {
     private static final long serialVersionUID = 1L;
     ImageIcon imageIcon;
     Label imageLabel;
    
    /**
     * Creates new empty button
     */
    public Button(){
        super();
        init();
        
 
    }
    /**
     * Creates new button with a text as a content
     * @param text text to be inside a button
    */
    public Button(String text){
        super(text);
         init();
    }
     /**
      * Creates new button with a image as a content
      * @param icon Icon to be set as content of the button
      */
     public Button(Icon icon){
        super(icon);
         init();
    }
     
     
     void init(){
       this.setRolloverEnabled(true);
       this.setFocusable(false);
       this.setFont(ConstantsList.getFancyFont(22));
     }   
     
     
     void setEmpty(){
          this.setBorder(null);
     }
     
     void changeFontSize(int value){
         this.setFont(ConstantsList.getFancyFont(value));
     }
     
    void addImage(String path,int height,int width){
      // this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
       // this.setMaximumSize(new Dimension(this.getWidth(), this.getHeight()));
            imageIcon = null;
            imageLabel = null;
       this.removeAll();
         BufferedImage img = null;
         
        try {
            
            
 
            ImageIO.setUseCache(false);
            img = ImageIO.read(new File(path));
            
            
        } catch (IOException e) {
            System.out.println(path);
            e.printStackTrace();
        }
         Image dimg = img.getScaledInstance(width,height, Image.SCALE_FAST);
      

       imageIcon = new ImageIcon(dimg);
       imageLabel = new Label(imageIcon);
       this.setLayout(new BorderLayout());
       this.add(imageLabel);
       this.revalidate();
       this.repaint();
   
    }
    
}
