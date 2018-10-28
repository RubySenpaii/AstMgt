/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.FileModification;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import objects.RequestForDeliveryInspection;
import services.PurchaseOrderService;
import services.RequestForDeliveryInspectionService;

/**
 *
 * @author RubySenpaii
 */
public class DeliveryInspectionServlet extends BaseServlet {

    private RequestForDeliveryInspectionService deliveryInspectionService;
    private PurchaseOrderService purchaseOrderService;

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("action used " + action);
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Request":
                case "Submit":
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

    public String AddRequestForInspection(HttpServletRequest request) {
        try {
            deliveryInspectionService = new RequestForDeliveryInspectionService();
            purchaseOrderService = new PurchaseOrderService();
            FileModification file = new FileModification();
            RequestForDeliveryInspection requestInspection = new RequestForDeliveryInspection();
            String orderNumber = purchaseOrderService.FindPurchaseOrderByNo(requestInspection.PurchaseOrderId).PurchaseOrderNumber;
            
            requestInspection.DeliveryInspectionId = deliveryInspectionService.GetRequestsForDeliveryInspection().size() + 1;
            Part invoiceFile = request.getPart("invoice");
            requestInspection.Invoice = orderNumber + "invoice";
            file.SaveFile(getServletContext().getRealPath("uploaded-files/invoice"), invoiceFile, requestInspection.Invoice);
            Part receipt = request.getPart("receipt");
            requestInspection.DeliveryReceipt = orderNumber + "receipt";
            file.SaveFile(getServletContext().getRealPath("uploaded-files/delivery-receipt"), receipt, requestInspection.DeliveryReceipt);
            return "/DeliveryInspectionService/List";
        } catch (IOException ex) {
            Logger.getLogger(DeliveryInspectionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(DeliveryInspectionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/DeliveryInspectionService/Add";
    }

    public String ListRequestsForInspection(HttpServletRequest request) {
        deliveryInspectionService = new RequestForDeliveryInspectionService();
        ArrayList<RequestForDeliveryInspection> requests = deliveryInspectionService.GetRequestsForDeliveryInspection();
        HttpSession session = request.getSession();
        session.setAttribute("requests", requests);
        return "/forms/delivery-inspection/list.jsp";
    }
}
