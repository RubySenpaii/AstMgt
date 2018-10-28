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
            + PurchaseOrder.COLUMN_ORDER_DATE + "," + PurchaseOrder.COLUMN_MODE_OF_PROCUREMENT + "," + PurchaseOrder.COLUMN_REMARKS + ","
            + PurchaseOrder.COLUMN_DELIVERY_ADDRESS + "," + PurchaseOrder.COLUMN_DELIVERY_DATE + "," + PurchaseOrder.COLUMN_DELIVERY_TERMS + ","
            + PurchaseOrder.COLUMN_PAYMENT_TERMS + "," + PurchaseOrder.COLUMN_CONFORME_SUPPLIER + "," + PurchaseOrder.COLUMN_CONFORME_DATE + ","
            + PurchaseOrder.COLUMN_ORS_NUMBER + "," + PurchaseOrder.COLUMN_ORS_DATE + "," + PurchaseOrder.COLUMN_PURCHASE_REQUEST_ID + ","
            + PurchaseOrder.COLUMN_SUPPLIER_ID + ") Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private String FindPurchaseOrderIdQuery = "SELECT * FROM PurchaseOrder WHERE " + PurchaseOrder.COLUMN_PURCHASE_ORDER_ID + " = ?";
    private String FindAllPurchaseOrderQ = "SELECT * FROM PurchaseOrder ;";

    public int AddNewPurchaseOrder(PurchaseOrder po) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(AddPurchaseOrderQuery);
            ps.setInt(1, po.PurchaseOrderId);
            ps.setString(2, po.PurchaseOrderNumber);
            ps.setObject(3, po.OrderDate);
            ps.setString(4, po.ModeOfProcurement);
            ps.setString(5, po.Remarks);
            ps.setString(6, po.DeliveryAddress);
            ps.setObject(7, po.DeliveryDate);
            ps.setString(8, po.DeliveryTerms);
            ps.setString(9, po.PaymentTerm);
            ps.setString(10, po.ConformeSupplier);
            ps.setObject(11, po.ConformeDate);
            ps.setString(12, po.ORSNumber);
            ps.setObject(13, po.OrderDate);
            ps.setInt(14, po.PurchaseRequestId);
            ps.setInt(15, po.SupplierId);
            int res = ps.executeUpdate();
            ps.close();
            conn.close();
            return res;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    public PurchaseOrder FindPurchaseOrderByNo(int prno) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindPurchaseOrderIdQuery);
            ps.setInt(1, prno);
            ArrayList<PurchaseOrder> elist = getResult(ps.executeQuery());
            ps.close();
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
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
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
            e.ModeOfProcurement = rs.getString(PurchaseOrder.COLUMN_MODE_OF_PROCUREMENT);
            e.Remarks = rs.getString(PurchaseOrder.COLUMN_REMARKS);
            e.DeliveryAddress = rs.getString(PurchaseOrder.COLUMN_DELIVERY_ADDRESS);
            e.DeliveryDate = rs.getDate(PurchaseOrder.COLUMN_DELIVERY_DATE);
            e.DeliveryTerms = rs.getString(PurchaseOrder.COLUMN_DELIVERY_TERMS);
            e.PaymentTerm = rs.getString(PurchaseOrder.COLUMN_PAYMENT_TERMS);
            e.ConformeSupplier = rs.getString(PurchaseOrder.COLUMN_CONFORME_SUPPLIER);
            e.ConformeDate = rs.getDate(PurchaseOrder.COLUMN_CONFORME_DATE);
            e.ApprovedBy = rs.getInt(PurchaseOrder.COLUMN_APPROVED_BY);
            e.ApprovedDate = rs.getDate(PurchaseOrder.COLUMN_APPROVED_DATE);
            e.ORSNumber = rs.getString(PurchaseOrder.COLUMN_ORS_NUMBER);
            e.ORSDate = rs.getDate(PurchaseOrder.COLUMN_ORS_DATE);
            purchaserequestList.add(e);
        }
        rs.close();
        return purchaserequestList;
    }

}
