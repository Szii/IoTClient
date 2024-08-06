/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import Base.BaseGUIInterface;

/**
 * Contains functionalities of Unit GUI which can be invoked outside of class
 * @author brune
 */
public interface UnitGUIInterface extends BaseGUIInterface{
    /**
     * Shows the name of the unit
     * @param name unit name
     */
      public void setUnitName(String name);
      /**
       * Shows the ID of the unit
       * @param id ID of unit
       */
      public void setID(String id);
      /**
       * Shows the status of the unit
       * @param status Status
       */
      public void setStatus(String status);
}
