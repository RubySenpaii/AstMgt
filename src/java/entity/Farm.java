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
 * @author Bryll Joey Delfin
 */
public class Farm {

    /**
     * @return the programs
     */
    public ArrayList<Programs> getPrograms() {
        return programs;
    }

    /**
     * @param programs the programs to set
     */
    public void setPrograms(ArrayList<Programs> programs) {
        this.programs = programs;
    }

    private int id,year;
    private String farmer,district,barangay,municipality;
    private SoilAnalysis soilanalysis;
    private CropValidation cropVal;
    private Fertilizer fertilizer;
    private Tillers tillers;
    private ArrayList<Recommendation> recommendation;
      private ArrayList<Programs> programs;
    private ArrayList<Fertilizer> fertlist;
    private ArrayList<Tillers> tillist;
    private ArrayList<Problems> problems;
    private Date date_updated;
    private double area,yield,totalHa,production,difYield;
    private String management_type,boundaries;
    private double latCenter,lngCenter;
  
    private boolean selected;

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
     * @return the farmer
     */
    public String getFarmer() {
        return farmer;
    }

    /**
     * @param farmer the farmer to set
     */
    public void setFarmer(String farmer) {
        this.farmer = farmer;
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
     * @return the barangay
     */
    public String getBarangay() {
        return barangay;
    }

    /**
     * @param barangay the barangay to set
     */
    public void setBarangay(String barangay) {
        this.barangay = barangay;
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
     * @return the management_type
     */
    public String getManagement_type() {
        return management_type;
    }

    /**
     * @param management_type the management_type to set
     */
    public void setManagement_type(String management_type) {
        this.management_type = management_type;
    }

    /**
     * @return the boundaries
     */
    public String getBoundaries() {
        return boundaries;
    }

    /**
     * @param boundaries the boundaries to set
     */
    public void setBoundaries(String boundaries) {
        this.boundaries = boundaries;
    }

    /**
     * @return the latCenter
     */
    public double getLatCenter() {
        return latCenter;
    }

    /**
     * @param latCenter the latCenter to set
     */
    public void setLatCenter(double latCenter) {
        this.latCenter = latCenter;
    }

    /**
     * @return the lngCenter
     */
    public double getLngCenter() {
        return lngCenter;
    }

    /**
     * @param lngCenter the lngCenter to set
     */
    public void setLngCenter(double lngCenter) {
        this.lngCenter = lngCenter;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
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
     * @return the totalHa
     */
    public double getTotalHa() {
        return totalHa;
    }

    /**
     * @param totalHa the totalHa to set
     */
    public void setTotalHa(double totalHa) {
        this.totalHa = totalHa;
    }

    /**
     * @return the date_updated
     */
    public Date getDate_updated() {
        return date_updated;
    }

    /**
     * @param date_updated the date_updated to set
     */
    public void setDate_updated(Date date_updated) {
        this.date_updated = date_updated;
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
     * @return the fertilizer
     */
    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    /**
     * @param fertilizer the fertilizer to set
     */
    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    /**
     * @return the tillers
     */
    public Tillers getTillers() {
        return tillers;
    }

    /**
     * @param tillers the tillers to set
     */
    public void setTillers(Tillers tillers) {
        this.tillers = tillers;
    }

    /**
     * @return the soilanalysis
     */
    public SoilAnalysis getSoilanalysis() {
        return soilanalysis;
    }

    /**
     * @param soilanalysis the soilanalysis to set
     */
    public void setSoilanalysis(SoilAnalysis soilanalysis) {
        this.soilanalysis = soilanalysis;
    }

    /**
     * @return the production
     */
    public double getProduction() {
        return production;
    }

    /**
     * @param production the production to set
     */
    public void setProduction(double production) {
        this.production = production;
    }

    /**
     * @return the cropVal
     */
    public CropValidation getCropVal() {
        return cropVal;
    }

    /**
     * @param cropVal the cropVal to set
     */
    public void setCropVal(CropValidation cropVal) {
        this.cropVal = cropVal;
    }

    /**
     * @return the difYield
     */
    public double getDifYield() {
        return difYield;
    }

    /**
     * @param difYield the difYield to set
     */
    public void setDifYield(double difYield) {
        this.difYield = difYield;
    }

    /**
     * @return the recommendation
     */
    public ArrayList<Recommendation> getRecommendation() {
        return recommendation;
    }

    /**
     * @param recommendation the recommendation to set
     */
    public void setRecommendation(ArrayList<Recommendation> recommendation) {
        this.recommendation = recommendation;
    }

    /**
     * @return the problems
     */
    public ArrayList<Problems> getProblems() {
        return problems;
    }

    /**
     * @param problems the problems to set
     */
    public void setProblems(ArrayList<Problems> problems) {
        this.problems = problems;
    }

    /**
     * @return the fertlist
     */
    public ArrayList<Fertilizer> getFertlist() {
        return fertlist;
    }

    /**
     * @param fertlist the fertlist to set
     */
    public void setFertlist(ArrayList<Fertilizer> fertlist) {
        this.fertlist = fertlist;
    }

    /**
     * @return the tillist
     */
    public ArrayList<Tillers> getTillist() {
        return tillist;
    }

    /**
     * @param tillist the tillist to set
     */
    public void setTillist(ArrayList<Tillers> tillist) {
        this.tillist = tillist;
    }

 




}
