package controller;

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

public class viewRecDetails extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        fixedRecDB recdb = new fixedRecDB();

        HttpSession session = request.getSession();
        Recommendation rec = new Recommendation();
         ArrayList<Problems> plist = new ArrayList<Problems>();
        int id= Integer.parseInt(request.getParameter("id"));
        rec = recdb.viewRecDetails(id);
        plist = recdb.viewProbRecTable(id);
      //  ArrayList<FarmRecTable> fct = recdb.viewFarmRecTable(id);
        
//        JSONObject data = new JSONObject();
//        JSONArray list = new JSONArray();
//        if (fct != null) {
//            for (int i = 0; i < fct.size(); i++) {
//                ArrayList<String> obj = new ArrayList<String>();
//                obj.add(fct.get(i).getName());
//                obj.add(fct.get(i).getBrgy());
//                obj.add(fct.get(i).getMunicipality());
//                obj.add(fct.get(i).getApproved());
//                list.add(obj);
//            }
//        }
        session.setAttribute("recdet", rec);
         session.setAttribute("problist", plist);
        //session.setAttribute("farmrecT", list);
        session.setAttribute("id", id);
      //   data.put("data", list);
        RequestDispatcher rd = context.getRequestDispatcher("/viewRecommendationDetails.jsp");

        rd.forward(request, response);
        //  response.setContentType("applications/json");
       // response.setCharacterEncoding("utf-8");
      //  response.getWriter().write(data.toString());
    }

}
