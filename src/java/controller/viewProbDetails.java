/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ProblemsDB;
import db.ProgramsDB;
import db.fixedRecDB;
import entity.Problems;
import entity.Programs;
import entity.Recommendation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Bryll Joey Delfin
 */
public class viewProbDetails extends HttpServlet {

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
            Problems prob = new Problems();
            int i = Integer.parseInt(request.getParameter("id"));
            fixedRecDB recDB = new fixedRecDB();
            ProblemsDB probDB = new ProblemsDB();
            ProgramsDB progDB = new ProgramsDB();
            Problems p = probDB.getProblemsDetails(i);
            Recommendation r;
            Programs pg;
            ArrayList<Recommendation> recomList = probDB.getAllSolutions(p.getProb_id());
            ArrayList<Programs>progList = progDB.getallPrograms(p.getProb_id());
            ArrayList<Problems> probList = null;
            ArrayList<Recommendation> solution = new ArrayList<Recommendation>();
            ArrayList<Programs> prg = new ArrayList<Programs>();
            if(recomList != null){
            for (int j = 0; j < recomList.size(); j++){
                r = new Recommendation();
                r = recDB.viewRecDetails(recomList.get(j).getId());
                solution.add(r);
            }
            }
            if(progList !=null){
                for (int k = 0; k<progList.size(); k++){
                pg = new Programs();
                    System.out.println(progList.get(k).getProg_name()+"WHET");
                pg = progDB.viewProgDetailForProblem(progList.get(k).getProg_name());
                prg.add(pg);
            }
            }
            if(p != null){
                if(recomList != null){
                p.settSolutions(recomList.size());
                }
                else{
                    p.settSolutions(0);
                }
                if(progList !=null){
                    p.settPrograms(progList.size());
                }
                else{
                    p.settPrograms(0);
                }
                
                probList = new ArrayList<Problems>();
                probList = probDB.showProblembyFarm(i);
                HttpSession session = request.getSession();
                session.setAttribute("problem", p);
                session.setAttribute("probid", i);
                session.setAttribute("solution", solution);
                session.setAttribute("programs", prg);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/viewProblemDetails.jsp");
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
