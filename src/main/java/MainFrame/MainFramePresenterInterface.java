/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFrame;

import Base.BasePresenterInterface;

/**
 * Used in implementation of main frame GUI controls
 * @author brune
 */
public interface MainFramePresenterInterface extends BasePresenterInterface {
    /**
    * What should happen when add unit action is invoked
    */
    public void onAddDeviceClicked();
    
    public void onGroupsManagementClicked();
    /**
     * What should happen when refresh action is invoked
     */
    public void onRefresh();
}
