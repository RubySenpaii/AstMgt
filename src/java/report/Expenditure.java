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
public class Expenditure {
    private String name;
    private double allocated;
    private double current;

    /**
     * @return the allocated
     */
    public double getAllocated() {
        return allocated;
    }

    /**
     * @param allocated the allocated to set
     */
    public void setAllocated(double allocated) {
        this.allocated = allocated;
    }

    /**
     * @return the current
     */
    public double getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(double current) {
        this.current = current;
    }

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
}
