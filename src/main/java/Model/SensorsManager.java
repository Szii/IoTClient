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
 * Class which stores the observer of panel with sensors and is able to fire events to all observer when method fireNotification is invoked
 * @author brune
 */
public class SensorsManager implements ManagerInterface<SensorsPanelObserver>{
     Queue<SensorsPanelObserver> observers = new ConcurrentLinkedQueue<SensorsPanelObserver>();
    /**
    * Adds a sensor panel observer to the collection
    * @param observer sensors panel observer to be removed
    */
     @Override
     public void addObserver(SensorsPanelObserver observer){
         observers.add(observer);
     }
     /**
     * Removes a sensor panel observer from the collection
     * @param observer sensors panel observer to be removed
     */
     @Override
     public void removeObserver(SensorsPanelObserver observer){
         observers.remove(observer);
     }
     /**
     * Fire notification to all sensor panel observers
     * @param notification Notification to be fired
     * @param unit_ID UnitObject, which possibly fired the notification
     */
     @Override
     public void fireNotification(String notification,UnitObject unit_ID){
        for(SensorsPanelObserver o : observers){
            if(notification.equals("sensorsChange")){
                o.onChangeNotification(unit_ID);
            }
        }
        
     }
}
