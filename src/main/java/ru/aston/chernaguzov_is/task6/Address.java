package ru.aston.chernaguzov_is.task6;

public class Address {

    private City city;
    private String street;
    private String house;
    private int distanceToTheCapital;

    public Address() {
    }

    public Address(City city, String street, String house, int distanceToTheCapital) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.distanceToTheCapital = distanceToTheCapital;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getDistanceToTheCapital() {
        return distanceToTheCapital;
    }

    public void setDistanceToTheCapital(int distanceToTheCapital) {
        this.distanceToTheCapital = distanceToTheCapital;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city=" + city +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", distanceToTheCapital=" + distanceToTheCapital +
                '}';
    }
}
