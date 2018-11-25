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
public class RepairLog {
    public static final String COLUMN_ASSET_TAG = "AssetTag";
    public static final String COLUMN_ARTICLE = "Article";
    public static final String COLUMN_COST = "Cost";
    public static final String COLUMN_REQUESTED_BY = "RequestedBy";
    public static final String COLUMN_REQUESTED_DATE = "RequestedDate";
    public static final String COLUMN_APPROVED_BY = "ApprovedBy";
    public static final String COLUMN_APPROVED_DATE = "ApprovedDate";
    public static final String COLUMN_REMARKS = "Remarks";
    
    public String AssetTag;
    public String Article;
    public double Cost;
    public int RequestedBy;
    public Date RequestedDate;
    public int ApprovedBy;
    public Date ApprovedDate;
    public String Remarks;
    
    public Employee Requester;
    public Employee Approver;
    public double TotalCost;
}
