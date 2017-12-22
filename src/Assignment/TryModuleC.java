/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;
import domain.*;
import ADT.ModuleCInterface;
import ADT.ModuleCList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author ong
 */
public class TryModuleC {
    Scanner s = new Scanner(System.in);
    Orders currentOrder = new Orders();
    ModuleCInterface<OrderDetail> currentDetail = new ModuleCList<OrderDetail>();
    double Subtotal = 0.00;
    ModuleCInterface<Food> CurrentFood = new ModuleCList<Food>();
    ModuleCInterface<Restaurant> restaurant = new ModuleCList<Restaurant>();
    ModuleCInterface<Food> food = new ModuleCList<Food>();
    ModuleCInterface<Customer> customer = new ModuleCList<Customer>();
    ModuleCInterface<Payment> payment = new ModuleCList<Payment>();
    ModuleCInterface<Orders> order = new ModuleCList<Orders>();
    ModuleCInterface<OrderDetail> orderdetail = new ModuleCList<OrderDetail>();
    /*private List<Orders> order = new ArrayList<>();
    private List<OrderDetail> orderdetail = new ArrayList<>();*/
    public static void main(String[] args) {
        TryModuleC keke = new TryModuleC();
        keke.initializeList();
    }
    
    public void initializeList(){
        restaurant.add(new Restaurant("RE000001", "Nandos", "Western", 'A', "Tneh Chee Wei", "asd", "016-6666666", "Setapak", "100", "1234567890"));
        restaurant.add(new Restaurant("RE000002", "KFC", "FastFood", 'N', "Tneh Chee Wai", "asd", "016-6666666", "Wangsa Maju", "200", "1234567890"));
        food.add(new Food("FM000001", "Chicken Bolognese", 11.50, "Noodles", 'A', restaurant.get(1), 'N'));
        food.add(new Food("FM000002", "Fish Bolognese", 11.50, "Noodles", 'A', restaurant.get(1), 'N'));
        food.add(new Food("FM000003", "Beef Bolognese", 13.50, "Noodles", 'A', restaurant.get(1), 'N'));
        food.add(new Food("FM000004", "Dinner Plate A", 11.50, "Set", 'A', restaurant.get(2), 'N'));
        food.add(new Food("FM000005", "Dinner Plate B", 12.50, "Set", 'A', restaurant.get(2), 'N'));
        food.add(new Food("FM000006", "Dinner Plate C", 13.50, "Set", 'A', restaurant.get(2), 'N'));
        food.add(new Food("FM000007", "Chicken Rice", 9.50, "Rice", 'A', restaurant.get(1), 'Y'));
        food.add(new Food("FM000008", "Dinner Plate D", 5.50, "Set", 'A', restaurant.get(2), 'Y'));
        food.add(new Food("FM000009", "Dinner Plate E", 6.50, "Set", 'A', restaurant.get(2), 'Y'));
        customer.add(new Customer("CU000001", "Miw Jin Li", "14,Taman Cantik,53300,Setapak,Kuala Lumpur", "Setapak", "0167897898", "971003355333", "1234567890"));
        customer.add(new Customer("CU000002", "Miw Jin Le", "14,Taman Cantik,53300,Wangsa Maju,Kuala Lumpur", "Wangsa Maju", "0167897899", "970104079999", "1234567890"));
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         try{
             Date date = dateFormat.parse("2017/12/21 12:21:12");
             cal.setTime(date);
             Date date1 = dateFormat.parse("2017/12/21 11:21:12");
             cal1.setTime(date1);
             Date date2 = dateFormat.parse("2017/12/21 10:21:12");
             cal2.setTime(date2);
         }catch(ParseException ex){
             System.out.println(ex);
         }
         order.add(new Orders(restaurant.get(1), customer.get(1), "OR000001", 10.00, 11.00, "1",cal2));
         order.add(new Orders(restaurant.get(2), customer.get(2), "OR000002", 11.00, 12.00, "1",cal));
         order.add(new Orders(restaurant.get(1), customer.get(1), "OR000003", 12.00, 13.00, "1",cal1));
         order.add(new Orders(restaurant.get(2), customer.get(2), "OR000004", 13.00, 14.00, "1",cal));
         orderdetail.add(new OrderDetail(order.get(1), food.get(1), 1,15.00));
         orderdetail.add(new OrderDetail(order.get(2), food.get(1), 1,16.00));
         orderdetail.add(new OrderDetail(order.get(3), food.get(2), 1,15.00));
         orderdetail.add(new OrderDetail(order.get(4), food.get(1), 1,16.00));
         payment.add(new Payment("PA000001", order.get(1), 10.00, cal2, "1", "Online"));
         payment.add(new Payment("PA000002", order.get(2), 11.00, cal, "1", "Online"));
         payment.add(new Payment("PA000003", order.get(3), 12.00, cal1, "1", "Online"));
         payment.add(new Payment("PA000004", order.get(4), 13.00, cal, "1", "Online"));
         CustomerLogin();
         GenerateOrderReport();
    }
    
