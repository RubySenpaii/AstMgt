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
import objects.PropertyAcknowledgementReceipt;

/**
 *
 * @author RubySenpaii
 */
public class PropertyAcknowledgementReceiptService {

    public int AddPropertyAcknowledgementReceipt(PropertyAcknowledgementReceipt receipt) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "INSERT INTO PropertyAcknowledgementReceipt(" + PropertyAcknowledgementReceipt.COLUMN_ACKNOWLEDGEMENT_RECEIPT_ID + ", "
                    + PropertyAcknowledgementReceipt.COLUMN_ISSUED_BY + ", " + PropertyAcknowledgementReceipt.COLUMN_ISSUED_DATE + ", "
                    + PropertyAcknowledgementReceipt.COLUMN_PAR_NO + ", " + PropertyAcknowledgementReceipt.COLUMN_REQUESTED_BY + ", " 
                    + PropertyAcknowledgementReceipt.COLUMN_REQUESTED_DATE + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, receipt.AcknowledgementReceiptId);
            ps.setInt(2, receipt.AcknowledgementReceiptId);
            ps.setObject(3, receipt.IssuedDate);
            ps.setString(4, receipt.PARNo);
            ps.setInt(5, receipt.RequestedBy);
            ps.setObject(6, receipt.RequestedDate);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public int UpdatePropertyAcknowledgeReceipt(PropertyAcknowledgementReceipt receipt) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "UPDATE PropertyAcknowledgementReceipt SET " + PropertyAcknowledgementReceipt.COLUMN_ISSUED_BY + " = ?, "
                    + PropertyAcknowledgementReceipt.COLUMN_ISSUED_DATE + " = ?, " + PropertyAcknowledgementReceipt.COLUMN_PAR_NO + " = ?, "
                    + PropertyAcknowledgementReceipt.COLUMN_REQUESTED_BY + " = ?, " + PropertyAcknowledgementReceipt.COLUMN_REQUESTED_DATE + " = ? "
                    + "WHERE " + PropertyAcknowledgementReceipt.COLUMN_ACKNOWLEDGEMENT_RECEIPT_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, receipt.IssuedBy);
            ps.setObject(2, receipt.IssuedDate);
            ps.setString(3, receipt.PARNo);
            ps.setInt(4, receipt.RequestedBy);
            ps.setObject(5, receipt.RequestedDate);
            ps.setInt(6, receipt.AcknowledgementReceiptId);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch(SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public ArrayList<PropertyAcknowledgementReceipt> GetPropertyAcknowledgementReceipts() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM PropertyAcknowledgementReceipt";
            PreparedStatement ps = con.prepareStatement(query);
            
            ArrayList<PropertyAcknowledgementReceipt> receipts = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return receipts;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }
    
    public PropertyAcknowledgementReceipt GetPropertyAcknowledgementReceipt(int receiptId) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM PropertyAcknowledgementReceipt WHERE "
                    + PropertyAcknowledgementReceipt.COLUMN_ACKNOWLEDGEMENT_RECEIPT_ID + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            PropertyAcknowledgementReceipt receipt = getResult(ps.executeQuery()).get(0);
            ps.close();
            con.close();
            return receipt;
        } catch (SQLException x) {
            System.err.println(x);
            return new PropertyAcknowledgementReceipt();
        }
    }
    
    private ArrayList<PropertyAcknowledgementReceipt> getResult(ResultSet rs) throws SQLException {
        ArrayList<PropertyAcknowledgementReceipt> receipts = new ArrayList<>();
        while (rs.next()) {
            PropertyAcknowledgementReceipt receipt = new PropertyAcknowledgementReceipt();
            receipt.AcknowledgementReceiptId = rs.getInt(PropertyAcknowledgementReceipt.COLUMN_ACKNOWLEDGEMENT_RECEIPT_ID);
            receipt.IssuedBy = rs.getInt(PropertyAcknowledgementReceipt.COLUMN_ISSUED_BY);
            receipt.IssuedDate = rs.getDate(PropertyAcknowledgementReceipt.COLUMN_ISSUED_DATE);
            receipt.PARNo = rs.getString(PropertyAcknowledgementReceipt.COLUMN_PAR_NO);
            receipt.RequestedBy = rs.getInt(PropertyAcknowledgementReceipt.COLUMN_REQUESTED_BY);
            receipt.RequestedDate = rs.getDate(PropertyAcknowledgementReceipt.COLUMN_REQUESTED_DATE);
            receipts.add(receipt);
        }
        rs.close();
        return receipts;
    }
}
