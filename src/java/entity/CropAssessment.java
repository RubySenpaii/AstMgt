/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Bryll Joey Delfin
 */
public class CropAssessment {
    private double prevArea, prevTons_Cane, thisArea, thisTons_Cane, todateArea, todateTonc_Cane, thisLKG, prevLKG, todateLKG; 
    private double estiArea, estiTons_Cane, percArea, percTons_Canel;
    private double rainfall;
    private String previous,thisweek,todate,percent, standing;
    private String estimated;
    private String particulars,dayname;
    private Date week_ending;
    /**
     * @return the prevArea
     */
    public double getPrevArea() {
        return prevArea;
    }

    /**
     * @param prevArea the prevArea to set
     */
    public void setPrevArea(double prevArea) {
        this.prevArea = prevArea;
    }

    /**
     * @return the prevTons_Cane
     */
    public double getPrevTons_Cane() {
        return prevTons_Cane;
    }

    /**
     * @param prevTons_Cane the prevTons_Cane to set
     */
    public void setPrevTons_Cane(double prevTons_Cane) {
        this.prevTons_Cane = prevTons_Cane;
    }

    /**
     * @return the thisArea
     */
    public double getThisArea() {
        return thisArea;
    }

    /**
     * @param thisArea the thisArea to set
     */
    public void setThisArea(double thisArea) {
        this.thisArea = thisArea;
    }

    /**
     * @return the thisTons_Cane
     */
    public double getThisTons_Cane() {
        return thisTons_Cane;
    }

    /**
     * @param thisTons_Cane the thisTons_Cane to set
     */
    public void setThisTons_Cane(double thisTons_Cane) {
        this.thisTons_Cane = thisTons_Cane;
    }

    /**
     * @return the todateArea
     */
    public double getTodateArea() {
        return todateArea;
    }

    /**
     * @param todateArea the todateArea to set
     */
    public void setTodateArea(double todateArea) {
        this.todateArea = todateArea;
    }

    /**
     * @return the todateTonc_Cane
     */
    public double getTodateTonc_Cane() {
        return todateTonc_Cane;
    }

    /**
     * @param todateTonc_Cane the todateTonc_Cane to set
     */
    public void setTodateTonc_Cane(double todateTonc_Cane) {
        this.todateTonc_Cane = todateTonc_Cane;
    }

    /**
     * @return the estiArea
     */
    public double getEstiArea() {
        return estiArea;
    }

    /**
     * @param estiArea the estiArea to set
     */
    public void setEstiArea(double estiArea) {
        this.estiArea = estiArea;
    }

    /**
     * @return the estiTons_Cane
     */
    public double getEstiTons_Cane() {
        return estiTons_Cane;
    }

    /**
     * @param estiTons_Cane the estiTons_Cane to set
     */
    public void setEstiTons_Cane(double estiTons_Cane) {
        this.estiTons_Cane = estiTons_Cane;
    }

    /**
     * @return the percArea
     */
    public double getPercArea() {
        return percArea;
    }

    /**
     * @param percArea the percArea to set
     */
    public void setPercArea(double percArea) {
        this.percArea = percArea;
    }

    /**
     * @return the percTons_Canel
     */
    public double getPercTons_Canel() {
        return percTons_Canel;
    }

    /**
     * @param percTons_Canel the percTons_Canel to set
     */
    public void setPercTons_Canel(double percTons_Canel) {
        this.percTons_Canel = percTons_Canel;
    }

    /**
     * @return the particulars
     */
    public String getParticulars() {
        return particulars;
    }

    /**
     * @param particulars the particulars to set
     */
    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    /**
     * @return the week_ending
     */
    public Date getWeek_ending() {
        return week_ending;
    }

    /**
     * @param week_ending the week_ending to set
     */
    public void setWeek_ending(Date week_ending) {
        this.week_ending = week_ending;
    }


    /**
     * @return the estimated
     */
    public String getEstimated() {
        return estimated;
    }

    /**
     * @param estimated the estimated to set
     */
    public void setEstimated(String estimated) {
        this.estimated = estimated;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * @param thisweek the thisweek to set
     */
    public void setThisweek(String thisweek) {
        this.thisweek = thisweek;
    }

    /**
     * @param todate the todate to set
     */
    public void setTodate(String todate) {
        this.todate = todate;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(String percent) {
        this.percent = percent;
    }

    /**
     * @param standing the standing to set
     */
    public void setStanding(String standing) {
        this.standing = standing;
    }

    /**
     * @param rainfall the rainfall to set
     */
    public void setRainfall(String rainfall) {
        this.setRainfall(rainfall);
    }

    /**
     * @return the rainfall
     */
    public double getRainfall() {
        return rainfall;
    }

    /**
     * @param rainfall the rainfall to set
     */
    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    /**
     * @return the previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * @return the thisweek
     */
    public String getThisweek() {
        return thisweek;
    }

    /**
     * @return the todate
     */
    public String getTodate() {
        return todate;
    }

    /**
     * @return the percent
     */
    public String getPercent() {
        return percent;
    }

    /**
     * @return the standing
     */
    public String getStanding() {
        return standing;
    }

    /**
     * @return the thisLKG
     */
    public double getThisLKG() {
        return thisLKG;
    }

    /**
     * @param thisLKG the thisLKG to set
     */
    public void setThisLKG(double thisLKG) {
        this.thisLKG = thisLKG;
    }

    /**
     * @return the prevLKG
     */
    public double getPrevLKG() {
        return prevLKG;
    }

    /**
     * @param prevLKG the prevLKG to set
     */
    public void setPrevLKG(double prevLKG) {
        this.prevLKG = prevLKG;
    }

    /**
     * @return the todateLKG
     */
    public double getTodateLKG() {
        return todateLKG;
    }

    /**
     * @param todateLKG the todateLKG to set
     */
    public void setTodateLKG(double todateLKG) {
        this.todateLKG = todateLKG;
    }

    /**
     * @return the dayname
     */
    public String getDayname() {
        return dayname;
    }

    /**
     * @param dayname the dayname to set
     */
    public void setDayname(String dayname) {
        this.dayname = dayname;
    }
    
}
