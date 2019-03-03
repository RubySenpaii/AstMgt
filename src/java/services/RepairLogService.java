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
import objects.RepairLog;

/**
 *
 * @author RubySenpaii
 */
public class RepairLogService {

    public int AddRepairLog(RepairLog log) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "INSERT INTO RepairLog(" + RepairLog.COLUMN_ARTICLE + ", " + RepairLog.COLUMN_ASSET_TAG + ", "
                    + RepairLog.COLUMN_COST + ", "
                    + RepairLog.COLUMN_REQUESTED_BY + ", " + RepairLog.COLUMN_REQUESTED_DATE + ") "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, log.Article);
            ps.setString(2, log.AssetTag);
            ps.setDouble(3, log.Cost);
            ps.setInt(4, log.RequestedBy);
            ps.setObject(5, log.RequestedDate);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public int UpdateRepairLog(RepairLog log) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "UPDATE RepairLog SET " + RepairLog.COLUMN_APPROVED_BY + " = ?, " + RepairLog.COLUMN_APPROVED_DATE + " = ? "
                    + "WHERE " + RepairLog.COLUMN_ASSET_TAG + " = ? AND " + RepairLog.COLUMN_REQUESTED_BY + " = ? AND "
                    + RepairLog.COLUMN_REQUESTED_DATE + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, log.ApprovedBy);
            ps.setObject(2, log.ApprovedDate);
            ps.setString(3, log.AssetTag);
            ps.setInt(4, log.RequestedBy);
            ps.setObject(5, log.RequestedDate);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public ArrayList<RepairLog> GetRepairLogs() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT " + RepairLog.COLUMN_REQUESTED_BY + ", " + RepairLog.COLUMN_REQUESTED_DATE
                    + ", " + RepairLog.COLUMN_ASSET_TAG + ", " + RepairLog.COLUMN_APPROVED_BY + ", " + RepairLog.COLUMN_APPROVED_DATE
                    + ", SUM(Cost) AS \"Total Cost\" "
                    + "FROM RepairLog "
                    + "GROUP BY " + RepairLog.COLUMN_REQUESTED_BY + ", " + RepairLog.COLUMN_REQUESTED_DATE
                    + ", " + RepairLog.COLUMN_ASSET_TAG + ", " + RepairLog.COLUMN_APPROVED_BY + ", " + RepairLog.COLUMN_APPROVED_DATE;
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            ArrayList<RepairLog> logs = new ArrayList<>();
            while (rs.next()) {
                RepairLog log = new RepairLog();
                log.ApprovedBy = rs.getInt(RepairLog.COLUMN_APPROVED_BY);
                log.ApprovedDate = rs.getDate(RepairLog.COLUMN_APPROVED_DATE);
                log.AssetTag = rs.getString(RepairLog.COLUMN_ASSET_TAG);
                log.RequestedBy = rs.getInt(RepairLog.COLUMN_REQUESTED_BY);
                log.RequestedDate = rs.getDate(RepairLog.COLUMN_REQUESTED_DATE);
                log.TotalCost = rs.getDouble("Total Cost");
                log.Requester = new EmployeeService().FindEmployeeById(log.RequestedBy);
                if (log.ApprovedBy != 0) {
                    log.Approver = new EmployeeService().FindEmployeeById(log.ApprovedBy);
                    log.Logs = GetRepairLogsByRequests(log);
                } else {
                    log.Approver = new Employee();
                    log.Approver.LastName = "-";
                    log.Approver.FirstName = "";
                    log.Logs = GetRepairLogsByRequestsNull(log);
                }
                logs.add(log);
            }
            rs.close();
            ps.close();
            con.close();
            return logs;
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        }
    }
    
    public ArrayList<RepairLog> GetApprovedRepairLogs(String assetTag) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT " + RepairLog.COLUMN_REQUESTED_BY + ", " + RepairLog.COLUMN_REQUESTED_DATE
                    + ", " + RepairLog.COLUMN_ASSET_TAG + ", " + RepairLog.COLUMN_APPROVED_BY + ", " + RepairLog.COLUMN_APPROVED_DATE
                    + ", SUM(Cost) AS \"Total Cost\" "
                    + "FROM RepairLog "
                    + "WHERE " + RepairLog.COLUMN_ASSET_TAG + " = ? AND " + RepairLog.COLUMN_APPROVED_BY + " IS NOT NULL "
                    + "GROUP BY " + RepairLog.COLUMN_REQUESTED_BY + ", " + RepairLog.COLUMN_REQUESTED_DATE
                    + ", " + RepairLog.COLUMN_ASSET_TAG + ", " + RepairLog.COLUMN_APPROVED_BY + ", " + RepairLog.COLUMN_APPROVED_DATE;
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);

            ResultSet rs = ps.executeQuery();
            ArrayList<RepairLog> logs = new ArrayList<>();
            while (rs.next()) {
                RepairLog log = new RepairLog();
                log.ApprovedBy = rs.getInt(RepairLog.COLUMN_APPROVED_BY);
                log.ApprovedDate = rs.getDate(RepairLog.COLUMN_APPROVED_DATE);
                log.AssetTag = rs.getString(RepairLog.COLUMN_ASSET_TAG);
                log.RequestedBy = rs.getInt(RepairLog.COLUMN_REQUESTED_BY);
                log.RequestedDate = rs.getDate(RepairLog.COLUMN_REQUESTED_DATE);
                log.TotalCost = rs.getDouble("Total Cost");
                log.Requester = new EmployeeService().FindEmployeeById(log.RequestedBy);
                if (log.ApprovedBy != 0) {
                    log.Approver = new EmployeeService().FindEmployeeById(log.ApprovedBy);
                    log.Logs = GetRepairLogsByRequests(log);
                } else {
                    log.Approver = new Employee();
                    log.Approver.LastName = "-";
                    log.Approver.FirstName = "";
                    log.Logs = GetRepairLogsByRequestsNull(log);
                }
                logs.add(log);
            }
            rs.close();
            ps.close();
            con.close();
            return logs;
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        }
    }

    public ArrayList<RepairLog> GetRepairLogsByRequests(RepairLog log) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM RepairLog WHERE " + RepairLog.COLUMN_REQUESTED_BY + " = ? AND " + RepairLog.COLUMN_REQUESTED_DATE + " = ? AND "
                    + RepairLog.COLUMN_ASSET_TAG + " = ? AND " + RepairLog.COLUMN_APPROVED_BY + " = ? AND " + RepairLog.COLUMN_APPROVED_DATE + " = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, log.RequestedBy);
            ps.setObject(2, log.RequestedDate);
            ps.setString(3, log.AssetTag);
            ps.setInt(4, log.ApprovedBy);
            ps.setObject(5, log.ApprovedDate);

            ArrayList<RepairLog> logs = GetResults(ps.executeQuery());
            ps.close();
            con.close();
            return logs;
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        }
    }

    public ArrayList<RepairLog> GetRepairLogsByRequestsNull(RepairLog log) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT * FROM RepairLog WHERE " + RepairLog.COLUMN_REQUESTED_BY + " = ? AND " + RepairLog.COLUMN_REQUESTED_DATE + " = ? AND "
                    + RepairLog.COLUMN_ASSET_TAG + " = ? AND " + RepairLog.COLUMN_APPROVED_BY + " IS NULL AND " + RepairLog.COLUMN_APPROVED_DATE + " IS NULL";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, log.RequestedBy);
            ps.setObject(2, log.RequestedDate);
            ps.setString(3, log.AssetTag);

            ArrayList<RepairLog> logs = GetResults(ps.executeQuery());
            ps.close();
            con.close();
            return logs;
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        }
    }

    public double GetTotalRepairCost(String assetTag) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT AssetTag, SUM(COST) AS 'Cost' FROM RepairLog WHERE AssetTag = ? AND ApprovedBy IS NOT NULL AND ApprovedDate IS NOT NULL GROUP BY AssetTag";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, assetTag);

            double totalCost = 0;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                totalCost += rs.getDouble("Cost");
            }
            ps.close();
            con.close();
            return totalCost;
        } catch (SQLException x) {
            System.err.println(x);
            return 0;
        }
    }

    private ArrayList<RepairLog> GetResults(ResultSet rs) throws SQLException {
        ArrayList<RepairLog> logs = new ArrayList<>();
        while (rs.next()) {
            RepairLog log = new RepairLog();
            log.ApprovedBy = rs.getInt(RepairLog.COLUMN_APPROVED_BY);
            log.ApprovedDate = rs.getDate(RepairLog.COLUMN_APPROVED_DATE);
            log.Article = rs.getString(RepairLog.COLUMN_ARTICLE);
            log.AssetTag = rs.getString(RepairLog.COLUMN_ASSET_TAG);
            log.Cost = rs.getDouble(RepairLog.COLUMN_COST);
            log.RequestedBy = rs.getInt(RepairLog.COLUMN_REQUESTED_BY);
            log.RequestedDate = rs.getDate(RepairLog.COLUMN_REQUESTED_DATE);
            logs.add(log);
        }
        return logs;
    }
}
