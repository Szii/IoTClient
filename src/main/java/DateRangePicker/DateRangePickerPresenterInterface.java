/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateRangePicker;

import Graph.ConfirmListener;
import Base.BasePresenterInterface;

/**
 * Contains behavior for picking range between two dates
 * @author brune
 */
public interface DateRangePickerPresenterInterface extends BasePresenterInterface{
    /**
     * Method gets a currently used confirm listener implementation
     * @return ConfirmListener currently used
     */
   public ConfirmListener getConfirmListener();
   /**
    * Method sets a confirm listener implementation
    * @param confirmListener ConfirmListener to be used
    */
   public void setConfirmListener(ConfirmListener confirmListener);
   
}
