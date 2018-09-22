/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Calendar;
import entity.CropAssessment;
import entity.CropNarrative;
import entity.Farmer;
import entity.statusReport;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Bryll Joey Delfin
 */
public class CropAssessmentDB {

    public ArrayList<CropAssessment> getCropAssesmentRajversion(Integer week_of_year, Integer year, String tdate) {
        ArrayList<CropAssessment> cT = new ArrayList<CropAssessment>();
        ArrayList<CropAssessment> currT = new ArrayList<CropAssessment>();
        ArrayList<CropAssessment> prevT = new ArrayList<CropAssessment>();
        currT = getCropAssessmentReportForTheWeek(week_of_year, year, tdate);
        prevT = getPrevCropAssessmentReportForTheWeek(week_of_year, year, tdate);
        CropAssessment ca = new CropAssessment(); //area part
        CropAssessment ca2 = new CropAssessment(); // tons cane part
        CropAssessment ca3 = new CropAssessment(); // lkg part
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat commaformat = new DecimalFormat("#.00");
        commaformat.setGroupingUsed(true);
        commaformat.setGroupingSize(3);
        double etc = Double.valueOf(df.format(getTotalEstimatedTonsCane(year))); // change into the crop year
        double eah = Double.valueOf(df.format(getTotalEstimatedArea(year)));
        double elkg = Double.valueOf(df.format(getTotalEstimatedLKG(year)));
        ca.setParticulars("Area (ha)"); // crop asessment for area particulars
        ca.setEstimated(commaformat.format(eah));
        String prev = commaformat.format(prevT.get(0).getPrevArea());
        ca.setPrevious(prev); // previous
        String curr = commaformat.format(currT.get(0).getThisArea());
        ca.setThisweek(curr); // this week
        double todate = prevT.get(0).getPrevArea() + currT.get(0).getThisArea();
        Double percenta = 0.00;
        ca.setTodate(commaformat.format(todate)); // todate
        System.out.println(todate + "TODATE");
        System.out.println(eah + "EAH");
        percenta = Double.valueOf(df.format((todate / eah) * 100)); // difference
        ca.setPercent(percenta.toString());
        String standing = commaformat.format(Double.valueOf(df.format(eah - todate)));
        ca.setStanding(standing); // remaining
        ca.setWeek_ending(currT.get(0).getWeek_ending());
        ca2.setParticulars("Tons Cane (tc)"); // crop assessment for tons cane particulars
        ca2.setEstimated(commaformat.format(etc));
        String prevt = commaformat.format(prevT.get(0).getPrevTons_Cane());
        ca2.setPrevious(prevt); // previous
        String currt = commaformat.format(currT.get(0).getThisTons_Cane());
        ca2.setThisweek(currt); // thisweek
        double todate2 = prevT.get(0).getPrevTons_Cane() + currT.get(0).getThisTons_Cane();
        Double percentb = 0.00;
        ca2.setTodate(commaformat.format(todate2)); // todate
        percentb = Double.valueOf(df.format((todate2 / etc) * 100));
        ca2.setPercent(commaformat.format(percentb)); // difference
        String standingt = commaformat.format(Double.valueOf(df.format(etc - todate2)));
        ca2.setStanding(standingt); //remaining
        ca3.setParticulars("LKG (lkg)"); // crop assessment for lkg particulars
        ca3.setEstimated(df.format(elkg));
        String prevl = commaformat.format(prevT.get(0).getPrevLKG());
        ca3.setPrevious(prevl);
        String currl = commaformat.format(currT.get(0).getThisLKG());
        ca3.setThisweek(currl);
        double todate3 = prevT.get(0).getPrevLKG() + currT.get(0).getThisLKG();
        ca3.setTodate(commaformat.format(todate3));
        Double percentc = 0.00;
        percentc = Double.valueOf(df.format((todate3 / elkg) * 100));
        ca3.setPercent(commaformat.format(percentc));
        String standingl = commaformat.format(Double.valueOf(df.format(elkg - todate3)));
        ca3.setStanding(standingl);
        cT.add(ca);
        cT.add(ca2);
        cT.add(ca3);
        return cT;
    }

