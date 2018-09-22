/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.ProblemsDB;
import entity.Problems;
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

/**
 *
 * @author Bryll Joey Delfin
 */
public class sendAlert extends HttpServlet {

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
            
            Problems p = new Problems();
            Problems p1 = new Problems();
            ProblemsDB pdb = new ProblemsDB();
            String line = request.getParameter("id");
            String[] para = line.split(",");
            int i = Integer.parseInt(para[0]);
            String barangay = para[1];
            p = pdb.getAlertDetails(i);
            HttpSession session = request.getSession();
            String muni = (String) session.getAttribute("municipality");
            System.out.println(muni+ " THIS IS THE MUNI :");
            ArrayList<Problems> pList = new ArrayList<Problems>();
            pList = pdb.getSpecificDisastersListByBarangay(i, muni, barangay);
            if(pList != null){
                int totalf = pList.get(0).getTotalFields() - pList.get(0).getTotalFarms();
                p.setTotalFarms(totalf);
            }
                session.setAttribute("problem", p);
                session.setAttribute("muni", muni);
                session.setAttribute("barangay", barangay);
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/sendAlert.jsp");
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
