/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ForumDB;
import db.ProblemsDB;
import db.fixedRecDB;
import entity.Forum;
import entity.Problems;
import entity.Recommendation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Bryll Joey Delfin
 */
public class viewPostList extends HttpServlet {

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
            ProblemsDB pDB = new ProblemsDB();
            fixedRecDB recDB = new fixedRecDB();
            ArrayList<Forum> fT = new ArrayList<Forum>();
            fT = fdb.getForumsList();
            JSONObject data = new JSONObject();
            JSONArray list = new JSONArray();
            if (fT != null) {
                for (int i = 0; i < fT.size(); i++) {
                    ArrayList<String> obj = new ArrayList<String>();
                    obj.add(fT.get(i).getTitle());
                    if (fT.get(i).getStatus().equalsIgnoreCase("Accepted")){
                        obj.add(fT.get(i).getTitle());
                        if (fT.get(i).getProb_id() != 0) {
                            Integer counter = fdb.getProblemCounter(fT.get(i).getProb_id());
                            Problems prob = pDB.getProblemsDetails(fT.get(i).getProb_id());
                            System.out.println(fT.get(i).getProb_id() + "LoL");
                            obj.add("N/A");
                            obj.add("0");
                            obj.add(prob.getProb_name());
                            obj.add(counter.toString());
                        } else if (fT.get(i).getRecom_id() != 0) {
                            Integer counter = fdb.getRecommendationCounter(fT.get(i).getRecom_id());
                            Recommendation recom = recDB.viewRecDetails(fT.get(i).getRecom_id());
                            System.out.println(fT.get(i).getRecom_id() + "XD");
                            obj.add(recom.getRecommendation_name());
                            obj.add(counter.toString());
                            obj.add("N/A");
                            obj.add("0");
                        }
                        else{
                            obj.add("N/A");
                        obj.add("0");
                        obj.add("N/A");
                        obj.add("0");
                        }
                        
                        obj.add(fT.get(i).getPhase());
                        list.add(obj);
                        }
                }
            }
            data.put("data", list);
            response.setContentType("applications/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(data.toString());
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