    public boolean checkExistingNarrative(int cyear, Date weekending) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from cropassessment where year=? and district=? AND week_ending=? order by year,district,week_ending";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cyear);
            pstmt.setString(2, "TARLAC");
            pstmt.setDate(3, weekending);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistingNarrative(Date sundayofweek) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from cropassessment where district=? AND week_ending=? order by year,district,week_ending";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, "TARLAC");
            pstmt.setDate(2, sundayofweek);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public CropNarrative viewCropNarrative(Date sundayofweek) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from cropassessment where district=? AND week_ending=? order by year,district,week_ending";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, "TARLAC");
            pstmt.setDate(2, sundayofweek);
            ResultSet rs = pstmt.executeQuery();
            CropNarrative cn = null;
            if (rs.next()) {
                cn = new CropNarrative();
                cn.setYear(rs.getInt("year"));
                cn.setWeekending(rs.getDate("week_ending"));
                cn.setDweather(rs.getString("weather"));
                cn.setDprice(rs.getString("prices_of_sugar"));
                cn.setDmill(rs.getString("mill_operation"));
                cn.setDinput(rs.getString("prices_of_inputs"));
                cn.setDother(rs.getString("others"));
                cn.setDanalysis(rs.getString("overall_analysis"));
                cn.setDbfindings(rs.getString("board_findings"));
                cn.setDbanalysis(rs.getString("board_overall"));
            }
            rs.close();
            pstmt.close();
            conn.close();
            return cn;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CropNarrative viewBoardCropNarrative(Date sundayofweek) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select year,board_findings,board_overall from cropassessment where district=? AND week_ending=?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, "TARLAC");
            pstmt.setDate(2, sundayofweek);
            ResultSet rs = pstmt.executeQuery();
            CropNarrative cn = null;
            if (rs.next()) {
                cn = new CropNarrative();
                cn.setYear(rs.getInt("year"));
                cn.setWeekending(sundayofweek);
                cn.setDbfindings(rs.getString("board_findings"));
                cn.setDbanalysis(rs.getString("board_overall"));
                 if (cn.getDbfindings() == null && cn.getDbanalysis() == null) {
                cn = null;
            }
            }
           
            rs.close();
            pstmt.close();
            conn.close();

            return cn;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean submitNarrative(CropNarrative cn) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into cropassessment(year,district,week_ending,weather,prices_of_sugar,mill_operation,prices_of_inputs,others,overall_analysis) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cn.getYear());
            pstmt.setString(2, "TARLAC");
            pstmt.setDate(3, cn.getWeekending());
            pstmt.setString(4, cn.getDweather());
            pstmt.setString(5, cn.getDprice());
            pstmt.setString(6, cn.getDmill());
            pstmt.setString(7, cn.getDinput());
            pstmt.setString(8, cn.getDother());
            pstmt.setString(9, cn.getDanalysis());
            int i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return i == 1;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean submitBoardNarrative(CropNarrative cn) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE cropassessment SET board_findings=?, board_overall=? WHERE year=? and district=? and week_ending=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cn.getDbfindings());
            pstmt.setString(2, cn.getDbanalysis());
            pstmt.setInt(3, cn.getYear());
            pstmt.setString(4, "TARLAC");
            pstmt.setDate(5, cn.getWeekending());

            int i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return i == 1;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public ArrayList<CropAssessment> getRainfallByDate(Date startDate, Date endDate) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select *,dayname(date) as dayname from rainfall where date between ? and ? group by date;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropAssessment> rainfall = null;
            CropAssessment rain;
            if (rs.next()) {
                rainfall = new ArrayList<>();
                do {
                    rain = new CropAssessment();
                    rain.setRainfall(rs.getDouble("amount"));
                    rain.setWeek_ending(rs.getDate("date"));
                    rain.setDayname(rs.getString("dayname"));
                    rainfall.add(rain);

                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return rainfall;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Date getPreviousWeek(Date endDate) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select date_add(?,INTERVAL -7 DAY) as previousweek;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setDate(1, endDate);

            ResultSet rs = pstmt.executeQuery();
            Date value = null;
            if (rs.next()) {
                value = rs.getDate("previousweek");
            }
            rs.close();
            pstmt.close();
            conn.close();

            return value;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Date getNextWeek(Date endDate) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select date_add(?,INTERVAL 7 DAY) as previousweek;";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setDate(1, endDate);

            ResultSet rs = pstmt.executeQuery();
            Date value = null;
            if (rs.next()) {
                value = rs.getDate("previousweek");
            }
            rs.close();
            pstmt.close();
            conn.close();

            return value;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Calendar> getCropAssessmentList(String year) {
        
        CalendarDB caldb = new CalendarDB();
        Calendar cal = null;
        if (year.equalsIgnoreCase("all")) {
            cal = caldb.getAllCropYearStartEnd();
        } else {
            cal = caldb.getCropYearStartEnd(Integer.parseInt(year));
        }
        ArrayList<Date> datelist = getAllWeekDatesfromRange(cal.getStarting(), cal.getEnding());
        ArrayList<Calendar> calist = new ArrayList<>();
        for (int i = 0; i < datelist.size(); i++) {
            Calendar c = caldb.getInitialCurrentYearDetails(datelist.get(i));
            calist.add(c);
        }

        return calist;
    }

    public ArrayList<Date> getAllWeekDatesfromRange(Date min, Date max) {
        CalendarDB caldb = new CalendarDB();
        ArrayList<Calendar> cal = caldb.getCurrentYearDetails();
        Date thissun = cal.get(0).getSundayofWeek();
        ArrayList<Date> list = new ArrayList<>();
        Date curdate = min, thedate;

        if (max.before(thissun) || max.equals(thissun)) {
            thedate = max;
        } else {
            thedate = thissun;
        }

        do {
            list.add(curdate);
            curdate = getNextWeek(curdate);

        } while (!curdate.after(thedate));
        return list;
    }

    public ArrayList<statusReport> getAllStatusReports(Date week) {
        ArrayList<statusReport> srlist = new ArrayList<>();
//        srlist.add(getStatusReportByWeek((Date.valueOf("2016-12-25"))));
        //gets the previous week from input date 
        srlist.add(getStatusReportByWeek((getPreviousWeek(week))));

        srlist.add(getStatusReportByWeek(week));

        return srlist;
    }

    public statusReport getStatusReportByWeek(Date week) {
        CalendarDB caldb = new CalendarDB();

        caldb.getCurrentYearDetails();

        Calendar cal = new Calendar();
        cal = caldb.getDateWeekDetails(week);

        statusReport sr = new statusReport();
        sr.setWeekEnding(cal.getSundayofWeek());
        sr.setWeekStarting(cal.getMondayofWeek());

        // statistically related
        sr.setHighestProdFarmer(getProdFarmerbyDate(cal.getMondayofWeek(), cal.getSundayofWeek(), "desc"));
        sr.setLowestProdFarmer(getProdFarmerbyDate(cal.getMondayofWeek(), cal.getSundayofWeek(), "asc"));
        sr.setHighestYieldFarmer(getYieldFarmerbyDate(cal.getMondayofWeek(), cal.getSundayofWeek(), "desc"));
        sr.setLowestYieldFarmer(getYieldFarmerbyDate(cal.getMondayofWeek(), cal.getSundayofWeek(), "asc"));

        //improvement related
        sr.setRecsSuggested(getTotalRecByStatus(cal.getMondayofWeek(), cal.getSundayofWeek(), "verifying"));
        sr.setRecsImplemented(getTotalRecByStatus(cal.getMondayofWeek(), cal.getSundayofWeek(), "active"));
        sr.setProbsReported(getTotalProbsByStatus(cal.getMondayofWeek(), cal.getSundayofWeek(), "active"));
        sr.setProbsSolved(getTotalProbsByStatus(cal.getMondayofWeek(), cal.getSundayofWeek(), "inactive"));

        return sr;
    }

    public int getTotalRecByStatus(Date startDate, Date endDate, String status) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select count(*) as count from `recommendations-fields` rf where rf.date>= ? and rf.date <=? and status=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            pstmt.setString(3, status);
            ResultSet rs = pstmt.executeQuery();
            int value = 0;
            if (rs.next()) {
                value = rs.getInt("count");
            }
            rs.close();
            pstmt.close();
            conn.close();

            return value;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getTotalProbsByStatus(Date startDate, Date endDate, String status) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select count(*) as count from `problems-fields` pf where pf.date>=? and pf.date <=? and status=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            pstmt.setString(3, status);
            ResultSet rs = pstmt.executeQuery();
            int value = 0;
            if (rs.next()) {
                value = rs.getInt("count");
            }
            rs.close();
            pstmt.close();
            conn.close();

            return value;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Farmer getYieldFarmerbyDate(Date startDate, Date endDate, String status) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select tc/area as yield,farmer from (select f.farmers_name farmer,p.date as thedate,sum(p.tons_cane) as tc, sum(p.area_harvested) as area  from production p join fields f on p.Fields_id=f.id where p.date>= ? and p.date<=?  group by f.Farmers_name )a order by yield " + status + "; ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
//            pstmt.set(3, status);
            ResultSet rs = pstmt.executeQuery();
            Farmer farmer = null;
            if (rs.next()) {
                //remove farmer unknown
                rs.next();
                farmer = new Farmer();
                farmer.settYield(rs.getDouble("yield"));
                farmer.setName(rs.getString("farmer"));
            }
            rs.close();
            pstmt.close();
            conn.close();

            return farmer;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Farmer getProdFarmerbyDate(Date startDate, Date endDate, String status) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select f.farmers_name farmer,p.date as thedate,sum(p.tons_cane) as tc from production p join fields f on p.Fields_id=f.id where p.date>=? and p.date<=? group by f.Farmers_name order by tc " + status + "; ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
//            pstmt.setString(3, status);
            ResultSet rs = pstmt.executeQuery();
            Farmer farmer = null;
            if (rs.next()) {
                //remove farmer unknown
                rs.next();
                farmer = new Farmer();
                farmer.setProduction(rs.getDouble("tc"));
                farmer.setName(rs.getString("farmer"));
            }
            rs.close();
            pstmt.close();
            conn.close();

            return farmer;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public CropNarrative getAssessmentNarrative(int year, Date weekending) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from cropassessment where year=? and district=? AND week_ending=? order by year,district,week_ending;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            pstmt.setString(2, "TARLAC");
            pstmt.setDate(3, weekending);
            ResultSet rs = pstmt.executeQuery();
            CropNarrative cn = null;
            if (rs.next()) {
                cn = new CropNarrative();
                cn.setYear(year);
                cn.setWeekending(weekending);
                cn.setDweather(rs.getString("weather"));
                cn.setDprice(rs.getString("prices_of_sugar"));
                cn.setDmill(rs.getString("mill_operation"));
                cn.setDinput(rs.getString("prices_of_inputs"));
                cn.setDother(rs.getString("others"));
                cn.setDanalysis(rs.getString("overall_analysis"));

            }
            rs.close();
            pstmt.close();
            conn.close();

            return cn;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropAssessment> getCropAssessmentReportForTheWeek(int weekofyear, int year, String tdate) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = null;
            String query = "SELECT * FROM weeklyestimate where id = ? and year = ?  ;";

            String query2 = "select date as week_ending,weekofyear(date), round(sum(area_harvested),2) as area, round(sum(tons_cane),2) as actual, sum(lkg) as lkg \n"
                    + "                    from production\n"
                    + "                    where year = ? and date<= ? and weekofyear(date) = ?\n"
                    + "                    group by weekofyear(date)\n"
                    + "                    order by date;";
            if (year <= 2016) {
                pstmt = conn.prepareStatement(query);
                int id = getWeeklyEstimateID(weekofyear, year);
                pstmt.setInt(1, id);
                pstmt.setInt(2, year);
            } else {
                pstmt = conn.prepareStatement(query2);
                pstmt.setInt(1, year);
                pstmt.setInt(3, weekofyear);
                pstmt.setString(2, tdate);
            }

            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropAssessment> cT = null;
            CropAssessment c;
            if (rs.next()) {
                cT = new ArrayList<>();
                do {
                    c = new CropAssessment();
                    c.setThisTons_Cane(rs.getDouble("actual"));
//                    c.setEstiTons_Cane(rs.getDouble("forecasted"));
                    c.setThisArea(rs.getDouble("area"));
                    c.setWeek_ending(rs.getDate("week_ending"));
                    if (year <= 2016) {

                    } else {
                        c.setThisLKG(rs.getDouble("lkg"));
                    }
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Double getTotalEstimatedTonsCane(int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            int num = getSelectedForecast(year);
            System.out.println(num + "YEARRRRRRRRRRRRRRRRRRRRRRRRR");
            if (num == 0) {
                num++;
                System.out.println(num + " LOL");
            }
            String query = "SELECT sum(forecasted) as 'total' FROM weeklyestimate where year = ? ;";
            String query2 = "SELECT forecasted" + num + " as 'total' FROM cropestimatedistrict where year = ? ;";
            PreparedStatement pstmt = null;
            if (year <= 2016) {
                pstmt = conn.prepareStatement(query);
            } else {
                pstmt = conn.prepareStatement(query2);
            }
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();
            Double tc = null;
            if (rs.next()) {
                tc = rs.getDouble("total");
                System.out.println(tc + "TANGINA NYO");
            } else {
                tc = 0.00;
            }
            rs.close();
            pstmt.close();
            conn.close();

            return tc;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.00;
    }

    public Double getTotalEstimatedArea(int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = null;
            String query = "SELECT sum(area) as 'total' FROM cropestimatedistrict where year = ? ;";
            String query2 = "SELECT area as total FROM sra.cropestimatedistrict where year = ? ;";
            if (year <= 2016) {
                pstmt = conn.prepareStatement(query);
            } else {
                year--;
                pstmt = conn.prepareStatement(query2);
            }

            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();
            Double area = 0.00;
            if (rs.next()) {
                area = rs.getDouble("total");
            } else {
                area = 0.00;
            }
            rs.close();
            pstmt.close();
            conn.close();

            return area;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.00;
    }

    public Double getTotalEstimatedLKG(int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            int num = getSelectedForecastLKG(year);
            System.out.println(num + "YEARRRRRRRRRRRRRRRRRRRRRRRRR");
            if (num == 0) {
                num++;
                System.out.println(num + " LOL");
            }
            String query = "SELECT sum(forecasted_lkg) as 'total' FROM weeklyestimate where year = ? ;";
            String query2 = "SELECT forecasted" + num + "_lkg as 'total' FROM cropestimatedistrict where year = ? ;";
            PreparedStatement pstmt = null;
            if (year <= 2016) {
                pstmt = conn.prepareStatement(query);
            } else {
                pstmt = conn.prepareStatement(query2);
            }
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();
            Double tc = null;
            if (rs.next()) {
                tc = rs.getDouble("total");
                System.out.println(tc + "TANGINA NYO");
            } else {
                tc = 0.00;
            }
            rs.close();
            pstmt.close();
            conn.close();

            return tc;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.00;
    }

    public ArrayList<CropAssessment> getRainFall(int weekofyear, int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "";
            PreparedStatement pstmt = null;
            if (year > 2017) {
                query = "SELECT *  FROM weeklyestimate where year = ? and id between ? and ?";
                pstmt = conn.prepareStatement(query);
                int id = getWeeklyEstimateID(weekofyear, year);
                pstmt.setInt(1, year);
                pstmt.setInt(2, id);
                int consid = id + 3;
                pstmt.setInt(3, consid);
            } else {
                query = "SELECT *, weekofyear(date), sum(amount)as rainfal, date as 'week_ending' FROM rainfall WHERE year(date) = ? AND weekofyear(date) BETWEEN ? AND ?  group by weekofyear(date) ;";
                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, year);
                pstmt.setInt(2, weekofyear);
                pstmt.setInt(3, weekofyear + 3);
            }

            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropAssessment> rainfall = null;
            CropAssessment rain;
            if (rs.next()) {
                rainfall = new ArrayList<>();
                do {
                    rain = new CropAssessment();
                    rain.setRainfall(rs.getDouble("rainfal"));
                    rain.setWeek_ending(rs.getDate("week_ending"));
                    rainfall.add(rain);

                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return rainfall;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropAssessment> getPrevCropAssessmentReportForTheWeek(int weekofyear, int year, String tdate) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = null;
            String query = "SELECT sum(area) as 'previous_area',sum(actual) as 'previous_tc' FROM weeklyestimate where id < ? and year = ? ;";

            String query2 = "select date as week_ending,weekofyear(date), round(sum(area_harvested),2) as previous_area, round(sum(tons_cane),2) as previous_tc, sum(lkg) as previous_lkg \n"
                    + "                    from production\n"
                    + "                    where year = ? and date< ? and yearweek(date)< yearweek(?)\n"
                    + "                    order by date;";
            if (year <= 2016) {
                pstmt = conn.prepareStatement(query);
                int id = getWeeklyEstimateID(weekofyear, year);
                pstmt.setInt(1, id);
                pstmt.setInt(2, year);
            } else {
                pstmt = conn.prepareStatement(query2);
                pstmt.setInt(1, year);
                if (weekofyear == 1) {
                    weekofyear = 52;
                }
                pstmt.setString(3, tdate);
                pstmt.setString(2, tdate);
            }
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropAssessment> cT = null;
            CropAssessment c;
            if (rs.next()) {
                cT = new ArrayList<>();
                do {
                    c = new CropAssessment();
                    c.setPrevTons_Cane(rs.getDouble("previous_tc"));
                    c.setPrevArea(rs.getDouble("previous_area"));
                    if (year <= 2016) {

                    } else {
                        c.setPrevLKG(rs.getDouble("previous_lkg"));
                    }
                    cT.add(c);

                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getWeeklyEstimateID(int weekofyear, int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT * FROM weeklyestimate where weekofyear(week_ending) = ? and year = ?  ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, weekofyear);
            pstmt.setInt(2, year);
            ResultSet rs = pstmt.executeQuery();
            int id = 0;
            CropAssessment c;
            if (rs.next()) {
                do {
                    id = rs.getInt("id");
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return id;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getSelectedForecast(int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT forecast FROM cropestimatedistrict where year = ?  ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();
            int id = 0;
            CropAssessment c;
            if (rs.next()) {
                do {
                    id = rs.getInt("forecast");
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return id;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getSelectedForecastLKG(int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT forecast_lkg FROM cropestimatedistrict where year = ?  ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();
            int id = 0;
            CropAssessment c;
            if (rs.next()) {
                do {
                    id = rs.getInt("forecast_lkg");
                    System.out.println(id + "AYYYYYYYYYYYYYYYYYYYYYYD");
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return id;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CropAssessment> getEstimatedProduction(int year) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "Select * from weeklyestimate where year = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, year);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<CropAssessment> cT = null;
            CropAssessment c;
            if (rs.next()) {
                cT = new ArrayList<>();
                do {
                    c = new CropAssessment();
                    cT.add(c);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return cT;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean printCA(int year, String district, String weekending) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            //load report location
            //   FileInputStream fis = new FileInputStream("C:\\Users\\ndrs\\Documents\\NetBeansProjects\\devappFirst\\src\\java\\reports\\PurchaseOrder.jrxml");
            //    BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);              
            //set parameters
            Map map = new HashMap();
            map.put("year", year);
            map.put("district", district);
            map.put("week_ending", weekending);
            //compile report
            //   JasperReport jasperReport = JasperCompileManager.compileReport(bufferedInputStream);
            String pathing = CropAssessment.class.getClassLoader().toString();
            System.out.println(pathing);
            File file = new File("cropassessmenttest.jrxml");
            String path = file.getAbsolutePath();

            String only_path = path;
            System.out.println(only_path);
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Bryll Joey Delfin\\Documents\\NetBeansProjects\\Reality\\src\\java\\reports\\cropassessmenttest.jrxml");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);

            System.out.println("it printed the file");
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\Bryll Joey Delfin\\Documents\\NetBeansProjects\\Reality\\CropAssess" + district + weekending + ".pdf");

            //view report to UI
            JasperViewer jv = new JasperViewer(jasperPrint, false);
//                jv.viewReport( jasperPrint, false );
            JasperViewer.viewReport(jasperPrint, false);

            return true;

        } catch (JRException ex) {
            Logger.getLogger(CropAssessmentDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

}
