package com.mymo.merger.fixtures;

import java.math.*;
import java.util.*;

public class FixtureFactory {
    public static final Long ADDRESS_ID = 1234L;
    public static final Long CUSTOMER_ID = 5678L;
    public static final Long ORDER_ID = 9876L;
    public static final Long PRODUCT_ID = 4321L;

    public static Address getAddress() {
        Address address = new Address();
        address.setId(ADDRESS_ID);
        address.setCompanyName("A Company Ltd.");
        address.setLine1("13 Letsbe Avenue");
        address.setLine2("1st Floor");
        address.setAptOrSuite("1");
        address.setCity("Chicago");
        address.setCountry(Country.AMERICA);
        address.setState(State.ILLINOIS);
        address.setPostalCode("60601");
        address.setPhone1("312 111 1111");
        address.setPhone2("312 222 2222");

        return address;
    }

    public static Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customer.setFirstName("John");
        customer.setFirstName("Hancock");
        customer.setEmailAddress("j.hancock@chicago.com");
        customer.setAddress(getAddress());

        return customer;
    }

    public static Product getProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName("A Tool");
        product.setDescription("Small handheld device");
        product.setPrice(BigDecimal.valueOf(100));
        product.setBarCode("barcode".getBytes());
        product.setManufacturer(getAddress());

        return product;
    }

    public static Order getOrder() {
        Order order = new Order();
        order.setId(ORDER_ID);
        order.setCustomer(getCustomer());
        order.setProduct(getProduct());
        order.setQuantity(10);
        order.setOrderDate(new Date());

        return order;
    }
}
