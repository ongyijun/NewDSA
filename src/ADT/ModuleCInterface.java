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
    public T remove(int givenPosition);
}
