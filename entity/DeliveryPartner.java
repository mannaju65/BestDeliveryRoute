package entity;

import java.util.ArrayList;
import java.util.List;

import enums.UserType;

public class DeliveryPartner extends Person {

    private double speed;

    private List<String> orderIds;

    public DeliveryPartner(String id, double speed) {
        super(id, UserType.DELIVERY_PARTNER);
        this.speed = speed;
        orderIds = new ArrayList<>();
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    public void acceptOrder(String order) {
        this.orderIds.add(order);
    }

    public List<String> getCurrentlyDeliveringOrders() {
        return orderIds;
    }

    public void completeDelivery(String order) {
        this.orderIds.remove(order);
    }
    
}
