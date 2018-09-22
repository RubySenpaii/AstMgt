/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.FarmerDB;
import db.ProductionDB;
import db.ProgramsDB;
import entity.Farmer;
import entity.Problems;
import entity.Programs;
import entity.brgySummary;
import entity.municipalSummary;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ndrs
 */
public class viewBrgySummary extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        FarmerDB farmerdb = new FarmerDB();
        ProductionDB prodb = new ProductionDB();
        HttpSession session = request.getSession();
        brgySummary brgy;
        ////////////////****** TODO CHECK IF NAME IS A FARMR
        String name = request.getParameter("name");
        String mname = request.getParameter("bname");
        System.out.println(mname+"municipalityname");

//        System.err.println("TODAYS user " + session.getAttribute("user"));
//        System.err.println("TODAYS DATE " + session.getAttribute("todayDate"));
        RequestDispatcher rd=null;
        if (farmerdb.searchNameInBarangay(name)) {
              brgy = prodb.viewBrgyBasicDet(name);
               session.setAttribute("brgydet", brgy);
               session.setAttribute("mname", mname);
            rd = context.getRequestDispatcher("/brgySummary.jsp");
        }
        rd.forward(request, response);
        response.setCharacterEncoding("utf-8");

    }

}
