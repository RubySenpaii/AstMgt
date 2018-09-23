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
public class Equipment {
    public static final String COLUMN_ASSET_ID = "AssetId";
    public static final String COLUMN_ASSET_TAG = "AssetTag";
    public static final String COLUMN_CONDITION = "Condition";
    public static final String COLUMN_FLAG = "Flag";
    public static final String COLUMN_DATE_ACQUIRED = "DateAcquired";
    public static final String COLUMN_ESTIMATED_USEFUL_LIFE = "EstimatedUsefulLife";
    
    public int AssetId;
    public String AssetTag;
    public String Condition;
    public int Flag;
    public Date DateAcquired;
    public int EstimatedUsefulLife;
    
    public Asset Asset;
}
