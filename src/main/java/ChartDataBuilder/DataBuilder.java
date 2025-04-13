/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChartDataBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * Class server as builder for series to be used as time dependant series
 * @author brune
 */
public class DataBuilder {
    
    
    private final String outputPattern = "yyyy.MM.dd HH:mm:ss"; 
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputPattern);
    private TimeSeriesCollection dataset = new TimeSeriesCollection(); 
    private String inputPattern = "yyyy-MM-dd HH:mm:ss"; 
    
    /**
     * Creates new Data builder object
     */
    public DataBuilder(){};

    /**
     * Gets a current time format
     * @return Returns currently used time format
     */
    public String getInputPattern() {
        return inputPattern;
    }

    /**
     * Sets a current time format
     * @param inputPattern Time format do be used
     */
    public void setInputPattern(String inputPattern) {
        this.inputPattern = inputPattern;
    }

    /**
     * Creates time series from data series
     * @param series Series of data, which contains all data in one dimensional array
     * @return TimeSeries from classic series
     */
    private TimeSeries createTimeSeries(Series series){
        TimeSeries  sensorMeasurements = new TimeSeries (series.getName());     
        int moisture;
        LocalDateTime dateTime;
        for (int i = 0;i < series.getData().size();i++){      
            moisture = Integer.valueOf(series.getData().get(i).getValue());
            dateTime = createDateTimeFromString(series.getData().get(i).getDate());
            addMeasurementValueAndDate(sensorMeasurements,moisture,dateTime,series.getSize());
        }  
        return sensorMeasurements;
    }
        
    /**
     * Method converts string to date representation based on outputPattern
     * @param dateTimeString String to be converted
     * @return DateTime representation of string
     */
    private LocalDateTime createDateTimeFromString(String dateTimeString){
        try {  
            return LocalDateTime.parse(changeDateFormat(dateTimeString),formatter);
        } catch (ParseException ex) {
            Logger.getLogger(DataBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Method changes the string in input format to the string in outputFormat
     * @param dateTimeString String to be formatted into a output format 
     * @return String in outputFormat
     * @throws ParseException Exception is thrown when dateTimeString is not in inputFormat
     */
    private String changeDateFormat(String dateTimeString) throws ParseException{
        SimpleDateFormat fromUser = new SimpleDateFormat(inputPattern);
        SimpleDateFormat myFormat = new SimpleDateFormat(outputPattern); 
        return  myFormat.format(fromUser.parse(dateTimeString));
    }
    
    /**
     * Clears the data
     */
    private void clearTimeSeries(){
        dataset = new TimeSeriesCollection();
    }
    
  /**
   * Method gets currently used dataset with new series which are added on top of existing ones
   * @param series List of series to be added
   * @param keepPreviousSeries If true, previous series are kept
   * @return Dataset with series converted to TimeSeries, able to show dependency of values in time
   */
    public TimeSeriesCollection getValueAndDateDataset(ArrayList<Series> series,boolean keepPreviousSeries){ 
        if(!keepPreviousSeries){
            clearTimeSeries();
        }
        for(Series s : series){
            dataset.addSeries(createTimeSeries(s)); 
        }
        return dataset;
    }
    
    /**
   * Method gets currently used dataset with availability to add one series into the dataset
   * @param series Single series to be added
   * @param keepPreviousSeries If true, previous series are kept
   * @return Dataset with series converted to TimeSeries, able to show dependency of values in time
   */
    public TimeSeriesCollection getValueAndDateDataset(Series series,boolean keepPreviousSeries){ 
        if(!keepPreviousSeries){
            clearTimeSeries();
        }
        dataset.addSeries(createTimeSeries(series));  
        return dataset;
    }
    
    /**
     * Clears the stored dataSeries
     */
    public void clearData(){
        clearTimeSeries();
    }
    
    /**
     * Remmoves last series from dataset
     */
    public void removeLastSeries(){
        dataset.removeSeries(dataset.getSeriesCount() - 1);
    }

    /**
     * Adds one measurement into the series
     * @param sensorMeasurements Series which is used 
     * @param value value 
     * @param date date
     * @param size Size which is used when converting a date into a measurement time
     */
     private void addMeasurementValueAndDate(TimeSeries sensorMeasurements,int value,LocalDateTime date,Size size){
         switch(size){        
             case MINUTELY:{
               sensorMeasurements.addOrUpdate(new Minute(date.getMinute(),new Hour(date.getHour(),new Day(date.getDayOfMonth(),date.getMonthValue(),date.getYear()))),value); 
               break;
             }
             
             case HOURLY:{
                sensorMeasurements.addOrUpdate(new Hour(date.getHour(),new Day(date.getDayOfMonth(),date.getMonthValue(),date.getYear())),value);
                break;
             }
             
             case DAILY:{
                sensorMeasurements.addOrUpdate(new Day(date.getDayOfMonth(),date.getMonthValue(),date.getYear()),value);
                break;
             }
             
         }
      }

   
    
     
    
}
