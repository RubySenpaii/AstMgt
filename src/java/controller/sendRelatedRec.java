/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.fixedRecDB;
import entity.programsKPI;
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

/**
 *
 * @author ndrs
 */
public class sendRelatedRec extends BaseServlet {

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
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();
         Enumeration<String> parameterNames = request.getParameterNames();
           String paramName;
           ArrayList<String> farmz= new ArrayList<>();
           ArrayList<String> recz= new ArrayList<>();
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            System.out.println(paramName);

             if (paramName.startsWith("id")) {
        
                 for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    System.out.println(request.getParameterValues(paramName)[i]+"rec id");
                    recz.add(request.getParameterValues(paramName)[i]);
                }
              
            }
            else if (paramName.startsWith("farmz")) {
                    for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    System.out.println(request.getParameterValues(paramName)[i]+"farmz");
                    farmz.add(request.getParameterValues(paramName)[i]);
                          }
              
            }

        }
    String message= request.getParameter("description");
        fixedRecDB recdb=new fixedRecDB();
        recdb.sendRecommendations(message,farmz, recz);
        String xfarm=farmz.get(0);
                response.sendRedirect("viewFieldDetails?id="+xfarm);

        response.setCharacterEncoding("utf-8");
    } 

}


