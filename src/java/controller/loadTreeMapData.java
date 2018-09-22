package controller;

import db.CalendarDB;
import db.ProductionDB;
import db.fixedRecDB;
import entity.Calendar;
import entity.FarmRecTable;
import entity.Recommendation;
import entity.prodMunicipality;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class loadTreeMapData extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductionDB proddb = new ProductionDB();
           int tag = Integer.parseInt(request.getParameter("tag"));
           String type = request.getParameter("type");
           CalendarDB caldb= new CalendarDB();
    ArrayList<prodMunicipality> list=null;
           if(tag>2016){
            if(caldb.checkifMilling()){
                System.out.println("entered 2017 milling check");
                list= proddb.getCurrProdMunicipalforYear(tag);
            }else{
                 ArrayList<Integer>histyrs= proddb.getDistinctHistProdYrs(tag);
                list= proddb.getProdMunicipalforYear(histyrs.get(0));
            }   
           }else{
          list= proddb.getProdMunicipalforYear(tag);
           }
       
        JSONObject data = new JSONObject();

        
        for (prodMunicipality list1 : list) {
            JSONObject trial = new JSONObject();
            for (int i = 0; i < list1.getBrgy().size(); i++) {
                JSONObject farm = new JSONObject();
                for (int y = 0; y < list1.getBrgy().get(i).getFarmer().size(); y++) {
                 Double prod=list1.getBrgy().get(i).getFarmer().get(y).getProduction();
                   Double area= list1.getBrgy().get(i).getFarmer().get(y).getTotalArea();
//                   Double yield=prod/area;
                   if(type.equalsIgnoreCase("area")){
                      
                         farm.put(list1.getBrgy().get(i).getFarmer().get(y).getName(), area);
                   }else if(type.equalsIgnoreCase("yield")){
                       farm.put(list1.getBrgy().get(i).getFarmer().get(y).getName(), prod/area);
                   }else{
                        farm.put(list1.getBrgy().get(i).getFarmer().get(y).getName(), prod);
                   }
                   
                    
//                    farm.put(list1.getBrgy().get(i).getFarmer().get(y).getName(), area);
//                    farm.put(list1.getBrgy().get(i).getFarmer().get(y).getName(), yield);
                    
                }
                trial.put(list1.getBrgy().get(i).getBarangay(), farm);
            }
            data.put(list1.getMunicipal(), trial);
        }

  
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
