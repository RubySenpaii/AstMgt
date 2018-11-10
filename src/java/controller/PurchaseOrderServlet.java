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
import objects.Employee;
import objects.PurchaseOrder;
import objects.PurchaseRequest;
import objects.Supplier;
import services.PurchaseOrderService;
import services.PurchaseRequestService;
import services.SupplierService;

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
                    SupplierService suppDB = new SupplierService();
                    PurchaseRequestService prDB = new PurchaseRequestService();
                    ArrayList<Supplier> supplierList = suppDB.FindAllSupplier();
                    HttpSession session = request.getSession();
                    Employee e = (Employee) session.getAttribute("employee");
                    int prid = (int) session.getAttribute("id");
                    Date approved = new java.sql.Date(System.currentTimeMillis());
                    System.out.println("Approving purchase request");
                    int approval = prDB.ApprovePurchaseRequest(e.EmployeeId, approved, prid);
                    if (approval == 0) {
                        System.out.println("Number :  " + approval);
                        url = "/forms/purchase-request/list.jsp";
                        break;
                    }
                    System.out.println("found a list of supplier: " + supplierList.size());
                    session.setAttribute("supplier", supplierList);
                    url = "/forms/purchase-order/add.jsp";
                    break;
                case "Submit":
                    url = AddPurchaseOrder(request);
                    break;
                case "Edit":
                    url = EditPurchaseOrder();
                    break;
                case "View":
                    url = ViewPurchaseOrder(request);
                    break;
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
        System.out.println("purchase order size: " + poList.size());
        HttpSession session = request.getSession();
        session.setAttribute("PO", poList);
        return "/forms/purchase-order/list.jsp";
    }

    private String AddPurchaseOrder(HttpServletRequest request) throws ParseException, SQLException {
        PurchaseOrder po = new PurchaseOrder();
        HttpSession session = request.getSession();
        PurchaseOrderService poDB = new PurchaseOrderService();
        SupplierService suppDB = new SupplierService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        po.OrderDate = new Date(System.currentTimeMillis());
        po.DeliveryDate = sdf.parse(request.getParameter("deldate"));
        po.ConformeDate = sdf.parse(request.getParameter("condate"));
        po.ORSDate = sdf.parse(request.getParameter("orsdate"));
        po.PurchaseOrderId = poDB.FindAllPurchaseOrder().size() + 1;
        PurchaseRequest pr = (PurchaseRequest) session.getAttribute("purchaseRequest");
        po.PurchaseRequestId = pr.PurchaseRequestId;
        Supplier supp = suppDB.FindSupplierByName(request.getParameter("supplier"));
        po.SupplierId = supp.SupplierId;
        po.PurchaseOrderNumber = "PO" + "-" + po.PurchaseOrderId;
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

    private String ViewPurchaseOrder(HttpServletRequest request) {
        PurchaseOrderService poService = new PurchaseOrderService();
        HttpSession session = request.getSession();
        PurchaseOrder purchaseOrder = poService.FindPurchaseOrderById(Integer.parseInt(request.getParameter("poId")));
        session.setAttribute("purchaseOrder", purchaseOrder);
        System.out.println("viewing purchase order: " + purchaseOrder.PurchaseOrderId);
        return "/forms/purchase-order/view.jsp";
    }
}
