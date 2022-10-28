package util;

import entity.Location;

public interface GeoDistanceCalculator {
    
    double getDistance(Location first, Location second);
}
