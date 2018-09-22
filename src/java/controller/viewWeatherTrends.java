/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.WeatherDB;
import entity.Weather;
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
public class viewWeatherTrends extends HttpServlet {

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
            WeatherDB wdb = new WeatherDB();
            Weather w = new Weather();
            ArrayList<Weather> weatherT = new ArrayList<Weather>();
            int year = Integer.parseInt(request.getParameter("id"));
            weatherT = wdb.getWeatherTrends(year);
            JSONObject months = new JSONObject(); 
            JSONObject production = new JSONObject(); 
            JSONObject rainfall = new JSONObject(); 
        JSONArray list = new JSONArray();
        JSONArray prod = new JSONArray();
        JSONArray rain = new JSONArray();
        if (weatherT != null) {
            for (int i = 0; i < weatherT.size(); i++) {
                ArrayList<String> mon = new ArrayList<String>();
                ArrayList<Double> pro = new ArrayList<Double>();
                ArrayList<Double> rai = new ArrayList<Double>();
                mon.add(weatherT.get(i).getMonths());
                pro.add(weatherT.get(i).getProduction());
                rai.add(weatherT.get(i).getRainfall());
                //obj.add(probT.get(i).getValidation());
                list.add(mon);
                prod.add(pro);
                rain.add(rai);
            }
        }
      months.put("months", list);
      production.put("prod", prod);
      rainfall.put("rain", rain);
          response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("[");
        response.getWriter().write(months.toString());
        response.getWriter().write(",");
        response.getWriter().write(production.toString());
        response.getWriter().write(",");
        response.getWriter().write(rainfall.toString());
        response.getWriter().write("]");
            
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
