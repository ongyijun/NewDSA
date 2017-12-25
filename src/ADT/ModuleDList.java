package ADT;

import domain.Orders;
import domain.DeliveryMan;
import domain.DeliveryOrder;
import domain.WorkStatus;
import java.util.Calendar;
import java.util.Date;

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
        if(!isEmpty()) 
        {
            int No = 1;
            boolean line = false;
            Node currentNode = firstNode;
            Orders ordersHeader = (Orders) currentNode.data;

            System.out.println("\n\nName : " + ordersHeader.getCustomer().getCustName());
            System.out.println("Area : " + ordersHeader.getCustomer().getCustArea());
            System.out.println("\nYour Pending Orders : ");
            System.out.println("*******************************************************");
            System.out.printf("* %3s. * %10s * %16s * %12s *\n", "No", "Order ID", "Restaurant Name", "Total Price");
            System.out.println("*******************************************************");
            

            for (int i = 1 ; i <= TotalEntries ; i++) 
            {
                Orders orders = (Orders) currentNode.data; 
                if (orders.getCustomer().getCustID().equals(CustID) && orders.getOrderStatus().equals("1"))
                {
                    String ORStatus;
                    String TotalPrice;
                    
                    if(No == 1){System.out.println("*      *            *                  *              *");}
                    TotalPrice = String.format("RM %5.2f", orders.getTotal());
                    if(orders.getOrderStatus().equals("1"))
                    {ORStatus = "Pending";}
                    else if(orders.getOrderStatus().equals("2"))
                    {ORStatus = "In Process";}
                    else if(orders.getOrderStatus().equals("3"))
                    {ORStatus = "Deliver";}
                    else if(orders.getOrderStatus().equals("4"))
                    {ORStatus = "Complete";}
                    else
                    {ORStatus = "-";}
                    
                    System.out.printf("* %3s. * %10s * %16s * %12s *\n", No, orders.getOrdersID(), orders.getRestaurant().getRestaurantName(), TotalPrice);
                    if(i == TotalEntries){System.out.println("*      *            *                  *              *");}
                    line = true;
                    No++;
                }
                currentNode = currentNode.next;
            }
            if(line){System.out.println("*      *            *                  *              *");}
            
            if(No == 1)
            {
                System.out.println("*                                                     *");
                System.out.println("*              No Have Any Pending Order              *");
                System.out.println("*                                                     *");
            }
            System.out.println("*******************************************************");
        }
        else
        {
            System.out.println("*******************************************************");
            System.out.println("*                                                     *");
            System.out.println("*              No Have Any Pending Order              *");
            System.out.println("*                                                     *");
            System.out.println("*******************************************************");
        }
    }
    
    @Override
    public void displayCustomerAssignOrderInTable(String CustID)
    {
        if(!isEmpty()) 
        {
            int No = 1;
            boolean line = false;
            Node currentNode = firstNode;
            DeliveryOrder DOHeader = (DeliveryOrder) currentNode.data;

            System.out.println("\n\nName : " + DOHeader.getOrder().getCustomer().getCustName());
            System.out.println("Area : " + DOHeader.getOrder().getCustomer().getCustArea());
            System.out.println("\nYour Pending Orders : ");
            System.out.println("******************************************************************************");
            System.out.printf("* %3s. * %10s * %16s * %20s * %12s *\n", "No", "Order ID", "Restaurant Name", "DeliveryMan Name", "Total Price");
            System.out.println("******************************************************************************");

            for (int i = 1 ; i <= TotalEntries ; i++) 
            {
                DeliveryOrder DO = (DeliveryOrder) currentNode.data;
                
                if (DO.getOrder().getCustomer().getCustID().equals(CustID) && DO.getOrder().getOrderStatus().equals("2"))
                {
                    if(No == 1){System.out.println("*      *            *                  *                      *              *");}
                    String TotalPrice;
                    TotalPrice = String.format("RM %5.2f", DO.getOrder().getTotal());
                    //System.out.println(No+". "+DO.toString());
                    System.out.printf("* %3s. * %10s * %16s * %20s * %12s *\n", No, DO.getOrder().getOrdersID(), DO.getOrder().getRestaurant().getRestaurantName(), DO.getWS().getDM().getStaffName(), TotalPrice);
                    line = true;
                    No++;
                }
                currentNode = currentNode.next;
            }
            if(line){System.out.println("*      *            *                  *                      *              *");}
            
            if(No == 1)
            {
                System.out.println("*                                                                            *");
                System.out.println("*                         No Have Any Pending Order                          *");
                System.out.println("*                                                                            *");
            }
            System.out.println("******************************************************************************");
        }
        else
        {
            System.out.println("***************************************************");
            System.out.println("*                                                 *");
            System.out.println("*     Current Do Not Assign Any Pending Order     *");
            System.out.println("*                                                 *");
            System.out.println("***************************************************");
        }
    }
    
    @Override
    public void displayCustomerDeliverOrderInTable(String CustID)
    {
        if(!isEmpty()) 
        {
            int No = 1;
            boolean line = false;
            Node currentNode = firstNode;
            DeliveryOrder DOHeader = (DeliveryOrder) currentNode.data;

            System.out.println("\n\nName : " + DOHeader.getOrder().getCustomer().getCustName());
            System.out.println("Area : " + DOHeader.getOrder().getCustomer().getCustArea());
            System.out.println("\nYour Pending Orders : ");
            System.out.println("*****************************************************************************************************");
            System.out.printf("* %3s. * %10s * %16s * %20s * %12s * %20s *\n", "No", "Order ID", "Restaurant Name", "DeliveryMan Name", "Total Price", "Time Remain");
            System.out.println("*****************************************************************************************************");

            for (int i = 1 ; i <= TotalEntries ; i++) 
            {
                DeliveryOrder DO = (DeliveryOrder) currentNode.data;
                if (DO.getOrder().getCustomer().getCustID().equals(CustID) && DO.getOrder().getOrderStatus().equals("3"))
                {
                    int difMinutes = 0;
                    int difHours = 0;
                    int diff = 0;

                    try 
                    {
                        Date d1 = DO.getDeliveredDate().getTime();//assign time
                        Date d2 = Calendar.getInstance().getTime();//current time

                        //in milliseconds
                        diff = (int)(d1.getTime() - d2.getTime());

                        difMinutes = diff / (60 * 1000) % 60;
                        difHours = diff / (60 * 60 * 1000) % 24;
                    }
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                    
                    //System.out.println(No+". "+DO.timeRemainToString());
                    if(No == 1){System.out.println("*      *            *                  *                      *              *                      *");}
                    String TotalPrice;
                    String difTime;
                    TotalPrice = String.format("RM %5.2f", DO.getOrder().getTotal());
                    difTime = String.format(difHours + " hours " + difMinutes + " minutes");
                    //System.out.println(No+". "+DO.toString());
                    System.out.printf("* %3s. * %10s * %16s * %20s * %12s * %20s *\n", No, DO.getOrder().getOrdersID(), DO.getOrder().getRestaurant().getRestaurantName(), DO.getWS().getDM().getStaffName(), TotalPrice, difTime);
                    line = true;
                    No++;
                }
                currentNode = currentNode.next;
            }
            if(line){System.out.println("*      *            *                  *                      *              *                      *");}
            
            if(No == 1)
            {
                System.out.println("*                                                                                                   *");
                System.out.println("*                                    No Have Any Pending Order                                      *");
                System.out.println("*                                                                                                   *");
            }
            System.out.println("*****************************************************************************************************");
        }
        else
        {
            System.out.println("*****************************************");
            System.out.println("*                                       *");
            System.out.println("*     No Have Any Order Deliver Yet     *");
            System.out.println("*                                       *");
            System.out.println("*****************************************");
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
        }
        else
        {
            System.out.println("Empty");
        }
    }
    //==========================s====================
}