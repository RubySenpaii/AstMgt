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
import javax.servlet.http.HttpSession;
import objects.Equipment;
import objects.Supplies;
import services.EquipmentService;
import services.PurchaseOrderService;
import services.SuppliesService;

/**
 *
 * @author RubySenpaii
 */
public class InventoryServlet extends BaseServlet {

    private EquipmentService equipmentService;
    private SuppliesService suppliesService;
    private PurchaseOrderService poService;

    @Override
    public void servletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getRequestURI();
        System.out.println("action used " + action);
        try {
            String url;
            switch (action.split("/")[action.split("/").length - 1]) {
                case "AcknowledgementRequest":
                    url = "inventory/acknowledgement.jsp";
                    break;
                case "Acknowledge":
                case "SuppliesList":
                    url = ListSupplies(request);
                    break;
                case "SuppliesView":
                case "EquipmentView":
                case "EquipmentList":
                default:
                    url = ListEquipment(request);
                    break;
            }
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception x) {
            throw new ServletException(x);
        }
    }

    private String Acknowledge(HttpServletRequest request) {
        return "";
    }

    private String ListEquipment(HttpServletRequest request) {
        equipmentService = new EquipmentService();
        ArrayList<Equipment> equipments = equipmentService.GetListOfEquipments();
        HttpSession session = request.getSession();
        session.setAttribute("equipments", equipments);
        return "inventory/equipment-list.jsp";
    }

    private String ListSupplies(HttpServletRequest request) {
        suppliesService = new SuppliesService();
        ArrayList<Supplies> supplies = suppliesService.FindAllSupplies();
        HttpSession session = request.getSession();
        session.setAttribute("supplies", supplies);
        return "inventory/supplies-list.jsp";
    }
}
