/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import ADT.*;
import domain.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    EmployeeListInterface<WorkStatus> wsList = new EmployeeList<>();
    private Employee loginStaff;
    private Restaurant r;
    Orders currentOrder = new Orders();
    ModuleCInterface<OrderDetail> currentDetail = new ModuleCList<>();
    double Subtotal = 0.00;
    Scanner s = new Scanner(System.in);
    ModuleCInterface<Food> CurrentFood = new ModuleCList<>();
    AListInterface<Restaurant> restaurant = new AList<>();
    AListInterface<Food> food = new AList<>();
    AListInterface<Customer> customer = new AList<>();
    /**/
    ModuleCInterface<Payment> payment = new ModuleCList<>();
    ModuleCInterface<Orders> order = new ModuleCList<>();
    ModuleCInterface<OrderDetail> orderdetail = new ModuleCList<>();

    ModuleDInterface<DeliveryOrder> CLDOrderList = new ModuleDList<>();
    ModuleDInterface<WorkStatus> CLWSList = new ModuleDList<>();

    Scanner sc = new Scanner(System.in);

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
        System.out.println("5. Customer Registration");
        System.out.println("0. Exit");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("0")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    //create class at module A and import the module A at this class and den call the method from here
                    RestaurantRegistration();
                    menu();
                    break;
                }
                case "2": {
                    staffMenu();    //calling staff menu interface
                    menu();
                    break;
                }
                case "3": {
                    CustomerLogin();
                    menu();
                    break;
                }
                case "4": {
                    boolean a = RestaurantLogin(r);
                    if (a = true) {
                        menu();
                    }
                    break;
                }
                case "5": {
                    CustomerRegistration();
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
        System.out.println("*********************************");
        System.out.println("*Welcome to Staff Menu Interface*");
        System.out.println("*********************************");
        while (login == false) {
            System.out.print("Please Enter Staff ID (-1 to back): ");
            String username = s.nextLine();
            if (!username.equals("-1")) {
                boolean success = StaffLogin(username.toUpperCase());
                if (success) {
                    if (loginStaff instanceof DeliveryMan) {
                        System.out.println("\n\n\n");
                        login = true;
                        DMMenu();
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
                    System.out.println("Error. Username Not Found!Please Try Again.\n");
                }
            }
            if (username.equals("-1")) {
                login = true;
                System.out.println("\n\n\n");
            }
        }
    }

    public void DMMenu() {
        DeliveryMan DM = (DeliveryMan) loginStaff;
        DeliveryManMainMenu(DM);
    }

    public void AdminMenu() {
        String selection = "-1";
        Date date = new Date();
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Welcome Back, " + loginStaff.getStaffName() + "\nCurrent Date:" + dateFormat.format(date) + "\n");  //display login staff information
        System.out.println("Please Select The Option Below");
        System.out.println("1. Retrieve Delivery Man Pending Delivery");
        System.out.println("2. Generate Total Deliveries Report");
        System.out.println("3. Generate Total Distance Report");
        System.out.println("4. Generate Top 20 Most Experience Delivery Man Report");
        System.out.println("5. Generate Top 20 Most Rating Delivery Man Report");
        System.out.println("6. Generate Daily Sales Detailed Report(Total Quantity)");
        System.out.println("7. Generate Daily Sales Detailed Report(Order Time)");
        System.out.println("8. Generate DeliveryMan Total Deliver Report");
        System.out.println("9. Generate Restaurant Detailed Report");
        System.out.println("0. Log Out");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("6") && !selection.equals("7") && !selection.equals("8") && !selection.equals("9") && !selection.equals("0")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    RetrievePendingDelivery();
                    AdminMenu();
                    break;
                }
                case "2": {
                    generateTotalDeliveriesReport();
                    AdminMenu();
                    break;
                }
                case "3": {
                    generateTotalDistanceReport();
                    AdminMenu();
                    break;
                }
                case "4": {
                    generateMostExperienceDeliverymanReport();
                    AdminMenu();
                    break;
                }
                case "5": {
                    generateMostRatingDeliverymanReport();
                    AdminMenu();
                    break;
                }
                case "6": {
                    GenerateOrderReportByQuantity();
                    AdminMenu();
                    break;
                }
                case "7": {
                    GenerateOrderReportByTime();
                    AdminMenu();
                    break;
                }
                case "8": {
                    GenerateDeliveryManTotalDeliverReport();
                    AdminMenu();
                    break;
                }
                case "9": {
                    genDetailReportByArea();
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
        System.out.println("6. Remove Staff");
        System.out.println("0. Log Out");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("6") && !selection.equals("0")) {
            System.out.print("Option: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    boolean success = AddNewEmployee(1);
                    if (success) {
                        System.out.println("New Employee Added Successfully.\nPress Enter to Continue...");
                        s.nextLine();
                    }
                    System.out.println("\n\n\n");
                    HRMenu();
                    break;
                }
                case "2": {
                    boolean success = AddNewEmployee(2);
                    if (success) {
                        System.out.println("New Employee Added Successfully.\nPress Enter to Continue...");
                        s.nextLine();
                    }
                    System.out.println("\n\n\n");
                    HRMenu();
                    break;
                }
                case "3": {
                    boolean success = AddNewEmployee(3);
                    if (success) {
                        System.out.println("New Employee Added Successfully.\nPress Enter to Continue...");
                        s.nextLine();
                    }
                    System.out.println("\n\n\n");
                    HRMenu();
                    break;
                }
                case "4": {
                    boolean find = false;
                    String ID = "0";
                    while (!find && !ID.equals("-1")) {
                        System.out.print("Please Enter A Delivery Man ID (-1 to back): ");
                        ID = s.nextLine();
                        find = DeliveryManUpdateStatus(ID.toUpperCase());
                        if (!find) {
                            System.out.println("Delivery Man ID Not Found.Please Try Again.\n");
                        }
                    }
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
                case "6": {
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
                                        + "Staff Basic Salary: %.2f\n",
                                        e.getStaffID(), e.getStaffName(), e.getStaffPhNo(), e.getStaffAdds(), e.getStaffPosition(), e.getWorkingStatus(), e.getBasicSalary());
                                boolean success = false;
                                while (!success) {
                                    System.out.print("Are You Sure U Want To Remove This? (Y/N) :");
                                    String confirm = s.nextLine();
                                    try {
                                        if (Character.toUpperCase(confirm.charAt(0)) == 'Y') {
                                            boolean remove = true;
                                            for (int i = 1; i <= wsList.getTotalEntries(); i++) {
                                                if (e.getStaffID().equals(wsList.get(i).getDM().getStaffID())) {
                                                    remove = false;
                                                }
                                            }
                                            if (remove) {
                                                boolean removeSuccessfully = false;
                                                if (e instanceof DeliveryMan) {
                                                    removeSuccessfully = DMList.removeStaff(e.getStaffID());
                                                } else if (e instanceof HR) {
                                                    if (!loginStaff.getStaffID().equals(e.getStaffID())) {
                                                        removeSuccessfully = HRList.removeStaff(e.getStaffID());
                                                    }
                                                } else {
                                                    removeSuccessfully = ADList.removeStaff(e.getStaffID());
                                                }

                                                if (removeSuccessfully) {
                                                    System.out.println("Removed Successfully.\nPress Enter to Continue...");
                                                    s.nextLine();
                                                    success = true;
                                                } else {
                                                    System.out.println("Removed Failed. Please Seek for Help from IT department.\nPress Enter to Continue...");
                                                    s.nextLine();
                                                    success = true;
                                                }
                                            } else {
                                                System.out.println("Remove Failed. The Employee has dependency with the work status.\nPress Enter to Continue....");
                                                success = true;
                                                s.nextLine();
                                            }
                                        } else if (Character.toUpperCase(confirm.charAt(0)) == 'N') {
                                            System.out.println("Operation Stopped by user.\nPress Enter to Continue....");
                                            success = true;
                                            s.nextLine();
                                        } else {
                                            System.out.println("Please Enter Only (Y) or (N).\n");
                                        }
                                    } catch (Exception ex) {
                                        System.out.println("Please Do Not Leave It Blank!");
                                    }
                                }
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
                    System.out.println("\nThank You For Using Our System.\nPress Enter to Continue...");
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
                if (DMList.get(i).getWorkingStatus().equals("Employed")) {
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
                } else {
                    System.out.println("The ID Had Already Been Deactivated.\nPress Enter to Continue...");
                    s.nextLine();
                    return true;
                }
            }
        }
        for (int i = 1; i <= HRList.getTotalEntries(); i++) {  //verify is it HR
            if (HRList.get(i).getStaffID().equals(username)) {
                if (DMList.get(i).getWorkingStatus().equals("Employed")) {
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
                } else {
                    System.out.println("The ID Had Already Been Deactivated.\nPress Enter to Continue...");
                    s.nextLine();
                    return true;
                }
            }
        }
        for (int i = 1; i <= ADList.getTotalEntries(); i++) {  //verify is it Admin
            if (ADList.get(i).getStaffID().equals(username)) {
                if (DMList.get(i).getWorkingStatus().equals("Employed")) {
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
                } else {
                    System.out.println("The ID Had Already Been Deactivated.\nPress Enter to Continue...");
                    s.nextLine();
                    return true;
                }
            }
        }
        return false;   //username not found
    }

    public Employee DisplayStaffDetails(String StaffID) {
        Employee e = DMList.getEmployeeDetails(StaffID);
        if (e == null) {
            e = HRList.getEmployeeDetails(StaffID);
        }
        if (e == null) {
            e = ADList.getEmployeeDetails(StaffID);
        }
        return e;
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
        boolean PhNoFormat = false;
        String PhNo = "";
        while (!PhNoFormat) {
            PhNoFormat = true;
            System.out.print("Enter Phone No: ");
            PhNo = s.nextLine();
            if (PhNo.equals("-1")) {
                return false;
            }
            if (PhNo.length() < 11) {
                PhNoFormat = false;
            }
            for (int i = 0; i < PhNo.length(); i++) {
                if (!Character.isDigit(PhNo.charAt(i))) {
                    if (i != 3) {
                        PhNoFormat = false;
                    } else {
                        if (PhNo.charAt(i) != '-' && PhNo.charAt(i) != ' ') {
                            PhNoFormat = false;
                        }
                    }
                }
            }
            if (!PhNoFormat) {
                System.out.println("Format of Phone Contact Number should only contains INTEGERs and One(1) Dash!\n");
            }
        }
        char Gender = ' ';
        while (Gender != 'M' && Gender != 'F') {
            System.out.print("Enter Gender(M/F): ");
            String G = s.nextLine();
            if (G.equals("-1")) {
                return false;
            } else {
                try {
                    Gender = Character.toUpperCase(G.charAt(0));
                    if (Gender != 'M' && Gender != 'F') {
                        System.out.println("Please Enter only M or F.");
                    }
                } catch (Exception ex) {
                    System.out.println("Please Do Not Leave It Blank!");
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
            DeliveryMan DM = new DeliveryMan(0, CurrentAvailable, CurrentLocation, rating, totalRateReceived, ID, Pw, Name, IC, PhNo, Gender, Adds, Email, Position, WorkingStatus, Salary, Salary, joinDate);
            boolean success = DMList.addWithValidation(DM);
            if (success) {
                return true;
            } else {
                System.out.println("Error! The IC have been used!\nPress Enter to Continue...");
                s.nextLine();
                return false;
            }
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
            boolean success = ADList.addWithValidation(admin);
            if (success) {
                return true;
            } else {
                System.out.println("Error! The IC have been used!\nPress Enter to Continue...");
                s.nextLine();
                return false;
            }
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
            boolean success = HRList.addWithValidation(hr);
            if (success) {
                return true;
            } else {
                System.out.println("Error! The IC have been used!\nPress Enter to Continue...");
                s.nextLine();
                return false;
            }
        }
    }

    public boolean DeliveryManUpdateStatus(String StaffID) {
        boolean find = false;
        for (int i = 1; i <= DMList.getTotalEntries(); i++) {
            if (DMList.get(i).getStaffID().equals(StaffID)) {
                find = true;
                DeliveryMan DM = DMList.get(i);
                if (DM.getWorkingStatus().equals("Employed")) {
                    String choice = "-1";
                    System.out.println("\n\nName: " + DM.getStaffName() + "\nIC: " + DM.getStaffIC());
                    System.out.print("Choose A Reason\n 1. Retired\n 2. Resigned\n 0. Back\n");
                    while (!choice.equals("1") && !choice.equals("2") && !choice.equals("0")) {
                        System.out.print("Your Choice: ");
                        choice = s.nextLine();
                        switch (choice) {
                            case "1": {
                                DM.setWorkingStatus("Retired");
                                boolean success = DMList.updateDeliveryManDetail(DM);
                                if (success) {
                                    System.out.println("\nUpdate Successfully.\nPress Enter to Continue...");
                                    s.nextLine();
                                }
                                break;
                            }
                            case "2": {
                                DM.setWorkingStatus("Resigned");
                                boolean success = DMList.updateDeliveryManDetail(DM);
                                if (success) {
                                    System.out.println("Update Successfully.\nPress Enter to Continue...");
                                    s.nextLine();
                                }
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

    public void DeliveryManUpdatePersonalDetails() {
        DeliveryMan DM = (DeliveryMan) loginStaff;
        System.out.println("\n\nYour ID: " + DM.getStaffID() + "\nYour Name: " + DM.getStaffName() + "\nYour Phone No: " + DM.getStaffPhNo() + "\nYour Address: " + DM.getStaffAdds() + "\nYour Email: " + DM.getStaffEmail());
        String choice = "-1";
        System.out.println("What You want do update:\n1. Phone No\n2. Address\n3. Email\n0. Back");
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("0")) {
            System.out.print("Choice: ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    boolean PhNoFormat = false;
                    String newPhNo = null;
                    while (!PhNoFormat) {
                        PhNoFormat = true;
                        System.out.println("\n\nYour Current Phone No: " + DM.getStaffPhNo());
                        System.out.print("New Phone No: ");
                        newPhNo = s.nextLine();
                        if (newPhNo.length() < 11) {
                            PhNoFormat = false;
                        }
                        for (int i = 0; i < newPhNo.length(); i++) {
                            if (!Character.isDigit(newPhNo.charAt(i))) {
                                if (i != 3) {
                                    PhNoFormat = false;
                                } else {
                                    if (newPhNo.charAt(i) != '-' && newPhNo.charAt(i) != ' ') {
                                        PhNoFormat = false;
                                    }
                                }
                            }
                        }
                        if (!PhNoFormat) {
                            System.out.println("Format of Phone Contact Number should only contains INTEGERs and One(1) Dash!\n");
                        }
                    }
                    DM.setStaffPhNo(newPhNo);
                    DMList.updateDeliveryManDetail(DM);
                    System.out.println("Update Phone No Successfully! Press Enter to Continue...");
                    s.nextLine();
                    break;
                }
                case "2": {
                    System.out.println("\n\nYour Current Address: " + DM.getStaffAdds());
                    System.out.print("New Address: ");
                    String newAdds = s.nextLine();
                    DM.setStaffAdds(newAdds);
                    DMList.updateDeliveryManDetail(DM);
                    System.out.println("Update Address Successfully! Press Enter to Continue...");
                    s.nextLine();
                    break;
                }
                case "3": {
                    System.out.println("\n\nYour Current Email: " + DM.getStaffEmail());
                    System.out.print("New Email: ");
                    String newEmail = s.nextLine();
                    DM.setStaffEmail(newEmail);
                    DMList.updateDeliveryManDetail(DM);
                    System.out.println("Update Email Successfully! Press Enter to Continue...");
                    s.nextLine();
                }
                case "0": {
                    System.out.println("\n\n");
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    break;
                }
            }
        }
    }

    public void CustomerFeedBackRating(DeliveryOrder DO) {
        boolean success = false;
        while (!success) {
            try {
                System.out.print("Thank You For using our System.\n ( 0.0 - 10.0 )How much would you like to rate our Delivery Man, " + DO.getWS().getDM().getStaffName() + "? (-1 to Cancel) :");
                double rate = s.nextDouble();
                s.nextLine();
                if (rate < -1 || rate > 10) {
                    System.out.println("Please Enter the Integer in range of 0.0 to 10.0 only.\nPress Enter to Continue...");
                    s.nextLine();
                    System.out.println("\n\n");
                } else if (rate == -1) {
                    System.out.println("Feedback Cancelled.\nPress Enter to Continue...");
                    s.nextLine();
                    System.out.println("\n\n");
                    for (int i = 1; i < CLDOrderList.getTotalEntries(); i++) {
                        if (CLDOrderList.get(i).getOrder().getOrdersID().equals(DO.getOrder().getOrdersID())) {
                            CLDOrderList.get(i).setFeedback("Completed");
                        }
                    }
                    success = true;
                } else {
                    boolean update = DMList.updateDeliveryManRating(DO.getWS().getDM().getStaffID(), rate);
                    if (update) {
                        System.out.println("Thank You for your feedback, has a nice day.\nPress Enter to Continue...");
                        s.nextLine();
                        System.out.println("\n\n");
                        for (int i = 1; i < CLDOrderList.getTotalEntries(); i++) {
                            if (CLDOrderList.get(i).getOrder().getOrdersID().equals(DO.getOrder().getOrdersID())) {
                                CLDOrderList.get(i).setFeedback("Completed");
                            }
                        }
                        success = true;
                    } else {
                        System.out.println("System Error.\nPress Enter to Continue...");
                        s.nextLine();
                        System.out.println("\n\n");
                        success = true;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Please Enter only INTEGERs!\nPress Enter to Continue...");
                s.nextLine();
            }
        }
    }

    public void RetrievePendingDelivery() {
        DMList.SortPendingDelivery();
        int count = 0;
        Calendar today = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String todayDate = SDF.format(today.getTime());
        System.out.println("\n\t\t\t\tPending Delivery of Delivery Man\n\t\t\t\t\tDate: " + todayDate);
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        System.out.println("\nStaff ID\tStaff Name\tCurrent Available\tCurrent Location\tTotal Pending Order");
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        for (int i = 1; i <= DMList.getTotalEntries(); i++) {
            if (DMList.get(i).getTotalPendingDelivery() > 0) {
                System.out.print("\n" + DMList.get(i).getStaffID() + "\t"
                        + DMList.get(i).getStaffName() + "\t" + DMList.get(i).getCurrentAvailable() + "\t\t"
                        + DMList.get(i).getCurrentLocation() + "\t\t" + DMList.get(i).getTotalPendingDelivery());
                count++;
            }
        }
        if (count == 0) {
            System.out.print("\n\t\tNone Record(s) Found...");
        }
        System.out.print("\n");
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        System.out.println("\n\n\nPress Enter To Continue...");
        s.nextLine();
    }

    public void generateTotalDeliveriesReport() {
        System.out.println("Please Select The Option Below\n1. Generate Today Total Deliveries Report\n2. Generate Specific Date Total Deliveries Report\n0. Back");
        String choice = "-1";
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("0")) {
            System.out.print("Your Choice: ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    int count = 0;
                    java.util.Calendar today = java.util.Calendar.getInstance();
                    java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    java.text.SimpleDateFormat SDF2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String todayDate = SDF.format(today.getTime());
                    System.out.println("\n\t\t\tDaily Total Deliveries of Delivery Man Report\n\t\t\t\t\tDate: " + todayDate);
                    for (int i = 0; i < 100; i++) {
                        System.out.print("*");
                    }
                    System.out.println("\nStaff ID\tStaff Name\tCheck In Date\t\tCheck Out Date\t\tTotal Deliveries");
                    for (int i = 0; i < 100; i++) {
                        System.out.print("*");
                    }

                    for (int i = 1; i <= wsList.getTotalEntries(); i++) {
                        String compareDate = SDF.format(wsList.get(i).getCheckIn().getTime());
                        if (wsList.get(i).getTotalDeliveredOrder() > 0 && todayDate.equals(compareDate)) {
                            System.out.println("\n" + wsList.get(i).getDM().getStaffID() + "\t" + wsList.get(i).getDM().getStaffName() + "\t" + SDF2.format(wsList.get(i).getCheckIn().getTime()) + "\t"
                                    + SDF2.format(wsList.get(i).getCheckOut().getTime()) + "\t" + wsList.get(i).getTotalDeliveredOrder());
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("\n\t\t\t\tNo Record(s) Found...");
                    }
                    for (int i = 0; i < 100; i++) {
                        System.out.print("*");
                    }
                    System.out.println("\n\n\nPress Enter To Continue...");
                    s.nextLine();
                    break;
                }
                case "2": {
                    int count = 0;
                    boolean parseDate = false;
                    while (!parseDate) {
                        System.out.print("Enter A Date (DD/MM/YYYY): ");
                        String date = s.nextLine();
                        try {
                            java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
                            java.util.Date newDate = SDF.parse(date);
                            String compareDate = SDF.format(newDate);
                            parseDate = true;
                            System.out.println("\n\t\t\tDaily Total Deliveries of Delivery Man Report\n\t\t\t\t\tDate: " + compareDate);
                            for (int i = 0; i < 100; i++) {
                                System.out.print("*");
                            }
                            System.out.println("\nStaff ID\tStaff Name\tCheck In Date\t\tCheck Out Date\t\tTotal Deliveries");
                            for (int i = 0; i < 100; i++) {
                                System.out.print("*");
                            }
                            java.text.SimpleDateFormat SDF2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            for (int i = 1; i <= wsList.getTotalEntries(); i++) {
                                if (wsList.get(i).getTotalDeliveredOrder() > 0 && compareDate.equals(SDF.format(wsList.get(i).getCheckIn().getTime()))) {
                                    System.out.println("\n" + wsList.get(i).getDM().getStaffID() + "\t" + wsList.get(i).getDM().getStaffName() + "\t" + SDF2.format(wsList.get(i).getCheckIn().getTime()) + "\t"
                                            + SDF2.format(wsList.get(i).getCheckOut().getTime()) + "\t" + wsList.get(i).getTotalDeliveredOrder());
                                    count++;
                                }
                            }
                            if (count == 0) {
                                System.out.println("\n\t\t\t\tNo Record(s) Found...");
                            }
                            for (int i = 0; i < 100; i++) {
                                System.out.print("*");
                            }
                            System.out.println("\n\n\nPress Enter To Continue...");
                            s.nextLine();
                        } catch (Exception e) {
                            System.out.println("Wrong Date Format! Try Again!");
                        }
                    }
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    try {
                        Integer.parseInt(choice);
                        System.out.println("Please Enter Again...");
                    } catch (Exception e) {
                        System.out.println("Please Enter only Integer...");
                    }
                    break;
                }
            }
        }
    }

    public void generateTotalDistanceReport() {
        System.out.println("Please Select The Option Below\n1. Generate Today Total Distance Report\n2. Generate Specific Date Total Distance Report\n0. Back");
        String choice = "-1";
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("0")) {
            System.out.print("Your Choice: ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    int count = 0;
                    java.util.Calendar today = java.util.Calendar.getInstance();
                    java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    java.text.SimpleDateFormat SDF2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String todayDate = SDF.format(today.getTime());
                    System.out.println("\n\t\t\tDaily Total Distances of Delivery Man Report\n\t\t\t\t\tDate: " + todayDate);
                    for (int i = 0; i < 100; i++) {
                        System.out.print("*");
                    }
                    System.out.println("\nStaff ID\tStaff Name\tCheck In Date\t\tCheck Out Date\t\tTotal Distance(in KM)");
                    for (int i = 0; i < 100; i++) {
                        System.out.print("*");
                    }

                    for (int i = 1; i <= wsList.getTotalEntries(); i++) {
                        String compareDate = SDF.format(wsList.get(i).getCheckIn().getTime());
                        if (wsList.get(i).getTotalDistance() > 0 && todayDate.equals(compareDate)) {
                            System.out.println("\n" + wsList.get(i).getDM().getStaffID() + "\t" + wsList.get(i).getDM().getStaffName() + "\t" + SDF2.format(wsList.get(i).getCheckIn().getTime()) + "\t"
                                    + SDF2.format(wsList.get(i).getCheckOut().getTime()) + "\t" + wsList.get(i).getTotalDistance());
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("\n\t\t\t\tNo Record(s) Found...");
                    }
                    for (int i = 0; i < 100; i++) {
                        System.out.print("*");
                    }
                    System.out.println("\n\n\nPress Enter To Continue...");
                    s.nextLine();
                    break;
                }
                case "2": {
                    int count = 0;
                    boolean parseDate = false;
                    while (!parseDate) {
                        System.out.print("Enter A Date (DD/MM/YYYY): ");
                        String date = s.nextLine();
                        try {
                            java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
                            java.util.Date newDate = SDF.parse(date);
                            String compareDate = SDF.format(newDate);
                            parseDate = true;
                            System.out.println("\n\t\t\tDaily Total Distances of Delivery Man Report\n\t\t\t\t\tDate: " + compareDate);
                            for (int i = 0; i < 100; i++) {
                                System.out.print("*");
                            }
                            System.out.println("\nStaff ID\tStaff Name\tCheck In Date\t\tCheck Out Date\t\tTotal Distance(in KM)");
                            for (int i = 0; i < 100; i++) {
                                System.out.print("*");
                            }
                            java.text.SimpleDateFormat SDF2 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            for (int i = 1; i <= wsList.getTotalEntries(); i++) {
                                if (wsList.get(i).getTotalDistance() > 0 && compareDate.equals(SDF.format(wsList.get(i).getCheckIn().getTime()))) {
                                    System.out.println("\n" + wsList.get(i).getDM().getStaffID() + "\t" + wsList.get(i).getDM().getStaffName() + "\t" + SDF2.format(wsList.get(i).getCheckIn().getTime()) + "\t"
                                            + SDF2.format(wsList.get(i).getCheckOut().getTime()) + "\t" + wsList.get(i).getTotalDistance());
                                    count++;
                                }
                            }
                            if (count == 0) {
                                System.out.println("\n\t\t\t\tNo Record(s) Found...");
                            }
                            for (int i = 0; i < 100; i++) {
                                System.out.print("*");
                            }
                            System.out.println("\n\n\nPress Enter To Continue...");
                            s.nextLine();
                        } catch (Exception e) {
                            System.out.println("Wrong Date Format! Try Again!");
                        }
                    }
                    break;
                }
                case "3": {
                    break;
                }
                default: {
                    try {
                        Integer.parseInt(choice);
                        System.out.println("Please Enter Again...");
                    } catch (Exception e) {
                        System.out.println("Please Enter only Integer...");
                    }
                    break;
                }
            }
        }
    }

    public void generateMostExperienceDeliverymanReport() {
        DMList.SortMostExperienceDeliveryMan();
        int count = 0;
        java.util.Calendar today = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String todayDate = SDF.format(today.getTime());
        System.out.println("\n\t\t\tTop 20 Most Experience Delivery Man Report\n\t\t\t\t\tDate: " + todayDate);
        for (int i = 0; i < 110; i++) {
            System.out.print("*");
        }
        System.out.println("\nNo\tStaff ID\tStaff Name\tStaff Phone No\tStaff Email\t\tJoin Date\tSalary(RM)");
        for (int i = 0; i < 110; i++) {
            System.out.print("*");
        }
        for (int i = 1; i <= DMList.getTotalEntries(); i++) {
            if (count < 20 && DMList.get(i).getWorkingStatus().equals("Employed")) {
                count++;
                System.out.println("\n" + count + "\t" + DMList.get(i).getStaffID() + "\t" + DMList.get(i).getStaffName() + "\t" + DMList.get(i).getStaffPhNo() + "\t"
                        + DMList.get(i).getStaffEmail() + "\t" + SDF.format(DMList.get(i).getJoinDate().getTime()) + "\t" + String.format("%.2f", DMList.get(i).getBasicSalary()));

            }
        }
        if (count == 0) {
            System.out.println("\n\t\t\t\tNo Record(s) Found...");
        }
        for (int i = 0; i < 110; i++) {
            System.out.print("*");
        }
        System.out.println("\n\n\nPress Enter To Continue...");
        s.nextLine();
    }

    public void generateMostRatingDeliverymanReport() {
        DMList.SortDeliveryManRating();
        int count = 0;
        java.util.Calendar today = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("dd/MM/yyyy");
        String todayDate = SDF.format(today.getTime());
        System.out.println("\n\t\t\tTop 20 Most Rating Delivery Man Report\n\t\t\t\t\tDate: " + todayDate);
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        System.out.println("\nNo\tStaff ID\tStaff Name\tJoin Date\tRating\tTotal Rated Received\tSalary(RM)");
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        for (int i = 1; i <= DMList.getTotalEntries(); i++) {
            if (count < 20 && DMList.get(i).getWorkingStatus().equals("Employed")) {
                count++;
                System.out.println("\n" + count + "\t" + DMList.get(i).getStaffID() + "\t" + DMList.get(i).getStaffName() + "\t" + SDF.format(DMList.get(i).getJoinDate().getTime()) + "\t"
                        + String.format("%.2f", DMList.get(i).getRating()) + "\t\t\t" + DMList.get(i).getTotalRateReceived() + "\t" + String.format("%.2f", DMList.get(i).getBasicSalary()));
            }
        }
        if (count == 0) {
            System.out.println("\n\t\t\t\tNo Record(s) Found...");
        }
        for (int i = 0; i < 100; i++) {
            System.out.print("*");
        }
        System.out.println("\n\n\nPress Enter To Continue...");
        s.nextLine();
    }
    //Miw End

    public static void main(String[] args) {
        MainProgram MP = new MainProgram();
        Calendar HRjoinDate = Calendar.getInstance();
        Calendar ADjoinDate = Calendar.getInstance();
        Calendar DMjoinDate = Calendar.getInstance();
        Calendar WSCheckINOut = Calendar.getInstance();
        DMjoinDate.add(Calendar.MONTH, 5);
        HRjoinDate.add(Calendar.MONTH, 1);
        MP.DMList.add(new DeliveryMan(1, "Not Available", "Not Available", 4.3, 1, "DM000001", "123456", "Ong Yi Jun", "971009-02-5213", "012-3456781", 'M', "2341 Lorong 3 Jalan ABC, 51020 KL", "OngYiJun@gmail.com", "Delivery Man", "Employed", 3500, 3500, ADjoinDate));
        MP.DMList.add(new DeliveryMan(0, "Available", "Not Available", 9.75, 5, "DM000002", "123456", "Ong Yi Yi", "971009-02-5213", "012-3456782", 'M', "2342 Lorong 3 Jalan ABC, 51021 KL", "OngYiYi@gmail.com", "Delivery Man", "Employed", 3750, 3750, HRjoinDate));
        MP.DMList.add(new DeliveryMan(3, "Not Available", "Not Available", 5.6, 2, "DM000003", "123456", "Ong Jun Jun", "971009-02-5213", "012-3456783", 'M', "2343 Lorong 3 Jalan ABC, 51022 KL", "OngJunJun@gmail.com", "Delivery Man", "Employed", 4000, 4000, HRjoinDate));
        MP.DMList.add(new DeliveryMan(4, "Not Available", "Not Available", 7.4, 3, "DM000004", "123456", "Ong Ong Yi", "971009-02-5213", "012-3456784", 'M', "2344 Lorong 3 Jalan ABC, 51023 KL", "OngOngYi@gmail.com", "Delivery Man", "Employed", 3250, 3250, DMjoinDate));
        MP.DMList.add(new DeliveryMan(2, "Available", "Not Available", 5.5, 7, "DM000005", "123456", "Ong Ong Jun", "971009-02-5213", "012-3456785", 'M', "2345 Lorong 3 Jalan ABC, 51024 KL", "OngOngJun@gmail.com", "Delivery Man", "Employed", 3000, 3000, DMjoinDate));

        MP.HRList.add(new HR(1, "HR000001", "123456", "Ong Ong Jun", "970707-07-0707", "010-2255533", 'M', "Jalan Prima Setapak, KL", "OngOngJun@hotmail.com", "HR", "Employed", 3500, 3750, HRjoinDate));
        MP.ADList.add(new Admin(20000, "AD000001", "123456", "ABC", "123456678", "012-345678", 'M', "22A, Deaman Ap, KL", "E@e.com", "Admin", "Employed", 6000, 6000, ADjoinDate));

        MP.restaurant.add(new Restaurant("RE000001", "Nandos", "Western", 'A', "Tneh Chee Wei", "asd", "016-1234567", "Setapak", "100", "1234567890"));
        MP.restaurant.add(new Restaurant("RE000002", "KFC", "FastFood", 'N', "Abu", "ase", "016-1234321", "Wangsa Maju", "200", "2"));
        MP.restaurant.add(new Restaurant("RE000003", "abc", "MalayFood", 'A', "T", "se", "011-12345678", "Wangsa Maju", "200", "2"));
        MP.restaurant.add(new Restaurant("RE000004", "Texas", "FastFood", 'N', "Ali", "ase", "016-5555555", "Genting Kelang", "200", "2"));
        MP.restaurant.add(new Restaurant("RE000005", "qwrv", "FastFood", 'N', "Mohamad Dahoud", "ase", "016-1235776", "Wangsa Maju", "200", "2"));
        MP.restaurant.add(new Restaurant("RE000006", "Sri aa", "FastFood", 'N', "Lee lee lee", "ase", "016-9864234", "Setapak", "200", "2"));

        MP.food.add(new Food("FM000001", "Chicken Bolognese", 11.50, "Main Dish", 'A', MP.restaurant.get(1), 1));
        MP.food.add(new Food("FM000002", "Fish Bolognese", 11.50, "Main Dish", 'A', MP.restaurant.get(1), 3));
        MP.food.add(new Food("FM000003", "Beef Bolognese", 13.50, "Side Dish", 'A', MP.restaurant.get(1), 3));
        MP.food.add(new Food("FM000004", "Dinner Plate A", 11.50, "Beverage", 'A', MP.restaurant.get(2), 1));
        MP.food.add(new Food("FM000005", "Dinner Plate B", 12.50, "Side Dish", 'A', MP.restaurant.get(2), 2));
        MP.food.add(new Food("FM000006", "Dinner Plate C", 13.50, "Beverage", 'A', MP.restaurant.get(2), 3));
        MP.food.add(new Food("FM000007", "Chicken Rice", 9.50, "Main Dish", 'A', MP.restaurant.get(1), 3));
        MP.food.add(new Food("FM000008", "Dinner Plate D", 5.50, "Side Dish", 'A', MP.restaurant.get(2), 1));
        MP.food.add(new Food("FM000009", "Dinner Plate E", 6.50, "Beverage", 'A', MP.restaurant.get(2), 2));

        MP.customer.add(new Customer("CU000001", "Miw Jin Li", "14,Taman Cantik,53300,Setapak,Kuala Lumpur", "Setapak", "0167897898", "971003355333", "1234567890"));
        MP.customer.add(new Customer("CU000002", "Miw Jin Le", "14,Taman Cantik,53300,Wangsa Maju,Kuala Lumpur", "Wangsa Maju", "0167897899", "970104079999", "1234567890"));

        Calendar previous = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = df.parse("2017/12/24");
            previous.setTime(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        MP.wsList.add(new WorkStatus("WS000001", previous, previous, 11, 3, MP.DMList.get(1)));
        MP.wsList.add(new WorkStatus("WS000002", WSCheckINOut, WSCheckINOut, 15, 6, MP.DMList.get(2)));
        MP.wsList.add(new WorkStatus("WS000003", WSCheckINOut, WSCheckINOut, 0, 0, MP.DMList.get(5)));

        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = dateFormat.parse("2017/12/21 12:21:12");
            cal.setTime(date);
            Date date1 = dateFormat.parse("2017/12/21 11:21:12");
            cal1.setTime(date1);
            Date date2 = dateFormat.parse("2017/12/21 10:21:12");
            cal2.setTime(date2);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        MP.order.add(new Orders(MP.restaurant.get(1), MP.customer.get(1), "OR000001", 10.00, 11.00, "1", cal2, 1));
        MP.order.add(new Orders(MP.restaurant.get(2), MP.customer.get(2), "OR000002", 11.00, 12.00, "1", cal, 2));
        MP.order.add(new Orders(MP.restaurant.get(1), MP.customer.get(1), "OR000003", 12.00, 13.00, "1", cal1, 10));
        MP.order.add(new Orders(MP.restaurant.get(2), MP.customer.get(2), "OR000004", 13.00, 14.00, "1", cal, 2));
        MP.orderdetail.add(new OrderDetail(MP.order.get(1), MP.food.get(1), 1, 15.00));
        MP.orderdetail.add(new OrderDetail(MP.order.get(2), MP.food.get(1), 1, 16.00));
        MP.orderdetail.add(new OrderDetail(MP.order.get(3), MP.food.get(2), 1, 15.00));
        MP.orderdetail.add(new OrderDetail(MP.order.get(4), MP.food.get(1), 1, 16.00));
        MP.payment.add(new Payment("PA000001", MP.order.get(1), 10.00, cal2, "1", "Online"));
        MP.payment.add(new Payment("PA000002", MP.order.get(2), 11.00, cal, "1", "Online"));
        MP.payment.add(new Payment("PA000003", MP.order.get(3), 12.00, cal1, "1", "Online"));
        MP.payment.add(new Payment("PA000004", MP.order.get(4), 13.00, cal, "1", "Online"));
        MP.menu();
    }//==========================================================================Void Main

    /*Module C Beginning*/
    public void CustomerLogin() {
        String name, password;
        int check = 0;
        System.out.println("--------------");
        System.out.println("Customer Login");
        System.out.println("--------------");
        System.out.print("Name:");
        name = s.nextLine();
        name = name.toUpperCase();
        System.out.print("Password:");
        password = s.nextLine();

        for (int i = 1; i <= customer.getNumOfEntries(); i++) {
            if (name.equals(customer.get(i).getCustName().toUpperCase())) {
                if (password.equals(customer.get(i).getCustPass())) {
                    check++;
                    System.out.println("Login Successful");
                    CustomerMenu(customer.get(i));
                } else {
                    check++;
                    System.out.println("Password is Invalid");
                    CustomerLogin();
                }
            }
        }
        if (check == 0) {
            System.out.println("This customer name is not exist");
            CustomerLogin();
        }
    }

    public void CustomerMenu(Customer current) {
        String selection = "";

        System.out.println("--------------");
        System.out.println("Customer Menu");
        System.out.println("--------------");
        System.out.println("Please select the below option");
        System.out.println("1. Make Order");
        System.out.println("2. Cancel Order");
        System.out.println("3. View Order");
        System.out.println("4. Feedback");
        System.out.println("0. Logout");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("0")) {
            System.out.print("Option: ");
            selection = s.nextLine();

            switch (selection) {
                case "1": {
                    SelectRestaurant(current);
                    CustomerMenu(current);
                    break;
                }
                case "2": {
                    cancelOrder(current);
                    CustomerMenu(current);
                    break;
                }
                case "3": {
                    DisplayCustomerOrder(current);
                    CustomerMenu(current);
                    break;
                }
                case "4": {
                    boolean find = false;
                    for (int i = 1; i <= CLDOrderList.getTotalEntries(); i++) {
                        if (CLDOrderList.get(i).getOrder().getCustomer().getCustID().equals(current.getCustID()) && CLDOrderList.get(i).getFeedback().equals("Pending")) {
                            CustomerFeedBackRating(CLDOrderList.get(i));
                            find = true;
                        }
                    }
                    if (!find) {
                        System.out.println("There is no completed order yet.\nPress Enter to Continue...\n");
                        s.nextLine();
                    } else {
                        System.out.println("There is no more pending Feedback available.\nPress Enter to Continue...\n");
                        s.nextLine();
                    }
                    CustomerMenu(current);
                    break;
                }
                case "0": {
                    System.out.println("\n\n\n\n");
                    break;
                }
                default: {
                    System.out.println("Error, Please Key In Again.");
                    CustomerMenu(current);
                    break;
                }
            }
        }
    }

    public void cancelOrder(Customer current) {
        String selection = "", orderid = "";
        boolean found = false, recordfound = false;
        do {
            Calendar cal = Calendar.getInstance();
            System.out.println("Below showing the order that you can cancel");
            System.out.println("You only can cancel order within 2 minute after you ordered");
            for (int i = 1; i <= order.getTotalEntries(); i++) {
                if (cal.getTimeInMillis() - order.get(i).getOrdersDateTime().getTimeInMillis() < 2 * 60 * 1000 && order.get(i).getOrderStatus().equals("1")
                        && order.get(i).getCustomer().getCustID().equals(current.getCustID())) {
                    System.out.println("Order ID = " + order.get(i).getOrdersID());
                    System.out.println("Restaurant = " + order.get(i).getRestaurant().getRestaurantName());
                    System.out.printf("Total = %.2f\n", order.get(i).getTotal());
                    recordfound = true;
                }
            }
            if (recordfound == true) {
                System.out.print("\nPlease Key in the order id that u want to cancel(0 to back)");
                orderid = s.nextLine();
                if (orderid.equals("0")) {
                    found = true;
                    return;
                }
                for (int j = 1; j <= order.getTotalEntries() && found == false; j++) {
                    if (order.get(j).getOrdersID().toUpperCase().equals(orderid.toUpperCase()) && cal.get(Calendar.MINUTE) - order.get(j).getOrdersDateTime().get(Calendar.MINUTE) < 2 && order.get(j).getOrderStatus().equals("1")
                            && order.get(j).getCustomer().getCustID().equals(current.getCustID())) {
                        found = true;
                        order.get(j).setOrderStatus("0");
                        payment.get(j).setPaymentStatus("0");
                        System.out.printf("Cancel Order Successful, Payment Amount Have Been Returned.\n");
                    }
                }
                if (found == false) {
                    System.out.printf("Please Key In Again\\n");
                }
            } else {
                System.out.printf("No Record\n\n");
            }
        } while (found == false && recordfound == true);
    }

    public void SelectRestaurant(Customer current) {
        boolean find = false;
        int resIndex = 0;
        String selection = "";
        System.out.println("\n\n*****************");
        System.out.println("*Restaurant List*");
        System.out.println("*****************");
        System.out.println("Please select the below option");
        for (int i = 1; i <= restaurant.getNumOfEntries(); i++) {
            System.out.println(i + ". " + restaurant.get(i).getRestaurantName());
        }
        System.out.print("Please Enter the Restaurant Name (Example:KFC) 0 to Back: ");
        selection = s.nextLine();
        if (selection.equals("0")) {
            System.out.printf("\n\n\n");
        } else {
            for (int i = 1; i <= restaurant.getNumOfEntries() && find == false; i++) {
                if (selection.equals(restaurant.get(i).getRestaurantName())) {
                    find = true;
                    resIndex = i;
                    for (int j = 1; j <= food.getNumOfEntries(); j++) {
                        if (food.get(j).getRestaurant().getRestaurantName().equals(selection) && food.get(j).getFoodAvailability() == 'A') {
                            CurrentFood.add(food.get(j));
                        }
                    }
                    makeOrder(customer.get(1), resIndex);
                }
            }
            if (find == false) {
                System.out.println();
                System.out.println("Please Enter Again");
                SelectRestaurant(current);
            }
        }
    }

    public void makeOrder(Customer current, int resIndex) {
        boolean checkout = false, ordered = false;
        String selection = "", foodid = "", nextID = "";
        int quantity;
        nextID = order.GetCurrentOrderID();
        Calendar cal = Calendar.getInstance();
        currentOrder.setOrdersID(nextID);
        currentOrder.setCustomer(current);
        currentOrder.setOrderStatus("1");
        currentOrder.setOrdersDateTime(cal);
        currentOrder.setRestaurant(restaurant.get(resIndex));
        currentOrder.setSubtotal(Subtotal);
        currentOrder.setTotal(Subtotal * 1.06);
        System.out.println("\n\nBelow are the foods provided by " + restaurant.get(resIndex).getRestaurantName());
        System.out.println("---------------------------------------------------");

        showFoodMenu(restaurant.get(resIndex), food);
        System.out.println("---------------------------------------------------");
        while (!foodid.equals("C") && !foodid.equals("0") && !foodid.equals("V") && checkout == false) {
            System.out.println("Please Enter the Food ID that You Want");
            System.out.println("(Press C to confirm, 0 to back and cancel, V to view cart):");
            foodid = s.nextLine();
            foodid = foodid.toUpperCase();

            if (foodid.equals("C")) {
                if (!currentDetail.isEmpty()) {
                    checkout = Confirmation(current);
                    if (checkout == false) {
                        makeOrder(current, resIndex);
                    }
                } else {
                    System.out.println("You Did Not Order Food Yet.");
                    makeOrder(current, resIndex);
                }
            } else if (foodid.equals("0")) {
                currentOrder = new Orders();
                currentDetail.clear();
                Subtotal = 0.00;
                CurrentFood.clear();
                break;
            } else if (foodid.equals("V")) {
                boolean again = ViewCart(current);
                if (again) {
                    makeOrder(current, resIndex);
                    break;
                } else {
                    SelectRestaurant(current);
                    break;
                }
            } else {
                //check whether food id is exist or not
                boolean foodcheck = false;
                for (int i = 1; i <= CurrentFood.getTotalEntries() && foodcheck == false; i++) {
                    if (foodid.equals(CurrentFood.get(i).getFoodID())) {
                        foodcheck = true;
                        do {
                            System.out.println("Please Enter the Quantity:");
                            while (!s.hasNextInt()) {
                                System.out.println("Please Enter the Quantity in Integer:");
                                s.nextLine();
                            }
                            quantity = s.nextInt();
                            s.nextLine();
                        } while (quantity < 1);
                        double currentSubtotal = CurrentFood.get(i).getFoodPrice() * quantity;
                        currentDetail.addDetail(new OrderDetail(currentOrder, CurrentFood.get(i), quantity, currentSubtotal));
                        Subtotal += currentSubtotal;
                        currentOrder.setTotalQuantity(currentOrder.getTotalQuantity() + quantity);
                        currentOrder.setSubtotal(Subtotal);
                    }
                }
                if (foodcheck == false) {
                    System.out.println("Please Enter The Correct Food ID");
                }
                //end of checking
            }
        }
    }

    public boolean ViewCart(Customer current) {
        String selection = "";
        boolean again = true;
        if (currentDetail.isEmpty()) {
            System.out.println("\n\nYou Do Not Order Any Food Yet.");
            System.out.println("Press Enter To Back.");
            s.nextLine();
        } else {
            System.out.println("\n\nBelow Are The Foods You Have Ordered Inside Your Cart");
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-   Food ID   -        Food Name        -  Quantity  -  Subtotal(RM)  -");
            System.out.println("-----------------------------------------------------------------------");
            for (int i = 1; i <= currentDetail.getTotalEntries(); i++) {
                System.out.printf("\n-  %9s  -", currentDetail.get(i).getFood().getFoodID());
                System.out.printf(" %22s  -", currentDetail.get(i).getFood().getFoodName());
                System.out.printf(" %9d  -", currentDetail.get(i).getQuantity());
                System.out.printf(" %13.2f  -", currentDetail.get(i).getFoodTotal());
            }
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.println("\nPlease Select Your Selection");
            System.out.println("1. Edit Food");
            System.out.println("2. Delete Food");
            System.out.println("3. Cancel Order");
            System.out.println("0. Back");
            while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("0")) {
                System.out.print("Selection: ");
                selection = s.nextLine();
                switch (selection) {
                    case "1": {
                        editFood();
                        break;
                    }
                    case "2": {
                        removeCartFood();
                        break;
                    }
                    case "3": {
                        currentOrder = new Orders();
                        currentDetail.clear();
                        Subtotal = 0.00;
                        CurrentFood.clear();
                        System.out.println("\nCancel Order Successful\n\n");
                        again = false;
                        break;
                    }
                    case "0": {
                        break;
                    }
                    default: {
                        System.out.println("Please Enter Again");
                        break;
                    }
                }
            }
        }
        return again;
    }

    public void editFood() {
        boolean found = false;
        String foodid = "", selection = "";
        int newquantity;
        do {
            System.out.println("\n\nBelow Are The Foods You Have Ordered Inside Your Cart");
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-   Food ID   -        Food Name        -  Quantity  -  Subtotal(RM)  -");
            System.out.println("-----------------------------------------------------------------------");
            for (int i = 1; i <= currentDetail.getTotalEntries(); i++) {
                System.out.printf("\n-  %9s  -", currentDetail.get(i).getFood().getFoodID());
                System.out.printf(" %22s  -", currentDetail.get(i).getFood().getFoodName());
                System.out.printf(" %9d  -", currentDetail.get(i).getQuantity());
                System.out.printf(" %13.2f  -", currentDetail.get(i).getFoodTotal());
            }
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.print("Please enter the food id that you want to edit(0 to back):");
            foodid = s.nextLine();
            if (foodid.equals("0")) {
                selection = "YES";
                return;
            }
            for (int i = 1; i <= currentDetail.getTotalEntries() && found == false; i++) {
                if (currentDetail.get(i).getFood().getFoodID().toUpperCase().equals(foodid.toUpperCase())) {
                    do {
                        System.out.print("Please Enter the Quantity:");
                        while (!s.hasNextInt()) {
                            System.out.print("Please Enter the Quantity in Integer:");
                            s.next();
                        }
                        newquantity = s.nextInt();
                        s.nextLine();
                    } while (newquantity < 1);
                    found = true;
                    currentOrder.setTotalQuantity(currentOrder.getTotalQuantity() - currentDetail.get(i).getQuantity() + newquantity);
                    Subtotal = currentDetail.editCart(newquantity, i, currentOrder.getSubtotal());
                    currentOrder.setSubtotal(Subtotal);
                }
            }
            if (found == false) {
                System.out.print("Wrong Food ID");
                selection = "YES";
            } else {
                System.out.print("Do You Want To Continue(Yes to continue, others to back)?");
                selection = s.nextLine();
                selection = selection.toUpperCase();
            }

        } while (selection.equals("YES"));
    }

    public void removeCartFood() {
        boolean found = false;
        String foodid = "", selection = "";
        do {
            System.out.println("\n\nBelow Are The Foods You Have Ordered Inside Your Cart");
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-   Food ID   -        Food Name        -  Quantity  -  Subtotal(RM)  -");
            System.out.println("-----------------------------------------------------------------------");
            for (int i = 1; i <= currentDetail.getTotalEntries(); i++) {
                System.out.printf("\n-  %9s  -", currentDetail.get(i).getFood().getFoodID());
                System.out.printf(" %22s  -", currentDetail.get(i).getFood().getFoodName());
                System.out.printf(" %9d  -", currentDetail.get(i).getQuantity());
                System.out.printf(" %13.2f  -", currentDetail.get(i).getFoodTotal());
            }
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.print("Please enter the food id that you want to remove(0 to back):");
            foodid = s.nextLine();
            if (foodid.equals("0")) {
                selection = "YES";
                return;
            }
            for (int i = 1; i <= currentDetail.getTotalEntries() && found == false; i++) {
                if (currentDetail.get(i).getFood().getFoodID().toUpperCase().equals(foodid.toUpperCase())) {
                    found = true;
                    currentOrder.setTotalQuantity(currentOrder.getTotalQuantity() - currentDetail.get(i).getQuantity());
                    Subtotal = currentDetail.removeCartFood(i, currentOrder.getSubtotal());
                    currentOrder.setSubtotal(Subtotal);
                }
            }
            if (found == false) {
                System.out.print("Wrong Food ID");
                selection = "YES";
            } else {
                System.out.print("Do You Want To Continue(Yes to continue, others to back)?");
                selection = s.nextLine();
                selection = selection.toUpperCase();
            }
        } while (selection.equals("YES"));
    }

    public boolean Confirmation(Customer current) {
        String selection = "";
        double roundoff = 0.00, total = 0.00;
        System.out.println("\n\nBelow Are The Foods You Have Ordered");
        System.out.println("------------------------------------------------------");
        System.out.println("-   Food ID   -        Food Name        -  Quantity  -");
        System.out.println("------------------------------------------------------");
        for (int h = 1; h <= currentDetail.getTotalEntries(); h++) {
            System.out.printf("-  %9s  -", currentDetail.get(h).getFood().getFoodID());
            System.out.printf(" %22s  -", currentDetail.get(h).getFood().getFoodName());
            System.out.printf(" %9d  -\n", currentDetail.get(h).getQuantity());
        }
        System.out.println("------------------------------------------------------");
        System.out.printf("Subtotal: RM%.2f\n", currentOrder.getSubtotal());
        System.out.printf("GST: RM%.2f\n", (currentOrder.getSubtotal() * 0.06));
        System.out.printf("Total: RM%.2f\n", (currentOrder.getSubtotal() * 1.06));
        System.out.println("------------------------------------");
        System.out.println("\n\nAre You Sure Want To CheckOut And Make Payment?");
        System.out.println("1. Yes");
        System.out.println("2. Back To Food Selection");
        while (!selection.equals("1") && !selection.equals("2")) {
            System.out.print("Selection: ");
            selection = s.nextLine();
            switch (selection) {
                case "1": {
                    //getting the system date
                    Calendar cal = Calendar.getInstance();
                    currentOrder.setOrdersDateTime(cal);
                    currentOrder.setSubtotal(Subtotal);
                    currentOrder.setTotal(Subtotal * 1.06);
                    currentOrder.setOrderStatus("1");//change to 1
                    order.add(currentOrder);
                    payment.add(new Payment(payment.GetCurrentPaymentID(), currentOrder, currentOrder.getTotal(), cal, "1", "Online"));
                    for (int i = 1; i <= currentDetail.getTotalEntries(); i++) {
                        orderdetail.add(currentDetail.get(i));
                        orderdetail.SortOrderDetail();
                    }
                    System.out.println("\nThank You For Your Order.");
                    System.out.println("You Have Order The Following Items.");
                    System.out.println("------------------------------------------------------");
                    System.out.println("-   Food ID   -        Food Name        -  Quantity  -");
                    System.out.println("------------------------------------------------------");
                    for (int h = 1; h <= currentDetail.getTotalEntries(); h++) {
                        System.out.printf("-  %9s  -", currentDetail.get(h).getFood().getFoodID());
                        System.out.printf(" %22s  -", currentDetail.get(h).getFood().getFoodName());
                        System.out.printf(" %9d  -\n", currentDetail.get(h).getQuantity());
                    }
                    System.out.println("------------------------------------------------------");
                    System.out.printf("Subtotal: RM%.2f\n", currentOrder.getSubtotal());
                    System.out.printf("GST: RM%.2f\n", (currentOrder.getSubtotal() * 0.06));
                    System.out.printf("Total: RM%.2f\n", currentOrder.getTotal());
                    currentOrder = new Orders();
                    currentDetail.clear();
                    Subtotal = 0.00;
                    CurrentFood.clear();
                    s.nextLine();
                    return true;
                }
                case "2": {
                    return false;
                }
                default: {
                    System.out.println("Please Enter Again");
                }
            }
        }
        return true;
    }

    public void GenerateOrderReportByQuantity() {
        String reportday = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar comparecal = Calendar.getInstance();
        System.out.print("Please Enter The Day (YYYY/MM/DD) : ");
        reportday = s.nextLine();
        try {
            Date date = dateFormat.parse(reportday);
            comparecal.setTime(date);
            order.GenerateDetailReportByQuantity(comparecal);
        } catch (ParseException ex) {
            System.out.println("Wrong Format\n\n");
        }

    }

    public void GenerateOrderReportByTime() {
        String reportday = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar comparecal = Calendar.getInstance();
        System.out.print("Please Enter The Day (YYYY/MM/DD) : ");
        reportday = s.nextLine();
        try {
            Date date = dateFormat.parse(reportday);
            comparecal.setTime(date);
            order.GenerateDetailReportByTime(comparecal);
        } catch (ParseException ex) {
            System.out.println("Wrong Format\n\n");
        }

    }

    public void retrieveCustomer() {
        String contact = "";
        boolean check = false;
        System.out.println("\n\n***************************");
        System.out.println("*Retrieve Customer Details*");
        System.out.println("***************************");
        while (check == false) {
            System.out.println("Please Enter The Contact Number :");
            while (s.hasNext("[A-Za-z]+")) {
                System.out.println("Please Enter The Contact Number in Correct Format.");
                s.nextLine();
            }
            contact = s.nextLine();
            for (int i = 1; i <= customer.getNumOfEntries(); i++) {
                if (contact.equals(customer.get(i).getCustTelNo())) {
                    System.out.println("\n\nPersonal Information");
                    System.out.println("---------------------------");
                    System.out.println("Name: " + customer.get(i).getCustName());
                    System.out.println("Address: " + customer.get(i).getCustAddress());
                    System.out.println("Area: " + customer.get(i).getCustArea());
                    System.out.println("IC Number: " + customer.get(i).getCustIC());
                    System.out.println("\n");
                    check = true;
                }
            }
            if (check == false) {
                System.out.println("This Contact Number is Not Exist in Customer Database");
                s.nextLine();
            }
        };
    }

    /*End of Module C*/
    //Module A
    public void CustomerRegistration() {
        int select = 0, check = 0;
        System.out.println("----------------------");
        System.out.println("Customer Registration");
        System.out.println("----------------------");

        int totalCust = customer.getNumOfEntries();
        String CustID = String.format("CU%06d", totalCust + 1);
        System.out.println("Customer ID: " + CustID);
        System.out.print("Customer Name: ");
        String CustName = sc.nextLine();
        System.out.print("Address: ");
        String CustAddress = sc.nextLine();
        System.out.println("1. Setapak");
        System.out.println("2. Wangsa Maju");
        System.out.println("3. Taman Melawati");
        System.out.println("4. Genting Kelang");
        do {
            System.out.print("Area: ");
            if (sc.hasNextInt()) {
                check = 1;
                select = sc.nextInt();
                String CustArea = null;
                switch (select) {
                    case 1: {
                        CustArea = "Setapak";
                        break;
                    }
                    case 2: {
                        CustArea = "Wangsa Maju";
                        break;
                    }
                    case 3: {
                        CustArea = "Taman Melawati";
                        break;
                    }
                    case 4: {
                        CustArea = "Genting Kelang";
                        break;
                    }
                    default: {
                        System.out.println("Invalid Input");
                    }
                }
                System.out.print("Contact No: ");
                sc.nextLine();
                String CustTelNo = sc.nextLine();
                System.out.print("IC No: ");
                String CustIC = sc.nextLine();
                System.out.print("Password: ");
                String CustPass = sc.nextLine();

                boolean b = true;
                for (int i = 1; i <= customer.getNumOfEntries(); i++) {
                    if (CustIC.equals(customer.get(i).getCustIC())) {
                        System.out.println("Customer already exists in the system.");
                        CustomerRegistration();
                        b = false;
                    }
                }
                if (b == true) {
                    Customer cust = new Customer(CustID, CustName, CustAddress, CustArea, CustTelNo, CustIC, CustPass);
                    customer.add(cust);
                    System.out.println("Successfully Registered.");

                }
            } else {
                System.out.println("Invalid Input");
                sc.next();
                check = 0;
            }
        } while (check == 0);
    }

    public void RestaurantRegistration() {
        int select = 0, check = 0;
        System.out.println("-----------------------");
        System.out.println("Affiliate Registration");
        System.out.println("-----------------------");

        int totalRest = restaurant.getNumOfEntries();
        String restid = String.format("RE%06d", totalRest + 1);
        System.out.println("Restaurant ID: " + restid);
        System.out.println("(Please remember the ID for further use.)");
        System.out.print("Restaurant Name: ");
        String rName = sc.nextLine();
        System.out.print("Restaurant Type: ");
        String rType = sc.nextLine();
        char rMenu = 'A';
        System.out.print("Owner Name: ");
        String oName = sc.nextLine();
        System.out.print("Address: ");
        String add = sc.nextLine();
        System.out.print("Contact No: ");
        String no = sc.nextLine();
        System.out.println("1. Setapak");
        System.out.println("2. Wangsa Maju");
        System.out.println("3. Taman Melawati");
        System.out.println("4. Genting Kelang");
        do {
            System.out.print("Area: ");
            if (sc.hasNextInt()) {
                check = 1;
                select = sc.nextInt();
                String area = null;
                switch (select) {
                    case 1: {
                        area = "Setapak";
                        break;
                    }
                    case 2: {
                        area = "Wangsa Maju";
                        break;
                    }
                    case 3: {
                        area = "Taman Melawati";
                        break;
                    }
                    case 4: {
                        area = "Genting Kelang";
                        break;
                    }
                    default: {
                        System.out.println("Invalid Input");
                    }
                }
                System.out.print("Latitude: ");
                sc.nextLine();
                String latitude = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                boolean b = true;
                for (int i = 1; i <= restaurant.getNumOfEntries(); i++) {
                    if (add.equals(restaurant.get(i).getAddress())) {
                        System.out.println("Restaurant already exists in the system.");
                        RestaurantRegistration();
                        b = false;
                    }
                }
                if (b == true) {
                    Restaurant rest = new Restaurant(restid, rName, rType, rMenu, oName, add, no, area, latitude, pass);
                    restaurant.add(rest);
                    System.out.println("Successfully Registered.");
                }
            } else {
                System.out.println("Invalid Input");
                sc.next();
                check = 0;
            }
        } while (check == 0);
    }

    public boolean RestaurantLogin(Restaurant r) {
        int idcount = 0;
        boolean exit = false;
        int password = 0;
        while (idcount == 0) {
            System.out.println("----------------");
            System.out.println("Affiliate Login");
            System.out.println("----------------");
            System.out.print("Restaurant ID (press E to exit): ");
            String id = sc.nextLine().toUpperCase();
            if (id.equals("E")) {
                exit = true;
                return exit;
            } else {
                for (int i = 1; i <= restaurant.getNumOfEntries(); i++) {
                    if (id.equals(restaurant.get(i).getRestaurantID())) {
                        while (password == 0) {
                            System.out.print("Password: ");
                            String pass = sc.nextLine();
                            idcount = 1;
                            if (pass.equals(restaurant.get(i).getPassword())) {
                                password = 1;
                                System.out.println("Successfully Login");
                                r = restaurant.get(i);
                                RestaurantMenu(r);
                            } else {
                                System.out.println("Invalid password");
                            }
                        }
                    }
                }
            }
            if (idcount == 0) {
                System.out.println("Invalid ID");
                RestaurantLogin(r);
            }
        }
        return exit;

    }

    public String FoodID() {
        int totalFood = food.getNumOfEntries();
        String foodid = null;
        if (!food.isEmpty()) {
            String fid = food.get(totalFood).getFoodID();
            int num = Integer.parseInt(fid.replaceAll("\\D+", ""));
            foodid = String.format("FM%06d", num + 1);
        } else {
            foodid = String.format("FM%06d", totalFood + 1);
        }
        return foodid;
    }

    public void addFood(Restaurant r) {
        int check = 0;
        System.out.println("--------");
        System.out.println("Add Food");
        System.out.println("--------");
        String foodid = FoodID();
        System.out.println("Food ID: " + foodid);
        System.out.print("Food Name: ");
        String fName = sc.nextLine();
        do {
            System.out.print("Food Price: RM");
            if (sc.hasNextDouble()) {
                Double price = sc.nextDouble();
                check = 1;
                sc.nextLine();
                System.out.print("Food Type(M-Main Dish S-Side Dish B-Beverage): ");
                char select = Character.toUpperCase(sc.next().charAt(0));
                char fAval = 'A';
                int pStatus = 1;
                switch (select) {

                    case 'M': {
                        String fType = "Main Dish";
                        Food f = new Food(foodid, fName, price, fType, fAval, r, pStatus);
                        food.add(f);
                        System.out.println("Food Successfully Added");
                        break;
                    }
                    case 'S': {
                        String fType = "Side Dish";
                        Food f = new Food(foodid, fName, price, fType, fAval, r, pStatus);
                        food.add(f);
                        System.out.println("Food Successfully Added");
                        break;
                    }
                    case 'B': {
                        String fType = "Beverage";
                        Food f = new Food(foodid, fName, price, fType, fAval, r, pStatus);
                        food.add(f);
                        System.out.println("Food Successfully Added");
                        break;
                    }
                    default: {
                        System.out.println("Invalid Input");
                        sc.nextLine();
                        addFood(r);
                        break;
                    }
                }
                int i = food.getNumOfEntries();
                System.out.println("Food ID: " + food.get(i).getFoodID());
                System.out.println("Food Name: " + food.get(i).getFoodName());
                System.out.println("Price: RM" + food.get(i).getFoodPrice());
                System.out.println("Food Type: " + food.get(i).getFoodType());
                System.out.println("Food Availability: Available");
                System.out.println("Successfully Added");
            } else {
                System.out.println("Invalid Input");
                sc.next();
                check = 0;
            }
        } while (check == 0);
        RestaurantMenu(r);
    }

    public void foodMenu(Restaurant r) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%8s %20s %15s %17s %18s \n", "Food ID", "Food Name", "Food Price (RM)", "Food Availability", "Promotional Status");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (int j = 1; j <= food.getNumOfEntries(); j++) {
            if ((r.getRestaurantID()).equals(food.get(j).getRestaurant().getRestaurantID()) && food.get(j).getFoodAvailability() == 'A' || food.get(j).getFoodAvailability() == 'N') {
                int pstatus = food.get(j).getpStatus();
                String pstat = "";
                switch (pstatus) {
                    case 1: {
                        pstat = "Normal";
                        break;
                    }
                    case 2: {
                        pstat = "Promo";
                        break;
                    }
                    case 3: {
                        pstat = "Exclusive Promo";
                        break;
                    }
                }
                System.out.printf("%8s %20s %15.2f %17s %18s \n", food.get(j).getFoodID(), food.get(j).getFoodName(),
                        food.get(j).getFoodPrice(), food.get(j).getFoodAvailability(), pstat);
            }
        }
    }

    public void updateFoodInterface(Restaurant r) {
        boolean id = false;
        System.out.println("------------------");
        System.out.println("Update Food Detail");
        System.out.println("------------------");
        System.out.println("Login as " + r.getRestaurantName() + " Restaurant\n");
        foodMenu(r);
        System.out.print("Enter Food ID to update (E to exit): ");
        String fid = sc.nextLine().toUpperCase();
        int i = 1;
        while (i <= food.getNumOfEntries()) {
            if (fid.equals(food.get(i).getFoodID()) && (r.getRestaurantID()).equals(food.get(i).getRestaurant().getRestaurantID())) {
                System.out.println("FoodID: " + food.get(i).getFoodID());
                System.out.println("Food Name: " + food.get(i).getFoodName());
                System.out.println("Price: RM" + food.get(i).getFoodPrice());
                System.out.println("Food Type: " + food.get(i).getFoodType());
                System.out.println("Food Availability: " + food.get(i).getFoodAvailability()
                        + " (A-Available N-Temporary not available)");
                System.out.println("Promotional Status: " + food.get(i).getpStatus()
                        + " (1-Normal 2-Promo 3-Exclusive Promo)");

                id = true;

                System.out.println("Which you want to update?");
                System.out.println("1 - Food Name");
                System.out.println("2 - Food Price");
                System.out.println("3 - Food Availability");
                System.out.println("4 - Promotional Status");
                System.out.println("0 - Back");
                System.out.print("Option: ");
                int option = sc.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("Current Food Name: " + food.get(i).getFoodName());
                        System.out.print("Updated Food Name: ");
                        sc.nextLine();
                        String uName = sc.nextLine();
                        food.get(i).setFoodName(uName);
                        System.out.println("Successfully updated");
                        RestaurantMenu(r);
                        break;
                    }
                    case 2: {
                        int t = 1;
                        System.out.println("Current Food Price: RM" + food.get(i).getFoodPrice());
                        do {
                            System.out.print("Updated Food Price: RM");
                            if (sc.hasNextDouble()) {
                                double uPrice = sc.nextDouble();
                                food.get(i).setFoodPrice(uPrice);
                                System.out.println("Successfully updated");
                                sc.nextLine();
                                RestaurantMenu(r);
                                t = 1;
                            } else {
                                System.out.println("Invalid Input");
                                sc.next();
                                t = 0;
                            }
                        } while (t == 0);
                        break;
                    }
                    case 3: {
                        int a = 0;
                        System.out.println("Current Food Availability: " + food.get(i).getFoodAvailability());
                        System.out.println("A-Available N-Temporary not available");
                        while (a == 0) {
                            System.out.print("Updated Availability: ");
                            char aval = Character.toUpperCase(sc.next().charAt(0));
                            switch (aval) {
                                case 'A':
                                case 'N': {
                                    food.get(i).setFoodAvailability(aval);
                                    a = 1;
                                    sc.nextLine();
                                    RestaurantMenu(r);
                                    break;
                                }
                                default: {
                                    System.out.println("Invalid Input");
                                    a = 0;
                                }
                            }
                        }
                        break;
                    }
                    case 4: {
                        int a = 0;
                        System.out.println("Current Promotional Status: " + food.get(i).getpStatus());
                        System.out.println("1-Normal 2-Promo 3-Exclusive Promo");
                        do {
                            System.out.print("Updated Promotional Status: ");
                            if (sc.hasNextInt()) {
                                int select = sc.nextInt();
                                switch (select) {
                                    case 1: {
                                        food.get(i).setpStatus(1);
                                        a = 1;
                                        sc.nextLine();
                                        RestaurantMenu(r);
                                        break;
                                    }
                                    case 2: {
                                        food.get(i).setpStatus(2);
                                        a = 1;
                                        sc.nextLine();
                                        RestaurantMenu(r);
                                        break;
                                    }
                                    case 3: {
                                        food.get(i).setpStatus(3);
                                        a = 1;
                                        sc.nextLine();
                                        RestaurantMenu(r);
                                        break;
                                    }
                                    default: {
                                        System.out.println("Invalid Input");
                                        a = 0;
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("Invalid Input");
                                sc.next();
                                a = 0;
                            }
                        } while (a == 0);
                        break;
                    }
                    case 0: {
                        sc.nextLine();
                        RestaurantMenu(r);
                        break;
                    }
                    default: {
                        System.out.println("Invalid. Please try again.");
                        break;
                    }
                }

            }
            i++;
        }
        if (fid.equals("E")) {
            RestaurantMenu(r);
            id = true;
        }
        if (id == false) {
            System.out.println("Invalid Food ID, Please try again.");
            updateFoodInterface(r);
        }
    }

    public void deleteFood(Restaurant r) {
        int f = 0;
        System.out.println("----------------");
        System.out.println("Delete Food Menu");
        System.out.println("----------------\n");
        foodMenu(r);
        System.out.println("Which one you wish to delete");
        System.out.print("Enter Food ID to delete: ");
        String fid = sc.nextLine().toUpperCase();
        for (int i = 1; i <= food.getNumOfEntries(); i++) {
            if (fid.equals(food.get(i).getFoodID()) && r.getRestaurantID().equals(food.get(i).getRestaurant().getRestaurantID())) {
                System.out.println("Are you sure you want to delete?(y - yes n - no)");
                System.out.print("Option:");
                char option = Character.toUpperCase(sc.nextLine().charAt(0));
                switch (option) {
                    case 'Y': {
                        System.out.println("Successfully deleted");
                        food.delete(i);
                        f = 1;
                        RestaurantMenu(r);
                        break;
                    }
                    case 'N': {
                        f = 1;
                        RestaurantMenu(r);
                        break;
                    }
                    default: {
                        f = 0;
                        System.out.println("Invalid input");
                        RestaurantMenu(r);
                        break;
                    }
                }
            }
        }
        if (f == 0) {
            System.out.println("Invalid input");
            deleteFood(r);
        }

    }

    public void SelectShowFirstMenu(Restaurant r) {
        int check = 0;
        System.out.println("----------------------");
        System.out.println("Select Show First Menu");
        System.out.println("----------------------\n");

        System.out.println("Please select which you want to show first.");
        System.out.println("A. Show as Normal");
        System.out.println("N. Show Newest Items First");
        System.out.println("P. Show Promotional Items First\n");

        System.out.println("Current Showed Menu: " + r.getRMenu());
        System.out.println("(A-Show as Normal N-Newest Items First P-Promotional Items First)");
        do {
            System.out.print("Option: ");
            char select = Character.toUpperCase(sc.next().charAt(0));
            switch (select) {
                case 'A': {
                    System.out.println("Successful Changed.");
                    char sf = 'A';
                    r.setRMenu(sf);
                    check = 1;
                    RestaurantMenu(r);
                    break;
                }
                case 'N': {
                    System.out.println("Successful Changed.");
                    char sf = 'N';
                    r.setRMenu(sf);
                    check = 1;
                    RestaurantMenu(r);
                    break;
                }
                case 'P': {
                    System.out.println("Successful Changed.");
                    char sf = 'P';
                    r.setRMenu(sf);
                    check = 1;
                    RestaurantMenu(r);
                    break;
                }
                default: {
                    System.out.println("Invalid input.");
                    check = 0;
                }
            }
        } while (check == 0);

    }

    public void showFoodMenu(Restaurant r, AListInterface<Food> f) {
        char rMenu = r.getRMenu();
        switch (rMenu) {
            case 'A': {
                showMenu(r, f);
                break;
            }
            case 'N': {
                showNewest(r, f);
                break;
            }
            case 'P': {
                showPromotional(r, f);
                break;
            }
        }
    }

    public void showMenu(Restaurant r, AListInterface<Food> f) {
        System.out.println("---------");
        System.out.println("Food Menu");
        System.out.println("---------");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%8s %20s %15s %15s \n", "Food ID", "Food Name", "Food Price(RM)", "Food Type");
        System.out.println("--------------------------------------------------------------------------");
        for (int j = 1; j <= food.getNumOfEntries(); j++) {

            if (r.getRestaurantID().equals(food.get(j).getRestaurant().getRestaurantID()) && food.get(j).getFoodAvailability() == 'A') {
                System.out.printf("%8s %20s %15.2f %15s \n", food.get(j).getFoodID(), food.get(j).getFoodName(),
                        food.get(j).getFoodPrice(), food.get(j).getFoodType());
            }
        }
    }

    public void showNewest(Restaurant r, AListInterface<Food> f) {
        System.out.println("---------");
        System.out.println("Food Menu");
        System.out.println("---------");
        System.out.println("From Newest Menu to Oldest Menu");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%8s %20s %15s %15s \n", "Food ID", "Food Name", "Food Price(RM)", "Food Type");
        System.out.println("----------------------------------------------------------------------------------");
        String id = r.getRestaurantID();
        food.sortNewest();
        for (int i = 1; i <= food.getNumOfEntries(); i++) {
            if (id.equals(food.get(i).getRestaurant().getRestaurantID()) && food.get(i).getFoodAvailability() == 'A') {
                System.out.printf("%8s %20s %15.2f %15s \n", food.get(i).getFoodID(), food.get(i).getFoodName(),
                        food.get(i).getFoodPrice(), food.get(i).getFoodType());
            }
        }
        food.sortNormal();
    }

    public void showPromotional(Restaurant r, AListInterface<Food> f) {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%8s %20s %15s %15s %18s \n", "Food ID", "Food Name", "Food Price (RM)", "Food Type", "Promotional Status");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        food.sortPromotional();
        for (int j = 1; j <= food.getNumOfEntries(); j++) {
            if ((r.getRestaurantID()).equals(food.get(j).getRestaurant().getRestaurantID()) && food.get(j).getFoodAvailability() == 'A') {
                int pstatus = food.get(j).getpStatus();
                String pstat = "";
                switch (pstatus) {
                    case 1: {
                        pstat = "Normal";
                        break;
                    }
                    case 2: {
                        pstat = "Promo";
                        break;
                    }
                    case 3: {
                        pstat = "Exclusive Promo";
                        break;
                    }
                }
                System.out.printf("%8s %20s %15.2f %15s %18s \n", food.get(j).getFoodID(), food.get(j).getFoodName(),
                        food.get(j).getFoodPrice(), food.get(j).getFoodType(), pstat);
            }

        }
        food.sortNormal();
    }

    public void genDetailReportByArea() {
        restaurant.sortRestaurantByArea();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("Printed on " + dateFormat.format(date));
        System.out.println("Restaurant Detailed Report\n");

        System.out.println("-----------------------------------------------------------------"
                + "-------------------------------------------------------------------------");
        System.out.printf("%13s %20s %15s %20s %15s %18s %30s\n", "Restaurant ID", "Restaurant Name", "Restaurant Type",
                "Owner Name", "Contact No", "Area", "Address");
        System.out.println("------------------------------------------------------------------"
                + "-----------------------------------------------------------------------");

        for (int j = 1; j <= restaurant.getNumOfEntries(); j++) {
            System.out.printf("%13s %20s %15s %20s %15s %18s %30s\n", restaurant.get(j).getRestaurantID(),
                    restaurant.get(j).getRestaurantName(), restaurant.get(j).getRestaurantType(), restaurant.get(j).getOwnerName(),
                    restaurant.get(j).getContactNo(), restaurant.get(j).getArea(), restaurant.get(j).getAddress());
        }

        System.out.println("------------------------------------------------------------------"
                + "-----------------------------------------------------------------------");
        System.out.printf("%113s%6d%12s\n", "Total Number Of Registered Restaurant: ", restaurant.getNumOfEntries(), " Restaurants.");
        System.out.println("------------------------------------------------------------------"
                + "-----------------------------------------------------------------------");
    }

    public void RestaurantMenu(Restaurant r) {
        int check = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------");
        System.out.println("Restaurant Menu");
        System.out.println("---------------");
        System.out.println("1. Add New Menu Items");
        System.out.println("2. Update Menu Item Details");
        System.out.println("3. Remove Menu Items");
        System.out.println("4. Select Show First Menu");
        System.out.println("5. Show Menu");
        System.out.println("0. Log Out");
        do {
            System.out.print("Option: ");
            if (sc.hasNextInt()) {
                check = 1;
                int selection = sc.nextInt();
                sc.nextLine();
                switch (selection) {
                    case 1: {
                        addFood(r);
                        break;
                    }
                    case 2: {
                        updateFoodInterface(r);
                        break;
                    }
                    case 3: {
                        deleteFood(r);
                        break;
                    }
                    case 4: {
                        SelectShowFirstMenu(r);
                        break;
                    }
                    case 5: {
                        showFoodMenu(r, food);
                        RestaurantMenu(r);
                        break;
                    }
                    case 0: {
                        System.out.println("Successfully Logout");
                        break;
                    }
                    default: {
                        System.out.println("Error. Please key in again.");
                        RestaurantMenu(r);
                        break;
                    }
                }
            } else {
                System.out.println("Invalid Input");
                sc.next();
                check = 0;
            }
        } while (check == 0);
    }

    //End Module A---------------------------------------------------------------------------------------------------------
    //MODULE D==============================================================================================================================
    public void DeliveryManMainMenu(DeliveryMan DM)//DeliveryMan <<<<<<<< MAIN MENU >>>>>>>>
    {
        Date date = new Date();
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\n\nWelcome Back, " + loginStaff.getStaffName() + "\nCurrent Date: " + dateFormat.format(date) + "\nCurrent Status: " + DM.getCurrentAvailable());  //display login staff information

        if (DM.getCurrentAvailable().equals("Not Available")) {
            DeliveryManMenuNotAva(DM);
        } else if (DM.getCurrentAvailable().equals("Available")) {
            AutoAssign1();
            DeliveryManMenuAva(DM);
        } else if (DM.getCurrentAvailable().equals("Break")) {
            DeliveryManMenuBreak(DM);
        } else if (DM.getCurrentAvailable().equals("Deliver")) {
            DeliveryManMenuDeliver(DM);
        }
    }

    public void DeliveryManMenuNotAva(DeliveryMan DM)//While DeliveryMan Status "Not Available"
    {
        String choice = "None";
        boolean checking = true;

        ModuleDInterface<DeliveryMan> CLDMList = new ModuleDList<>();
        for (int j = 1; j <= DMList.getTotalEntries(); j++) {
            CLDMList.add(DMList.get(j));
        }
        System.out.println("\nPlease Select The Option Below");
        System.out.println("1. Clock In \n2. Exit");
        while (!choice.equals("1") && !choice.equals("2")) {
            System.out.print("Option : ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    for (int j = 1; j <= CLDOrderList.getTotalEntries(); j++) {
                        if (CLDOrderList.get(j).getDeliveryStatus().equals("Pending") && CLDOrderList.get(j).getWS().getDM().getStaffID().equals(DM.getStaffID())) {
                            checking = false;
                        }
                    }
                    if (checking) {
                        String WSID = String.format("WS%06d", wsList.getTotalEntries() + 1);

                        CLDMList.updateDMClockInOut(DM.getStaffID());
                        wsList.add(new WorkStatus(WSID, Calendar.getInstance(), Calendar.getInstance(), 0, 0, DM));
                    }
                    DeliveryManMainMenu(DM);
                    break;
                }
                case "2": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    choice = "None";
                }
            }
        }
    }

    public void DeliveryManMenuAva(DeliveryMan DM)//While DeliveryMan Status "Available"
    {
        String choice = "None";
        boolean checking = true;

        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        String tmpDate = "";
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        ModuleDInterface<DeliveryMan> CLDMList = new ModuleDList<>();
        for (int j = 1; j <= DMList.getTotalEntries(); j++) {
            CLDMList.add(DMList.get(j));
        }
        System.out.println("\nToday Delivery Order : ");
        System.out.println("***************************************************************************");
        System.out.printf("*%3s * %10s * %16s * %15s * %16s * \n", "No", "Order ID", "Restaurant Area", "Customer Area", "Delivery Status");
        System.out.println("***************************************************************************");
        int no = 1;
        for (int j = 1; j <= CLDOrderList.getTotalEntries(); j++) {
            tmpDate = dateFormat.format(CLDOrderList.get(j).getAssignedDate().getTime());
            if (tmpDate.equals(dateFormat.format(date)) && CLDOrderList.get(j).getWS().getDM().getStaffID().equals(DM.getStaffID())) {
                System.out.printf("*%3s * %10s * %16s * %15s * %16s * \n", no, CLDOrderList.get(j).getOrder().getOrdersID(), CLDOrderList.get(j).getOrder().getRestaurant().getArea(), CLDOrderList.get(j).getOrder().getCustomer().getCustArea(), CLDOrderList.get(j).getDeliveryStatus());
                no++;
            }
        }
        if (no == 1) {
            System.out.println("*                          No Deliver Order                               *");
        }
        System.out.println("***************************************************************************");

        System.out.println("\nPlease Select The Option Below");
        System.out.println("1. Clock Out \n2. Deliver Order \n3. Update Personal Contact Details \n4. Retrive Customer Details \n5. Break \n6. Exit");
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5") && !choice.equals("6")) {
            System.out.print("Option : ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    for (int j = 1; j <= CLDOrderList.getTotalEntries(); j++) {
                        if (CLDOrderList.get(j).getDeliveryStatus().equals("Pending") && CLDOrderList.get(j).getWS().getDM().getStaffID().equals(DM.getStaffID())) {
                            checking = false;
                        }
                    }
                    if (checking) {
                        CLDMList.updateDMClockInOut(DM.getStaffID());
                        for (int k = 1; k <= wsList.getTotalEntries(); k++) {
                            if (wsList.get(k).getDM().getStaffID().equals(DM.getStaffID()) && wsList.get(k).getCheckIn().equals(wsList.get(k).getCheckOut())) {
                                wsList.get(k).setCheckOut(cal);
                            }
                        }
                    }
                    DeliveryManMainMenu(DM);
                    break;
                }
                case "2": {
                    DeliverOrder(DM);
                    break;
                }
                case "3": {
                    DeliveryManUpdatePersonalDetails();
                    break;
                }
                case "4": {
                    retrieveCustomer();
                    break;
                }
                case "5": {
                    DeliveryManStatus(DM);
                    break;
                }
                case "6": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    choice = "None";
                }
            }
        }
    }

    public void DeliveryManMenuBreak(DeliveryMan DM)//While DeliveryMan Status "Break"
    {
        String choice = "None";
        boolean checking = true;
        Calendar cal = Calendar.getInstance();

        ModuleDInterface<DeliveryMan> CLDMList = new ModuleDList<>();
        for (int j = 1; j <= DMList.getTotalEntries(); j++) {
            CLDMList.add(DMList.get(j));
        }

        System.out.println("\nPlease Select The Option Below");
        System.out.println("1. Clock Out \n2. Update Personal Contact Details \n3. Retrive Customer Details \n4. Available \n5. Exit");
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("5")) {
            System.out.print("Option : ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    for (int j = 1; j <= CLDOrderList.getTotalEntries(); j++) {
                        if (CLDOrderList.get(j).getDeliveryStatus().equals("Pending") && CLDOrderList.get(j).getWS().getDM().getStaffID().equals(DM.getStaffID())) {
                            checking = false;
                        }
                    }
                    if (checking) {
                        CLDMList.updateDMClockInOut(DM.getStaffID());
                        for (int k = 1; k <= wsList.getTotalEntries(); k++) {
                            if (wsList.get(k).getDM().getStaffID().equals(DM.getStaffID()) && wsList.get(k).getCheckIn().equals(wsList.get(k).getCheckOut())) {
                                wsList.get(k).setCheckOut(cal);
                            }
                        }
                    }
                    DeliveryManMainMenu(DM);
                    break;
                }
                case "2": {
                    DeliveryManUpdatePersonalDetails();
                    break;
                }
                case "3": {
                    retrieveCustomer();
                    break;
                }
                case "4": {
                    DeliveryManStatus(DM);
                    break;
                }
                case "5": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    choice = "None";
                }
            }
        }
    }

    public void DeliveryManMenuDeliver(DeliveryMan DM)//While DeliveryMan Status "Deliver"
    {
        String choice = "None";

        ModuleDInterface<DeliveryMan> CLDMList = new ModuleDList<>();
        for (int j = 1; j <= DMList.getTotalEntries(); j++) {
            CLDMList.add(DMList.get(j));
        }

        System.out.println("\nCurrent Delivery Order : ");
        System.out.println("**********************************************************************");
        System.out.printf("* %10s * %16s * %15s * %16s * \n", "Order ID", "Restaurant Area", "Customer Area", "Delivery Status");
        System.out.println("**********************************************************************");
        for (int j = 1; j <= CLDOrderList.getTotalEntries(); j++) {
            if (CLDOrderList.get(j).getDeliveryStatus().equals("Deliver") && CLDOrderList.get(j).getWS().getDM().getStaffID().equals(DM.getStaffID())) {
                System.out.printf("* %10s * %16s * %15s * %16s * \n", CLDOrderList.get(j).getOrder().getOrdersID(), CLDOrderList.get(j).getOrder().getRestaurant().getArea(), CLDOrderList.get(j).getOrder().getCustomer().getCustArea(), CLDOrderList.get(j).getDeliveryStatus());
            }
        }
        System.out.println("**********************************************************************");

        System.out.println("\nDeliver Order Customer Detail : ");
        for (int j = 1; j <= order.getTotalEntries(); j++) {
            if (CLDOrderList.get(j).getDeliveryStatus().equals("Deliver") && CLDOrderList.get(j).getWS().getDM().getStaffID().equals(DM.getStaffID())) {
                System.out.printf("%-8s : %-50s\n", "Name", CLDOrderList.get(j).getOrder().getCustomer().getCustName());
                System.out.printf("%-8s : %-50s\n", "Tel.", CLDOrderList.get(j).getOrder().getCustomer().getCustTelNo());
                System.out.printf("%-8s : %-50s\n", "Address", CLDOrderList.get(j).getOrder().getCustomer().getCustAddress());
            }
        }

        System.out.println("\nPlease Select The Option Below");
        System.out.println("1. Complete Deliver \n2. Exit");
        while (!choice.equals("1") && !choice.equals("2")) {
            System.out.print("Option : ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    CompletedDeliver(DM);
                    break;
                }
                case "2": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    choice = "None";
                }
            }
        }
    }

    public void DeliverOrder(DeliveryMan DM)//Deliver the Pending Order
    {
        boolean check = false;

        for (int i = 1; i <= CLDOrderList.getTotalEntries(); i++) {
            int deliverTime = 0;
            int calDistance = 0;

            if (CLDOrderList.get(i).getWS().getDM().getStaffID().equals(DM.getStaffID()) && CLDOrderList.get(i).getWS().getDM().getCurrentAvailable().equals("Available")) {
                if (CLDOrderList.get(i).getDeliveryStatus().equals("Pending")) {
                    //Restaurant Area 1km = 5minute;
                    if (CLDOrderList.get(i).getOrder().getRestaurant().getArea().equals("Setapak")) {
                        deliverTime += 2 * 5;
                        calDistance += 2;
                    } else if (CLDOrderList.get(i).getOrder().getRestaurant().getArea().equals("Wangsa Maju")) {
                        deliverTime += 4 * 5;
                        calDistance += 4;
                    } else if (CLDOrderList.get(i).getOrder().getRestaurant().getArea().equals("Genting Kelang")) {
                        deliverTime += 6 * 5;
                        calDistance += 6;
                    } else if (CLDOrderList.get(i).getOrder().getRestaurant().getArea().equals("Taman Melawati")) {
                        deliverTime += 8 * 5;
                        calDistance += 8;
                    } else {
                        deliverTime += 0;
                        calDistance += 0;
                    }

                    //Customer Area 1km = 5minute;
                    if (CLDOrderList.get(i).getOrder().getCustomer().getCustArea().equals("Setapak")) {
                        deliverTime += 2 * 5;
                        calDistance += 2;
                    } else if (CLDOrderList.get(i).getOrder().getCustomer().getCustArea().equals("Wangsa Maju")) {
                        deliverTime += 4 * 5;
                        calDistance += 4;
                    } else if (CLDOrderList.get(i).getOrder().getCustomer().getCustArea().equals("Genting Kelang")) {
                        deliverTime += 6 * 5;
                        calDistance += 6;
                    } else if (CLDOrderList.get(i).getOrder().getCustomer().getCustArea().equals("Taman Melawati")) {
                        deliverTime += 8 * 5;
                        calDistance += 8;
                    } else {
                        deliverTime += 0;
                        calDistance += 0;
                    }

                    Calendar ExtimateDeliverTime = Calendar.getInstance();
                    ExtimateDeliverTime.add(Calendar.MINUTE, deliverTime);
                    CLDOrderList.get(i).setDeliveredDate(ExtimateDeliverTime);
                    CLDOrderList.get(i).setDeliveredTime(ExtimateDeliverTime);
                    CLDOrderList.get(i).setDeliveryStatus("Deliver");
                    CLDOrderList.get(i).getWS().setTotalDeliveredOrder(CLDOrderList.get(i).getWS().getTotalDeliveredOrder() + 1);
                    CLDOrderList.get(i).getWS().setTotalDistance(CLDOrderList.get(i).getWS().getTotalDistance() + calDistance);

                    CLDOrderList.get(i).getOrder().setOrderStatus("3");

                    for (int j = 1; j <= DMList.getTotalEntries(); j++) {
                        if (DMList.get(j).getStaffID().equals(DM.getStaffID())) {
                            DMList.get(j).setCurrentAvailable("Deliver");
                        }
                    }
                    check = true;
                }
            }
        }
        if (!check) {
            System.out.println("\n\nNo Deliver Order\n\n");
            s.nextLine();
        }
        DeliveryManMainMenu(DM);
    }

    public void CompletedDeliver(DeliveryMan DM)//The Order is complete deliver
    {
        for (int i = 1; i <= CLDOrderList.getTotalEntries(); i++) {
            if (CLDOrderList.get(i).getWS().getDM().getStaffID().equals(DM.getStaffID()) && CLDOrderList.get(i).getWS().getDM().getCurrentAvailable().equals("Deliver")) {
                if (CLDOrderList.get(i).getDeliveryStatus().equals("Deliver")) {
                    CLDOrderList.get(i).setDeliveryStatus("Complete");
                    CLDOrderList.get(i).getOrder().setOrderStatus("4");
                    CLDOrderList.get(i).setFeedback("Pending");
                    for (int j = 1; j <= DMList.getTotalEntries(); j++) {
                        if (DMList.get(j).getStaffID().equals(DM.getStaffID())) {
                            DMList.get(j).setCurrentAvailable("Available");
                            DMList.get(j).setTotalPendingDelivery(DMList.get(j).getTotalPendingDelivery() - 1);
                        }
                    }
                }
            }
        }
        DeliveryManMainMenu(DM);
    }

    public void DeliveryManStatus(DeliveryMan DM)//Update the delivery status to Break / Available
    {
        if (DM.getCurrentAvailable().equals("Available")) {
            DM.setCurrentAvailable("Break");
        } else if (DM.getCurrentAvailable().equals("Break")) {
            DM.setCurrentAvailable("Available");
        }

        DeliveryManMainMenu(DM);
    }

    public void AutoAssign1()//Get Order
    {
        Orders curOrder = new Orders();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -2);

        for (int i = 1; i <= order.getTotalEntries(); i++) {
            if (order.get(i).getOrderStatus().equals("1")) {
                curOrder = order.get(i);
                AutoAssign2(curOrder);
            }
        }
    }

    public void AutoAssign2(Orders curOrder)//Assign
    {
        Calendar cal = Calendar.getInstance();

        WorkStatus curWS = new WorkStatus();
        int tmpPendingDelivery = 0;
        boolean Available = false;
        boolean check = false;

        for (int y = 1; y <= wsList.getTotalEntries(); y++) {
            CLWSList.add(wsList.get(y));
        }
        for (int j = 1; j <= CLWSList.getTotalEntries(); j++) {
            if (CLWSList.get(j).getDM().getCurrentAvailable().equals("Available")) {
                tmpPendingDelivery = CLWSList.get(j).getDM().getTotalPendingDelivery();
                curWS = CLWSList.get(j);
                check = true;
                Available = true;
            }
        }

        for (int i = 1; i <= CLWSList.getTotalEntries(); i++) {
            if (CLWSList.get(i).getDM().getTotalPendingDelivery() < tmpPendingDelivery && CLWSList.get(i).getDM().getCurrentAvailable().equals("Available")) {
                curWS = CLWSList.get(i);
            }
        }

        if (!Available) {
            for (int j = 1; j <= CLWSList.getTotalEntries(); j++) {
                if (CLWSList.get(j).getDM().getCurrentAvailable().equals("Deliver")) {
                    tmpPendingDelivery = CLWSList.get(j).getDM().getTotalPendingDelivery();
                    curWS = CLWSList.get(j);
                    check = true;
                }
            }

            for (int i = 1; i <= CLWSList.getTotalEntries(); i++) {
                if (CLWSList.get(i).getDM().getTotalPendingDelivery() < tmpPendingDelivery && CLWSList.get(i).getDM().getCurrentAvailable().equals("Deliver")) {
                    curWS = CLWSList.get(i);
                }
            }
        }

        if (check) {
            DeliveryOrder DO = new DeliveryOrder(curWS, curOrder, cal, cal, cal, cal, "Pending", "Not Yet Delivered");
            CLDOrderList.add(DO);
            curOrder.setOrderStatus("2");
            int tpd = curWS.getDM().getTotalPendingDelivery();
            curWS.getDM().setTotalPendingDelivery(tpd + 1);
        }

    }

    public void DisplayCustomerOrder(Customer cust) {
        ModuleDInterface<Orders> CLORList = new ModuleDList<>();
        for (int j = 1; j <= order.getTotalEntries(); j++) {
            CLORList.add(order.get(j));
        }
        String CustID = cust.getCustID();
        String choice = "None";

        System.out.println("\nPlease Select The Option Below");
        System.out.println("1. View Deliver Order \n2. View Deliver Order already Assign \n3. View Deliver Order Time Remain \n4. Exit");
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) {
            System.out.print("Option : ");
            choice = s.nextLine();
            switch (choice) {
                case "1": {
                    CLORList.displayCustomerPendingOrderInTable(CustID);
                    DisplayCustomerOrder(cust);
                    break;
                }
                case "2": {
                    CLDOrderList.displayCustomerAssignOrderInTable(CustID);
                    DisplayCustomerOrder(cust);
                    break;
                }
                case "3": {
                    CLDOrderList.displayCustomerDeliverOrderInTable(CustID);
                    DisplayCustomerOrder(cust);
                    break;
                }
                case "4": {
                    break;
                }
                default: {
                    System.out.println("Please Enter Again...");
                    choice = "None";
                }
            }
        }
    }

    public void GenerateDeliveryManTotalDeliverReport() {
        ModuleDInterface<WorkStatus> CLWSList = new ModuleDList<>();
        for (int j = 1; j <= wsList.getTotalEntries(); j++) {
            CLWSList.add(wsList.get(j));
        }

        Calendar curCal = Calendar.getInstance();
        Scanner sc = new Scanner(System.in);
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        DateFormat datetimeFormat = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dateEnter = "";
        boolean check = false;

        System.out.print("Enter Date (dd/MM/yyyy): ");
        dateEnter = sc.nextLine();

        System.out.println("DeliveryMan TotalDeliver Report on " + dateEnter);
        System.out.println("*****************************************************************************************************");
        System.out.printf("* %10s * %15s * %20s * %20s * %20s *\n", "Working ID", "DeliveryMan ID", "DeliveryMan Name", "Check In Time", "Total Deliver Order");
        System.out.println("*****************************************************************************************************");
        for (int t = 1; t <= CLWSList.getTotalEntries(); t++) {
            if (dateFormat.format(CLWSList.get(t).getCheckIn().getTime()).equals(dateEnter)) {
                System.out.printf("* %10s * %15s * %20s * %20s * %20d *\n", CLWSList.get(t).getWorkingID(), CLWSList.get(t).getDM().getStaffID(), CLWSList.get(t).getDM().getStaffName(), datetimeFormat.format(CLWSList.get(t).getCheckIn().getTime()), CLWSList.get(t).getTotalDeliveredOrder());
                check = true;
            }
        }
        if (!check) {
            System.out.printf("*                                Do not have any record on %-40s *\n", dateEnter);
        }
        System.out.println("*****************************************************************************************************");
    }
}
