/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropAssessmentDB;
import entity.Calendar;
import entity.CropAssessment;
import entity.CropNarrative;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
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
public class MDOHomepage extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/Homepage.jsp");
        HttpSession session = request.getSession();
                //start of the crop assessment report
        //START OF MDO
        CalendarDB caldb = new CalendarDB();
        CropAssessmentDB cadb = new CropAssessmentDB();

        ArrayList<Calendar> calist = caldb.getCurrentYearDetails();//gets the phases/today/crop yr
        Integer cropyear = calist.get(0).getYear();
        Date todayDate = calist.get(0).getTodayDate();
        Calendar cal = caldb.getCalendarTypes(todayDate);//weekofyear//month//day

        //start of the crop assessment report
        ArrayList<CropAssessment> caT = null;
        Date week_ending = null;
        boolean milling = false;
        if (caldb.checkifMilling()) {//checks if today is milling period
            caT = new ArrayList<CropAssessment>();
            System.out.println(cal.getEweek() + "EWEEK");

            caT = cadb.getCropAssesmentRajversion(cal.getEweek(), cropyear, calist.get(0).getTodayDate().toString());
            week_ending = caT.get(0).getWeek_ending();
        }
        CropNarrative cn = null;
         CropNarrative bn = null;
        ArrayList<CropAssessment> rain = cadb.getRainFall(cal.getEweek(), cropyear);

        if (cadb.checkExistingNarrative(cropyear, calist.get(0).getSundayofWeek()) == true) {
//                        System.out.println("it entered tester");
            cn = new CropNarrative();
            cn = cadb.getAssessmentNarrative(cropyear, calist.get(0).getSundayofWeek());
            bn=cadb.viewBoardCropNarrative(calist.get(0).getSundayofWeek());
        }
        
        session.setAttribute("rainfall", rain);
        session.setAttribute("narrative", cn);
         session.setAttribute("hboardnarrative", bn);
        session.setAttribute("CropAss", caT);
//END OF MDO

        rd.forward(request, response);

    }

}
