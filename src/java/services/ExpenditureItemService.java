/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnectionFactory;
import extra.SharedFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import objects.ExpenditureItem;

/**
 *
 * @author rubysenpaii
 */
public class ExpenditureItemService {
    
    public int AddExpenditureItem(ExpenditureItem expenditureItem) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "INSERT INTO ExpenditureItem (" + ExpenditureItem.COLUMN_ASSET_ID + ", " + ExpenditureItem.COLUMN_DIVISION
                    + ", " + ExpenditureItem.COLUMN_QUANTITY_LIMIT + ", " + ExpenditureItem.COLUMN_QUANTITY_ORDERED
                    + ", " + ExpenditureItem.COLUMN_QUARTER + ", " + ExpenditureItem.COLUMN_YEAR + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, expenditureItem.AssetId);
            ps.setString(2, expenditureItem.Division);
            ps.setInt(3, expenditureItem.QuantityLimit);
            ps.setInt(4, expenditureItem.QuantityOrdered);
            ps.setString(5, expenditureItem.Quarter);
            ps.setInt(6, expenditureItem.Year);
            
            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public int AddExpenditureItems(ArrayList<ExpenditureItem> expenditureItems) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            con.setAutoCommit(false);
            
            String query = "INSERT INTO ExpenditureItem (" + ExpenditureItem.COLUMN_ASSET_ID + ", " + ExpenditureItem.COLUMN_DIVISION
                    + ", " + ExpenditureItem.COLUMN_QUANTITY_LIMIT + ", " + ExpenditureItem.COLUMN_QUANTITY_ORDERED
                    + ", " + ExpenditureItem.COLUMN_QUARTER + ", " + ExpenditureItem.COLUMN_YEAR + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            for (ExpenditureItem expenditureItem : expenditureItems) {
                ps.setInt(1, expenditureItem.AssetId);
                ps.setString(2, expenditureItem.Division);
                ps.setInt(3, expenditureItem.QuantityLimit);
                ps.setInt(4, expenditureItem.QuantityOrdered);
                ps.setString(5, expenditureItem.Quarter);
                ps.setInt(6, expenditureItem.Year);
                ps.addBatch();
            }
            
            int[] result = ps.executeBatch();
            con.commit();
            ps.close();
            con.close();
            return result.length;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public int UpdateExpenditureItem(ExpenditureItem expenditureItem) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "UPDATE expenditureitem SET " + ExpenditureItem.COLUMN_QUANTITY_ORDERED + " = ? , " + ExpenditureItem.COLUMN_QUANTITY_LIMIT + " = ? "
                    + "WHERE " + ExpenditureItem.COLUMN_ASSET_ID + " = ? AND " + ExpenditureItem.COLUMN_DIVISION + " = ? AND "
                    + ExpenditureItem.COLUMN_QUARTER + " = ? AND " + ExpenditureItem.COLUMN_YEAR + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, expenditureItem.QuantityOrdered);
            ps.setInt(2, expenditureItem.QuantityLimit);
            ps.setInt(3, expenditureItem.AssetId);
            ps.setString(4, expenditureItem.Division);
            ps.setString(5, expenditureItem.Quarter);
            ps.setInt(6, expenditureItem.Year);
            
            int result = ps.executeUpdate();
            con.close();
            ps.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public ArrayList<ExpenditureItem> GetExpenditureItems() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM ExpenditureItem";
            PreparedStatement ps = con.prepareStatement(query);
            
            ArrayList<ExpenditureItem> expenditureItems = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return expenditureItems;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }
    
    public ArrayList<ExpenditureItem> GetCurrentExpenditureItems() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT * FROM ExpenditureItem WHERE Year = ? AND Quarter = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Calendar.getInstance().get(Calendar.YEAR));
            ps.setString(2, SharedFormat.getQuarter());
            
            ArrayList<ExpenditureItem> expenditureItems = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return expenditureItems;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }
    
    public ExpenditureItem GetExpenditureItemsByDivision(String division , int assetid , String quarter ,int year) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            String query = "SELECT * FROM ExpenditureItem WHERE Division = ? and AssetId = ? and Quarter = ? and Year = ? ";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, division);
            ps.setInt(2, assetid);
            ps.setString(3, quarter);
            ps.setInt(4, year);
            ExpenditureItem  es = new ExpenditureItem();
            ArrayList<ExpenditureItem> expenditureItems = getResult(ps.executeQuery());
            ps.close();
            con.close();
            es = expenditureItems.get(0);
            return es;
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        }
    }
    
    
    private ArrayList<ExpenditureItem> getResult(ResultSet rs) throws SQLException {
        ArrayList<ExpenditureItem> expenditureItems = new ArrayList<>();
        while (rs.next()) {
            ExpenditureItem expenditureItem = new ExpenditureItem();
            expenditureItem.AssetId = rs.getInt(ExpenditureItem.COLUMN_ASSET_ID);
            expenditureItem.Division = rs.getString(ExpenditureItem.COLUMN_DIVISION);
            expenditureItem.QuantityLimit = rs.getInt(ExpenditureItem.COLUMN_QUANTITY_LIMIT);
            expenditureItem.QuantityOrdered = rs.getInt(ExpenditureItem.COLUMN_QUANTITY_ORDERED);
            expenditureItem.Quarter = rs.getString(ExpenditureItem.COLUMN_QUARTER);
            expenditureItem.Year = rs.getInt(ExpenditureItem.COLUMN_YEAR);
            expenditureItems.add(expenditureItem);
        }
        rs.close();
        return expenditureItems;
    }
}
