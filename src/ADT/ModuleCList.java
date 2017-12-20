/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import domain.Food;
import domain.OrderDetail;
import domain.Orders;
import domain.Restaurant;
import domain.Customer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author ong
 */
public class ModuleCList<T> implements ModuleCInterface<T> {
    private Node firstNode;
    private Node lastNode;
    private int TotalEntries;
    
    public ModuleCList(){
        clear();
    }
    
    public void add(T newEntry){
        Node newNode = new Node(newEntry);
        if(isEmpty()){
            firstNode = newNode;
            lastNode = newNode;
        }
        else{
            newNode.previous = lastNode;
            lastNode.next = newNode;
            lastNode = lastNode.next;
        }
        TotalEntries++;
    }
    
    public void addDetail(T newEntry){
        Node newNode = new Node(newEntry);
        boolean found = false;
        int previousqty = 0,newqty = 0;
        double previousfoodtotal = 0.00,newfoodtotal = 0.00;
        if(isEmpty()){
            firstNode = newNode;
            lastNode = newNode;
            TotalEntries++;
        }
        else{
            OrderDetail newDetail = (OrderDetail)newNode.data;
            Node tempNode = firstNode;
            while(tempNode!=null&&found==false){
                OrderDetail testDetail = (OrderDetail)tempNode.data;
                if(newDetail.getFood().getFoodID().equals(testDetail.getFood().getFoodID())){
                    found = true;
                    previousqty = testDetail.getQuantity();
                    previousfoodtotal = testDetail.getFoodTotal();
                    newqty = newDetail.getQuantity()+previousqty;
                    newfoodtotal = newDetail.getFoodTotal()+previousfoodtotal;
                    testDetail.setQuantity(newqty);
                    testDetail.setFoodTotal(newfoodtotal);
                    tempNode.data = (T)testDetail;
                }
                tempNode = tempNode.next;
            }
            if(found==false){
                newNode.previous = lastNode;
                lastNode.next = newNode;
                lastNode = lastNode.next;
                TotalEntries++;
            }
        }
        SortOrderDetail();
    }
    
    public T get(int newposition){
        T value = null;
        if(isEmpty()){
            return value;
        }
        else if(newposition > 0 && newposition <= TotalEntries){
            Node tempnode = firstNode;
            for(int i=1 ; i<newposition ; i++){
                tempnode = tempnode.next;
            }
            value = tempnode.data;
        }
        else{
            System.out.println("Out Of Bound");
        }
        return value;
    }
    public int getTotalEntries(){
        return TotalEntries;
    }
    public boolean isEmpty(){
        return TotalEntries == 0;
    }
    public void clear(){
        firstNode = null;
        lastNode = null;
        TotalEntries = 0;
    }
    
    public void SortOrderDetail(){
        if(!isEmpty()&&TotalEntries==2){
                OrderDetail first = (OrderDetail)firstNode.data;
                OrderDetail last = (OrderDetail)lastNode.data;
                if(last.getFoodTotal()>first.getFoodTotal()){
                    lastNode.next = firstNode;
                    firstNode.previous = lastNode;
                    firstNode = lastNode;
                    lastNode = lastNode.next;
            }
        }
        else if(!isEmpty()&&TotalEntries>2){
            Node currentNode = firstNode;
            Node tempNode = null;
            for(int i=1 ; i<=TotalEntries ; i++){
                if(i>1&&currentNode.next!=null){
                    currentNode = currentNode.next;
                }
                tempNode = currentNode;
                for(int j=i+1 ; j<=TotalEntries ; j++){
                    if(tempNode.next!=null){
                        tempNode = tempNode.next;
                        OrderDetail current = (OrderDetail)currentNode.data;
                        OrderDetail temp = (OrderDetail)tempNode.data;
                        if(temp.getFoodTotal()>current.getFoodTotal()){
                            /*Node nextNode = tempNode.next;
                            Node previousNode = tempNode.previous;
                            if(i==1){
                                tempNode.next = currentNode.next;
                                currentNode.next.previous = tempNode;
                                nextNode.previous = currentNode;
                                currentNode.next = nextNode;
                                currentNode.previous = previousNode;
                                previousNode.next = currentNode;
                                firstNode = tempNode;
                            }
                            else{
                                Node previousNode1 = currentNode.previous;
                                tempNode.next = currentNode.next;
                                currentNode.next.previous = tempNode;
                                tempNode.previous = previousNode1;
                                previousNode1.next = tempNode;
                                nextNode.previous = currentNode;
                                currentNode.next = nextNode;
                                currentNode.previous = previousNode;
                                previousNode.next = currentNode;
                            }*/
                            
                            T value = tempNode.data;
                            tempNode.data=currentNode.data;
                            currentNode.data = value;
                        }
                    }
                }
            }
        }
    }
    
