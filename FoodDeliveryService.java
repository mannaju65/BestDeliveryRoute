import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Customer;
import entity.DeliveryPartner;
import entity.FoodItem;
import entity.Order;
import entity.Restaurant;
import entity.RouteNode;
import util.HaversineDistance;

public class FoodDeliveryService {
    
    private StorageInterface storage;

    private OrderDeliveryManagementService orderDeliveryManagementService;

    public FoodDeliveryService(StorageInterface storageInterface) {
        this.storage = storageInterface;
        this.orderDeliveryManagementService = 
        new OrderDeliveryManagementService(storage, new BruteForceRouteSelectionStrategy(HaversineDistance.getInstance()));
    }

    public void addCustomer(Customer customer) {
        storage.addCustomer(customer);
    }

    public void addDeliveryPartner(DeliveryPartner deliveryPartner) {
        storage.addDeliveryPartner(deliveryPartner);
    }

    public void addRestaurent(Restaurant restaurant) {
        storage.addRestaurent(restaurant);
    }

    public Order getOrderDetails(String orderId) {
        return storage.getOrder(orderId);
    }

    public void createOrder(String orderId, String customerId, String restaurent, List<FoodItem> food) {
        Customer customer = storage.getCustomer(customerId);
        Restaurant restaurant = storage.getRestaurent(restaurent);
        Order order = new Order(orderId, customer, food, restaurant, new Random().nextInt(60));
        storage.addOrder(order);
    }

    public DeliveryPartner getDeliveryPartnerForOrder(String orderId) {
        Order order = storage.getOrder(orderId);
        DeliveryPartner partner =  orderDeliveryManagementService.findDeliveryPartner(order);
        partner.acceptOrder(orderId);
        return partner;
    }

    public List<RouteNode> getShortestPathForDelivery(String deliveryPartnerId) {
        DeliveryPartner deliveryPartner = storage.getDeliveryPartner(deliveryPartnerId);
        List<Order> currentOrders = new ArrayList<>();
        for(String orderId : deliveryPartner.getCurrentlyDeliveringOrders()) {
            currentOrders.add(storage.getOrder(orderId));
        }
        return orderDeliveryManagementService.assignDeliveryPartnerAndFindRoute(currentOrders, deliveryPartner);
    }
}
