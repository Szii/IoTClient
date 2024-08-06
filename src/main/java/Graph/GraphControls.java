/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import ChartDataBuilder.ChartType;
import ChartDataBuilder.Size;
import ViewModel.Measurement;
import java.util.ArrayList;

/**
 * Implementation of this class enables implementation of creating a chart
 * @author brune
 */
public interface GraphControls {
    /**
     * Method creates a graph
     * @param chartType Type of graph
     * @param size Size of measured data
     * @param data Data to be used
     */
    public void createChart(ChartType chartType,Size size,ArrayList<Measurement> data);
}
