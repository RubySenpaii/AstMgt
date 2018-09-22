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
public class ProdBarangay {
  
    private String Barangay;
    private ArrayList<Farmer> farmer;

    /**
     * @return the Barangay
     */
    public String getBarangay() {
        return Barangay;
    }

    /**
     * @param Barangay the Barangay to set
     */
    public void setBarangay(String Barangay) {
        this.Barangay = Barangay;
    }

    /**
     * @return the farmer
     */
    public ArrayList<Farmer> getFarmer() {
        return farmer;
    }

    /**
     * @param farmer the farmer to set
     */
    public void setFarmer(ArrayList<Farmer> farmer) {
        this.farmer = farmer;
    }
    
}
