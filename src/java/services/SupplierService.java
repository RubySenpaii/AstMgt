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
import objects.Supplier;

/**
 *
 * @author Bryll Joey Delfin
 */
public class SupplierService {

    private String AddSupplierQuery = "INSERT INTO Supplier(" + Supplier.COLUMN_SUPPLIER_ID + "," + Supplier.COLUMN_SUPPLIER_NAME + ","
            + Supplier.COLUMN_SUPPLIER_ADDRESS + "," + Supplier.COLUMN_SUPPLIER_TIN + ", " + Supplier.COLUMN_SUPPLIER_TYPE + ")VALUES(?,?,?,?)";

    private String FindSupplierByIdQuery = "SELECT * FROM Supplier WHERE " + Supplier.COLUMN_SUPPLIER_ID + " = ?";
    private String FindSupplierByNameQuery = "SELECT * FROM Supplier WHERE " + Supplier.COLUMN_SUPPLIER_NAME + " = ?";
    private String FindAllSupplierQuery = "SELECT * FROM Supplier ;";

    public int AddNewSupplier(Supplier s) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(AddSupplierQuery);
            ps.setInt(1, s.SupplierId);
            ps.setString(2, s.SupplierName);
            ps.setString(3, s.SupplierAddress);
            ps.setString(4, s.SupplierTIN);
            ps.setString(5, s.SupplierType);
            int res = ps.executeUpdate();
            ps.close();
            conn.close();
            return res;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    public Supplier FindSupplierById(int suppno) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindSupplierByIdQuery);
            ps.setInt(1, suppno);

            ArrayList<Supplier> elist = getResult(ps.executeQuery());
            ps.close();
            conn.close();
            return elist.get(0);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public Supplier FindSupplierByName(String suppname) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindSupplierByNameQuery);
            ps.setString(1, suppname);

            ArrayList<Supplier> elist = getResult(ps.executeQuery());
            ps.close();
            conn.close();
            return elist.get(0);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<Supplier> FindAllSupplier() {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(FindAllSupplierQuery);
            ArrayList<Supplier> elist = getResult(ps.executeQuery());

            ps.close();
            conn.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    public ArrayList<Supplier> FindSupplierByType(String type) {
        DBConnectionFactory db = DBConnectionFactory.getInstance();
        Connection conn = db.getConnection();

        try {
            String query = "SELECT * FROM Supplier WHERE " + Supplier.COLUMN_SUPPLIER_TYPE + " = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, type);

            ArrayList<Supplier> elist = getResult(ps.executeQuery());
            ps.close();
            conn.close();
            return elist;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return null;
    }

    private ArrayList<Supplier> getResult(ResultSet rs) throws SQLException {
        ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
        while (rs.next()) {
            Supplier e = new Supplier();
            e.SupplierId = rs.getInt(Supplier.COLUMN_SUPPLIER_ID);
            e.SupplierName = rs.getString(Supplier.COLUMN_SUPPLIER_NAME);
            e.SupplierAddress = rs.getString(Supplier.COLUMN_SUPPLIER_ADDRESS);
            e.SupplierTIN = rs.getString(Supplier.COLUMN_SUPPLIER_TIN);
            e.SupplierType = rs.getString(Supplier.COLUMN_SUPPLIER_TYPE);
            suppliers.add(e);
        }
        rs.close();
        return suppliers;
    }

}
