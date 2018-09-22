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
public class Forum {
    private String farmer,message,status,comment_User, comment_message, comment_Date , title, id_and_status, problem_name,recommendation_name,name , fid, img_url, phase;
    private ArrayList<String> image;
    private Integer id , prob_id, counter, recom_id, Fields_id;
    private Date date_posted, date_started,date_ended;

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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * @return the counter
     */
    public Integer getCounter() {
        return counter;
    }

    /**
     * @param counter the counter to set
     */
    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    /**
     * @return the date_posted
     */
    public Date getDate_posted() {
        return date_posted;
    }

    /**
     * @param date_posted the date_posted to set
     */
    public void setDate_posted(Date date_posted) {
        this.date_posted = date_posted;
    }

    /**
     * @return the date_started
     */
    public Date getDate_started() {
        return date_started;
    }

    /**
     * @param date_started the date_started to set
     */
    public void setDate_started(Date date_started) {
        this.date_started = date_started;
    }

    /**
     * @return the date_ended
     */
    public Date getDate_ended() {
        return date_ended;
    }

    /**
     * @param date_ended the date_ended to set
     */
    public void setDate_ended(Date date_ended) {
        this.date_ended = date_ended;
    }

    /**
     * @return the comment_User
     */
    public String getComment_User() {
        return comment_User;
    }

    /**
     * @param comment_User the comment_User to set
     */
    public void setComment_User(String comment_User) {
        this.comment_User = comment_User;
    }

    /**
     * @return the comment_message
     */
    public String getComment_message() {
        return comment_message;
    }

    /**
     * @param comment_message the comment_message to set
     */
    public void setComment_message(String comment_message) {
        this.comment_message = comment_message;
    }

    /**
     * @return the comment_Date
     */
    public String getComment_Date() {
        return comment_Date;
    }

    /**
     * @param comment_Date the comment_Date to set
     */
    public void setComment_Date(String comment_Date) {
        this.comment_Date = comment_Date;
    }

    /**
     * @return the recom_id
     */
    public Integer getRecom_id() {
        return recom_id;
    }

    /**
     * @param recom_id the recom_id to set
     */
    public void setRecom_id(Integer recom_id) {
        this.recom_id = recom_id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the id_and_status
     */
    public String getId_and_status() {
        return id_and_status;
    }

    /**
     * @param id_and_status the id_and_status to set
     */
    public void setId_and_status(String id_and_status) {
        this.id_and_status = id_and_status;
    }

    /**
     * @return the Fields_id
     */
    public Integer getFields_id() {
        return Fields_id;
    }

    /**
     * @param Fields_id the Fields_id to set
     */
    public void setFields_id(Integer Fields_id) {
        this.Fields_id = Fields_id;
    }

    /**
     * @return the problem_name
     */
    public String getProblem_name() {
        return problem_name;
    }

    /**
     * @param problem_name the problem_name to set
     */
    public void setProblem_name(String problem_name) {
        this.problem_name = problem_name;
    }

    /**
     * @return the recommendation_name
     */
    public String getRecommendation_name() {
        return recommendation_name;
    }

    /**
     * @param recommendation_name the recommendation_name to set
     */
    public void setRecommendation_name(String recommendation_name) {
        this.recommendation_name = recommendation_name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fid
     */
    public String getFid() {
        return fid;
    }

    /**
     * @param fid the fid to set
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * @return the img_url
     */
    public String getImg_url() {
        return img_url;
    }

    /**
     * @param img_url the img_url to set
     */
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    /**
     * @return the image
     */
    public ArrayList<String> getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(ArrayList<String> image) {
        this.image = image;
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

  }
