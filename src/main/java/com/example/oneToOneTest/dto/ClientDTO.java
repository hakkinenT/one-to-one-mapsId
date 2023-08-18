package com.example.oneToOneTest.dto;

import com.example.oneToOneTest.entities.Client;

public class ClientDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

    private AddressDTO address;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String email, String phone, AddressDTO address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        address = (entity.getAddress() == null) ? null : new AddressDTO(entity.getAddress());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public AddressDTO getAddress() {
        return address;
    }
}
