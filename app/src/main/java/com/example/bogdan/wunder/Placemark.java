package com.example.bogdan.wunder;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Placemark extends RealmObject{

    @SerializedName("vin")
    @PrimaryKey
    private String vin;

    @SerializedName("address")
    private String address;

    @SerializedName("engineType")
    private String engineType;

    @SerializedName("exterior")
    private String exterior;

    @SerializedName("fuel")
    private int fuel;

    @SerializedName("interior")
    private String interior;

    @SerializedName("name")
    private String name;

    private double latitude;
    private double longitude;

    public Placemark(){

    }

    public Placemark (PlacemarkJSON parseEntity){

        setName(parseEntity.getName());
        setAddress(parseEntity.getAddress());
        setLatitude(parseEntity.getLatitude());
        setLongitude(parseEntity.getLongitude());
        setInterior(parseEntity.getInterior());
        setExterior(parseEntity.getExterior());
        setEngineType(parseEntity.getEngineType());
        setVin(parseEntity.getVin());
        setFuel(parseEntity.getFuel());

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
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

