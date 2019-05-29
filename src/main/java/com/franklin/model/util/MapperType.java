package com.franklin.model.util;

public enum MapperType {
    Appointment("AppointmentRequest"),
    HairCut("HairCutRequest"),
    OwnerRequest("OwnerRequest"),
    PurchaseRequest("PurchaseRequest");

    private final String name;
    private MapperType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}

