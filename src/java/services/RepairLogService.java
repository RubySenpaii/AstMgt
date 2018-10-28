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

            String query = "INSERT INTO RepairLog(" + RepairLog.COLUMN_APPROVED_BY + ", " + RepairLog.COLUMN_APPROVED_DATE + ", "
                    + RepairLog.COLUMN_ARTICLE + ", " + RepairLog.COLUMN_ASSET_TAG + ", " + RepairLog.COLUMN_COST + ", "
                    + RepairLog.COLUMN_REQUESTED_BY + ", " + RepairLog.COLUMN_REQUESTED_DATE + ") "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, log.ApprovedBy);
            ps.setObject(2, log.ApprovedDate);
            ps.setString(3, log.Article);
            ps.setString(4, log.AssetTag);
            ps.setDouble(5, log.Cost);
            ps.setInt(6, log.RequestedBy);
            ps.setObject(7, log.RequestedDate);

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
            
            String query = "SELECT * FROM RepairLog";
            PreparedStatement ps = con.prepareStatement(query);
            
            ArrayList<RepairLog> logs = GetResults(ps.executeQuery());
            ps.close();
            con.close();
            return logs;
        } catch (SQLException x) {
            System.err.println(x);
            return null;
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
        rs.close();
        return logs;
    }
}
