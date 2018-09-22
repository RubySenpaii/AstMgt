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
public class updateKPIProgram extends BaseServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

       
        ProgramsDB progdb = new ProgramsDB();

        Enumeration<String> parameterNames = request.getParameterNames();

       String prog_name=request.getParameter("prog_name");

        String paramName;
        ArrayList<programsKPI> kpis = new ArrayList();

        programsKPI pkpi = null;
        int sYear = Integer.parseInt(request.getParameter("kpi_year"));
   
        int tYears = Integer.parseInt(request.getParameter("tYears"));

        int count = 0;
   

        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            System.out.println(paramName);

            if (paramName.startsWith("y" + count)) {
                ArrayList<Double> value = new ArrayList<>();
                for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    System.out.println(request.getParameterValues(paramName)[i]);
                    value.add(Double.parseDouble(request.getParameterValues(paramName)[i]));
                }
                pkpi.setaValues(value);
                kpis.add(pkpi);
            } else if (paramName.startsWith("kNa")) {
                System.out.println(paramName.substring(3, 4) + " :counter");
                
                count = Integer.parseInt(paramName.substring(3, 4));
                pkpi = new programsKPI();
                pkpi.setProgram_name(prog_name);
                pkpi.setKpi(request.getParameterValues(paramName)[0]);
                pkpi.setKpi_year(sYear);
                pkpi.settYears(tYears);
                System.out.println(request.getParameterValues(paramName)[0]);
            }

        }

        for (programsKPI kpi : kpis) {
            System.out.print("/" + kpi.getKpi() + "/");
            for (int a = 0; a < kpi.getaValues().size(); a++) {
                System.out.println("/" + kpi.getaValues().get(a) + "/");
            }
        }

//        boolean test = progdb.updateKPIs(kpis);
        boolean test = progdb.addMoreKPIs(kpis);
   

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
