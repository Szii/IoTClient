/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChartDataBuilder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.RangeType;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * Class server as builder for chart, being wrapper for jfreeChart library. 
 * @author brune
 */
public class ChartBuilder {
    DataBuilder dataBuilder;
    /**
     * Creates new Chart builder.
     */
    public ChartBuilder(){}

    /**
     * Creates new Chart Builder.
     * @param dataBuilder Data builder implementation for work with chart data.
     */
    public void setDataBuilder(DataBuilder dataBuilder) {
        this.dataBuilder = dataBuilder;
    }
    
    /**
     * Makes chart panel which can be used as GUI component. 
     * @param type Type of graph
     * @param size Size of data
     * @param data List of series. One series corresponds to one measurement
     * @return 
     */
    public ChartPanel build(ChartType type,Size size,ArrayList<Series> data, String measurementType){
        
        if(type.equals(ChartType.SENSOR_MEASUREMENT_SINGLE)){
            
            for(Series s : data){
                s.setSize(size); 
            }   
            
            JFreeChart XYLineChart = null;
            
            if(measurementType.equals("TYPE_HUMIDITY")){
                  XYLineChart = getTimeSeriesChart(dataBuilder.getValueAndDateDataset(data,false),"Sensor measurement","Time","Moisture %"); 
            }
            
            if("TYPE_TEMPERATURE".equals(measurementType)){
                  XYLineChart = getTimeSeriesChart(dataBuilder.getValueAndDateDataset(data,false),"Sensor measurement","Time","Celsius"); 
            }
            
            ChartPanel chartPanel =  getChartPanel(XYLineChart);
            XYPlot plot = createPlot(XYLineChart,"dd.MM.yyyy HH:mm");
            plot.setRenderer(createRenderer()); 
            return chartPanel;
        }
        
        if(type.equals(ChartType.SENSOR_MEASUREMENT_MULTIPLE)){
            
            for(Series s : data){
                s.setSize(size); 
            }   
            
            JFreeChart XYLineChart = null;
            
            if("TYPE_HUMIDITY".equals(measurementType)){
                  XYLineChart = getTimeSeriesChart(dataBuilder.getValueAndDateDataset(data,true),"Sensor measurement","Time","Moisture %"); 
            }
            
            if("TYPE_TEMPERATURE".equals(measurementType)){
                  XYLineChart = getTimeSeriesChart(dataBuilder.getValueAndDateDataset(data,true),"Sensor measurement","Time","Celsius"); 
            }
            
            ChartPanel chartPanel =  getChartPanel(XYLineChart);
            XYPlot plot = createPlot(XYLineChart,"dd.MM.yyyy HH:mm");
            plot.setRenderer(createRenderer());
            return chartPanel;
        }
        return null;
        
   
    }
    

    /**
     * Creates the chart with x and y axes, where x is time axis and y is value in time
     * @param dataset Dataset to be used
     * @param name Name of chart to be shown
     * @param xAxis name of x axis
     * @param yAxis name of y axis
     * @return Returns x-y chart
     */
    private JFreeChart getTimeSeriesChart(TimeSeriesCollection dataset,String name,String xAxis,String yAxis){
        JFreeChart xylineChart = ChartFactory.createTimeSeriesChart(
        name ,
        xAxis ,
        yAxis ,
        dataset ,
        true , true , false);
        
        return xylineChart;
    }
    
    
    /**
     * Method creates renderer for x y shape line
     * @return Renderer
     */
    private XYLineAndShapeRenderer createRenderer(){
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      return renderer;
    }
    
    /**
     * Method takes the chart and create make a plot based on dateFormat
     * @param chart Chart to be used
     * @param dateFormat date format to be applied on x axis
     * @return Formatted XYplot
     */
    private XYPlot createPlot(JFreeChart chart,String dateFormat){
       final XYPlot plot = chart.getXYPlot( );
       DateAxis axis = (DateAxis) plot.getDomainAxis();
       axis.setDateFormatOverride(new SimpleDateFormat(dateFormat));
       axis.setAutoRange(false);
       NumberAxis domain = (NumberAxis) plot.getRangeAxis();
       domain.setRange(0, 100);  
      // domain.setAutoRangeIncludesZero(true);
       
       domain.setAutoRangeMinimumSize(100);
       domain.setAutoRange(false);
       return plot;
    }
    
    /**
     * Method puts chart into a chartPanel GUI component
     * @param chart Chart to be used
     * @return Chart as GUI component
     */
    private ChartPanel getChartPanel(JFreeChart chart){
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        return chartPanel;
    }
    
    /**
     * Removes the last series from the data
     */
    public void removeLastSeries(){
        dataBuilder.removeLastSeries();
    }
    
    /**
     * Removes all series
     */
    public void clearData(){
        dataBuilder.clearData();
    }
}
