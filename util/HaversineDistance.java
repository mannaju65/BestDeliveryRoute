package util;

import entity.Location;

public class HaversineDistance implements GeoDistanceCalculator {

    private static HaversineDistance haversineDistance;

    private static final double RADIUS_EARTH = 6371;

    private HaversineDistance() {
    }

    public static HaversineDistance getInstance() {
        HaversineDistance tmpHaversineDistance = haversineDistance;
        if(tmpHaversineDistance != null) {
            return tmpHaversineDistance;
        } 
        synchronized(HaversineDistance.class) {
            if(haversineDistance == null) {
                haversineDistance = new HaversineDistance();
            }
            return haversineDistance;
        }
    }

    /* returns haversine distance in K.M
     * 
     */
    @Override
    public double getDistance(Location first, Location second) {
    
        double diffLat = Math.toRadians(second.getLat() - first.getLat());
        double diffLon = Math.toRadians(second.getLong() - first.getLong());
 
        double latFirst = Math.toRadians(first.getLat());
        double latSecond = Math.toRadians(second.getLat());
 
        double a = Math.pow(Math.sin(diffLat / 2), 2) +
                   Math.pow(Math.sin(diffLon / 2), 2) *
                   Math.cos(latFirst) *
                   Math.cos(latSecond);
        double c = 2 * Math.asin(Math.sqrt(a));
        return RADIUS_EARTH * c;
    }
    
}
