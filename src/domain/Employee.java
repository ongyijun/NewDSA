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
public abstract class Employee{

    private String StaffID;
    private String StaffPw;
    private String StaffName;
    private String StaffIC;
    private String StaffPhNo;
    private char StaffGender;
    private String StaffAdds;
    private String StaffEmail;
    private String StaffPosition;
    private String WorkingStatus;
    private double BasicSalary;
    private double TotalPay;
    private Calendar joinDate;

    public Employee() {
    }

    public Employee(String StaffID, String StaffPw, String StaffName, String StaffIC, String StaffPhNo, char StaffGender, String StaffAdds, String StaffEmail, String StaffPosition, String WorkingStatus, double BasicSalary, double TotalPay, Calendar joinDate) {
        this.StaffID = StaffID;
        this.StaffPw = StaffPw;
        this.StaffName = StaffName;
        this.StaffIC = StaffIC;
        this.StaffPhNo = StaffPhNo;
        this.StaffGender = StaffGender;
        this.StaffAdds = StaffAdds;
        this.StaffEmail = StaffEmail;
        this.StaffPosition = StaffPosition;
        this.WorkingStatus = WorkingStatus;
        this.BasicSalary = BasicSalary;
        this.TotalPay = TotalPay;
        this.joinDate = joinDate;
    }

    public String getStaffPw() {
        return StaffPw;
    }

    public void setStaffPw(String StaffPw) {
        this.StaffPw = StaffPw;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String StaffID) {
        this.StaffID = StaffID;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    public String getStaffIC() {
        return StaffIC;
    }

    public void setStaffIC(String StaffIC) {
        this.StaffIC = StaffIC;
    }

    public String getStaffPhNo() {
        return StaffPhNo;
    }

    public void setStaffPhNo(String StaffPhNo) {
        this.StaffPhNo = StaffPhNo;
    }

    public char getStaffGender() {
        return StaffGender;
    }

    public void setStaffGender(char StaffGender) {
        this.StaffGender = StaffGender;
    }

    public String getStaffAdds() {
        return StaffAdds;
    }

    public void setStaffAdds(String StaffAdds) {
        this.StaffAdds = StaffAdds;
    }

    public String getStaffEmail() {
        return StaffEmail;
    }

    public void setStaffEmail(String StaffEmail) {
        this.StaffEmail = StaffEmail;
    }

    public String getStaffPosition() {
        return StaffPosition;
    }

    public void setStaffPosition(String StaffPosition) {
        this.StaffPosition = StaffPosition;
    }

    public String getWorkingStatus() {
        return WorkingStatus;
    }

    public void setWorkingStatus(String WorkingStatus) {
        this.WorkingStatus = WorkingStatus;
    }

    public double getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(double BasicSalary) {
        this.BasicSalary = BasicSalary;
    }

    public double getTotalPay() {
        return TotalPay;
    }

    public void setTotalPay(double TotalPay) {
        this.TotalPay = TotalPay;
    }

    public Calendar getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Calendar joinDate) {
        this.joinDate = joinDate;
    }

    public abstract void calculateSalary();
}
