package controller;

import db.ProblemsDB;
import db.ProgramsDB;
import db.fixedRecDB;
import entity.FarmRecTable;
import entity.Problems;
import entity.Programs;
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

public class viewProgramsTable extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();
        ProgramsDB progDB = new ProgramsDB();

        Programs prog = new Programs();

        ArrayList<Programs> bct = progDB.viewProgramsTable();

        JSONObject data = new JSONObject();
        JSONArray list = new JSONArray();
        if (bct != null) {
            for (int i = 0; i < bct.size(); i++) {
                ArrayList<String> obj = new ArrayList<String>();
                System.out.println(bct.get(i).getProg_name());
                obj.add(bct.get(i).getProg_name());
                obj.add(bct.get(i).getType());
                obj.add(bct.get(i).getDate_created().toString() + "-" + bct.get(i).getDate_end().toString());
                obj.add(Integer.toString(bct.get(i).gettFarms()));
                obj.add(bct.get(i).getStatus());
                obj.add(Double.toString(bct.get(i).getProgress()));
                obj.add(bct.get(i).getProg_name());

                list.add(obj);
            }
        }
        data.put("data", list);
        response.setContentType("applications/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data.toString());
    }

}
