/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Calendar;
import entity.Forum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryll Joey Delfin
 */
public class ForumDB {
    public ArrayList<Forum> getForumsList() {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT * FROM posts p;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Forum> fT = null;
            Forum f;
            if (rs.next()) { 
                 fT = new ArrayList<Forum>();
                do {
                    f = new Forum();
                    f.setTitle(rs.getString("title"));
                    f.setId(rs.getInt("id"));
                   // f.setFarmer(rs.getString("Farmers_name"));
                   f.setFields_id(rs.getInt("Fields_id"));
                   f.setFarmer(getPostersName(f.getFields_id()));
                    f.setMessage(rs.getString("message"));
                    f.setDate_started(rs.getDate("date_started"));
                    f.setDate_posted(rs.getDate("date_posted"));
                    f.setStatus(rs.getString("status"));
                    f.setRecom_id(rs.getInt("Recommendations_id"));
                    f.setPhase(rs.getString("phase"));
                    if(f.getRecom_id() != null){
                            String recom_name = getRecommendationName(f.getRecom_id());
                            f.setRecommendation_name(recom_name);
                        }
                    else{
                        f.setRecommendation_name("N/A");
                    }
                    f.setProb_id(rs.getInt("Problems_id"));
                    if(f.getProb_id() != null){
                            String prob_name = getProblemName(f.getProb_id());
                            f.setProblem_name(prob_name);
                        }
                    else{
                            f.setProblem_name("N/A");
                    }
                    String line = f.getId()+","+f.getStatus();
                    f.setId_and_status(line);
                    fT.add(f);
                } while (rs.next());
            }
            rs.close();
            stmt.close();
            conn.close();
            
            return fT;
        } catch (SQLException ex) {
            Logger.getLogger(ProblemsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<Forum> getCommentDetails(Integer id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT p.id,p.message,p.date_started, p.Fields_id ,p.date_posted,c.Farmers_name as 'commentor',c.Users_username as 'commentormdo',c.message as 'comments',c.date as 'date_comment' FROM posts p left join comments c on p.id = c.Posts_id where p.id = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Forum> fT = new ArrayList<Forum>();
            Forum f = null;
                if (rs.next()) {   
                    do {
                        f = new Forum();
                        f.setId(rs.getInt("id"));
                        //f.setFarmer(rs.getString("Farmers_name"));
                        f.setFields_id(rs.getInt("Fields_id"));
                        f.setFarmer(getPostersName(f.getFields_id()));
                        f.setMessage(rs.getString("message"));
                        f.setDate_started(rs.getDate("date_started"));
                        f.setDate_posted(rs.getDate("date_posted"));
                        String test = rs.getString("commentor");
                        String test2 = rs.getString("commentormdo");
                        if(test!=null){
                            f.setComment_User(test);
                            f.setComment_message(rs.getString("comments"));
                            f.setComment_Date(rs.getString("date_comment"));
                        }
                        else if (test2!=null){
                            f.setComment_User(test2+ " (MDO)");
                            f.setComment_message(rs.getString("comments"));
                            f.setComment_Date(rs.getString("date_comment"));
                        }
                        
                        System.out.println(f.getComment_User()+ " USERS DATABASE");
                        fT.add(f);
                    } while (rs.next());
                }
                rs.close();
                pstmt.close();
                conn.close();            
            return fT;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Forum getForumDetail(Integer id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT p.id,p.Fields_id,p.message,p.date_started,p.date_posted,p.title ,p.status, p.Recommendations_id, p.Problems_id,p.phase  from posts p  where p.id = ?  ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Forum> fT = new ArrayList<Forum>();
            ArrayList<String> images = new ArrayList<String>();
            Forum f = null;
                if (rs.next()) {
                        f = new Forum();
                        f.setId(rs.getInt("id"));
                        f.setTitle(rs.getString("title"));
                        //f.setFarmer(rs.getString("Farmers_name"));
                        f.setFields_id(rs.getInt("Fields_id"));
                        f.setFarmer(getPostersName(f.getFields_id()));
                        f.setMessage(rs.getString("message"));
                        f.setDate_started(rs.getDate("date_started"));
                        f.setDate_posted(rs.getDate("date_posted"));
                        f.setStatus(rs.getString("status"));
                        f.setProb_id(rs.getInt("Problems_id"));
                        f.setPhase(rs.getString("phase"));
                        if(f.getProb_id() != null){
                            String prob_name = getProblemName(f.getProb_id());
                            f.setProblem_name(prob_name);
                        }
                        f.setRecom_id(rs.getInt("Recommendations_id"));
                        if(f.getRecom_id() != null){
                            String recom_name = getRecommendationName(f.getRecom_id());
                            f.setRecommendation_name(recom_name);
                        }
                        String line = f.getId()+","+f.getStatus();
                        f.setId_and_status(line);
                        System.out.println(id+ " ID POST  ID");
                        f.setImage(getImages(id));
                        fT.add(f);
                }
                rs.close();
                pstmt.close();
                conn.close();            
            return f;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }public Forum getForumDetailByName(String id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT p.id,p.Fields_id,p.message,p.date_started,p.date_posted,p.title ,p.status, p.Recommendations_id, p.Problems_id,p.phase  from posts p  where p.title = ?  and p.status = 'Accepted' ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,id);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Forum> fT = new ArrayList<Forum>();
            ArrayList<String> images = new ArrayList<String>();
            Forum f = null;
                if (rs.next()) {
                        f = new Forum();
                        f.setId(rs.getInt("id"));
                        f.setTitle(rs.getString("title"));
                        //f.setFarmer(rs.getString("Farmers_name"));
                        f.setFields_id(rs.getInt("Fields_id"));
                        f.setFarmer(getPostersName(f.getFields_id()));
                        f.setMessage(rs.getString("message"));
                        f.setDate_started(rs.getDate("date_started"));
                        f.setDate_posted(rs.getDate("date_posted"));
                        f.setStatus(rs.getString("status"));
                        f.setProb_id(rs.getInt("Problems_id"));
                        f.setPhase(rs.getString("phase"));
                        if(f.getProb_id() != null){
                            String prob_name = getProblemName(f.getProb_id());
                            f.setProblem_name(prob_name);
                        }
                        f.setRecom_id(rs.getInt("Recommendations_id"));
                        if(f.getRecom_id() != null){
                            String recom_name = getRecommendationName(f.getRecom_id());
                            f.setRecommendation_name(recom_name);
                        }
                        String line = f.getId()+","+f.getStatus();
                        f.setId_and_status(line);
                        System.out.println(id+ " ID POST  ID");
                        f.setImage(getImages(f.getId()));
                        fT.add(f);
                }
                rs.close();
                pstmt.close();
                conn.close();            
            return f;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Integer updateDateofPost(Integer id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "update posts set date_posted =?  where id = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            CalendarDB caldb=new CalendarDB();
            ArrayList<Calendar> cal= caldb.getCurrentYearDetails();
            pstmt.setDate(1,   cal.get(0).getTodayDate());
            pstmt.setInt(2,id);
            int check = pstmt.executeUpdate();
                System.out.println(check + " This is the test");
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<String> getImages(int post_id){
        try {
            System.out.println("entered images method");
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from mediafile where Posts_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,post_id);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Forum> fT = new ArrayList<Forum>();
            ArrayList<String> images = new ArrayList<String>();
                if (rs.next()) {
                    do{
                    String r= rs.getString("img_url");
                    String x=r.replace("\\", "/");
                    System.out.println(x);
                 images.add(x);
                    }while(rs.next());
                }
                rs.close();
                pstmt.close();
                conn.close();    
                 System.out.println("ending images method"+images.size());
            return images;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Integer approvePost(Integer id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "update posts set status = 'Accepted' where id = ?  ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            int check = pstmt.executeUpdate();
                System.out.println(check + " This is the test");
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Integer rejectPost(Integer id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "update posts set status = 'Rejected' where id = ?  ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,id);
            int check = pstmt.executeUpdate();
                System.out.println(check + " This is the test");
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Integer updatePostProblems(String title,Integer fields_id, Integer prob_id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "update posts set Problems_id = ? where title = ? and Fields_id = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, prob_id);
            pstmt.setString(2,title);
            pstmt.setInt(3, fields_id);
            int check = pstmt.executeUpdate();
                System.out.println(check + " This is the test");
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Integer updatePostRecommendations(String title,Integer fields_id, Integer recom_id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "update posts set Recommendations_id = ? where title = ? and Fields_id = ?  ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, recom_id);
            pstmt.setString(2,title);
            pstmt.setInt(3, fields_id);
            int check = pstmt.executeUpdate();
                System.out.println(check + " UPDATING POST DONE");
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getProblemCounter(Integer problem_id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT count(pf.Fields_id) as `counter` from posts p join problems pr on p.Problems_id = pr.id join `problems-fields` pf on pr.id = pf.Problems_id where pf.Problems_id = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,problem_id);
            ResultSet rs = pstmt.executeQuery();
            Integer count = 0;
                if (rs.next()) {
                        count += rs.getInt("counter");
                }
                rs.close();
                pstmt.close();
                conn.close();            
            return count;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getRecommendationCounter(Integer recom_id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "Select count(rf.Fields_id) as `counter` from posts p join recommendations r on p.Recommendations_id = r.id join `recommendations-fields` rf on r.id = rf.Recommendations_id where rf.Recommendations_id = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,recom_id);
            ResultSet rs = pstmt.executeQuery();
            Integer count = 0;
                if (rs.next()){ 
                        count += rs.getInt("counter");    
                }
                rs.close();
                pstmt.close();
                conn.close();            
            return count;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public String getPostersName(Integer fields_id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "Select Farmers_name from fields where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,fields_id);
            ResultSet rs = pstmt.executeQuery();
            String farmer = "";
                if (rs.next()){ 
                   farmer = rs.getString("Farmers_name");
                }
                rs.close();
                pstmt.close();
                conn.close();            
            return farmer;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String getProblemName(Integer prob_id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "Select name from problems where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,prob_id);
            ResultSet rs = pstmt.executeQuery();
            String prob = "Error";
                if (rs.next()){ 
                   prob = rs.getString("name");
                }
                rs.close();
                pstmt.close();
                conn.close();            
            return prob;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String getRecommendationName(Integer prob_id){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "Select recommendation from recommendations where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,prob_id);
            ResultSet rs = pstmt.executeQuery();
            String prob = "";
                if (rs.next()){ 
                   prob = rs.getString("recommendation");
                }
                rs.close();
                pstmt.close();
                conn.close();            
            return prob;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Integer addComments(Integer post_id,String message,Date date_commented, String username){
        int check=0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO comments(Posts_id,message,recommended,date,Users_username) values(?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, post_id);
            pstmt.setString(2, message);
            pstmt.setString(3, "N");
            pstmt.setDate(4, date_commented);
            pstmt.setString(5, username);
            check  = pstmt.executeUpdate();
            if(check!=0){
                check = getLastCommentsId();
            }
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public Integer addnewpost(String title, String message, Date date_s, Date date_p, String status, String phase, Integer rec_id){
        int check=0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO posts(title,message,date_started,date_posted,status,phase,Recommendations_id) values(?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, title);
            pstmt.setString(2, message);
            pstmt.setDate(3, date_s);
            pstmt.setDate(4, date_p);
            pstmt.setString(5, status);
            pstmt.setString(6, phase);
            pstmt.setInt(7, rec_id);
            check  = pstmt.executeUpdate();
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public Integer addCommentsUsers(Integer comments_id,String username){
        int check=0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO commentscoreuser values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(2, comments_id);
            pstmt.setString(1, username);
            check  = pstmt.executeUpdate();
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getLastCommentsId() {
        try {
            // put functions here : previous week production, this week production
            int i = 0;
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT  max(id)as id FROM comments ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                do{
                i = rs.getInt("id");
                }while(rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return i;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(subjectiveRecDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public Integer addRecommendationNotification(Integer fields_id,String message,Date date, Integer rec_id, Integer rec_field_id){
        int check=0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO notifications(disaster,received,Fields_id,message,date,Recommendations_id,Recommendations_Fields_id) values(?,?,?,?,?,?,?); ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "N");
            pstmt.setString(2, "N");
            pstmt.setInt(3, fields_id);
            pstmt.setString(4,message);
            pstmt.setDate(5, date);
            pstmt.setInt(6, rec_id);
            pstmt.setInt(7, rec_field_id);
            check  = pstmt.executeUpdate();
                
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public Integer addRedirectionNotification(Integer fields_id,String message,Date date, Integer post_id, Integer prob_id, Integer recom_id){
        int check=0;
        try {
            String query = "";
            Integer finid = 0;
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            if(prob_id == 0){
            query = "INSERT INTO notifications(disaster,received,Fields_id,message,date,Posts_id,`Recommendations_Fields_id`) values(?,?,?,?,?,?,?); ";  
            finid = recom_id;
            }
            else if(recom_id ==0){
            query = "INSERT INTO notifications(disaster,received,Fields_id,message,date,Posts_id,`Problems-Fields_id`) values(?,?,?,?,?,?,?); ";    
            finid = prob_id;
            }
            
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "N");
            pstmt.setString(2, "N");
            pstmt.setInt(3, fields_id);
            pstmt.setString(4,message);
            pstmt.setDate(5, date);
            pstmt.setInt(6, post_id);
            pstmt.setInt(7, finid);
            check  = pstmt.executeUpdate();
                
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public Integer addRecommendationProblem(Integer fields_id,String message,Date date, Integer prob_id){
        int check=0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO notifications(disaster,received,Fields_id,message,date,`Problems-Fields_id`) values(?,?,?,?,?,?); ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "N");
            pstmt.setString(2, "N");
            pstmt.setInt(3, fields_id);
            pstmt.setString(4,message);
            pstmt.setDate(5, date);
            pstmt.setInt(6, prob_id);
            check  = pstmt.executeUpdate();
                
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public Integer addCommentNotification(Integer fields_id,String message,Date date, Integer post_id){
        int check=0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO notifications(disaster,received,Fields_id,message,date,Posts_id) values(?,?,?,?,?,?); ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "N");
            pstmt.setString(2, "N");
            pstmt.setInt(3, fields_id);
            pstmt.setString(4,message);
            pstmt.setDate(5, date);
            pstmt.setInt(6, post_id);
            check  = pstmt.executeUpdate();
                
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public Integer addAlertNotification(Integer fields_id,String message,Date date, Integer disaster_id){
        int check=0;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "INSERT INTO notifications(disaster,received,Fields_id,message,date,DisasterAlerts_id) values(?,?,?,?,?,?); ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "Y");
            pstmt.setString(2, "N");
            pstmt.setInt(3, fields_id);
            pstmt.setString(4,message);
            pstmt.setDate(5, date);
            pstmt.setInt(6, disaster_id);
            check  = pstmt.executeUpdate();
                
                pstmt.close();
                conn.close();            
            return check;
                 }catch (SQLException ex) {
            Logger.getLogger(ForumDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}

