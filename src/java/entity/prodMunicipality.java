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
public class prodMunicipality {
  private String municipal;
  private int year;
    private ArrayList<ProdBarangay> brgy;

    /**
     * @return the municipal
     */
    public String getMunicipal() {
        return municipal;
    }

    /**
     * @param municipal the municipal to set
     */
    public void setMunicipal(String municipal) {
        this.municipal = municipal;
    }

    /**
     * @return the brgy
     */
    public ArrayList<ProdBarangay> getBrgy() {
        return brgy;
    }

    /**
     * @param brgy the brgy to set
     */
    public void setBrgy(ArrayList<ProdBarangay> brgy) {
        this.brgy = brgy;
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
    
}
