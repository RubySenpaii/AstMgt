/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import objects.PurchaseOrder;
import services.PurchaseOrderService;

/**
 *
 * @author RubySenpaii
 */
public class PurchaseOrderServlet extends BaseServlet {

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Add":
                    url = "/forms/purchase-order/add.jsp";
                    break;
                case "Submit":
                    url = AddPurchaseOrder(request);
                    break;
                case "Edit":
                    url = EditPurchaseOrder();
                    break;
                case "View":
                case "Flag":
                case "List":
                default:
                    url = ListPurchaseOrder(request);
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }

    private String ListPurchaseOrder(HttpServletRequest request) throws SQLException {
        PurchaseOrderService poDB = new PurchaseOrderService();
        ArrayList<PurchaseOrder> poList = new ArrayList<>();
        poList = poDB.FindAllPurchaseOrder();
        System.out.println("SIZE" + poList.size());
        HttpSession session = request.getSession();
        session.setAttribute("PO", poList);
        return "/forms/purchase-order/list.jsp";
    }

    private String AddPurchaseOrder(HttpServletRequest request) throws ParseException {
        PurchaseOrder po = new PurchaseOrder();
        PurchaseOrderService poDB = new PurchaseOrderService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        po.ApprovedDate = sdf.parse(request.getParameter("appdate"));
        po.OrderDate = sdf.parse(request.getParameter("odate"));
        po.DeliveryDate = sdf.parse(request.getParameter("deldate"));
        po.ConformeDate = sdf.parse(request.getParameter("condate"));
        po.ORSDate = sdf.parse(request.getParameter("orsdate"));
        po.ReceivedDate = sdf.parse(request.getParameter("recdate"));
        po.AuthorizedBy = Integer.parseInt(request.getParameter("autby"));
        po.ApprovedBy = Integer.parseInt(request.getParameter("appby"));
        po.PurchaseOrderId = Integer.parseInt(request.getParameter("pro"));
        po.PurchaseRequestId = Integer.parseInt(request.getParameter("pri"));
        po.ReceivedBy = Integer.parseInt(request.getParameter("recby"));
        po.SupplierId = Integer.parseInt(request.getParameter("sid"));
        po.PurchaseOrderNumber = request.getParameter("pono");
        po.ModeOfProcurement = request.getParameter("mop");
        po.Remarks = request.getParameter("remarks");
        po.DeliveryAddress = request.getParameter("deladd");
        po.DeliveryTerms = request.getParameter("delterms");
        po.ORSNumber = request.getParameter("orsno");
        po.PaymentTerm = request.getParameter("payterms");
        po.ConformeSupplier = request.getParameter("consupp");
        int result = poDB.AddNewPurchaseOrder(po);
        switch (result) {
            case 0:
                return "/forms/login.jsp";
            case 1:
                return "/forms/purchase-order/add.jsp";
            default:
                return "/forms/login.jsp";
        }
    }

    private String EditPurchaseOrder() {
        int result = 0;
        switch (result) {
            default:
                return "";
        }
    }
}
