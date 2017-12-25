/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author HP
 */
public interface AListInterface<T> {
    public boolean isEmpty();
    public int getNumOfEntries();
    public void clear();
    public void add(T entry);
    public T get(int pos);
    public boolean replace(int pos, T entry);
    public void delete(int pos);
    public void sortNewest();
    public void sortNormal();
    public void sortPromotional();
    public void sortRestaurantByArea();
}
