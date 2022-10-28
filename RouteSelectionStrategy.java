import java.util.List;

import entity.DeliveryPartner;
import entity.Order;
import entity.RouteNode;

public interface RouteSelectionStrategy {

    List<RouteNode> getRouteOfDelivery(List<Order> orders, DeliveryPartner deliveryPartner);
    
}
