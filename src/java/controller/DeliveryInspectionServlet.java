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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import objects.PurchaseOrder;
import objects.RequestForDeliveryInspection;
import services.EmployeeService;
import services.PurchaseOrderService;
import services.RequestForDeliveryInspectionService;

/**
 *
 * @author RubySenpaii
 */
@MultipartConfig
public class DeliveryInspectionServlet extends BaseServlet {

    private RequestForDeliveryInspectionService deliveryInspectionService = new RequestForDeliveryInspectionService();
    private PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("action used " + action);
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Request":
                    url = RequestInspection(request);
                    break;
                case "Submit":
                    url = AddRequestForInspection(request);
                    break;
                case "Review":
                    url = ReviewRequest(request);
                    break;
                case "List":
                default:
                    url = ListRequestsForInspection(request);
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }

    public String RequestInspection(HttpServletRequest request) {
        PurchaseOrder purchaseOrder = purchaseOrderService.FindPurchaseOrderById(Integer.parseInt(request.getParameter("purchaseOrder")));
        ArrayList<Employee> employees = employeeService.FindInspectorSpecializing(purchaseOrder.PurchaseRequest.AssetsRequested.get(0).Asset.AssetType);
        HttpSession session = request.getSession();
        session.setAttribute("employees", employees);
        return "/forms/delivery-inspection/request.jsp";
    }

    public String AddRequestForInspection(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            FileModification file = new FileModification();
            PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
            RequestForDeliveryInspection requestInspection = new RequestForDeliveryInspection();
            String orderNumber = purchaseOrderService.FindPurchaseOrderById(purchaseOrder.PurchaseOrderId).PurchaseOrderNumber;

            /*try {
                purchaseOrder.ORSNumber = request.getParameter("orsno");
                purchaseOrder.ORSDate = SharedFormat.DB_DATE_ENTRY.parse(request.getParameter("orsdate"));
                int result = purchaseOrderService.UpdatePurchaseOrder(purchaseOrder);
                System.out.println("update result: " + result);
            } catch (ParseException ex) {
                Logger.getLogger(DeliveryInspectionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            requestInspection.DeliveryInspectionId = deliveryInspectionService.GetTotalDeliveryInspection().size() + 1;
            System.out.println("LAMAN IS" + requestInspection.DeliveryInspectionId);
            Part invoiceFile = request.getPart("invoice");
            requestInspection.Invoice = orderNumber + "invoice";
            int invoiceSaved = file.SaveFile(getServletContext().getRealPath("/uploaded-files/invoice"), invoiceFile, requestInspection.Invoice);
            if (invoiceSaved == 1) {
                //Part receipt = request.getPart("receipt");
                //requestInspection.DeliveryReceipt = orderNumber + "receipt";
                requestInspection.DeliveryReceipt = "placeholder";
                int receiptSaved = 1; //file.SaveFile(getServletContext().getRealPath("/uploaded-files/delivery-receipt"), receipt, requestInspection.DeliveryReceipt);
                if (receiptSaved == 1) {
                    requestInspection.PurchaseOrderId = purchaseOrder.PurchaseOrderId;
                    requestInspection.CreatedDate = Calendar.getInstance().getTime();
                    Employee user = (Employee) session.getAttribute("user");
                    requestInspection.CreatedBy = user.EmployeeId;
                    String fullName = request.getParameter("assign");
                    requestInspection.AssignedTo = employeeService.FindEmployeeByFullName(fullName).EmployeeId;
                    requestInspection.Remarks = request.getParameter("remarks");
                    requestInspection.FromBidding = 0;
                    requestInspection.ManagementRemarks = "";
                    requestInspection.IsCompleted = 0;
                    int added = deliveryInspectionService.AddRequestForDeliveryInspection(requestInspection);
                    if (added == 1) {
                        session.setAttribute("notif", true);
                        requestInspection.ApprovedBy = user.EmployeeId;
                        int update = deliveryInspectionService.UpdateRequestForDeliveryInspection(requestInspection);
                        return "/DeliveryInspectionServlet/List";
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DeliveryInspectionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(DeliveryInspectionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/DeliveryInspectionServlet/Add";
    }

    public String ListRequestsForInspection(HttpServletRequest request) {
        ArrayList<RequestForDeliveryInspection> requests = deliveryInspectionService.GetRequestsForDeliveryInspection();
        HttpSession session = request.getSession();
        session.setAttribute("requests", requests);
        return "/forms/delivery-inspection/list.jsp";
    }

    public String ReviewRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (request.getParameter("action").equals("Approve")) {
            Employee user = (Employee) session.getAttribute("user");
            RequestForDeliveryInspection requestInspection = deliveryInspectionService.GetRequestForDeliveryInspection(Integer.parseInt(request.getParameter("requestId")));
            requestInspection.ApprovedBy = user.EmployeeId;
            int update = deliveryInspectionService.UpdateRequestForDeliveryInspection(requestInspection);
            session.setAttribute("approveNotif", true);
            System.out.println("update request for delivery inspection status: " + update);
        }
        return "/DeliveryInspectionServlet/List";
    }
}
