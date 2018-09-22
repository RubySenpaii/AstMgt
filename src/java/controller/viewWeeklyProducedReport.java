/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropBoardDB;
import entity.Calendar;
import entity.CropBoard;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
public class viewWeeklyProducedReport extends HttpServlet {

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
            ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
            Date date = calist.get(0).getTodayDate();
            int todayYear = calist.get(0).getYear();
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(date);
            int weekOfYear = cal.get(java.util.Calendar.WEEK_OF_YEAR);
            System.out.println(weekOfYear + "what then ?");
            JSONObject production = new JSONObject();
            if(calist.get(0).getYear() <= 2016){ // this is historical
            ArrayList<CropBoard> cT = new ArrayList<CropBoard>();
            ArrayList<CropBoard> avgT = new ArrayList<CropBoard>();
            cT = cdb.getWeeklyProducedReport(type, todayYear, date.toString());
            avgT = cdb.getWeeklyAverageProducedReport(type, todayYear, date.toString());
            double sum = 0;
            double totala = 0;
            int div = avgT.size();
            for (int i = 0; i < avgT.size(); i++) {
                sum += avgT.get(i).getProduction();
            }
            totala = sum / div;
            double avga = Math.round(totala * 100.0) / 100.0;
            JSONArray prod = new JSONArray();
            JSONArray avg = new JSONArray();
            JSONArray dates = new JSONArray();
            if (cT != null) {
                for (int i = 0; i < cT.size(); i++) {
                    ArrayList<Double> d = new ArrayList<Double>();
                    ArrayList<Double> a = new ArrayList<Double>();
                    ArrayList<String> dat = new ArrayList<String>();
                    d.add(cT.get(i).getProduction());
                    a.add(avga);
                    dat.add(cT.get(i).getWeek_ending().toString());
                    prod.add(d);
                    avg.add(a);
                    dates.add(dat);
                }
            }
            session.setAttribute("todayYear", todayYear);
            session.setAttribute("weekOfYear", weekOfYear);
            production.put("prod", prod);
            production.put("average", avg);
            production.put("dates", dates);
            }
            else{ // this is current 
            ArrayList<CropBoard> cT = new ArrayList<CropBoard>();
            cT = cdb.getCurrentWeeklyProducedReport(type, todayYear, date.toString());
            double sum = 0;
            double totala = 0;
            int div = cT.size();
            for (int i = 0; i < cT.size(); i++) {
                sum += cT.get(i).getProduction();
            }
            totala = sum / div;
            double avga = Math.round(totala * 100.0) / 100.0;
            JSONArray prod = new JSONArray();
            JSONArray avg = new JSONArray();
            JSONArray dates = new JSONArray();
            if (cT != null) {
                for (int i = 0; i < cT.size(); i++) {
                    ArrayList<Double> d = new ArrayList<Double>();
                    ArrayList<Double> a = new ArrayList<Double>();
                    ArrayList<String> dat = new ArrayList<String>();
                    d.add(cT.get(i).getProduction());
                    a.add(avga);
                    dat.add(cT.get(i).getWeek_ending().toString());
                    prod.add(d);
                    avg.add(a);
                    dates.add(dat);
                }
            }
            session.setAttribute("todayYear", todayYear);
            session.setAttribute("weekOfYear", weekOfYear);
            production.put("prod", prod);
            production.put("average", avg);
            production.put("dates", dates);
            }
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
