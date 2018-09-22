/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropBoardDB;
import entity.CropBoard;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Bryll Joey Delfin
 */
public class viewWeeklyProducedReportByRegion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String type = request.getParameter("id");
            CropBoard cropB = new CropBoard();
            CropBoardDB cdb = new CropBoardDB();
            HttpSession session = request.getSession();
            CalendarDB caldb = new CalendarDB();
            ArrayList<entity.Calendar> calist = caldb.getCurrentYearDetails();
            Date date = calist.get(0).getTodayDate();
            int todayYear = calist.get(0).getYear();
            Date datep = (Date) session.getAttribute("datepick");
            Calendar cal = Calendar.getInstance();
            cal.setTime(datep);
            int weekOfYear = 0;
            if(todayYear <= 2016){
             weekOfYear = cdb.getWeekOfYear(datep.toString());
            }
            else{ weekOfYear = cdb.getWeekOfYear(datep.toString());}
            double avge = 0;
            ArrayList<CropBoard> cT = new ArrayList<CropBoard>();
            ArrayList<CropBoard> aT = new ArrayList<CropBoard>();
            JSONObject production = new JSONObject();
            if(calist.get(0).getYear() <= 2016){                // START OF HISTORICAL 
                cT = cdb.getWeeklyProducedReportByRegion(type, todayYear,datep.toString(), weekOfYear);
            aT = cdb.getWeeklyAverageProducedReportDetails(type, todayYear, datep.toString());
            
            JSONArray listp = new JSONArray();
            JSONArray listc = new JSONArray();
            JSONArray lista = new JSONArray();
            for(int i=0;i<aT.size();i++){
                if(aT.get(i).getWeek_ending().toString().equalsIgnoreCase(datep.toString())){
                    avge = aT.get(i).getProduction();
                    System.out.println(aT.get(i).getArea()+"area");
                    }
            }
            
            if(cT != null){
                for(int i =0; i<cT.size();i++){
                    ArrayList<String> c = new ArrayList<String>();
                    ArrayList<Double> p = new ArrayList<Double>();
                    ArrayList<Double> a = new ArrayList<Double>();
                    c.add(cT.get(i).getDistrict());
                    p.add(cT.get(i).getProduction());
                    a.add(avge);
                    listp.add(p);
                    listc.add(c);
                    lista.add(a);
                }
            }
            production.put("categories", listc);
            production.put("prod", listp);
            production.put("avg", lista);
            }
            else{  // START OF CURRENT
            aT = cdb.getCurrentWeeklyProducedReport(type, todayYear, date.toString());
            double sum = 0;
            double totala = 0;
            int div = aT.size();
            for (int i = 0; i < aT.size(); i++) {
                sum += aT.get(i).getProduction();
            }
            totala = sum / div;
            double avga = Math.round(totala * 100.0) / 100.0;
            cT = cdb.getCurrentWeeklyProducedReportByRegion(type, todayYear,date.toString(), weekOfYear);
            JSONArray listp = new JSONArray();
            JSONArray listc = new JSONArray();
            JSONArray lista = new JSONArray();
            if(cT != null){
                for(int i =0; i<cT.size();i++){
                    ArrayList<String> c = new ArrayList<String>();
                    ArrayList<Double> p = new ArrayList<Double>();
                    ArrayList<Double> a = new ArrayList<Double>();
                    c.add(cT.get(i).getDistrict());
                    p.add(cT.get(i).getProduction());
                    a.add(avga);
                    listp.add(p);
                    listc.add(c);
                    lista.add(a);
                }
            }
            production.put("categories", listc);
            production.put("prod", listp);
            production.put("avg", lista);
            }
//            session.setAttribute("avgyear", todayYear);
//            session.setAttribute("weekOfYear", weekOfYear);
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(production.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
