/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.CropEstimateDB;
import db.fixedRecDB;
import entity.FarmRecTable;
import entity.Recommendation;
import entity.cropEstimate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author ndrs
 */
public class changeSelectedForecastforYear extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        CropEstimateDB estdb = new CropEstimateDB();

       
        HttpSession session = request.getSession();
        String fullname=request.getParameter("name");
         String[] list = fullname.split(",");
           System.out.println("it entered"+Integer.parseInt(list[0]));
           System.out.println("it entered"+Integer.parseInt(list[1]));
cropEstimate ce= new cropEstimate();
if(!list[0].isEmpty() && !list[1].isEmpty() ){
    System.out.println("it entered");
    ce.setYear(Integer.parseInt(list[0]));
    ce.setSelection(Integer.parseInt(list[1]));
      estdb.updateForecastSelection(ce);
}

       
    }

}
