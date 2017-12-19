/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import domain.DeliveryMan;
import domain.Employee;

/**
 *
 * @author MY
 */
public class EmployeeList<T> implements EmployeeListInterface<T> {

    private Node firstNode;
    private Node lastNode;
    private int TotalEntries;

    public EmployeeList() {
        clear();
    }

    public void add(T newEntry) {   //basic operation
        Node node = new Node(newEntry);
        if (isEmpty()) {
            firstNode = node;
            lastNode = node;
        } else {
            node.previous = lastNode;
            lastNode.next = node;
            lastNode = lastNode.next;
        }
        TotalEntries++;
    }

    public boolean addWithICValidation(T newEntry) {
        Employee e = (Employee) newEntry;
        Node currentNode = firstNode;
        for (int i = 1; i <= TotalEntries; i++) {
            Employee compareE = (Employee) currentNode.data;
            if (e.getStaffIC().equals(compareE.getStaffIC())) {
                return false;
            }
            currentNode = currentNode.next;
        }
        add(newEntry);
        return true;
    }

    public T get(int position) {    //basic operation
        T getResult = null;
        if (position > 0 && position <= TotalEntries) {
            Node tempNode = firstNode;
            for (int i = 1; i < position; i++) {
                tempNode = tempNode.next;
            }
            getResult = tempNode.data;
        } else {
            System.out.println("List Out of Range!");
        }
        return getResult;
    }

