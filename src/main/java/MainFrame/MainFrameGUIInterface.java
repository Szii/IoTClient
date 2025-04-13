/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFrame;

import Base.BaseGUIInterface;
import Components.Panel;
import javax.swing.JFrame;

/**
 * Contains functionalities of main frame GUI which can be invoked outside of class
 * @author brune
 */
public interface MainFrameGUIInterface extends BaseGUIInterface {
    /**
     * Clear the window of all components
     */
    
    public void clear();
 /**
  * Refresh to window itself
  */
    public void refresh();
   /**
    * Adds a main panel to the window, serving as content panel
    * @param p Panel to be added
    */
    public void addPanel(Panel p);
    /**
     * Returns frame used as main window
     * @return frame used as main window
     */
    public JFrame getFrame();
    
    
}
