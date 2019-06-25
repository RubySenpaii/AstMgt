/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author RubySenpaii
 */
public class Asset {
    public static final String COLUMN_ASSET_ID = "AssetId";
    public static final String COLUMN_STOCK_NO = "StockNo";
    public static final String COLUMN_ASSET_NAME = "AssetName";
    public static final String COLUMN_UNIT = "Unit";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_FUND_CLUSTER = "FundCluster";
    public static final String COLUMN_ASSET_TYPE = "AssetType";
    public static final String COLUMN_ESTIMATED_USEFUL_LIFE = "EstimatedUsefulLife";
    public static final String COLUMN_QUANTITY_LIMIT = "QuantityLimit";
    public static final String COLUMN_QUANTITY_ORDERED = "QuantityOrdered";
    
    public int AssetId;
    public String StockNo;
    public String AssetName;
    public String Unit;
    public String Description;
    public String FundCluster;
    public String AssetType;
    public int EstimatedUsefulLife;
    public int QuantityLimit;
    public int AdminQuantityLimit;
    public int GeneralQuantityLimit;
    public int PersonnelQuantityLimit;
    public int ProcurementQuantityLimit;
    public int RecordsQuantityLimit;
    public int QuantityOrdered;
    public int AdminQuantityOrdered;
    public int GeneralQuantityOrdered;
    public int PersonnelQuantityOrdered;
    public int ProcurementQuantityOrdered;
    public int RecordsQuantityOrdered;
}
