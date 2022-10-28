package entity;

public class DeliveryDetails {

    private DeliveryPartner driver;

    private int expectedTimeToDeliver;

    public DeliveryDetails(DeliveryPartner driver, int expectedTime) {
        this.driver = driver;
        this.expectedTimeToDeliver = expectedTime;
    }

    public DeliveryPartner getDeliveryPartner() {
        return this.driver;
    }

    public int getDeliveryTime() {
        return this.expectedTimeToDeliver;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.expectedTimeToDeliver = deliveryTime;
    }
}
