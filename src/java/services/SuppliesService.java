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
            + Supplies.COLUMN_TIMESTAMP + "," + Supplies.COLUMN_TOTALQUANTITY + ", " + Supplies.COLUMN_AMOUNT_CONSUMED + ") "
            + "VALUES(?, ?, ?, ?, ?)";

    private String FindSuppliesByIdQuery = "SELECT * FROM Supplies WHERE " + Supplies.COLUMN_ASSET_ID + " = ?";
    private String FindAllSuppliesQuery = "SELECT * FROM Supplies;";

    public int AddNewSupply(Supplies s) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(AddSuppliesQuery);
            ps.setInt(1, s.AssetId);
            ps.setInt(2, s.AmountAcquired);
            ps.setObject(3, s.Timestamp);
            ps.setInt(4, s.TotalQuantity);
            ps.setInt(5, s.AmountConsumed);

            int res = ps.executeUpdate();
            ps.close();
            conn.close();
            return res;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return -1;
    }

    public int ConsumeSupply(Supplies s) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();

            String query = "INSERT INTO Supplies(" + Supplies.COLUMN_ASSET_ID + "," + Supplies.COLUMN_AMOUNT_ACQUIRED + ","
                    + Supplies.COLUMN_TIMESTAMP + "," + Supplies.COLUMN_TOTALQUANTITY + ", " + Supplies.COLUMN_AMOUNT_CONSUMED + ", "
                    + Supplies.COLUMN_DIVISION + ") "
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, s.AssetId);
            ps.setInt(2, s.AmountAcquired);
            ps.setObject(3, s.Timestamp);
            ps.setInt(4, s.TotalQuantity);
            ps.setInt(5, s.AmountConsumed);
            ps.setString(6, s.Division);

            int res = ps.executeUpdate();
            ps.close();
            conn.close();
            return res;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return -1;
    }

    public ArrayList<Supplies> FindSuppliesByAssetId(int assetId) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindSuppliesByIdQuery);
            ps.setInt(1, assetId);

            ArrayList<Supplies> elist = getResult(ps.executeQuery());
            ps.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<Supplies> FindAllSupplies() {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            String query = "SELECT T1.* FROM Supplies T1 INNER JOIN "
                    + "(SELECT AssetId, MAX(S.Timestamp) AS 'Latest'  FROM Supplies S GROUP BY S.AssetId) T2 "
                    + "ON T1.AssetId = T2.AssetId AND T1.Timestamp = T2.Latest";
            PreparedStatement ps = conn.prepareStatement(query);
            
            ArrayList<Supplies> elist = getResult(ps.executeQuery());

            ps.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public int GetLatestCountOfSupplies(int assetId) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT T1.* FROM Supplies T1 INNER JOIN "
                    + "(SELECT AssetId, MAX(S.Timestamp) AS 'Latest'  FROM Supplies S WHERE S.AssetId = ? GROUP BY S.AssetId) T2 "
                    + "ON T1.AssetId = T2.AssetId AND T1.Timestamp = T2.Latest";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, assetId);

            int result = 0;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt(Supplies.COLUMN_TOTALQUANTITY);
            }

            ps.close();
            con.close();
            rs.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    private ArrayList<Supplies> getResult(ResultSet rs) throws SQLException {
        ArrayList<Supplies> purchaserequestList = new ArrayList<Supplies>();
        while (rs.next()) {
            Supplies e = new Supplies();
            e.AssetId = rs.getInt(Supplies.COLUMN_ASSET_ID);
            e.AmountAcquired = rs.getInt(Supplies.COLUMN_AMOUNT_ACQUIRED);
            e.Timestamp = rs.getDate(Supplies.COLUMN_TIMESTAMP);
            e.TotalQuantity = rs.getInt(Supplies.COLUMN_TOTALQUANTITY);
            e.AmountConsumed = rs.getInt(Supplies.COLUMN_AMOUNT_CONSUMED);
            e.Division = rs.getString(Supplies.COLUMN_DIVISION);

            e.Asset = new AssetService().GetAsset(e.AssetId);
            purchaserequestList.add(e);
        }
        rs.close();
        return purchaserequestList;
    }
}
