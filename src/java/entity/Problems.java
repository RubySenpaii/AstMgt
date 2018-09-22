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
public class Problems {
    private Integer prob_id, totalFarms, fields_id,tSolutions, totalFields, count ,tPrograms ;
    private String prob_name, prob_details, barangay,municipality, status,type,impact,farmer,farm,validation,phase,user_name, district;
    private Double prob_loss,damage;
    private double percent_affected;
    private Date date_updated ,date_created;
    private ArrayList<Recommendation> reclist;

    /**
     * @return the prob_id
     */
    public Integer getProb_id() {
        return prob_id;
    }

    /**
     * @param prob_id the prob_id to set
     */
    public void setProb_id(Integer prob_id) {
        this.prob_id = prob_id;
    }

    /**
     * @return the prob_name
     */
    public String getProb_name() {
        return prob_name;
    }

    /**
     * @param prob_name the prob_name to set
     */
    public void setProb_name(String prob_name) {
        this.prob_name = prob_name;
    }

    /**
     * @return the prob_details
     */
    public String getProb_details() {
        return prob_details;
    }

    /**
     * @param prob_details the prob_details to set
     */
    public void setProb_details(String prob_details) {
        this.prob_details = prob_details;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the prob_loss
     */
    public Double getProb_loss() {
        return prob_loss;
    }

    /**
     * @param prob_loss the prob_loss to set
     */
    public void setProb_loss(Double prob_loss) {
        this.prob_loss = prob_loss;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the impact
     */
    public String getImpact() {
        return impact;
    }

    /**
     * @param impact the impact to set
     */
    public void setImpact(String impact) {
        this.impact = impact;
    }

    /**
     * @return the totalFarms
     */
    public Integer getTotalFarms() {
        return totalFarms;
    }

    /**
     * @param totalFarms the totalFarms to set
     */
    public void setTotalFarms(Integer totalFarms) {
        this.totalFarms = totalFarms;
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
     * @return the farm
     */
    public String getFarm() {
        return farm;
    }

    /**
     * @param farm the farm to set
     */
    public void setFarm(String farm) {
        this.farm = farm;
    }

    /**
     * @return the validation
     */
    public String getValidation() {
        return validation;
    }

    /**
     * @param validation the validation to set
     */
    public void setValidation(String validation) {
        this.validation = validation;
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
     * @return the fields_id
     */
    public Integer getFields_id() {
        return fields_id;
    }

    /**
     * @param fields_id the fields_id to set
     */
    public void setFields_id(Integer fields_id) {
        this.fields_id = fields_id;
    }

    /**
     * @return the percent_affected
     */
    

    /**
     * @return the tSolutions
     */
    public Integer gettSolutions() {
        return tSolutions;
    }

    /**
     * @param tSolutions the tSolutions to set
     */
    public void settSolutions(Integer tSolutions) {
        this.tSolutions = tSolutions;
    }

    /**
     * @return the phase
     */
    public String getPhase() {
        return phase;
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }

    /**
     * @return the date_created
     */
    public Date getDate_created() {
        return date_created;
    }

    /**
     * @param date_created the date_created to set
     */
    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @return the percent_affected
     */
    public double getPercent_affected() {
        return percent_affected;
    }

    /**
     * @param percent_affected the percent_affected to set
     */
    public void setPercent_affected(double percent_affected) {
        this.percent_affected = percent_affected;
    }

    /**
     * @return the totalFields
     */
    public Integer getTotalFields() {
        return totalFields;
    }

    /**
     * @param totalFields the totalFields to set
     */
    public void setTotalFields(Integer totalFields) {
        this.totalFields = totalFields;
    }

    /**
     * @return the damage
     */
    public Double getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(Double damage) {
        this.damage = damage;
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
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return the reclist
     */
    public ArrayList<Recommendation> getReclist() {
        return reclist;
    }

    /**
     * @param reclist the reclist to set
     */
    public void setReclist(ArrayList<Recommendation> reclist) {
        this.reclist = reclist;
    }

    /**
     * @return the tPrograms
     */
    public Integer gettPrograms() {
        return tPrograms;
    }

    /**
     * @param tPrograms the tPrograms to set
     */
    public void settPrograms(Integer tPrograms) {
        this.tPrograms = tPrograms;
    }
}
