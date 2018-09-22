/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Weather;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author Bryll Joey Delfin
 */
public class WeatherDB {
    public ArrayList<Weather> getWeatherTrends( int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
             String query = "";
            if (year > 2016){
            query = " Select monthname(date) as 'months', year(date) as 'year', sum(tons_cane) as 'actual' from production where year = ? group by month(date) order by date; ";
            System.out.println(year + "YEARRRRRRRRrrrrrrrrrrr");
            }
            else{
            query = "SELECT monthname(week_ending) as 'months', week_ending, year, sum(rainfal) as 'rain', sum(actual) as 'actual' FROM weeklyestimate where year = ? group by month(week_ending) order by week_ending;;";
            }
            String query2 = "SELECT sum(amount)as 'rain' from rainfall where year(date) = ? group by month(date) order by date; ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt.setInt(1, year);
            pstmt2.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();
            ResultSet rs2 = pstmt2.executeQuery();
            ArrayList<Weather> wT = null;
            Weather w;
            if (rs.next()&&rs2.next()) { 
                 wT = new ArrayList<>();
                do {
                    w = new Weather();
                    w.setMonths(rs.getString("months"));
                    w.setYear(rs.getString("year"));
                    w.setProduction(rs.getDouble("actual"));
                    System.out.println(w.getProduction()+"NATRAJJJJJJJJJ");
                    w.setRainfall(rs2.getDouble("rain"));
                    System.out.println(w.getRainfall()+"NATRAJJJJJJJJJ");
                    wT.add(w);
                } while (rs.next()&&rs2.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            
            return wT;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
