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
public class PurchaseOrder {
    public int PurchaseOrderId;
    public int PurchaseRequestId;
    public String PurchaseOrderNumber;
    public int SupplierId;
    public Date OrderDate;
    public String ModeOfProcurement;
    public String Remarks;
    public String DeliveryAddress;
    public Date DeliveryDate;
    public String DeliveryTerm;
    public String PaymentTerm;
    public String ConformeSupplier;
    public Date ConformeDate;
    public int AuthorizedBy;
    public Date AuthorizedDate;
    public String ORSNumber;
    public Date ORSDate;
    public int ReceivedBy;
    public Date ReceivedDate;
    
    public PurchaseRequest PurchaseRequest;
    public Supplier Supplier;
    public Employee Authorizer;
    public Employee Approver;
    public Employee Receiver;
}
