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
public class viewCropAssessment extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();
        CalendarDB caldb = new CalendarDB();
        CropAssessmentDB cadb = new CropAssessmentDB();

        //INITIALIZATION OF VALUES;
        RequestDispatcher rd = null;

// *** FROM THE SELECTED PARAMETER
       

        String theweek = request.getParameter("theweek");
        String toprint = request.getParameter("toprint");
        Boolean print = false;
        if (toprint != null) {
            session.setAttribute("printca", true);
        } else {
            session.setAttribute("printca", null);
        }

        if (theweek == null) {

            ArrayList<entity.Calendar> calist = caldb.getCurrentYearDetails();//gets the phases/today/crop yr
            Date todayDate = calist.get(0).getTodayDate();
            theweek = todayDate.toString();
        }
        Date thisweek = Date.valueOf(theweek);
        entity.Calendar weekcal = caldb.getInitialCurrentYearDetails(thisweek);//gets the phases/today/crop yr
        Calendar cal = caldb.getCalendarTypes(thisweek);
        ArrayList<CropAssessment> caT = null;
        if (caldb.checkifMilling(thisweek)) {
            caT = cadb.getCropAssesmentRajversion(cal.getEweek(), weekcal.getYear(), thisweek.toString());

        }
        Calendar daweek = caldb.getDateWeekDetails(thisweek);
        ArrayList<CropAssessment> rain = cadb.getRainfallByDate(daweek.getMondayofWeek(), daweek.getSundayofWeek());
        ArrayList<statusReport> srlist = cadb.getAllStatusReports(thisweek);

        CropNarrative cn = null;
         CropNarrative bn = null;
        if (cadb.checkExistingNarrative(daweek.getSundayofWeek()) == true) {
              cn=cadb.viewCropNarrative(daweek.getSundayofWeek());
              
        }
        bn=cadb.viewBoardCropNarrative(daweek.getSundayofWeek());
        session.setAttribute("statusRep", srlist);
        session.setAttribute("CropAss", caT);
        session.setAttribute("cayear", weekcal.getYear());
        session.setAttribute("carain", rain);
        session.setAttribute("statusRep", srlist);
        session.setAttribute("MdoNarrative", cn);
        session.setAttribute("boardNarrative", bn);
//            session.setAttribute("Week_ending",calist.get(0).getSundayofWeek());
        session.setAttribute("SundayofWeek", daweek.getSundayofWeek());
        session.setAttribute("MondayofWeek", daweek.getMondayofWeek());

        rd = context.getRequestDispatcher("/createCropAssessment.jsp");

        rd.forward(request, response);
        response.setCharacterEncoding("utf-8");

    }

}
