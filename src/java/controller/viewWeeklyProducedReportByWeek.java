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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bryll Joey Delfin
 */
public class viewWeeklyProducedReportByWeek extends HttpServlet {

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
            String type = request.getParameter("type");
            CropBoard cropB = new CropBoard();
            CropBoardDB cdb = new CropBoardDB();
            HttpSession session = request.getSession();
            CalendarDB caldb = new CalendarDB();
            ArrayList<entity.Calendar> calist = caldb.getCurrentYearDetails();
            Date date = calist.get(0).getTodayDate();
            int todayYear = calist.get(0).getYear();
            String id = request.getParameter("id");
            int i = Integer.parseInt(id);
            if(todayYear <= 2016){
                ArrayList<CropBoard> cT = new ArrayList<CropBoard>();
            cT = cdb.getWeeklyProducedReport(type, todayYear, date.toString());
            java.sql.Date cdate = cT.get(i).getWeek_ending();
            Calendar cal = Calendar.getInstance();
            cal.setTime(cdate);
            int yearpicked = cal.get(Calendar.YEAR);
            int woyp = cdb.getWeekOfYear(cdate.toString());
            ArrayList<CropBoard> cTr = new ArrayList<CropBoard>();
            cTr = cdb.getWeeklyProducedReportByRegion(type,todayYear,cdate.toString(), woyp);
            if(cTr != null){
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(cdate);
//                int yearpicked = cal.get(Calendar.YEAR);
                
                session.setAttribute("datepick", cdate);
                session.setAttribute("todayYear", todayYear);
                session.setAttribute("yearpicked", yearpicked);
                session.setAttribute("WOF", woyp);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/RegionWeekView.jsp");
                rd.forward(request, response);
            }
            else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            }
            else {
                ArrayList<CropBoard> cT = new ArrayList<CropBoard>();
            cT = cdb.getCurrentWeeklyProducedReport(type, todayYear, date.toString());
            java.sql.Date cdate = cT.get(i).getWeek_ending();
            Calendar cal = Calendar.getInstance();
            cal.setTime(cdate);
            int yearpicked = cal.get(Calendar.YEAR);
            int woyp = cdb.getWeekOfYear(cdate.toString());
            ArrayList<CropBoard> cTr = new ArrayList<CropBoard>();
            cTr = cdb.getCurrentWeeklyProducedReportByRegion(type,todayYear,cdate.toString(), woyp);
            if(cTr != null){
//                Calendar cal = Calendar.getInstance();
//                cal.setTime(cdate);
//                int yearpicked = cal.get(Calendar.YEAR);
                
                session.setAttribute("datepick", cdate);
                session.setAttribute("todayYear", todayYear);
                session.setAttribute("yearpicked", yearpicked);
                session.setAttribute("WOF", woyp);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/RegionWeekView.jsp");
                rd.forward(request, response);
            }
            else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            }
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
