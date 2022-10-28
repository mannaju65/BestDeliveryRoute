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

public class BruteForceRouteSelectionStrategy implements RouteSelectionStrategy {

    private GeoDistanceCalculator geoDistanceCalculator;

    public BruteForceRouteSelectionStrategy(GeoDistanceCalculator calculator) {
        this.geoDistanceCalculator = calculator;
    }

    /** Find the order of nodes to visit to complete batch
     * 
     * @param orders
     * @param deliveryPartner
     */
    @Override
    public List<RouteNode> getRouteOfDelivery(List<Order> orders, DeliveryPartner deliveryPartner) {
        Map<String, Order> orderMap = new HashMap<>();
        Map<String, RouteNode> restaurentMap = new HashMap<>();
        FoodDeliveryUtil.getRestaurentAndOrderMap(orders, restaurentMap, orderMap);
        List<List<RouteNode>> allPaths = new ArrayList<>();
        List<RouteNode> nodesToVisit = new LinkedList<>(restaurentMap.values());
        findAllPathCombination(allPaths, new LinkedList<>(), nodesToVisit, deliveryPartner, orderMap);
        List<RouteNode> shortestPath = new ArrayList<>();
        for(List<RouteNode> path : allPaths) {
            if(path != null && path.size() > 0) {
                if(shortestPath.size() == 0) {
                    shortestPath = path;
                } else {
                    if(path.get(path.size()-1).getTimeToReachNode() < shortestPath.get(shortestPath.size()-1).getTimeToReachNode()) {
                        shortestPath = path;
                    }
                }
            }
        }
        for(RouteNode node : shortestPath) {
            FoodDeliveryUtil.updateDeliveryDetails(node, orderMap, deliveryPartner);
        }
        return shortestPath;
    }

    private void findAllPathCombination(List<List<RouteNode>> allPaths, List<RouteNode> currentPath,
     List<RouteNode> nodesToVisit, DeliveryPartner deliveryPartner, Map<String, Order> orderMap) {
        if(nodesToVisit == null || nodesToVisit.size() == 0) {
            allPaths.add(currentPath);
            return;
        }
        RouteNode currentNode = (currentPath == null || currentPath.size()==0) ? getStartingNode(deliveryPartner) : currentPath.get(currentPath.size()-1);
        for(int i = 0; i< nodesToVisit.size();i++) {
            int timeToReach = FoodDeliveryUtil.calculateTime(nodesToVisit.get(i), currentNode, deliveryPartner.getSpeed(), orderMap, geoDistanceCalculator);
            RouteNode clonedNode = nodesToVisit.get(i).clone();
            clonedNode.updateTimeToReach(timeToReach);
            List<RouteNode> path = new LinkedList<>(currentPath);
            path.add(clonedNode);
            List<RouteNode> nextVisitedNode = new LinkedList<>(nodesToVisit);
            nextVisitedNode.remove(i);
            FoodDeliveryUtil.addDropLocations(nextVisitedNode, clonedNode, orderMap);
            findAllPathCombination(allPaths, path, nextVisitedNode, deliveryPartner, orderMap);
        } 
    }

    private RouteNode getStartingNode(DeliveryPartner deliveryPartner) {
        RouteNode currentNode = new RouteNode(deliveryPartner.getLocation(), null);
        currentNode.updateTimeToReach(0);
        return currentNode;
    }
    
}
