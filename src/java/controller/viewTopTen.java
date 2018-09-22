/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.ProblemsDB;
import db.fixedRecDB;
import db.subjectiveRecDB;
import entity.Calendar;
import entity.Problems;
import entity.Recommendation;
import java.io.IOException;
import java.io.PrintWriter;
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
public class viewTopTen extends HttpServlet {

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
            CalendarDB cdb = new CalendarDB();
            ArrayList<Calendar> cT = new ArrayList<Calendar>();
            ArrayList<Calendar> calist = cdb.getCurrentYearDetails();//gets the phases/today/crop yr
            Integer cropyear = calist.get(0).getYear();
            cT = cdb.getPhases(cropyear);
            subjectiveRecDB recdb = new subjectiveRecDB();
            fixedRecDB fdb = new fixedRecDB();
            ProblemsDB pdb = new ProblemsDB();
            ArrayList<Problems> pT = new ArrayList<Problems>();
            ArrayList<Recommendation> rT = new ArrayList<Recommendation>();
            ArrayList<Problems> problist = null;
            ArrayList<Recommendation> reclist = null;
            rT = recdb.getTopRec();
            pT = pdb.getTopProb();
            for(int i =0; i< pT.size();i++){
                reclist = new ArrayList<Recommendation>();
                reclist = pdb.getAllSolutions(pT.get(i).getProb_id());
                pT.get(i).setReclist(reclist);
            }
            for(int i=0;i<rT.size();i++){
                problist = new ArrayList<Problems>();
                problist = fdb.viewProbRecTable(rT.get(i).getId());
                rT.get(i).setProblist(problist);
            }
            HttpSession session = request.getSession();
            ServletContext context = getServletContext();
            
            if(rT != null && pT != null){
                session.setAttribute("cat", cT);
                session.setAttribute("rec", rT);
                session.setAttribute("pro", pT);
                RequestDispatcher rd = context.getRequestDispatcher("/topRecommendations.jsp");
                rd.forward(request, response);
            }
            else{
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
