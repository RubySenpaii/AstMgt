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
public class AssetRequested {
    public static final String COLUMN_PURCHASE_REQUEST_ID = "PurchaseRequestId";
    public static final String COLUMN_ASSET_ID = "AssetId";
    public static final String COLUMN_QUANTITY = "Quantity";
    public static final String COLUMN_UNIT_COST = "UnitCost";
    
    public int PurchaseRequestId;
    public int AssetId;
    public int Quantity;
    public double UnitCost;
    
    public Asset Asset;
    public PurchaseRequest PurchaseRequest;
}
