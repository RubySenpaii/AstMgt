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
public class Fertilizer {
    private int year,fields_id;
    private String fertilizer;
    private Double first_dose,second_dose;

    /**
     * @return the first_dose
     */
    public Double getFirst_dose() {
        return first_dose;
    }

    /**
     * @param first_dose the first_dose to set
     */
    public void setFirst_dose(Double first_dose) {
        this.first_dose = first_dose;
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
     * @return the second_dose
     */
    public Double getSecond_dose() {
        return second_dose;
    }

    /**
     * @param second_dose the second_dose to set
     */
    public void setSecond_dose(Double second_dose) {
        this.second_dose = second_dose;
    }

    /**
     * @return the fertilizer
     */
    public String getFertilizer() {
        return fertilizer;
    }

    /**
     * @param fertilizer the fertilizer to set
     */
    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }
}
