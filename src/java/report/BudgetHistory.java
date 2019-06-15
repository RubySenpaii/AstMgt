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
public class BudgetHistory {
    private int year;
    private String quarter;
    private String division;
    private double limit;
    private double spent;

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the quarter
     */
    public String getQuarter() {
        return quarter;
    }

    /**
     * @param quarter the quarter to set
     */
    public void setQuarter(String quarter) {
        this.quarter = quarter;
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
     * @return the limit
     */
    public double getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(double limit) {
        this.limit = limit;
    }

    /**
     * @return the spent
     */
    public double getSpent() {
        return spent;
    }

    /**
     * @param spent the spent to set
     */
    public void setSpent(double spent) {
        this.spent = spent;
    }
}
