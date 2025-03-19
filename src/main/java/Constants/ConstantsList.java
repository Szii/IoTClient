package Constants;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Constants list used in program
 * @author brune
 */
public class ConstantsList {

    /**
     * Stores the name of logged user
     */
    public static String loggedUser;
    /**
     * Token given by server
     */
    public static String token = "";

    /**
     * Stores a preset for default border
     */
    public static final Border classicBorder = BorderFactory.createEmptyBorder(10,10,10,10);
    
    /**
     * Stores a basic font
     */
    private  static final Font simpleFont = new Font("Helvetica Neue", Font.PLAIN,22);
    /**
     * Stores a thematic font
     */
    private static Font Fancy = getFancyFont(22);
  
    /**
     * Method creates a font from .tff file 
     * @param size size of font
     * @return Font described by a file
     */
    public static Font getFancyFont(long size){
         try {
           Fancy = Font.createFont(Font.TRUETYPE_FONT, new File("customFont.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("customFont.ttf")));
        } catch (FontFormatException | IOException ex) {
        }
         return Fancy;
         
     }    
}
