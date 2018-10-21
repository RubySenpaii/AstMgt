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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.PurchaseRequest;
import services.PurchaseRequestService;

/**
 *
 * @author RubySenpaii
 */
public class PurchaseRequestServlet extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Add":
                    url = "/AMS/forms/purchase-request/add.jsp";
                    break;
                case "Submit":
                    url = AddPurchaseRequest();
                    break;
                case "Edit":
                    url = EditPurchaseRequest();
                    break;
                case "View":
                case "List":
                case "Flag":
                default:
                    url = NoAction();
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }

    private String AddPurchaseRequest() {
        PurchaseRequestService purchaseRequestService = new PurchaseRequestService();
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        int result = purchaseRequestService.AddPurchaseRequest(purchaseRequest);
        switch (result) {
            case 0: return "";
            case 1: return "";
            default: return "";
        }
    }
    
    private String EditPurchaseRequest() {
        PurchaseRequestService purchaseRequestService = new PurchaseRequestService();
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        int result = purchaseRequestService.UpdatePurchaseRequest(purchaseRequest);
        switch (result) {
            default: return "";
        }
    }
    
    private String NoAction() {
        return "/forms/purchase-request/add.jsp";
    }
}
