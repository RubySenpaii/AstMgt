package controller;

import db.CalendarDB;
import db.CropAssessmentDB;
import db.CropEstimateDB;
import db.UsersDB;
import entity.Calendar;
import entity.CropAssessment;
import entity.CropNarrative;
import entity.User;
import entity.cropEstimate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 // THIS IS THE CODE TO GET TODAYS' DIFFERENT DETAILS (JUST COPY PASTE IT 
                //TO THE METHODS AND AVOID USING THIS IN SERVLETS AS TO LESSEN ERRORS
                //AND MAKE THINGS MORE READABLE)               
                //CalendarDB caldb= new CalendarDB();
                //ArrayList<Calendar> calist= caldb.getCurrentYearDetails();
                //int cropyr=calist.get(0).getYear();


@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            User oneUser = new User();
            oneUser.setUsername(request.getParameter("username"));
            oneUser.setPassword(request.getParameter("password"));
            UsersDB myUserDB = new UsersDB();
            CropAssessmentDB cadb = new CropAssessmentDB();
            CropEstimateDB cedb=new CropEstimateDB();
            User successful = myUserDB.authenticate(oneUser);
            if (successful != null) {
                 ServletContext context = getServletContext();
                HttpSession session = request.getSession();
                
              
                String sdate = request.getParameter("currentdate");

                
                java.util.Date inicheck = new SimpleDateFormat("MM/dd/yyyy").parse(sdate);
                Date todayDate = new java.sql.Date(inicheck.getTime());
                CalendarDB caldb = new CalendarDB();

                if(caldb.checkifvalidDate(todayDate)){
                    
                  caldb.processNewTodayDate(todayDate);//this is to set todaydate to db
                Calendar cal = caldb.getCalendarTypes(todayDate);//weekofyear//month//day
                ArrayList<Calendar> calist = caldb.getCurrentYearDetails();//gets the phases/today/crop yr
                Integer cropyear = calist.get(0).getYear();
              
                
                //start of the crop assessment report
                ArrayList<CropAssessment> caT=null;
                 Date week_ending =null;
                 if(cropyear>2016){
                     if(!cedb.checkExistingCropEstYear(cropyear)){
                  
                         cedb.generateYearlyEstimate(1,1); 
                         cedb.updateYearlyEstimate();
                     }else{
                         cropEstimate ces= cedb.viewDistEstbyYear(cropyear);
                       
                            cedb.deleteSelectedDistrictYear(cropyear);
                            cedb.generateYearlyEstimate(ces.getSelection(),ces.getSelectionlkg());
                            cedb.updateYearlyEstimate();
                     }
                     
                 }
                
                 boolean milling = false;
                if(caldb.checkifMilling()){//checks if today is milling period
                    caT  = new ArrayList<CropAssessment>();
                    System.out.println(cal.getEweek()+"EWEEK");
                    System.out.println(calist.get(0).getTodayDate()+"TODAYDATE");
                caT = cadb.getCropAssesmentRajversion(cal.getEweek(), cropyear, calist.get(0).getTodayDate().toString());
                week_ending=caT.get(0).getWeek_ending();
                System.out.println(week_ending+"*****8");
                }else {
                    
                }
               

                RequestDispatcher rd = null;
                if (successful.getGroup().equalsIgnoreCase("MDO")) {
//START OF MDO
                    rd = context.getRequestDispatcher("/Homepage.jsp");
                    CropNarrative cn = null;
                   CropNarrative bn = null;
                    ArrayList<CropAssessment> rain = cadb.getRainFall(cal.getEweek(), cropyear);

                    if (cadb.checkExistingNarrative(cropyear, calist.get(0).getSundayofWeek()) == true) {
//                        System.out.println("it entered tester");
                        cn = new CropNarrative();
                        cn = cadb.getAssessmentNarrative(cropyear, calist.get(0).getSundayofWeek());
                bn = cadb.viewBoardCropNarrative(calist.get(0).getSundayofWeek());
                       
                    }
                
                    session.setAttribute("rainfall", rain);
                    session.setAttribute("narrative", cn);
                    session.setAttribute("hboardnarrative", bn);
                    session.setAttribute("CropAss", caT);
//END OF MDO
                } else if (successful.getGroup().equalsIgnoreCase("Board")) {
                    if(caldb.checkifMilling()){
                        milling = true;
                        session.setAttribute("todayYear", cropyear);
                        rd = context.getRequestDispatcher("/Homepage_Board.jsp");
                    }
                    else{
                        rd = context.getRequestDispatcher("/viewCropEstimate.jsp");
                    }
                    session.setAttribute("milling", milling);
//END OF BOARD
                }
                session.setAttribute("Week_ending", week_ending);
                session.setAttribute("todayDate", todayDate);
                session.setAttribute("todaysDate", todayDate);
                session.setAttribute("todayYear", cropyear);
                session.setAttribute("todayPhases", calist);
                session.setAttribute("todayMonth", cal.getEmonth());
                session.setAttribute("todayDay", cal.getEday());
                session.setAttribute("weekOfYear", cal.getEweek());
                session.setAttribute("user", successful);
                rd.forward(request, response);
            }else{
                   
                RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                }
            } else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
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
