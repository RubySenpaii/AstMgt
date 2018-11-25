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
import objects.ExpenditureLimit;
import objects.ExpenditureTracking;

/**
 *
 * @author rubysenpaii
 */
public class ExpenditureTrackingService {
    
    public int AddEquipmentTracking(ExpenditureLimit expenditure) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "INSERT INTO ExpenditureTracking (" + ExpenditureTracking.COLUMN_DIVISION + ", " + ExpenditureTracking.COLUMN_EQUIPMENT
                    + ", " + ExpenditureTracking.COLUMN_QUARTER + ", " + ExpenditureTracking.COLUMN_SUPPLIES + ", " + ExpenditureTracking.COLUMN_TIMESTAMP
                    + ", " + ExpenditureTracking.COLUMN_YEAR + ") VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, expenditure.Division);
            ps.setDouble(2, expenditure.Equipment);
            ps.setString(3, expenditure.Quarter);
            ps.setDouble(4, expenditure.Supplies);
            ps.setObject(5, Calendar.getInstance().getTime());
            ps.setInt(6, expenditure.Year);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }

    public int AddEquipmentTracking(ExpenditureTracking expenditure) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "INSERT INTO ExpenditureTracking (" + ExpenditureTracking.COLUMN_DIVISION + ", " + ExpenditureTracking.COLUMN_EQUIPMENT
                    + ", " + ExpenditureTracking.COLUMN_QUARTER + ", " + ExpenditureTracking.COLUMN_SUPPLIES + ", " + ExpenditureTracking.COLUMN_TIMESTAMP
                    + ", " + ExpenditureTracking.COLUMN_YEAR + ") VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, expenditure.Division);
            ps.setDouble(2, expenditure.Equipment);
            ps.setString(3, expenditure.Quarter);
            ps.setDouble(4, expenditure.Supplies);
            ps.setObject(5, expenditure.Timestamp);
            ps.setInt(6, expenditure.Year);

            int result = ps.executeUpdate();
            ps.close();
            con.close();
            return result;
        } catch (SQLException x) {
            System.err.println(x);
            return -1;
        }
    }
    
    public ExpenditureTracking GetCurrentExpenditure(String division) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();
            
            String query = "SELECT T1.* FROM ExpenditureTracking T1 INNER JOIN \n" 
                    + "(SELECT T2.Year, T2.Quarter, T2.Division, MAX(T2.Timestamp) AS 'Latest' "
                    + "FROM ExpenditureTracking T2 "
                    + "WHERE T2.Year = ? AND T2.Quarter = ? AND T2.Division = ? GROUP BY T2.Year, T2.Quarter, T2.Division) T2 \n" 
                    + "ON T1.Year = T2.Year AND T1.Quarter = T2.Quarter AND T1.Division = T2.Division AND T1.Timestamp = T2.Latest";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Calendar.getInstance().get(Calendar.YEAR));
            ps.setString(2, SharedFormat.getQuarter());
            ps.setString(3, division);
            
            ArrayList<ExpenditureTracking> expenditures = GetResultSet(ps.executeQuery());
            ps.close();
            con.close();
            return expenditures.get(0);
        } catch (SQLException x) {
            System.err.println(x);
            return null;
        } catch (IndexOutOfBoundsException x) {
            ExpenditureTracking tracking = new ExpenditureTracking();
            tracking.Equipment = 0;
            tracking.Supplies = 0;
            return tracking;
        }
    }
    
    private ArrayList<ExpenditureTracking> GetResultSet(ResultSet rs) throws SQLException {
        ArrayList<ExpenditureTracking> expenditures = new ArrayList<>();
        while (rs.next()) {
            ExpenditureTracking expenditure = new ExpenditureTracking();
            expenditure.Division = rs.getString(ExpenditureTracking.COLUMN_DIVISION);
            expenditure.Equipment = rs.getDouble(ExpenditureTracking.COLUMN_EQUIPMENT);
            expenditure.Quarter = rs.getString(ExpenditureTracking.COLUMN_QUARTER);
            expenditure.Supplies = rs.getDouble(ExpenditureTracking.COLUMN_SUPPLIES);
            expenditure.Timestamp = rs.getDate(ExpenditureTracking.COLUMN_TIMESTAMP);
            expenditure.Year = rs.getInt(ExpenditureTracking.COLUMN_YEAR);
            expenditures.add(expenditure);
        }
        rs.close();
        return expenditures;
    }
}
