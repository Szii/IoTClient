/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPanel;

import Base.BasePresenterInterface;

/**
 * Used in implementation of main panel GUI controls
 * @author brune
 */
public interface MainPanelPresenterInterface extends BasePresenterInterface {
    /**
     * Method updates the whole screen of the application
     */
    public void updateGUI();
    /**
     * Method update the units which are shown
     */
    public void updateUnits();

}
