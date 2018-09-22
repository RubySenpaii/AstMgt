/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropAssessmentDB;
import entity.CropAssessment;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import entity.Calendar;
import entity.CropNarrative;
import entity.User;
import entity.statusReport;
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
public class selectReportList extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        CalendarDB caldb = new CalendarDB();
        CropAssessmentDB cadb = new CropAssessmentDB();

        //INITIALIZATION OF VALUES;
        RequestDispatcher rd = null;

// *** FROM THE SELECTED PARAMETER
        String mondayofweek = request.getParameter("MondayofWeek");
        String sundayofweek = request.getParameter("SundayofWeek");
        String reptype = request.getParameter("repType");
        String status = request.getParameter("status");
        session.setAttribute("SundayofWeek", Date.valueOf(sundayofweek));
        session.setAttribute("MondayofWeek", Date.valueOf(mondayofweek));
        session.setAttribute("status", status);
        if (reptype.equalsIgnoreCase("rec")) {
            rd = context.getRequestDispatcher("/ReportRecommendationList.jsp");
        } else {
            rd = context.getRequestDispatcher("/ReportProblemList.jsp");
        }

//        rd = context.getRequestDispatcher("/createCropAssessment.jsp");
        rd.forward(request, response);
        response.setCharacterEncoding("utf-8");

    }

}
