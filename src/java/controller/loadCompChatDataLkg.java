/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CropEstimateDB;
import db.fixedRecDB;
import entity.FarmRecTable;
import entity.Recommendation;
import entity.cropEstimate;
import java.io.IOException;
import java.io.PrintWriter;
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
public class loadCompChatDataLkg extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        CropEstimateDB estdb = new CropEstimateDB();

        HttpSession session = request.getSession();

        ArrayList<cropEstimate> fct = estdb.viewCropEstBarChart();
        JSONObject data = new JSONObject();
        JSONArray series = new JSONArray();
JSONArray drilldata=new JSONArray();
        if (fct != null) {
            //actual loop
            String val = "actual";
            for (int z = 0; z <= 1; z++) {
                System.out.println(z);
                if (z == 1) {
                    val = "estimated";
                }
                JSONObject actser = new JSONObject();
                JSONArray yrlist = new JSONArray();
                actser.put("name", val);
                for (int i = 0; i < fct.size(); i++) {
                    JSONObject yrobj = new JSONObject();
                    yrobj.put("name", fct.get(i).getYear());
                     if(z!=1){
                    yrobj.put("y", fct.get(i).getLkg());
                 }else{
                     yrobj.put("y", fct.get(i).getForecastlkg());
                         }
                   yrobj.put("drilldown", fct.get(i).getYear() +"-"+ val);
                    yrlist.add(yrobj);
              JSONObject monthobj = new JSONObject();
            monthobj.put("id", fct.get(i).getYear()+"-"+val); 
            //added this for the series legend ONLY
              monthobj.put("name", val); 
            JSONArray months = new JSONArray();  
            for(int d=0;d<fct.get(i).getMonthcropest().size();d++){
                 JSONArray month = new JSONArray(); 
                 month.add(fct.get(i).getMonthcropest().get(d).getMonth());
                 if(z!=1){
                     month.add(fct.get(i).getMonthcropest().get(d).getLkg());
                 }else{
                     month.add(fct.get(i).getMonthcropest().get(d).getForecastlkg());
                 }
                 months.add(month);
                 }
                 monthobj.put("data", months);
                 drilldata.add(monthobj);
                }
                actser.put("data", yrlist);
                series.add(actser);
                 }

System.out.println(series);
System.out.println(drilldata);

        }


        data.put("list", series);
        data.put("dlist", drilldata);
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
       // response.getWriter().write(data.toString());
       
    }

}
