/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JDialog;

/**
 * Wrapper for JDialog
 * @author brune
 */
public abstract class Popup extends JDialog {
      private Panel popupPanel;
    Window source;
    
    /**
     * Creates new empty popup
     */
    public Popup(){   
       init();
  
    }
    /**
     * Creates new empty popup with window as its source.
     * @param source source window
     */
    public Popup(Window source)
    {
        this.source = source;    
        init(); 
    }
     
     
     
     private void init(){
      
         
        this.setSize(new Dimension(300,200));
        this.setLocationRelativeTo(null);
        setModal(true);
        setUndecorated(true);
        
        popupPanel = new Panel();
        this.add(popupPanel);
        
     }
    
     /**
      * Removes a popup
      */
    protected void remove(){
        this.dispose(); 
    }
    
    /**
     * Gets content panel of popup
     * @return content panel of popup
     */
    protected Panel getPanel(){
        return popupPanel;
    }
    
}
