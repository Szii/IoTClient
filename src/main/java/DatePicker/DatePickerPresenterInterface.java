/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatePicker;

import Base.BasePresenterInterface;
import Graph.ConfirmListener;

/**
 * Contains behavior of picking a time interval
 * @author brune
 */
public interface DatePickerPresenterInterface extends BasePresenterInterface{
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
