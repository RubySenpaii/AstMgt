/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import entity.Calendar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class editDates extends HttpServlet {

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
            int check = 0;
            HttpSession session = request.getSession();
            
            String district = "Tarlac";
            /* TODO output your page here. You may use following sample code. */
            Calendar cal = new Calendar();
            CalendarDB cdb = new CalendarDB();
            ArrayList<Calendar> calist=cdb.getCurrentYearDetails();
            int year= calist.get(0).getYear();
            ArrayList<Calendar> cT = new ArrayList<Calendar>();
            if(request.getParameter("pstart")!= ""){
                cal = new Calendar();
                cal.setPhase("Planting");
                String reserv = request.getParameter("pstart");
                String[] lines = reserv.split("-");
                Date start = cdb.convertStringtoSQLDate(lines[0]);
                Date end = cdb.convertStringtoSQLDate(lines[1]);
                cal.setStarting(start);
                cal.setEnding(end);
                cT.add(cal);
            }
            if(request.getParameter("gstart")!= ""){
                cal = new Calendar();
                cal.setPhase("Germination");
                String reserv = request.getParameter("gstart");
                String[] lines = reserv.split("-");
                Date start = cdb.convertStringtoSQLDate(lines[0]);
                Date end = cdb.convertStringtoSQLDate(lines[1]);
                cal.setStarting(start);
                cal.setEnding(end);
                cT.add(cal);
            }
            if(request.getParameter("mstart")!= ""){
                cal = new Calendar();
                cal.setPhase("Milling");
                String reserv = request.getParameter("mstart");
                String[] lines = reserv.split("-");
                Date start = cdb.convertStringtoSQLDate(lines[0]);
                Date end = cdb.convertStringtoSQLDate(lines[1]);
                cal.setStarting(start);
                cal.setEnding(end);
                cT.add(cal);
            }
            if(request.getParameter("tstart")!= ""){
                cal = new Calendar();
                cal.setPhase("Tillering");
                String reserv = request.getParameter("tstart");
                String[] lines = reserv.split("-");
                Date start = cdb.convertStringtoSQLDate(lines[0]);
                Date end = cdb.convertStringtoSQLDate(lines[1]);
                cal.setStarting(start);
                cal.setEnding(end);
                cT.add(cal);
            }
            if(request.getParameter("sstart")!= ""){
                cal = new Calendar();
                cal.setPhase("Stalk Elongation");
                String reserv = request.getParameter("sstart");
                String[] lines = reserv.split("-");
                Date start = cdb.convertStringtoSQLDate(lines[0]);
                Date end = cdb.convertStringtoSQLDate(lines[1]);
                cal.setStarting(start);
                cal.setEnding(end);
                cT.add(cal);
            }
            if(request.getParameter("rstart")!= ""){
                cal = new Calendar();
                cal.setPhase("Ripening");
                String reserv = request.getParameter("rstart");
                String[] lines = reserv.split("-");
                Date start = cdb.convertStringtoSQLDate(lines[0]);
                Date end = cdb.convertStringtoSQLDate(lines[1]);
                cal.setStarting(start);
                cal.setEnding(end);
                cT.add(cal);
            }
            if(request.getParameter("ystart")!= ""){
                cal = new Calendar();
                cal.setPhase("Yield Formation");
                String reserv = request.getParameter("ystart");
                String[] lines = reserv.split("-");
                System.out.println(lines[1]+"ENDDDDDDDDDDDDDDDDD");
                Date start = cdb.convertStringtoSQLDate(lines[0]);
                Date end = cdb.convertStringtoSQLDate(lines[1]);
                System.out.println(end+"ENDDDDDDDDDDDDDDDDD");
                cal.setStarting(start);
                cal.setEnding(end);
                cT.add(cal);
            }
            if(cT.size()==1){
            for(int i=0; i<cT.size();i++){
                check = cdb.updatePhaseDates(cT.get(i), year,district);
            }    
            }
            else{
                for(int i=0; i<cT.size();i++){
                check = cdb.addPhasesDates(cT.get(i), year+1,district);
            }
            }
            
            if (check>0) {
            ServletContext context = getServletContext();
        response.sendRedirect("viewPhasesDates");
//            rd.forward(request, response);
        } else {
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/Homepage.jsp");
            rd.forward(request, response);
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
