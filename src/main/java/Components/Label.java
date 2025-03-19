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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Wrapper for JLabel
 * @author brune
 */
public class Label extends JLabel {
    ImageIcon imageIcon;
    Label imageLabel;
    String path;
    public Label(String text){
        super(text,SwingConstants.CENTER);
        init();     
    }
    /**
     * Creates new label with image as its content
     * @param image Image to be set into a label
     */
    public Label(ImageIcon image){
        super(image);
       init();
    }
    /**
     * Creates new empty Label
     */
    public Label(){
        super("",SwingConstants.CENTER);
        init();
    }
    /**
     * Creates new label with icon to be set as its content
     * @param image icon to be set
     * @param horizontalAlignment alignment value
     */
    public Label(Icon image, int horizontalAlignment){
        super(image,horizontalAlignment);
        init();
    }
    /**
     * Create new label with text set as its content
     * @param text text to be set
     * @param horizontalAlignment alignment value 
     */
    public Label(String text, int horizontalAlignment){
        super(text,horizontalAlignment);
       init();
    }
    

    void init(){
       this.setFont(ConstantsList.getFancyFont(19));
      // this.setBorder(ConstantsList.classicBorder);
    //   this.setForeground(Color.white);
     //  this.setBackground(Color.black);
       this.setFocusable(false);
    }
    
    void changeFontSize(int value){
         this.setFont(ConstantsList.getFancyFont(value));
    }
    
    
       void addImage(String path,int height,int width){
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
   
   void setBackground(){
      addImage("background.jpg",1000,2000); 
   }
}
