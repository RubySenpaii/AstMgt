/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.ExpenditureLimit;
import services.ExpenditureLimitService;

/**
 *
 * @author RubySenpaii
 */
public class ExpenditureServlet extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("action used " + action);
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Submit":
                    url = SubmitExpenditureLimit(request);
                    break;
                default:
                    url = "/management/expenditure_limit.jsp";
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }
    
    private String SubmitExpenditureLimit(HttpServletRequest request) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        double supplies = Double.parseDouble(request.getParameter("supplies"));
        double equipment = Double.parseDouble(request.getParameter("equipment"));
        
        ExpenditureLimit limit = new ExpenditureLimit();
        limit.Equipment = equipment;
        limit.Supplies = supplies;
        limit.Year = year;
        
        ExpenditureLimitService expenditureService = new ExpenditureLimitService();
        int result = expenditureService.AddExpenditureLimit(limit);
        if (result == 1) {
            return "/template.jsp";
        } else {
            return "/AMS/ExpenditureServlet";
        }
    }
}
