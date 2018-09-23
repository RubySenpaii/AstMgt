/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.Date;

/**
 *
 * @author RubySenpaii
 */
public class AssetTracking {
    public static final String COLUMN_ASSET_ID = "AssetId";
    public static final String COLUMN_RELEASED_BY = "ReleasedBy";
    public static final String COLUMN_TRANSFER_DATE = "TransferDate";
    public static final String COLUMN_RELATED_TO = "RelatedTo";
    public static final String COLUMN_APPROVED_BY = "ApprovedBy";
    public static final String COLUMN_APPROVED_DATE = "ApprovedDate";
    
    public int AssetId;
    public int ReleasedBy;
    public Date TransferDate;
    public int ReleasedTo;
    public int ApprovedBy;
    public Date ApprovedDate;
    
    public Asset Asset;
}
