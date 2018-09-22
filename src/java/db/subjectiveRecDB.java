/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Recommendation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author Bryll Joey Delfin
 */
public class subjectiveRecDB {
    public ArrayList<Recommendation> getTopRec(){
        try {
            // put functions here : previous week production, this week production
            ArrayList<Recommendation> rT = new ArrayList<Recommendation>();
            Recommendation r = null;
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = " (\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Germination'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)\n" +
"\n" +
"			union all\n" +
"\n" +
"			(\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Tillering'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)\n" +
"\n" +
"\n" +
"			union all\n" +
"\n" +
"			(\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Stalk Elongation'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)\n" +
"\n" +
"\n" +
"			union all\n" +
"\n" +
"			(\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Yield Formation'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)\n" +
"\n" +
"\n" +
"			union all\n" +
"\n" +
"			(\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Ripening'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)\n" +
"\n" +
"			union all\n" +
"\n" +
"			(\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Milling'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)\n" +
"\n" +
"\n" +
"			union all\n" +
"\n" +
"			(\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Planting'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)\n" +
"\n" +
"\n" +
"			union all\n" +
"\n" +
"			(\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Year'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)\n" +
"\n" +
"\n" +
"			union all\n" +
"\n" +
"			(\n" +
"			select r.recommendation, r.type, r.description, r.phase, count(r.id) as'count', r.id \n" +
"			from recommendations r join `recommendations-fields` rf on  r.id = rf.Recommendations_id\n" +
"			where rf.status = 'active' and rf.date <= (select current_date from configuration) and r.phase = 'Land Preparation'\n" +
"			group by r.id\n" +
"			order by count desc\n" +
"			limit 3\n" +
"			)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                do{
                    r = new Recommendation();
                    r.setId(rs.getInt("id"));
                    r.setRecommendation_name(rs.getString("recommendation"));
                    r.setType(rs.getString("type"));
                    r.setPhase(rs.getString("phase"));
                    r.setCounter(rs.getInt("count"));
                    rT.add(r);
                }while(rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return rT;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(subjectiveRecDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int addRecommendation(Recommendation recommend) {
        try {
            // put functions here : previous week production, this week production
            int i = 0;
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into recommendations (recommendation , type , description, improvement ,phase) values (?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, recommend.getRecommendation_name());
            pstmt.setString(2, recommend.getType());
            pstmt.setString(3, recommend.getDescription());
            pstmt.setString(4, recommend.getImprovement());
           // pstmt.setDate(5, recommend.getDate_create());
           // pstmt.setDate(5, recommend.getDate_start());
           // pstmt.setDate(6, recommend.getDate_end());
            pstmt.setString(5, recommend.getPhase());
           // pstmt.setDate(6, recommend.getTrigger_date());
            i = pstmt.executeUpdate();
            if(i==1){
                i=getLastRecommendationId();
            }
            pstmt.close();
            conn.close();
            return i;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(subjectiveRecDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int connectRecommendationtoProblem(int recom_id, int prob_id) {
        try {
            // put functions here : previous week production, this week production
            int i = 0;
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into `recommendations-problems` (Recommendations_id,Problems_id) values (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,recom_id);
            pstmt.setInt(2, prob_id);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return i;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(subjectiveRecDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int linkToRecoms(Integer field_id, Integer recomid, Date date, String status, Integer duration_days) {
        try {
            // put functions here : previous week production, this week production
            int i = 0;
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into `recommendations-fields`(Recommendations_id,Fields_id,status,date,duration_days) values(?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, recomid);
            pstmt.setInt(2, field_id);
            pstmt.setString(3, status);
            pstmt.setDate(4, date);
            pstmt.setInt(5, duration_days);
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return i;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(subjectiveRecDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int getLastRecommendationId() {
    try {
        // put functions here : previous week production, this week production
        int i = 0;
        DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
        Connection conn = myFactory.getConnection();
        String query = "SELECT  max(id)as id FROM sra.recommendations ;";
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
}
