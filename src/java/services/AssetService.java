/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objects.Asset;

/**
 *
 * @author RubySenpaii
 */
public class AssetService {

    public void AddAsset(Asset asset) {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Asset(AssetId, StockNo, AssetName, Unit, Descrip)");
        } catch (SQLException x) {
            System.err.println(x);
        }
    }
}
