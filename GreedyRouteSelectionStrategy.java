import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import entity.DeliveryPartner;
import entity.Order;
import entity.RouteNode;
import util.FoodDeliveryUtil;
import util.GeoDistanceCalculator;

public class GreedyRouteSelectionStrategy implements RouteSelectionStrategy {

    private GeoDistanceCalculator geoDistanceCalculator;

    public GreedyRouteSelectionStrategy(GeoDistanceCalculator calculator) {
        this.geoDistanceCalculator = calculator;
    }


    /** Find the order of nodes to visit to complete batch
     * 
     * @param orders
     * @param deliveryPartner
     */
    @Override
    public List<RouteNode> getRouteOfDelivery(List<Order> orders,  DeliveryPartner deliveryPartner) {
        Map<String, Order> orderMap = new HashMap<>();
        Map<String, RouteNode> restaurentMap = new HashMap<>();
        FoodDeliveryUtil.getRestaurentAndOrderMap(orders, restaurentMap, orderMap);
        List<RouteNode> shortestPath = new ArrayList<>();
        List<RouteNode> nodesToVisit = new LinkedList<>(restaurentMap.values());
        RouteNode currentNode = new RouteNode(deliveryPartner.getLocation(), null);
        currentNode.updateTimeToReach(0);
        while(!nodesToVisit.isEmpty()) {
            int minTimeToReachNext = -1;
            int nextNodeIndex = -1;
            for(int i = 0; i< nodesToVisit.size();i++) {
                int timeToReach = FoodDeliveryUtil.calculateTime(nodesToVisit.get(i), currentNode, deliveryPartner.getSpeed(), orderMap, geoDistanceCalculator);
                if(nextNodeIndex == -1) {
                    nextNodeIndex = i;
                    minTimeToReachNext = timeToReach;
                } else if(timeToReach < minTimeToReachNext) {
                    minTimeToReachNext = timeToReach;
                    nextNodeIndex = i;
                }
            }
            RouteNode nextNode =  nodesToVisit.remove(nextNodeIndex);
            nextNode.updateTimeToReach(minTimeToReachNext);
            shortestPath.add(nextNode);
            FoodDeliveryUtil.addDropLocations(nodesToVisit, nextNode, orderMap);
            FoodDeliveryUtil.updateDeliveryDetails(nextNode, orderMap, deliveryPartner);
            currentNode = nextNode;
        }
        return shortestPath;
    }


    
}