    public boolean replace(int givenPosition, T newEntry) {     //basic operation
        boolean success = true;

        if ((givenPosition >= 1) && (givenPosition <= TotalEntries)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.next;
            }
            currentNode.data = newEntry;
        } else {
            success = false;
        }
        return success;
    }

    public T getEmployeeDetails(String StaffID) {
        T result = null;
        Node currentNode = firstNode;
        for (int i = 1; i <= TotalEntries; i++) {
            Employee e = (Employee) currentNode.data;
            if (e.getStaffID().equals(StaffID)) {
                result = currentNode.data;
                i = TotalEntries + 1;
            }
            currentNode = currentNode.next;
        }
        return result;
    }

    public boolean updateDeliveryManDetail(T anEntry) {
        DeliveryMan DM = (DeliveryMan) anEntry;
        Node currentNode = firstNode;
        for (int i = 1; i <= TotalEntries; i++) {
            DeliveryMan compareDM = (DeliveryMan) currentNode.data;
            if (DM.getStaffID().equals(compareDM.getStaffID())) {
                currentNode.data = anEntry;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public T remove(int givenPosition) {        //basic operation
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= TotalEntries)) {
            if (givenPosition == 1) {
                result = firstNode.data;
                firstNode = firstNode.next;
                if (firstNode != null) {
                    firstNode.previous = null;
                }
            } else {
                Node nodeBefore = firstNode;
                for (int i = 1; i < givenPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;
                }
                result = nodeBefore.next.data;
                Node nodeAfter = nodeBefore.next.next;
                nodeBefore.next = nodeAfter;
                if (nodeAfter != null) {
                    nodeAfter.previous = nodeBefore;
                }
            }
            TotalEntries--;
        }
        return result;
    }

    public boolean removeStaff(String StaffID) {
        Node currentNode = firstNode;
        for (int i = 1; i <= TotalEntries; i++) {
            Employee e = (Employee) currentNode.data;
            if (e.getStaffID().equals(StaffID)) {
                remove(i);
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public int getTotalEntries() {  //basic operation
        return TotalEntries;
    }

    public boolean isEmpty() {  //basic operation
        return TotalEntries == 0;
    }

    public void clear() {   //basic operation
        firstNode = null;
        lastNode = null;
        TotalEntries = 0;
    }

    public void SortMostExperienceDeliveryMan() {

        if (!isEmpty()) {       //If the list is Not Empty
            Node DMnode = firstNode;                        //get the first node as current node
            for (int i = 1; i <= TotalEntries; i++) {       //getting the current node
                boolean found = false;
                Node TempNode = firstNode;                  //reset compare node to the first node
                DeliveryMan DM = (DeliveryMan) DMnode.data;

                for (int j = 1; j < i + 1; j++) {             //getting the compare node before the current node
                    DeliveryMan Temp = (DeliveryMan) TempNode.data;
                    Node currentNode = DMnode;              //assign current node to temporary node

                    if (DM.getJoinDate().getTime().compareTo(Temp.getJoinDate().getTime()) < 0) {       //If the current node is smaller than compare node

                        found = true;
                        DMnode = DMnode.next;   // moving the current node to the next node first before sorting

                        currentNode.previous.next = currentNode.next;

                        if (currentNode.next != null) {             //if the temporary current node is not the last node
                            currentNode.next.previous = currentNode.previous;
                        } else {                                  //if the temporary current node is last node, then the last node is the previous of temporary current node
                            lastNode = currentNode.previous;
                        }

                        currentNode.previous = TempNode.previous;
                        currentNode.next = TempNode;
                        if (TempNode.previous != null) {            //if the compare node is not the first node
                            TempNode.previous.next = currentNode;
                        } else {                                  //if the compare node is first node, then the first node will be temporary current node
                            firstNode = currentNode;
                        }
                        TempNode.previous = currentNode;

                        j = i;                                    //after sort the temporary current node with compare node, jump out the for loop
                    }
                    TempNode = TempNode.next;                  //getting next compare node to perform sorting
                }
                if (!found) {
                    DMnode = DMnode.next;                   //getting next current node if not sort performed
                }
            }
        }
    }

    public void SortDeliveryManRating() {

        if (!isEmpty()) {       //If the list is Not Empty
            Node DMnode = firstNode;                        //get the first node as current node
            for (int i = 1; i <= TotalEntries; i++) {       //getting the current node
                boolean found = false;
                Node TempNode = firstNode;                  //reset compare node to the first node
                DeliveryMan DM = (DeliveryMan) DMnode.data;

                for (int j = 1; j < i + 1; j++) {             //getting the compare node before the current node
                    DeliveryMan Temp = (DeliveryMan) TempNode.data;
                    Node currentNode = DMnode;              //assign current node to temporary node

                    if (DM.getRating() > Temp.getRating()) {       //If the current node is bigger than compare node

                        found = true;
                        DMnode = DMnode.next;   // moving the current node to the next node first before sorting

                        currentNode.previous.next = currentNode.next;

                        if (currentNode.next != null) {             //if the temporary current node is not the last node
                            currentNode.next.previous = currentNode.previous;
                        } else {                                  //if the temporary current node is last node, then the last node is the previous of temporary current node
                            lastNode = currentNode.previous;
                        }

                        currentNode.previous = TempNode.previous;
                        currentNode.next = TempNode;
                        if (TempNode.previous != null) {            //if the compare node is not the first node
                            TempNode.previous.next = currentNode;
                        } else {                                  //if the compare node is first node, then the first node will be temporary current node
                            firstNode = currentNode;
                        }
                        TempNode.previous = currentNode;

                        j = i;                                    //after sort the temporary current node with compare node, jump out the for loop
                    }
                    TempNode = TempNode.next;                  //getting next compare node to perform sorting
                }
                if (!found) {
                    DMnode = DMnode.next;                   //getting next current node if not sort performed
                }
            }
        }
    }

    public boolean updateDeliveryManRating(String StaffID, double rating) {
        Node currentNode = firstNode;
        for (int i = 1; i <= TotalEntries; i++) {
            DeliveryMan DM = (DeliveryMan) currentNode.data;
            if (DM.getStaffID().equals(StaffID)) {
                Double Rating = ((DM.getRating() * DM.getTotalRateReceived()) + rating) / (DM.getTotalRateReceived() + 1);
                DM.setRating(Rating);
                DM.setTotalRateReceived(DM.getTotalRateReceived() + 1);
                currentNode.data = (T) DM;
                return true;
            }
        }
        return false;
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
