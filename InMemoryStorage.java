import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Customer;
import entity.DeliveryPartner;
import entity.Order;
import entity.Restaurant;

public class InMemoryStorage implements StorageInterface {

    private Map<String, Customer> customerMap;

    private Map<String, DeliveryPartner> deliveryPartnerMap;

    private Map<String, Restaurant> restaurentMap;

    private Map<String, Order> orderMap;

    public InMemoryStorage() {
        customerMap = new HashMap<>();
        deliveryPartnerMap = new HashMap<>();
        orderMap = new HashMap<>();
        restaurentMap = new HashMap<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        customerMap.put(customer.getUserId(), customer);
    }

    @Override
    public void addDeliveryPartner(DeliveryPartner deliveryPartner) {
        deliveryPartnerMap.put(deliveryPartner.getUserId(), deliveryPartner);
    }

    @Override
    public List<DeliveryPartner> findAllDeliveryPartners() {
        return new ArrayList<>(deliveryPartnerMap.values());
    }

    @Override
    public void addRestaurent(Restaurant restaurant) {
        restaurentMap.put(restaurant.getName(), restaurant);
    }

    @Override
    public Customer getCustomer(String customerId) {
        return customerMap.get(customerId);
    }

    @Override
    public Restaurant getRestaurent(String restaurentName) {
        return restaurentMap.get(restaurentName);
    }

    @Override
    public void addOrder(Order order) {
        orderMap.put(order.getOrderId(), order);
    }

    @Override
    public Order getOrder(String orderId) {
        return orderMap.get(orderId);
    }

    @Override
    public DeliveryPartner getDeliveryPartner(String partnerId) {
        return deliveryPartnerMap.get(partnerId);
    }
    
}
