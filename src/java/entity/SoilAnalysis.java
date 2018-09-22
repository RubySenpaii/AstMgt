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
public class SoilAnalysis {
    private int field_id;
    private double ph_lvl,organic_matter,phosphorus,potassium, nitrogen; 

    /**
     * @return the field_id
     */
    public int getField_id() {
        return field_id;
    }

    /**
     * @param field_id the field_id to set
     */
    public void setField_id(int field_id) {
        this.field_id = field_id;
    }

    /**
     * @return the ph_lvl
     */
    public double getPh_lvl() {
        return ph_lvl;
    }

    /**
     * @param ph_lvl the ph_lvl to set
     */
    public void setPh_lvl(double ph_lvl) {
        this.ph_lvl = ph_lvl;
    }

    /**
     * @return the organic_matter
     */
    public double getOrganic_matter() {
        return organic_matter;
    }

    /**
     * @param organic_matter the organic_matter to set
     */
    public void setOrganic_matter(double organic_matter) {
        this.organic_matter = organic_matter;
    }

    /**
     * @return the phosphorus
     */
    public double getPhosphorus() {
        return phosphorus;
    }

    /**
     * @param phosphorus the phosphorus to set
     */
    public void setPhosphorus(double phosphorus) {
        this.phosphorus = phosphorus;
    }

    /**
     * @return the potassium
     */
    public double getPotassium() {
        return potassium;
    }

    /**
     * @param potassium the potassium to set
     */
    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    /**
     * @return the nitrogen
     */
    public double getNitrogen() {
        return nitrogen;
    }

    /**
     * @param nitrogen the nitrogen to set
     */
    public void setNitrogen(double nitrogen) {
        this.nitrogen = nitrogen;
    }
}
