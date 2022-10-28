package util;

import java.util.List;
import java.util.Map;

import entity.DeliveryDetails;
import entity.DeliveryPartner;
import entity.Order;
import entity.RouteNode;
import enums.RouteNodeType;

public class FoodDeliveryUtil {
    
    public static void getRestaurentAndOrderMap(List<Order> orders, Map<String, RouteNode> restaurentMap, Map<String, Order> orderMap) {
        for(Order order : orders) {
            orderMap.put(order.getOrderId(), order);
            if(!restaurentMap.containsKey(order.getRestaurent().getName())) {
                restaurentMap.put(order.getRestaurent().getName(), new RouteNode(order.getRestaurent().getLocation(), RouteNodeType.PICK_UP));
            }
            restaurentMap.get(order.getRestaurent().getName()).getOrders().add(order.getOrderId());
        }
    }

    /** Calculate time to reach destination in minutes
     * 
     * @param destination
     * @param source
     * @param speed
     * @param orderMap
     * @return
     */
    public static int calculateTime(RouteNode destination, RouteNode source, double speed, Map<String, Order> orderMap ,GeoDistanceCalculator geoDistanceCalculator) {
        double distance = geoDistanceCalculator.getDistance(source.getLocation(), destination.getLocation());
        int timeToCoverDistance = (int)((60)*distance/speed);
        timeToCoverDistance += source.getTimeToReachNode();
        if(destination.getNodeType().equals(RouteNodeType.PICK_UP)) {
            int maxPreparationTime = 0;
            for(String orderId : destination.getOrders()) {
                maxPreparationTime = Math.max(maxPreparationTime, orderMap.get(orderId).getPreparationTime());
            }
            if(maxPreparationTime > timeToCoverDistance) {
                timeToCoverDistance = maxPreparationTime;
            }
        }
        return timeToCoverDistance;
    }

    public static void addDropLocations(List<RouteNode> nodesToVisit, RouteNode currentNode, Map<String, Order> orderMap) {
        if(!currentNode.getNodeType().equals(RouteNodeType.PICK_UP)) return;
        if(currentNode.getOrders() == null || currentNode.getOrders().size() == 0) return;
        for(String orderId : currentNode.getOrders()) {
            Order order = orderMap.get(orderId);
            nodesToVisit.add(new RouteNode(order.getCustomer().getLocation(), RouteNodeType.DROP));
        }
    }

    public static void updateDeliveryDetails(RouteNode currentNode, Map<String, Order> orderMap, DeliveryPartner deliveryPartner) {
        if(!currentNode.getNodeType().equals(RouteNodeType.DROP)) return;
        if(currentNode.getOrders() == null || currentNode.getOrders().size() == 0) return;
        for(String orderId : currentNode.getOrders()) {
            Order order = orderMap.get(orderId);
            order.setDeliveryDetails(new DeliveryDetails(deliveryPartner, currentNode.getTimeToReachNode()));
        }
    }
}
