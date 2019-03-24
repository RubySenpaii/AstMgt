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
    public static final String COLUMN_PURCHASE_ORDER_ID = "PurchaseOrderId";
    public static final String COLUMN_PURCHASE_REQUEST_ID = "PurchaseRequestId";
    public static final String COLUMN_PURCHASE_ORDER_NO = "PurchaseOrderNo";
    public static final String COLUMN_SUPPLIER_ID = "SupplierId";
    public static final String COLUMN_ORDER_DATE = "OrderDate";
    public static final String COLUMN_REMARKS = "Remarks";
    public static final String COLUMN_DELIVERY_ADDRESS = "DeliveryAddress";
    public static final String COLUMN_DELIVERY_DATE = "DeliveryDate";
    public static final String COLUMN_DELIVERY_TERMS = "DeliveryTerms";
    public static final String COLUMN_PAYMENT_TERMS = "PaymentTerms";
    public static final String COLUMN_CONFORME_SUPPLIER = "ConformeSupplier";
    public static final String COLUMN_APPROVED_BY = "ApprovedBy";
    public static final String COLUMN_APPROVED_DATE = "ApprovedDate";
    public static final String COLUMN_ORS_NUMBER = "ORSNumber";
    public static final String COLUMN_ORS_DATE = "ORSDate";
    
    public int PurchaseOrderId;
    public int PurchaseRequestId;
    public String PurchaseOrderNumber;
    public int SupplierId;
    public Date OrderDate;
    public String Remarks;
    public String DeliveryAddress;
    public Date DeliveryDate;
    public String DeliveryTerms;
    public String PaymentTerm;
    public String ConformeSupplier;
    public int ApprovedBy;
    public Date ApprovedDate;
    public String ORSNumber;
    public Date ORSDate;
    
    public PurchaseRequest PurchaseRequest;
    public Supplier Supplier;
    public Employee Approver;
}
