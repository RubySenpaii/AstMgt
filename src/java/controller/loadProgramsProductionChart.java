/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropEstimateDB;
import db.ProgramsDB;
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
public class loadProgramsProductionChart extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        ProgramsDB estdb = new ProgramsDB();

        HttpSession session = request.getSession();
        String progname = request.getParameter("name");
        ArrayList<ArrayList<cropEstimate>> fct = estdb.getProductionChartbyProgram(progname);
        ArrayList<cropEstimate> prce = fct.get(0);
        ArrayList<cropEstimate> poce = fct.get(1);
        ArrayList<Integer> categ = estdb.getAllDistinctYrsHistProd();
        ArrayList<entity.Calendar> cal = null;
        CalendarDB caldb=new CalendarDB();
        cal = caldb.getCurrentYearDetails();
        Integer todayyear = cal.get(0).getYear();
        if(todayyear>=2017){
               categ.add(2017);
        }
     
        JSONObject data = new JSONObject();

        JSONArray category = new JSONArray();
        JSONArray bar = new JSONArray();
        JSONArray bar2 = new JSONArray();

        if (fct != null) {
            for (int i = 0; i < categ.size(); i++) {
                //assume 2012 for this loop
                //search for if it exists in loop
                category.add(categ.get(i));
                boolean chck = false;
                if (prce != null) {
                    for (int b = 0; b < prce.size(); b++) {
                        if (categ.get(i) == prce.get(b).getYear()) {
                            bar.add(prce.get(b).getActual());
                            chck = true;
                        }
                    }
                }
                if (chck == false) {
                    bar.add(null);
                }
                boolean chck2 = false;
                if (poce != null) {
                    for (int b = 0; b < poce.size(); b++) {
                        if (categ.get(i) == poce.get(b).getYear()) {
                            bar2.add(poce.get(b).getActual());
                            chck2 = true;
                        }
                    }
                }
                if (chck2 == false) {
                    bar2.add(null);
                }

            }
        }

        data.put("categ", category);
        data.put("prce", bar);
        data.put("poce", bar2);

        System.out.println(data);

        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
       // response.getWriter().write(data.toString());

    }

}
