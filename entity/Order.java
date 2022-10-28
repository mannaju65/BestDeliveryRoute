package entity;

import java.util.List;

import enums.OrderStatus;

public class Order {

    private final String orderId;
    
    private final Customer customer;

    private final List<FoodItem> items;

    private final Restaurant restaurant;

    private OrderStatus status;

    private DeliveryDetails deliveryDetails;

    private int preparationTime;

    public Order(String orderid, Customer customer, List<FoodItem> food, Restaurant restaurant, int preparationTime) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderId = orderid;
        this.items = food;
        this.status = OrderStatus.PLACED;
        this.preparationTime = preparationTime;
    }

    public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public DeliveryDetails getDeliveryDetails() {
        return this.deliveryDetails;
    }

    public OrderStatus getOrderStatus() {
        return this.status;
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Restaurant getRestaurent() {
        return this.restaurant;
    }

    public List<FoodItem> getFoodList() {
        return this.items;
    }

    public int getPreparationTime() {
        return this.preparationTime;
    }

    public void updatePreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

}
