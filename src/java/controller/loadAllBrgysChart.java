/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropEstimateDB;
import db.ProductionDB;
import db.ProductionDB;
import db.ProductionDB;
import db.fixedRecDB;
import entity.Calendar;
import entity.FarmRecTable;
import entity.Recommendation;
import entity.brgySummary;
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
public class loadAllBrgysChart extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        ProductionDB pdb = new ProductionDB();

        HttpSession session = request.getSession();
        String mname = request.getParameter("name");

        CalendarDB caldb = new CalendarDB();
        ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
        int cropyr = calist.get(0).getYear();

        ArrayList<brgySummary> fct = null;
        if (cropyr > 2016) {
            if (caldb.checkifMilling()) {
                fct = pdb.viewCurrBarangayMuniSummary(cropyr, mname);
            } else {
                ArrayList<Integer> histyrs = pdb.getDistinctHistProdYrs(cropyr);
                fct = pdb.viewBarangayMuniSummary(histyrs.get(0), mname);
            }
        } else {
            fct = pdb.viewBarangayMuniSummary(cropyr, mname);
        }

        String district = "TARLAC";
//        String average = pdb.getDistrictProductionAvg(year,district);
        String type = request.getParameter("type");
        String average = null;

        if (type.equalsIgnoreCase("area")) {
            if (cropyr > 2016 && caldb.checkifMilling()) {
                average = pdb.getCurrDistrictHarvestAreaAvgTest3(cropyr, mname, district);
            } else {
                average = pdb.getDistrictAreaAvgTest1(cropyr, mname, district);
            }

        } else {
            if (cropyr > 2016) {
                if (caldb.checkifMilling()) {
                    average = pdb.getCurrDistrictProductionAvgTest3(cropyr, mname, district);
                    System.out.println(average + "curr prod avg");
                } else {
                    ArrayList<Integer> histyrs = pdb.getDistinctHistProdYrs(cropyr);
                    average = pdb.getDistrictProductionAvgTest3(histyrs.get(0), mname, district);
                    System.out.println(average + "last year hist prod avg");
                }

            } else {
                average = pdb.getDistrictProductionAvgTest3(cropyr, mname, district);
                System.out.println(average + "hist prod avg");
            }

        }
        if (type.equalsIgnoreCase("yield")) {
            Double yield = null;
            String tarea = null;
            if (cropyr > 2016 && caldb.checkifMilling()) {
                tarea = pdb.getCurrDistrictHarvestAreaAvgTest3(cropyr, mname, district);
            } else {
                tarea = pdb.getDistrictAreaAvgTest1(cropyr, mname, district);
            }

            yield = Double.parseDouble(average) / Double.parseDouble(tarea);
            average = Double.toString(yield);

        }

        JSONObject data = new JSONObject();

        JSONArray category = new JSONArray();
        JSONArray bar = new JSONArray();
        JSONArray avgProd = new JSONArray();

        if (fct != null) {
            for (int i = 0; i < fct.size(); i++) {
                category.add(fct.get(i).getBarangay());

                if (type.equalsIgnoreCase("area")) {
                    bar.add(fct.get(i).getArea());
                } else if (type.equalsIgnoreCase("yield")) {
                    bar.add(fct.get(i).getActual() / fct.get(i).getArea());
                } else {
                    bar.add(fct.get(i).getActual());
                }
                avgProd.add(Double.parseDouble(average));
            }
        }

        data.put("categ", category);
        data.put("bar", bar);
        data.put("avgprod", avgProd);
        data.put("curyr", cropyr);

        System.out.println(data);

        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());

    }

}
