/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.FarmerDB;
import db.ProductionDB;
import db.ProgramsDB;
import entity.Calendar;
import entity.Farm;
import entity.Farmer;
import entity.Problems;
import entity.Programs;
import entity.brgySummary;
import entity.municipalSummary;
import entity.programsKPI;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
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
 * @author ndrs
 */
public class viewFarmerProfile extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();

        FarmerDB farmerdb = new FarmerDB();
        HttpSession session = request.getSession();
        Farmer farmer;
        ////////////////****** TODO CHECK IF NAME IS A FARMR
        String name = request.getParameter("name");

        System.err.println("TODAYS user " + session.getAttribute("user"));
        System.err.println("TODAYS DATE " + session.getAttribute("todayDate"));
        RequestDispatcher rd;
        if (farmerdb.searchNameInMunicipal(name)) {
             ProductionDB prodb = new ProductionDB();
            municipalSummary ms=prodb.viewMunicipalBasicDet(name);
            session.setAttribute("munidet", ms);
            rd = context.getRequestDispatcher("/municipalSummary.jsp");
            
        } else if (farmerdb.searchNameInBarangay(name)) {
                  ProductionDB prodb = new ProductionDB();
                 brgySummary brgy = prodb.viewBrgyBasicDet(name);
               session.setAttribute("brgydet", brgy);
            rd = context.getRequestDispatcher("/brgySummary.jsp");
       
        } else {
         
            ProductionDB prodb=new ProductionDB();
             CalendarDB caldb= new CalendarDB();
            ArrayList<Calendar> calist= caldb.getCurrentYearDetails();
            int cropyr=calist.get(0).getYear();
            Date todayDate=calist.get(0).getTodayDate();
            
             farmer = farmerdb.viewFarmerDetails(name);
             Farmer farmer2;
              if(cropyr>2016){
            if(caldb.checkifMilling()){
                  farmer2  = prodb.viewCurrFarmerSummarybyYear(name,cropyr,todayDate);
                   
            }else{
                   ArrayList<Integer>histyrs= prodb.getDistinctHistProdYrs(cropyr);
                    farmer2  = prodb.viewFarmerSummarybyYearTest(null,name,histyrs.get(0));
               }
        }else{
          farmer2  = prodb.viewFarmerSummarybyYearTest(null,name,cropyr);
        }
              DecimalFormat df = new DecimalFormat(".##");
              farmer.setCurHA(farmer2.getTotalArea());
              farmer.setCurProd(Double.parseDouble(df.format(farmer2.getProduction())));
              farmer.setCurYield(Double.parseDouble(df.format(farmer.getCurProd()/farmer.getCurHA())));
              
           
            session.setAttribute("farmDet", farmer);
            session.setAttribute("farm", name);
            rd = context.getRequestDispatcher("/viewFarmerProfile.jsp");
        }
        rd.forward(request, response);
        response.setCharacterEncoding("utf-8");

    }

}
