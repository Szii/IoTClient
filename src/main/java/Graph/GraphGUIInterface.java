/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Base.BaseGUIInterface;
import ViewModel.GroupViewModel;
import ViewModel.SensorViewModel;
import com.irrigation.Messages.MessageData.Device;
import java.util.ArrayList;
import org.jfree.chart.ChartPanel;


/**
 * Contains functionalities of Graph GUI component which can be invoked outside of class
 * @author brune
 */
public interface GraphGUIInterface extends BaseGUIInterface{
    
    /**
     * Sets a graph to be shown by this component
     * @param data graph to be shown
     */
    public void setGraph(ChartPanel data);
    
    /**
     * Sets a available units for selection
     * @param units available units
     */
    public void setUnitsComboBoxModel(ArrayList<GroupViewModel> units);
    /**
     * Sets available sensors for selection
     * @param sensors available sensors
     */
    public void setSensorsComboBoxModel(ArrayList<Device> sensors);
    /**
     * Gets a sensor selected
     * @return Selected sensor
     */
    public SensorViewModel getSelectedSensor();
    /**
     * Sets a visible time period 
     * @param from lower bound
     * @param to upper bound
     */
    public void setPeriodLabel(String from,String to);
    
    /**
     * Gets a currently shown time period lower bound 
     * @return time period lower bound
     */
    public String getFromPeriod();
    /**
     * Gets a currently shown time period upper bound 
     * @return time period upper bound
     */
    public String getToPeriod();
    
    /**
     * Sets the sensor which initiated the showing of this component
     * @param initiator Sensor to be set as initiator
     */
    public void setGraphInitiator(Device initiator);
    
    /**
     *  Gets a sensor which initiated showing of this component
     * @return ID of sensor
     */
    public String getGraphInitiator();
  
    

}
