/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ForumDB;
import db.ProblemsDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @author Bryll Joey Delfin
 */
public class determineFarmProblems extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ForumDB fdb = new ForumDB();
            ProblemsDB pdb = new ProblemsDB();
            ArrayList<String> flist = new ArrayList<String>();
            String test = request.getParameter("flist");
            String message  = request.getParameter("msg");
            String remove1 = test.replace("[", "");
            String remove2 = remove1.replace("]", "");
            HttpSession session = request.getSession();
            Date date = (Date) session.getAttribute("todayDate");
            int check = 0;
            int pass = 0;
            flist = new ArrayList<String>(Arrays.asList(remove2.split(",")));
            flist = new ArrayList<String>(Arrays.asList(remove2.split("\\s*,\\s*")));
//            for(int i=0;i<flist.size();i++){                              
//            System.out.println(flist.get(i)+"IS THIS WHAT YOUR LOOKING FOR");
//            int j = Integer.parseInt(flist.get(i));
//                System.out.println(j+ " WILL IT WORK ?");
//            }

            Enumeration<String> parameterNames = request.getParameterNames();
            String paramName;
            ArrayList<String> pT = new ArrayList<String>();
            while (parameterNames.hasMoreElements()) {
                paramName = parameterNames.nextElement();
                System.out.println(paramName);
                if (paramName.startsWith("probid")) {
                    for(int j = 0; j<flist.size();j++){
                    for (int i = 0; i < request.getParameterValues(paramName).length; i++) {
                        pT.add(request.getParameterValues(paramName)[i]);
                        System.out.println(request.getParameterValues(paramName)[i]);
                        //connects problem torecommendation table
                        int recom_id = Integer.parseInt(request.getParameterValues(paramName)[i]);
                        System.out.println(recom_id+ " LOLOLOLOLOLOL");
                        check = pdb.checkProblemFieldsId(recom_id, Integer.parseInt(flist.get(j)));
                        if(check ==0){
                            pass = pdb.linktoProblems(Integer.parseInt(flist.get(j)), recom_id, date, "Verifying");
                            fdb.addRecommendationProblem(Integer.parseInt(flist.get(j)), message, date, pass);
                        }
                        else{
                            pass = pdb.updateProblemsFields(Integer.parseInt(flist.get(j)), recom_id, test);
                            fdb.addRecommendationProblem(Integer.parseInt(flist.get(j)), message, date, check);
                        }
                    }
                    }
                }
            }
            if(pass >0){
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/determineProblem.jsp");
                rd.forward(request, response);
            }
            else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }    
            
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
