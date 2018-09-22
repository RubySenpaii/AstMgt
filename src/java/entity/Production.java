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
public class Production {
    private int id, fields_id;
    private double area_harvested, tons_cane, lkg;
    private Date date;

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
     * @return the fields_id
     */
    public int getFields_id() {
        return fields_id;
    }

    /**
     * @param fields_id the fields_id to set
     */
    public void setFields_id(int fields_id) {
        this.fields_id = fields_id;
    }

    /**
     * @return the area_harvested
     */
    public double getArea_harvested() {
        return area_harvested;
    }

    /**
     * @param area_harvested the area_harvested to set
     */
    public void setArea_harvested(double area_harvested) {
        this.area_harvested = area_harvested;
    }

    /**
     * @return the tons_cane
     */
    public double getTons_cane() {
        return tons_cane;
    }

    /**
     * @param tons_cane the tons_cane to set
     */
    public void setTons_cane(double tons_cane) {
        this.tons_cane = tons_cane;
    }

    /**
     * @return the lkg
     */
    public double getLkg() {
        return lkg;
    }

    /**
     * @param lkg the lkg to set
     */
    public void setLkg(double lkg) {
        this.lkg = lkg;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