    public void CustomerLogin(){
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
        
        for(int i=1 ; i<=customer.getTotalEntries(); i++){
            if(name.equals(customer.get(i).getCustName().toUpperCase())){
                if(password.equals(customer.get(i).getCustPass())){
                    check++;
                    System.out.println("Login Successful");
                    CustomerMenu(customer.get(i));
                }
                else{
                    System.out.println("Password is Invalid");
                    CustomerLogin();
                }
            }
        }
        if(check==0){
            System.out.println("This customer name is not exist");
            CustomerLogin();
        }
    }
    
    public void CustomerMenu(Customer current){
        String selection = "0";

        System.out.println("--------------");
        System.out.println("Customer Menu");
        System.out.println("--------------");
        System.out.println("Please select the below option");
        System.out.println("1. Make Order");
        System.out.println("2. Cancel Order");
        System.out.println("3. Logout");
        while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3")) {
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
    
    public String getCurrentID(){
        String nextID = "";
        if(order.isEmpty()){
                nextID = "OR000001";
        }
        else{
            String currentID = order.get(order.getTotalEntries()).getOrdersID();
            int ID = Integer.parseInt(currentID.replace("OR", ""));
            ID++;
            nextID = "OR" + String.format("%06d",ID);
        }
        return nextID;
    }
    
    public String getPaymentCurrentID(){
        String nextID = "";
        if(payment.isEmpty()){
                nextID = "PA000001";
        }
        else{
            String currentID = payment.get(payment.getTotalEntries()).getPaymentID();
            int ID = Integer.parseInt(currentID.replace("PA", ""));
            ID++;
            nextID = "PA" + String.format("%06d",ID);
        }
        return nextID;
    }
    
    public void cancelOrder(Customer current){
        String selection = "",orderid = "";
        boolean found = false, recordfound = false;
        do{
            Calendar cal = Calendar.getInstance();
            System.out.println("Below showing the order that you can cancel");
            System.out.println("You only can cancel order within 2 minute after you ordered");
            for(int i=1 ; i<=order.getTotalEntries() ; i++){
                if(cal.getTimeInMillis()-order.get(i).getOrdersDateTime().getTimeInMillis()<2*60*1000&&order.get(i).getOrderStatus().equals("1")
                        &&order.get(i).getCustomer().getCustID().equals(current.getCustID())){
                    System.out.println("Order ID = "+order.get(i).getOrdersID());
                    System.out.println("Restaurant = "+order.get(i).getRestaurant().getRestaurantName());
                    System.out.printf("Total = %.2f\n",order.get(i).getTotal());
                    recordfound = true;
                }
            }
            if(recordfound == true){
                System.out.print("\nPlease Key in the order id that u want to cancel(0 to back)");
                orderid = s.nextLine();
                if(orderid.equals("0")){
                    found = true;
                    return;
                }
                for(int j=1 ; j<=order.getTotalEntries()&&found==false ; j++){
                    if(order.get(j).getOrdersID().toUpperCase().equals(orderid.toUpperCase())&&cal.get(Calendar.MINUTE)-order.get(j).getOrdersDateTime().get(Calendar.MINUTE)<2&&order.get(j).getOrderStatus().equals("1")
                            &&order.get(j).getCustomer().getCustID().equals(current.getCustID())){
                        found = true;
                        order.get(j).setOrderStatus("0");
                        payment.get(j).setPaymentStatus("0");
                        System.out.println("Cancel Order Successful, Payment Amount Have Been Returned.");
                    }
                }
                if(found == false){
                    System.out.println("Please Key In Again");
                }
            }
            else{
                System.out.println("No Record");
            }
        }while(found == false&&recordfound == true);
    }
    
    public void SelectRestaurant(Customer current){
        boolean find = false;
        int resIndex=0;
        String selection = "0";
        System.out.println("\n\n*****************");
        System.out.println("*Restaurant List*");
        System.out.println("*****************");
        System.out.println("Please select the below option");
        for(int i=1 ; i<=restaurant.getTotalEntries() ; i++){
            System.out.println(i+". "+restaurant.get(i).getRestaurantName());
        }
        System.out.print("Please Enter the Restaurant Name (Example:KFC) B to Back: ");
        selection = s.nextLine();
        if(selection.equals("B")){
            CustomerMenu(current);
        }
        else{
        for(int i=1 ; i<=restaurant.getTotalEntries()&&find==false ; i++){
            if(selection.equals(restaurant.get(i).getRestaurantName())){
                find = true;
                resIndex = i;
                for(int j=1 ; j<=food.getTotalEntries() ; j++){
                    if(food.get(j).getRestaurant().getRestaurantName().equals(selection)&&food.get(j).getFoodAvailability()=='A'){
                        CurrentFood.add(food.get(j));
                    }
                }
                makeOrder(customer.get(1),resIndex);
            }
        }
        if(find==false){
            System.out.println();
            System.out.println("Please Enter Again");
            SelectRestaurant(current);
        }
      }
    }
    
    public void makeOrder(Customer current, int resIndex){
        boolean checkout=false, ordered=false;
        String selection = "0",foodid = "0", nextID = "0";
        int quantity;
        nextID = getCurrentID();
        Calendar cal = Calendar.getInstance();
        currentOrder.setOrdersID(nextID);
        currentOrder.setCustomer(current);
        currentOrder.setOrderStatus("1");
        currentOrder.setOrdersDateTime(cal);
        currentOrder.setRestaurant(restaurant.get(resIndex));
        currentOrder.setSubtotal(Subtotal);
        currentOrder.setTotal(Subtotal*1.06);
        System.out.println("\n\nBelow are the foods provided by "+restaurant.get(resIndex).getRestaurantName());
        System.out.println("---------------------------------------------------");
        for(int k=1 ; k<=CurrentFood.getTotalEntries() ; k++){
            System.out.println("Food ID->"+CurrentFood.get(k).getFoodID());
            System.out.println("Food Name->"+CurrentFood.get(k).getFoodName());
            System.out.printf("Food Price-> RM%.2f\n",CurrentFood.get(k).getFoodPrice());
        }
        System.out.println("---------------------------------------------------");
        while(!foodid.equals("C")&&!foodid.equals("B")&&!foodid.equals("V")&&checkout==false){
            System.out.println("Please Enter the Food ID that You Want");
            System.out.println("(Press C to confirm, B to back and cancel, V to view cart):");
            foodid = s.nextLine();
            foodid = foodid.toUpperCase();
            
            if(foodid.equals("C")){
                checkout = Confirmation(current);
                if(checkout==false){
                    makeOrder(current,resIndex);
                }
            }
            else if(foodid.equals("B")){
                currentOrder = new Orders();
                currentDetail.clear();
                Subtotal = 0.00;
                CurrentFood.clear();
                CustomerMenu(current);
                break;
            }
            else if(foodid.equals("V")){
                boolean again = ViewCart(current);
                if(again){
                    makeOrder(current,resIndex);
                    break;
                }
                else{
                    SelectRestaurant(current);
                    break;
                }
            }
            else{
                //check whether food id is exist or not
                boolean foodcheck = false;
                for(int i=1; i<=CurrentFood.getTotalEntries()&&foodcheck==false; i++){
                    if(foodid.equals(CurrentFood.get(i).getFoodID())){
                        foodcheck = true;
                        do{
                            System.out.println("Please Enter the Quantity:");
                            while(!s.hasNextInt()){
                                System.out.println("Please Enter the Quantity in Integer:");
                                s.next();
                            }
                            quantity = s.nextInt();
                            s.nextLine();
                        }while(quantity<1);
                        double currentSubtotal = CurrentFood.get(i).getFoodPrice()*quantity;
                        currentDetail.addDetail(new OrderDetail(currentOrder,CurrentFood.get(i),quantity,currentSubtotal));
                        Subtotal+=currentSubtotal;
                        currentOrder.setSubtotal(Subtotal);
                    }
                }
                if(foodcheck==false){
                    System.out.println("Please Enter The Correct Food ID");
                }
                //end of checking
            }
        }
    }
    
    public boolean ViewCart(Customer current){
        String selection = "";
        boolean again = true;
        if(currentDetail.isEmpty()){
            System.out.println("\n\nYou Do Not Order Any Food Yet.");
            System.out.println("Press Enter To Back.");
            s.nextLine();
        }
        else{
            System.out.println("\n\nBelow Are The Foods You Have Ordered Inside Your Cart");
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-   Food ID   -        Food Name        -  Quantity  -  Subtotal(RM)  -");
            System.out.println("-----------------------------------------------------------------------");
            for(int i=1 ; i<=currentDetail.getTotalEntries(); i++){
                System.out.printf("\n-  %9s  -",currentDetail.get(i).getFood().getFoodID());
                System.out.printf(" %22s  -",currentDetail.get(i).getFood().getFoodName());
                System.out.printf(" %9d  -",currentDetail.get(i).getQuantity());
                System.out.printf(" %13.2f  -",currentDetail.get(i).getFoodTotal());
            }
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.println("\nPlease Select Your Selection");
            System.out.println("1. Edit Food");
            System.out.println("2. Delete Food");
            System.out.println("3. Cancel Order");
            System.out.println("4. Back");
            while(!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4")){
                System.out.print("Selection: ");
                selection = s.nextLine();
                switch(selection){
                    case "1":{
                        editFood();
                        break;
                    }
                    case "2":{
                        deleteFood();
                        break;
                    }
                    case "3":{
                        currentOrder = new Orders();
                        currentDetail.clear();
                        Subtotal = 0.00;
                        CurrentFood.clear();
                        System.out.println("\nCancel Order Successful\n\n");
                        again = false;
                        break;
                    }
                    case "4":{
                        break;
                    }
                    default:{
                        System.out.println("Please Enter Again");
                        break;
                    }
                }
            }
        }
        return again;
    }
    
    public void editFood(){
        String foodid = "",selection = "";
        int newquantity;
        do{
            System.out.println("\n\nBelow Are The Foods You Have Ordered Inside Your Cart");
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-   Food ID   -        Food Name        -  Quantity  -  Subtotal(RM)  -");
            System.out.println("-----------------------------------------------------------------------");
            for(int i=1 ; i<=currentDetail.getTotalEntries(); i++){
                System.out.printf("\n-  %9s  -",currentDetail.get(i).getFood().getFoodID());
                System.out.printf(" %22s  -",currentDetail.get(i).getFood().getFoodName());
                System.out.printf(" %9d  -",currentDetail.get(i).getQuantity());
                System.out.printf(" %13.2f  -",currentDetail.get(i).getFoodTotal());
            }
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.print("Please enter the food id that you want to edit(0 to back):");
            foodid = s.nextLine();
            if(foodid.equals("0")){
                selection = "YES";
                return ;
            }
            for(int i=1 ; i<=currentDetail.getTotalEntries() ; i++){
                if(currentDetail.get(i).getFood().getFoodID().toUpperCase().equals(foodid.toUpperCase())){
                    do{
                        System.out.println("Please Enter the Quantity:");
                        while(!s.hasNextInt()){
                            System.out.println("Please Enter the Quantity in Integer:");
                            s.next();
                        }
                        newquantity = s.nextInt();
                        s.nextLine();
                    }while(newquantity<1);
                    Subtotal = currentDetail.editCart(newquantity,i,currentOrder.getSubtotal());
                    currentOrder.setSubtotal(Subtotal);
                }
            }
            System.out.print("Do You Want To Continue(Yes to continue, others to back)?");
            selection = s.nextLine();
            selection = selection.toUpperCase();
        }while(selection.equals("YES"));
    }
    
    public void deleteFood(){
        String foodid = "",selection = "";
        do{
            System.out.println("\n\nBelow Are The Foods You Have Ordered Inside Your Cart");
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("-   Food ID   -        Food Name        -  Quantity  -  Subtotal(RM)  -");
            System.out.println("-----------------------------------------------------------------------");
            for(int i=1 ; i<=currentDetail.getTotalEntries(); i++){
                System.out.printf("\n-  %9s  -",currentDetail.get(i).getFood().getFoodID());
                System.out.printf(" %22s  -",currentDetail.get(i).getFood().getFoodName());
                System.out.printf(" %9d  -",currentDetail.get(i).getQuantity());
                System.out.printf(" %13.2f  -",currentDetail.get(i).getFoodTotal());
            }
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.print("Please enter the food id that you want to remove(0 to back):");
            foodid = s.nextLine();
            if(foodid.equals("0")){
                selection = "YES";
                return;
            }
            for(int i=1 ; i<=currentDetail.getTotalEntries() ; i++){
                if(currentDetail.get(i).getFood().getFoodID().toUpperCase().equals(foodid.toUpperCase())){
                    Subtotal = currentDetail.deleteFood(i, currentOrder.getSubtotal());
                    currentOrder.setSubtotal(Subtotal);
                }
            }
            System.out.print("Do You Want To Continue(Yes to continue, others to back)?");
            selection = s.nextLine();
            selection = selection.toUpperCase();
        }while(selection.equals("YES"));
    }
    
    public boolean Confirmation(Customer current){
        String selection = "";
        double roundoff = 0.00, total = 0.00;
        System.out.println("\n\nBelow Are The Foods You Have Ordered");
        System.out.println("------------------------------------");
        for(int i=1 ; i<=currentDetail.getTotalEntries() ; i++){
            System.out.println("Food ID: "+currentDetail.get(i).getFood().getFoodID());
            System.out.println("Food Name: "+currentDetail.get(i).getFood().getFoodName());
            System.out.println("Quantity: "+currentDetail.get(i).getQuantity());
        }
        System.out.println("------------------------------------");
        System.out.printf("Subtotal: RM%.2f\n",currentOrder.getSubtotal());
        System.out.printf("GST: RM%.2f\n",(currentOrder.getSubtotal()*0.06));
        System.out.printf("Total: RM%.2f\n",(currentOrder.getSubtotal()*1.06));
        System.out.println("------------------------------------");
        System.out.println("\n\nAre You Sure Want To CheckOut And Make Payment?");
        System.out.println("1. Yes");
        System.out.println("2. Back To Food Selection");
        while(!selection.equals("1") && !selection.equals("2")){
        System.out.print("Selection: ");
        selection = s.nextLine();
        switch(selection){
            case "1":{
                    //getting the system date
                    Calendar cal = Calendar.getInstance();
                    currentOrder.setOrdersDateTime(cal);
                    currentOrder.setSubtotal(Subtotal);
                    currentOrder.setTotal(Subtotal*1.06);
                    currentOrder.setOrderStatus("1");//change to 1
                    order.add(currentOrder);
                    payment.add(new Payment(getPaymentCurrentID(),currentOrder,currentOrder.getTotal(),cal,"1","Online"));
                    for(int i=1 ; i<=currentDetail.getTotalEntries() ; i++){
                        orderdetail.add(currentDetail.get(i));
                        orderdetail.SortOrderDetail();
                    }
                    System.out.println("\nThank You For Your Order.");
                    System.out.println("You Have Order The Following Items.");
                    System.out.println("-----------------------------------");
                    for(int h=1;h<=currentDetail.getTotalEntries();h++){
                        System.out.println("Food Name: "+currentDetail.get(h).getFood().getFoodName());
                        System.out.println("Food ID: "+currentDetail.get(h).getFood().getFoodID());
                        System.out.println("Quantity: "+currentDetail.get(h).getQuantity());
                    }
                    System.out.printf("Subtotal: RM%.2f\n",currentOrder.getSubtotal());
                    System.out.printf("GST: RM%.2f\n",(currentOrder.getSubtotal()*0.06));
                    System.out.printf("Total: RM%.2f\n",currentOrder.getTotal());
                    /*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    for(int k=1 ; k<=order.getTotalEntries() ; k++){
                        System.out.println("---------------------------------");
                        System.out.println("Order Date Time->"+dateFormat.format(order.get(k).getOrdersDateTime().getTime()));
                        System.out.println("---------------------------------");
                        System.out.println("Restaurant Name->"+order.get(k).getRestaurant().getRestaurantName());
                        for(int j=1 ; j<=orderdetail.getTotalEntries(); j++){
                            System.out.println(orderdetail.get(j).getFoodTotal());
                        }
                        System.out.printf("Subtotal->RM%.2f\n",order.get(k).getSubtotal());
                        System.out.printf("Total->RM%.2f\n",order.get(k).getTotal());
                        System.out.println("-----------------------------");
                    }*/
                    currentOrder = new Orders();
                    currentDetail.clear();
                    Subtotal= 0.00;
                    CurrentFood.clear();
                    s.nextLine();
                    return true;
            }
            case "2":{
                return false;
            }
            default:{
                System.out.println("Please Enter Again");
            }
        }
        }
        return true;
    }
    
    public void GenerateOrderReport(){
        String reportday = "";
        System.out.print("Please Enter The Day (YYYY/MM/DD) : ");
        reportday = s.nextLine();
        order.GenerateDetailReport(reportday);
    }
    
    public void retrieveCustomer(){
        String contact = "";
        boolean check = false;
        System.out.println("\n\n***************************");
        System.out.println("*Retrieve Customer Details*");
        System.out.println("***************************");
        while(check==false){
            System.out.println("Please Enter The Contact Number :");
            while (s.hasNext("[A-Za-z]+")) {
                System.out.println("Please Enter The Contact Number in Correct Format.");
                s.nextLine();
            }
            contact = s.nextLine();
            for(int i=1 ; i<=customer.getTotalEntries(); i++){
                if(contact.equals(customer.get(i).getCustTelNo())){
                    System.out.println("\n\nPersonal Information");
                    System.out.println("---------------------------");
                    System.out.println("Name: "+customer.get(i).getCustName());
                    System.out.println("Address: "+customer.get(i).getCustAddress());
                    System.out.println("Area: "+customer.get(i).getCustArea());
                    System.out.println("IC Number: "+customer.get(i).getCustIC());
                    System.out.println("\n");
                    check=true;
                }
            }
            if(check==false){
                System.out.println("This Contact Number is Not Exist in Customer Database");
                s.nextLine();
            }
        };
    }
}
