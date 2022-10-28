import java.util.ArrayList;
import java.util.List;

import entity.DeliveryPartner;
import entity.Order;
import entity.RouteNode;

public class OrderDeliveryManagementService {

    private StorageInterface storage;

    private RouteSelectionStrategy routeSelectionStrategy;

    public OrderDeliveryManagementService(StorageInterface storageInterface, RouteSelectionStrategy routeSelectionStrategy) {
        this.routeSelectionStrategy = routeSelectionStrategy;
        this.storage = storageInterface;
    }

    public void setRouteSelectionStraegy(RouteSelectionStrategy routeSelectionStrategy) {
        this.routeSelectionStrategy = routeSelectionStrategy;
    }

    /* There should be ideally a strategy to find deliveryPartner
     * 
     */
    public DeliveryPartner findDeliveryPartner(Order order) {
        List<DeliveryPartner> allPartners =  storage.findAllDeliveryPartners();
        return (allPartners.size() == 0) ? null : allPartners.get(0);
    }
    

    /** Use strategy pattern to find best route
     * 
     * @param orders
     * @param deliveryPartner
     * @return
     */
    public List<RouteNode> assignDeliveryPartnerAndFindRoute(List<Order> orders, DeliveryPartner deliveryPartner) {
        if(orders == null || orders.size() == 0) return new ArrayList<>();
        return routeSelectionStrategy.getRouteOfDelivery(orders, deliveryPartner);
    }
}
