package entity;

import enums.UserType;

public class Customer extends Person {

    public Customer(String id) {
        super(id, UserType.CUSTOMER);
    }
    
}
