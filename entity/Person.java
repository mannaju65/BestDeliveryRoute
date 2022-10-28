package entity;

import enums.UserType;

public abstract class Person {
    
    private final String userId;

    private final UserType userType;

    private Location location;

    protected Person(String id, UserType userType) {
        this.userId = id;
        this.userType = userType;
    }

    public void setLocation(Location current) {
        this.location = current;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getUserId() {
        return this.userId;
    }

    public UserType getUserType() {
        return this.userType;
    }
}
