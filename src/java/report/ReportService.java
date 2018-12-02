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
import services.EquipmentService;
import services.RepairLogService;

/**
 *
 * @author rubysenpaii
 */
public class ReportService {

    public ArrayList<Asset> GetGeneralPPEData() {
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
                    temp = populateGeneralPPE(rs);
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
                        temp = populateGeneralPPE(rs);
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

    public ArrayList<Asset> GetGeneralSuppliesData() {
        try {
            DBConnectionFactory db = DBConnectionFactory.getInstance();
            Connection con = db.getConnection();

            String query = "SELECT A.*, T1.Division, T1.Consumed\n"
                    + "FROM Asset A JOIN\n"
                    + "(SELECT Supplies.AssetId, Supplies.Division, SUM(Supplies.AmountConsumed) AS 'Consumed'\n"
                    + "FROM Supplies\n"
                    + "WHERE Supplies.Division IS NOT NULL\n"
                    + "GROUP BY Supplies.AssetId, Supplies.Division) T1 ON A.AssetId = T1.AssetId\n"
                    + "ORDER BY A.AssetId";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            ArrayList<Asset> assets = new ArrayList<>();
            int count = 0;
            Asset temp = new Asset();
            while (rs.next()) {
                if (count == 0) {
                    temp = populateSupplies(rs);
                    assets.add(temp);
                    count++;
                } else {
                    if (temp.getAssetId() == rs.getInt(Asset.COLUMN_ASSET_ID)) {
                        String division = rs.getString("Division");
                        switch (division) {
                            case "Admin Services":
                                assets.get(assets.size() - 1).setAdminConsumed(rs.getInt("Consumed"));
                                break;
                            case "General Services":
                                assets.get(assets.size() - 1).setGeneralConsumed(rs.getInt("Consumed"));
                                break;
                            case "Procurement":
                                assets.get(assets.size() - 1).setProcurementConsumed(rs.getInt("Consumed"));
                                break;
                            case "Finance":
                                assets.get(assets.size() - 1).setFinancialConsumed(rs.getInt("Consumed"));
                                break;
                            case "Management":
                                assets.get(assets.size() - 1).setManagementConsumed(rs.getInt("Consumed"));
                        }
                    } else {
                        temp = populateSupplies(rs);
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

    public SpecificEquipment GetSpecificEquipmentDetails(String assetName) {
        SpecificEquipment specificEquipment = new SpecificEquipment();
        ArrayList<report.Equipment> reportEquipments = new ArrayList<>();
        ArrayList<objects.Equipment> sqlEquipments = new EquipmentService().GetListOfEquipmentsWithAssetName(assetName);
        int used = 0, stock = 0, disposed = 0, expiring = 0, extended = 0;
        for (objects.Equipment sqlEquipment : sqlEquipments) {
            report.Equipment reportEquipment = new report.Equipment();
            reportEquipment.setAssetTag(sqlEquipment.AssetTag);
            reportEquipment.setCurrentUser(sqlEquipment.CurrentUser.FullName());
            reportEquipment.setDateAcquired(sqlEquipment.DateAcquired);
            reportEquipment.setEstimatedUsefulLife(sqlEquipment.Asset.EstimatedUsefulLife);
            reportEquipment.setStatus(sqlEquipment.Status());
            reportEquipment.setTotalRepairs(new RepairLogService().GetTotalRepairCost(sqlEquipment.AssetTag));
            reportEquipments.add(reportEquipment);
            switch (sqlEquipment.Flag) {
                case 0: disposed++; break;
                case 1: stock++; break;
                case 2: used++; break;
                case 3: expiring++; break;
                case 4: extended++; break;
            }
        }
        specificEquipment.AssetName = assetName;
        specificEquipment.Equipments = reportEquipments;
        specificEquipment.BeingUsed = used;
        specificEquipment.Disposed = disposed;
        specificEquipment.Expiring = expiring;
        specificEquipment.Extended = extended;
        specificEquipment.Stocked = stock;
        return specificEquipment;
    }

    private Asset populateSupplies(ResultSet rs) throws SQLException {
        Asset asset = new Asset();
        asset.setAssetId(rs.getInt(Asset.COLUMN_ASSET_ID));
        asset.setAssetName(rs.getString(Asset.COLUMN_ASSET_NAME));
        asset.setDescription(rs.getString(Asset.COLUMN_DESCRIPTION));
        asset.setFundCluster(rs.getString(Asset.COLUMN_FUND_CLUSTER));
        asset.setStockNo(rs.getString(Asset.COLUMN_STOCK_NO));
        asset.setUnit(rs.getString(Asset.COLUMN_UNIT));
        asset.setAssetType(rs.getString(Asset.COLUMN_ASSET_TYPE));
        asset.setAdminConsumed(0);
        asset.setGeneralConsumed(0);
        asset.setProcurementConsumed(0);
        asset.setFinancialConsumed(0);

        String division = rs.getString("Division");
        switch (division) {
            case "Admin Services":
                asset.setAdminConsumed(rs.getInt("Consumed"));
                break;
            case "General Services":
                asset.setGeneralConsumed(rs.getInt("Consumed"));
                break;
            case "Procurement":
                asset.setProcurementConsumed(rs.getInt("Consumed"));
                break;
            case "Finance":
                asset.setFinancialConsumed(rs.getInt("Consumed"));
                break;
            case "Management":
                asset.setManagementConsumed(rs.getInt("Consumed"));
                break;
        }
        return asset;
    }

    private Asset populateGeneralPPE(ResultSet rs) throws SQLException {
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
