/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class searchSimilarFarms extends BaseServlet {

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
    
      String id = request.getParameter("id");
        String tag = request.getParameter("tag");
             System.out.println(tag+": this tag m8");
             String[] tags = tag.split(",");
//           for(int i=0; i<tags.length; i++){
//             System.out.println(tags[i]);
//    }
           FarmsDB farmdb=new FarmsDB();
         ArrayList<String> idlist= farmdb.searchFarmsbyTags(tags,Integer.parseInt(id));  
        ArrayList<Farm> farmlist = farmdb.getSearchTableResult(idlist,Integer.parseInt(id));

        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        if (idlist != null) {
            for (int i = 0; i < idlist.size(); i++) {
                ArrayList<String> obj = new ArrayList<>();
               // System.out.println("nope not empty");
                obj.add(Integer.toString(farmlist.get(i).getId()));
                obj.add(Integer.toString(farmlist.get(i).getId()));
                obj.add(farmlist.get(i).getFarmer());
                obj.add(farmlist.get(i).getBarangay());
                obj.add(farmlist.get(i).getMunicipality());
                obj.add(Double.toString(farmlist.get(i).getYield()));
                obj.add(Double.toString(farmlist.get(i).getDifYield()));
                obj.add(Integer.toString(farmlist.get(i).getId()));
                list.add(obj);
            }
        }
        data.put("data", list);

        //addprogKPI
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());

    }

}
