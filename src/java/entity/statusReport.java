/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author nrajputsaldana
 */
public class statusReport {
private Date weekStarting,weekEnding;
    private int recsImplemented;
    private int recsSuggested;
    private int probsReported;
    private int probsSolved;
    
    private Farmer highestProdFarmer;
    private Farmer lowestProdFarmer;
    private Farmer highestYieldFarmer;
    private Farmer lowestYieldFarmer;
            

    /**
     * @return the recsImplemented
     */
    public int getRecsImplemented() {
        return recsImplemented;
    }

    /**
     * @param recsImplemented the recsImplemented to set
     */
    public void setRecsImplemented(int recsImplemented) {
        this.recsImplemented = recsImplemented;
    }

    /**
     * @return the recsSuggested
     */
    public int getRecsSuggested() {
        return recsSuggested;
    }

    /**
     * @param recsSuggested the recsSuggested to set
     */
    public void setRecsSuggested(int recsSuggested) {
        this.recsSuggested = recsSuggested;
    }

    /**
     * @return the ProbsReported
     */

    /**
     * @return the probsSolved
     */
    public int getProbsSolved() {
        return probsSolved;
    }

    /**
     * @param probsSolved the probsSolved to set
     */
    public void setProbsSolved(int probsSolved) {
        this.probsSolved = probsSolved;
    }

    /**
     * @return the weekEnding
     */
    public Date getWeekEnding() {
        return weekEnding;
    }

    /**
     * @param weekEnding the weekEnding to set
     */
    public void setWeekEnding(Date weekEnding) {
        this.weekEnding = weekEnding;
    }

    /**
     * @return the weekStarting
     */
    public Date getWeekStarting() {
        return weekStarting;
    }

    /**
     * @param weekStarting the weekStarting to set
     */
    public void setWeekStarting(Date weekStarting) {
        this.weekStarting = weekStarting;
    }

    /**
     * @return the probsReported
     */
    public int getProbsReported() {
        return probsReported;
    }

    /**
     * @param probsReported the probsReported to set
     */
    public void setProbsReported(int probsReported) {
        this.probsReported = probsReported;
    }

    /**
     * @return the highestProdFarmer
     */
    public Farmer getHighestProdFarmer() {
        return highestProdFarmer;
    }

    /**
     * @param highestProdFarmer the highestProdFarmer to set
     */
    public void setHighestProdFarmer(Farmer highestProdFarmer) {
        this.highestProdFarmer = highestProdFarmer;
    }

    /**
     * @return the lowestProdFarmer
     */
    public Farmer getLowestProdFarmer() {
        return lowestProdFarmer;
    }

    /**
     * @param lowestProdFarmer the lowestProdFarmer to set
     */
    public void setLowestProdFarmer(Farmer lowestProdFarmer) {
        this.lowestProdFarmer = lowestProdFarmer;
    }

    /**
     * @return the highestYieldFarmer
     */
    public Farmer getHighestYieldFarmer() {
        return highestYieldFarmer;
    }

    /**
     * @param highestYieldFarmer the highestYieldFarmer to set
     */
    public void setHighestYieldFarmer(Farmer highestYieldFarmer) {
        this.highestYieldFarmer = highestYieldFarmer;
    }

    /**
     * @return the lowestYieldFarmer
     */
    public Farmer getLowestYieldFarmer() {
        return lowestYieldFarmer;
    }

    /**
     * @param lowestYieldFarmer the lowestYieldFarmer to set
     */
    public void setLowestYieldFarmer(Farmer lowestYieldFarmer) {
        this.lowestYieldFarmer = lowestYieldFarmer;
    }

    

}
