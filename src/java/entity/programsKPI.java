/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author ndrs
 */
public class programsKPI {
    private String kpi, program_name;
    private int kpi_year,tYears;
    
    private ArrayList<Double> values,aValues;
    /**
     * @return the kpi
     */
    public String getKpi() {
        return kpi;
    }

    /**
     * @param kpi the kpi to set
     */
    public void setKpi(String kpi) {
        this.kpi = kpi;
    }

    /**
     * @return the year
     */

    /**
     * @return the program_name
     */
    public String getProgram_name() {
        return program_name;
    }

    /**
     * @param program_name the program_name to set
     */
    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    /**
     * @return the kpi_year
     */
    public int getKpi_year() {
        return kpi_year;
    }

    /**
     * @param kpi_year the kpi_year to set
     */
    public void setKpi_year(int kpi_year) {
        this.kpi_year = kpi_year;
    }

    /**
     * @return the values
     */
    public ArrayList<Double> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    /**
     * @return the aValues
     */
    public ArrayList<Double> getaValues() {
        return aValues;
    }

    /**
     * @param aValues the aValues to set
     */
    public void setaValues(ArrayList<Double> aValues) {
        this.aValues = aValues;
    }

    /**
     * @return the tYears
     */
    public int gettYears() {
        return tYears;
    }

    /**
     * @param tYears the tYears to set
     */
    public void settYears(int tYears) {
        this.tYears = tYears;
    }


    
}
