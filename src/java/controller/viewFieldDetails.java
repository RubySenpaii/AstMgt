package controller;

import db.CalendarDB;
import db.FarmsDB;
import db.ProductionDB;
import db.ProgramsDB;
import db.fixedRecDB;
import entity.Calendar;
import entity.Farm;
import entity.FarmRecTable;
import entity.Farmer;
import entity.Problems;
import entity.Programs;
import entity.Recommendation;
import entity.programsKPI;
import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class viewFieldDetails extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();

        ProgramsDB progdb = new ProgramsDB();
        HttpSession session = request.getSession();
        FarmsDB farmsdb=new FarmsDB();
        FarmsDB farmdb = new FarmsDB();
        ProductionDB prodb=new ProductionDB();
     
        
         System.err.println("TODAYS user " + session.getAttribute("user"));
     
     
        int id = Integer.parseInt(request.getParameter("id"));
//            CalendarDB caldb= new CalendarDB();
//   ArrayList<Calendar> calist= caldb.getCurrentYearDetails();
//   int cropyr=calist.get(0).getYear();
        
        //added date
        
            Farm farm;
          
             CalendarDB caldb= new CalendarDB();
            ArrayList<Calendar> calist= caldb.getCurrentYearDetails();
            int cropyr=calist.get(0).getYear();
            Date todayDate=calist.get(0).getTodayDate();
            
            farm=farmsdb.getAllFieldDetails(id);
              Farmer farmer;
       if(cropyr>2016){
            if(caldb.checkifMilling()){
                  
                 farmer  = prodb.viewFieldSummarybyYear(id,cropyr,todayDate);
                   
            }else{
                  
                   ArrayList<Integer>histyrs= prodb.getDistinctHistProdYrs(cropyr);
                    farmer  = prodb.viewFarmerSummarybyYearTest(null,farm.getFarmer(),histyrs.get(0));
                  }
        }else{
         farm=farmsdb.getAllFieldDetails(id);
          farmer  = prodb.viewFarmerSummarybyYearTest(null,farm.getFarmer(),cropyr);
        }
       DecimalFormat df = new DecimalFormat("#.##");
                   farm.setProduction(Double.parseDouble(df.format(farmer.getProduction())));
                   farm.setTotalHa(farmer.getTotalArea());
                   if(farm.getProduction()!=0 && farm.getTotalHa()!=0){
                      farm.setYield(Double.parseDouble(df.format(farmer.getProduction()/farmer.getTotalArea()))); 
                   }else{
                       farm.setYield(0); 
                   }
                   
          session.setAttribute("farm", farm);
          session.setAttribute("id", id);

        RequestDispatcher rd = context.getRequestDispatcher("/farmerComparison.jsp");

        rd.forward(request, response);

        response.setCharacterEncoding("utf-8");

    }

}
