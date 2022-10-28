import java.util.ArrayList;
import java.util.List;

import entity.Customer;
import entity.DeliveryPartner;
import entity.FoodItem;
import entity.Location;
import entity.Restaurant;
import entity.RouteNode;

public class Driver {

    public static void main(String[] args) {
        StorageInterface storageInterface = new InMemoryStorage();
        FoodDeliveryService foodDeliveryService = new FoodDeliveryService(storageInterface);

        Customer customer1 = new Customer("1");
        customer1.setLocation(new Location(51.550, 0.157));
        Customer customer2 = new Customer("2");
        customer2.setLocation(new Location(51.4787, 0.216));
        foodDeliveryService.addCustomer(customer1);
        foodDeliveryService.addCustomer(customer2);

        DeliveryPartner deliveryPartner1 = new DeliveryPartner("1", 20);
        deliveryPartner1.setLocation(new Location(51.450, 0.134));
        foodDeliveryService.addDeliveryPartner(deliveryPartner1);

        foodDeliveryService.addRestaurent(new Restaurant(new Location(51.5007, 0.1246), "oudh"));
        foodDeliveryService.addRestaurent(new Restaurant(new Location(51.590, 0.117), "lazzez"));

        List<FoodItem> foodItem = new ArrayList<>();
        foodItem.add(new FoodItem("rice", 100, 1));
        foodItem.add(new FoodItem("roti", 10, 4));
        foodDeliveryService.createOrder("1", "1", "oudh", foodItem);
        foodDeliveryService.createOrder("2", "2", "lazzez", foodItem);

        foodDeliveryService.getDeliveryPartnerForOrder("1");
        foodDeliveryService.getDeliveryPartnerForOrder("2");

        List<RouteNode> pathNodes = foodDeliveryService.getShortestPathForDelivery("1");
        pathNodes.forEach(node -> System.out.println(node.toString()));
        // System.out.println(foodDeliveryService.getOrderDetails("1").getPreparationTime());
        // System.out.println(foodDeliveryService.getOrderDetails("2").getPreparationTime());

    }
}