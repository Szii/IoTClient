/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import Constants.ConstantsList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Wrapper for JPanel
 * @author brune
 */
public class Panel extends JPanel {
     private static final long serialVersionUID = 1L;
    ImageIcon imageIcon;
    Label imageLabel ;
    String path;
    
    /**
     * Creates new empty Panel
     */
    public Panel(){
        super();
        init();
    }
    
    /**
    * Creates new empty panel with layout mananger.
    * @param comp layout manager to be set
    */
    public Panel(LayoutManager comp){
        super(comp);
        init();
        this.setVisible(true);
    }
   /**
    * Inits the panel, creating its border
    */
   public void init(){
       //addImage("background.jpg",1000,2000);
    //   this.setForeground(Color.white);
     //  this.setBackground(Color.black);
//       this.setBorder(ConstantsList.lineBorder);
      //  this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            this.setBorder(ConstantsList.classicBorder);
         this.setFocusable(false);
   }
   /**
    * Clear the fomratting of panel
    */
   public void setEmpty(){
      this.setBorder(null);  
      this.setForeground(Color.white);
      this.setBackground(Color.black);
   }
   
   /**
    * Adds a image into the panel
    * @param path Path to the image
    * @param height image height
    * @param width image width
    */
   public void addImage(String path,int height,int width){
      // this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
       // this.setMaximumSize(new Dimension(this.getWidth(), this.getHeight()));
       this.path = path;
            imageIcon = null;
            imageLabel = null;
            this.removeAll();
       this.revalidate();
       this.repaint();
         BufferedImage img = null;
        try {
            System.out.println("trying to read image: " + path);
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int x = this.getWidth();
        int y = this.getHeight();
        System.out.println("" + x +  " , " + y);
       Image dimg = img.getScaledInstance(width,height, Image.SCALE_SMOOTH);
       

       imageIcon = new ImageIcon(dimg);
       imageLabel = new Label(imageIcon);
       this.setLayout(new BorderLayout());
       this.add(imageLabel);
       this.revalidate();
       this.repaint();
     
   }
   
}
