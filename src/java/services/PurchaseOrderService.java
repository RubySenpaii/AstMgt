/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Employee;
import objects.PurchaseOrder;

/**
 *
 * @author Bryll Joey Delfin
 */
public class PurchaseOrderService {

    private String AddPurchaseOrderQuery = "INSERT INTO PurchaseOrder(" + PurchaseOrder.COLUMN_PURCHASE_ORDER_ID + "," + PurchaseOrder.COLUMN_PURCHASE_ORDER_NO + ","
            + PurchaseOrder.COLUMN_ORDER_DATE + "," + PurchaseOrder.COLUMN_REMARKS + ","
            + PurchaseOrder.COLUMN_DELIVERY_ADDRESS + "," + PurchaseOrder.COLUMN_DELIVERY_DATE + "," + PurchaseOrder.COLUMN_DELIVERY_TERMS + ","
            + PurchaseOrder.COLUMN_PAYMENT_TERMS + "," + PurchaseOrder.COLUMN_CONFORME_SUPPLIER + ","
            + PurchaseOrder.COLUMN_ORS_NUMBER + "," + PurchaseOrder.COLUMN_ORS_DATE + "," + PurchaseOrder.COLUMN_PURCHASE_REQUEST_ID + ","
            + PurchaseOrder.COLUMN_SUPPLIER_ID + ") Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String CheckPurchaseOrderwithPurchaseRequestIdQuery = "SELECT * FROM PurchaseOrder WHERE " + PurchaseOrder.COLUMN_PURCHASE_REQUEST_ID + " = ? ;";
    private String FindPurchaseOrderIdQuery = "SELECT * FROM PurchaseOrder WHERE " + PurchaseOrder.COLUMN_PURCHASE_ORDER_ID + " = ?";
    private String FindAllPurchaseOrderQ = "SELECT * FROM PurchaseOrder  ORDER BY "+ PurchaseOrder.COLUMN_PURCHASE_ORDER_NO+" ASC ;";

    public int AddNewPurchaseOrder(PurchaseOrder po) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(AddPurchaseOrderQuery);
            ps.setInt(1, po.PurchaseOrderId);
            ps.setString(2, po.PurchaseOrderNumber);
            ps.setObject(3, po.OrderDate);
            ps.setString(4, po.Remarks);
            ps.setString(5, "Department of Agrarian Reform - Central Office");
            ps.setObject(6, po.DeliveryDate);
            ps.setString(7, po.DeliveryTerms);
            ps.setString(8, po.PaymentTerm);
            ps.setString(9, po.ConformeSupplier);
            ps.setString(10, po.ORSNumber);
            ps.setObject(11, po.OrderDate);
            ps.setInt(12, po.PurchaseRequestId);
            ps.setInt(13, po.SupplierId);
            int res = ps.executeUpdate();
            ps.close();
            conn.close();
            return res;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }
    
    public int UpdatePurchaseOrder(PurchaseOrder purchaseOrder) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "UPDATE PurchaseOrder SET " + PurchaseOrder.COLUMN_ORS_DATE + " = ?,  " + PurchaseOrder.COLUMN_ORS_NUMBER + " = ? "
                    + "WHERE " + PurchaseOrder.COLUMN_PURCHASE_ORDER_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setObject(1, purchaseOrder.ORSDate);
            ps.setString(2, purchaseOrder.ORSNumber);
            ps.setInt(3, purchaseOrder.PurchaseOrderId);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public PurchaseOrder FindPurchaseOrderByPurchaseRequestId(int prId) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(CheckPurchaseOrderwithPurchaseRequestIdQuery);
            ps.setInt(1, prId);
            ArrayList<PurchaseOrder> elist = getResult(ps.executeQuery());
            ps.close();
            conn.close();
            return elist.get(0);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public PurchaseOrder FindPurchaseOrderById(int poId) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindPurchaseOrderIdQuery);
            ps.setInt(1, poId);
            ArrayList<PurchaseOrder> elist = getResult(ps.executeQuery());
            ps.close();
            conn.close();
            return elist.get(0);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<PurchaseOrder> FindAllPurchaseOrder() throws SQLException {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(FindAllPurchaseOrderQ);
            ArrayList<PurchaseOrder> elist = getResult(ps.executeQuery());
            ps.close();
            conn.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    
    public ArrayList<PurchaseOrder> getPurchaseOrderExpectingDelivery() {
        ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<>();
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM PurchaseOrder WHERE DATEDIFF(NOW(), DeliveryDate) < 7");
            purchaseOrders = getResult(ps.executeQuery());
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return purchaseOrders;
    }

    private ArrayList<PurchaseOrder> getResult(ResultSet rs) throws SQLException {
        ArrayList<PurchaseOrder> purchaserequestList = new ArrayList<PurchaseOrder>();
        while (rs.next()) {
            PurchaseOrder e = new PurchaseOrder();
            e.PurchaseOrderId = rs.getInt(PurchaseOrder.COLUMN_PURCHASE_ORDER_ID);
            e.PurchaseRequestId = rs.getInt(PurchaseOrder.COLUMN_PURCHASE_REQUEST_ID);
            e.PurchaseOrderNumber = rs.getString(PurchaseOrder.COLUMN_PURCHASE_ORDER_NO);
            e.SupplierId = rs.getInt(PurchaseOrder.COLUMN_SUPPLIER_ID);
            e.OrderDate = rs.getDate(PurchaseOrder.COLUMN_ORDER_DATE);
            e.Remarks = rs.getString(PurchaseOrder.COLUMN_REMARKS);
            e.DeliveryAddress = rs.getString(PurchaseOrder.COLUMN_DELIVERY_ADDRESS);
            e.DeliveryDate = rs.getDate(PurchaseOrder.COLUMN_DELIVERY_DATE);
            e.DeliveryTerms = rs.getString(PurchaseOrder.COLUMN_DELIVERY_TERMS);
            e.PaymentTerm = rs.getString(PurchaseOrder.COLUMN_PAYMENT_TERMS);
            e.ConformeSupplier = rs.getString(PurchaseOrder.COLUMN_CONFORME_SUPPLIER);
            e.ApprovedBy = rs.getInt(PurchaseOrder.COLUMN_APPROVED_BY);
            e.ApprovedDate = rs.getDate(PurchaseOrder.COLUMN_APPROVED_DATE);
            e.ORSNumber = rs.getString(PurchaseOrder.COLUMN_ORS_NUMBER);
            e.ORSDate = rs.getDate(PurchaseOrder.COLUMN_ORS_DATE);

            e.Supplier = new SupplierService().FindSupplierById(e.SupplierId);
            e.PurchaseRequest = new PurchaseRequestService().FindPurhcaseRequesById(e.PurchaseRequestId);
            purchaserequestList.add(e);
        }
        rs.close();
        return purchaserequestList;
    }

}
