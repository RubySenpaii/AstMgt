/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.SharedFormat;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import objects.Equipment;
import objects.ExpenditureTracking;
import objects.RepairLog;
import services.AssetIncidentService;
import services.AssetService;
import services.AssetTrackingService;
import services.EmployeeService;
import services.EquipmentService;
import services.ExpenditureTrackingService;
import services.RepairLogService;

/**
 *
 * @author RubySenpaii
 */
public class AssetServlet extends BaseServlet {

    private AssetService assetService = new AssetService();
    private EquipmentService equipmentService = new EquipmentService();
    private EmployeeService employeeService = new EmployeeService();
    private AssetIncidentService assetIncidentService = new AssetIncidentService();
    private AssetTrackingService assetTrackingService = new AssetTrackingService();
    private RepairLogService repairLogService = new RepairLogService();

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("action used " + action);
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Add":
                    url = AddAsset(request);
                    break;
                case "Donate":
                    url = Donate(request);
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
                case "LogRepair":
                    url = LogRepair(request);
                    break;
                case "SubmitRepair":
                    url = SubmitRepair(request);
                    break;
                case "RepairRequests":
                    url = RepairRequests(request);
                    break;
                case "RepairRequest":
                    url = RepairRequest(request);
                    break;
                case "ApproveRepair":
                    url = ApproveRepair(request);
                    break;
                case "SubmitIncident":
                    url = SubmitIncident(request);
                    break;
                case "EquipmentStatus":
                    url = ChangeEquipStatus(request);
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

    private String LogRepair(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");
        ArrayList<AssetTracking> userAssets = new AssetTrackingService().GetArrayListOfEmployee(employee.EmployeeId);
        session.setAttribute("userAssets", userAssets);
        return "/forms/asset/log-repair.jsp";
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
        asset.EstimatedUsefulLife = Integer.parseInt(request.getParameter("estimated-useful-life"));
        int result = assetService.AddAsset(asset);
        if (result == 1) {
            return "/AssetServlet/List";
        } else {
            return "/AssetServlet/Add";
        }
    }

    private String AddAsset(HttpServletRequest request) {
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
        return "/forms/asset/add.jsp";
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
        incident.Severity = Integer.parseInt(request.getParameter("severity"));
        incident.ReportedBy = employee.EmployeeId;
        int result = assetIncidentService.AddAssetIncident(incident);
        if (result == 1) {
            if (incident.Remarks.contains("dispose") || incident.Remarks.contains("disposal") || incident.Severity == 3) {
                Equipment equipment = equipmentService.GetEquipmentWithAssetTag(incident.AssetTag);
                equipment.Flag = 0;
                result = equipmentService.UpdateEquipment(equipment);
                System.out.println("successfully update equipment flag: " + result);
            }
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
            int transferType = Integer.parseInt(request.getParameter("transfer-type"));
            tracking.Remarks = transferType + request.getParameter("remarks");
            tracking.TransferDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("transfer-date"));
            int result = assetTrackingService.AddAssetTracking(tracking);
            if (result == 1) {
                return "/InventoryServlet/EquipmentList";
            }
        } catch (ParseException x) {
            System.err.println(x);
        }
        return "/AssetServlet/LogTracking";
    }

    private String SubmitRepair(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");

        String assetTag = request.getParameter("asset-tag");
        String[] articles = request.getParameterValues("article");
        String[] costs = request.getParameterValues("cost");

        for (int i = 0; i < articles.length; i++) {
            RepairLog repairLog = new RepairLog();
            repairLog.AssetTag = assetTag.split("\\*")[0];
            repairLog.RequestedBy = employee.EmployeeId;
            repairLog.RequestedDate = Calendar.getInstance().getTime();
            repairLog.Article = articles[i];
            repairLog.Cost = Double.parseDouble(costs[i]);
            int result = repairLogService.AddRepairLog(repairLog);
            if (result == 1) {
                ExpenditureTrackingService expenditureTrackingService = new ExpenditureTrackingService();
                ExpenditureTracking expenditure = expenditureTrackingService.GetCurrentExpenditure(employee.Division);
                expenditure.Timestamp = Calendar.getInstance().getTime();
                expenditure.Equipment -= repairLog.Cost;
                result = expenditureTrackingService.AddEquipmentTracking(expenditure);
            }
            System.out.println("result: " + result);
        }

        return "/InventoryServlet/EquipmentList";
    }

    private String RepairRequests(HttpServletRequest request) {
        ArrayList<RepairLog> repairRequests = repairLogService.GetRepairLogs();
        HttpSession session = request.getSession();
        session.setAttribute("repairRequests", repairRequests);
        return "/forms/asset/repair-requests.jsp";
    }

    private String RepairRequest(HttpServletRequest request) {
        int idx = Integer.parseInt(request.getParameter("index"));
        HttpSession session = request.getSession();
        RepairLog log = ((ArrayList<RepairLog>) session.getAttribute("repairRequests")).get(idx);
        ArrayList<AssetIncident> incidents = new AssetIncidentService().GetIncidentsOfAsset(log.AssetTag);
        session.setAttribute("repairRequest", log);
        Equipment equip = new Equipment();
        equip = equipmentService.GetEquipmentWithAssetTag(log.AssetTag);
        session.setAttribute("incidents", incidents);
        session.setAttribute("equipmentCost", equip.AcquisitionCost);
        double total = 0.00;
        for (RepairLog tlogs : log.Logs) {
            total += tlogs.Cost;
        }
        session.setAttribute("totalCost", total);
        return "/forms/asset/repair-request.jsp";
    }

    private String ApproveRepair(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");
        RepairLog log = (RepairLog) session.getAttribute("repairRequest");
        log.ApprovedBy = employee.EmployeeId;
        log.ApprovedDate = Calendar.getInstance().getTime();
        int result = repairLogService.UpdateRepairLog(log);
        System.out.println("update result: " + result);
        return "/AssetServlet/RepairRequests";
    }

    private String ChangeEquipStatus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String assetTag = request.getParameter("asset-tag");
        String action = request.getParameter("action");
        Equipment equip = equipmentService.GetEquipmentWithAssetTag(assetTag);
        if (action.equals("extend")) {
            equip.Flag = 4;
            equipmentService.UpdateEquipment(equip);
        } else if (action.equals("dispose")) {
            equip.Flag = 0;
            equipmentService.UpdateEquipment(equip);
        } else if (action.equals("reacquire")) {
            equip.Flag = 0;
            equipmentService.UpdateEquipment(equip);
            return "/PurchaseRequest/Add?asset-id=" + equip.AssetId;
        }
        return "/InventoryServlet/EquipmentList";
    }

    private String Donate(HttpServletRequest request) {
        String assetTag = request.getParameter("asset-tag");
        String receiver = request.getParameter("receiver");
        Equipment equip = equipmentService.GetEquipmentWithAssetTag(assetTag);
        equip.Flag = 5;
        equip.Condition = receiver;
        equipmentService.UpdateEquipment(equip);
        return "/InventoryServlet/EquipmentList";
    }
}
