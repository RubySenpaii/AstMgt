/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ForumDB;
import db.ProblemsDB;
import db.fixedRecDB;
import entity.Forum;
import entity.Recommendation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
public class linktoPost extends HttpServlet {

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
            ForumDB fdb = new ForumDB();
            ProblemsDB pdb = new ProblemsDB();
            fixedRecDB rdb = new fixedRecDB();
            int test = 0;
            java.sql.Date tdate = (java.sql.Date) session.getAttribute("todaysDate");
            int fields_id = Integer.parseInt(request.getParameter("fields"));
            Enumeration<String> parameterNames = request.getParameterNames();
            String paramName;
            String param = "";
            ArrayList<String> pT = new ArrayList<String>();
            while (parameterNames.hasMoreElements()) {
                paramName = parameterNames.nextElement();
                System.out.println(paramName);
                if (paramName.startsWith("probid1[]")) {
                    for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                        pT.add(request.getParameterValues(paramName)[i]);
                         param = request.getParameterValues(paramName)[i];
                        //connects recommendation to problem table 
                       //int test = recDB.connectRecommendationtoProblem(check,prob_id);
                        
                    }

                }
            }
            Forum f = new Forum();
            f = fdb.getForumDetailByName(param);
            if(f.getProb_id()!= null){
            int pfid = pdb.linktoProblems(fields_id, f.getProb_id(), tdate, "Verfying");
            test  = fdb.addRedirectionNotification(fields_id, "You been redirected to this post : " + f.getTitle(), tdate,f.getId(),pfid,0);
            }
            else if(f.getRecom_id() != null){
            int rfid = rdb.linktoRecommendation(fields_id, f.getRecom_id(), tdate,"Active");
            test  = fdb.addRedirectionNotification(fields_id, "You been redirected to this post : " + f.getTitle(), tdate,f.getId(),0,rfid);
            }
            
            if(test>0){
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/Forum.jsp");
                rd.forward(request, response);
            }
            else{
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
