/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import objects.Supplier;
import services.AssetService;
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
                case "Submit":
                    url = SubmitSupplier(request);
                    break;
                case "List":
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
        supplier.SupplierId = supplierService.FindAllSupplier().size()+1;
        supplier.SupplierName = request.getParameter("name");
        supplier.SupplierAddress = request.getParameter("address");
        supplier.ContactNumber = request.getParameter("contactno");
        supplier.ContactPerson = request.getParameter("contactperson");
        supplier.SupplierType = request.getParameter("supptype");
        supplier.SupplierTIN = request.getParameter("supptin");
        int result = supplierService.AddNewSupplier(supplier);
        if (result == 1) {
            System.out.println("Here i am");
            return "/SupplierServlet/List";
        } else {
            return "/SupplierServlet/Add";
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
