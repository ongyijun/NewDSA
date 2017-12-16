/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import ADT.EmployeeList;
import ADT.EmployeeListInterface;
import domain.DeliveryMan;
import java.util.Calendar;

/**
 *
 * @author MY
 */
public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EmployeeListInterface <DeliveryMan> DM = new EmployeeList<>();
        Calendar date3 = Calendar.getInstance();
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        Calendar date4 = Calendar.getInstance();
        date3.add(Calendar.MINUTE, 5);
        date2.add(Calendar.MINUTE, 3);
        date4.add(Calendar.MINUTE, 1);
        DeliveryMan newEntry = new DeliveryMan(0, "", "", "", "", "", "", "", 'C', "", "", "", "", 0, 0, date1);
        DeliveryMan newEntry3 = new DeliveryMan(0, "", "", "", "", "", "", "", 'C', "", "", "", "", 0, 0, date3);
        DeliveryMan newEntry2 = new DeliveryMan(0, "", "", "", "", "", "", "", 'C', "", "", "", "", 0, 0, date2);
        DeliveryMan newEntry4 = new DeliveryMan(0, "", "", "", "", "", "", "", 'C', "", "", "", "", 0, 0, date4);
        DM.add(newEntry3);
        DM.add(newEntry2);
        DM.add(newEntry);
        
        System.out.println(DM.get(1).getJoinDate().getTime());
        System.out.println(DM.get(2).getJoinDate().getTime());
        System.out.println(DM.get(3).getJoinDate().getTime()+"\n");
        
        DM.SortMostExperienceDeliveryMan();
        
        DM.add(newEntry4);
        System.out.println("\n"+DM.get(1).getJoinDate().getTime());
        System.out.println(DM.get(2).getJoinDate().getTime());
        System.out.println(DM.get(3).getJoinDate().getTime());
        System.out.println(DM.get(4).getJoinDate().getTime()+"\n");
        
        DM.SortMostExperienceDeliveryMan();
        
        System.out.println("\n"+DM.get(1).getJoinDate().getTime());
        System.out.println(DM.get(2).getJoinDate().getTime());
        System.out.println(DM.get(3).getJoinDate().getTime());
        System.out.println(DM.get(4).getJoinDate().getTime());
        
    }
    
}
