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
import objects.RequestForDeliveryInspection;

/**
 *
 * @author RubySenpaii
 */
public class RequestForDeliveryInspectionService {
    
    public int AddRequestForDeliveryInspection(RequestForDeliveryInspection deliveryRequest) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "INSERT INTO RequestForDeliveryInspection (" + RequestForDeliveryInspection.COLUMN_CREATED_BY + " = ?, "
                    + RequestForDeliveryInspection.COLUMN_CREATED_DATE + " = ?, " + RequestForDeliveryInspection.COLUMN_DELIVERY_INSPECTION_ID + " = ?, "
                    + RequestForDeliveryInspection.COLUMN_DELIVERY_RECEIPT + " = ?, " + RequestForDeliveryInspection.COLUMN_FROM_BIDDING + " = ?, "
                    + RequestForDeliveryInspection.COLUMN_INVOICE + " = ?, " + RequestForDeliveryInspection.COLUMN_MANAGEMENT_REMARKS + " = ?, " 
                    + RequestForDeliveryInspection.COLUMN_PURCHASE_ORDER_ID + " = ?, " + RequestForDeliveryInspection.COLUMN_REMARKS + " = ? "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, deliveryRequest.CreatedBy);
            ps.setObject(2, deliveryRequest.CreatedDate);
            ps.setInt(3, deliveryRequest.DeliveryInspectionId);
            ps.setString(4, deliveryRequest.DeliveryReceipt);
            ps.setInt(5, deliveryRequest.FromBidding);
            ps.setString(6, deliveryRequest.Invoice);
            ps.setString(7, deliveryRequest.ManagementRemarks);
            ps.setInt(8, deliveryRequest.PurchaseOrderId);
            ps.setString(9, deliveryRequest.Remarks);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public int UpdateRequestForDeliveryInspection(RequestForDeliveryInspection deliveryReceipt) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "UPDATE RequestForDeliveryInspection SET " + RequestForDeliveryInspection.COLUMN_CREATED_BY + " = ?, "
                    + RequestForDeliveryInspection.COLUMN_CREATED_DATE + " = ?, " + RequestForDeliveryInspection.COLUMN_DELIVERY_RECEIPT + " = ?, "
                    + RequestForDeliveryInspection.COLUMN_FROM_BIDDING + " = ?, " + RequestForDeliveryInspection.COLUMN_INVOICE + " = ?, "
                    + RequestForDeliveryInspection.COLUMN_MANAGEMENT_REMARKS + " = ?, " + RequestForDeliveryInspection.COLUMN_PURCHASE_ORDER_ID + " = ?, "
                    + RequestForDeliveryInspection.COLUMN_REMARKS + " = ? "
                    + "WHERE " + RequestForDeliveryInspection.COLUMN_DELIVERY_INSPECTION_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, deliveryReceipt.CreatedBy);
            ps.setObject(2, deliveryReceipt.CreatedDate);
            ps.setString(3, deliveryReceipt.DeliveryReceipt);
            ps.setInt(4, deliveryReceipt.FromBidding);
            ps.setString(5, deliveryReceipt.Invoice);
            ps.setString(6, deliveryReceipt.ManagementRemarks);
            ps.setInt(7, deliveryReceipt.PurchaseOrderId);
            ps.setString(8, deliveryReceipt.Remarks);
            ps.setInt(9, deliveryReceipt.DeliveryInspectionId);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public ArrayList<RequestForDeliveryInspection> GetRequestsForDeliveryInspection() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM RequestForDeliveryInspection";
            PreparedStatement ps = con.prepareStatement(query);
            
            ArrayList<RequestForDeliveryInspection> deliveryRequests = GetResult(ps.executeQuery());
            ps.close();
            con.close();
            return deliveryRequests;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }
    
    public RequestForDeliveryInspection GetRequestForDeliveryInspection(int deliveryInspectionId) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM RequestForDeliveryInspection "
                    + "WHERE " + RequestForDeliveryInspection.COLUMN_DELIVERY_INSPECTION_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, deliveryInspectionId);
            
            RequestForDeliveryInspection deliveryRequest = GetResult(ps.executeQuery()).get(0);
            ps.close();
            con.close();
            return deliveryRequest;
        } catch (SQLException x) {
            System.err.println(x);
            return new RequestForDeliveryInspection();
        }
    }
     
    private ArrayList<RequestForDeliveryInspection> GetResult(ResultSet rs) throws SQLException {
        ArrayList<RequestForDeliveryInspection> deliveryRequests = new ArrayList<>();
        while(rs.next()) {
            RequestForDeliveryInspection deliveryRequest = new RequestForDeliveryInspection();
            deliveryRequest.CreatedBy = rs.getInt(RequestForDeliveryInspection.COLUMN_CREATED_BY);
            deliveryRequest.CreatedDate = rs.getDate(RequestForDeliveryInspection.COLUMN_CREATED_DATE);
            deliveryRequest.DeliveryInspectionId = rs.getInt(RequestForDeliveryInspection.COLUMN_DELIVERY_INSPECTION_ID);
            deliveryRequest.DeliveryReceipt = rs.getString(RequestForDeliveryInspection.COLUMN_DELIVERY_RECEIPT);
            deliveryRequest.FromBidding = rs.getInt(RequestForDeliveryInspection.COLUMN_FROM_BIDDING);
            deliveryRequest.Invoice = rs.getString(RequestForDeliveryInspection.COLUMN_INVOICE);
            deliveryRequest.ManagementRemarks = rs.getString(RequestForDeliveryInspection.COLUMN_MANAGEMENT_REMARKS);
            deliveryRequest.PurchaseOrderId = rs.getInt(RequestForDeliveryInspection.COLUMN_PURCHASE_ORDER_ID);
            deliveryRequest.Remarks = rs.getString(RequestForDeliveryInspection.COLUMN_REMARKS);
            deliveryRequests.add(deliveryRequest);
        }
        rs.close();
        return deliveryRequests;
    }
}