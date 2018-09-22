/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CropEstimateDB;
import db.FarmsDB;
import db.ProgramsDB;
import entity.Farm;
import entity.programsKPI;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Bryll Joey Delfin
 */
public class loadCropEstYearList extends BaseServlet {

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
        CropEstimateDB cedb = new CropEstimateDB();


          int tag = Integer.parseInt(request.getParameter("tag"));
      System.out.println(tag);
        ArrayList<Integer> data = cedb.getDistinctYearsDESC(tag);
          JSONArray jarray = new JSONArray();
        for (int i = 0; i < data.size(); i++) {
            jarray.add(data.get(i));
         
                
        }
      
    
        //addprogKPI
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jarray.toString());

    }

}
