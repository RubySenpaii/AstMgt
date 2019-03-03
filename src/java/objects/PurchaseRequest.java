/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author RubySenpaii
 */
public class PurchaseRequest {
    
    public static final String COLUMN_PURCHASE_REQUEST_ID = "PurchaseRequestId";
    public static final String COLUMN_PURCHASE_REQUEST_NO = "PurchaseRequestNo";
    public static final String COLUMN_RESPONSIBILITY_CENTER_CODE = "ResponsibilityCenterCode";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_PURPOSE = "Purpose";
    public static final String COLUMN_REQUESTED_BY = "RequestedBy";
    public static final String COLUMN_REQUESTED_DATE = "RequestedDate";
    public static final String COLUMN_APPROVED_BY = "ApprovedBy";
    public static final String COLUMN_APPROVED_DATE = "ApprovedDate";
    public static final String COLUMN_REMARKS = "Remarks";
    public static final String COLUMN_SUPPLIER_ID = "SupplierId";
    
    public int PurchaseRequestId;
    public String PurchaseRequestNo;
    public String ResponsibilityCenterCode;
    public Date Date;
    public String Purpose;
    public int RequestedBy;
    public Date RequestedDate;
    public int ApprovedBy;
    public Date ApprovedDate;
    public String Remarks;
    public int SupplierId;
    
    public Employee Requester;
    public Employee Approver;
    public ArrayList<AssetRequested> AssetsRequested;
    public Supplier Supplier;
    public double TotalCost;
}
