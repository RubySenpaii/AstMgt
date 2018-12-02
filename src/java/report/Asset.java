/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

/**
 *
 * @author rubysenpaii
 */
public class Asset {
    public static final String COLUMN_ASSET_ID = "AssetId";
    public static final String COLUMN_STOCK_NO = "StockNo";
    public static final String COLUMN_ASSET_NAME = "AssetName";
    public static final String COLUMN_UNIT = "Unit";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_FUND_CLUSTER = "FundCluster";
    public static final String COLUMN_ASSET_TYPE = "AssetType";

    private int assetId;
    private String stockNo;
    private String assetName;
    private String unit;
    private String description;
    private String fundCluster;
    private String assetType;
    private int quantityOnStock;
    private int quantityDisposed;
    private int quantityUsed;
    
    private String division;
    private int adminConsumed;
    private int generalConsumed;
    private int procurementConsumed;
    private int financialConsumed;
    private int managementConsumed;

    /**
     * @return the assetId
     */
    public int getAssetId() {
        return assetId;
    }

    /**
     * @param assetId the assetId to set
     */
    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    /**
     * @return the stockNo
     */
    public String getStockNo() {
        return stockNo;
    }

    /**
     * @param stockNo the stockNo to set
     */
    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    /**
     * @return the assetName
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * @param assetName the assetName to set
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the fundCluster
     */
    public String getFundCluster() {
        return fundCluster;
    }

    /**
     * @param fundCluster the fundCluster to set
     */
    public void setFundCluster(String fundCluster) {
        this.fundCluster = fundCluster;
    }

    /**
     * @return the assetType
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * @param assetType the assetType to set
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    /**
     * @return the quantityOnStock
     */
    public int getQuantityOnStock() {
        return quantityOnStock;
    }

    /**
     * @param quantityOnStock the quantityOnStock to set
     */
    public void setQuantityOnStock(int quantityOnStock) {
        this.quantityOnStock = quantityOnStock;
    }

    /**
     * @return the quantityDisposed
     */
    public int getQuantityDisposed() {
        return quantityDisposed;
    }

    /**
     * @param quantityDisposed the quantityDisposed to set
     */
    public void setQuantityDisposed(int quantityDisposed) {
        this.quantityDisposed = quantityDisposed;
    }

    /**
     * @return the quantityUsed
     */
    public int getQuantityUsed() {
        return quantityUsed;
    }

    /**
     * @param quantityUsed the quantityUsed to set
     */
    public void setQuantityUsed(int quantityUsed) {
        this.quantityUsed = quantityUsed;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * @return the adminConsumed
     */
    public int getAdminConsumed() {
        return adminConsumed;
    }

    /**
     * @param adminConsumed the adminConsumed to set
     */
    public void setAdminConsumed(int adminConsumed) {
        this.adminConsumed = adminConsumed;
    }

    /**
     * @return the generalConsumed
     */
    public int getGeneralConsumed() {
        return generalConsumed;
    }

    /**
     * @param generalConsumed the generalConsumed to set
     */
    public void setGeneralConsumed(int generalConsumed) {
        this.generalConsumed = generalConsumed;
    }

    /**
     * @return the procurementConsumed
     */
    public int getProcurementConsumed() {
        return procurementConsumed;
    }

    /**
     * @param procurementConsumed the procurementConsumed to set
     */
    public void setProcurementConsumed(int procurementConsumed) {
        this.procurementConsumed = procurementConsumed;
    }

    /**
     * @return the financialConsumed
     */
    public int getFinancialConsumed() {
        return financialConsumed;
    }

    /**
     * @param financialConsumed the financialConsumed to set
     */
    public void setFinancialConsumed(int financialConsumed) {
        this.financialConsumed = financialConsumed;
    }

    /**
     * @return the managementConsumed
     */
    public int getManagementConsumed() {
        return managementConsumed;
    }

    /**
     * @param managementConsumed the managementConsumed to set
     */
    public void setManagementConsumed(int managementConsumed) {
        this.managementConsumed = managementConsumed;
    }
}
