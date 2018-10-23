/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                    url = "/forms/purchase-request/add.jsp";
                    break;
                case "Submit":
                    url = AddPurchaseRequest(request);
                    break;
                case "Edit":
                    url = EditPurchaseRequest();
                    break;
                case "View":
                case "List":
                    url = ListPurchaseRequest(request);
                    break;
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
    
    private String ListPurchaseRequest(HttpServletRequest request){
        PurchaseRequestService prservice = new PurchaseRequestService();
        ArrayList<PurchaseRequest> PRList = new ArrayList<>();
        PRList = prservice.FindAllPR();
        HttpSession session = request.getSession();
        session.setAttribute("PR", PRList);
        return "/forms/purchase-request/list.jsp";
    }
    private String AddPurchaseRequest(HttpServletRequest request) throws ParseException {

        PurchaseRequest pr = new PurchaseRequest();
        pr.PurchaseRequestId = Integer.parseInt(request.getParameter("pri"));
        pr.ApprovedBy = Integer.parseInt(request.getParameter("appby"));
        pr.RequestedBy = Integer.parseInt(request.getParameter("reqby"));
        pr.PurchaseRequestNo = request.getParameter("pr");
        pr.Purpose = request.getParameter("purpose");
        pr.ResponsibilityCenterCode = request.getParameter("rcc");
        System.out.println(request.getParameter("date"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        pr.Date = sdf.parse(request.getParameter("date"));
        pr.ApprovedDate = sdf.parse(request.getParameter("appdate"));
        pr.RequestedDate = sdf.parse(request.getParameter("reqdate"));
        
        PurchaseRequestService purchaseRequestService = new PurchaseRequestService();
        int result = purchaseRequestService.AddPurchaseRequest(pr);
        switch (result) {
            case 0:
                return "/forms/login.jsp";
            case 1:
                return "/forms/purchase-request/add.jsp";
            default:
                return "/forms/login.jsp";
        }
    }

    private String EditPurchaseRequest() {
        PurchaseRequestService purchaseRequestService = new PurchaseRequestService();
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        int result = purchaseRequestService.UpdatePurchaseRequest(purchaseRequest);
        switch (result) {
            default:
                return "";
        }
    }

    private String NoAction() {
        return "/forms/purchase-request/add.jsp";
    }
}
