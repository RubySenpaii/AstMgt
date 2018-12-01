/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.PurchaseRequest;
import services.PurchaseOrderService;
import services.PurchaseRequestService;

/**
 *
 * @author rubysenpaii
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchaseRequestService purchaseRequestService = new PurchaseRequestService();
        PurchaseOrderService purchaseOrderService = new PurchaseOrderService();
        HttpSession session = request.getSession();
        
        // list of pending purchase request
        ArrayList<PurchaseRequest> pendingPurchaseRequest = purchaseRequestService.FindPendingPurchaseRequests();
        // list of approved request and pending order
        ArrayList<PurchaseRequest> approvedPurchaseRequest = purchaseRequestService.FindApprovedPurchaseRequest();
        // list of rejected purchase request
        ArrayList<PurchaseRequest> rejectedPurchaseRequest = purchaseRequestService.FindRejectedPurchaseRequest();
        // list of approved and rejected purchase order
        // list of almost expiring equipment
        // list of low supplies
        
        session.setAttribute("pendingPurchaseRequests", pendingPurchaseRequest);
        session.setAttribute("approvedPurchaseRequests", approvedPurchaseRequest);
        session.setAttribute("rejectedPurchaseRequests", rejectedPurchaseRequest);
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/homepage.jsp");
        rd.forward(request, response);
    }
}
