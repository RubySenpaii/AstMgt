/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ndrs
 */
public class municipalSummary {
       private int year,tfarmers,tfields;
       private double area,actual,forecasted,yield;
        private String district,municipality;

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
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * @return the actual
     */
    public double getActual() {
        return actual;
    }

    /**
     * @param actual the actual to set
     */
    public void setActual(double actual) {
        this.actual = actual;
    }

    /**
     * @return the forecasted
     */
    public double getForecasted() {
        return forecasted;
    }

    /**
     * @param forecasted the forecasted to set
     */
    public void setForecasted(double forecasted) {
        this.forecasted = forecasted;
    }

    /**
     * @return the yield
     */
    public double getYield() {
        return yield;
    }

    /**
     * @param yield the yield to set
     */
    public void setYield(double yield) {
        this.yield = yield;
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
     * @return the municipality
     */
    public String getMunicipality() {
        return municipality;
    }

    /**
     * @param municipality the municipality to set
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    /**
     * @return the tfarmers
     */
    public int getTfarmers() {
        return tfarmers;
    }

    /**
     * @param tfarmers the tfarmers to set
     */
    public void setTfarmers(int tfarmers) {
        this.tfarmers = tfarmers;
    }

    /**
     * @return the tfields
     */
    public int getTfields() {
        return tfields;
    }

    /**
     * @param tfields the tfields to set
     */
    public void setTfields(int tfields) {
        this.tfields = tfields;
    }

   
    
}
