package controller;

import db.FarmsDB;
import db.fixedRecDB;
import entity.Farm;
import entity.FarmRecTable;
import entity.Recommendation;
import entity.compRecommendation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class viewOtherRecommendations extends BaseServlet {
    

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
         fixedRecDB frb = new fixedRecDB();
            ArrayList<Recommendation> fct = new ArrayList<Recommendation>();
           
             fct = frb.viewRecList();
        JSONObject data = new JSONObject();
        JSONArray dalist = new JSONArray();
        if (fct != null) {
            if (!fct.isEmpty()) {
            for (int i = 0; i < fct.size(); i++) {
                ArrayList<String> obj = new ArrayList<>();
                obj.add(Integer.toString(fct.get(i).getId()));
                obj.add(fct.get(i).getRecommendation_name());
                obj.add(fct.get(i).getType());
                obj.add(fct.get(i).getDescription());
                 obj.add(Integer.toString(fct.get(i).getId()));
                dalist.add(obj);
            }
            }
        }
      data.put("data", dalist);
          response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
