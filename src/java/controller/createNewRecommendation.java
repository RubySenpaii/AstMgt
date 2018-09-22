/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.CropAssessmentDB;
import db.ForumDB;
import db.subjectiveRecDB;
import entity.Calendar;
import entity.Problems;
import entity.Recommendation;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
import java.text.DateFormat;
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
public class createNewRecommendation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            Recommendation r = new Recommendation();
            subjectiveRecDB recDB = new subjectiveRecDB();
            ForumDB fdb = new ForumDB();
            int notif = 0;
            int duration = 0;
            int pass = 0;
            String f = request.getParameter("fields");
            int fields_id = 0;
            if (f != null) {
                fields_id = Integer.parseInt(request.getParameter("fields"));
            } else {
                fields_id = 0;
            }
            System.out.println(fields_id+"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
            String title = request.getParameter("title");
            String dur = request.getParameter("config");
            System.out.println(dur+"DUR TAKEN");
            if (dur == null || dur.equalsIgnoreCase("")) {
                duration = 0;
            } else {
                duration = Integer.parseInt(dur);
            }
            HttpSession session = request.getSession();
            CalendarDB caldb = new CalendarDB();
            ArrayList<Calendar> calist = caldb.getCurrentYearDetails();//gets the phases/today/crop yr
            Integer cropyear = calist.get(0).getYear();
            Date dateparam = calist.get(0).getTodayDate();
            System.out.println(dateparam + "DATEPARAM"+duration+"duration");
            String name = request.getParameter("rec_id");
            if(name == null){
            name  = request.getParameter("recommendation_name");
            }
            r.setRecommendation_name(name);
            r.setPhase(request.getParameter("period"));
            r.setType(request.getParameter("type"));
//            String dates = request.getParameter("datepicker");
//            String datee = request.getParameter("dateend");
            r.setDescription(request.getParameter("description"));
            //  r.setConfig(Integer.parseInt(request.getParameter("config")));
            int check2 = 0;
            int check3 = 0;
            Enumeration<String> parameterNames = request.getParameterNames();
            String paramName;
            ArrayList<String> pT = new ArrayList<String>();
            if (fields_id != 0) {
                while (parameterNames.hasMoreElements()) {
                    paramName = parameterNames.nextElement();
                    System.out.println(paramName + "parameter");
                    if (paramName.startsWith("probTables")) {
                        for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                            pT.add(request.getParameterValues(paramName)[i]);
                            System.out.println(request.getParameterValues(paramName)[i]);
                            //connects recommendation to problem table
                            int probid = Integer.parseInt(request.getParameterValues(paramName)[i]);
                            System.out.println(probid+"when i am out of options left");
                            if (duration > 0) {
                                r.setImprovement("Y");
                                int check = recDB.addRecommendation(r);
                                pass = check;
                                check3 = recDB.linkToRecoms(fields_id, check, dateparam, "Active", duration);
                            } else {
                                r.setImprovement("N");
                                int check = recDB.addRecommendation(r);
                                pass = check;
                                check2 = recDB.connectRecommendationtoProblem(check, probid);
                                check3 = recDB.linkToRecoms(fields_id, check, dateparam, "Active", duration);
                            }
                        }
                    }
                }
            } else{ 
                if (duration > 0) {
                    r.setImprovement("Y");
                  pass =  recDB.addRecommendation(r);
            } else {
                while (parameterNames.hasMoreElements()) {
                    paramName = parameterNames.nextElement();
                    System.out.println(paramName + "parameter");
                    if (paramName.startsWith("probid[]")) {
                        for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                            pT.add(request.getParameterValues(paramName)[i]);
                            System.out.println(request.getParameterValues(paramName)[i]);
                            //connects recommendation to problem table
                            int probid = Integer.parseInt(request.getParameterValues(paramName)[i]);
                            System.out.println(probid+"PROBID");
                            r.setImprovement("N");
                            int check = recDB.addRecommendation(r);
                            pass = check;
                            System.out.println(check+"checker");
                            check2 = recDB.connectRecommendationtoProblem(check, probid);
                        }
                    }
                }
            }
            }
            if (pass > 0 || check3 > 0) {
                if (fields_id != 0) {
                    fdb.updatePostRecommendations(title, fields_id, pass);
                    notif = fdb.addRecommendationNotification(fields_id, "The MDO have suggested for you to do this recommendation", dateparam, pass, fields_id);
                    ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/Forum.jsp");
                rd.forward(request, response);
                }
                else{
                fdb.addnewpost(r.getRecommendation_name(), r.getDescription(), dateparam, dateparam, "Accepted", calist.get(0).getPhase(), pass);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/createNewRecommendation.jsp");
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
