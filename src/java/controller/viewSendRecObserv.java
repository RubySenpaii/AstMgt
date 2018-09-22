/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.FarmsDB;
import db.ProblemsDB;
import db.fixedRecDB;
import entity.Farm;
import entity.Problems;
import entity.Recommendation;
import entity.compRecommendation;
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
public class viewSendRecObserv extends HttpServlet {

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
        Enumeration<String> parameterNames = request.getParameterNames();
        String paramName;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> allist = new ArrayList<String>();
        ArrayList<String> recslist = new ArrayList<String>();
        ArrayList<String> probslist = new ArrayList<String>();

        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            System.out.println(paramName);
            if (paramName.startsWith("farmid")) {
                for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    list.add(request.getParameterValues(paramName)[i]);
                    System.out.println(request.getParameterValues(paramName)[i] + " LOOOOOOOL");
                }

            } else if (paramName.startsWith("allid")) {
                for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    allist.add(request.getParameterValues(paramName)[i]);
                    System.out.println(request.getParameterValues(paramName)[i] + " NIGGAGANGIAIGGNGIG");
                }

            } else if (paramName.startsWith("recsid")) {
                for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    recslist.add(request.getParameterValues(paramName)[i]);
                    System.out.println(request.getParameterValues(paramName)[i] + "ALLMYRECS now get recs");
                }

            } else if (paramName.startsWith("probsid")) {
                for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                    probslist.add(request.getParameterValues(paramName)[i]);
                    System.out.println(request.getParameterValues(paramName)[i] + "ALLMYPROBS probs are everywhere");
                }
            }
        }
        String values = request.getParameter("atools");

        HttpSession session = request.getSession();
        ServletContext context = getServletContext();
        RequestDispatcher rd = null;
        if (values.equals("crec")) {
            rd = context.getRequestDispatcher("/createNewRecommendation.jsp");
        } else if (values.equals("srec")) {
            rd = context.getRequestDispatcher("/sendRelatedRec.jsp");
            session.setAttribute("allid", allist);
        } else if (values.equals("sorec")) {
            rd = context.getRequestDispatcher("/sendRecommendations.jsp");
        } else if (values.equals("vrec")) {
            rd = context.getRequestDispatcher("/validateRecommendations.jsp");
            fixedRecDB fixdb = new fixedRecDB();
            ArrayList<Recommendation> recs = null;
            //convert recslist to recommendations
            recs = fixdb.viewAllRecDetails(recslist);
            session.setAttribute("darecs", recs);
        } else {
            rd = context.getRequestDispatcher("/validateProblems.jsp");
            //convert recslist to problems
            ProblemsDB probdb = new ProblemsDB();
            ArrayList<Problems> probs = null;
            probs = probdb.viewSelectedProblems(probslist);
            session.setAttribute("daprobs", probs);
        }

        session.setAttribute("flist", list);

        rd.forward(request, response);

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
