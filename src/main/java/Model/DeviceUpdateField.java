/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author brune
 */
public enum DeviceUpdateField {
    THRESHOLD("threshold"),
    GROUP("group"),
    NICKNAME("nickname"),
    IRRIGATION_TIME("irrigation_time");

    private final String fieldName;

    DeviceUpdateField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static DeviceUpdateField fromString(String text) {
        for (DeviceUpdateField field : DeviceUpdateField.values()) {
            if (field.fieldName.equalsIgnoreCase(text)) {
                return field;
            }
        }
        throw new IllegalArgumentException("Invalid update field: " + text);
    }
}
