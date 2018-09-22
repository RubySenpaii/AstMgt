/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import db.CropEstimateDB;
import entity.Recommendation;
import entity.cropEstimate;
import java.io.IOException;
import java.io.PrintWriter;

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
public class generateForecast extends HttpServlet {

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
       
            CropEstimateDB cedb = new CropEstimateDB();
            cropEstimate ce= new cropEstimate();
            Double area=null,rain=null,tiller=null,temp=null,production=null;
            String area1=null,rain1=null,tiller1=null,temp1=null,production1=null;
       
        area1=request.getParameter("area");
        production1=request.getParameter("production");
        rain1=request.getParameter("rain");
        temp1=request.getParameter("temp");
        tiller1= request.getParameter("tiller");
        if(!area1.isEmpty()){
             area= Double.parseDouble(area1);
            }
         if(!production1.isEmpty()){
             production= Double.parseDouble(production1);
             System.out.println(production+"PRODUCTION");
            }
        if(!rain1.isEmpty()){
             rain= Double.parseDouble(rain1);
            }
        if(!temp1.isEmpty()){
             temp= Double.parseDouble(temp1);
            }
        if(!tiller1.isEmpty()){
             tiller= Double.parseDouble(tiller1);
            }
        
        
        boolean check =  cedb.selectEstimates(area,production, rain, tiller, temp);
            if (check){
                
                ServletContext context = getServletContext();
                 response.sendRedirect("viewCropEstimate");
            }
            else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/Recommendation.jsp");
                rd.forward(request, response);
            }     
        }finally {
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
