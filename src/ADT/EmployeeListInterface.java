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
    public void add(T newEntry);
    public T get(int position);
    public int getTotalEntries();
    public boolean isEmpty();
    public void clear();
    public void SortMostExperienceDeliveryMan();
}
