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
import java.text.SimpleDateFormat;
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
    private ModuleCInterface<Restaurant> restaurant = new ModuleCList<Restaurant>();
    private ModuleCInterface<Food> food = new ModuleCList<Food>();
    private ModuleCInterface<Customer> customer = new ModuleCList<Customer>();
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
        customer.add(new Customer("CU000001", "Miw Jin Le", "14,Taman Cantik,53300,Wangsa Maju,Kuala Lumpur", "Wangsa Maju", "0167897899", "970104079999", "1234567890"));
        SelectRestaurant();
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
    
    public void SelectRestaurant(){
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
        /*if(selection.equals("B")){
            CustomerMenu(current);
        }
        else{*/
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
            SelectRestaurant();
        }
      /*}*/
    }
    
    public void makeOrder(Customer current, int resIndex){
        boolean checkout=false, ordered=false;
        String selection = "0",foodid = "0", nextID = "0";
        int quantity;
        nextID = getCurrentID();
        Calendar cal = Calendar.getInstance();
        currentOrder.setCustomer(current);
        currentOrder.setOrderStatus("Pending");
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
            /*else if(foodid.equals("B")){
                currentOrder = new Orders();
                currentDetail.clear();
                Subtotal = 0.00;
                CurrentFood.clear();
                CustomerMenu(current);
                break;
            }*/
            else if(foodid.equals("V")){
                boolean again = ViewCart(current);
                if(again){
                    makeOrder(current,resIndex);
                    break;
                }
                else{
                    SelectRestaurant();
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
                        for(int q=1 ; q<=currentDetail.getTotalEntries() ; q++){
                            if(currentDetail.get(q).getFood().getFoodID().equals(foodid)){
                                int currentqty = currentDetail.get(q).getQuantity();
                                quantity = quantity + currentqty;
                                currentDetail.get(q).setQuantity(quantity);
                                currentSubtotal = CurrentFood.get(i).getFoodPrice()*(quantity-currentqty);
                                currentDetail.get(q).setFoodTotal(CurrentFood.get(i).getFoodPrice()*(quantity));
                                currentDetail.SortOrderDetail();
                                ordered=true;
                            }
                        }

                        if(ordered == false){
                        currentDetail.addDetail(new OrderDetail(currentOrder,CurrentFood.get(i),quantity,currentSubtotal));
                        }

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
            System.out.println("------------------------------------------------------");
            System.out.println("-   Food ID   -        Food Name        -  Quantity  -");
            System.out.println("------------------------------------------------------");
            for(int i=1 ; i<=currentDetail.getTotalEntries(); i++){
                System.out.printf("\n-  %9s  -",currentDetail.get(i).getFood().getFoodID());
                System.out.printf(" %22s  -",currentDetail.get(i).getFood().getFoodName());
                System.out.printf(" %9d  -",currentDetail.get(i).getQuantity());
            }
            System.out.println("\n------------------------------------------------------");
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
                        System.out.println("Edit!");
                        break;
                    }
                    case "2":{
                        System.out.println("Delete");
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
        System.out.println("\n\nAre You Sure Want To CheckOut?");
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
                    for(int i=1 ; i<=currentDetail.getTotalEntries() ; i++){
                        orderdetail.addDetail(currentDetail.get(i));
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
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
                    }
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
