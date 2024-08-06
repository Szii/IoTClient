/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Implementation of behavior when unit is changed. Methods of this behavior are invoked by corresponding observer manager
 * @author brune
 */
public interface UnitsPanelObserver extends Observer{
    /**
     * What should happen when event according to chaning the units is fired
     */
     public void onUnitsChange();
}
