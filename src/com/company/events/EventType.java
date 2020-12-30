package com.company.events;

public enum EventType {
    NEW_CUSTOMER,
    PRICE_FALL,
    ADMISSION,
    DELETE_EXP,
    MOVE_TO_SHOP,
    SERVE_CUSTOMER;

    @Override
    public String toString() {
        return this.name();
    }
}