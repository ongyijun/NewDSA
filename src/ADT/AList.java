/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import domain.Food;
import domain.Restaurant;
/**
 *
 * @author HP
 * @param <T>
 */
public class AList<T> implements AListInterface<T> {
    private int numOfEntries;
    private Node fNode;
    
    private class Node{
        private T data; 
        private Node next;
        
        public Node(T data){
            this.data = data;
            this.next = null;
        }
        
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    
    public boolean isEmpty(){
        return numOfEntries == 0;
    }
    
    public int getNumOfEntries(){
        return numOfEntries;
    }
    
    public void clear() {
        fNode = null;
        numOfEntries = 0;
  }
    
    public void add(T entry){
        Node node = new Node(entry);
        if(isEmpty()){
            fNode = node;
        }else{
            Node cNode = fNode;
            while(cNode.next != null){
                cNode = cNode.next;
            }
            cNode.next = node;
        }
        numOfEntries++;
    }
    
    public T get(int pos){
        T data = null;
        if((pos>0) && (pos <= numOfEntries)){
            Node currentNode = fNode;
            for (int i = 0; i < pos - 1; ++i) {
                currentNode = currentNode.next;		
            }
            data=currentNode.data;
        }else if(isEmpty()){
            return data;
        }
        return data;
    }
    
    public boolean replace(int pos, T entry){
        boolean successful = true;
        
        if ((pos>0)&&(pos<=numOfEntries)) {
            Node currentNode = fNode;
                for (int i=0; i<pos -1; ++i) {
                    currentNode = currentNode.next;
                }
            currentNode.data = entry;
        } else {
            successful = false;
        }   
        return successful;
    }
    
    public void delete(int pos){
        if(!isEmpty()){
            if((pos>0)&&(pos<=numOfEntries)){
                if(pos>1){
                    Node temp = fNode;
                    for (int i=1; i<pos -1; ++i) {
                        temp = temp.next;
                    }
                    temp.next=temp.next.next;
                }else{
                    if(fNode.next!=null){
                        fNode = fNode.next;
                    }else{
                        fNode = null;
                    }
                }
                numOfEntries--;
            }
        }else{
            System.out.println("No data.");
        }
    }
    
    public void sortNewest(){ //Sort from newest food item to the oldest food item
        Node cNode = fNode;
        /*
        Food f1 =(Food)fNode.data;
        int id = Integer.parseInt(f1.getFoodID().replaceAll("\\D+", ""));
        */
        Node bNode = null;
        Node aNode = null;
        if(!isEmpty()){
                for(int i=1 ; i<=numOfEntries ; i++){       
                    if(cNode!=null){
                        if(numOfEntries>1){
                            aNode=cNode.next;
                            cNode.next=bNode;
                            bNode=cNode;
                            cNode=aNode;
                        }else if(numOfEntries==1){
                            bNode=cNode;
                        }
                    }
                    fNode = bNode;
                }
            }else{
            System.out.println("No data inside.");
        }
    }
    
    public void sortNormal(){
        
    }
    
    public void sortPromotional(){
        if(!isEmpty()){
            Node cNode = fNode;
            Node bNode = null;
            Node aNode = null;
            Food f = null;
            Food f2 = null;
            if(numOfEntries>2){
                for(int i=1;i<=numOfEntries;i++){
                    if(cNode.next!=null){
                        for(int j=1;j<=numOfEntries;j++){
                            f = (Food) cNode.data;
                            f2 = (Food)cNode.next.data;
                            if(f.getpStatus()<f2.getpStatus()){
                                aNode=cNode.next;
                                cNode.next=bNode;
                                bNode=cNode;
                                cNode=aNode;
                            }
                        } 
                        fNode=bNode;
                    }
                    //fNode=bNode;
                } 
            }else if(numOfEntries==2){
                f = (Food) cNode.data;
                f2 = (Food) cNode.next.data; 
                for(int i=1; i<=numOfEntries;i++){
                    if(cNode!=null){
                        if(f.getpStatus()<f2.getpStatus()){
                            aNode=cNode.next;
                            cNode.next=bNode;
                            bNode=cNode;
                            cNode=aNode;
                        }else{
                            bNode=cNode;
                        }
                        fNode=bNode;  
                    }
                }  
            }
        }
    }
    
    
}