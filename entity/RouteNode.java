package entity;

import java.util.ArrayList;
import java.util.List;

import enums.RouteNodeType;

public class RouteNode {
    
    private final Location location;

    private List<String> orders;

    private int timeToReach;

    private final RouteNodeType nodeType;

    public RouteNode(Location location, RouteNodeType nodeType) {
        this.location = location;
        this.orders = new ArrayList<>();
        this.nodeType = nodeType;
    }

    public RouteNodeType getNodeType() {
        return this.nodeType;
    }

    public List<String> getOrders() {
        return this.orders;
    }

    public Location getLocation() {
        return this.location;
    }

    public void updateTimeToReach(int updatedTime) {
        this.timeToReach = updatedTime;
    }

    public int getTimeToReachNode() {
        return this.timeToReach;
    }

    public RouteNode clone() {
        RouteNode node = new RouteNode(location, nodeType);
        node.orders = new ArrayList<>(orders);
        return node;
    }

    @Override
    public String toString() {
        return "Node Latitude : " + location.getLat() + ", Longitude : " + location.getLong() + ", Type : " + nodeType.name() + ", Time : " + timeToReach;
    }
}
