/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.SharedFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import objects.ExpenditureLimit;
import objects.ExpenditureTracking;
import objects.PurchaseOrder;
import objects.RequestForDeliveryInspection;
import objects.Supplies;
import services.AssetIncidentService;
import services.AssetRequestedService;
import services.AssetTrackingService;
import services.EmployeeService;
import services.EquipmentService;
import services.ExpenditureTrackingService;
import services.PurchaseOrderService;
import services.RepairLogService;
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
    private RepairLogService repairLogService = new RepairLogService();

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
                case "EmployeeEquipment":
                    url = EmployeeEquipment(request);
                    break;
                case "ShowTrackingRequests":
                    url = ShowTrackingRequests(request);
                    break;
                case "ReviewTracking":
                    url = ReviewTracking(request);
                    break;
                case "UpdateItem":
                    url = UpdateItem(request);
                    break;
                case "EquipmentHistory":
                    url = EquipmentHistory(request);
                    break;
                case "RetiringEmployee":
                    url =  RetiringEmployee(request);
                    break;
                case "RetrieveItems":
                    url = RetrieveItems(request);
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
    
    private String RetrieveItems(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee user = (Employee) session.getAttribute("user");
        String[] assetTags = request.getParameterValues("asset-name");
        for (int i = 0; i < assetTags.length; i++) {
            AssetTracking tracking = new AssetTracking();
            tracking.AssetTag = assetTags[i];
            tracking.ReleasedBy = new AssetTrackingService().GetCurrentuser(assetTags[i]).EmployeeId;
            tracking.ReleasedTo = user.EmployeeId;
            tracking.Remarks = 1 + request.getParameter("remarks");
            tracking.TransferDate = Calendar.getInstance().getTime();
            int result = assetTrackingService.AddAssetTracking(tracking);
            
            Equipment equipment = new EquipmentService().GetEquipmentWithAssetTag(assetTags[i]);
            equipment.Flag = 1;
            System.out.println("Updated equip flag " + new EquipmentService().UpdateEquipment(equipment));
            System.out.println("Retrieve items result: " + result);
        }
        return "/HomeServlet";
    }
    
    private String EquipmentHistory(HttpServletRequest request) {
        int quarter = Integer.parseInt(SharedFormat.getQuarter().charAt(1) + "");
        ArrayList<Equipment> equipments = equipmentService.GetListOfEquipmentsForTheQuarter(quarter);
        HttpSession session = request.getSession();
        session.setAttribute("equipments", equipments);
        return "/inventory/equipment-history.jsp";
    }

    private String AcknowledgeRequest(HttpServletRequest request) {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        RequestForDeliveryInspection requestInspection = deliveryInspectionService.GetRequestForDeliveryInspection(requestId);
        HttpSession session = request.getSession();
        session.setAttribute("purchaseOrder", requestInspection.PurchaseOrder);
        session.setAttribute("requestId", requestId);
        return "/inventory/acknowledgement.jsp";
    }

    private String Acknowledge(HttpServletRequest request) {
        HttpSession session = request.getSession();
        PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
        ArrayList<Integer> originalAssetsRequested = (ArrayList<Integer>) session.getAttribute("ogAssetsReq");
        ArrayList<AssetRequested> assetsRequested = purchaseOrder.PurchaseRequest.AssetsRequested;
        String[] assetTags = request.getParameterValues("asset-tag");
        String[] condition = request.getParameterValues("condition");
        RequestForDeliveryInspection rfi = new RequestForDeliveryInspectionService().GetRequestForInspectionByPurchaseOrder(purchaseOrder.PurchaseOrderId);
        rfi.IsCompleted = 1;
        System.out.println("updated rfi: " + new RequestForDeliveryInspectionService().UpdateRequestForDeliveryInspection(rfi));
        int qty = 1, counter = 0;
        double total = 0;
        for (int i = 0; i < assetsRequested.size(); i++) {
            try {
                if (assetsRequested.get(i).Quantity != originalAssetsRequested.get(i)) {
                    total += ((originalAssetsRequested.get(i) - assetsRequested.get(i).Quantity) * assetsRequested.get(i).UnitCost);
                }
                ExpenditureTrackingService expenditureTrackingService = new ExpenditureTrackingService();
                ExpenditureTracking expenditure = expenditureTrackingService.GetCurrentExpenditure(purchaseOrder.PurchaseRequest.Requester.Division);
                expenditure.Timestamp = Calendar.getInstance().getTime();
                expenditure.Equipment += total;
                int result = expenditureTrackingService.AddEquipmentTracking(expenditure);
            } catch (NullPointerException x) {
                System.out.println("full purchase received");
            }
            if (assetsRequested.get(i).Asset.AssetType.toLowerCase().contains("equipment")) {
                String description = "";
                // split description by \n and split details using //
                if (assetsRequested.get(i).Asset.AssetType.contains("Furniture")) {
                    description += "Color//" + request.getParameterValues("color")[counter] + "__";
                    description += "Office Destination//" + request.getParameterValues("office-destination")[counter] + "__";
                } else if (assetsRequested.get(i).Asset.AssetType.contains("Vehicle")) {
                    description += "Engine Number//" + request.getParameterValues("engine-number")[counter] + "__";
                    description += "Chassis Number//" + request.getParameterValues("chassis-number")[counter] + "__";
                    description += "Make//" + request.getParameterValues("make")[counter] + "__";
                    description += "Model//" + request.getParameterValues("model")[counter] + "__";
                    description += "Year//" + request.getParameterValues("year")[counter] + "__";
                    description += "Color//" + request.getParameterValues("color")[counter] + "__";
                } else if (assetsRequested.get(i).Asset.AssetType.contains("Appliance")) {
                    description += "Brand//" + request.getParameterValues("brand")[counter] + "__";
                    description += "Model//" + request.getParameterValues("model")[counter] + "__";
                    description += "Color//" + request.getParameterValues("color")[counter] + "__";
                } else if (assetsRequested.get(i).Asset.AssetType.contains("Electronics")) {
                    description += "Brand//" + request.getParameterValues("brand")[counter] + "__";
                    description += "Model//" + request.getParameterValues("model")[counter] + "__";
                    description += "Serial Number//" + request.getParameterValues("serial-number")[counter] + "__";
                    //description += "MAC Address//" + request.getParameterValues("mac-address")[counter] + "__";
                    description += "Build Number//" + request.getParameterValues("build-number")[counter] + "__";
                    description += "Color//" + request.getParameterValues("color")[counter] + "__";
                }
                description += "Warranty//" + request.getParameterValues("warranty")[counter];
                Employee employee = (Employee) session.getAttribute("user");
                Equipment equipment = new Equipment();
                equipment.AssetId = assetsRequested.get(i).AssetId;
                equipment.AssetTag = assetTags[counter];
                equipment.Condition = condition[counter];
                equipment.AcquisitionCost = assetsRequested.get(i).UnitCost;
                equipment.DateAcquired = Calendar.getInstance().getTime();
                equipment.Description = description;
                equipment.ModeOfProcurement = purchaseOrder.PurchaseRequest.ModeOfProcurement;
                equipment.Flag = 2;
                int result = equipmentService.AddEquipment(equipment);

                AssetTracking init = new AssetTracking();
                init.AssetTag = equipment.AssetTag;
                init.ApprovedBy = employee.EmployeeId;
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                init.ApprovedDate = cal.getTime();
                init.ReleasedBy = employee.EmployeeId;
                init.ReleasedTo = purchaseOrder.PurchaseRequest.RequestedBy;
                init.Remarks = "received asset";
                init.TransferDate = cal.getTime();
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
        session.setAttribute("invNotif", "true");
        return "/InventoryServlet/EquipmentList";
    }

    private String ListEquipment(HttpServletRequest request) {
        ArrayList<Equipment> equipments = equipmentService.GetListOfEquipments();
        HttpSession session = request.getSession();
        session.setAttribute("equipments", equipments);
        return "/inventory/equipment-list.jsp";
    }

    private String EmployeeEquipment(HttpServletRequest request) {
        ArrayList<Equipment> equipments = equipmentService.GetListOfEquipmentsOwnedBy(Integer.parseInt(request.getParameter("employeeId")));
        HttpSession session = request.getSession();
        session.setAttribute("equipments", equipments);
        return "/inventory/employee_equipment.jsp";
    }

    private String RetiringEmployee(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");
        employee.Flag  = 2;
        int res = new EmployeeService().UpdateEmployee(employee);
        System.out.println("retired emp " + res);
        return "/HomeServlet";
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
        if (equipment.Flag == 4) {
            equipment.Asset.EstimatedUsefulLife += 1;
        }
        equipment.TrackingLogs = assetTrackingService.GetAssetHistory(assetTag);
        equipment.IncidentLogs = assetIncidentService.GetIncidentsOfAsset(assetTag);
        equipment.RepairLogs = repairLogService.GetApprovedRepairLogs(assetTag);
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
        Employee user = (Employee) session.getAttribute("user");
        ArrayList<AssetTracking> assetTrackings = assetTrackingService.GetPendingTracking(user.EmployeeId,user.UserLevel);
        
        for (AssetTracking assetTracking : assetTrackings) {
            assetTracking.Trackings = assetTrackingService.GetAssetHistory(assetTracking.AssetTag);
            assetTracking.Incidents = assetIncidentService.GetIncidentsOfAsset(assetTracking.AssetTag);
            assetTracking.EmployeeAssets = assetTrackingService.GetArrayListOfEmployee(assetTracking.ReleasedTo);
        }
        session.setAttribute("assetTrackings", assetTrackings);
        return "/forms/asset/tracking-requests.jsp";
    }

    private String ReviewTracking(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<AssetTracking> trackings = (ArrayList<AssetTracking>) session.getAttribute("assetTrackings");
        int idx = Integer.parseInt(request.getParameter("idx"));
        AssetTracking tracking = trackings.get(idx);
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

    private String UpdateItem(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<AssetRequested> assetsRequested = ((PurchaseOrder) session.getAttribute("purchaseOrder")).PurchaseRequest.AssetsRequested;
        ArrayList<Integer> assetsQty = new ArrayList<>();
        String[] newQtys = request.getParameterValues("newQuantity");
        for (int i = 0; i < newQtys.length; i++) {
            assetsQty.add(assetsRequested.get(i).Quantity);
            assetsRequested.get(i).Quantity = Integer.parseInt(newQtys[i]);
        }
        session.setAttribute("ogAssetsReq", assetsQty);
        new AssetRequestedService().UpdateAssetRequest(assetsRequested);
        return "/InventoryServlet/AcknowledgementRequest?requestId=" + ((int) session.getAttribute("requestId"));
    }
}
