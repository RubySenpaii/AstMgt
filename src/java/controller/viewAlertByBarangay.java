/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CalendarDB;
import db.ProblemsDB;
import entity.Calendar;
import entity.Problems;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
public class viewAlertByBarangay extends HttpServlet {

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
            JSONObject data= new JSONObject();
        ProblemsDB pdb = new ProblemsDB();
        ArrayList<Problems> probT = new ArrayList<Problems>();
        HttpSession session = request.getSession();
        
        String num =  session.getAttribute("probid").toString();
        int probid = Integer.parseInt(num);
        String municipality = (String) session.getAttribute("municipality");
        String date = (String) session.getAttribute("date");
        probT = pdb.getDisastersListByBarangay(probid,municipality,date);
        JSONArray list = new JSONArray();
        for(int i=0;i<probT.size();i++){
            ArrayList<String> obj = new ArrayList<String>();
            obj.add(probT.get(i).getDate_updated().toString());
            obj.add(probT.get(i).getBarangay());
            obj.add(probT.get(i).getTotalFarms().toString());   
            obj.add(probT.get(i).getTotalFields().toString());
            DecimalFormat df = new DecimalFormat("#.##");
            double perc = Double.valueOf(df.format(probT.get(i).getPercent_affected()));
            obj.add(Double.toString(perc));
            String id = probT.get(i).getProb_id().toString()+","+probT.get(i).getBarangay();
            obj.add(id);
            list.add(obj);
        }
        session.setAttribute("municipality", municipality);
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
