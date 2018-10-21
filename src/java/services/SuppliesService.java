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
import objects.Supplies;

/**
 *
 * @author Bryll Joey Delfin
 */
public class SuppliesService {
    private String AddSuppliesQuery = "INSERT INTO Supplies(" + Supplies.COLUMN_ASSET_ID + "," + Supplies.COLUMN_AMOUNT_ACQUIRED + ","
            + Supplies.COLUMN_TIMESTAMP + "," + Supplies.COLUMN_TOTALQUANTITY + ", " + Supplies.COLUMN_AMOUNT_DISPOSED + ")VALUES(?,?,?,?)";

    private String FindSuppliesByIdQuery = "SELECT * FROM Supplies WHERE " + Supplies.COLUMN_ASSET_ID + " = ?";
    private String FindAllSuppliesQuery = "SELECT * FROM Supplies;";
    
    public int AddNewSupplier(Supplies s) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(AddSuppliesQuery);
            ps.setInt(1, s.AssetId);
            ps.setInt(2, s.AmountAcquired);
            ps.setObject(3, s.Timestamp);
            ps.setInt(4, s.TotalQuantity);
            ps.setInt(5, s.AmountDisposed);
            int res = ps.executeUpdate();
            ps.close();
            conn.close();
            return res;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }
    
    public ArrayList<Supplies> FindSuppliesById(int suppno) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindSuppliesByIdQuery);
            ps.setInt(1, suppno);
            ArrayList<Supplies> elist = getResult(ps.executeQuery());
            ps.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }
    
    public ArrayList<Supplies> FindAllSupplier() {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindAllSuppliesQuery);
            ArrayList<Supplies> elist = getResult(ps.executeQuery());
            ps.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    private ArrayList<Supplies> getResult(ResultSet rs) throws SQLException {
        ArrayList<Supplies> purchaserequestList = new ArrayList<Supplies>();
        while (rs.next()) {
            Supplies e = new Supplies();
            e.AssetId = rs.getInt(Supplies.COLUMN_ASSET_ID);
            e.AmountAcquired = rs.getInt(Supplies.COLUMN_AMOUNT_ACQUIRED);
            e.Timestamp = rs.getDate(Supplies.COLUMN_TIMESTAMP);
            e.TotalQuantity = rs.getInt(Supplies.COLUMN_TOTALQUANTITY);
            e.AmountDisposed = rs.getInt(Supplies.COLUMN_AMOUNT_DISPOSED);
            purchaserequestList.add(e);
        }
        rs.close();
        return purchaserequestList;
    }
}
