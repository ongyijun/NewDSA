/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import ADT.*;
import domain.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author MY
 */
public class MainProgram {

    EmployeeListInterface<DeliveryMan> DMList = new EmployeeList<>();
    EmployeeListInterface<Admin> ADList = new EmployeeList<>();
    EmployeeListInterface<HR> HRList = new EmployeeList<>();
    private Employee loginStaff;
    Scanner s = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    // All Menu Start
    public void menu() {

        String selection = "-1";
        System.out.println("**************************************");
        System.out.println("*Welcome to Faster Deliverymen System*");
        System.out.println("**************************************");
        System.out.println("Please Select The Option Below");
        System.out.println("1. Affiliate Register");
        System.out.println("2. Staff Login");
        System.out.println("3. Customer Login");
        System.out.println("4. Affiliate Login");
        System.out.println("0. Exit");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("0")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    //create class at module A and import the module A at this class and den call the method from here
                    //A.RestaurantRegistration();
                    menu();
                    break;
                }
                case "2": {
                    staffMenu();    //calling staff menu interface
                    menu();
                    break;
                }
                case "3": {
                    //C.CustomerLogin();
                    menu();
                    break;
                }
                case "4": {
                    //A.RestaurantLogin(r);
                    /*
                    if (a) {
                        RestaurantMenu(restaurant);
                    }
                     */
                    break;
                }
                case "0": {
                    System.out.println("GoodBye.");
                    break;
                }
                default: {
                    System.out.println("Error, Please Key In Again.\n");
                    break;
                }
            }
        }
    }

    public void staffMenu() {
        Scanner s = new Scanner(System.in);
        boolean login = false;
        while (login == false) {
            System.out.println("*********************************");
            System.out.println("*Welcome to Staff Menu Interface*");
            System.out.println("*********************************");
            System.out.print("Please Enter Staff ID (-1 to back): ");
            String username = s.nextLine();
            if (!username.equals("-1")) {
                boolean success = StaffLogin(username.toUpperCase());
                if (success) {
                    if (loginStaff instanceof DeliveryMan) {
                        System.out.println("\n\n\n");
                        login = true;
                        //DeliveryManMenu(loginStaff);
                    } else if (loginStaff instanceof HR) {
                        login = true;
                        System.out.println("\n\n\n");
                        HRMenu();
                    } else if (loginStaff instanceof Admin) {
                        System.out.println("\n\n\n");
                        login = true;
                        AdminMenu();
                    } else {
                        username = "-1";
                        login = true;
                    }
                } else {
                    System.out.println("Error. Username Not Found!Please Try Again.\n\n");
                }
            }
            if(username.equals("-1")){
                System.out.println("Thank You For Using Our System.\nPress Enter to Continue...");
                s.nextLine();
                login = true;
                System.out.println("\n\n\n");
            }
        }
    }

    public void AdminMenu() {
        String selection = "-1";
        Date date = new Date();
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Welcome Back, " + loginStaff.getStaffName() + "\nCurrent Date:" + dateFormat.format(date) + "\n");  //display login staff information
        System.out.println("Please Select The Option Below");
        System.out.println("1. View Delivery Man Clock In & Clock Out");
        System.out.println("2. Retrieve Delivery Man Pending Delivery");
        System.out.println("3. Generate Total Deliveries Report");
        System.out.println("4. Generate Total Distance Report");
        System.out.println("0. Log Out");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") & !selection.equals("5") && !selection.equals("0")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    //D.ViewDeliverManClockInOut(DMList);
                    AdminMenu();
                    break;
                }
                case "2": {
                    //B.RetrieveDeliveryManPendingDeliveryMenu();
                    AdminMenu();
                    break;
                }
                case "3": {
                    //B.generateTotalDeliveriesReportMenu();
                    AdminMenu();
                    break;
                }
                case "4": {
                    //B.generateTotalDistanceReportMenu();
                    AdminMenu();
                    break;
                }
                case "0": {
                    loginStaff = null;
                    System.out.println("Thank You For Using Our System.\nPress Any Key to Continue...");
                    s.nextLine();
                    System.out.println("\n\n\n");
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...\n");
                    break;
                }
            }
        }
    }

    public void HRMenu() {
        String selection = "-1";
        Date date = new Date();
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Welcome Back, " + loginStaff.getStaffName() + "\nCurrent Date:" + dateFormat.format(date) + "\n"); //display login staff information
        System.out.println("Please Select The Option Below");
        System.out.println("1. Add New Delivery Man");
        System.out.println("2. Add New Admin");
        System.out.println("3. Add New HR");
        System.out.println("4. Update Delivery Man Status");
        System.out.println("5. View Staff Details");
        System.out.println("0. Log Out");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("0")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    boolean success = AddNewEmployee(1);
                    if (success) {
                        System.out.println("New Employee Added Successfully.Press Enter to Continue...");
                        s.nextLine();
                    }
                    System.out.println("\n\n\n");
                    HRMenu();
                    break;
                }
                case "2": {
                    boolean success = AddNewEmployee(2);
                    if (success) {
                        System.out.println("New Employee Added Successfully.Press Enter to Continue...");
                        s.nextLine();
                    }
                    System.out.println("\n\n\n");
                    HRMenu();
                    break;
                }
                case "3": {
                    boolean success = AddNewEmployee(3);
                    if (success) {
                        System.out.println("New Employee Added Successfully.Press Enter to Continue...");
                        s.nextLine();
                    }
                    System.out.println("\n\n\n");
                    HRMenu();
                    break;
                }
                case "4": {
                    System.out.print("Please Enter A Delivery Man ID (-1 to back): ");
                    String ID = s.nextLine();
                    if (!ID.equals("-1")) {

                    }
                    //boolean find = B.DisplayDeliveryManUpdateStatus(ID.toUpperCase());
                    //if (find) {
                    //    DMList = B.getDeliveryMen();
                    //}
                    HRMenu();
                    break;
                }
                case "5": {
                    boolean find = false;
                    while (find == false) {
                        System.out.print("Please Enter A Staff ID (-1 to back): ");
                        String ID = s.nextLine();
                        if (!ID.equals("-1")) {
                            Employee e = DisplayStaffDetails(ID.toUpperCase());
                            if (e == null) {
                                System.out.println("Error. Staff ID Not Found!");
                            } else {
                                find = true;
                                System.out.printf("Staff ID: %s\n"
                                        + "StaffName: %s\n"
                                        + "Staff Phone No: %s\n"
                                        + "Staff Address: %s\n"
                                        + "Staff Position: %s\n"
                                        + "Staff Status: %s\n"
                                        + "Staff Basic Salary: %.2f\n"
                                        + "Press Enter To Continue...", e.getStaffID(), e.getStaffName(), e.getStaffPhNo(), e.getStaffAdds(), e.getStaffPosition(), e.getWorkingStatus(), e.getBasicSalary());
                                s.nextLine();
                            }
                        } else {
                            find = true;
                        }
                    }
                    HRMenu();
                    break;
                }
                case "0": {
                    loginStaff = null;
                    System.out.println("Thank You For Using Our System.\nPress Enter to Continue...");
                    s.nextLine();
                    System.out.println("\n\n\n");
                    break;
                }
                default: {
                    System.out.println("Error, Please Key In Again.");
                    break;
                }
            }
        }
    }
    //All Menu End

    //Miw Start
    public boolean StaffLogin(String username) {   //verify the Staff ussername and password
        boolean login = false;
        for (int i = 1; i <= DMList.getTotalEntries(); i++) {  //verify is it delivery man
            if (DMList.get(i).getStaffID().equals(username)) {   //if found username
                while (login == false) {
                    System.out.print("Please Enter Password (-1 to back): ");
                    String pw = s.nextLine();
                    if (DMList.get(i).getStaffPw().equals(pw)) { //if password correct
                        login = true;
                        System.out.println("Login As " + DMList.get(i).getStaffPosition() + " Successfully!\nPress Enter to Continue...");
                        s.nextLine();
                        loginStaff = DMList.get(i);
                        return true;
                    } else if (pw.equals("-1")) {  //if back
                        loginStaff = null;
                        return true;
                    } else {
                        System.out.println("Error. Wrong Password Entered!Please Try Again.\n");
                    }
                }
            }
        }
        for (int i = 1; i <= HRList.getTotalEntries(); i++) {  //verify is it HR
            if (HRList.get(i).getStaffID().equals(username)) {
                while (login == false) {
                    System.out.print("Please Enter Password (-1 to back): ");
                    String pw = s.nextLine();
                    if (HRList.get(i).getStaffPw().equals(pw)) { //if password correct
                        login = true;
                        System.out.println("Login As " + HRList.get(i).getStaffPosition() + " Successfully!\nPress Enter to Continue...");
                        s.nextLine();
                        loginStaff = HRList.get(i);
                        return true;
                    } else if (pw.equals("-1")) { //if back
                        loginStaff = null;
                        return true;
                    } else {
                        System.out.println("Error. Wrong Password Entered!Please Try Again.\n");
                    }
                }
            }
        }
        for (int i = 1; i <= ADList.getTotalEntries(); i++) {  //verify is it Admin
            if (ADList.get(i).getStaffID().equals(username)) {
                while (login == false) {
                    System.out.print("Please Enter Password (-1 to back): ");
                    String pw = s.nextLine();
                    if (ADList.get(i).getStaffPw().equals(pw)) { //if password correct
                        login = true;
                        System.out.println("Login As " + ADList.get(i).getStaffPosition() + " Successfully!\nPress Enter to Continue...");
                        s.nextLine();
                        loginStaff = ADList.get(i);
                        return true;
                    } else if (pw.equals("-1")) {  //if back
                        loginStaff = null;
                        return true;
                    } else {
                        System.out.println("Error. Wrong Password Entered!Try Again.\n");
                    }
                }
            }
        }
        return false;   //username not found
    }

    public Employee DisplayStaffDetails(String StaffID) {
        boolean find = false;
        for (int i = 0; i < DMList.getTotalEntries(); i++) {
            if (DMList.get(i).getStaffID().equals(StaffID)) {
                return DMList.get(i);
            }
        }
        if (find == false) {
            for (int i = 0; i < HRList.getTotalEntries(); i++) {
                if (HRList.get(i).getStaffID().equals(StaffID)) {
                    return HRList.get(i);
                }
            }
        }
        if (find == false) {
            for (int i = 0; i < ADList.getTotalEntries(); i++) {
                if (ADList.get(i).getStaffID().equals(StaffID)) {
                    return ADList.get(i);
                }
            }
        }
        return null;
    }

    public boolean AddNewEmployee(int choice) {
        String ID = null;
        Calendar joinDate = Calendar.getInstance();
        if (choice == 1) {    //Delivery Man
            ID = String.format("DM%06d", DMList.getTotalEntries() + 1);
        } else if (choice == 2) {  //Admin
            ID = String.format("AD%06d", ADList.getTotalEntries() + 1);
        } else {  //HR
            ID = String.format("HR%06d", HRList.getTotalEntries() + 1);
        }
        System.out.println("\n\nID: " + ID + "(This will be the username)");
        System.out.println("You can exit this interface anytime by enter (-1)");
        System.out.print("Enter Password: ");
        String Pw = s.nextLine();
        if (Pw.equals("-1")) {
            return false;
        }
        System.out.print("Enter Name: ");
        String Name = s.nextLine();
        if (Name.equals("-1")) {
            return false;
        }
        System.out.print("Enter IC: ");
        String IC = s.nextLine();
        if (IC.equals("-1")) {
            return false;
        }
        System.out.print("Enter Phone No: ");
        String PhNo = s.nextLine();
        if (PhNo.equals("-1")) {
            return false;
        }
        char Gender = ' ';
        while (Gender != 'M' && Gender != 'F') {
            System.out.print("Enter Gender(M/F): ");
            String G = s.nextLine();
            if (G.equals("-1")) {
                return false;
            } else {
                Gender = Character.toUpperCase(G.charAt(0));
                if (Gender != 'M' && Gender != 'F') {
                    System.out.println("Please Enter only M or F.");
                }
            }
        }
        System.out.print("Enter Address: ");
        String Adds = s.nextLine();
        if (Adds.equals("-1")) {
            return false;
        }
        System.out.print("Enter Email: ");
        String Email = s.nextLine();
        if (Email.equals("-1")) {
            return false;
        }
        if (choice == 1) {   //DM
            String Position = "Delivery Man";
            boolean parseDouble = false;
            double Salary = 0;
            while (!parseDouble) {
                try {
                    System.out.print("Enter Basic Salary: ");
                    Salary = s.nextDouble();
                    parseDouble = true;
                    s.nextLine();
                } catch (Exception e) {
                    s.nextLine();
                    System.out.println("Salary Must Be Only In Integer!");
                }
            }
            if (Salary == -1) {
                return false;
            }
            String WorkingStatus = "Employed";
            String CurrentAvailable = "Not Yet Clock-In";
            String CurrentLocation = "None";
            double rating = 0.0;
            int totalRateReceived = 0;
            DeliveryMan DM = new DeliveryMan(0, CurrentAvailable, CurrentLocation, rating,totalRateReceived, ID, Pw, Name, IC, PhNo, Gender, Adds, Email, Position, WorkingStatus, Salary, Salary, joinDate);
            DMList.add(DM);
            return true;
        } else if (choice == 2) { //Admin
            String Position = "Admin";
            boolean parseDouble = false;
            double annualSale = 0;
            while (!parseDouble) {
                try {
                    System.out.print("Enter Total Annual Sales: ");
                    annualSale = s.nextDouble();
                    parseDouble = true;
                    s.nextLine();
                } catch (Exception e) {
                    s.nextLine();
                    System.out.println("Annual Sales Must Be Only In Integer!");
                }
            }
            if (annualSale == -1) {
                return false;
            }
            String WorkingStatus = "Employed";
            Admin admin = new Admin(annualSale, ID, Pw, Name, IC, PhNo, Gender, Adds, Email, Position, WorkingStatus, 0, 0, joinDate);
            admin.calculateSalary();
            ADList.add(admin);
            return true;
        } else {//HR
            String Position = "HR";
            boolean parseInt = false;
            int TotalStaffManaged = 0;
            while (!parseInt) {
                try {
                    System.out.print("Enter Total Staff Managed: ");
                    TotalStaffManaged = s.nextInt();
                    parseInt = true;
                    s.nextLine();
                } catch (Exception e) {
                    s.nextLine();
                    System.out.println("Total Staff Managed Must Be Only In Integer!");
                }
            }
            if (TotalStaffManaged == -1) {
                return false;
            }
            boolean parseDouble = false;
            double Salary = 0;
            while (!parseDouble) {
                try {
                    System.out.print("Enter Basic Salary: ");
                    Salary = s.nextDouble();
                    parseDouble = true;
                    s.nextLine();
                } catch (Exception e) {
                    s.nextLine();
                    System.out.println("Salary Must Be Only In Integer!");
                }
            }
            if (Salary == -1) {
                return false;
            }
            String WorkingStatus = "Employed";
            HR hr = new HR(TotalStaffManaged, ID, Pw, Name, IC, PhNo, Gender, Adds, Email, Position, WorkingStatus, Salary, 0, joinDate);
            hr.calculateSalary();
            HRList.add(hr);
            return true;
        }
    }

    public boolean DeliveryManUpdateStatus(String StaffID) {
        boolean find = false;
        for (int i = 0; i < DMList.getTotalEntries(); i++) {
            if (DMList.get(i).getStaffID().equals(StaffID)) {
                find = true;
                DeliveryMan DM = DMList.get(i);
                if (DM.getWorkingStatus().equals("Employed")) {
                    String choice = "0";
                    System.out.println("\n\nName: " + DM.getStaffName() + "\nIC: " + DM.getStaffIC());
                    System.out.print("Choose A Reason\n 1. Retired\n 2. Resigned\n0.Back");
                    while (!choice.equals("1") && !choice.equals("2") && !choice.equals("0")) {
                        System.out.print("Your Choice: ");
                        choice = s.nextLine();
                        switch (choice) {
                            case "1": {
                                updateDeliveryManStatus(DM, choice);
                                break;
                            }
                            case "2": {
                                updateDeliveryManStatus(DM, choice);
                                break;
                            }
                            case "0": {
                                break;
                            }
                            default: {
                                System.out.println("Error, Please Key In Again.\n");
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("Error. Delivery Man Already Not Working With Us!\nPress Enter to Continue...");
                    s.nextLine();
                }
            }
        }
        return find;
    }

    public void updateDeliveryManStatus(DeliveryMan DM, String i) {
        if (i.equals("1")) {
            DM.setWorkingStatus("Retired");
        } else {
            DM.setWorkingStatus("Resigned");
        }
        for (int j = 0; j < DMList.getTotalEntries(); j++) {
            if (DMList.get(j).getStaffID().equals(DM.getStaffID())) {
                DMList.replace(j, DM);
            }
        }
        System.out.println("Update Status Successfully! New Status: " + DM.getWorkingStatus());
        System.out.println("Press Enter to Continue...");
        s.nextLine();
        System.out.println("\n\n\n");

    }
    //Miw End

    public static void main(String[] args) {
        MainProgram MP = new MainProgram();
        Calendar HRjoinDate = Calendar.getInstance();
        Calendar ADjoinDate = Calendar.getInstance();
        Calendar DMjoinDate = Calendar.getInstance();
        DMjoinDate.add(Calendar.MONTH, 5);
        HRjoinDate.add(Calendar.MONTH, 1);
        MP.DMList.add(new DeliveryMan(1, "Not Available", "None",4.0 ,5 ,"DM000001", "123456", "Ong Yi Jun", "971009-02-5213", "012-3456789", 'M', "2345 Lorong 3 Jalan ABC, 51020 KL", "OngYiJun@gmail.com", "Delivery Man", "Employed", 3500, 3500,DMjoinDate));
        MP.HRList.add(new HR(1, "HR000001", "123456", "Ong Ong Jun", "970707-07-0707", "010-2255533", 'M', "Jalan Prima Setapak, KL", "OngOngJun@hotmail.com", "HR", "Employed", 3500, 3750,HRjoinDate));
        MP.ADList.add(new Admin(0, "AD000001", "123456", "ABC", "123456678", "012-345678", 'M', "22A, Deaman Ap, KL", "E@e.com", "Admin", "Employed", 0, 0,ADjoinDate));
        MP.menu();
        // TODO code application logic here
        /*Calendar date3 = Calendar.getInstance();
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
        System.out.println(DM.get(3).getJoinDate().getTime() + "\n");

        DM.SortMostExperienceDeliveryMan();

        DM.add(newEntry4);
        System.out.println("\n" + DM.get(1).getJoinDate().getTime());
        System.out.println(DM.get(2).getJoinDate().getTime());
        System.out.println(DM.get(3).getJoinDate().getTime());
        System.out.println(DM.get(4).getJoinDate().getTime() + "\n");

        DM.SortMostExperienceDeliveryMan();

        System.out.println("\n" + DM.get(1).getJoinDate().getTime());
        System.out.println(DM.get(2).getJoinDate().getTime());
        System.out.println(DM.get(3).getJoinDate().getTime());
        System.out.println(DM.get(4).getJoinDate().getTime());*/

    }

}
