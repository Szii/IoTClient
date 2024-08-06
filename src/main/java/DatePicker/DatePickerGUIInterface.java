/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatePicker;

import Base.BaseGUIInterface;
import java.util.Date;

/**
 * Contains behavior of GUI for picking the date
 * @author brune
 */
public interface DatePickerGUIInterface extends BaseGUIInterface{
    /**
     * Gets a selected date
     * @return Data in string format
     */
    public String getDate();
    /**
     * Removes the component from view
     */
    public void close();
}
