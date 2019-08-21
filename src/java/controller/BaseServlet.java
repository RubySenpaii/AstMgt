package controller;

import extra.SharedFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.AssetIncident;
import objects.AssetTracking;
import objects.Employee;
import objects.Equipment;
import objects.ExpenditureLimit;
import objects.ExpenditureTracking;
import objects.PurchaseOrder;
import objects.PurchaseRequest;
import objects.RepairLog;
import objects.RequestForDeliveryInspection;
import objects.Supplies;
import services.AssetIncidentService;
import services.AssetTrackingService;
import services.EmployeeService;
import services.EquipmentService;
import services.ExpenditureLimitService;
import services.ExpenditureTrackingService;
import services.PurchaseOrderService;
import services.PurchaseRequestService;
import services.RepairLogService;
import services.RequestForDeliveryInspectionService;
import services.SuppliesService;

@WebServlet(name = "BaseServlet", urlPatterns = {"/BaseServlet"})
public abstract class BaseServlet extends HttpServlet {

    public abstract void servletAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
            Employee user = (Employee) session.getAttribute("user");
            if (user.EmployeeId > 0) {
                System.out.println("user accessing " + request.getRequestURI());
                PurchaseRequestService purchaseRequestService = new PurchaseRequestService();
                PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
                EquipmentService equipmentService = new EquipmentService();
                // additional requests  stuff
                ArrayList<AssetTracking> assetTrackings = new AssetTrackingService().GetPendingTracking(user.EmployeeId, user.UserLevel);
                ArrayList<RepairLog> repairRequests = new RepairLogService().GetRepairLogs();
                //SuppliesService suppliesService = new SuppliesService();
                ExpenditureLimit exLimist = new ExpenditureLimitService().GetExpenditureLimitForYear(Calendar.getInstance().get(Calendar.YEAR), user.Division);
                ExpenditureTracking limit = new ExpenditureTrackingService().GetCurrentExpenditure(user.Division);
                ArrayList<PurchaseRequest> pendingPurchaseRequest = purchaseRequestService.FindPendingPurchaseRequests();
                // list of approved request and pending order
                ArrayList<PurchaseRequest> approvedPurchaseRequest = purchaseRequestService.FindApprovedPurchaseRequest();
                // list of rejected purchase request
                ArrayList<PurchaseRequest> rejectedPurchaseRequest = purchaseRequestService.FindRejectedPurchaseRequest();
                // list of approved and rejected purchase order
                // list of almost expiring equipment
                ArrayList<Equipment> equipments = equipmentService.GetListOfEquipments();
                ArrayList<Equipment> expiringEquipments = new ArrayList<>();
                for (Equipment equipment : equipments) {
                    Calendar cal = Calendar.getInstance();
                    Date now = cal.getTime();
                    cal.setTime(equipment.DateAcquired);
                    cal.add(Calendar.YEAR, equipment.Asset.EstimatedUsefulLife);
                    Date expiryDate = cal.getTime();
                    if (now.after(expiryDate) && (equipment.Flag == 1 || equipment.Flag == 2)) {
                        expiringEquipments.add(equipment);
                    }
                }
                ArrayList<PurchaseOrder> deliveringPurchaseOrder = purchaseOrderService.getPurchaseOrderExpectingDelivery();
                for (int i = 0; i < deliveringPurchaseOrder.size(); i++) {
                    try {
                        RequestForDeliveryInspection rfi = new RequestForDeliveryInspectionService().GetRequestForInspectionByPurchaseOrder(deliveringPurchaseOrder.get(i).PurchaseOrderId);
                        if (rfi.IsCompleted == 1) {
                            deliveringPurchaseOrder.remove(i);
                            i--;
                        }
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("no rfi yet");
                    }
                }
                // list of low supplies
                /*ArrayList<Supplies> lowSupplies = new ArrayList<>();
                ArrayList<Supplies> suppliesList = suppliesService.FindAllSupplies();
                for (Supplies supplies: suppliesList) {
                    if (supplies.TotalQuantity < 20) {
                        lowSupplies.add(supplies);
                    }
                }*/
                ArrayList<RequestForDeliveryInspection> pendingInspections = new RequestForDeliveryInspectionService().GetPendingRfi();
                ArrayList<Employee> retiringEmployees = new EmployeeService().GetRetiringEmployeeList();
                ArrayList<PurchaseOrder> purchaseOrders = purchaseOrderService.FindAllPurchaseOrder();
                ArrayList<RequestForDeliveryInspection> inspections = new RequestForDeliveryInspectionService().GetRequestsForDeliveryInspection();
                ArrayList<PurchaseOrder> poNoInspections = new ArrayList<>();
                for (PurchaseOrder purchaseOrder: purchaseOrders) {
                    for (int i = 0; i < inspections.size(); i++) {
                        if (purchaseOrder.PurchaseOrderId == inspections.get(i).PurchaseOrderId) {
                            break;
                        } else if (i == inspections.size() - 1) {
                            poNoInspections.add(purchaseOrder);
                        }
                    }
                }
                ArrayList<AssetIncident> incidents = new AssetIncidentService().GetPendingIncidents();
                ArrayList<Equipment> tempEquipments = new EquipmentService().GetListOfFlagEquipments(6);
                session.setAttribute("limit", limit);
                session.setAttribute("pendingPurchaseRequests", pendingPurchaseRequest);
                session.setAttribute("approvedPurchaseRequests", approvedPurchaseRequest);
                session.setAttribute("rejectedPurchaseRequests", rejectedPurchaseRequest);
                //session.setAttribute("lowSupplies", lowSupplies);
                session.setAttribute("expiringEquipments", expiringEquipments);
                session.setAttribute("trackingSize", assetTrackings.size());
                session.setAttribute("repairSize", repairRequests.size());
                session.setAttribute("purchaseOrderDelivering", deliveringPurchaseOrder);
                session.setAttribute("retiringEmployees", retiringEmployees);
                session.setAttribute("pendingInspections", pendingInspections);
                session.setAttribute("poNoInspection", poNoInspections);
                session.setAttribute("pendingAssetIncidents", incidents);
                session.setAttribute("tempEquip", tempEquipments);
                session.setAttribute("xlimit", exLimist);
                servletAction(request, response);
            } else {
                ServletContext context = getServletContext();
                RequestDispatcher rd = context.getRequestDispatcher("/Login");
                rd.forward(request, response);
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
