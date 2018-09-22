/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import controller.createNewProgram;
import entity.Calendar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryll Joey Delfin
 */
public class CalendarDB {

    public boolean insertCurrentDate(Date curdate, int cropyr) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query1 = "truncate configuration;";
            PreparedStatement pstmt = conn.prepareStatement(query1);
            pstmt.executeUpdate();
            pstmt.close();
            String query = "insert configuration values(?,?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDate(1, curdate);
            stmt.setInt(2, cropyr);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CalendarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void processNewTodayDate(Date todayDate) {
        Calendar cal = getInitialCurrentYearDetails(todayDate);
        insertCurrentDate(todayDate, cal.getYear());
    }

    public Calendar getCalendarTypes(Date todayDate) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(todayDate);
        CropBoardDB cdb = new CropBoardDB();
        int week_of_year = cdb.getWeekOfYear(todayDate.toString());
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH);
        int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
        Calendar calen = new Calendar();
        calen.setEday(day);
        calen.setEmonth(month);
        calen.setEyear(year);
        calen.setEweek(week_of_year);
        return calen;
    }

    public ArrayList<Calendar> getCaledarInputs() {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT * FROM crop_calendar;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Calendar> fT = null;
            Calendar f;
            if (rs.next()) {
                fT = new ArrayList<Calendar>();
                do {
                    f = new Calendar();
                    f.setDistrict(rs.getString("district"));
                    f.setYear(rs.getInt("year"));
                    f.setPhase(rs.getString("phase"));
                    f.setStarting(rs.getDate("date_starting"));
                    f.setEnding(rs.getDate("date_ending"));
                    fT.add(f);
                } while (rs.next());
            }
            rs.close();
            stmt.close();
            conn.close();

            return fT;
        } catch (SQLException ex) {
            Logger.getLogger(CalendarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Calendar getCurrWeekDetails() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select c.`current_date` as `current_date`,(SELECT (Select DATE(c.`current_date` + INTERVAL (0 - WEEKDAY(c.`current_date`)) DAY))) as thismonday,(SELECT DATE(c.`current_date` + INTERVAL (6 - WEEKDAY(c.`current_date`)) DAY)) as thisunday from configuration c;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            Calendar cal = null;

            if (rs.next()) {
                cal = new Calendar();
                cal.setTodayDate(rs.getDate("current_date"));
                cal.setMondayofWeek(rs.getDate("thismonday"));
                cal.setSundayofWeek(rs.getDate("thisSunday"));

            }
            rs.close();
            pstmt.close();
            conn.close();
            return cal;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public Calendar getDateWeekDetails(Date previousWeek) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT (Select DATE(? + INTERVAL (0 - WEEKDAY(?)) DAY)) as thismonday,(SELECT DATE(? + INTERVAL (6 - WEEKDAY(?)) DAY)) as thisSunday;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, previousWeek);
            pstmt.setDate(2, previousWeek);
            pstmt.setDate(3, previousWeek);
            pstmt.setDate(4, previousWeek);
            ResultSet rs = pstmt.executeQuery();
            Calendar cal = null;

            if (rs.next()) {
                cal = new Calendar();

                cal.setMondayofWeek(rs.getDate("thismonday"));
                cal.setSundayofWeek(rs.getDate("thisSunday"));

            }
            rs.close();
            pstmt.close();
            conn.close();
            return cal;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public ArrayList<Calendar> getCurrentYearDetails() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select cc.year,cc.district,cc.phase,c.current_date,(Select DATE(c.`current_date` + INTERVAL (0 - WEEKDAY(c.`current_date`)) DAY)) as thismonday,(SELECT DATE(c.`current_date` + INTERVAL (6 - WEEKDAY(c.`current_date`)) DAY)) as thisSunday from crop_calendar cc join configuration c where c.`current_date` between cc.date_starting and cc.date_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Calendar> list = null;

            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    Calendar cal = new Calendar();
                    cal.setYear(rs.getInt("year"));
                    cal.setDistrict(rs.getString("district"));
                    cal.setPhase(rs.getString("phase"));
                    cal.setTodayDate(rs.getDate("current_date"));
                    cal.setMondayofWeek(rs.getDate("thismonday"));
                    cal.setSundayofWeek(rs.getDate("thisSunday"));
                    list.add(cal);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Calendar getCurrentCropYearStartEnd() {
        try {
            ArrayList<Calendar> calist = getCurrentYearDetails();//gets the phases/today/crop yr
            int cropyr = calist.get(0).getYear();
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select min(date_starting) as st,max(date_ending) as ed from crop_calendar where year=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cropyr);
            ResultSet rs = pstmt.executeQuery();

            Calendar cal = null;
            if (rs.next()) {

                cal = new Calendar();
                cal.setYear(cropyr);
                cal.setStarting(rs.getDate("st"));
                cal.setEnding(rs.getDate("ed"));
            }
            rs.close();
            pstmt.close();
            conn.close();
            return cal;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Calendar getAllCropYearStartEnd() {
        try {
            ArrayList<Calendar> calist = getCurrentYearDetails();//gets the phases/today/crop yr
            int cropyr = calist.get(0).getYear();
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT DATE(t1.ds + INTERVAL (6 - WEEKDAY(t1.ds)) DAY) as st,DATE(t1.de + INTERVAL (6 - WEEKDAY(t1.de)) DAY) as ed,year from (select min(cc.date_starting) as ds,max(cc.date_ending) as de,cc.year from crop_calendar cc)t1;";
            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setInt(1, cropyr);
            ResultSet rs = pstmt.executeQuery();

            Calendar cal = null;
            if (rs.next()) {

                cal = new Calendar();
                cal.setYear(cropyr);
                cal.setStarting(rs.getDate("st"));
                cal.setEnding(rs.getDate("ed"));
            }
            rs.close();
            pstmt.close();
            conn.close();
            return cal;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Calendar getCropYearStartEnd(int year) {
        try {
            ArrayList<Calendar> calist = getCurrentYearDetails();//gets the phases/today/crop yr
            int cropyr = calist.get(0).getYear();
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT DATE(t1.ds + INTERVAL (6 - WEEKDAY(t1.ds)) DAY) as st,DATE(t1.de + INTERVAL (6 - WEEKDAY(t1.de)) DAY) as ed,year from (select min(cc.date_starting) as ds,max(cc.date_ending) as de,cc.year from crop_calendar cc where cc.year=? group by cc.year)t1;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();

            Calendar cal = null;
            if (rs.next()) {

                cal = new Calendar();
                cal.setYear(cropyr);
                cal.setStarting(rs.getDate("st"));
                cal.setEnding(rs.getDate("ed"));
            }
            rs.close();
            pstmt.close();
            conn.close();
            return cal;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<Calendar> getPhases(int cyear) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT *, DATE_FORMAT((date_starting+Interval 1 year),'%m/%d/%Y') as 'datepickers',DATE_FORMAT((date_ending+Interval 1 year),'%m/%d/%Y') as 'datepickere' FROM crop_calendar where year = ? order by date_starting;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cyear);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Calendar> list = null;

            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    Calendar cal = new Calendar();
                    cal.setYear(rs.getInt("year"));
                    cal.setDistrict(rs.getString("district"));
                    cal.setPhase(rs.getString("phase"));
                    cal.setStarting(rs.getDate("date_starting"));
                    cal.setEnding(rs.getDate("date_ending"));
                    cal.setDatepickers(rs.getString("datepickers"));
                    cal.setDatepickere(rs.getString("datepickere"));
                    list.add(cal);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<Integer> getRecommendationsByPhase(String phase) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT * FROM recommendations where improvement = 'Y' and phase = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, phase);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Integer> list = null;

            if (rs.next()) {
                list = new ArrayList<Integer>();
                do {
                    Calendar cal = new Calendar();
                    cal.setRecom_id(rs.getInt("id"));
                    list.add(cal.getRecom_id());
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Integer updateDurationByPhases(Calendar id) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "update `recommendations-fields` set duration_days = ? where status = 'Active' and Recommendations_id = ?  ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id.getDuration());
            pstmt.setInt(2, id.getRecom_id());
            int check = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return check;
        } catch (SQLException ex) {
            Logger.getLogger(CalendarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<String> getDistinctCropCalYears() {
        try {
            ArrayList<Calendar> calist = getCurrentYearDetails();
            Date todayDate = calist.get(0).getTodayDate();

            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select distinct year from crop_calendar where year<=? order by year desc;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, todayDate);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<String> list = null;
            if (rs.next()) {
                list = new ArrayList<String>();
                do {
                    list.add(rs.getString("year"));

                } while (rs.next());
            }
            pstmt.close();
            conn.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CalendarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //THIS METHOD IS ONLY FOR LOGIN ***DO NOT USE

    public Calendar getInitialCurrentYearDetails(Date todayDate) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT cc.year,cc.district,cc.phase from crop_calendar cc where ? between cc.date_starting and cc.date_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, todayDate);
            ResultSet rs = pstmt.executeQuery();
            Calendar cal = null;
            if (rs.next()) {

                cal = new Calendar();
                cal.setYear(rs.getInt("year"));
                cal.setDistrict(rs.getString("district"));
                cal.setPhase(rs.getString("phase"));
                cal.setTodayDate(todayDate);

            }

            rs.close();
            pstmt.close();
            conn.close();
            return cal;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean checkifMilling() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select cc.year,cc.district,cc.phase,c.current_date from crop_calendar cc join configuration c where cc.phase='Milling' and c.`current_date` between cc.date_starting and cc.date_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            Calendar cal = null;
            boolean chckr = false;
            if (rs.next()) {
                chckr = true;

            }

            rs.close();
            pstmt.close();
            conn.close();
            return chckr;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
 public boolean checkifMilling(Date date) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select cc.year,cc.district,cc.phase from crop_calendar cc  where cc.phase='Milling' and ? between cc.date_starting and cc.date_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            Calendar cal = null;
            boolean chckr = false;
            if (rs.next()) {
                chckr = true;

            }

            rs.close();
            pstmt.close();
            conn.close();
            return chckr;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    public boolean checkifTillering() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select cc.year,cc.district,cc.phase,c.current_date from crop_calendar cc join configuration c where cc.phase='Tillering' and c.`current_date` between cc.date_starting and cc.date_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            Calendar cal = null;
            boolean chckr = false;
            if (rs.next()) {
                chckr = true;

            }

            rs.close();
            pstmt.close();
            conn.close();
            return chckr;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean checkifvalidDate(Date todayDate) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select cc.year,cc.district,cc.phase,c.current_date from crop_calendar cc join configuration c where ? between cc.date_starting and cc.date_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, todayDate);
            ResultSet rs = pstmt.executeQuery();
            Calendar cal = null;
            boolean chckr = false;
            if (rs.next()) {
                chckr = true;

            }

            rs.close();
            pstmt.close();
            conn.close();
            return chckr;

        } catch (SQLException ex) {
            Logger.getLogger(CropEstimateDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Date convertStringtoSQLDate(String lined) {
        Date pdate = null;
        try {
            java.util.Date date = new SimpleDateFormat("MM/dd/yyyy").parse(lined);
            pdate = new java.sql.Date(date.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(CalendarDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdate;
    }

    public int updatePhaseDates(Calendar cal, int year, String district) {
        try {
            CalendarDB cdb = new CalendarDB();
            ArrayList<Calendar> calist = cdb.getCurrentYearDetails();
            int curyr = calist.get(0).getYear();
            System.out.println(curyr + "#*#*#*#*#*#*#*#*#*#*#*#*");
            // put functions here : previous week production, this week production
            int i = 0;
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "update crop_calendar set date_starting = ? ,date_ending = ? where year = ? and district = ? and phase = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(3, curyr);
            pstmt.setString(4, district);
            pstmt.setDate(1, cal.getStarting());
            pstmt.setDate(2, cal.getEnding());
            pstmt.setString(5, cal.getPhase());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return i;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(subjectiveRecDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int addPhasesDates(Calendar cal, int year, String district) {
        try {
            // put functions here : previous week production, this week production
            int i = 0;
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "Insert into crop_calendar(year,district,phase,date_starting,date_ending) values (?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, district);
            pstmt.setString(3, cal.getPhase());
            pstmt.setDate(4, cal.getStarting());
            pstmt.setDate(5, cal.getEnding());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return i;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(subjectiveRecDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
