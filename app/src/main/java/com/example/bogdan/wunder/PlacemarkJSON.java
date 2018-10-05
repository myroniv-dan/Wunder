package com.example.bogdan.wunder;

public class PlacemarkJSON {

    private String address;

    private double[] coordinates;

    private String engineType;

    private String exterior;

    private int fuel;

    private String interior;

    private String name;

    private String vin;

    public PlacemarkJSON(String address, double[] coordinates, String engineType, String exterior, int fuel, String interior, String name, String vin) {
        this.address = address;
        this.coordinates = coordinates;
        this.engineType = engineType;
        this.exterior = exterior;
        this.fuel = fuel;
        this.interior = interior;
        this.name = name;
        this.vin = vin;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return coordinates[0];
    }

    public double getLongitude() {
        return coordinates[1];
    }

    public String getEngineType() {
        return engineType;
    }

    public String getExterior() {
        return exterior;
    }

    public int getFuel() {
        return fuel;
    }

    public String getInterior() {
        return interior;
    }

    public String getName() {
        return name;
    }

    public String getVin() {
        return vin;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

}

