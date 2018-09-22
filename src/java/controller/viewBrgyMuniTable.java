package controller;

import db.CalendarDB;
import db.ProductionDB;
import db.fixedRecDB;
import entity.Calendar;
import entity.FarmRecTable;
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

public class viewBrgyMuniTable extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
       

        HttpSession session = request.getSession();
    ProductionDB prodb= new ProductionDB();
      String muni= request.getParameter("name");
      ArrayList<brgySummary> fct=null;
      
        CalendarDB caldb= new CalendarDB();
   ArrayList<Calendar> calist= caldb.getCurrentYearDetails();
   int cropyr=calist.get(0).getYear();
   
if(cropyr>2016){
      if(caldb.checkifMilling()){
          System.out.println("brgymunitable- its milling");
           fct = prodb.viewCurrBrgyMuniTable(muni,cropyr);
      }else{
           ArrayList<Integer>histyrs= prodb.getDistinctHistProdYrs(cropyr);
         fct = prodb.viewBrgyMuniTable(muni,histyrs.get(0));    
      }
      
}else{
   fct = prodb.viewBrgyMuniTable(muni,cropyr);
}
        
        DecimalFormat df = new DecimalFormat("#.00");
                df.setGroupingUsed(true);
                df.setGroupingSize(3);
        
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        if (fct != null) {
            for (int i = 0; i < fct.size(); i++) {
                ArrayList<String> obj = new ArrayList<>();
                obj.add(fct.get(i).getBarangay());
                obj.add(Integer.toString(fct.get(i).getTfarmers()));
                obj.add(Integer.toString(fct.get(i).getYear()));
                obj.add(df.format(fct.get(i).getArea()));
                obj.add(df.format(fct.get(i).getActual()));
//                obj.add(Double.toString(fct.get(i).getYield()));
                obj.add(fct.get(i).getBarangay());
                 list.add(obj);
            }
        }
      data.put("data", list);
          response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