    public T remove(int givenPosition){
        T value = null;
        Node tempNode = firstNode;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else if(givenPosition>=1&&givenPosition<=TotalEntries){
            for(int i=1; i<givenPosition ; i++){
                tempNode = tempNode.next;
            }
            value = tempNode.data;
            if(givenPosition==1){
                firstNode = tempNode.next;
                tempNode.next.previous = null;
            }
            else if(givenPosition==TotalEntries){
                lastNode = tempNode.previous;
                tempNode.previous.next = null;
            }
            else{
                tempNode.previous.next = tempNode.next;
                tempNode.next.previous = tempNode.previous;
            }
            TotalEntries--;
        }
        return value;
    }
    
    public boolean GenerateDetailReport(String day){
        boolean success = false;
        Node currentNode = firstNode;
            Node tempNode = null;
            for(int i=1 ; i<=TotalEntries ; i++){
                if(i>1&&currentNode.next!=null){
                    currentNode = currentNode.next;
                }
                tempNode = currentNode;
                for(int j=i+1 ; j<=TotalEntries ; j++){
                    if(tempNode.next!=null){
                        tempNode = tempNode.next;
                        Orders current = (Orders)currentNode.data;
                        Orders temp = (Orders)tempNode.data;
                        if(temp.getOrdersDateTime().getTime().compareTo(current.getOrdersDateTime().getTime())>0){
                            T value = tempNode.data;
                            tempNode.data=currentNode.data;
                            currentNode.data = value;
                        }
                    }
                }
            }
            Calendar comparecal = Calendar.getInstance();
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            try{
                Date date = dateFormat.parse(day);
                comparecal.setTime(date);
            }catch(ParseException ex){
                System.out.print(ex);
            }
            String reporttitle = "Daily Summary Report";
            System.out.printf("%50s%20s\n",reporttitle,dateFormat.format(cal.getTime()));
            System.out.println("\t\t--------------------------------------------------------------------");
            tempNode = firstNode;
            int j=0;
            for(int i=1 ; i<=TotalEntries ; i++){
                Orders record = (Orders)tempNode.data;
                if(dateFormat.format(record.getOrdersDateTime().getTime()).compareTo(dateFormat.format(comparecal.getTime()))==0){
                    System.out.printf("%50s%20s\n",String.format("Customer Name:"),record.getCustomer().getCustName());
                    System.out.printf("%50s%20s\n",String.format("Restaurant Name:"),record.getRestaurant().getRestaurantName());
                    System.out.printf("%50s%16s%.2f\n",String.format("Total:"),String.format("RM"),record.getTotal());
                    System.out.printf("%50s%20s\n",String.format("Order Date:"),dateFormat.format(record.getOrdersDateTime().getTime()));
                    System.out.println("\t\t--------------------------------------------------------------------");
                    j++;
                    success = true;
                }
                tempNode = tempNode.next;
            }
            System.out.printf("%70s%d\n",String.format("Record Count : "),j);
            return success;
    }
    
    private class Node {

        private Node previous;
        private T data;
        private Node next;

        private Node(T data) {
            this.previous = null;
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        private Node(Node previous, T data) {
            this.data = data;
            this.previous = previous;
        }

        private Node(Node previous, T data, Node next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }
}
