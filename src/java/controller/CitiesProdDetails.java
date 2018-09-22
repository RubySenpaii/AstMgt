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
public class CitiesProdDetails extends HttpServlet {

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
            CropBoard cb = new CropBoard();
            CropBoardDB cdb = new CropBoardDB();
            ArrayList<CropBoard> cT = new ArrayList<CropBoard>();
            String line = request.getParameter("id");
            String[] param = line.split(",");
            Date cdate = Date.valueOf(param[1]);
            System.out.println(cdate + " BEFORE !!!!");
            HttpSession session = request.getSession();
            CalendarDB caldb = new CalendarDB();
            ArrayList<entity.Calendar> calist = caldb.getCurrentYearDetails();
            int year = calist.get(0).getYear();
            Date tdate = calist.get(0).getTodayDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try{
            java.util.Date date = sdf.parse(param[1]);
            cdate = new java.sql.Date(date.getTime());
            } catch (ParseException ex) {
            Logger.getLogger(createNewProgram.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(cdate +" AFTER ");
            Calendar cal = Calendar.getInstance();
            cal.setTime(cdate);
            int wof = cdb.getWeekOfYear(cdate.toString());
            if(year <= 2016){
            cT = cdb.getWeeklyProducedReportByRegionDetails(year,cdate.toString(),param[0],wof);
            if(cT!=null){
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/CitiesWeekView.jsp");
                session.setAttribute("todayDate", cdate);
                session.setAttribute("crop", cT);
                rd.forward(request, response);  
            }
            else{
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            }
            else{
                
                cT = cdb.getCurrentWeeklyProducedReportByRegionDetails(year,tdate.toString(),param[0],wof);
            if(cT!=null){
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/CitiesWeekView.jsp");
                session.setAttribute("todayDate", cdate);
                session.setAttribute("crop", cT);
                rd.forward(request, response);  
            }
            else{
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
