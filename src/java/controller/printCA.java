/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CropAssessmentDB;
import entity.CropNarrative;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ndrs
 */
public class printCA extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
      response.setContentType("text/html;charset=UTF-8");
      
       HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            CropAssessmentDB cadb=new CropAssessmentDB();
          
           
             String cropyear= request.getParameter("cropyear");
           String weekending= request.getParameter("weekending");
//          Date.valueOf(weekending)
           
     cadb.printCA(Integer.parseInt(cropyear), "TARLAC",weekending);
          
        
        }
    
    }

}
