/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateRangePicker;

import Base.BaseGUIInterface;

/**
 * Contains behavior which is invokable on GUI when selecting date range
 * @author brune
 */
public interface DateRangePickerGUIInterface extends BaseGUIInterface {
    /**
     * Gets a lower bound date
     * @return Date as string
     */
    public String getFirstDate();
    /**
     * Gets a upper bound date
     * @return Date as string
     */
    public String getSecondDate();
    /**
     * Gets a lower bound date
     * @return Date as string
     */
    public String getFirstTime();
   /**
     * Gets a upper bound time
     * @return Time as string
     */
    public String getSecondTime();
    
    /**
     * Removes a GUI component from the view
     */
    public void close();
}
