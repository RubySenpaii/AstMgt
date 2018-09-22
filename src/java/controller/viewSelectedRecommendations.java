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

public class viewSelectedRecommendations extends BaseServlet {
    

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            ArrayList<compRecommendation>fct= farmdb.getSimilarRecommendations(emptyfarm,flist);
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
