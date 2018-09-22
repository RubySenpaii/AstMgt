package controller;

import db.fixedRecDB;
import entity.FarmRecTable;
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

public class viewFRTable extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        fixedRecDB recdb = new fixedRecDB();

        HttpSession session = request.getSession();
        Recommendation rec = new Recommendation();
      

        ArrayList<FarmRecTable> fct = recdb.viewFarmRecTable(Integer.parseInt(request.getParameter("id")));
        
        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        if (fct != null) {
            for (int i = 0; i < fct.size(); i++) {
                ArrayList<String> obj = new ArrayList<>();
                obj.add(Integer.toString(fct.get(i).getId()));
                obj.add(fct.get(i).getName());
                obj.add(fct.get(i).getBrgy());
                obj.add(fct.get(i).getMunicipality());
                 obj.add(fct.get(i).getDate_updated().toString());
                obj.add(fct.get(i).getStatus());
                obj.add(Integer.toString(fct.get(i).getDuration_days()));
                obj.add(Integer.toString(fct.get(i).getId()));
                list.add(obj);
               
            }
        }
      data.put("data", list);
          response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
