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
public class MonthlyCropEstimate {
    private int year;
    private Double tActual,tForc,lkg,forecastlkg;
    private String month,district;

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
     * @return the tActual
     */
    public Double gettActual() {
        return tActual;
    }

    /**
     * @param tActual the tActual to set
     */
    public void settActual(Double tActual) {
        this.tActual = tActual;
    }

    /**
     * @return the tForc
     */
    public Double gettForc() {
        return tForc;
    }

    /**
     * @param tForc the tForc to set
     */
    public void settForc(Double tForc) {
        this.tForc = tForc;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
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
     * @return the forecastlkg
     */
    public Double getForecastlkg() {
        return forecastlkg;
    }

    /**
     * @param forecastlkg the forecastlkg to set
     */
    public void setForecastlkg(Double forecastlkg) {
        this.forecastlkg = forecastlkg;
    }
}
