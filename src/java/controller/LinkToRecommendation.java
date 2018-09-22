/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ForumDB;
import db.fixedRecDB;
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
public class LinkToRecommendation extends BaseServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            fixedRecDB rdb = new fixedRecDB();
            ForumDB fdb = new ForumDB();
            int fields_id = Integer.parseInt(request.getParameter("fields"));
            String title = request.getParameter("title");
            String lined = request.getParameter("date");
            HttpSession session = request.getSession();
            java.sql.Date rdate = (java.sql.Date) session.getAttribute("todayDate");     
            System.out.println(rdate +" THE NEW DATE");
            int test = 0;
            int test2 = 0;
            int notif = 0;
            int recom_id = 0;
            Enumeration<String> parameterNames = request.getParameterNames();
            String paramName;
            ArrayList<String> pT = new ArrayList<String>();
            while (parameterNames.hasMoreElements()) {
                paramName = parameterNames.nextElement();
                System.out.println(paramName);
                if (paramName.startsWith("recid")) {
                    for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                        pT.add(request.getParameterValues(paramName)[i]);
                        System.out.println(request.getParameterValues(paramName)[i]+ "ANO TO ?");
                        //connects recommendation to problem table
                         recom_id = Integer.parseInt(request.getParameterValues(paramName)[i]);
                        //int test = recDB.connectRecommendationtoProblem(check,prob_id);
                         test2 = fdb.updatePostRecommendations(title, fields_id, recom_id);
                         
                    }
                    
                }
            }
            test = rdb.linktoRecommendation(fields_id, recom_id, rdate, "Active");
            notif = fdb.addRecommendationNotification(fields_id, "You have been suggested to do this recommendation: "+recom_id, rdate, recom_id, fields_id);
            if (test > 0 || test2 >0){
                
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

}
