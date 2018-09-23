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
public class PurchaseRequest {
    public int PurchaseRequestId;
    public String PurchaseRequestNo;
    public String EntityName;
    public String Office;
    public String ResponsibilityCenterCode;
    public Date Date;
    public String Purpose;
    public int RequestedBy;
    public Date RequestedDate;
    public int ApprovedBy;
    public Date ApprovedDate;
    
    public Employee Requester;
    public Employee Approver;
}
