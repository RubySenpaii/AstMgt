/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tbd;

import java.util.Date;
import objects.Employee;
import objects.PurchaseOrder;

/**
 *
 * @author RubySenpaii
 */
public class PropertyAcknowledgementReceipt {
    public static final String COLUMN_ACKNOWLEDGEMENT_RECEIPT_ID = "AcknowledgementReceiptId";
    public static final String COLUMN_PAR_NO = "PARNo";
    public static final String COLUMN_REQUESTED_BY = "RequestedBy";
    public static final String COLUMN_REQUESTED_DATE = "RequestedDate";
    public static final String COLUMN_ISSUED_BY = "IssuedBy";
    public static final String COLUMN_ISSUED_DATE = "IssuedDate";
    
    public int AcknowledgementReceiptId;
    public String PARNo;
    public int RequestedBy;
    public Date RequestedDate;
    public int IssuedBy;
    public Date IssuedDate;
    
    public PurchaseOrder PurchaseOrder;
    public Employee Creator;
}
