package controller;

import db.ProgramsDB;
import db.fixedRecDB;
import entity.FarmRecTable;
import entity.Problems;
import entity.Programs;
import entity.Recommendation;
import entity.programsKPI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class viewProgramDetails extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = getServletContext();

        ProgramsDB progdb = new ProgramsDB();
        HttpSession session = request.getSession();
        Programs prog;
        ArrayList<Problems> probList;
        ArrayList<programsKPI> kpilist;
        String name = request.getParameter("name").trim();
     
        System.err.println("TODAYS user " + session.getAttribute("user"));
        System.err.println("TODAYS DATE " + session.getAttribute("todayDate"));
        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            System.out.println(attrNames);
        }
        System.out.println(name);
//
        prog = progdb.viewProgDetails(name);
        if(prog==null) System.out.println("is null bruh");
        probList = progdb.viewProgProb(name);
        kpilist = progdb.viewProg1Targets(name);
//        prog.settFarms(42);
        if(kpilist!=null){
               prog.setProgress(progdb.viewProgramProgress(kpilist));
        }else{
            prog.setProgress(0);
        }
     
        session.setAttribute("progdet", prog);
        session.setAttribute("prob", probList);
        session.setAttribute("kpis", kpilist);
        session.setAttribute("name", name);

        RequestDispatcher rd = context.getRequestDispatcher("/viewProgramDetails.jsp");

        rd.forward(request, response);

        response.setCharacterEncoding("utf-8");

    }

}
