package ADT;

import domain.Orders;
import domain.DeliveryMan;
import domain.DeliveryOrder;
import domain.WorkStatus;

public class ModuleDList<T> implements ModuleDInterface<T> 
{
    private Node firstNode;
    private Node lastNode;
    private int TotalEntries;
    
    public class Node 
    {
        private Node previous;
        private T data;
        private Node next;

        private Node(T data) {this.previous = null;this.data = data; this.next = null;}

        private Node(T data, Node next) {this.data = data; this.next = next;}

        private Node(Node previous, T data) {this.data = data; this.previous = previous;}

        private Node(Node previous, T data, Node next) {this.data = data; this.previous = previous; this.next = next;}
        
        private void setData(T data) {this.data = data;}
        
        private void setNext(Node next) {this.next = next;}
        
        private void setPrevious(Node previous) {this.previous = previous;}
    }
    
    //Basic Operation===============================
    @Override
    public void add(T newEntry) 
    {
        Node node = new Node(newEntry);
        if (isEmpty()) 
        {
            firstNode = node;
            lastNode = node;
        }
        else 
        {
            node.previous = lastNode;
            lastNode.next = node;
            lastNode = lastNode.next;
        }
        TotalEntries++;
    }
    
    @Override
    public T get(int position) 
    { 
        T getResult = null;
        
        if (position > 0 && position <= TotalEntries) 
        {
            Node tempNode = firstNode;
            for (int i = 1; i < position; i++) 
            {
                tempNode = tempNode.next;
            }
            getResult = tempNode.data;
        } 
        else 
        {
            System.out.println("List Out of Range!");
        }
        return getResult;
    }
    
