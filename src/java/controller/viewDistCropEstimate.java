/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropAssessmentDB;
import db.CropBoardDB;
import db.CropEstimateDB;
import db.fixedRecDB;
import entity.Calendar;
import entity.CropBoard;
import entity.FarmRecTable;
import entity.Recommendation;
import entity.cropEstimate;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ndrs
 */
public class viewDistCropEstimate extends BaseServlet {
  @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        CropEstimateDB estdb = new CropEstimateDB();

        HttpSession session = request.getSession();
        cropEstimate ce = new cropEstimate();
        cropEstimate cen = new cropEstimate();
       ArrayList<cropEstimate> fct = new ArrayList<>();
       ArrayList<CropBoard> production = new ArrayList<>();
       ArrayList<cropEstimate> ces = new ArrayList<>();
       CalendarDB caldb = new CalendarDB();
       CropBoardDB cdb = new CropBoardDB();
       ArrayList<Calendar> calist = caldb.getCurrentYearDetails();//gets the phases/today/crop yr
       Calendar cal = caldb.getCalendarTypes(calist.get(0).getTodayDate());//weekofyear//month//day
         int year = Integer.parseInt(request.getParameter("year"));
         DecimalFormat df = new DecimalFormat("#.00");
                df.setGroupingUsed(true);
                df.setGroupingSize(3);
         if(year <=2016){
        fct = estdb.viewDistrictEstimates(year);
         }
         else{
        production =cdb.getCurrentWeeklyProducedReport("TC", year, calist.get(0).getTodayDate().toString());
        ce = estdb.getEstimatePreviousYear(year);
        ces = estdb.getWeeklyEstimatePreviousYears(year);
         }
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        if (fct != null) {
            if(year <=2016){
            for (int i = 0; i < fct.size(); i++) {
                ArrayList<String> obj = new ArrayList<>();
                obj.add(Integer.toString(fct.get(i).getYear()));
                obj.add(fct.get(i).getWeek_ending().toString());
                obj.add(df.format(fct.get(i).getArea()));
                obj.add(Double.toString(fct.get(i).getRainfall()));
                obj.add(df.format(fct.get(i).getActual()));
                obj.add(df.format(fct.get(i).getForecasted()));
                 obj.add(Double.toString(fct.get(i).getDifference())+"%");
                 obj.add(df.format(fct.get(i).getLkg()));
                obj.add(df.format(fct.get(i).getForecastlkg()));
                 obj.add(df.format(fct.get(i).getDifference())+"%");
                list.add(obj);
            }
        }
            else if(caldb.checkifMilling()){
                
                if(production.size()>3){
                    ces = new ArrayList<>();
                    for(int i=0; i < production.size();i++){
                        cen = new cropEstimate();
                        cen.setActual(production.get(i).getTc());
                        cen.setArea(production.get(i).getArea());
                        cen.setLkg(production.get(i).getLkg());
                        cen.setRainfall(production.get(i).getRainfall());
                        ces.add(cen);
                    }
                    
                }
                
            for(int i=0; i<production.size(); i++){
                ArrayList<String> obj = new ArrayList<>();
                obj.add(Integer.toString(calist.get(0).getYear()));
                obj.add(production.get(i).getWeek_ending().toString());
                obj.add(df.format(production.get(i).getArea()));
                obj.add(production.get(i).getRainfall().toString());
                obj.add(df.format(production.get(i).getTc()));
                if(i>3){
                    ces = new ArrayList<>();
                    for(int j=0;j<i;j++){
                        cen = new cropEstimate();
                        cen.setActual(production.get(j).getTc());
                        cen.setArea(production.get(j).getArea());
                        cen.setLkg(production.get(j).getLkg());
                        cen.setRainfall(production.get(j).getRainfall());
                        ces.add(cen);
                    }
                }
                System.out.println(ces+"this is what ????????????????????/");
                double  dle =   estdb.genForecast1(ce, ces);
                double  dlkg = estdb.genLKGForecast1(ce, ces);
                obj.add(df.format(dle));
                double diff = ((dle-production.get(i).getTc())/dle)*100;
                obj.add(df.format(diff));
                obj.add(df.format(production.get(i).getLkg()));
                obj.add(df.format(dlkg));
                double difflkg = ((dlkg-production.get(i).getLkg())/dlkg)*100;
                obj.add(df.format(difflkg));
                list.add(obj);
            }   
            }
        }
      data.put("data", list);
          response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
