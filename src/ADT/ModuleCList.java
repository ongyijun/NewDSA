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
            boolean find = false;
            for(){
                for(int i=0 ; i<TotalEntries&&find==false ; i++){
                    Node node1 = getNodeAt(i+1);
                    Node node2 = lastNode;

                    OrderDetail detail1 = (OrderDetail)node1.data;
                    OrderDetail detail2 = (OrderDetail)node2.data;
                    if(detail1.getFoodTotal()<detail2.getFoodTotal()){
                        node2.next = node1;
                        node1.previous = node2;
                        find=true;
                    }
                }
            }
        }
    }
    
    public Node getNodeAt(int givenPosition){
        Node currentNode = firstNode;
        
        for(int i=1 ; i<givenPosition ; i++){
            currentNode = currentNode.next;
        }
        return currentNode;
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
