    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ndrs
 */
public class cropEstimate {
    private int year,selection,selectionlkg,id;
    private double area,actual,forecasted,forecast2,forecast3,difference,differencelkg,rainfall,tiller,temp, lkg;
    private double forecastlkg,forecastlkg2,forecastlkg3;
    private String district,municipality;
    private Date week_ending;
    private ArrayList<MonthlyCropEstimate> monthcropest;
    

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
     * @return the difference
     */
    public double getDifference() {
        return difference;
    }

    /**
     * @param difference the difference to set
     */
    public void setDifference(double difference) {
        this.difference = difference;
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
     * @return the forecast2
     */
    public double getForecast2() {
        return forecast2;
    }

    /**
     * @param forecast2 the forecast2 to set
     */
    public void setForecast2(double forecast2) {
        this.forecast2 = forecast2;
    }

    /**
     * @return the forecast3
     */
    public double getForecast3() {
        return forecast3;
    }

    /**
     * @param forecast3 the forecast3 to set
     */
    public void setForecast3(double forecast3) {
        this.forecast3 = forecast3;
    }

    /**
     * @return the monthcropest
     */
    public ArrayList<MonthlyCropEstimate> getMonthcropest() {
        return monthcropest;
    }

    /**
     * @param monthcropest the monthcropest to set
     */
    public void setMonthcropest(ArrayList<MonthlyCropEstimate> monthcropest) {
        this.monthcropest = monthcropest;
    }

    /**
     * @param rainfall the rainfall to set
     */
    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    /**
     * @return the rainfall
     */
    public double getRainfall() {
        return rainfall;
    }

    /**
     * @return the tiller
     */
    public double getTiller() {
        return tiller;
    }

    /**
     * @param tiller the tiller to set
     */
    public void setTiller(double tiller) {
        this.tiller = tiller;
    }

    /**
     * @return the temp
     */
    public double getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * @return the selection
     */
    public int getSelection() {
        return selection;
    }

    /**
     * @param selection the selection to set
     */
    public void setSelection(int selection) {
        this.selection = selection;
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
     * @return the selectionlkg
     */
    public int getSelectionlkg() {
        return selectionlkg;
    }

    /**
     * @param selectionlkg the selectionlkg to set
     */
    public void setSelectionlkg(int selectionlkg) {
        this.selectionlkg = selectionlkg;
    }

    /**
     * @return the forecastlkg
     */
    public double getForecastlkg() {
        return forecastlkg;
    }

    /**
     * @param forecastlkg the forecastlkg to set
     */
    public void setForecastlkg(double forecastlkg) {
        this.forecastlkg = forecastlkg;
    }

    /**
     * @return the forecastlkg2
     */
    public double getForecastlkg2() {
        return forecastlkg2;
    }

    /**
     * @param forecastlkg2 the forecastlkg2 to set
     */
    public void setForecastlkg2(double forecastlkg2) {
        this.forecastlkg2 = forecastlkg2;
    }

    /**
     * @return the forecastlkg3
     */
    public double getForecastlkg3() {
        return forecastlkg3;
    }

    /**
     * @param forecastlkg3 the forecastlkg3 to set
     */
    public void setForecastlkg3(double forecastlkg3) {
        this.forecastlkg3 = forecastlkg3;
    }

    /**
     * @return the differencelkg
     */
    public double getDifferencelkg() {
        return differencelkg;
    }

    /**
     * @param differencelkg the differencelkg to set
     */
    public void setDifferencelkg(double differencelkg) {
        this.differencelkg = differencelkg;
    }
    
}
