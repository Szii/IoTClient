/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import ViewModel.UnitObject;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class which stores the observer of panel with units and is able to fire events to all observer when method fireNotification is invoked
 * @author brune
 */
public class UnitsManager implements ManagerInterface<UnitsPanelObserver>{
   Queue<UnitsPanelObserver> observers = new ConcurrentLinkedQueue<UnitsPanelObserver>();
   /**
    * Adds a unit panel observer to the collection
    * @param observer unit panel observer to be added
    */
    @Override
    public void addObserver(UnitsPanelObserver observer) {
        observers.add(observer);
    }
    /**
     * Removes a unit panel observer from the collection
     * @param observer unit panel observer to be removed
     */
    @Override
    public void removeObserver(UnitsPanelObserver observer) {
       observers.remove(observer);
    }
    /**
     * Fire notification to all unit panel observers
     * @param notification Notification to be fired
     * @param unit_ID UnitObject, which possibly fired the notification
     */
    @Override
    public void fireNotification(String notification, UnitObject unit_ID) {
           for(UnitsPanelObserver o : observers){
            if(notification.equals("unitsChange")){
                o.onUnitsChange();
            }
        }
    }
    
}
