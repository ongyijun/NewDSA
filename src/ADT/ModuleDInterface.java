package ADT;

import java.util.Calendar;

public interface ModuleDInterface<T>
{
    public void add(T newEntry);
    public T get(int position);
    public int getTotalEntries();
    public T remove(int position);
    public boolean isEmpty();
    public void clear();
    public boolean update(int index, T Entry);
    public boolean updateDMClockInOut(String DMID);
    public void display();
    public void displayCustomerPendingOrderInTable(String CustID);
    public void displayCustomerAssignOrderInTable(String CustID);
    public void displayCustomerDeliverOrderInTable(String CustID);
    public void SortTotalDelivery();
}
