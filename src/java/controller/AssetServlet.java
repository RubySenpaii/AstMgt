/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.SharedFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Asset;
import objects.AssetIncident;
import objects.AssetTracking;
import objects.Employee;
import services.AssetIncidentService;
import services.AssetService;
import services.AssetTrackingService;
import services.EmployeeService;

/**
 *
 * @author RubySenpaii
 */
public class AssetServlet extends BaseServlet {

    private AssetService assetService = new AssetService();
    private EmployeeService employeeService = new EmployeeService();
    private AssetIncidentService assetIncidentService = new AssetIncidentService();
    private AssetTrackingService assetTrackingService = new AssetTrackingService();

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("action used " + action);
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Add":
                    url = "/forms/asset/add.jsp";
                    break;
                case "Submit":
                    url = SubmitAsset(request);
                    break;
                case "LogIncident":
                    url = "/forms/asset/log-incident.jsp";
                    break;
                case "LogTracking":
                    url = LogTracking(request);
                    break;
                case "SubmitIncident":
                    url = SubmitIncident(request);
                    break;
                case "SubmitTracking":
                    url = SubmitTracking(request);
                    break;
                case "List":
                default:
                    url = ListAssets(request);
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }

    private String SubmitAsset(HttpServletRequest request) {
        Asset asset = new Asset();
        asset.AssetId = assetService.GetAssets().size() + 1;
        asset.AssetName = request.getParameter("asset-name");
        asset.AssetType = request.getParameter("asset-type");
        asset.Description = request.getParameter("description");
        asset.FundCluster = request.getParameter("fund-cluster");
        asset.StockNo = request.getParameter("stock-no");
        asset.Unit = request.getParameter("unit");
        int result = assetService.AddAsset(asset);
        if (result == 1) {
            return "/AssetServlet/List";
        } else {
            return "/AssetServlet/Add";
        }
    }

    private String ListAssets(HttpServletRequest request) {
        ArrayList<Asset> assets = assetService.GetAssets();
        HttpSession session = request.getSession();
        session.setAttribute("assets", assets);
        return "/forms/asset/list.jsp";
    }

    private String LogTracking(HttpServletRequest request) {
        ArrayList<Employee> employees = employeeService.FindAllEmployee();
        HttpSession session = request.getSession();
        session.setAttribute("employees", employees);
        return "/forms/asset/log-tracking.jsp";
    }

    private String SubmitIncident(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");

        AssetIncident incident = new AssetIncident();
        incident.AssetTag = request.getParameter("asset-tag");
        incident.Timestamp = Calendar.getInstance().getTime();
        incident.Remarks = request.getParameter("remarks");
        incident.ReportedBy = employee.EmployeeId;
        int result = assetIncidentService.AddAssetIncident(incident);
        if (result == 1) {
            return "/InventoryServlet/EquipmentList";
        } else {
            return "/AssetServlet/LogIncident";
        }
    }

    private String SubmitTracking(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("user");

            AssetTracking tracking = new AssetTracking();
            tracking.AssetTag = request.getParameter("asset-tag");
            tracking.ReleasedBy = employee.EmployeeId;
            tracking.ReleasedTo = employeeService.FindEmployeeByFullName(request.getParameter("release-to")).EmployeeId;
            tracking.Remarks = request.getParameter("remarks");
            tracking.TransferDate = SharedFormat.DB_DATE_ENTRY.parse(request.getParameter("transfer-date"));
            int result = assetTrackingService.AddAssetTracking(tracking);
            if (result == 1) {
                return "/InventoryServlet/EquipmentList";
            }
        } catch (ParseException x) {
            System.err.println(x);
        }
        return "/AssetServlet/LogTracking";
    }
}
