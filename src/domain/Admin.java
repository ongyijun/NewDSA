/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;

/**
 *
 * @author MY
 */
public class Admin extends Employee {

    private double annualSale;

    public Admin() {
    }

    public Admin(double annualSale) {
        this.annualSale = annualSale;
    }

    public Admin(double annualSale, String StaffID, String StaffPw, String StaffName, String StaffIC, String StaffPhNo, char StaffGender, String StaffAdds, String StaffEmail, String StaffPosition, String WorkingStatus, double BasicSalary, double TotalPay, Calendar joinDate) {
        super(StaffID, StaffPw, StaffName, StaffIC, StaffPhNo, StaffGender, StaffAdds, StaffEmail, StaffPosition, WorkingStatus, BasicSalary, TotalPay, joinDate);
        this.annualSale = annualSale;
    }

    public double getAnnualSale() {
        return annualSale;
    }

    public void setAnnualSale(double annualSale) {
        this.annualSale = annualSale;
    }

    @Override
    public void calculateSalary() {
        super.setBasicSalary(annualSale * 0.3);
        super.setTotalPay(super.getBasicSalary());
    }
}
