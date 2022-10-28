import java.util.List;

import entity.Customer;
import entity.DeliveryPartner;
import entity.Order;
import entity.Restaurant;

public interface StorageInterface {
    
    public void addCustomer(Customer customer);

    public void addDeliveryPartner(DeliveryPartner deliveryPartner);

    public void addRestaurent(Restaurant restaurant);

    public void addOrder(Order order);

    public Order getOrder(String orderId);

    public Customer getCustomer(String customerId);

    public DeliveryPartner getDeliveryPartner(String partnerId);

    public Restaurant getRestaurent(String restaurentName);

    public List<DeliveryPartner> findAllDeliveryPartners();
}
