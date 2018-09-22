/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Problems;
import entity.Programs;
import entity.cropEstimate;
import entity.programsKPI;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ndrs
 */
public class ProgramsDB {

    public Date StringtoSQLDate(String sdate) {
        try {
            java.util.Date endcheck = new SimpleDateFormat("MM/dd/yyyy").parse(sdate);
            Date modifiedendDate = new java.sql.Date(endcheck.getTime());
            return modifiedendDate;
        } catch (ParseException ex) {
            Logger.getLogger(ProgramsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public java.util.Date SQLDatetoUtil(String sdate) {
        try {
            java.util.Date endcheck = new SimpleDateFormat("yyyy-MM/dd").parse(sdate);
            java.util.Date modifiedendDate = new java.util.Date(endcheck.getTime());
            return modifiedendDate;
        } catch (ParseException ex) {
            Logger.getLogger(ProgramsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Programs> getallPrograms(int probid) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select  programs_name  \n"
                    + "from `programs-problems`\n"
                    + "where problems_id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, probid);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Programs> list = null;
            Programs p = null;

            while (rs.next()) {
                list = new ArrayList<Programs>();
                
                do {
                    p = new Programs();
                    p.setProg_name(rs.getString("programs_name"));
                    list.add(p);
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
    public Programs viewProgDetailForProblem(String id) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT * FROM sra.programs where name = ? ;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            Programs r = new Programs();
            if (rs.next()) {
            r.setProg_name(rs.getString("name"));
            r.setDescription(rs.getString("description"));
            }
            rs.close();
            pstmt.close();
            conn.close();

            return r;
        } catch (SQLException ex) {
            Logger.getLogger(ProgramsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean createNewProgram(Programs prog, ArrayList<programsKPI> kpis) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into programs values (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, prog.getProg_name());
            pstmt.setDate(2, (Date) prog.getDate_created());
            pstmt.setDate(3, (Date) prog.getDate_initial());
            pstmt.setDate(4, (Date) prog.getDate_end());
            pstmt.setString(5, prog.getDescription());
            pstmt.setString(6, "active");
            pstmt.setString(7, "Tarlac");
            pstmt.setString(8, prog.getType());
            int isSuccess = pstmt.executeUpdate();

            kpis.get(0).setProgram_name(prog.getProg_name());

            pstmt.close();

            boolean kpisSuccess = addKPIs(kpis);

            conn.close();

            return isSuccess == 1 && kpisSuccess;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public ArrayList<ArrayList<cropEstimate>> getProductionChartbyProgram(String progname) {

        Programs prog = viewProgDetails(progname);
        // todaydate
        Calendar cal = Calendar.getInstance();
        cal.setTime(prog.getDate_initial());
        int year = cal.get(Calendar.YEAR);
        // get production method per year (2 instances) 1 pre and 1 post
        ArrayList<cropEstimate> prce = getProgramPreProduction(year, progname);
        ArrayList<cropEstimate> poce = getProgramPostProduction(year, progname);
        ArrayList<ArrayList<cropEstimate>> list = new ArrayList();
        list.add(prce);
        list.add(poce);
        return list;
    }

    public ArrayList<cropEstimate> getProgramPreProduction(int year, String progname) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select sum(hp.tons_cane)as production, hp.year\n"
                    + "from historicalproduction hp\n"
                    + "where hp.Farmers_name in (\n"
                    + "						select Farmers_name\n"
                    + "                        from fields\n"
                    + "                        where id in \n"
                    + "								(\n"
                    + "                                select Fields_id\n"
                    + "                                from `Problems-Fields`\n"
                    + "                                where Problems_id in (\n"
                    + "													select Problems_id\n"
                    + "                                                    from `Programs-Problems`\n"
                    + "                                                    where Programs_name = ? \n"
                    + "														)\n"
                    + "                                )\n"
                    + "						) and year <? \n"
                    + "group by year;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, progname);
            pstmt.setInt(2, year);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<cropEstimate> list = null;

            if (rs.next()) {
                list = new ArrayList<>();

                do {
                    cropEstimate ce = new cropEstimate();
                    ce.setYear(rs.getInt("year"));
                    ce.setActual(rs.getDouble("production"));
                    list.add(ce);
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

    public ArrayList<cropEstimate> getProgramPostProduction(int year, String progname) {
        try {
            
            CalendarDB caldb=new CalendarDB();
            ArrayList<entity.Calendar> cal=null;
            cal=caldb.getCurrentYearDetails();
          Integer todayyear=  cal.get(0).getYear();
            
            
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "(select sum(hp.tons_cane)as production, hp.year\n"
                    + "from historicalproduction hp\n"
                    + "where hp.Farmers_name in (\n"
                    + "						select Farmers_name\n"
                    + "                        from fields\n"
                    + "                        where id in \n"
                    + "								(\n"
                    + "                                select Fields_id\n"
                    + "                                from `Problems-Fields`\n"
                    + "                                where Problems_id in (\n"
                    + "													select Problems_id\n"
                    + "                                                    from `Programs-Problems`\n"
                    + "                                                    where Programs_name = ? \n"
                    + "														)\n"
                    + "                                )\n"
                    + "						) and year >=? and year<=? \n"
                    + "group by year)\n" +
"union all\n" +
"(select sum(p.tons_cane) as production, p.year from production p  where p.Fields_id in\n" +
" (select Fields_id from `Problems-fields` where Problems_id in (select Problems_id from `programs-problems` where programs_name=?) and year>=? and year<=?)\n" +
"group by year);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, progname);
            pstmt.setInt(2, year);
            pstmt.setInt(3, todayyear);
            pstmt.setString(4, progname);
            pstmt.setInt(5, year);
            pstmt.setInt(6, todayyear);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<cropEstimate> list = null;

            if (rs.next()) {
                list = new ArrayList<>();

                do {
                    cropEstimate ce = new cropEstimate();
                    ce.setYear(rs.getInt("year"));
                    ce.setActual(rs.getDouble("production"));
                    list.add(ce);
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

    public ArrayList<Integer> getAllDistinctYrsHistProd() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select Distinct(year) from historicalproduction order by year;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Integer> list = null;

            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    list.add(rs.getInt("year"));
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

    public ArrayList<Programs> getProgramByFarm(String farm) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select p.name,p.status,p.type,p.description,pf.Fields_id from programs p join `programs-problems` pp on p.name=pp.Programs_name join `problems-fields` pf on pp.Problems_id=pf.Problems_id where pf.Fields_id=? and pf.status='active';";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, farm);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Programs> list = null;
            Programs p;
            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    p = new Programs();
                    p.setProg_name(rs.getString("name"));
                    p.setStatus(rs.getString("status"));
                    p.setType(rs.getString("type"));
                    p.setDescription(rs.getString("description"));
                    list.add(p);
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

    public ArrayList<Programs> getProgramByFarmer(String farmer) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select p.name,p.description,pf.Fields_id from programs p join `programs-problems` pp on p.name=pp.Programs_name join `problems-fields` pf on pp.Problems_id=pf.Problems_id join fields f on pf.Fields_id=f.id where f.Farmers_name=? and pf.status='active' group by p.name;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, farmer);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Programs> list = null;
            Programs p;
            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    p = new Programs();
                    p.setProg_name(rs.getString("name"));
                    p.setDescription(rs.getString("description"));
                    list.add(p);
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

    public ArrayList<Programs> getOngoingProjects() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from programs where status = 'Active';";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Programs> list = null;
            Programs p;
            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    p = new Programs();
                    p.setProg_name(rs.getString("name"));
                    p.setDate_initial(rs.getDate("date_initial"));
                    p.setDate_created(rs.getDate("date_created"));
                    p.setDate_end(rs.getDate("date_end"));
                    p.setDescription(rs.getString("description"));
                    p.setDistrict(rs.getString("district"));
                    list.add(p);
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

    public ArrayList<Programs> getOngoingProjectsBoard(Date currDate) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select * from programs where status = 'Active' and date_end > ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, currDate);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Programs> list = null;
            Programs p;
            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    p = new Programs();
                    p.setProg_name(rs.getString("name"));
                    p.setDate_initial(rs.getDate("date_initial"));
                    p.setDate_created(rs.getDate("date_created"));
                    p.setDate_end(rs.getDate("date_end"));
                    p.setDescription(rs.getString("description"));
                    p.setDistrict(rs.getString("district"));
                    list.add(p);
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

    public boolean addKPIs(ArrayList<programsKPI> kpis) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into kpis (year,Programs_name,name,value,actual) values (?,?,?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < kpis.size(); i++) {

                int sYear = kpis.get(i).getKpi_year();
                for (int a = 0; a < kpis.get(i).getValues().size(); a++) {
                    pstmt.setInt(1, sYear + a);
                    pstmt.setString(2, kpis.get(0).getProgram_name());
                    pstmt.setString(3, kpis.get(i).getKpi());
                    pstmt.setDouble(4, kpis.get(i).getValues().get(a));

                    pstmt.addBatch();
                }

            }
            pstmt.executeBatch();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateKPIs(ArrayList<programsKPI> kpis) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE kpis SET actual=? WHERE year=? and Programs_name=? and name=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < kpis.size(); i++) {

                int sYear = kpis.get(i).getKpi_year();
                for (int a = 0; a < kpis.get(i).getaValues().size(); a++) {
                    pstmt.setDouble(1, kpis.get(i).getaValues().get(a));
                    pstmt.setInt(2, sYear + a);
                    pstmt.setString(3, kpis.get(0).getProgram_name());
                    pstmt.setString(4, kpis.get(i).getKpi());
                    pstmt.addBatch();
                }

            }
            pstmt.executeBatch();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean addMoreKPIs(ArrayList<programsKPI> kpis) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "UPDATE kpis SET actual=actual+? WHERE year=? and Programs_name=? and name=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < kpis.size(); i++) {
                int sYear = kpis.get(i).getKpi_year();
                for (int a = 0; a < kpis.get(i).getaValues().size(); a++) {
                    pstmt.setDouble(1, kpis.get(i).getaValues().get(a));
                    pstmt.setInt(2, sYear + a);
                    pstmt.setString(3, kpis.get(0).getProgram_name());
                    pstmt.setString(4, kpis.get(i).getKpi());
                    pstmt.addBatch();
                }

            }
            pstmt.executeBatch();
            pstmt.close();
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean addProgProb(ArrayList<String> probids, String prog_name) {
        try {

            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into `Programs-Problems` (Programs_name,Problems_id,status) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            for (int i = 0; i < probids.size(); i++) {

                pstmt.setString(1, prog_name);
                pstmt.setInt(2, Integer.parseInt(probids.get(i)));
                pstmt.setString(3, "Validated");
                pstmt.addBatch();
            }
            System.out.println("ADDED PROBLEMS TO PROGRAMS");
            pstmt.executeBatch();
            pstmt.close();
            conn.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UsersDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public ArrayList<Programs> viewProgramsTable() {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select pg.name,pg.date_created,pg.status,pg.type,pg.date_end, count(pf.fields_id) as count from programs pg join `programs-problems` pp on pg.name=pp.programs_name join `problems-fields` pf on pp.problems_id=pf.problems_id group by pg.name;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Programs> list = null;
            Programs p;
            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    p = new Programs();
                    p.setProg_name(rs.getString("name"));
                    p.setStatus(rs.getString("status"));
                    p.setType(rs.getString("type"));
                    if (rs.getDate("date_created") == null) {
                        p.setDate_created(StringtoSQLDate("23/06/2015"));
                    } else {
                        p.setDate_created(rs.getDate("date_created"));
                    }
                    if (rs.getDate("date_end") == null) {
                        p.setDate_end(StringtoSQLDate("23/06/2018"));
                    } else {
                        p.setDate_end(rs.getDate("date_end"));
                    }
                    ArrayList<programsKPI> kpis = viewProg1Targets(p.getProg_name());
                    if (kpis != null) {
                        p.setProgress(viewProgramProgress(kpis));
                    } else {
                        p.setProgress(0);
                    }

                    p.settFarms(rs.getInt("count"));
                    list.add(p);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProblemsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Programs viewProgDetails(String prog_name) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select pg.name,pg.date_created,pg.date_initial,pg.date_end,pg.description,pg.status,pg.type,count(pf.fields_id) as count from programs pg join `programs-problems` pp on pg.name=pp.programs_name join `problems-fields` pf on pp.problems_id=pf.problems_id where pg.name=? group by pg.name;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, prog_name);
            ResultSet rs = pstmt.executeQuery();

            Programs p = null;
            if (rs.next()) {

                p = new Programs();
                p.setProg_name(rs.getString("name"));
                p.setDate_created(rs.getDate("date_created"));
                p.setDate_initial(rs.getDate("date_initial"));
                p.setDate_end(rs.getDate("date_end"));
                p.setDescription(rs.getString("description"));
                p.setStatus(rs.getString("status"));
                p.setType(rs.getString("type"));
                //p.setProgress(rs.getInt("progress"));
                p.settFarms(rs.getInt("count"));

            }
            rs.close();
            pstmt.close();
            conn.close();

            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProblemsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Problems> viewProgProb(String prog_name) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select p.id,p.name,p.description,count(pf.Fields_id) as count from problems p join `programs-problems` pp on p.id=pp.Problems_id join `problems-fields` pf on pf.Problems_id=p.id where pp.Programs_name=? group by p.id;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, prog_name);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Problems> list = null;
            Problems p = null;
            if (rs.next()) {
                list = new ArrayList<>();
                do {
                    p = new Problems();
                    p.setProb_id(rs.getInt("id"));
                    p.setProb_name(rs.getString("name"));
                    p.setProb_details(rs.getString("description"));
                    p.setCount(rs.getInt("count"));
                    list.add(p);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            conn.close();

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProblemsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//works only for 1 row
//    public ArrayList<programsKPI> viewProgTargets(String prog_name) {
//        try {
//            // put functions here : previous week production, this week production
//            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
//            Connection conn = myFactory.getConnection();
//            String query = "select year,Programs_name,name,value,actual,(select count(DISTINCT year) from kpis where Programs_name=?) as count from kpis where programs_name=? order by name;";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, prog_name);
//            pstmt.setString(2, prog_name);
//            ResultSet rs = pstmt.executeQuery();
//            ArrayList<programsKPI> list = null;
//            programsKPI p = null;
//            if (rs.next()) {
//                list = new ArrayList<>();
//
//                do {
//                    p = new programsKPI();
//                    p.setKpi(rs.getString("name"));
//                    p.setKpi_year(rs.getInt("year"));
//                    p.settYears(rs.getInt("count"));
//
//                    ArrayList<Double> values = new ArrayList();
//                    ArrayList<Double> avalues = new ArrayList();
//                    int counter = p.gettYears();
//                    do {
//                        String namechkr = rs.getString("name");
//                        System.out.println(rs.getRow() + "row no");
//                        System.out.println(counter);
//                        System.out.println(rs.getDouble("value") + "value");
//                        System.out.println(rs.getDouble("actual") + "actual");
//                        values.add(rs.getDouble("value"));
//                        avalues.add(rs.getDouble("actual"));
//                        if (counter > 0) {
//                            rs.next();
//                            System.out.println(rs.getRow() + "row no");
//                        }
//
//                        
//                        counter--;
//
//                    } while (counter > 0);
//
//                    p.setValues(values);
//                    p.setaValues(avalues);
//                    list.add(p);
//                } while (rs.next());
//            }
//
//            rs.close();
//            pstmt.close();
//            conn.close();
//
//            return list;
//        } catch (SQLException ex) {
//            Logger.getLogger(ProblemsDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

    public Double viewProgramProgress(ArrayList<programsKPI> kpi) {
        if (kpi != null) {

            ArrayList<Double> perc = new ArrayList();
            for (int i = 0; i < kpi.size(); i++) {
                for (int b = 0; b < kpi.get(i).getValues().size(); b++) {
                    double suma = 0, sum = 0, chkr = 0;
                    sum = kpi.get(i).getValues().get(b);
                    suma = kpi.get(i).getaValues().get(b);
                    chkr = (suma / sum) * 100.0;
                    if (chkr > 100.0) {
                        chkr = 100.0;
                    }
                    perc.add(chkr);
                }
            }

            double fin = 0, tavg = 0;
            for (int i = 0; i < perc.size(); i++) {
                fin += perc.get(i);
            }
            tavg = fin / perc.size();
            DecimalFormat df = new DecimalFormat(".##");
            double fnal = Double.parseDouble(df.format(tavg));
            if (fnal > 100.0) {
                fnal = 100;
            }

            return fnal;
        } else {
            return null;
        }
    }

    public ArrayList<programsKPI> viewProg1Targets(String prog_name) {
        try {
            // put functions here : previous week production, this week production
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select year,Programs_name,name,value,actual,(select count(DISTINCT year) from kpis where Programs_name=?) as count from kpis where programs_name=? order by name;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, prog_name);
            pstmt.setString(2, prog_name);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<programsKPI> list = null;
            programsKPI p = null;
            if (rs.next()) {
                list = new ArrayList<>();

                do {
                    p = new programsKPI();
                    p.setKpi(rs.getString("name"));
                    p.setKpi_year(rs.getInt("year"));
                    p.settYears(rs.getInt("count"));

                    ArrayList<Double> values = new ArrayList();
                    ArrayList<Double> avalues = new ArrayList();
                    int counter = p.gettYears();

                    for (int c = p.gettYears(); c > 0; c--) {
                        String namechkr = rs.getString("name");
                        System.out.println(rs.getRow() + "row no");
                        System.out.println(counter);
                        System.out.println(rs.getDouble("value") + "value");
                        System.out.println(rs.getDouble("actual") + "actual");
                        values.add(rs.getDouble("value"));
                        avalues.add(rs.getDouble("actual"));
                        if (counter > 0) {
                            rs.next();
                            System.out.println(rs.getRow() + "row no");
                        }

                        if (c == 1) {
                            rs.previous();
                        }

                    }

                    p.setValues(values);
                    p.setaValues(avalues);
                    list.add(p);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            conn.close();

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProblemsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
