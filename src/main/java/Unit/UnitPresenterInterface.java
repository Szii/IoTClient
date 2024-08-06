/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import Base.BasePresenterInterface;

/**
 * Contains functionalities which can be used by GUI component controlled by implementation of this class
 * @author brune
 */
public interface UnitPresenterInterface extends BasePresenterInterface{
    /**
     * What should happen when remove action is invoked
     */
     public void onRemoveUnitClicked();
    
     /**
      * What should happen when change name action is invoked
      */
    public void onChangeNameClicked();
    
    /**
     * What should happen when show sensors action is invoked
     */
    public void onShowSensorsClicked();
}
