/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChartDataBuilder;

/**
 * Enum containing enumerators for Chart type
 * @author brune
 */
public enum ChartType {
    /**
     * Creates a chart with single series, if there is more series as input, only last series is taken into account.
     * Any previous series are removed.
     */
    SENSOR_MEASUREMENT_SINGLE,
    /**
     * Creates a chart with multiple series. Previous series are kept, if not deleted manually. If there are more series in the input, all of them are drawn.
     */
    SENSOR_MEASUREMENT_MULTIPLE
}
