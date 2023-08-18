package com.example.oneToOneTest.dto;

import com.example.oneToOneTest.entities.Address;

public class AddressDTO {
    private Long id;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String number;
    private String zipCode;

    public AddressDTO(Long id, String street, String neighborhood, String city, String state, String number, String zipCode) {
        this.id = id;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.number = number;
        this.zipCode = zipCode;
    }

    public AddressDTO(Address entity) {
        this.id = entity.getId();
        this.street = entity.getStreet();
        this.neighborhood = entity.getNeighborhood();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.number = entity.getNumber();
        this.zipCode = entity.getZipCode();
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getNumber() {
        return number;
    }

    public String getZipCode() {
        return zipCode;
    }
}
