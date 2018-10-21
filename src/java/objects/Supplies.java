/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.Date;

/**
 *
 * @author RubySenpaii
 */
public class Supplies {
    public static final String COLUMN_ASSET_ID = "AssetId";
    public static final String COLUMN_AMOUNT_ACQUIRED = "AmountAcquired";
    public static final String COLUMN_AMOUNT_DISPOSED = "AmountDisposed";
    public static final String COLUMN_TIMESTAMP = "Timestamp";
    public static final String COLUMN_TOTALQUANTITY = "TotalQuantity";
    
    public int AssetId;
    public int AmountAcquired;
    public int AmountDisposed;
    public Date Timestamp;
    public int TotalQuantity;
}
