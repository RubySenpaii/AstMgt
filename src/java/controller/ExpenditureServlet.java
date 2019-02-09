/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.FileModification;
import extra.SharedFormat;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
                case "SubmitWFP":
                    url = WFP(request);
                    break;
                case "SubmitAPP":
                    url = APP(request);
                    break;
                case "Submit":
                    url = SubmitExpenditureLimit(request);
                    break;
                default:
                    url = ExpenditureLimit(request);
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }

    private String ExpenditureLimit(HttpServletRequest request) {
        File file = new File(getServletContext().getRealPath(SharedFormat.APP_FILE_PATH));
        String fileNames[] = file.list();
        ArrayList<String> filtered = new ArrayList<>();
        for (int i = 0; i < fileNames.length; i++) {
            if (!fileNames[i].equals("placeholder.md")) {
                filtered.add(fileNames[i]);
            }
        }
        Collections.sort(filtered);
        HttpSession session = request.getSession();
        session.setAttribute("fileList", filtered);
        return "/management/expenditure_limit.jsp";
    }

    private String WFP(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Employee user = (Employee) session.getAttribute("user");
            FileModification file = new FileModification();
            Part wfpFile = request.getPart("wfp");
            file.SaveFile(getServletContext().getRealPath("/uploaded-files/wfp"), wfpFile, "WFP" + user.Division + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()));

            //Part appFile = request.getPart("annual-plan");
            //file.SaveFile(getServletContext().getRealPath("/uploaded-files/app"), appFile, "AnnualProcurementPlan" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()));
        } catch (IOException x) {
            System.err.println("IO Exception in uploading wfp");
            System.err.println(x);
        } catch (ServletException x) {
            System.err.println("Servlet Exception in uploading wfp");
            System.err.println(x);
        }
        return "/HomeServlet";
    }

    private String APP(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Employee user = (Employee) session.getAttribute("user");
            FileModification file = new FileModification();

            Part appFile = request.getPart("app");
            file.SaveFile(getServletContext().getRealPath("/uploaded-files/app"), appFile, "AnnualProcurementPlan" + SharedFormat.TIME_STAMP.format(Calendar.getInstance().getTime()));
        } catch (IOException x) {
            System.err.println("IO Exception in uploading app");
            System.err.println(x);
        } catch (ServletException x) {
            System.err.println("Servlet Exception in uploading app");
            System.err.println(x);
        }
        return "/HomeServlet";
    }

    private String SubmitExpenditureLimit(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");

        int year = Calendar.getInstance().get(Calendar.YEAR);
        String quarter = SharedFormat.getQuarter();
//        double adminSupplies = Double.parseDouble(request.getParameter("admin-supplies"));
        double adminSupplies = 0.00;
        double adminEquipment = Double.parseDouble(request.getParameter("admin-equipment"));
//        double procurementSupplies = Double.parseDouble(request.getParameter("procurement-supplies"));
        double procurementSupplies = 0.00;
        double procurementEquipment = Double.parseDouble(request.getParameter("procurement-equipment"));
//        double managementSupplies = Double.parseDouble(request.getParameter("management-supplies"));
        double managementSupplies = 0.00;
        double managementEquipment = Double.parseDouble(request.getParameter("management-equipment"));
//        double generalSupplies = Double.parseDouble(request.getParameter("general-supplies"));
        double generalSupplies = 0.00;
        double generalEquipment = Double.parseDouble(request.getParameter("general-equipment"));
//        double financeSupplies = Double.parseDouble(request.getParameter("finance-supplies"));
        double financeSupplies = 0.00;
        double financeEquipment = Double.parseDouble(request.getParameter("finance-equipment"));

        ExpenditureLimit adminLimit = new ExpenditureLimit();
        adminLimit.Division = "Admin";
        adminLimit.Equipment = adminEquipment;
        adminLimit.Quarter = quarter;
        adminLimit.Supplies = adminSupplies;
        adminLimit.Year = year;

        ExpenditureLimit procurementLimit = new ExpenditureLimit();
        procurementLimit.Division = "Procurement";
        procurementLimit.Equipment = procurementEquipment;
        procurementLimit.Quarter = quarter;
        procurementLimit.Supplies = procurementSupplies;
        procurementLimit.Year = year;

        ExpenditureLimit managementLimit = new ExpenditureLimit();
        managementLimit.Division = "Management";
        managementLimit.Equipment = managementEquipment;
        managementLimit.Quarter = quarter;
        managementLimit.Supplies = managementSupplies;
        managementLimit.Year = year;

        ExpenditureLimit generalLimit = new ExpenditureLimit();
        generalLimit.Division = "General";
        generalLimit.Equipment = generalEquipment;
        generalLimit.Quarter = quarter;
        generalLimit.Supplies = generalSupplies;
        generalLimit.Year = year;

        ExpenditureLimit financeLimit = new ExpenditureLimit();
        financeLimit.Division = "Finance";
        financeLimit.Equipment = financeEquipment;
        financeLimit.Quarter = quarter;
        financeLimit.Supplies = financeSupplies;
        financeLimit.Year = year;

        /*try {
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
        }*/
        ExpenditureLimitService expenditureService = new ExpenditureLimitService();
        int result = expenditureService.AddExpenditureLimit(adminLimit);
        result = expenditureService.AddExpenditureLimit(financeLimit);
        result = expenditureService.AddExpenditureLimit(generalLimit);
        result = expenditureService.AddExpenditureLimit(managementLimit);
        result = expenditureService.AddExpenditureLimit(procurementLimit);
        if (result == 1) {
            return "/template.jsp";
        } else {
            return "/AMS/ExpenditureServlet";
        }
    }
}
