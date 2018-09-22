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
public class CropBoard {
    private int id, year, weekofyear;
    private String district, region;
    private Double area, tc, lkg, rainfall, estimated, production;
    private Date week_ending;

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
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the area
     */
    public Double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * @return the tc
     */
    public Double getTc() {
        return tc;
    }

    /**
     * @param tc the tc to set
     */
    public void setTc(Double tc) {
        this.tc = tc;
    }

    /**
     * @return the lkg
     */
    public Double getLkg() {
        return lkg;
    }

    /**
     * @param lkg the lkg to set
     */
    public void setLkg(Double lkg) {
        this.lkg = lkg;
    }

    /**
     * @return the rainfall
     */
    public Double getRainfall() {
        return rainfall;
    }

    /**
     * @param rainfall the rainfall to set
     */
    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

    /**
     * @return the estimated
     */
    public Double getEstimated() {
        return estimated;
    }

    /**
     * @param estimated the estimated to set
     */
    public void setEstimated(Double estimated) {
        this.estimated = estimated;
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
     * @return the production
     */
    public Double getProduction() {
        return production;
    }

    /**
     * @param production the production to set
     */
    public void setProduction(Double production) {
        this.production = production;
    }

    /**
     * @return the weekofyear
     */
    public int getWeekofyear() {
        return weekofyear;
    }

    /**
     * @param weekofyear the weekofyear to set
     */
    public void setWeekofyear(int weekofyear) {
        this.weekofyear = weekofyear;
    }
    
}
