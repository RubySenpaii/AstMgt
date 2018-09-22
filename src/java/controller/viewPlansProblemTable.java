package controller;

import db.ProblemsDB;
import db.fixedRecDB;
import entity.FarmRecTable;
import entity.Problems;
import entity.Recommendation;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class viewPlansProblemTable extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        ProblemsDB probdb = new ProblemsDB();

      
        Problems prob = new Problems();
      
   ArrayList<Problems> bct = probdb.getProblemsList();
        //ArrayList<Problems> bct = probdb.getProblemsWithBrgy();
        
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        if (bct != null) {
            for (int i = 0; i < bct.size(); i++) {
                ArrayList<String> obj = new ArrayList<String>();
                 obj.add(Integer.toString(bct.get(i).getProb_id()));
                obj.add(bct.get(i).getProb_name());
               obj.add(bct.get(i).getProb_details());
                obj.add(bct.get(i).getStatus());
                obj.add(bct.get(i).getType());
                obj.add(Integer.toString(bct.get(i).getTotalFarms()));
                obj.add(Integer.toString(bct.get(i).gettSolutions()));
                list.add(obj);
            }
        }
            data.put("data", list);
          response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
