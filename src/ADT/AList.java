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
            System.out.println("No data.");
        }
    }
    
    public void sortNormal(){ //Sort based on ascending order of foodid
        if(!isEmpty()){
            Node cNode = fNode;
            Node bNode = null;
            Node aNode = null;
            Node temp = null;
            Food f = null;
            Food f2 = null;
            if(numOfEntries>2){
                for(int i=1;i<=numOfEntries;i++){
                    
                    temp=cNode;
                    int k=i+1;
                    
                    while(k<=numOfEntries){
                        temp=temp.next;
                        Food cf=(Food)cNode.data;
                        int id = Integer.parseInt(cf.getFoodID().replaceAll("\\D+", ""));
                        Food tempf=(Food)temp.data;
                        int tempid = Integer.parseInt(tempf.getFoodID().replaceAll("\\D+", ""));
                        
                        if(id>tempid){
                            T data=temp.data;
                            temp.data=cNode.data;
                            cNode.data=data;
                        }
                        k++;
                    }
                    cNode=cNode.next;
                }
            }else if(numOfEntries==2){
                f = (Food) cNode.data;
                int id1 = Integer.parseInt(f.getFoodID().replaceAll("\\D+", ""));
                f2 = (Food) cNode.next.data; 
                int id2 = Integer.parseInt(f2.getFoodID().replaceAll("\\D+", ""));
                for(int i=1; i<=numOfEntries;i++){
                    if(cNode!=null){
                        if(id1>id2){
                            bNode=cNode.next;
                            cNode.next=aNode;
                            aNode=cNode;
                            cNode=bNode;
                        }else{
                            aNode=cNode;
                        }
                        fNode=aNode;  
                    }
                }  
            }
        }else{
            System.out.println("No data.");
        }
    }
    
    public void sortPromotional(){// Sort the food menu based on the promotional status
        if(!isEmpty()){
            Node cNode = fNode;
            Node bNode = null;
            Node aNode = null;
            Node temp = null;
            Food f = null;
            Food f2 = null;
            if(numOfEntries>2){
                for(int i=1;i<=numOfEntries;i++){
                    
                    temp=cNode;
                    int k=i+1;
                    
                    while(k<=numOfEntries){
                        temp=temp.next;
                        Food cf=(Food)cNode.data;
                        Food tempf=(Food)temp.data;
                        
                        if(cf.getpStatus()<tempf.getpStatus()){
                            T data=temp.data;
                            temp.data=cNode.data;
                            cNode.data=data;
                        }
                        k++;
                    }
                    cNode=cNode.next;
                }
            }else if(numOfEntries==2){
                f = (Food) cNode.data;
                f2 = (Food) cNode.next.data; 
                for(int i=1; i<=numOfEntries;i++){
                    if(cNode!=null){
                        if(f.getpStatus()<f2.getpStatus()){
                            bNode=cNode.next;
                            cNode.next=aNode;
                            aNode=cNode;
                            cNode=bNode;
                        }else{
                            aNode=cNode;
                        }
                        fNode=aNode;  
                    }
                }  
            }
        }else{
            System.out.println("No data.");
        }
    }
    
    public void sortRestaurantByArea(){//Sort the restaurant based on area
        if(!isEmpty()){
            Node cNode = fNode;
            Node bNode = null;
            Node aNode = null;
            Node temp = null;
            Restaurant r = null;
            Restaurant r1 = null;
            if(numOfEntries>2){
                for(int i=1;i<=numOfEntries;i++){
                    
                    temp=cNode;
                    int k=i+1;
                    
                    while(k<=numOfEntries){
                        temp=temp.next;
                        Restaurant cr=(Restaurant)cNode.data;
                        Restaurant tempr=(Restaurant)temp.data;
                        
                        if(cr.getArea().compareTo(tempr.getArea())>0){
                            T data=temp.data;
                            temp.data=cNode.data;
                            cNode.data=data;
                        }
                        k++;
                    }
                    cNode=cNode.next;
                }
            }else if(numOfEntries==2){
                r = (Restaurant) cNode.data;
                r1 = (Restaurant) cNode.next.data; 
                for(int i=1; i<=numOfEntries;i++){
                    if(cNode!=null){
                        if(r.getArea().compareTo(r1.getArea())>0){
                            bNode=cNode.next;
                            cNode.next=aNode;
                            aNode=cNode;
                            cNode=bNode;
                        }else{
                            aNode=cNode;
                        }
                        fNode=aNode;  
                    }
                }  
            }
        }else{
            System.out.println("No data.");
        }
    }
    
    
}