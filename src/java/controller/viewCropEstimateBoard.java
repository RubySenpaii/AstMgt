/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropAssessmentDB;
import db.CropEstimateDB;
import entity.CropAssessment;
import entity.cropEstimate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class viewCropEstimateBoard extends BaseServlet {

   @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
    CropEstimateDB estdb = new CropEstimateDB();
     CalendarDB caldb=new CalendarDB();
   ArrayList<entity.Calendar> cal= caldb.getCurrentYearDetails();
   Integer cropyear=cal.get(0).getYear();
   System.out.println("THIS IS WHAT PASSES US AND Y CROP ESTIMATE DIDNT UPDATE"+cropyear);
    if(cropyear>2016){
                     if(!estdb.checkExistingCropEstYear(cropyear)){
                  
                         estdb.generateYearlyEstimate(1,1); 
                         estdb.updateYearlyEstimate();
                     }else{
                         cropEstimate ces= estdb.viewDistEstbyYear(cropyear);
                       
                            estdb.deleteSelectedDistrictYear(cropyear);
                            estdb.generateYearlyEstimate(ces.getSelection(),ces.getSelectionlkg());
                            estdb.updateYearlyEstimate();
                     }
                     
                 }
    
    
                ArrayList<cropEstimate> fct = estdb.viewAllDiffEstimates();
   
             session.setAttribute("est", fct);
       
          RequestDispatcher  rd = context.getRequestDispatcher("/viewCropEstimateBoard.jsp");
       
        rd.forward(request, response);
        response.setCharacterEncoding("utf-8");

    }

}