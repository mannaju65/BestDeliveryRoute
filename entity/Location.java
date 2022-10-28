package entity;

public class Location {
    
    private final double latitude;

    private final double longitude;

    public Location(double x, double y) {
        this.latitude = x;
        this.longitude = y;
    }

    public double getLat() {
        return this.latitude;
    }

    public double getLong() {
        return this.longitude;
    }

}
