/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    public static final String COLUMN_SERIAL_NUMBER = "SerialNumber";
    public static final String COLUMN_ACQUISITION_COST = "AcquisitionCost";
    
    public int AssetId;
    public String AssetTag;
    public String Condition;
    public int Flag;
    public Date DateAcquired;
    public String SerialNumber;
    public double AcquisitionCost;
    
    public Asset Asset;
    public Employee CurrentUser;
    public ArrayList<AssetTracking> TrackingLogs;
    public ArrayList<AssetIncident> IncidentLogs;
    
    public String Status() {
        switch (this.Flag) {
            case 0: return "Disposed";
            case 1: return "Stocked";
            case 2: return "Being Used";
            case 3: return "Expiring";
            case 4: return "Extended";
            default: return "N/A";
        }
    }
    
    public double getDepreciation() {
        long diffInMillies = Calendar.getInstance().getTime().getTime() - this.DateAcquired.getTime();
        long days = TimeUnit.MILLISECONDS.toDays(diffInMillies);
        return this.AcquisitionCost / this.Asset.EstimatedUsefulLife * (days / 365.4);
    }
}
