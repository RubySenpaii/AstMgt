/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.Date;

/**
 *
 * @author rubysenpaii
 */
public class ExpenditureTracking {
    public static final String COLUMN_YEAR = "Year";
    public static final String COLUMN_QUARTER = "Quarter";
    public static final String COLUMN_DIVISION = "Division";
    public static final String COLUMN_TIMESTAMP = "Timestamp";
    public static final String COLUMN_EQUIPMENT = "Equipment";
    public static final String COLUMN_SUPPLIES = "Supplies";
    
    public int Year;
    public String Quarter;
    public String Division;
    public Date Timestamp;
    public double Equipment;
    public double Supplies;
}
