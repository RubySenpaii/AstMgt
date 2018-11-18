/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.FileModification;
import extra.SharedFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import objects.Employee;
import objects.ExpenditureLimit;
import services.ExpenditureLimitService;

/**
 *
 * @author RubySenpaii
 */
@MultipartConfig
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
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        double supplies = Double.parseDouble(request.getParameter("supplies"));
        double equipment = Double.parseDouble(request.getParameter("equipment"));

        ExpenditureLimit limit = new ExpenditureLimit();
        limit.Equipment = equipment;
        limit.Supplies = supplies;
        limit.Year = year;
        limit.Division = employee.Division;

        try {
            FileModification file = new FileModification();
            Part wfpFile = request.getPart("financial-plan");
            file.SaveFile(getServletContext().getRealPath("/uploaded-files/wfp"), wfpFile, "WorkFinancialPlan" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()));
            
            //Part appFile = request.getPart("annual-plan");
            //file.SaveFile(getServletContext().getRealPath("/uploaded-files/app"), appFile, "AnnualProcurementPlan" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()));
        } catch (IOException x) {
            System.err.println("IO Exception in uploading wfp or app");
            System.err.println(x);
        } catch (ServletException x) {
            System.err.println("Servlet Exception in uploading wfp or app");
            System.err.println(x);
        }

        ExpenditureLimitService expenditureService = new ExpenditureLimitService();
        int result = expenditureService.AddExpenditureLimit(limit);
        if (result == 1) {
            return "/template.jsp";
        } else {
            return "/AMS/ExpenditureServlet";
        }
    }
}
