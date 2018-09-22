/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.ForumDB;
import db.ProblemsDB;
import entity.Calendar;
import entity.Problems;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class sendAlertToFarmers extends HttpServlet {

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
            HttpSession session = request.getSession();
            ProblemsDB pdb = new ProblemsDB();
            ForumDB fdb = new ForumDB();
            String muni = (String) session.getAttribute("muni");
            String barangay = (String) session.getAttribute("barangay");
            String district = pdb.getDistrict(barangay, muni);
            String msg = request.getParameter("message");
            if (msg.equalsIgnoreCase("")) {
                msg = "The MDO has alerted you to be aware that a disaster is happening near your barangay";
            }
            System.out.println(msg + " dafuq ?");
            Problems p = (Problems) session.getAttribute("problem");
            CalendarDB caldb = new CalendarDB();
            ArrayList<Calendar> calist = caldb.getCurrentYearDetails();
            Date pdate = calist.get(0).getTodayDate();
            int check = 0;
            int test = 0;
            if (p != null) {
                check = pdb.addAlertToFarmers(p.getProb_id(), msg, pdate, barangay, district, muni);
            }
            if (check > 0) {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/Disaster Report.jsp");
                rd.forward(request, response);
            } else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
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
