/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import domain.Payment;
import domain.OrderDetail;
import domain.Orders;
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
            for(int i=1 ; i<=TotalEntries&&found==false ; i++){
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
                    break;
                }
                tempNode = tempNode.next;
            }
            if(found==false){
                lastNode.next = newNode;
                newNode.previous = lastNode;
                lastNode = lastNode.next;
                TotalEntries++;
            }
        }
        SortOrderDetail();
    }
    
    public T get(int position){
        T value = null;
        if(isEmpty()){
            return value;
        }
        else if(position > 0 && position <= TotalEntries){
            Node tempNode;
            tempNode = getRecordAt(position);
            value = tempNode.data;
        }
        else{
            System.out.println("No Record In List");
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
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else if(!isEmpty()&&TotalEntries==2){
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
                            T value = tempNode.data;
                            tempNode.data=currentNode.data;
                            currentNode.data = value;
                        }
                    }
                }
            }
        }
    }
    
    private void SortOrderQuantity(){
        Node currentNode = firstNode;
        Node tempNode = null;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else{
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
                        if(temp.getTotalQuantity()>current.getTotalQuantity()){
                            T value = tempNode.data;
                            tempNode.data=currentNode.data;
                            currentNode.data = value;
                        }
                    }
                }
            }
        }
    }
    
    private void SortOrderDate(){
        Node currentNode = firstNode;
        Node tempNode = null;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else{
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
        }
    }
    
    public T remove(int position){
        T value = null;
        Node tempNode;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else if(position>=1&&position<=TotalEntries){
            tempNode = getRecordAt(position);
            value = tempNode.data;
            if(position==1){
                if(firstNode.next!=null){
                    firstNode = tempNode.next;
                    tempNode.next.previous = null;
                }
                else{
                    clear();
                    TotalEntries++; 
                    // totalEntries already become 0 by clear but bottom stil contains totalentries--
                    // if did not contains totalentries ++, totalentries will become -1
                }
            }
            else if(position==TotalEntries){
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
    
    public String GetCurrentOrderID(){
        String nextID = "";
        if(isEmpty()){
                nextID = "OR000001";
        }
        else{
            Orders order = (Orders)lastNode.data;
            String currentID = order.getOrdersID();
            int ID = Integer.parseInt(currentID.replace("OR", ""));
            ID++;
            nextID = "OR" + String.format("%06d",ID);
        }
        return nextID;
    }
    public String GetCurrentPaymentID(){
        String nextID = "";
        if(isEmpty()){
                nextID = "PA000001";
        }
        else{
            Payment payment = (Payment)lastNode.data;
            String currentID = payment.getPaymentID();
            int ID = Integer.parseInt(currentID.replace("PA", ""));
            ID++;
            nextID = "PA" + String.format("%06d",ID);
        }
        return nextID;
    }
    
    public boolean GenerateDetailReportByQuantity(java.util.Calendar comparecal){
        boolean success = false;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else{
            SortOrderQuantity();
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            
            String reporttitle = "Daily Order Detailed Report Based on Quantity";
            System.out.printf("%78s of %s\n",reporttitle,dateFormat.format(comparecal.getTime()));
            System.out.printf("%64s%s\n",String.format("Generate Date:"),dateFormat.format(cal.getTime()));
            System.out.println("\t\t------------------------------------------------------------------------------------------");
            System.out.println("\t\t-   No.   -     Customer     -     Restaurant     -     Total     -          Order       -");
            System.out.println("\t\t-         -       Name       -        Name        -    Quantity   -        Date Time     -");
            System.out.println("\t\t------------------------------------------------------------------------------------------");
            Node tempNode = firstNode;
            int j=0;
            for(int i=1 ; i<=TotalEntries ; i++){
                Orders record = (Orders)tempNode.data;
                if(dateFormat.format(record.getOrdersDateTime().getTime()).compareTo(dateFormat.format(comparecal.getTime()))==0&&record.getOrderStatus().equals("4")){
                    System.out.printf("\t\t- %4d    -",j+1);
                    System.out.printf("%14s    -",record.getCustomer().getCustName());
                    System.out.printf("%15s     -",record.getRestaurant().getRestaurantName());
                    System.out.printf("%12d   -",record.getTotalQuantity());
                    System.out.printf("%20s  -\n",dateFormat1.format(record.getOrdersDateTime().getTime()));
                    System.out.println("\t\t------------------------------------------------------------------------------------------");
                    j++;
                    success = true;
                }
                tempNode = tempNode.next;
            }
            System.out.printf("%103s%d\n",String.format("Record Count : "),j);
        }
        return success;
    }
    
    public boolean GenerateDetailReportByTime(java.util.Calendar comparecal){
        boolean success = false;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else{
            SortOrderDate();
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String reporttitle = "Daily Order Detailed Report Based on Time";
            System.out.printf("%78s of %s\n",reporttitle,dateFormat.format(comparecal.getTime()));
            System.out.printf("%64s%s\n",String.format("Generate Date:"),dateFormat.format(cal.getTime()));
            System.out.println("\t\t------------------------------------------------------------------------------------------");
            System.out.println("\t\t-   No.   -     Customer     -     Restaurant     -     Total     -          Order       -");
            System.out.println("\t\t-         -       Name       -        Name        -    Quantity   -        Date Time     -");
            System.out.println("\t\t------------------------------------------------------------------------------------------");
            Node tempNode = firstNode;
            int j=0;
            for(int i=1 ; i<=TotalEntries ; i++){
                Orders record = (Orders)tempNode.data;
                if(dateFormat.format(record.getOrdersDateTime().getTime()).compareTo(dateFormat.format(comparecal.getTime()))==0&&record.getOrderStatus().equals("4")){
                    System.out.printf("\t\t- %4d    -",j+1);
                    System.out.printf("%14s    -",record.getCustomer().getCustName());
                    System.out.printf("%15s     -",record.getRestaurant().getRestaurantName());
                    System.out.printf("%12d   -",record.getTotalQuantity());
                    System.out.printf("%20s  -\n",dateFormat1.format(record.getOrdersDateTime().getTime()));
                    System.out.println("\t\t------------------------------------------------------------------------------------------");
                    j++;
                    success = true;
                }
                tempNode = tempNode.next;
            }
            System.out.printf("%103s%d\n",String.format("Record Count : "),j);
        }
        return success;
    }
    
    public double editCart(int newquantity, int position, double subtotal){
        double newsubtotal = 0.00;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else{
            Node currentNode = getRecordAt(position);
            OrderDetail currentDetail = (OrderDetail) currentNode.data;
            currentDetail.setQuantity(newquantity);
            double currentFoodTotal = currentDetail.getFoodTotal();
            double newFoodTotal = newquantity*currentDetail.getFood().getFoodPrice();
            double currentSubtotal = subtotal;
            newsubtotal = currentSubtotal-currentFoodTotal+newFoodTotal;
            currentDetail.setFoodTotal(newFoodTotal);
            currentDetail.setQuantity(newquantity);
            currentNode.data = (T)currentDetail;
            SortOrderDetail();
        }
        return newsubtotal;
    }
    
    public double removeCartFood(int position, double subtotal){
        double newsubtotal = 0.00;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else{
            Node currentNode = getRecordAt(position);
            OrderDetail currentDetail = (OrderDetail) currentNode.data;
            double currentFoodTotal = currentDetail.getFoodTotal();
            newsubtotal = subtotal-currentFoodTotal;
            remove(position);
            SortOrderDetail();
        }
        return newsubtotal;
    }
    
    private Node getRecordAt(int position){
        Node tempNode = firstNode;
        if(isEmpty()){
            System.out.println("No Record In List");
        }
        else if(position>=1&&position<=TotalEntries){
            for(int i=1; i<position ; i++){
                tempNode = tempNode.next;
            }
        }
        return tempNode;
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
