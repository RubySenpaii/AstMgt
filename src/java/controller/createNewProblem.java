/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ForumDB;
import db.ProblemsDB;
import db.subjectiveRecDB;
import entity.Forum;
import entity.Problems;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
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
public class createNewProblem extends HttpServlet {

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
            Problems p = new Problems();
            ProblemsDB pDB = new ProblemsDB();
            ForumDB fdb = new ForumDB();
            subjectiveRecDB sDB = new subjectiveRecDB();
            int fields_id = Integer.parseInt(request.getParameter("fields"));
            String title = request.getParameter("title");
            String lined = request.getParameter("date");
            Date pdate = Date.valueOf(lined);
            System.out.println(pdate + " BEFORE !!!!");
            try{
            java.util.Date date = new SimpleDateFormat("MM/dd/yyyy").parse(lined);
            pdate = new java.sql.Date(date.getTime());
            } catch (ParseException ex) {
            Logger.getLogger(createNewProgram.class.getName()).log(Level.SEVERE, null, ex);
            } 
            p.setProb_name(request.getParameter("prob_name"));
            p.setProb_details(request.getParameter("description"));
            //p.setType(request.getParameter("type"));
            p.setPhase(request.getParameter("period"));
            p.setStatus("Active");
            HttpSession session = request.getSession();
            java.sql.Date dateparam = (Date) session.getAttribute("todayDate");
            p.setDate_created(dateparam);
            int check = pDB.addProblem(p);
//            Enumeration<String> parameterNames = request.getParameterNames();
//            String paramName;
//            ArrayList<String> pT = new ArrayList<String>();
//            while (parameterNames.hasMoreElements()) {
//                paramName = parameterNames.nextElement();
//                System.out.println(paramName);
//                if (paramName.startsWith("probid")) {
//                    for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
//                        pT.add(request.getParameterValues(paramName)[i]);
//                        System.out.println(request.getParameterValues(paramName)[i]);
//                        //connects problem torecommendation table
//                        int recom_id = Integer.parseInt(request.getParameterValues(paramName)[i]);
//                        int test = sDB.connectRecommendationtoProblem(recom_id, check);
//                    }
//                }
//            }

            if (check > 0){
                fdb.updatePostProblems(title, fields_id, check);
                int pfid = pDB.linktoProblems(fields_id, check, dateparam, p.getStatus());
                fdb.addRecommendationProblem(fields_id, p.getProb_details(), pdate, pfid);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/Forum.jsp");
                rd.forward(request, response);
            }
            else {
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
