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
    public int DeliveryInspectionId;
    public int PurchaseOrderId;
    public String Invoice;
    public String DeliveryReceipt;
    public int CreatedBy;
    public Date CreatedDate;
    public String Remarks;
    public String ManagementRemarks;
    public int FromBidding;
}
