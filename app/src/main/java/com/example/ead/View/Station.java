package com.example.ead.View;

public class Station {
    private String owner_id;
    private String register_no;
    private String fuel_station_name;
    private String station_id;

    public Station() {}

    public Station(String owner_id, String register_no, String fuel_station_name, String station_id) {
        this.owner_id = owner_id;
        this.register_no = register_no;
        this.fuel_station_name = fuel_station_name;
        this.station_id = station_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getRegister_no() {
        return register_no;
    }

    public void setRegister_no(String register_no) {
        this.register_no = register_no;
    }

    public String getFuel_station_name() {
        return fuel_station_name;
    }

    public void setFuel_station_name(String fuel_station_name) {
        this.fuel_station_name = fuel_station_name;
    }

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }
}
