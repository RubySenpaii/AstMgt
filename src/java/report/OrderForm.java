/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.util.ArrayList;

/**
 *
 * @author rubysenpaii
 */
public class OrderForm {
    private String title;
    private String fromName;
    private String fromAddress;
    private String fromContact;
    private String toName;
    private String toAddress;
    private String toContact;
    private String requestedBy;
    private String approvedBy;
    private ArrayList<OrderDetail> details;
    
    public OrderForm() {}

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the fromName
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * @param fromName the fromName to set
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * @return the fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * @param fromAddress the fromAddress to set
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * @return the fromContact
     */
    public String getFromContact() {
        return fromContact;
    }

    /**
     * @param fromContact the fromContact to set
     */
    public void setFromContact(String fromContact) {
        this.fromContact = fromContact;
    }

    /**
     * @return the toName
     */
    public String getToName() {
        return toName;
    }

    /**
     * @param toName the toName to set
     */
    public void setToName(String toName) {
        this.toName = toName;
    }

    /**
     * @return the toAddress
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * @param toAddress the toAddress to set
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * @return the toContact
     */
    public String getToContact() {
        return toContact;
    }

    /**
     * @param toContact the toContact to set
     */
    public void setToContact(String toContact) {
        this.toContact = toContact;
    }

    /**
     * @return the requestedBy
     */
    public String getRequestedBy() {
        return requestedBy;
    }

    /**
     * @param requestedBy the requestedBy to set
     */
    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    /**
     * @return the approvedBy
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * @return the details
     */
    public ArrayList<OrderDetail> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(ArrayList<OrderDetail> details) {
        this.details = details;
    }
}


