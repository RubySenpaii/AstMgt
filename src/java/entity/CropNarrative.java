/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author ndrs
 */
public class CropNarrative {
    private int year;
    private int id;
    private Date weekending;
    private String district;
    private String dweather;
    private String dprice;
    private String dmill;
    private String dinput;
    private String dother;
    private String danalysis;
    private String dbanalysis;
    private String dbfindings;

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the weekending
     */
    public Date getWeekending() {
        return weekending;
    }

    /**
     * @param weekending the weekending to set
     */
    public void setWeekending(Date weekending) {
        this.weekending = weekending;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the dprice
     */
    public String getDprice() {
        return dprice;
    }

    /**
     * @param dprice the dprice to set
     */
    public void setDprice(String dprice) {
        this.dprice = dprice;
    }

    /**
     * @return the dmill
     */
    public String getDmill() {
        return dmill;
    }

    /**
     * @param dmill the dmill to set
     */
    public void setDmill(String dmill) {
        this.dmill = dmill;
    }

    /**
     * @return the dinput
     */
    public String getDinput() {
        return dinput;
    }

    /**
     * @param dinput the dinput to set
     */
    public void setDinput(String dinput) {
        this.dinput = dinput;
    }

    /**
     * @return the dother
     */
    public String getDother() {
        return dother;
    }

    /**
     * @param dother the dother to set
     */
    public void setDother(String dother) {
        this.dother = dother;
    }

    /**
     * @return the danalysis
     */
    public String getDanalysis() {
        return danalysis;
    }

    /**
     * @param danalysis the danalysis to set
     */
    public void setDanalysis(String danalysis) {
        this.danalysis = danalysis;
    }

    /**
     * @return the dweather
     */
    public String getDweather() {
        return dweather;
    }

    /**
     * @param dweather the dweather to set
     */
    public void setDweather(String dweather) {
        this.dweather = dweather;
    }

    /**
     * @return the dbanalysis
     */
    public String getDbanalysis() {
        return dbanalysis;
    }

    /**
     * @param dbanalysis the dbanalysis to set
     */
    public void setDbanalysis(String dbanalysis) {
        this.dbanalysis = dbanalysis;
    }

    /**
     * @return the dbfindings
     */
    public String getDbfindings() {
        return dbfindings;
    }

    /**
     * @param dbfindings the dbfindings to set
     */
    public void setDbfindings(String dbfindings) {
        this.dbfindings = dbfindings;
    }
}
