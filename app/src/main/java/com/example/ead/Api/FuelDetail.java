package com.example.ead.Api;

public class FuelDetail {
    String  fuel_type,quantity;

    public FuelDetail(String fuel_type, String quantity) {
        this.fuel_type = fuel_type;
        this.quantity = quantity;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}


