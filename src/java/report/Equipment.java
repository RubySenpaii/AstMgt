/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import java.util.Date;

/**
 *
 * @author rubysenpaii
 */
public class Equipment {
    private String assetTag;
    private Date dateAcquired;
    private int estimatedUsefulLife;
    private String status;
    private double totalRepairs;
    private String currentUser;

    /**
     * @return the assetTag
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * @param assetTag the assetTag to set
     */
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     * @return the dateAcquired
     */
    public Date getDateAcquired() {
        return dateAcquired;
    }

    /**
     * @param dateAcquired the dateAcquired to set
     */
    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    /**
     * @return the estimatedUsefulLife
     */
    public int getEstimatedUsefulLife() {
        return estimatedUsefulLife;
    }

    /**
     * @param estimatedUsefulLife the estimatedUsefulLife to set
     */
    public void setEstimatedUsefulLife(int estimatedUsefulLife) {
        this.estimatedUsefulLife = estimatedUsefulLife;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the totalRepairs
     */
    public double getTotalRepairs() {
        return totalRepairs;
    }

    /**
     * @param totalRepairs the totalRepairs to set
     */
    public void setTotalRepairs(double totalRepairs) {
        this.totalRepairs = totalRepairs;
    }

    /**
     * @return the currentUser
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}