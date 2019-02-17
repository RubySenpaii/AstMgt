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
import objects.AssetTracking;
import objects.Employee;

/**
 *
 * @author RubySenpaii
 */
public class AssetTrackingService {

    public int AddAssetTracking(AssetTracking assetTracking) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "INSERT INTO AssetTracking(" + AssetTracking.COLUMN_ASSET_TAG + ", "
                    + AssetTracking.COLUMN_RELEASED_BY + ", " + AssetTracking.COLUMN_RELEASED_TO + ", "
                    + AssetTracking.COLUMN_TRANSFER_DATE + ", " + assetTracking.COLUMN_REMARKS + ") "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTracking.AssetTag);
            ps.setInt(2, assetTracking.ReleasedBy);
            ps.setInt(3, assetTracking.ReleasedTo);
            ps.setObject(4, assetTracking.TransferDate);
            ps.setString(5, assetTracking.Remarks);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public int UpdateAssetTracking(AssetTracking assetTracking) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "UPDATE AssetTracking SET " + AssetTracking.COLUMN_APPROVED_BY + " = ?, "
                    + AssetTracking.COLUMN_APPROVED_DATE + " = ?, " + AssetTracking.COLUMN_RELEASED_BY + " = ?, "
                    + AssetTracking.COLUMN_RELEASED_TO + " = ?, " + AssetTracking.COLUMN_TRANSFER_DATE + " = ?, "
                    + AssetTracking.COLUMN_REMARKS + " = ? "
                    + "WHERE " + AssetTracking.COLUMN_ASSET_TAG + " = ? AND " + AssetTracking.COLUMN_RELEASED_BY + " = ? AND " + AssetTracking.COLUMN_TRANSFER_DATE + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, assetTracking.ApprovedBy);
            ps.setObject(2, assetTracking.ApprovedDate);
            ps.setInt(3, assetTracking.ReleasedBy);
            ps.setInt(4, assetTracking.ReleasedTo);
            ps.setObject(5, assetTracking.TransferDate);
            ps.setString(6, assetTracking.Remarks);
            ps.setString(7, assetTracking.AssetTag);
            ps.setInt(8, assetTracking.ReleasedBy);
            ps.setObject(9, assetTracking.TransferDate);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public AssetTracking GetAssetStatus(String assetTag) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM AssetTracking WHERE " + AssetTracking.COLUMN_ASSET_TAG + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);

            ArrayList<AssetTracking> assetHistory = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return assetHistory.get(0);
        } catch (SQLException x) {
            System.err.println(x);
            return new AssetTracking();
        }
    }

    public ArrayList<AssetTracking> GetAssetHistory(String assetTag) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM AssetTracking WHERE " + AssetTracking.COLUMN_ASSET_TAG + " = ? AND " + AssetTracking.COLUMN_APPROVED_BY + " IS NOT NULL";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);

            ArrayList<AssetTracking> assetHistory = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return assetHistory;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }

    public ArrayList<AssetTracking> GetPendingTracking() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM AssetTracking WHERE " + AssetTracking.COLUMN_APPROVED_BY + " IS NULL";
            PreparedStatement ps = con.prepareStatement(query);

            ArrayList<AssetTracking> assetHistory = getResult(ps.executeQuery());
            ps.close();
            con.close();
            return assetHistory;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }

    public Employee GetCurrentuser(String assetTag) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT T1.* FROM AssetTracking T1 INNER JOIN "
                    + "(SELECT T2.AssetTag, MAX(T2.TransferDate) AS 'Latest' FROM AssetTracking T2 WHERE T2.AssetTag = ? AND T2.ApprovedBy IS NOT NULL GROUP BY T2.AssetTag) T2 "
                    + "ON T1.AssetTag = T2.AssetTag AND T1.TransferDate = T2.Latest";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);

            int employeeId = 0;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employeeId = rs.getInt("ReleasedTo");
            }

            ps.close();
            rs.close();
            con.close();
            if (employeeId > 0) {
                return new EmployeeService().FindEmployeeById(employeeId);
            }
        } catch (SQLException x) {
            System.err.println(x);
        }
        return null;
    }

    private ArrayList<AssetTracking> getResult(ResultSet rs) throws SQLException {
        ArrayList<AssetTracking> assetHistory = new ArrayList<>();
        while (rs.next()) {
            AssetTracking assetStatus = new AssetTracking();
            assetStatus.ApprovedBy = rs.getInt(AssetTracking.COLUMN_APPROVED_BY);
            assetStatus.ApprovedDate = rs.getDate(AssetTracking.COLUMN_APPROVED_DATE);
            assetStatus.AssetTag = rs.getString(AssetTracking.COLUMN_ASSET_TAG);
            assetStatus.ReleasedBy = rs.getInt(AssetTracking.COLUMN_RELEASED_BY);
            assetStatus.ReleasedTo = rs.getInt(AssetTracking.COLUMN_RELEASED_TO);
            assetStatus.TransferDate = rs.getDate(AssetTracking.COLUMN_TRANSFER_DATE);
            assetStatus.Remarks = rs.getString(AssetTracking.COLUMN_REMARKS);

            EmployeeService employeeService = new EmployeeService();
            if (assetStatus.ApprovedBy > 0) {
                assetStatus.Approver = employeeService.FindEmployeeById(assetStatus.ApprovedBy);
            }
            assetStatus.ReleaseBy = employeeService.FindEmployeeById(assetStatus.ReleasedBy);
            assetStatus.ReleaseTo = employeeService.FindEmployeeById(assetStatus.ReleasedTo);
            assetHistory.add(assetStatus);
        }
        rs.close();
        return assetHistory;
    }
}
