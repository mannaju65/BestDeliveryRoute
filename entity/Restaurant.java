package entity;

public class Restaurant {

    private final String name;
    
    private final Location location;

    public Restaurant(Location location, String restaurantName) {
        this.location = location;
        this.name = restaurantName;
    }

    public String getName() {
        return this.name;
    }

    public Location getLocation() {
        return this.location;
    }
}
