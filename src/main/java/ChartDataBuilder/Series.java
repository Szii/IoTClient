/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChartDataBuilder;

import java.util.ArrayList;

/**
 * Class serves as wrapper for measured data, size of measured data and name of series
 * @author brune
 */
public class Series {
    
    String name;
    ArrayList<String> data;
    Size size;
    /**
     * Creates new series data holder
     * @param name Name of series
     * @param data Data contained within a series
     * @param size size of series
     * @see Size
     */
    public Series(String name, ArrayList<String> data, Size size) {
        this.name = name;
        this.data = data;
        this.size = size;
    }
    
    /**
    * Creates new series data holder
    * @param name Name of series
    * @param data Data contained within a series
    */
    public Series(String name, ArrayList<String> data) {
        this.name = name;
        this.data = data;
    }

    /**
     * Gets a size of series
     * @return time interval of measurement within the series
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets a size for series
     * @param size size to be set
     */
    public void setSize(Size size) {
        this.size = size;
    }
    /**
     * Gets name of series
     * @return name of series
     */
    public String getName() {
        return name;
    }
    /**
     * Sets name for series
     * @param name name of series
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets data within the series
     * @return data within the series
     */
    public ArrayList<String> getData() {
        return data;
    }
    /**
     * Set data which series will contains
     * @param data data to be set
     */
    public void setData(ArrayList<String> data) {
        this.data = data;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
}
