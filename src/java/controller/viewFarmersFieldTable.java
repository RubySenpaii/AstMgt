package controller;

import db.CalendarDB;
import db.FarmerDB;
import db.FarmsDB;
import db.ProductionDB;
import db.fixedRecDB;
import entity.Calendar;
import entity.Farm;
import entity.FarmRecTable;
import entity.Farmer;
import entity.Recommendation;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class viewFarmersFieldTable extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        FarmsDB farmdb = new FarmsDB();
        ProductionDB prodb=new ProductionDB();

        HttpSession session = request.getSession();
        // Recommendation rec = new Recommendation();
        String name = request.getParameter("name");
            
            CalendarDB caldb= new CalendarDB();
            ArrayList<Calendar> calist= caldb.getCurrentYearDetails();
            int cropyr=calist.get(0).getYear();
            Date todayDate=calist.get(0).getTodayDate();
        
          ArrayList<Farm> farms =null;
        if(cropyr>2016){
            if(caldb.checkifMilling()){
                 farms  = farmdb.getCurrFarmerFieldsTable(name,cropyr,todayDate);
            }else{
                   ArrayList<Integer>histyrs= prodb.getDistinctHistProdYrs(cropyr);
                    farms  = farmdb.getFarmerFieldsTable(name,histyrs.get(0));
            }
        }else{
        farms  = farmdb.getFarmerFieldsTable(name,cropyr);
        }
        
       

        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        if (farms != null) {
            for (int i = 0; i < farms.size(); i++) {
                ArrayList<String> obj = new ArrayList<>();
                obj.add(Integer.toString(farms.get(i).getId()));
                obj.add(farms.get(i).getBarangay());
                obj.add(farms.get(i).getMunicipality());
//                 obj.add("2015-11-12");
                DecimalFormat df = new DecimalFormat("#.##");
                obj.add(Double.toString(farms.get(i).getArea()));
                obj.add(df.format(farms.get(i).getProduction()));
                obj.add(Double.toString(farms.get(i).getTotalHa()));
                   Double yield;
                if(farms.get(i).getProduction()!=0 && farms.get(i).getTotalHa()!=0){
                    yield= farms.get(i).getProduction()/farms.get(i).getTotalHa();
                   }else{
                       yield=0.0;
                   }
             
                 if(farms.get(i).getDifYield()==0){
                     farms.get(i).setDifYield(0);
                 }
               
                System.out.println(yield+"da yield");
                obj.add(df.format(yield));
               
                obj.add(df.format(farms.get(i).getDifYield()));
//                obj.add("TPEH43");
                obj.add(Integer.toString(farms.get(i).getId()));
                list.add(obj);
            }
        }
        data.put("data", list);
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
