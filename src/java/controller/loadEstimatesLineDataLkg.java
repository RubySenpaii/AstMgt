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
import java.util.List;
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
public class loadEstimatesLineDataLkg extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        CropEstimateDB estdb = new CropEstimateDB();

        HttpSession session = request.getSession();

        ArrayList<cropEstimate> fct = estdb.viewAllDiffEstimates();
        JSONObject data = new JSONObject();
     
      
        JSONArray category = new JSONArray();
        JSONArray bar = new JSONArray();
        JSONArray est1 = new JSONArray();
        JSONArray est2 = new JSONArray();
        JSONArray est3 = new JSONArray();

        if (fct != null) {
            for(int i = 0; i < fct.size(); i++){
                category.add(fct.get(i).getYear());
                if(fct.get(i).getLkg()==0.0){
                    bar.add(null);
                }else{
                  bar.add(fct.get(i).getLkg());  
                }
                   List<Double> doublist=new ArrayList<>();
                doublist.add(fct.get(i).getForecastlkg());
                doublist.add(fct.get(i).getForecastlkg2());
                doublist.add(fct.get(i).getForecastlkg3());
//                if(){
                if(estdb.closest(fct.get(i).getLkg(),doublist)==fct.get(i).getForecastlkg()){
                     JSONObject colpick = new JSONObject();
                    colpick.put("y", fct.get(i).getForecastlkg());
                    colpick.put("borderColor", "red");
                    est1.add(colpick);
                }else {
                     est1.add(fct.get(i).getForecastlkg());
                }
                if(estdb.closest(fct.get(i).getLkg(),doublist)==fct.get(i).getForecastlkg2()){
                     JSONObject colpick = new JSONObject();
                    colpick.put("y", fct.get(i).getForecastlkg2());
                    colpick.put("borderColor", "red");
              est2.add(colpick);
                }else{
                      est2.add(fct.get(i).getForecastlkg2());
                }
                if(estdb.closest(fct.get(i).getLkg(),doublist)==fct.get(i).getForecastlkg3()){
                     JSONObject colpick = new JSONObject();
                    colpick.put("y", fct.get(i).getForecastlkg3());
                    colpick.put("borderColor", "red");
                    est3.add(colpick);
                }else{
                     est3.add(fct.get(i).getForecastlkg3());
                }
                
//                est1.add(fct.get(i).getForecastlkg());
//                est2.add(fct.get(i).getForecastlkg2());
//                est3.add(fct.get(i).getForecastlkg3());
            }
            
            
        }


        data.put("categ", category);
        data.put("bard", bar);
        data.put("estd", est1);
        data.put("est2d", est2);
        data.put("est3d", est3);
        
        
        
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
       // response.getWriter().write(data.toString());
       
    }

}
