package controller;

import db.CalendarDB;
import db.ProductionDB;
import db.fixedRecDB;
import entity.Calendar;
import entity.FarmRecTable;
import entity.Farmer;
import entity.Recommendation;
import entity.brgySummary;
import java.io.IOException;
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

public class viewFarmerBrgyTable extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
       

        HttpSession session = request.getSession();
    ProductionDB prodb= new ProductionDB();
      String farm= request.getParameter("name");
 CalendarDB caldb= new CalendarDB();
   ArrayList<Calendar> calist= caldb.getCurrentYearDetails();
   int cropyr=calist.get(0).getYear();
   ArrayList<Farmer> fct =null;
   if(cropyr>2016){
      if(caldb.checkifMilling()){
          System.out.println("brgymunitable- its milling");
           fct = prodb.viewCurrFarmerBrgyTable(farm,cropyr);
      }else{
           ArrayList<Integer>histyrs= prodb.getDistinctHistProdYrs(cropyr);
         fct = prodb.viewFarmerBrgyTable(farm,histyrs.get(0));    
      }
      
}else{
         fct = prodb.viewFarmerBrgyTable(farm,cropyr);
   }
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        DecimalFormat df = new DecimalFormat("#.00");
                df.setGroupingUsed(true);
                df.setGroupingSize(3);
        
        if (fct != null) {
            for (int i = 0; i < fct.size(); i++) {
                ArrayList<String> obj = new ArrayList<>();
                obj.add(fct.get(i).getName());
              
                obj.add(Integer.toString(fct.get(i).getTfarms()));
                  obj.add(fct.get(i).getYear());
                obj.add(df.format(fct.get(i).getTotalArea()));
                obj.add(df.format(fct.get(i).getProduction()));
                obj.add(fct.get(i).getName());
                 list.add(obj);
            }
        }
      data.put("data", list);
          response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
