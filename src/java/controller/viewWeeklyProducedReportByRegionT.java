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
public class viewWeeklyProducedReportByRegionT extends HttpServlet {

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
            CropBoard cropB = new CropBoard();
            CropBoardDB cdb = new CropBoardDB();
            HttpSession session = request.getSession();
            CalendarDB caldb = new CalendarDB();
            ArrayList<entity.Calendar> calist = caldb.getCurrentYearDetails();
            Date date = calist.get(0).getTodayDate();
            int yp = (int) session.getAttribute("yearpicked");
            int todayYear = (int) session.getAttribute("todayYear"); 
            int weekOfYear = (int) session.getAttribute("WOF");
            Date cdate = (Date) session.getAttribute("datepick");
            System.out.println(todayYear +"TANGINA"+weekOfYear + " TAE NAMN");
            ArrayList<CropBoard> cT = new ArrayList<CropBoard>();
            JSONObject data = new JSONObject();
            DecimalFormat df = new DecimalFormat("#.00");
                df.setGroupingUsed(true);
                df.setGroupingSize(3);
            if(todayYear <= 2016){
                cT = cdb.getWeeklyProducedReportByRegionT(todayYear,cdate.toString(),weekOfYear);
            JSONArray prod = new JSONArray();
            if(cT != null){
                for(int i=0;i<cT.size();i++){
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(cT.get(i).getDistrict());
                    list.add(df.format(cT.get(i).getArea()));
                    list.add(df.format(cT.get(i).getTc()));
                    list.add(df.format(cT.get(i).getLkg()));
                    String id = cT.get(i).getDistrict()+","+cdate.toString();
                    list.add(id);
                    prod.add(list);
                }
            }
            data.put("data", prod);
            session.setAttribute("todayYear", todayYear);
            session.setAttribute("weekOfYear", weekOfYear);
            }
            else {
                cT = cdb.getCurrentWeeklyProducedReportByRegion("HA",todayYear,date.toString(),weekOfYear);
                
            JSONArray prod = new JSONArray();
            if(cT != null){
                for(int i=0;i<cT.size();i++){
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(cT.get(i).getDistrict());
                    list.add(df.format(cT.get(i).getArea()));
                    list.add(df.format(cT.get(i).getTc()));
                    list.add(df.format(cT.get(i).getLkg()));
                    String id = cT.get(i).getDistrict()+","+cdate.toString();
                    list.add(id);
                    prod.add(list);
                }
            }
            data.put("data", prod);
            session.setAttribute("todayYear", todayYear);
            session.setAttribute("weekOfYear", weekOfYear);
            }
            
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
            
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
