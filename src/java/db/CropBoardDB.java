/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.CropBoard;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryll Joey Delfin
 */
public class CropBoardDB {

    public ArrayList<CropBoard> getWeeklyProducedReport(String type, Integer year, String date) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id,district,week_ending ,weekofyear(week_ending) as wof, sum(area) as total_area, sum(actual) as total_actual, sum(lkg) as total_lkg \n"
                    + "from dashboarddata\n"
                    + "where year = ? and week_ending <= ? \n"
                    + "group by weekofyear(week_ending)\n"
                    + "order by week_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("total_area"));
                    c.setTc(rs.getDouble("total_actual"));
                    c.setLkg(rs.getDouble("total_lkg"));
                    c.setId(rs.getInt("id"));
                    c.setDistrict("district");
                    c.setWeek_ending(rs.getDate("week_ending"));
                    System.out.println(c.getWeek_ending()+"WOFWOF");
                    c.setWeekofyear(rs.getInt("week_ending"));
                    if (type.equalsIgnoreCase("TC")) {
                        c.setProduction(c.getTc());
                    } else if (type.equalsIgnoreCase("HA")) {
                        c.setProduction(c.getArea());
                    } else if (type.equalsIgnoreCase("LKG")) {
                        c.setProduction(c.getLkg());
                    } else {
                        System.out.println("TYPE : " + type);
                    }
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getCurrentWeeklyProducedReport(String type, Integer year, String date) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select date,weekofyear(date) as wof, sum(area_harvested) as total_area, sum(tons_cane) as total_actual, sum(lkg) as total_lkg \n"
                    + "from production\n"
                    + "where year = ? and date <= ? \n"
                    + "group by weekofyear(date)\n"
                    + "order by date;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("total_area"));
                    c.setTc(rs.getDouble("total_actual"));
                    c.setLkg(rs.getDouble("total_lkg"));
//                    c.setId(rs.getInt("id"));
//                    c.setDistrict("district");
                    c.setWeekofyear(rs.getInt("wof"));
                    c.setWeek_ending(rs.getDate("date"));
                    c.setRainfall(getcurrentrainfall(c.getWeekofyear()));
                    if (type.equalsIgnoreCase("TC")) {
                        c.setProduction(c.getTc());
                    } else if (type.equalsIgnoreCase("HA")) {
                        c.setProduction(c.getArea());
                    } else if (type.equalsIgnoreCase("LKG")) {
                        c.setProduction(c.getLkg());
                    } else {
                        System.out.println("TYPE : " + type);
                    }
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getWeeklyAverageProducedReport(String type, Integer year, String date) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id,district,week_ending ,weekofyear(week_ending), sum(area) as total_area, sum(actual) as total_actual, sum(lkg) as total_lkg \n"
                    + "from dashboarddata\n"
                    + "where year = ? and week_ending <= ? \n"
                    + "group by weekofyear(week_ending)\n"
                    + "order by week_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("total_area"));
                    c.setTc(rs.getDouble("total_actual"));
                    c.setLkg(rs.getDouble("total_lkg"));
                    c.setId(rs.getInt("id"));
                    c.setDistrict("district");
                    c.setWeek_ending(rs.getDate("week_ending"));
                    if (type.equalsIgnoreCase("TC")) {
                        c.setProduction(c.getTc());
                    } else if (type.equalsIgnoreCase("HA")) {
                        c.setProduction(c.getArea());
                    } else if (type.equalsIgnoreCase("LKG")) {
                        c.setProduction(c.getLkg());
                    } else {
                        System.out.println("TYPE : " + type);
                    }
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getWeeklyAverageProducedReportDetails(String type, Integer year, String date) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id,district,week_ending ,weekofyear(week_ending), avg(area) as total_area, avg(actual) as total_actual, avg(lkg) as total_lkg \n"
                    + "from dashboarddata\n"
                    + "where year = ? and week_ending <= ? \n"
                    + "group by weekofyear(week_ending)\n"
                    + "order by week_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("total_area"));
                    c.setTc(rs.getDouble("total_actual"));
                    c.setLkg(rs.getDouble("total_lkg"));
                    c.setId(rs.getInt("id"));
                    c.setDistrict("district");
                    System.out.println(c.getArea() + " AREAAAAAAAAAAAA");
                    c.setWeek_ending(rs.getDate("week_ending"));
                    if (type.equalsIgnoreCase("TC")) {
                        c.setProduction(c.getTc());
                    } else if (type.equalsIgnoreCase("HA")) {
                        c.setProduction(c.getArea());
                    } else if (type.equalsIgnoreCase("LKG")) {
                        c.setProduction(c.getLkg());
                    } else {
                    }
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getWeeklyProducedReportByRegion(String type, Integer year, String we, Integer weekofyear) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select d.id, d.week_ending, rd.region as place, sum(area) as total_area, sum(actual) as total_actual, sum(lkg) as total_lkg  \n"
                    + "from dashboarddata d join `ref-districts` rd on d.district = rd.district\n"
                    + "where year = ? and week_ending <= ? and weekofyear(week_ending) = ? \n"
                    + "group by rd.region\n"
                    + "order by d.week_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, we);
            pstmt.setInt(3, weekofyear);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("total_area"));
                    c.setTc(rs.getDouble("total_actual"));
                    c.setLkg(rs.getDouble("total_lkg"));
                    c.setId(rs.getInt("id"));
                    c.setDistrict(rs.getString("place"));
                    c.setWeek_ending(rs.getDate("week_ending"));
                    if (type.equalsIgnoreCase("TC")) {
                        c.setProduction(c.getTc());
                    } else if (type.equalsIgnoreCase("HA")) {
                        c.setProduction(c.getArea());
                    } else if (type.equalsIgnoreCase("LKG")) {
                        c.setProduction(c.getLkg());
                    } else {
                        System.out.println("TYPE : " + type);
                    }
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getCurrentWeeklyProducedReportByRegion(String type, Integer year, String we, Integer weekofyear) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select  p.date, rd.region as place, sum(p.area_harvested) as total_area, sum(p.tons_cane) as total_actual, sum(p.lkg) as total_lkg  \n"
                    + "from production p join fields f on p.Fields_id = f.id\n"
                    + "				join `ref-barangays` rb on rb.barangay = f.barangay and rb.municipality = f.municipality and rb.district = f.district\n"
                    + "                join `ref-municipalities` rm on rm.district = rb.district and rm.municipality = rb.municipality\n"
                    + "                join `ref-districts` rd on rd.district = rm.district\n"
                    + "where year = ? and p.date <= ? and weekofyear(p.date) = ? \n"
                    + "group by rd.region\n"
                    + "order by p.date;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, we);
            pstmt.setInt(3, weekofyear);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("total_area"));
                    c.setTc(rs.getDouble("total_actual"));
                    c.setLkg(rs.getDouble("total_lkg"));
                    c.setDistrict(rs.getString("place"));
                    c.setWeek_ending(rs.getDate("date"));
                    if (type.equalsIgnoreCase("TC")) {
                        c.setProduction(c.getTc());
                    } else if (type.equalsIgnoreCase("HA")) {
                        c.setProduction(c.getArea());
                    } else if (type.equalsIgnoreCase("LKG")) {
                        c.setProduction(c.getLkg());
                    } else {
                        System.out.println("TYPE : " + type);
                    }
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getCurrentWeeklyAvgProducedReportByRegion(String type, Integer year, String we, Integer weekofyear) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select date,weekofyear(date), round(avg(area_harvested),2) as avg_area, round(avg(tons_cane),2) as avg_actual , round(avg(lkg),2) as avg_lkg \n"
                    + "                    from production p \n"
                    + "                    where year = ? and date <= ? \n"
                    + "                    group by weekofyear(date)\n"
                    + "                    order by date;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, we);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("avg_area"));
                    c.setTc(rs.getDouble("avg_actual"));
                    c.setLkg(rs.getDouble("avg_lkg"));
                    c.setWeek_ending(rs.getDate("date"));
                    System.out.println(c.getWeek_ending() + " RAJ");
                    if (type.equalsIgnoreCase("TC")) {
                        c.setProduction(c.getTc());
                    } else if (type.equalsIgnoreCase("HA")) {
                        c.setProduction(c.getArea());
                    } else if (type.equalsIgnoreCase("LKG")) {
                        c.setProduction(c.getLkg());
                    } else {
                        System.out.println("TYPE : " + type);
                    }
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getWeeklyProducedReportByRegionT(Integer year, String we, Integer weekofyear) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select d.id, d.week_ending, rd.region as place, sum(area) as total_area, sum(actual) as total_actual, sum(lkg) as total_lkg  \n"
                    + "from dashboarddata d join `ref-districts` rd on d.district = rd.district\n"
                    + "where year = ? and week_ending <= ? and weekofyear(week_ending) = ? \n"
                    + "group by rd.region\n"
                    + "order by d.week_ending";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, we);
            pstmt.setInt(3, weekofyear);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("total_area"));
                    c.setTc(rs.getDouble("total_actual"));
                    c.setLkg(rs.getDouble("total_lkg"));
                    c.setId(rs.getInt("id"));
                    c.setDistrict(rs.getString("place"));
                    c.setWeek_ending(rs.getDate("week_ending"));
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getWeekOfYear(String date) {
        try {
            int wof = 0;
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select weekofyear(?) as weeks";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                wof = rs.getInt("weeks");
            }
            rs.close();
            pstmt.close();
            conn.close();

            return wof;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getWeeklyProducedReportByRegionDetails(Integer cropyear, String we, String region, Integer weekofyear) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select d.week_ending, d.id ,rd.region, sum(area) as area, sum(actual) as actual, sum(lkg) as lkg, sum(forecasted) as forecasted ,d.district\n"
                    + "from dashboarddata d join `ref-districts` rd on d.district = rd.district\n"
                    + "where year = ? and week_ending <= ? and weekofyear(week_ending) = ? and rd.region = ? \n"
                    + "group by  d.district\n"
                    + "order by d.week_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cropyear);
            pstmt.setString(2, we);
            pstmt.setInt(3, weekofyear);
            pstmt.setString(4, region);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(rs.getDouble("area"));
                    c.setTc(rs.getDouble("actual"));
                    c.setLkg(rs.getDouble("lkg"));
                    c.setId(rs.getInt("id"));
                    c.setDistrict(rs.getString("district"));
                    c.setWeek_ending(rs.getDate("week_ending"));
                    Double d = rs.getDouble("forecasted");
                    if (d != null) {
                        c.setEstimated(rs.getDouble("forecasted"));
                    } else {
                        c.setEstimated(0.00);
                    }
                    System.out.println("DETAILS TESTING");
                    System.out.println(c.getDistrict() + " It should work");
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropBoard> getCurrentWeeklyProducedReportByRegionDetails(Integer cropyear, String we, String region, Integer weekofyear) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select p.date, rd.district, sum(p.area_harvested) as area , sum(p.tons_cane) as actual, sum(p.lkg) as lkg \n"
                    + "from production p join fields f on p.Fields_id = f.id\n"
                    + "				join `ref-barangays` rb on rb.barangay = f.barangay and rb.municipality = f.municipality and rb.district = f.district\n"
                    + "                join `ref-municipalities` rm on rm.district = rb.district and rm.municipality = rb.municipality\n"
                    + "                join `ref-districts` rd on rd.district = rm.district\n"
                    + "where year = ? and p.date <= ? and weekofyear(p.date) = ? and rd.region = ?  \n"
                    + "group by rd.district\n"
                    + "order by p.date;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            DecimalFormat df = new DecimalFormat("#.##");
            pstmt.setInt(1, cropyear);
            pstmt.setString(2, we);
            pstmt.setInt(3, weekofyear);
            pstmt.setString(4, region);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropBoard> cT = null;
            CropBoard c;
            if (rs.next()) {
                cT = new ArrayList<CropBoard>();
                do {
                    c = new CropBoard();
                    c.setArea(Double.valueOf(df.format(rs.getDouble("area"))));
                    c.setTc(Double.valueOf(df.format(rs.getDouble("actual"))));
                    c.setLkg(Double.valueOf(df.format(rs.getDouble("lkg"))));
                    c.setDistrict(rs.getString("district"));
                    c.setWeek_ending(rs.getDate("date"));
                    c.setEstimated(0.00);
                    System.out.println("DETAILS TESTING");
                    System.out.println(c.getDistrict() + " It should work");
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Double getcurrentrainfall(Integer weekofyear) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT sum(r.amount) as rainfall FROM rainfall r join crop_calendar c where phase='Milling' and r.date between c.date_starting and c.date_ending and weekofyear(r.date) =? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, weekofyear);
            ResultSet rs = pstmt.executeQuery();
  
            ArrayList<Double> cT = null;
            Double c = 0.00;
            if (rs.next()) {
                cT = new ArrayList<Double>();
                do {
                    c = rs.getDouble("rainfall");
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return c;
        } catch (SQLException ex) {
            Logger.getLogger(CropBoardDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
