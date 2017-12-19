/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Calendar;
/**
 *
 * @author ong
 */
public class Payment {
    private String PaymentID;
    private Orders orders;
    private double PaymentAmount;
    private Calendar PaymentDateTime;
    private String PaymentStatus;
    private String PaymentMethod;

    public Payment() {
    }

    public Payment(String PaymentID, Orders orders, double PaymentAmount, Calendar PaymentDateTime, String PaymentStatus, String PaymentMethod) {
        this.PaymentID = PaymentID;
        this.orders = orders;
        this.PaymentAmount = PaymentAmount;
        this.PaymentDateTime = PaymentDateTime;
        this.PaymentStatus = PaymentStatus;
        this.PaymentMethod = PaymentMethod;
    }

    public String getPaymentID() {
        return PaymentID;
    }

    public Orders getOrders() {
        return orders;
    }

    public double getPaymentAmount() {
        return PaymentAmount;
    }

    public Calendar getPaymentDateTime() {
        return PaymentDateTime;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentID(String PaymentID) {
        this.PaymentID = PaymentID;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public void setPaymentAmount(double PaymentAmount) {
        this.PaymentAmount = PaymentAmount;
    }

    public void setPaymentDateTime(Calendar PaymentDateTime) {
        this.PaymentDateTime = PaymentDateTime;
    }

    public void setPaymentStatus(String PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" + "PaymentID=" + PaymentID + ", orders=" + orders + ", PaymentAmount=" + PaymentAmount + ", PaymentDateTime=" + PaymentDateTime + ", PaymentStatus=" + PaymentStatus + ", PaymentMethod=" + PaymentMethod + '}';
    }
    
}
