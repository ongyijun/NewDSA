/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author ong
 */
public class OrderDetail {
    private Orders orders;
    private Food food;
    private int Quantity;
    private double foodTotal;

    public OrderDetail(Orders orders, Food food, int Quantity, double foodTotal) {
        this.orders = orders;
        this.food = food;
        this.Quantity = Quantity;
        this.foodTotal = foodTotal;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getFoodTotal() {
        return foodTotal;
    }

    public void setFoodTotal(double foodTotal) {
        this.foodTotal = foodTotal;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orders=" + orders + ", food=" + food + ", Quantity=" + Quantity + '}';
    }
    
}
