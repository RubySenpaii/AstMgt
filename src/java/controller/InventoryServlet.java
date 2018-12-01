/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.AssetIncident;
import objects.AssetRequested;
import objects.AssetTracking;
import objects.Employee;
import objects.Equipment;
import objects.PurchaseOrder;
import objects.RequestForDeliveryInspection;
import objects.Supplies;
import services.AssetIncidentService;
import services.AssetTrackingService;
import services.EquipmentService;
import services.PurchaseOrderService;
import services.RequestForDeliveryInspectionService;
import services.SuppliesService;

/**
 *
 * @author RubySenpaii
 */
public class InventoryServlet extends BaseServlet {

    private EquipmentService equipmentService = new EquipmentService();
    private SuppliesService suppliesService = new SuppliesService();
    private PurchaseOrderService poService = new PurchaseOrderService();
    private RequestForDeliveryInspectionService deliveryInspectionService = new RequestForDeliveryInspectionService();
    private AssetTrackingService assetTrackingService = new AssetTrackingService();
    private AssetIncidentService assetIncidentService = new AssetIncidentService();

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "AcknowledgementRequest":
                    url = AcknowledgeRequest(request);
                    break;
                case "Acknowledge":
                    url = Acknowledge(request);
                    break;
                case "SuppliesList":
                    url = ListSupplies(request);
                    break;
                case "SuppliesView":
                    url = ViewSupplies(request);
                    break;
                case "ReleaseSupplies":
                    url = ReleaseSupplies(request);
                    break;
                case "SubmitRelease":
                    url = SubmitRelease(request);
                    break;
                case "EquipmentView":
                    url = ViewEquipment(request);
                    break;
                case "ShowTrackingRequests":
                    url = ShowTrackingRequests(request);
                    break;
                case "ReviewTracking":
                    url = ReviewTracking(request);
                    break;
                case "EquipmentList":
                default:
                    url = ListEquipment(request);
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }

    private String AcknowledgeRequest(HttpServletRequest request) {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        RequestForDeliveryInspection requestInspection = deliveryInspectionService.GetRequestForDeliveryInspection(requestId);
        HttpSession session = request.getSession();
        session.setAttribute("purchaseOrder", requestInspection.PurchaseOrder);
        return "/inventory/acknowledgement.jsp";
    }

    private String Acknowledge(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<AssetRequested> assetsRequested = ((PurchaseOrder) session.getAttribute("purchaseOrder")).PurchaseRequest.AssetsRequested;
        String[] assetTags = request.getParameterValues("asset-tag");
        String[] condition = request.getParameterValues("condition");
        int qty = 1, counter = 0;
        for (int i = 0; i < assetsRequested.size(); i++) {
            if (assetsRequested.get(i).Asset.AssetType.contains("Equipment")) {
                Employee employee = (Employee) session.getAttribute("user");
                Equipment equipment = new Equipment();
                equipment.AssetId = assetsRequested.get(i).AssetId;
                equipment.AssetTag = assetTags[counter];
                equipment.Condition = condition[counter];
                equipment.DateAcquired = Calendar.getInstance().getTime();
                equipment.Flag = 1;
                int result = equipmentService.AddEquipment(equipment);

                AssetTracking init = new AssetTracking();
                init.AssetTag = equipment.AssetTag;
                init.ApprovedBy = employee.EmployeeId;
                init.ApprovedDate = Calendar.getInstance().getTime();
                init.ReleasedBy = employee.EmployeeId;
                init.ReleasedTo = employee.EmployeeId;
                init.Remarks = "received asset";
                init.TransferDate = Calendar.getInstance().getTime();
                int trackResult = assetTrackingService.AddAssetTracking(init);
                assetTrackingService.UpdateAssetTracking(init);
                System.out.println(equipment.AssetTag + " is added: " + result + " tracking: " + trackResult);
                if (qty != assetsRequested.get(i).Quantity) {
                    qty++;
                    i--;
                } else {
                    qty = 1;
                }
            } else {
                Supplies supplies = new Supplies();
                supplies.AssetId = assetsRequested.get(i).AssetId;
                supplies.AmountAcquired = assetsRequested.get(i).Quantity;
                supplies.AmountConsumed = 0;
                supplies.Timestamp = Calendar.getInstance().getTime();
                supplies.TotalQuantity = supplies.AmountAcquired + suppliesService.GetLatestCountOfSupplies(supplies.AssetId);
                int result = suppliesService.AddNewSupply(supplies);
                System.out.println(supplies.AssetId + " is updated: " + result);
            }
            counter++;
        }
        return "/InventoryServlet/EquipmentList";
    }

    private String ListEquipment(HttpServletRequest request) {
        ArrayList<Equipment> equipments = equipmentService.GetListOfEquipments();
        HttpSession session = request.getSession();
        session.setAttribute("equipments", equipments);
        return "/inventory/equipment-list.jsp";
    }

    private String ListSupplies(HttpServletRequest request) {
        ArrayList<Supplies> supplies = suppliesService.FindAllSupplies();
        HttpSession session = request.getSession();
        session.setAttribute("supplies", supplies);
        return "/inventory/supplies-list.jsp";
    }

    private String ViewEquipment(HttpServletRequest request) {
        String assetTag = request.getParameter("asset-tag");
        Equipment equipment = equipmentService.GetEquipmentWithAssetTag(assetTag);
        equipment.TrackingLogs = assetTrackingService.GetAssetHistory(assetTag);
        equipment.IncidentLogs = assetIncidentService.GetIncidentsOfAsset(assetTag);
        HttpSession session = request.getSession();
        session.setAttribute("equipment", equipment);
        return "/inventory/equipment-view.jsp";
    }
    
    private String ViewSupplies(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int assetId = Integer.parseInt(request.getParameter("asset-id"));
        ArrayList<Supplies> supplies = suppliesService.FindSuppliesByAssetId(assetId);
        session.setAttribute("supplies", supplies);
        return "/inventory/supplies-view.jsp";
    }

    private String ReleaseSupplies(HttpServletRequest request) {
        int assetId = Integer.parseInt(request.getParameter("asset-id"));
        HttpSession session = request.getSession();
        int currentCount = suppliesService.GetLatestCountOfSupplies(assetId);
        Supplies supply = new Supplies();
        supply.AssetId = assetId;
        supply.TotalQuantity = currentCount;
        session.setAttribute("supply", supply);
        return "/inventory/release-supplies.jsp";
    }

    private String SubmitRelease(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Supplies supply = (Supplies) session.getAttribute("supply");

        String[] quantity = request.getParameterValues("quantity");
        String[] division = request.getParameterValues("division");

        int currentQty = supply.TotalQuantity;
        for (int i = 0; i < quantity.length; i++) {
            Supplies tempSupply = new Supplies();
            tempSupply.AssetId = supply.AssetId;
            tempSupply.AmountAcquired = 0;
            tempSupply.AmountConsumed = Integer.parseInt(quantity[i]);
            tempSupply.Division = division[i];
            tempSupply.Timestamp = Calendar.getInstance().getTime();
            tempSupply.TotalQuantity = currentQty - tempSupply.AmountConsumed;
            int result = suppliesService.ConsumeSupply(tempSupply);
            if (result == 1) {
                currentQty = tempSupply.TotalQuantity;
            }
            System.out.println("update result: " + result);
        }
        return "/InventoryServlet/SuppliesList";
    }
    
    private String ShowTrackingRequests(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<AssetTracking> assetTrackings = assetTrackingService.GetPendingTracking();
        session.setAttribute("assetTrackings", assetTrackings);
        return "/forms/asset/tracking-requests.jsp";
    }
    
    private String ReviewTracking(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<AssetTracking> trackings = (ArrayList<AssetTracking>) session.getAttribute("assetTrackings");
        AssetTracking tracking = trackings.get(0);
        String value = request.getParameter("review");
        tracking.ApprovedBy = ((Employee) session.getAttribute("user")).EmployeeId;
        if (value.equals("approve")) {
            tracking.ApprovedDate = Calendar.getInstance().getTime();
        }
        int result = assetTrackingService.UpdateAssetTracking(tracking);
        if (result == 1) {
            int newStatus = Integer.parseInt(String.valueOf(tracking.Remarks.charAt(0)));
            Equipment equipment = equipmentService.GetEquipmentWithAssetTag(tracking.AssetTag);
            equipment.Flag = newStatus;
            equipmentService.UpdateEquipment(equipment);
        }
        return "/InventoryServlet/ShowTrackingRequests";
    }
}
