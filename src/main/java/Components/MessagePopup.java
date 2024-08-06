/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;

/**
 * Component extending Popup for showing text messages
 * @author brune
 */
public class MessagePopup extends Popup{
     private Label textLabel;
    private Button okButton;
    private final String text;
    
    

    /**
     * Create new text popup
     * @param source source window
     * @param text popup text content
     */
    public MessagePopup(Window source,String text) {
        super(source);
        this.text = text;
        init();
    }
    /**
     * Create new text popup
     * @param text popup text content
     */
    public MessagePopup(String text) {
        super();
        this.text = text;
        init();
    }
    
    
    private void init(){
        
       
        this.getPanel().setLayout(new GridLayout(2,1));
  
        
        Panel textPanel = new Panel();
        Panel buttonPanel = new Panel();
      //  textPanel.setEmpty();
     //   buttonPanel.setEmpty();

        textLabel = new Label(text);     
         textPanel.add(textLabel);
         this.getPanel().add(textPanel);
        
        okButton = new Button("Ok");
        okButton.addActionListener((ActionEvent e)->{
            super.remove();
        });       
  
        buttonPanel.add(okButton);
        this.getPanel().add(buttonPanel);

        this.setVisible(true);      
        
    }
     
   /**
    * Sets a text shown by popup
    * @param text text to be shown
    */
   public void setText(String text){
     textLabel = new Label(text);
   }
   public void setFontSize(int size){
       
   }
}
