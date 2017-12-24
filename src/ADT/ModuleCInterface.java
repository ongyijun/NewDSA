/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author ong
 */
public interface ModuleCInterface<T> {
    public void add(T newEntry);
    public void addDetail(T newEntry);
    public T get(int position);
    public int getTotalEntries();
    public boolean isEmpty();
    public void clear();
    public void SortOrderDetail();
    public T remove(int position);
    public String GetCurrentOrderID();
    public String GetCurrentPaymentID();
    public boolean GenerateDetailReportByQuantity(java.util.Calendar comparecal);
    public boolean GenerateDetailReportByTime(java.util.Calendar comparecal);
    public double editCart(int newquantity, int position, double subtotal);
    public double deleteFood(int position, double subtotal);
}
