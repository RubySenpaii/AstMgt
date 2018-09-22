/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.FarmsDB;
import db.fixedRecDB;
import entity.Farm;
import entity.Problems;
import entity.Recommendation;
import entity.compProblems;
import entity.compRecommendation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
public class viewSelectedProblems extends HttpServlet {

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
         ServletContext context = getServletContext();
        fixedRecDB recdb = new fixedRecDB();

        HttpSession session = request.getSession();
        Recommendation rec = new Recommendation();
        
   Enumeration<String> parameterNames = request.getParameterNames();
            String paramName;
            ArrayList<String> list = new ArrayList<String>();

            String[] values = request.getParameterValues("farmid");
           values[0]=values[0].replace("[","");
           values[0]=values[0].replace("]","");
            String[] innerArray=values[0].split(",");

             for(int i=0; i<values.length;i++){
                   System.out.println(values[i]+"dist checkr");
            }
            
             
              for(int i=0; i<innerArray.length;i++){
                   System.out.println(innerArray[i]+"dick checkr");
                   list.add(innerArray[i].trim());
            }
        
            FarmsDB farmdb= new FarmsDB();
            ArrayList<Farm> flist=farmdb.getAllFieldComp(list);
            Farm emptyfarm= new Farm();
            ArrayList<compProblems>fct= farmdb.getSimilarProblems(emptyfarm,flist);
        JSONObject data = new JSONObject();
        JSONArray dalist = new JSONArray();
        if (fct != null) {
            for (int i = 0; i < fct.size(); i++) {
                ArrayList<String> obj = new ArrayList<>();
                obj.add(Integer.toString(fct.get(i).getProb_id()));
                obj.add(fct.get(i).getProb_name());
                obj.add(fct.get(i).getType());
                obj.add(fct.get(i).getProb_details());
                obj.add(Integer.toString(fct.get(i).getProb_id())+"lol");
                dalist.add(obj);
               
            }
        }
      data.put("data", dalist);
          response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
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
