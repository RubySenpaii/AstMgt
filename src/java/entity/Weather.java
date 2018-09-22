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
public class Weather {
    private Date week_ending;
    private Double rainfall, production;
    private String dates, months, year; 

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
     * @return the dates
     */
    public String getDates() {
        return dates;
    }

    /**
     * @param dates the dates to set
     */
    public void setDates(String dates) {
        this.dates = dates;
    }

    /**
     * @return the months
     */
    public String getMonths() {
        return months;
    }

    /**
     * @param months the months to set
     */
    public void setMonths(String months) {
        this.months = months;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }


}
