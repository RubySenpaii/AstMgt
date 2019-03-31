/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author rubysenpaii
 */
public class ExpenditureItem {
    public static final String COLUMN_YEAR = "Year";
    public static final String COLUMN_QUARTER = "Quarter";
    public static final String COLUMN_DIVISION = "Division";
    public static final String COLUMN_ASSET_ID = "AssetId";
    public static final String COLUMN_QUANTITY_LIMIT = "QuantityLimit";
    public static final String COLUMN_QUANTITY_ORDERED = "QuantityOrdered";
    
    public int Year;
    public String Quarter;
    public String Division;
    public int AssetId;
    public int QuantityLimit;
    public int QuantityOrdered;
}
