/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Base implementation of observer manager
 * @author brune
 * @param <T> Defines of type which will be used as collection to be managed
 */
public interface ManagerInterface<T extends Observer> {
    /**
     *  Method adds a observer to collection of observers manager by this manager
     * @param observer Any type of observer corresponding to the implementation
     */
     public void addObserver(T observer);
     /**
     *  Method removes a observer from collection of observers manager by this manager
     * @param observer Any type of observer corresponding to the implementation
     */
    public void removeObserver(T observer);
    
     /**
     * Fire notification to all observers managed by this manager
     * @param notification Notification to be fired
     * @param unit_ID Source object, which possibly fired the notification
     */
    public void fireNotification(String notification);
}
