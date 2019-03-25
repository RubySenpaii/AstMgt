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
public class AssetRepair {
    private String name;
    private String type;
    private int repairCount;
    private double totalRepair;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the repairCount
     */
    public int getRepairCount() {
        return repairCount;
    }

    /**
     * @param repairCount the repairCount to set
     */
    public void setRepairCount(int repairCount) {
        this.repairCount = repairCount;
    }

    /**
     * @return the totalRepair
     */
    public double getTotalRepair() {
        return totalRepair;
    }

    /**
     * @param totalRepair the totalRepair to set
     */
    public void setTotalRepair(double totalRepair) {
        this.totalRepair = totalRepair;
    }
}
