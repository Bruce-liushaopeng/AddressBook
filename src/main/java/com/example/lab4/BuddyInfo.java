package com.example.lab4;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String address;

    public BuddyInfo() {
        this.name = "NULL";
        this.address = "NULL";
    }
    public BuddyInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
