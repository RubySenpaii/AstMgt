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
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.AssetRequested;
import objects.Employee;
import objects.ExpenditureTracking;
import objects.PurchaseOrder;
import objects.PurchaseRequest;
import objects.Supplier;
import services.AssetRequestedService;
import services.AssetService;
import services.ExpenditureTrackingService;
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
                    url = ApproveAndAddPurchaseOrder(request);
                    break;
                case "GoToPO":
                    url = CreatePurchaseOrder(request);
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
    
    private String ApproveAndAddPurchaseOrder(HttpServletRequest request) throws ServletException {
        SupplierService suppDB = new SupplierService();
        PurchaseRequestService prDB = new PurchaseRequestService();
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("user");
        int prid = Integer.parseInt(request.getParameter("prid"));
        Date approved = new java.sql.Date(System.currentTimeMillis());
        try {
            System.out.println("Approving purchase request" + employee.FullName());
            System.out.println("Approving purchase request" + approved);
            System.out.println("Approving purchase request" + prid);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        
        int approval = prDB.ApprovePurchaseRequest(employee.EmployeeId, approved, prid);
        if (approval == 0) {
            System.out.println("Number :  " + approval);
            return "/forms/purchase-request/list.jsp";
        }
        
        ArrayList<AssetRequested> assetsRequested = new AssetRequestedService().GetAssetsRequestedWithPurchaseRequest(prid);
        double equipTotal = 0, suppliesTotal = 0;
        for (AssetRequested assetRequested: assetsRequested) {
            if (assetRequested.Asset.AssetType.contains("Equipment")) {
                equipTotal += assetRequested.Quantity * assetRequested.UnitCost;
            } else {
                suppliesTotal += assetRequested.Quantity * assetRequested.UnitCost;
            }
        }
        System.out.println("expenditure for pr supplies " + suppliesTotal + " equipment " + equipTotal);
        
        ExpenditureTrackingService expenditureTrackingService = new ExpenditureTrackingService();
        ExpenditureTracking expenditure = expenditureTrackingService.GetCurrentExpenditure(employee.Division);
        expenditure.Timestamp = Calendar.getInstance().getTime();
        expenditure.Equipment -= equipTotal;
        expenditure.Supplies -=  suppliesTotal;
        int result = expenditureTrackingService.AddEquipmentTracking(expenditure);
        ArrayList<Supplier> supplierList = suppDB.FindSupplierByType(assetsRequested.get(0).Asset.AssetType);
        System.out.println("found a list of supplier: " + supplierList.size());
        System.out.println("upadte tracking success: " + result);
        session.setAttribute("limit", expenditure);
        session.setAttribute("supplierList", supplierList);
        return "/forms/purchase-order/add.jsp";
    }
    
    private String CreatePurchaseOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int purchaseRequestId = Integer.parseInt(request.getParameter("prid"));
        ArrayList<AssetRequested> assetsRequested = new AssetRequestedService().GetAssetsRequestedWithPurchaseRequest(purchaseRequestId);
        ArrayList<Supplier> supplierList = new SupplierService().FindSupplierByType(assetsRequested.get(0).Asset.AssetType);
        System.out.println("found a list of supplier: " + supplierList.size());
        session.setAttribute("supplierList", supplierList);
        return "/forms/purchase-order/add.jsp";
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
        AssetRequestedService assetReqDB = new AssetRequestedService();
        AssetService assetDB = new AssetService();
        ArrayList<AssetRequested> assetReqList = assetReqDB.GetAssetsRequestedWithPurchaseRequest(purchaseOrder.PurchaseRequestId);
        ArrayList<String> assetNameList = new ArrayList<>();
        for (int i = 0; i < assetReqList.size(); i++) {
            String name = assetDB.GetAsset(assetReqList.get(i).AssetId).AssetName;
            System.out.println("NAMES are : " + name);
            assetNameList.add(name);
        }
        
        session.setAttribute("assetRequested", assetReqList);
        session.setAttribute("assetNames", assetNameList);
        session.setAttribute("purchaseOrder", purchaseOrder);
        System.out.println("viewing purchase order: " + purchaseOrder.PurchaseOrderId);
        return "/forms/purchase-order/view.jsp";
    }
}
