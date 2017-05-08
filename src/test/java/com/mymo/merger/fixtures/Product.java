package com.mymo.merger.fixtures;

import com.mymo.merger.annotation.*;

import java.math.*;

@Mergeable
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private byte[] barCode;
    private Address manufacturer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte[] getBarCode() {
        return barCode;
    }

    public void setBarCode(byte[] barCode) {
        this.barCode = barCode;
    }

    public Address getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Address manufacturer) {
        this.manufacturer = manufacturer;
    }
}
