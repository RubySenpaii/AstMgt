/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ndrs
 */
public class Programs {
    private String prog_name,type,description,district,status;
    private Date date_created, date_initial,date_end;
    private int tFarms;
    private double progress;
    
  private ArrayList<String> probid;
    /**
     * @return the prog_name
     */
    public String getProg_name() {
        return prog_name;
    }

    /**
     * @param prog_name the prog_name to set
     */
    public void setProg_name(String prog_name) {
        this.prog_name = prog_name;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the date_initial
     */
    public Date getDate_initial() {
        return date_initial;
    }

    /**
     * @param date_initial the date_initial to set
     */
    public void setDate_initial(Date date_initial) {
        this.date_initial = date_initial;
    }

    /**
     * @return the date_end
     */
    public Date getDate_end() {
        return date_end;
    }

    /**
     * @param date_end the date_end to set
     */
    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    /**
     * @return the probid
     */
    public ArrayList<String> getProbid() {
        return probid;
    }

    /**
     * @param probid the probid to set
     */
    public void setProbid(ArrayList<String> probid) {
        this.probid = probid;
    }

    /**
     * @return the tFarms
     */
    public int gettFarms() {
        return tFarms;
    }

    /**
     * @param tFarms the tFarms to set
     */
    public void settFarms(int tFarms) {
        this.tFarms = tFarms;
    }

    /**
     * @return the progress
     */
    public double getProgress() {
        return progress;
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(double progress) {
        this.progress = progress;
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


           
}
