/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 * Base for implementation of GUI component
 * @author brune
 */
public interface BaseGUIInterface {
    /**
     * Sets a controls for GUI component
     * @param presenter Controls to be set
     */
    public void setPresenter(BasePresenterInterface presenter);
    /**
     * Initialize the component by rendering its subcomponents
     */
    public void initView();
    /**
     * Update the component by fetching the new data
     */
    public void updateGUI();
}
