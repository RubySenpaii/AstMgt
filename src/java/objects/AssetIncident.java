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
public class AssetIncident {
    public static final String COLUMN_ASSET_TAG = "AssetTag";
    public static final String COLUMN_TIMESTAMP = "Timestamp";
    public static final String COLUMN_SEVERITY = "Severity";
    public static final String COLUMN_REMARKS = "Remarks";
    public static final String COLUMN_REPORTED_BY = "ReportedBy";
    
    public String AssetTag;
    public Date Timestamp;
    public int Severity;
    public String Remarks;
    public int ReportedBy;
    
    public Equipment Equipment;
    public Employee Reporter;
    
    public int lowCount;
    public int mediumCount;
    public int highCount;
    
    public String getSeverity() {
        switch(this.Severity) {
            case 1: return "Low";
            case 2: return "Medium";
            case 3: return "High";
            default: return "?";
        }
    }
    
    public String incidentResult() {
        if (highCount > 0) {
            return "Poor";
        } else if (mediumCount >= 2 || lowCount >= 6) {
            return "Marginal";
        } else if (mediumCount >= 1 || lowCount >= 3) {
            return "Adequate";
        } else if (lowCount >= 2) {
            return "Good";
        } else {
            return "Excellent";
        }
    }
    
    public String usefulLifeResult(double age, double estUsefulLife) {
        double value = age / estUsefulLife;
        if (value < 0.1) {
            return "Excellent";
        } else if (value < 0.25) {
            return "Good";
        } else if (value < 0.5) {
            return "Adequate";
        } else if (value < 0.75) {
            return "Marginal";
        } else {
            return "Poor";
        }
    }
    
    public String resultComparison(String incident, String useful) {
        if (getNumericValue(incident) > getNumericValue(useful)) {
            return useful;
        } else {
            return incident;
        }
    }
    
    public int getNumericValue(String sValue) {
        switch (sValue) {
            case "Excellent": return 5;
            case "Good": return 4;
            case "Adequate": return 3;
            case "Marginal": return 2;
            case "Poor": return 1;
            default: return -1;
        }
    }
}