    @Override
    public boolean update(int position, T UpdateItem)
    {
        if(position >= 1 && position <= TotalEntries)
        {
            Node currentNode = firstNode;
            int looping = 1;
            
            if(TotalEntries == 1 && position == 1)
            {
                currentNode.setData(UpdateItem);
                return true;
            }
            else
            {
                while(looping != position)
                {
                    currentNode = currentNode.next;
                    looping++;
                }
                
                currentNode.setData(UpdateItem);
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public int getTotalEntries() 
    {
        return TotalEntries;
    }
    
    @Override
    public T remove(int position) 
    {
        T result = null;

        if ((position >= 1) && (position <= TotalEntries)) 
        {
            if (position == 1) 
            {
                result = firstNode.data;
                firstNode = firstNode.next;
                if (firstNode != null) 
                {
                    firstNode.previous = null;
                }
            } 
            else 
            {
                Node nodeBefore = firstNode;
                
                for (int i = 1; i < position - 1; ++i) 
                {
                    nodeBefore = nodeBefore.next;
                }
                
                result = nodeBefore.next.data;
                Node nodeAfter = nodeBefore.next.next;
                nodeBefore.next = nodeAfter;
                
                if (nodeAfter != null) 
                {
                    nodeAfter.previous = nodeBefore;
                }
            }
            TotalEntries--;
        }
        return result;
    }
    
    
    
    @Override
    public boolean isEmpty()
    {
        return TotalEntries == 0;
    }
    
    @Override
    public void clear() 
    {
        firstNode = null;
        lastNode = null;
        TotalEntries = 0;
    }
    //Basic Operation===============================
    //==============================================
    
    @Override//Update DeliveryMen Clock In / Clock Out
    public boolean updateDMClockInOut(String DMID)
    {
        Node currentNode = firstNode;
        boolean updated = false;
        
        for (int i = 1 ; i <= TotalEntries ; i++) 
        {
            DeliveryMan DM = (DeliveryMan) currentNode.data;
            if (DM.getStaffID().equals(DMID)) 
            {
                if(DM.getCurrentAvailable().equals("Not Available"))
                {
                    DM.setCurrentAvailable("Available");
                    updated = true;
                    currentNode.data = (T)DM;
                }
                else if(DM.getCurrentAvailable().equals("Available") || DM.getCurrentAvailable().equals("Break"))
                {
                    DM.setCurrentAvailable("Not Available");
                    updated = true;
                    currentNode.data = (T)DM;
                }
            }
            currentNode = currentNode.next;
        }
        return updated;
    }
    
    public void displayPendingDeliverOrder(String DMID)
    {
        Node currentNode = firstNode;
        
        for (int i = 1 ; i <= TotalEntries ; i++) 
        {
            DeliveryOrder DO = (DeliveryOrder) currentNode.data;
            if (DO.getWS().getDM().getStaffID().equals(DMID) && DO.getDeliveryStatus().equals("Pending")) 
            {
                System.out.println(DO.getOrder().getOrdersID());
            }
        }
    }
    
    @Override
    public void displayCustomerPendingOrderInTable(String CustID)
    {
        int No = 1;
        Node currentNode = firstNode;
        Orders ordersHeader = (Orders) currentNode.data;
        
        System.out.println("Name : " + ordersHeader.getCustomer().getCustName());
        System.out.println("Area : " + ordersHeader.getCustomer().getCustArea());
        System.out.println();
        
        for (int i = 1 ; i <= TotalEntries ; i++) 
        {
            Orders orders = (Orders) currentNode.data; 
            if (orders.getCustomer().getCustID().equals(CustID) && orders.getOrderStatus().equals("1"))
            {
                System.out.println(No+". "+orders.toString());
                No++;
            }
            currentNode = currentNode.next;
        }
    }
    
    @Override
    public void displayCustomerAssignOrderInTable(String CustID)
    {
        int No = 1;
        Node currentNode = firstNode;
        DeliveryOrder DOHeader = (DeliveryOrder) currentNode.data;
        
        System.out.println("Name : " + DOHeader.getOrder().getCustomer().getCustName());
        System.out.println("Area : " + DOHeader.getOrder().getCustomer().getCustArea());
        
        for (int i = 1 ; i <= TotalEntries ; i++) 
        {
            DeliveryOrder DO = (DeliveryOrder) currentNode.data;
            if (DO.getOrder().getCustomer().getCustID().equals(CustID) && DO.getOrder().getOrderStatus().equals("2"))
            {
                System.out.println(No+". "+DO.toString());
                No++;
            }
            currentNode = currentNode.next;
        }
    }
    
    @Override
    public void displayCustomerDeliverOrderInTable(String CustID)
    {
        int No = 1;
        Node currentNode = firstNode;
        DeliveryOrder DOHeader = (DeliveryOrder) currentNode.data;
        
        System.out.println("Name : " + DOHeader.getOrder().getCustomer().getCustName());
        System.out.println("Area : " + DOHeader.getOrder().getCustomer().getCustArea());
        
        for (int i = 1 ; i <= TotalEntries ; i++) 
        {
            DeliveryOrder DO = (DeliveryOrder) currentNode.data;
            if (DO.getOrder().getCustomer().getCustID().equals(CustID) && DO.getOrder().getOrderStatus().equals("3"))
            {
                //System.out.println(No+". "+DO.timeRemainToString());
                No++;
            }
            currentNode = currentNode.next;
        }
    }
    
    @Override
    public void display()
    {
        int i=1;
        if (TotalEntries == 0) 
        {
            System.out.println("empty");
            return;
        }
        
        Node currentNode = firstNode;
        
        while (currentNode.next != null)
        {   
            System.out.println(i+". "+currentNode.data.toString());
            currentNode = currentNode.next;
            i++;
        }
        System.out.println(TotalEntries+". "+currentNode.data.toString());
    }
    
    @Override
    public void SortTotalDelivery()
    {
        if(!isEmpty()) 
        {
            Node curNode = firstNode;
            Node tempNode = null;
            int i = 1 ;
            
            while( i <= TotalEntries)
            {
                tempNode = curNode;
                int j = i + 1 ;
                
                while( j <= TotalEntries)
                {
                    tempNode = tempNode.next;
                    WorkStatus curWS = (WorkStatus)curNode.data;
                    WorkStatus tempWS = (WorkStatus)tempNode.data;

                    if(tempWS.getTotalDeliveredOrder() > curWS.getTotalDeliveredOrder())
                    {
                        T arrange = tempNode.data;
                        tempNode.data=curNode.data;
                        curNode.data = arrange;
                    }//End If Loop
                    j++;
                }//End While Loop
                curNode = curNode.next;
                i++;
            }//End While Loop
        }//End If Loop
    }
    //==========================s====================
}