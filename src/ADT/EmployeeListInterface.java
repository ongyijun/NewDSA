/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author MY
 */
public interface EmployeeListInterface <T>{
    public void add(T newEntry); //basic operation
    public boolean addWithValidation(T newEntry);
    public T get(int position); //basic operation
    public boolean replace(int givenPosition, T newEntry); //basic operation
    public T getEmployeeDetails(String StaffID);
    public boolean updateDeliveryManDetail(T anEntry);
    public T remove(int givenPosition); //basic operation
    public boolean removeStaff(String StaffID);
    public int getTotalEntries(); //basic operation
    public boolean isEmpty(); //basic operation
    public void clear(); //basic operation
    public void SortMostExperienceDeliveryMan();
    public void SortDeliveryManRating();
    public void SortPendingDelivery();
    public boolean updateDeliveryManRating(String StaffID, double rating);
}
