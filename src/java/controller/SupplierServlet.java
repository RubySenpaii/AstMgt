/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import extra.SharedFormat;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Asset;
import objects.Supplier;
import objects.SupplierItem;
import services.AssetService;
import services.SupplierItemService;
import services.SupplierService;

/**
 *
 * @author RubySenpaii
 */
public class SupplierServlet extends BaseServlet {
    
    private SupplierService supplierService = new SupplierService();
    private AssetService assetService = new AssetService();
    
    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("action used " + action);
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "Add":
                    url = AddSupplier(request);
                    break;
                case "View":
                    url = AddSupplierItem(request);
                    break;
                case "Submit":
                    url = SubmitSupplier(request);
                    break;
                case "AddSupplyItem":
                    url = SubmitSupplierItem(request);
                    break;
                case "UpdateItem":
                    url = UpdateSupplierItem(request);
                    break;
                case "UpdateItems":
                    url = UpdateSupplierItems(request);
                default:
                    url = ListSupplier(request);
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }
    
    private String SubmitSupplier(HttpServletRequest request) {
        Supplier supplier = new Supplier();
        supplier.SupplierId = supplierService.FindAllSupplier().size() + 1;
        supplier.SupplierName = request.getParameter("name");
        supplier.SupplierAddress = request.getParameter("address");
        supplier.ContactNumber = request.getParameter("contactno");
        supplier.ContactPerson = request.getParameter("contactperson");
        supplier.SupplierType = request.getParameter("supptype");
        supplier.SupplierTIN = request.getParameter("supptin");
        int result = supplierService.AddNewSupplier(supplier);
        if (result == 1) {
            return "/SupplierServlet/List";
        } else {
            return "/SupplierServlet/Add";
        }
    }
    
    private String UpdateSupplierItem(HttpServletRequest request) {
        AssetService assetDB = new AssetService();
        ArrayList<Asset> assetList = new ArrayList<Asset>();
        SupplierItemService sitemDB = new SupplierItemService();
        Supplier supplier = supplierService.FindSupplierById(Integer.parseInt(request.getParameter("upsupid")));
        assetList = assetDB.GetAssetsWithType(supplier.SupplierType);
        ArrayList<SupplierItem> sitem = sitemDB.FindSupplierItemById(supplier.SupplierId);
        HttpSession session = request.getSession();
        session.setAttribute("assets", assetList);
        session.setAttribute("supplier", supplier);
        session.setAttribute("supplier items", sitem);
        return "/forms/supplier/updatesupplyitem.jsp";
    }
    
    private String UpdateSupplierItems(HttpServletRequest request) {
//        AssetService assetDB = new AssetService();
        ArrayList<Asset> assetList = new ArrayList<Asset>();
        String[] price = request.getParameterValues("newprice");
        String[] assetid = request.getParameterValues("assetid");
        String[] oldprice = request.getParameterValues("oldprice");
        String suppid = request.getParameter("suppid");
        SharedFormat sf = new SharedFormat();
        for (int i = 0; i < assetid.length; i++) {
            if (price[i].isEmpty()) {
                price[i] = oldprice[i];
                System.out.println("tetetetetet" + price[i]);
            }
        }
        SupplierItemService sitemDB = new SupplierItemService();
        sitemDB.UpdateSupplierItems(Integer.parseInt(suppid), assetid, price);
//        assetList = assetDB.GetAssetsWithType(supplier.SupplierType);
//        ArrayList<SupplierItem> sitem = sitemDB.FindSupplierItemById(supplier.SupplierId);
        HttpSession session = request.getSession();
//        session.setAttribute("assets", assetList);
//        session.setAttribute("supplier", supplier);
//        session.setAttribute("supplier items", sitem);
        return "/forms/supplier/updatesupplyitem.jsp";
    }
    
    private String AddSupplierItem(HttpServletRequest request) {
        AssetService assetDB = new AssetService();
        ArrayList<Asset> assetList = new ArrayList<Asset>();
        SupplierItemService sitemDB = new SupplierItemService();
        Supplier supplier = supplierService.FindSupplierById(Integer.parseInt(request.getParameter("suppid")));
        assetList = assetDB.GetAssetsWithType(supplier.SupplierType);
        ArrayList<SupplierItem> sitem = sitemDB.FindSupplierItemById(supplier.SupplierId);
        HttpSession session = request.getSession();
        session.setAttribute("assets", assetList);
        session.setAttribute("supplier", supplier);
        session.setAttribute("supplier items", sitem);
        return "/forms/supplier/addsupplyitem.jsp";
    }
    
    private String SubmitSupplierItem(HttpServletRequest request) {
        
        SupplierItemService sitemDB = new SupplierItemService();
        String[] assets = request.getParameterValues("assets");
        String[] prices = request.getParameterValues("price");
        HttpSession session = request.getSession();
        Supplier s = (Supplier) session.getAttribute("supplier");
        int checker = 0;
        System.out.println(s);
        for (int i = 0; i < assets.length; i++) {
            SupplierItem sitem = new SupplierItem();
            Asset asset = new Asset();
            asset = assetService.GetAssetByName(assets[i]);
            sitem.AssetId = asset.AssetId;
            sitem.SupplierId = s.SupplierId;
            sitem.price = Integer.parseInt(prices[i]);
            checker = sitemDB.AddNewSupplier(sitem);
        }
        if (checker != 0) {
            System.out.println("PASSED");
            return "/forms/supplier/list.jsp";
        } else {
            System.out.println("FAILED");
            return "/forms/supplier/list.jsp";
        }
    }
    
    private String AddSupplier(HttpServletRequest request) {
        return "/forms/supplier/add.jsp";
    }
    
    private String ListSupplier(HttpServletRequest request) {
        ArrayList<Supplier> slist = new ArrayList<>();
        slist = supplierService.FindAllSupplier();
        HttpSession session = request.getSession();
        session.setAttribute("supplier", slist);
        return "/forms/supplier/list.jsp";
    }
    
}
