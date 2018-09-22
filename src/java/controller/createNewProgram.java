/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ProgramsDB;
import entity.Programs;
import entity.programsKPI;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class createNewProgram extends BaseServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        Programs newProg = new Programs();
        ProgramsDB progdb = new ProgramsDB();

        Enumeration<String> parameterNames = request.getParameterNames();

        newProg.setProg_name(request.getParameter("projectname"));

        try {
            String reserv = request.getParameter("reservation");
            String[] lines = reserv.split("-");
            String inidate = lines[0];
            java.util.Date inicheck = new SimpleDateFormat("MM/dd/yyyy").parse(inidate);
             Date modifiediniDate = new java.sql.Date(inicheck.getTime());
            newProg.setDate_initial(modifiediniDate);
            String enddate = lines[1];
            java.util.Date endcheck = new SimpleDateFormat("MM/dd/yyyy").parse(enddate);
            Date modifiedendDate = new java.sql.Date(endcheck.getTime());
            newProg.setDate_end(modifiedendDate);
            newProg.setDate_created(modifiediniDate);
        } catch (ParseException ex) {
            Logger.getLogger(createNewProgram.class.getName()).log(Level.SEVERE, null, ex);
        }

        newProg.setDescription(request.getParameter("Description"));
        newProg.setType(request.getParameter("projecttype"));

        String paramName;
        ArrayList<programsKPI> kpis = new ArrayList();

        programsKPI pkpi = null;
//        int sYear = 2014;
//        int tYears = 3;
        int sYear = Integer.parseInt(request.getParameter("kpi_year").trim());
      
        int tYears = Integer.parseInt(request.getParameter("tYears").trim());
        System.out.println(sYear+"tyrs"+tYears);

        int count = 0;
        ArrayList<String> problist = new ArrayList();

        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            System.out.println(paramName);

            if (paramName.substring(0, 2).equals("y" + count)) {
                ArrayList<Double> value = new ArrayList<>();
                for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    System.out.println(request.getParameterValues(paramName)[i]);
                    value.add(Double.parseDouble(request.getParameterValues(paramName)[i]));
                }
                pkpi.setValues(value);
                kpis.add(pkpi);
            } else if (paramName.substring(0, 4).equals("kpis")) {
                System.out.println(paramName.substring(4, 5) + " :counter");
                count = Integer.parseInt(paramName.substring(4, 5));
                pkpi = new programsKPI();
                pkpi.setKpi(request.getParameterValues(paramName)[0]);
                pkpi.setKpi_year(sYear);
                pkpi.settYears(tYears);
                System.out.println(request.getParameterValues(paramName)[0]);
            } else if (paramName.startsWith("probid")) {
        
                 for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    System.out.println(request.getParameterValues(paramName)[i]+"problem id");
                    problist.add(request.getParameterValues(paramName)[i]);
                    
                }
              
            }

        }

        for (programsKPI kpi : kpis) {
            System.out.print("/" + kpi.getKpi() + "/");
            for (int a = 0; a < kpi.getValues().size(); a++) {
                System.out.println("/" + kpi.getValues().get(a) + "/");
            }
        }

        boolean test = progdb.createNewProgram(newProg, kpis);
        if(problist.isEmpty()==false){
                boolean probSuccess = progdb.addProgProb(problist, newProg.getProg_name());   
        }

        //addprogKPI

        if (test) {
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/viewPrograms.jsp");
            rd.forward(request, response);
        } else {
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher("/Homepage.jsp");
            rd.forward(request, response);
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
