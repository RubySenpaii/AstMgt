//reality
package db;

import entity.Calendar;
import entity.CropValidation;
import entity.Farm;
import entity.Farmer;
import entity.Fertilizer;
import entity.Problems;
import entity.Recommendation;
import entity.SoilAnalysis;
import entity.TagsList;
import entity.Tillers;
import entity.compProblems;
import entity.compRecommendation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FarmsDB {
    
    public boolean createFarm(Farm farm) {
        //DOESNT WORK
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "insert into farms(owner, farm_name, boundary, area) values (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(3, farm.getBoundaries());
            pstmt.setDouble(4, farm.getArea());
            int i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean editFarm(Farm farm) {
        //DOESNT WORK
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "update farms set district = ?, management_type = ? , address= ?, nitrogen= ?, phosphorus= ?, potassium = ?, ph_level= ? "
                    + "where owner = ? and farm_name = ?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, farm.getDistrict());
            pstmt.setString(2, farm.getManagement_type());
            
            int i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return i == 1;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<Farm> getFarmerFieldsTable(String fname, int cropyr) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id,Farmers_name,barangay,municipality,area from fields where farmers_name=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fname);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Farm> farms = null;
            Farm farm;
            
            ProductionDB prodb = new ProductionDB();
            Farmer farmer = prodb.viewFarmerSummarybyYearTest(fname, fname, cropyr);
            
            if (rs.next()) {
                farms = new ArrayList<Farm>();
                do {
                    farm = new Farm();
                    farm.setId(rs.getInt("id"));
                    farm.setFarmer(fname);
                    farm.setProduction(farmer.getProduction());
                    farm.setTotalHa(farmer.getTotalArea());
                    farm.setArea(rs.getDouble("area"));
                    farm.setDifYield((farm.getArea() / farm.getArea()) * 100);
                    farm.setBarangay(rs.getString("barangay"));
                    farm.setMunicipality(rs.getString("municipality"));
                    farms.add(farm);
                    
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return farms;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> getAllFarmersFields(String fname) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id from fields where farmers_name=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fname);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<String> farms = null;
            
            ProductionDB prodb = new ProductionDB();
            if (rs.next()) {
                farms = new ArrayList<String>();
                do {
                    farms.add(rs.getString("id"));
                    
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return farms;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Farm> getCurrFarmerFieldsTable(String fname, int cropyr, Date todayDate) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id,Farmers_name,barangay,municipality,area from fields where farmers_name=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fname);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Farm> farms = null;
            Farm farm;
            
            ProductionDB prodb = new ProductionDB();
            if (rs.next()) {
                farms = new ArrayList<Farm>();
                do {
                    farm = new Farm();
                    farm.setId(rs.getInt("id"));
                    farm.setFarmer(fname);
                    
                    Farmer farmer = new Farmer();
                    farmer = prodb.viewFieldSummarybyYear(farm.getId(), cropyr, todayDate);
                    farm.setProduction(farmer.getProduction());
                    farm.setTotalHa(farmer.getTotalArea());
                    farm.setArea(rs.getDouble("area"));
                    farm.setDifYield((farm.getTotalHa() / farm.getArea()) * 100);
                    farm.setBarangay(rs.getString("barangay"));
                    farm.setMunicipality(rs.getString("municipality"));
                    farms.add(farm);
                    
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return farms;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//crop validation will only show for the current year so that means it will be empty for 2016 and not show the 2015

    public Farm getAllFieldDetails(int fid) {
        CalendarDB caldb = new CalendarDB();
        ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
        int cropyr = calist.get(0).getYear();
        Farm farm = null;
        farm = getBasicFieldDetails(fid);
        farm.setYear(cropyr);
        // farmproductiondetails
        fixedRecDB fRecDB = new fixedRecDB();
        
        ProblemsDB probdb = new ProblemsDB();
        
        ProgramsDB progdb = new ProgramsDB();
        //added date
        farm.setRecommendation(fRecDB.viewFarmRecTablebyFarm(fid));
        //added date
        farm.setProblems(probdb.getFarmProblemDetbyFarm(fid));
//
        farm.setPrograms(progdb.getProgramByFarm(Integer.toString(fid)));
        
        farm.setSoilanalysis(getFieldSoilAnalysis(farm.getId()));
        farm.setFertilizer(getFieldFertilizers(farm.getId(), cropyr));
        farm.setTillist(getFieldTillersList(farm.getId(), cropyr));
        farm.setFertlist(getFieldFertilizersList(farm.getId(), cropyr));
        farm.setTillers(getFieldTillers(farm.getId(), cropyr));
        farm.setCropVal(getFieldCropValidation(farm.getId(), cropyr));
        
        return farm;
    }

    //USES AND GETS ONLY ACTIVE PROBLEMS AND RECOMMENDATIONS
    public Farm getAllFieldDetailsComp(int fid) {
        CalendarDB caldb = new CalendarDB();
        ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
        int cropyr = calist.get(0).getYear();
        Farm farm = null;
        farm = getBasicFieldDetails(fid);
        farm.setYear(cropyr);
        // farmproductiondetails
        fixedRecDB fRecDB = new fixedRecDB();
        
        ProblemsDB probdb = new ProblemsDB();
        //added date
        farm.setRecommendation(fRecDB.viewFarmRecTablebyFarmComparison(fid));
        //added date
        farm.setProblems(probdb.getFarmProblemDetbyFarmComp(fid));
        
        farm.setSoilanalysis(getFieldSoilAnalysis(farm.getId()));
        farm.setFertilizer(getFieldFertilizers(farm.getId(), cropyr));
        farm.setTillist(getFieldTillersList(farm.getId(), cropyr));
        farm.setFertlist(getFieldFertilizersList(farm.getId(), cropyr));
        farm.setTillers(getFieldTillers(farm.getId(), cropyr));
        farm.setCropVal(getFieldCropValidation(farm.getId(), cropyr));
        
        return farm;
    }
    
    public ArrayList<Farm> getAllFieldComp(ArrayList<String> list) {
        ArrayList<Farm> flist = null;
        if (list != null) {
            flist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Farm farm = getAllFieldDetailsComp(Integer.parseInt(list.get(i)));
                flist.add(farm);
            }
            
        }
        
        return flist;
    }
    
    public Farm getBasicFieldDetails(int fid) {
        // **** TODO : GET (INTEGER)YEAR TO GIVE TO FERTILIZER, TILLERS,CROP VALIDATION AND MONTHLY
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select f.id,f.farmers_name,f.barangay,f.municipality,hp.tons_cane,f.area,avg(hp.tons_cane/hp.area) as tavgyield from historicalproduction hp join fields f on hp.farmers_name=f.farmers_name where f.id=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, fid);
            ResultSet rs = pstmt.executeQuery();
            
            Farm farm = null;
            if (rs.next()) {
                
                farm = new Farm();
                farm.setId(fid);
                farm.setFarmer(rs.getString("Farmers_name"));
                farm.setArea(rs.getDouble("area"));
                farm.setBarangay(rs.getString("barangay"));
                farm.setMunicipality(rs.getString("municipality"));
                
                Farm yfarm = getFarmDet(Integer.toString(fid));
                farm.setProduction(yfarm.getProduction());
                farm.setTotalHa(yfarm.getTotalHa());
                farm.setYield(yfarm.getYield());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return farm;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Farm getFieldProductionDet(int fid, int cropyr, Date todayDate) {
        // **** TODO : GET (INTEGER)YEAR TO GIVE TO FERTILIZER, TILLERS,CROP VALIDATION AND MONTHLY
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "SELECT ifnull(sum(p.tons_cane),0) as production, ifnull(sum(p.area_harvested),0) as tarea,ifnull(round(sum(p.tons_cane)/sum(p.area_harvested),2),0) as yield, p.year from production p join fields f on p.fields_id=f.id where f.id=? and year=? and date<=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, fid);
            pstmt.setInt(2, cropyr);
            pstmt.setDate(3, todayDate);
            
            ResultSet rs = pstmt.executeQuery();
            
            Farm farm = null;
            if (rs.next()) {
                
                farm = new Farm();
                farm.setId(fid);
                farm.setProduction(rs.getDouble("production"));
                farm.setTotalHa(rs.getDouble("tarea"));
                farm.setYield(rs.getDouble("yield"));
            }
            rs.close();
            pstmt.close();
            conn.close();
            return farm;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Farm getHistFieldProductionDet(String farmers_name, int cropyr) {
        // **** TODO : GET (INTEGER)YEAR TO GIVE TO FERTILIZER, TILLERS,CROP VALIDATION AND MONTHLY
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select *,round(cane/area,2) as yield\n"
                    + "from (select sum(tons_cane) as cane from historicalproduction where farmers_name=? and year=?)t1 \n"
                    + "join (select sum(area) as area from fields where farmers_name=?)t2;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, farmers_name);
            pstmt.setString(2, farmers_name);
            pstmt.setInt(3, cropyr);
            
            ResultSet rs = pstmt.executeQuery();
            
            Farm farm = null;
            if (rs.next()) {
                
                farm = new Farm();
                farm.setFarmer(farmers_name);
                farm.setProduction(rs.getDouble("cane"));
                farm.setTotalHa(rs.getDouble("area"));
                farm.setYield(rs.getDouble("yield"));
            }
            rs.close();
            pstmt.close();
            conn.close();
            return farm;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public SoilAnalysis getFieldSoilAnalysis(int id) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id,ph_level,organic_matter,phosphorus,potassium from soilanalysis s where id=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            SoilAnalysis sa = null;
            if (rs.next()) {
                sa = new SoilAnalysis();
                sa.setField_id(id);
                sa.setPh_lvl(rs.getDouble("ph_level"));
                sa.setOrganic_matter(rs.getDouble("organic_matter"));
                sa.setPhosphorus(rs.getDouble("phosphorus"));
                sa.setPotassium(rs.getDouble("potassium"));
                
            }
            rs.close();
            pstmt.close();
            conn.close();
            return sa;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Fertilizer getFieldFertilizers(int id, int year) {
        // DB HAS MULTIPLE FOR THE SAME YEAR 
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select year,Fields_id,fertilizer,first_dose,second_dose from fertilizers where  Fields_id=? and year<=? order by year DESC;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, year);
            ResultSet rs = pstmt.executeQuery();
            Fertilizer fz = null;
            if (rs.next()) {
                fz = new Fertilizer();
                fz.setYear(rs.getInt("year"));
                fz.setFields_id(id);
                fz.setFertilizer(rs.getString("fertilizer"));
                fz.setFirst_dose(rs.getDouble("first_dose"));
                fz.setSecond_dose(rs.getDouble("second_dose"));
                
            }
            rs.close();
            pstmt.close();
            conn.close();
            return fz;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Fertilizer> getFieldFertilizersList(int id, int year) {
        // DB HAS MULTIPLE FOR THE SAME YEAR 
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select year,Fields_id,fertilizer,first_dose,second_dose from fertilizers where  Fields_id=? and year=(select max(year) from fertilizers where fields_id=? and year<=?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            pstmt.setInt(3, year);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Fertilizer> flist = null;
            if (rs.next()) {
                flist = new ArrayList<>();
                Fertilizer fz = null;
                do {
                    fz = new Fertilizer();
                    fz.setYear(rs.getInt("year"));
                    fz.setFields_id(id);
                    fz.setFertilizer(rs.getString("fertilizer"));
                    fz.setFirst_dose(rs.getDouble("first_dose"));
                    fz.setSecond_dose(rs.getDouble("second_dose"));
                    flist.add(fz);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return flist;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Tillers getFieldTillers(int id, int year) {
        //TODO YEAR
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select year,Fields_id,rep,count from tillers where Fields_id=? and year<=? order by year DESC;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, year);
            ResultSet rs = pstmt.executeQuery();
            Tillers til = null;
            if (rs.next()) {
                til = new Tillers();
                til.setYear(rs.getInt("year"));
                til.setFields_id(id);
                til.setRep(rs.getInt("rep"));
                til.setCount(rs.getInt("count"));
                
            }
            rs.close();
            pstmt.close();
            conn.close();
            return til;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Tillers> getFieldTillersList(int id, int year) {
        //TODO YEAR
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select year,Fields_id,rep,count from tillers where Fields_id=? and year=(select max(year) from tillers where fields_id=? and year<=?);";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, id);
            pstmt.setInt(3, year);
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<Tillers> tilist = null;
            if (rs.next()) {
                tilist = new ArrayList<>();
                Tillers til = null;
                do {
                    til = new Tillers();
                    til.setYear(rs.getInt("year"));
                    til.setFields_id(id);
                    til.setRep(rs.getInt("rep"));
                    til.setCount(rs.getInt("count"));
                    tilist.add(til);
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return tilist;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
// i need validation to see if the p

    public CropValidation getFieldCropValidation(int id, int year) {
        //TODO YEAR
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
//            String query = "select cs.year,cs.Fields_id,cs.variety,cs.crop_class,cs.texture,cs.farming_system,cs.topography,cs.furrow_distance,cs.planting_density,cs.num_millable,cs.avg_millable_stool,cs.brix,cs.stalk_length,cs.diameter,cs.weight from cropvalidationsurveys cs where Fields_id=? and year=?;";
            String query = "select cs.year,cs.Fields_id,cs.variety,cs.crop_class,cs.texture,cs.farming_system,cs.topography,cs.furrow_distance,cs.planting_density,cs.num_millable,cs.avg_millable_stool,cs.brix,cs.stalk_length,cs.diameter,cs.weight from cropvalidationsurveys cs where Fields_id=? and year<=? order by cs.year DESC;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, year);
            ResultSet rs = pstmt.executeQuery();
            CropValidation cv = null;
            
            if (rs.next()) {
                cv = new CropValidation();
                cv.setYear(rs.getInt("year"));
                cv.setField_id(rs.getInt("Fields_id"));
                cv.setVariety(rs.getString("variety"));
                System.out.println("crop val not null" + cv.getVariety());
                cv.setCrop_class(rs.getString("crop_class"));
                cv.setTexture(rs.getString("texture"));
                cv.setFarming_system(rs.getString("farming_system"));
                cv.setTopography(rs.getString("topography"));
                cv.setFurrow_distance(rs.getDouble("furrow_distance"));
                cv.setPlanting_density(rs.getDouble("planting_density"));
//                 cv.setHarvest_date(rs.getDate("harvest_date"));
//                 cv.setDate_millable(rs.getDate("date_millable"));
                cv.setNum_millable(rs.getInt("num_millable"));
                cv.setAvg_millable_stool(rs.getDouble("avg_millable_stool"));
                cv.setBrix(rs.getDouble("brix"));
                cv.setStalk_length(rs.getDouble("stalk_length"));
                cv.setDiameter(rs.getDouble("diameter"));
                cv.setWeight(rs.getDouble("weight"));
            }
            rs.close();
            pstmt.close();
            conn.close();
            return cv;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Farm> getSearchTableResult(ArrayList<String> ids, int fid) {
        ArrayList<Farm> list = null;
        
        if (ids != null) {
            list = new ArrayList<>();
            for (int i = 0; i < ids.size(); i++) {
                
                Farm farm = getFarmDet(ids.get(i));
                Farm sFarmr = getFarmDet(Integer.toString(fid));
                farm = getYieldDif(farm, sFarmr);
                System.out.println(farm.getId());
                list.add(farm);
            }
            
        }
        return list;
    }
    
    public Farm getYieldDif(Farm farm, Farm sfarm) {
        // CHECK IF FORMULA IS RIGHT !~~~ ITS STILL QUESTIONABLE
        double f = farm.getYield();
        double sf = sfarm.getYield();

        // * 100.0 / 100.0
        //  double roundOff = Math.round((((sf-f)/sf))*100) ;
        //double roundOff= Math.round(((((sf-f)/sf))*100)*100.0)/100.0;
        double roundOff = Math.round(((((f - sf) / sf)) * 100) * 100.0) / 100.0;
        // double roundOff = Math.round(((((sf-f)/sf))*100)*100)/100 ;
        // double roundOff =(double) Math.round(((((sf-f)/sf))*100) * 100) / 100);
        farm.setDifYield(roundOff);
        //      Math.round(f,2);
        //  System.out.println();

        return farm;
        
    }
    
    public Farm getFarmDet(String id) {
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select f.id,f.Farmers_name,f.barangay,f.municipality,avg(hp.tons_cane/hp.area) as avgyield from historicalproduction hp join fields f on hp.farmers_name=f.farmers_name where f.id=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, Integer.parseInt(id));
            
            ResultSet rs = pstmt.executeQuery();
            Farm f = null;
            if (rs.next()) {
                f = new Farm();
                f.setId(Integer.parseInt(id));
                f.setFarmer(rs.getString("Farmers_name"));
                f.setBarangay(rs.getString("barangay"));
                f.setMunicipality(rs.getString("municipality"));
                f.setYield(rs.getDouble("avgyield"));
                CalendarDB caldb = new CalendarDB();
                ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
                int cropyr = calist.get(0).getYear();
                Date todayDate = calist.get(0).getTodayDate();
                Farm yfarm = null;
                if (cropyr > 2016) {
                    if (caldb.checkifMilling()) {
                        yfarm = getFieldProductionDet(Integer.parseInt(id), cropyr, todayDate);
                    } else {
                        ProductionDB prodb = new ProductionDB();
                        ArrayList<Integer> histyrs = prodb.getDistinctHistProdYrs(cropyr);
                        yfarm = getHistFieldProductionDet(f.getFarmer(), histyrs.get(0));
                    }
                } else {
                    yfarm = getHistFieldProductionDet(f.getFarmer(), cropyr);
                }
                f.setProduction(yfarm.getProduction());
                f.setTotalHa(yfarm.getTotalHa());
                f.setYield(yfarm.getYield());
                
            }
            rs.close();
            pstmt.close();
            conn.close();
            return f;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> getTags(Farm f) {
        ArrayList<String> taglist = new ArrayList<>();
        if (f.getArea() != 0) {
            taglist.add("area");
        }
        if (f.getBarangay() != null) {
            taglist.add("barangay");
        }
        if (f.getMunicipality() != null) {
            taglist.add("municipality");
        }
        if (f.getFarmer() != null) {
            taglist.add("farmer");
        }
        if (f.getYield() != 0) {
            taglist.add("yield");
        }
        if (f.getTotalHa() != 0) {
            taglist.add("totalHa");
        }
        if (f.getProduction() != 0) {
            taglist.add("production");
        }
        //soil analysis
        SoilAnalysis sa = f.getSoilanalysis();
        if (sa.getPh_lvl() != 0) {
            taglist.add("ph lvl");
        }
        if (sa.getOrganic_matter() != 0) {
            taglist.add("OM");
        }
        if (sa.getPhosphorus() != 0) {
            taglist.add("Phosphorus");
        }
        if (sa.getPotassium() != 0) {
            taglist.add("Potassium");
        }
        //TILLER      
        Tillers till = f.getTillers();
        if (till != null) {
            if (till.getRep() != 0) {
                taglist.add("Rep");
            }
            if (till.getCount() != 0) {
                taglist.add("Count");
            }
        }
        //FERTILIZER
        Fertilizer fz = f.getFertilizer();
        if (fz != null) {
            if (fz.getFertilizer() != null) {
                taglist.add("Fertilizer");
            }
            if (fz.getFirst_dose() != null) {
                taglist.add("first dose");
            }
            if (fz.getSecond_dose() != null) {
                taglist.add("second dose");
            }
        }
        //CROP VAL
        CropValidation cv = f.getCropVal();
        if (cv != null) {
            if (cv.getVariety() != null) {
                taglist.add("variety");
            }
            if (cv.getCrop_class() != null) {
                taglist.add("crop class");
            }
            if (cv.getTexture() != null) {
                taglist.add("texture");
            }
            if (cv.getFarming_system() != null) {
                taglist.add("farming system");
            }
            if (cv.getTopography() != null) {
                taglist.add("topography");
            }
            if (cv.getFurrow_distance() != null) {
                taglist.add("furrow dist");
            }
            if (cv.getPlanting_density() != null) {
                taglist.add("plant density");
            }
            if (cv.getPlanting_date() != null) {
                taglist.add("plant date");
            }
            if (cv.getHarvest_date() != null) {
                taglist.add("harvest date");
            }
            if (cv.getDate_millable() != null) {
                taglist.add("date mill");
            }
            if (cv.getNum_millable() != 0) {
                taglist.add("num mill");
            }
            if (cv.getAvg_millable_stool() != null) {
                taglist.add("avgMilStool");
            }
            if (cv.getBrix() != null) {
                taglist.add("Brix");
            }
            if (cv.getStalk_length() != null) {
                taglist.add("Stalk Len");
            }
            if (cv.getDiameter() != null) {
                taglist.add("diameter");
            }
            if (cv.getWeight() != null) {
                taglist.add("weight");
            }
        }
        return taglist;
    }
//added date

    public ArrayList<String> searchFarmsbyTags(String[] list, int id) {
        ArrayList<String> bfarm, sa, till, fz, cv, prod;
        
        bfarm = checkFieldBasicDetails(list);
        prod = checkFieldProdDetails(list);
        sa = checkFieldSoilAnalysis(list);
        till = checkFieldTill(list);
        fz = checkFieldFzer(list);
        cv = checkFieldCropVal(list);
        CalendarDB caldb = new CalendarDB();
        ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
        int cropyr = calist.get(0).getYear();
//check each for crop year 2017 and above
        //check if area is necessary for prod field det
        if (!bfarm.isEmpty()) {
            bfarm = (getAllBasicDetbyTags(bfarm, id));
        }
        if (!prod.isEmpty()) {
            prod = (getAllProdFieldDetbyTags(prod, id));
        }
        if (!sa.isEmpty()) {
            sa = (getAllSAbyTags(sa, id));
        }
        if (!till.isEmpty()) {
            till = (getAllTillbyTags(till, id, cropyr));
        }
        if (!fz.isEmpty()) {
            fz = (getAllFzbyTags(fz, id, cropyr));
        }
        if (!cv.isEmpty()) {
            cv = (getAllCVbyTags(cv, id, cropyr));
        }
        
        ArrayList<ArrayList<String>> idlists = new ArrayList<>();
        if (!bfarm.isEmpty()) {
            idlists.add(bfarm);
            for (int i = 0; i < bfarm.size(); i++) {
                System.out.println(bfarm.get(i) + "bfarm post check");
            }
        }
        if (!prod.isEmpty()) {
            idlists.add(prod);
            for (int i = 0; i < prod.size(); i++) {
                System.out.println(prod.get(i) + "prod post check");
            }
        }
        if (!sa.isEmpty()) {
            idlists.add(sa);
            for (int i = 0; i < sa.size(); i++) {
                System.out.println(sa.get(i) + "SA post check");
            }
        }
        if (!till.isEmpty()) {
            idlists.add(till);
            for (int i = 0; i < till.size(); i++) {
                System.out.println(till.get(i) + "TILL post check");
            }
        }
        if (!fz.isEmpty()) {
            idlists.add(fz);
            for (int i = 0; i < fz.size(); i++) {
                System.out.println(fz.get(i) + "FERTZ post check");
            }
        }
        if (!cv.isEmpty()) {
            idlists.add(cv);
            for (int i = 0; i < cv.size(); i++) {
                System.out.println(cv.get(i) + "CV post check");
            }
        }
        
        System.out.println(idlists.size() + " TOTAL TABLES(Lists) USED");
        
        if (!idlists.isEmpty() && idlists.size() > 1) {
            System.out.println("Entered filtering loop");
            for (int i = 1; i < idlists.size(); i++) {
                idlists.get(0).retainAll(idlists.get(i));
                
            }
            
        }
        
        return idlists.get(0);
    }
    
    public ArrayList<String> getAllCVbyTags(ArrayList<String> list, int id, int year) {
        // I GROUPED THE QUERY BY FIELD ID ***TODO CHANGE IT// add year 
        try {
            String values = "";
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("variety")) {
                    values += "variety=?";
                } else if (list.get(i).equalsIgnoreCase("crop class")) {
                    values += "crop_class=?";
                } else if (list.get(i).equalsIgnoreCase("texture")) {
                    values += "texture=?";
                } else if (list.get(i).equalsIgnoreCase("farming system")) {
                    values += "farming_system=?";
                } else if (list.get(i).equalsIgnoreCase("topography")) {
                    values += "topography=?";
                } else if (list.get(i).equalsIgnoreCase("furrow dist")) {
                    values += "furrow_distance=?";
                } else if (list.get(i).equalsIgnoreCase("plant density")) {
                    values += "planting_density=?";
                } else if (list.get(i).equalsIgnoreCase("plant date")) {
                    values += "planting_date=?";
                } else if (list.get(i).equalsIgnoreCase("harvest date")) {
                    values += "harvest_date=?";
                } else if (list.get(i).equalsIgnoreCase("date mill")) {
                    values += "date_millable=?";
                } else if (list.get(i).equalsIgnoreCase("num mill")) {
                    values += "num_millable=?";
                } else if (list.get(i).equalsIgnoreCase("avgMilStool")) {
                    values += "avg_millable_stool=?";
                } else if (list.get(i).equalsIgnoreCase("Brix")) {
                    values += "brix=?";
                } else if (list.get(i).equalsIgnoreCase("Stalk Len")) {
                    values += "stalk_length=?";
                } else if (list.get(i).equalsIgnoreCase("diameter")) {
                    values += "diameter=?";
                } else if (list.get(i).equalsIgnoreCase("weight")) {
                    values += "weight=?";
                }
                if (list.size() > 0 && i != list.size() - 1) {
                    values += " and ";
                }
            }
            System.out.println("where query for cropval det::" + values);
            
            CropValidation cv = getFieldCropValidation(id, year);
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select Fields_id from cropvalidationsurveys where " + values + "and year<=? group by Fields_id order by year DESC;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            System.out.println(query);
            int c = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("variety")) {
                    pstmt.setString(c = c + 1, cv.getVariety());
                } else if (list.get(i).equalsIgnoreCase("crop class")) {
                    pstmt.setString(c = c + 1, cv.getCrop_class());
                } else if (list.get(i).equalsIgnoreCase("texture")) {
                    pstmt.setString(c = c + 1, cv.getTexture());
                } else if (list.get(i).equalsIgnoreCase("farming system")) {
                    pstmt.setString(c = c + 1, cv.getFarming_system());
                } else if (list.get(i).equalsIgnoreCase("topography")) {
                    pstmt.setString(c = c + 1, cv.getTopography());
                } else if (list.get(i).equalsIgnoreCase("furrow dist")) {
                    pstmt.setDouble(c = c + 1, cv.getFurrow_distance());
                } else if (list.get(i).equalsIgnoreCase("plant density")) {
                    pstmt.setDouble(c = c + 1, cv.getPlanting_density());
                } else if (list.get(i).equalsIgnoreCase("plant date")) {
                    pstmt.setDate(c = c + 1, cv.getPlanting_date());
                } else if (list.get(i).equalsIgnoreCase("harvest date")) {
                    pstmt.setDate(c = c + 1, cv.getHarvest_date());
                } else if (list.get(i).equalsIgnoreCase("date mill")) {
                    pstmt.setDate(c = c + 1, cv.getDate_millable());
                } else if (list.get(i).equalsIgnoreCase("num mill")) {
                    pstmt.setInt(c = c + 1, cv.getNum_millable());
                } else if (list.get(i).equalsIgnoreCase("avgMilStool")) {
                    pstmt.setDouble(c = c + 1, cv.getAvg_millable_stool());
                } else if (list.get(i).equalsIgnoreCase("Brix")) {
                    pstmt.setDouble(c = c + 1, cv.getBrix());
                } else if (list.get(i).equalsIgnoreCase("Stalk Len")) {
                    pstmt.setDouble(c = c + 1, cv.getStalk_length());
                } else if (list.get(i).equalsIgnoreCase("diameter")) {
                    pstmt.setDouble(c = c + 1, cv.getDiameter());
                } else if (list.get(i).equalsIgnoreCase("weight")) {
                    pstmt.setDouble(c = c + 1, cv.getWeight());
                }
            }
            pstmt.setInt(c = c + 1, year);
            
            ResultSet rs = pstmt.executeQuery();
            ArrayList<String> fids = new ArrayList<>();
            if (rs.next()) {
                fids = new ArrayList<>();
                do {
                    fids.add(rs.getString("Fields_id"));
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return fids;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> getAllFzbyTags(ArrayList<String> list, int id, int year) {
        // I GROUPED THE QUERY BY FIELD ID ***TODO CHANGE IT
        try {
            String values = "";
            for (int i = 0; i < list.size(); i++) {
                
                if (list.get(i).equalsIgnoreCase("Fertilizer")) {
                    values += "fertilizer=?";
                } else if (list.get(i).equalsIgnoreCase("first dose")) {
                    values += "first_dose=?";
                } else if (list.get(i).equalsIgnoreCase("second dose")) {
                    values += "second_dose=?";
                }
                if (list.size() > 0 && i != list.size() - 1) {
                    values += " and ";
                }
            }
            
            System.out.println("where query for Fertz det::" + values);
            
            Fertilizer fz = getFieldFertilizers(id, year);
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select Fields_id from fertilizers where " + values + "and year=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            int c = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("Fertilizer")) {
                    pstmt.setString(c = c + 1, fz.getFertilizer());
                } else if (list.get(i).equalsIgnoreCase("first dose")) {
                    pstmt.setDouble(c = c + 1, fz.getFirst_dose());
                } else if (list.get(i).equalsIgnoreCase("second dose")) {
                    pstmt.setDouble(c = c + 1, fz.getSecond_dose());
                }
            }
            pstmt.setInt(c = c + 1, year);
            
            ResultSet rs = pstmt.executeQuery();
            ArrayList<String> fids = new ArrayList<>();
            if (rs.next()) {
                fids = new ArrayList<>();
                do {
                    fids.add(rs.getString("Fields_id"));
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return fids;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> getAllTillbyTags(ArrayList<String> list, int id, int year) {
        // I GROUPED THE QUERY BY FIELD ID ***TODO CHANGE IT
        try {
            String values = "";
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("rep")) {
                    values += "rep=?";
                } else if (list.get(i).equalsIgnoreCase("Count")) {
                    values += "count=?";
                }
                
                if (list.size() > 0 && i != list.size() - 1) {
                    values += " and ";
                }
            }
            
            System.out.println("where query for Till det::" + values);
            
            Tillers till = getFieldTillers(id, year);
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select Fields_id from tillers where " + values + "and year=? group by Fields_id";
            PreparedStatement pstmt = conn.prepareStatement(query);
            int c = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("rep")) {
                    pstmt.setDouble(c = c + 1, till.getRep());
                } else if (list.get(i).equalsIgnoreCase("count")) {
                    pstmt.setDouble(c = c + 1, till.getCount());
                }
            }
            pstmt.setInt(c = c + 1, year);
            
            ResultSet rs = pstmt.executeQuery();
            ArrayList<String> fids = new ArrayList<>();;
            if (rs.next()) {
                fids = new ArrayList<>();
                do {
                    fids.add(rs.getString("Fields_id"));
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return fids;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> getAllSAbyTags(ArrayList<String> list, int id) {
        try {
            String values = "";
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("ph lvl")) {
                    values += "ph_level=?";
                } else if (list.get(i).equalsIgnoreCase("OM")) {
                    values += "organic_matter=?";
                } else if (list.get(i).equalsIgnoreCase("Phosphorus")) {
                    values += "phosphorus=?";
                } else if (list.get(i).equalsIgnoreCase("Potassium")) {
                    values += "potassium=?";
                }
                if (list.size() > 0 && i != list.size() - 1) {
                    values += " and ";
                }
            }
            System.out.println("where query for SoAna det::" + values);
            
            SoilAnalysis sa = getFieldSoilAnalysis(id);
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id from soilanalysis s where " + values;
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            int c = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("ph lvl")) {
                    pstmt.setDouble(c = c + 1, sa.getPh_lvl());
                } else if (list.get(i).equalsIgnoreCase("OM")) {
                    pstmt.setDouble(c = c + 1, sa.getOrganic_matter());
                } else if (list.get(i).equalsIgnoreCase("Phosphorus")) {
                    pstmt.setDouble(c = c + 1, sa.getPhosphorus());
                } else if (list.get(i).equalsIgnoreCase("Potassium")) {
                    pstmt.setDouble(c = c + 1, sa.getPotassium());
                }
            }
            
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<String> fids = new ArrayList<>();
            if (rs.next()) {
                fids = new ArrayList<>();
                do {
                    fids.add(rs.getString("id"));
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return fids;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public ArrayList<String> getAllProdFieldDetbyTags(ArrayList<String> list, int id) {
        CalendarDB caldb = new CalendarDB();
        ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
        int cropyr = calist.get(0).getYear();
        Date todayDate = calist.get(0).getTodayDate();
        
        ArrayList<String> fids = new ArrayList<>();
        if (cropyr > 2016) {
            if (caldb.checkifMilling()) {
                //cur details for HA,PROD,YIELD,TAREA 
                fids = getAllCurProdDetbyTags(list, id, cropyr, todayDate);
            } else {
                ProductionDB prodb = new ProductionDB();
                ArrayList<Integer> histyrs = prodb.getDistinctHistProdYrs(cropyr);
                fids = getAllProdDetbyTags(list, id, histyrs.get(0));
                
            }
        } else {
            fids = getAllProdDetbyTags(list, id, cropyr);
        }
        return fids;
    }
    
    public ArrayList<String> getAllProdDetbyTags(ArrayList<String> list, int id, int cropyr) {
        
        try {
            
            Farm getfarm = getFarmDet(Integer.toString(id));
            Farm farm = getHistFieldProductionDet(getfarm.getFarmer(), cropyr);
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select cane,area,cane/area as yield,t1.farmers_name\n"
                    + "from (select sum(tons_cane) as cane,farmers_name  from historicalproduction where year=? group by farmers_name)t1 \n"
                    + "join (select sum(area) as area, farmers_name from fields group by farmers_name)t2 on t1.farmers_name = t2.farmers_name\n"
                    + "having round(yield,2)=?;";
            PreparedStatement pstmt = conn.prepareStatement(query);
            int c = 0;
            pstmt.setInt(c = c + 1, cropyr);
            pstmt.setDouble(c = c + 1, farm.getYield());
            
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<String> fids = new ArrayList<>();
            if (rs.next()) {
                fids = new ArrayList<>();
                do {
                    fids.addAll(getAllFarmersFields(rs.getString("farmers_name")));
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return fids;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> getAllCurProdDetbyTags(ArrayList<String> list, int id, int cropyr, Date todayDate) {
        
        try {
            String values = "";
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("totalHa")) {
                    values += "sum(area_harvested)=?";
                } else if (list.get(i).equalsIgnoreCase("production")) {
                    values += "sum(tons_cane)=?";
                } else if (list.get(i).equalsIgnoreCase("yield")) {
                    values += "round(sum(tons_cane)/sum(area_harvested),2)=?";
                }
                //else if(list.get(i).equalsIgnoreCase("yield")) values+="yield=?" ;
                // else if(list.get(i).equalsIgnoreCase("totalHa")) values+="totalHa=?" ;
                //   else if(list.get(i).equalsIgnoreCase("production")) values+="production=?" ;

                if (list.size() > 0 && i != list.size() - 1) {
                    values += " and ";
                }
            }
            System.out.println("where query for prod det::" + values);
            
            Farm farm = getFieldProductionDet(id, cropyr, todayDate);
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select fields_id from production where year=? and date<=? group by fields_id having " + values;
            System.out.println(query + ": the query");
            PreparedStatement pstmt = conn.prepareStatement(query);
            int c = 0;
            pstmt.setInt(c = c + 1, cropyr);
            pstmt.setDate(c = c + 1, todayDate);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("totalHa")) {
                    pstmt.setDouble(c = c + 1, farm.getTotalHa());
                } else if (list.get(i).equalsIgnoreCase("production")) {
                    pstmt.setDouble(c = c + 1, farm.getProduction());
                } else if (list.get(i).equalsIgnoreCase("yield")) {
                    pstmt.setDouble(c = c + 1, farm.getYield());
                }
            }
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<String> fids = new ArrayList<>();
            if (rs.next()) {
                fids = new ArrayList<>();
                do {
                    fids.add(rs.getString("fields_id"));
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return fids;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> getAllBasicDetbyTags(ArrayList<String> list, int id) {
        
        try {
            String values = "";
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("area")) {
                    values += "area=?";
                } else if (list.get(i).equalsIgnoreCase("barangay")) {
                    values += "barangay=?";
                } else if (list.get(i).equalsIgnoreCase("municipality")) {
                    values += "municipality=?";
                } else if (list.get(i).equalsIgnoreCase("farmer")) {
                    values += "Farmers_name=?";
                }
                //else if(list.get(i).equalsIgnoreCase("yield")) values+="yield=?" ;
                // else if(list.get(i).equalsIgnoreCase("totalHa")) values+="totalHa=?" ;
                //   else if(list.get(i).equalsIgnoreCase("production")) values+="production=?" ;

                if (list.size() > 0 && i != list.size() - 1) {
                    values += " and ";
                }
            }
            System.out.println("where query for basic det::" + values);
            
            Farm farm = getBasicFieldDetails(id);
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String query = "select id from fields where " + values;
            System.out.println(query + ": the query");
            PreparedStatement pstmt = conn.prepareStatement(query);
            int c = 0;
            System.out.println(farm.getFarmer() + "dis farmer iz not empty");
            System.out.println(farm.getArea() + "dis area iz not empty");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase("area")) {
                    System.out.println(c + "area");
                    pstmt.setDouble(c = c + 1, farm.getArea());
                    System.out.println(c + "Earea");
                } else if (list.get(i).equalsIgnoreCase("barangay")) {
                    pstmt.setString(c = c + 1, farm.getBarangay());
                } else if (list.get(i).equalsIgnoreCase("municipality")) {
                    pstmt.setString(c = c + 1, farm.getMunicipality());
                } else if (list.get(i).equalsIgnoreCase("farmer")) {
                    System.out.println(c + "b4");
                    pstmt.setString(c = c + 1, farm.getFarmer());
                    System.out.println(c + "aftr");
                }
                //else if(list.get(i).equalsIgnoreCase("yield")) values+="yield=?" ;
                // else if(list.get(i).equalsIgnoreCase("totalHa")) values+="totalHa=?" ;
                //   else if(list.get(i).equalsIgnoreCase("production")) values+="production=?" ;
            }
            ResultSet rs = pstmt.executeQuery();
            
            ArrayList<String> fids = new ArrayList<>();
            if (rs.next()) {
                fids = new ArrayList<>();
                do {
                    fids.add(rs.getString("id"));
                } while (rs.next());
            }
            rs.close();
            pstmt.close();
            conn.close();
            return fids;
        } catch (SQLException ex) {
            Logger.getLogger(FarmsDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<String> checkFieldCropVal(String[] list) {
        ArrayList<String> taglist = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i].equalsIgnoreCase("variety")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("crop class")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("texture")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("farming system")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("topography")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("furrow dist")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("plant density")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("plant date")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("harvest date")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("date mill")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("num mill")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("avgMilStool")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("Brix")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("Stalk Len")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("diameter")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("weight")) {
                taglist.add(list[i]);
            }
        }
        return taglist;
    }
    
    public ArrayList<String> checkFieldFzer(String[] list) {
        ArrayList<String> taglist = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i].equalsIgnoreCase("Fertilizer")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("first dose")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("second dose")) {
                taglist.add(list[i]);
            }
        }
        return taglist;
    }
    
    public ArrayList<String> checkFieldTill(String[] list) {
        ArrayList<String> taglist = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i].equalsIgnoreCase("rep")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("Count")) {
                taglist.add(list[i]);
            }
        }
        return taglist;
    }
    
    public ArrayList<String> checkFieldSoilAnalysis(String[] list) {
        
        ArrayList<String> taglist = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i].equalsIgnoreCase("ph lvl")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("OM")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("Phosphorus")) {
                taglist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("Potassium")) {
                taglist.add(list[i]);
            }
        }
        return taglist;
    }
    
    public ArrayList<String> checkFieldBasicDetails(String[] list) {
        ArrayList<String> chcklist = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i].equalsIgnoreCase("area")) {
                chcklist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("barangay")) {
                chcklist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("municipality")) {
                chcklist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("farmer")) {
                chcklist.add(list[i]);
            }
        }
        return chcklist;
    }
    
    public ArrayList<String> checkFieldProdDetails(String[] list) {
        ArrayList<String> chcklist = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            if (list[i].equalsIgnoreCase("yield")) {
                chcklist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("totalHa")) {
                chcklist.add(list[i]);
            } else if (list[i].equalsIgnoreCase("production")) {
                chcklist.add(list[i]);
            }
        }
        return chcklist;
    }
    
    public ArrayList<compRecommendation> getSimilarRecommendations(Farm farm, ArrayList<Farm> dalist) {
        ArrayList<Farm> list = new ArrayList<>();
        list = dalist;
        ArrayList<Recommendation> farmRec = new ArrayList<Recommendation>();
        if (farm.getRecommendation() != null) {
            if (!farm.getRecommendation().isEmpty()) {
                farmRec.addAll(farm.getRecommendation());
            }
        }
        ArrayList<compRecommendation> reclist = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Recommendation> rec = new ArrayList<Recommendation>();
            rec = list.get(i).getRecommendation();
            
            if (rec != null) {
//          System.out.println(rec.size());
                if (!rec.isEmpty()) {
                    farmRec.addAll(list.get(i).getRecommendation());
                }
            }
        }
        
        if (!farmRec.isEmpty()) {
            for (int i = 0; i < farmRec.size(); i++) {
                for (int j = i + 1; j < farmRec.size(); j++) {
                    if (farmRec.get(i).getId().equals(farmRec.get(j).getId())) {
                        farmRec.remove(j);
                        j--;
                    }
                }
            }
        }
        if (!farmRec.isEmpty()) {
            for (int i = 0; i < farmRec.size(); i++) {
                compRecommendation cr = new compRecommendation();
                cr.setId(farmRec.get(i).getId());
                cr.setRecommendation_name(farmRec.get(i).getRecommendation_name());
                cr.setType(farmRec.get(i).getType());
                cr.setPhase(farmRec.get(i).getPhase());
                cr.setDescription(farmRec.get(i).getDescription());
                reclist.add(cr);
            }
        }
        
        if (!reclist.isEmpty()) {
            list.add(0, farm);
            for (int i = 0; i < reclist.size(); i++) {
                ArrayList<Integer> fieldz = new ArrayList<>();
                boolean chrk = false;
                for (int c = 0; c < list.size(); c++) {
                    
                    ArrayList<Recommendation> rec = new ArrayList<Recommendation>();
                    rec = list.get(c).getRecommendation();
                    if (rec != null) {
                        if (!rec.isEmpty()) {
                            for (int b = 0; b < rec.size(); b++) {
                                if (rec.get(b).getId().equals(reclist.get(i).getId())) {
                                    System.out.println(list.get(c).getId());
                                    fieldz.add(list.get(c).getId());
                                    chrk = true;
                                    
                                }
                            }
                        }
                    }
                    if (chrk == false) {
                        fieldz.add(null);
                    }
                    chrk = false;
                }
                reclist.get(i).setFarms(fieldz);
            }
        }
        if (!reclist.isEmpty()) {
            System.out.println("***loop starts here***");
            for (int i = 0; i < reclist.size(); i++) {
                System.out.println(reclist.get(i).getRecommendation_name() + ":post rec name");
                fixedRecDB recdb = new fixedRecDB();
                // SETS THE LIST OF PROBLEMS THAT THE RECOMMENDATION IS LINKED TO(IF THERE IS)
                reclist.get(i).setProblist(recdb.viewProbRecTable(reclist.get(i).getId()));

//                for (int b = 0; b < reclist.get(i).getFarms().size(); b++) {
//                    System.out.println(reclist.get(i).getFarms().get(b) + "farm");
//
//                }
            }
        }
        
        return reclist;
        
    }
    
    public ArrayList<compRecommendation> getPreSelectedRecommendations(Farm farm, ArrayList<Farm> dalist, ArrayList<compProblems> problist) {
        ArrayList<Farm> list = new ArrayList<>();
        list = dalist;
        ArrayList<Recommendation> farmRec = new ArrayList<Recommendation>();
//adds recommendation of FARM 1 if there is
        if (farm.getRecommendation() != null) {
            if (!farm.getRecommendation().isEmpty()) {
                farmRec.addAll(farm.getRecommendation());
            }
        }

//adds all recommendations of the farms to the list 'farmRec'
        ArrayList<compRecommendation> reclist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Recommendation> rec = new ArrayList<Recommendation>();
            rec = list.get(i).getRecommendation();
            
            if (rec != null) {
                if (!rec.isEmpty()) {
                    farmRec.addAll(list.get(i).getRecommendation());
                }
            }
        }

        //gives solution of the problems list to the farm rec
        for (int i = 0; i < problist.size(); i++) {
            ArrayList<Recommendation> rec = new ArrayList<Recommendation>();
            rec = problist.get(i).getReclist();
            if (rec != null) {
                if (!rec.isEmpty()) {
                    farmRec.addAll(rec);
                }
            }
        }

//this method removes duplicate values from farmRec arrayList
        if (!farmRec.isEmpty()) {
            for (int i = 0; i < farmRec.size(); i++) {
                for (int j = i + 1; j < farmRec.size(); j++) {
                    if (farmRec.get(i).getId().equals(farmRec.get(j).getId())) {
                        farmRec.remove(j);
                        j--;
                    }
                }
            }
        }
        //this method shortens the list by PHASE 
        if (!farmRec.isEmpty()) {
            for (int i = 0; i < farmRec.size(); i++) {
                if (currPhaseChecker(farmRec.get(i)) == false) {
                    farmRec.remove(i);
                    i--;
                }
                
            }
        }

        //this method converts farmRec to compRec
        if (!farmRec.isEmpty()) {
            for (int i = 0; i < farmRec.size(); i++) {
                compRecommendation cr = new compRecommendation();
                cr.setId(farmRec.get(i).getId());
                cr.setRecommendation_name(farmRec.get(i).getRecommendation_name());
                cr.setType(farmRec.get(i).getType());
                cr.setPhase(farmRec.get(i).getPhase());
                cr.setDescription(farmRec.get(i).getDescription());
                reclist.add(cr);
            }
        }
//this method makes the fields format null,null,787,null
        if (!reclist.isEmpty()) {
            list.add(0, farm);
            for (int i = 0; i < reclist.size(); i++) {
                ArrayList<Integer> fieldz = new ArrayList<>();
                boolean chrk = false;
                for (int c = 0; c < list.size(); c++) {
                    
                    ArrayList<Recommendation> rec = new ArrayList<Recommendation>();
                    rec = list.get(c).getRecommendation();
                    if (rec != null) {
                        if (!rec.isEmpty()) {
                            for (int b = 0; b < rec.size(); b++) {
                                if (rec.get(b).getId().equals(reclist.get(i).getId())) {
                                    System.out.println(list.get(c).getId());
                                    fieldz.add(list.get(c).getId());
                                    chrk = true;
                                    
                                }
                            }
                        }
                    }
                    if (chrk == false) {
                        fieldz.add(null);
                    }
                    chrk = false;
                }
                reclist.get(i).setFarms(fieldz);
            }
        }
        if (!reclist.isEmpty()) {
            System.out.println("***loop starts here***");
            for (int i = 0; i < reclist.size(); i++) {
                System.out.println(reclist.get(i).getRecommendation_name() + ":post rec name");
                fixedRecDB recdb = new fixedRecDB();
                // SETS THE LIST OF PROBLEMS THAT THE RECOMMENDATION IS LINKED TO(IF THERE IS)
                reclist.get(i).setProblist(recdb.viewProbRecTable(reclist.get(i).getId()));

//                for (int b = 0; b < reclist.get(i).getFarms().size(); b++) {
//                    System.out.println(reclist.get(i).getFarms().get(b) + "farm");
//
//                }
            }
        }
        
        return reclist;
        
    }

    //Method to cehck acceptable phases (used to shortlist the preselection of recommendations)
    public boolean currPhaseChecker(Recommendation r) {
        CalendarDB caldb = new CalendarDB();
        ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
        
        boolean checker = false;
        //checks if the recommendation is part of the current phase/s

        for (int i = 0; i < calist.size(); i++) {
            if (calist.get(i).getPhase().equalsIgnoreCase(r.getPhase())) {
                checker = true;
            }
        }
        //checks if the recommendation has its recommendation as year
        if (r.getPhase().equalsIgnoreCase("year")) {
            checker = true;
        }
        return checker;
        
    }
    
    public ArrayList<compProblems> getSimilarProblems(Farm farm, ArrayList<Farm> dalist) {
        ArrayList<Farm> list = new ArrayList<>();
        list = dalist;
        ArrayList<Problems> farmRec = new ArrayList<Problems>();
        if (farm.getProblems() != null) {
            if (!farm.getProblems().isEmpty()) {
                farmRec.addAll(farm.getProblems());
            }
        }
        ArrayList<compProblems> reclist = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Problems> rec = new ArrayList<Problems>();
            rec = list.get(i).getProblems();
            
            if (rec != null) {
//          System.out.println(rec.size());
                if (!rec.isEmpty()) {
                    farmRec.addAll(list.get(i).getProblems());
                }
            }
        }
        
        if (!farmRec.isEmpty()) {
            for (int i = 0; i < farmRec.size(); i++) {
                for (int j = i + 1; j < farmRec.size(); j++) {
                    if (farmRec.get(i).getProb_id().equals(farmRec.get(j).getProb_id())) {
                        farmRec.remove(j);
                        j--;
                    }
                }
            }
        }
        if (!farmRec.isEmpty()) {
            for (int i = 0; i < farmRec.size(); i++) {
                compProblems cr = new compProblems();
                cr.setProb_id(farmRec.get(i).getProb_id());
                cr.setProb_name(farmRec.get(i).getProb_name());
                cr.setType(farmRec.get(i).getType());
                cr.setPhase(farmRec.get(i).getPhase());
                cr.setProb_details(farmRec.get(i).getProb_details());
                reclist.add(cr);
            }
        }
        
        if (!reclist.isEmpty()) {
            list.add(0, farm);
            for (int i = 0; i < reclist.size(); i++) {
                ArrayList<Integer> fieldz = new ArrayList<>();
                boolean chrk = false;
                for (int c = 0; c < list.size(); c++) {
                    
                    ArrayList<Problems> rec = new ArrayList<Problems>();
                    rec = list.get(c).getProblems();
                    if (rec != null) {
                        if (!rec.isEmpty()) {
                            for (int b = 0; b < rec.size(); b++) {
                                if (rec.get(b).getProb_id().equals(reclist.get(i).getProb_id())) {
                                    System.out.println(list.get(c).getId());
                                    fieldz.add(list.get(c).getId());
                                    chrk = true;
                                    
                                }
                            }
                        }
                    }
                    if (chrk == false) {
                        fieldz.add(null);
                    }
                    chrk = false;
                }
                reclist.get(i).setFarms(fieldz);
            }
        }
        if (!reclist.isEmpty()) {
            System.out.println("***loop starts here***");
            for (int i = 0; i < reclist.size(); i++) {
                System.out.println(reclist.get(i).getProb_name() + ":post prob name");
                ProblemsDB probdb = new ProblemsDB();
                // CODE FOR ADDING SOLUTIONS FOR THE PROBLEM
                reclist.get(i).setReclist(probdb.getAllSolutions(reclist.get(i).getProb_id()));
                for (int b = 0; b < reclist.get(i).getFarms().size(); b++) {
                    System.out.println(reclist.get(i).getFarms().get(b) + "farm");
                    
                }
            }
        }
        
        return reclist;
        
    }
    
    public ArrayList<compRecommendation> getUnSimilarRecommendations(Farm farm, ArrayList<Farm> list) {
        ArrayList<Recommendation> farmRec = farm.getRecommendation();
        ArrayList<compRecommendation> reclist = new ArrayList<>();
        
        if (farm.getRecommendation() != null) {
            for (int i = 0; i < farm.getRecommendation().size(); i++) {
                compRecommendation rec = new compRecommendation();
                rec.setId(farm.getRecommendation().get(i).getId());
                rec.setRecommendation_name(farm.getRecommendation().get(i).getRecommendation_name());
                rec.setDescription(farm.getRecommendation().get(i).getDescription());
                System.out.println(reclist.get(i).getRecommendation_name() + "rec name" + farm.getId());
            }
        }
        
        for (int b = 0; b < list.size(); b++) {
            if (list.get(b).getRecommendation() != null && !list.get(b).getRecommendation().isEmpty()) {
                
                for (int i = 0; i < list.get(i).getRecommendation().size(); i++) {
                    if (!reclist.isEmpty()) {
                        for (int d = 0; d < reclist.size(); d++) {
                            
                            if (reclist.get(d).getId() != list.get(b).getRecommendation().get(i).getId()) {
                                compRecommendation rec = new compRecommendation();
                                rec.setId(list.get(b).getRecommendation().get(i).getId());
                                rec.setRecommendation_name(list.get(b).getRecommendation().get(i).getRecommendation_name());
                                rec.setDescription(list.get(b).getRecommendation().get(i).getDescription());
                                reclist.add(rec);
                            }
                        }
                    } else {
                        compRecommendation rec = new compRecommendation();
                        rec.setId(list.get(b).getRecommendation().get(i).getId());
                        rec.setRecommendation_name(list.get(b).getRecommendation().get(i).getRecommendation_name());
                        rec.setDescription(list.get(b).getRecommendation().get(i).getDescription());
                        reclist.add(rec);
                    }
                }
            }
        }
        for (int i = 0; i < reclist.size(); i++) {
            System.out.println(reclist.get(i).getRecommendation_name() + "rec name");
        }

//    for(int i=0;i<reclist.size(); i++){
//        compRecommendation rec=new compRecommendation();
//        rec.setRecommendation_name(rec.getRecommendation_name());
//        
//        rec.getDescription();
//    }
        return reclist;
    }
    
}
