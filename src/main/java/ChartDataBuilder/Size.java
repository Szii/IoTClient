/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChartDataBuilder;

/**
 * Enum containing  enumerators for stating what time intervals should series cover
 * @author brune
 */
public enum Size {
    /**
     * Series will contains minutely measurements
     */
    MINUTELY,
    /**
     * Series wíll contains hourly measurements only
     */
    HOURLY,
    /**
     * Series will contains daily measurements only
     */
    DAILY
}
