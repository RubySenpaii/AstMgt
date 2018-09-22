/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CropEstimateDB;
import db.ProductionDB;
import db.fixedRecDB;
import entity.FarmRecTable;
import entity.Recommendation;
import entity.brgySummary;
import entity.cropEstimate;
import entity.municipalSummary;
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
public class loadBrgySumYrsChart extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        ProductionDB pdb= new ProductionDB();

        HttpSession session = request.getSession();
           String bname= request.getParameter("name");
        ArrayList<brgySummary> fct = pdb.viewBrgySummaryDet(bname);
        ArrayList<brgySummary> curlist = pdb.viewCurrBrgySummaryDet(bname);
        
        
        ArrayList<String> avgprod= pdb.viewDistrictAvgProdBrgySummary();
        JSONObject data = new JSONObject();
     
      
        JSONArray category = new JSONArray();
        JSONArray bar = new JSONArray();
         JSONArray line = new JSONArray();
    
        if (fct != null) {
              
            for(int i = 0; i < fct.size(); i++){
                category.add(Integer.toString(fct.get(i).getYear()));
                bar.add(fct.get(i).getActual());
                 line.add(Double.parseDouble(avgprod.get(i)));
             }
            
            
        }
 if (curlist != null) {
              
            for(int i = 0; i < curlist.size(); i++){
                category.add(Integer.toString(curlist.get(i).getYear()));
                bar.add(curlist.get(i).getActual());
            line.add(Double.parseDouble(avgprod.get(0)));
                
             }
            
            
        }

       data.put("categ", category);
       data.put("bar", bar);
     data.put("line", line);
      
        
        
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
       // response.getWriter().write(data.toString());
       
    }

}
