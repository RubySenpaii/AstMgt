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
public class RequestForDeliveryInspection {
    public static final String COLUMN_DELIVERY_INSPECTION_ID = "DeliveryInspectionId";
    public static final String COLUMN_PURCHASE_ORDER_ID = "PurchaseOrderId";
    public static final String COLUMN_INVOICE = "Invoice";
    public static final String COLUMN_DELIVERY_RECEIPT = "DeliveryReceipt";
    public static final String COLUMN_CREATED_BY = "CreatedBy";
    public static final String COLUMN_CREATED_DATE = "CreatedDate";
    public static final String COLUMN_REMARKS = "Remarks";
    public static final String COLUMN_MANAGEMENT_REMARKS = "ManagementRemarks";
    public static final String COLUMN_FROM_BIDDING = "FromBidding";
    public static final String COLUMN_ASSIGNED_TO = "AssignedTo";
    public static final String COLUMN_APPROVED_BY = "ApprovedBy";
    
    public int DeliveryInspectionId;
    public int PurchaseOrderId;
    public String Invoice;
    public String DeliveryReceipt;
    public int CreatedBy;
    public Date CreatedDate;
    public String Remarks;
    public String ManagementRemarks;
    public int FromBidding;
    public int AssignedTo;
    public int ApprovedBy;
    
    public PurchaseOrder PurchaseOrder;
    public Employee Creator;
    public Employee Assigned;
    public Employee Approver;
}
