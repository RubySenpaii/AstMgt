/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import db.DBConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rubysenpaii
 */
public class ReportService {

    public ArrayList<Asset> GetAssets() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT A.*, T1.EquipmentCount, T1.Flag\n"
                    + "FROM Asset A JOIN\n"
                    + "(SELECT Equipment.AssetId, Equipment.Flag, COUNT(Equipment.AssetTag) AS 'EquipmentCount'\n"
                    + "FROM Equipment\n"
                    + "GROUP BY Equipment.AssetId, Equipment.Flag) T1 ON A.AssetId = T1.AssetId\n"
                    + "ORDER BY A.AssetId";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            ArrayList<Asset> assets = new ArrayList<>();
            int count = 0;
            Asset temp = new Asset();
            while (rs.next()) {
                if (count == 0) {
                    temp = populateAsset(rs);
                    assets.add(temp);
                    count++;
                } else {
                    if (temp.getAssetId() == rs.getInt(Asset.COLUMN_ASSET_ID)) {
                        int flag = rs.getInt("Flag");
                        switch (flag) {
                            case 0:
                                assets.get(assets.size() - 1).setQuantityDisposed(rs.getInt("EquipmentCount"));
                                break;
                            case 1:
                                assets.get(assets.size() - 1).setQuantityOnStock(rs.getInt("EquipmentCount"));
                                break;
                            case 2:
                                assets.get(assets.size() - 1).setQuantityUsed(rs.getInt("EquipmentCount"));
                                break;
                        }
                    } else {
                        temp = populateAsset(rs);
                        assets.add(temp);
                        count++;
                    }
                }
            }

            rs.close();
            ps.close();
            con.close();
            return assets;
        } catch (SQLException x) {
            System.err.println(x);
            return new ArrayList<>();
        }
    }

    private Asset populateAsset(ResultSet rs) throws SQLException {
        Asset asset = new Asset();
        asset.setAssetId(rs.getInt(Asset.COLUMN_ASSET_ID));
        asset.setAssetName(rs.getString(Asset.COLUMN_ASSET_NAME));
        asset.setDescription(rs.getString(Asset.COLUMN_DESCRIPTION));
        asset.setFundCluster(rs.getString(Asset.COLUMN_FUND_CLUSTER));
        asset.setStockNo(rs.getString(Asset.COLUMN_STOCK_NO));
        asset.setUnit(rs.getString(Asset.COLUMN_UNIT));
        asset.setAssetType(rs.getString(Asset.COLUMN_ASSET_TYPE));

        int flag = rs.getInt("Flag");
        switch (flag) {
            case 0:
                asset.setQuantityDisposed(rs.getInt("EquipmentCount"));
                break;
            case 1:
                asset.setQuantityOnStock(rs.getInt("EquipmentCount"));
                break;
            case 2:
                asset.setQuantityUsed(rs.getInt("EquipmentCount"));
                break;
        }
        return asset;
    }
}
