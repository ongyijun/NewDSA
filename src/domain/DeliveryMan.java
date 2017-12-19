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
public class DeliveryMan extends Employee {

    private int TotalPendingDelivery;
    private String CurrentAvailable;
    private String CurrentLocation;
    private double rating;
    private int TotalRateReceived;

    public DeliveryMan() {
    }

    public DeliveryMan(int TotalPendingDelivery, String CurrentAvailable, String CurrentLocation, double rating, int TotalRateReceived) {
        this.TotalPendingDelivery = TotalPendingDelivery;
        this.CurrentAvailable = CurrentAvailable;
        this.CurrentLocation = CurrentLocation;
        this.rating = rating;
        this.TotalRateReceived = TotalRateReceived;
    }

    public DeliveryMan(int TotalPendingDelivery, String CurrentAvailable, String CurrentLocation, double rating, int TotalRateReceived, String StaffID, String StaffPw, String StaffName, String StaffIC, String StaffPhNo, char StaffGender, String StaffAdds, String StaffEmail, String StaffPosition, String WorkingStatus, double BasicSalary, double TotalPay, Calendar joinDate) {
        super(StaffID, StaffPw, StaffName, StaffIC, StaffPhNo, StaffGender, StaffAdds, StaffEmail, StaffPosition, WorkingStatus, BasicSalary, TotalPay, joinDate);
        this.TotalPendingDelivery = TotalPendingDelivery;
        this.CurrentAvailable = CurrentAvailable;
        this.CurrentLocation = CurrentLocation;
        this.rating = rating;
        this.TotalRateReceived = TotalRateReceived;
    }

    public int getTotalPendingDelivery() {
        return TotalPendingDelivery;
    }

    public void setTotalPendingDelivery(int TotalPendingDelivery) {
        this.TotalPendingDelivery = TotalPendingDelivery;
    }

    public String getCurrentAvailable() {
        return CurrentAvailable;
    }

    public void setCurrentAvailable(String CurrentAvailable) {
        this.CurrentAvailable = CurrentAvailable;
    }

    public String getCurrentLocation() {
        return CurrentLocation;
    }

    public void setCurrentLocation(String CurrentLocation) {
        this.CurrentLocation = CurrentLocation;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalRateReceived() {
        return TotalRateReceived;
    }

    public void setTotalRateReceived(int TotalRateReceived) {
        this.TotalRateReceived = TotalRateReceived;
    }

    @Override
    public void calculateSalary() {
        super.setTotalPay(super.getBasicSalary());
    }

}
