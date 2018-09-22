/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import db.ProblemsDB;
import entity.Problems;
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
public class viewProbList extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        JSONObject data= new JSONObject();
        ProblemsDB pdb = new ProblemsDB();
        ArrayList<Problems> probT = new ArrayList<Problems>();
        probT = pdb.getProblemsList();
        System.err.println(probT.get(0).getProb_name()+"NATRAJ ");
        JSONArray list = new JSONArray();
        for(int i=0;i<probT.size();i++){
            ArrayList<String> obj = new ArrayList<String>();
            obj.add(probT.get(i).getProb_id().toString());
            obj.add(probT.get(i).getProb_name());         
            obj.add(probT.get(i).getProb_details());
            obj.add(probT.get(i).getTotalFarms().toString());
            obj.add(probT.get(i).getStatus());
            obj.add(probT.get(i).getType());
            obj.add(probT.get(i).getProb_id().toString());
            list.add(obj);
        }
        data.put("data", list);
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
        
        }
        

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



